<?php

require_once './vendor/autoload.php';

use Doctrine\Common\ClassLoader,
    Doctrine\ODM\MongoDB\Configuration,
    Doctrine\ODM\MongoDB\Mapping\Driver\AnnotationDriver,
    Doctrine\ODM\MongoDB\DocumentManager,
    Doctrine\MongoDB\Connection;

$config = new Configuration();
$config->setProxyDir(__DIR__ . '/cache');
$config->setProxyNamespace('Proxies');

$config->setHydratorDir(__DIR__ . '/cache');
$config->setHydratorNamespace('Hydrators');

$annotationDriver = $config->newDefaultAnnotationDriver(array(__DIR__ . '/model'));
$config->setMetadataDriverImpl($annotationDriver);
AnnotationDriver::registerAnnotationClasses();

$dm = DocumentManager::create(new Connection(), $config);

// DONE: Muito importante, certifica-se que os índices foram criados
$dm->getSchemaManager()->ensureIndexes();

// classes do modelo
$classLoader = new ClassLoader('Model', __DIR__);
$classLoader->register();

// aplicativo
$app = new Slim\Slim();

$app->get('/', function() {
    include_once './view/index.html';
});

// listagem
$app->get('/cadastro/pessoa', function() {
    global $dm;

    echo json_encode($dm
                    ->getRepository('Model\Pessoa')
                    ->findAll());
});

// obter registro
$app->get('/cadastro/pessoa/:id', function($id) {
    print_r($id);
    // não implementado
});

// persistir novo registro
$app->post('/cadastro/pessoa', function() {
    global $dm;

    // DONE: Méau
    $request_body = file_get_contents('php://input');
    $data = json_decode($request_body);

    $pessoa = new Model\Pessoa();
    $pessoa->setNome($data->nome);
    $pessoa->setNascimento(new DateTime($data->nascimento));

    $dm->persist($pessoa);
    $dm->flush();

    echo json_encode($pessoa);
});

// persistir registro existente
$app->post('/cadastro/pessoa/:id', function($id) {
    global $dm;

    // DONE: Méau
    $request_body = file_get_contents('php://input');
    $data = json_decode($request_body);

    $pessoa = $dm->getRepository('Model\Pessoa')->find($id);
    $pessoa->setNome($data->nome);
    $pessoa->setNascimento(new DateTime($data->nascimento));

    $dm->persist($pessoa);
    $dm->flush();

    // força o cálculo da idade
    echo json_encode($dm->getRepository('Model\Pessoa')->find($id));
});

// obter registro
$app->delete('/cadastro/pessoa/:id', function($id) {
    global $dm;

    $pessoa = $dm->getRepository('Model\Pessoa')->find($id);
    $dm->remove($pessoa);
    $dm->flush();

    echo true;
});

$app->run();
