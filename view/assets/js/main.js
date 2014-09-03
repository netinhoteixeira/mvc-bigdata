var app = angular.module('app', ['ngResource']);

function PrincipalController($scope, $resource) {
    var Pessoa = $resource('/cadastro/pessoa/:id', {id: '@id'});

    $scope.pessoas = [];
    $scope.atualizado = false;
    $scope.mostrarFormulario = false;
    $scope.pessoa = {
        id: null,
        nome: null,
        nascimento: null,
        idade: null
    };

    $scope.atualizar = function() {
        $scope.mostrarFormulario = false;
        $scope.pessoas = Pessoa.query(function() {
        });
    };

    $scope.adicionar = function() {
        $scope.pessoa = new Pessoa();
        $scope.mostrarFormulario = true;
    };

    $scope.editar = function(pessoa) {
        $scope.pessoa = pessoa;
        $scope.mostrarFormulario = true;
    };

    $scope.salvar = function() {
        var novoRegistro = ($scope.pessoa.id === null);
        $scope.pessoa.$save(function(pessoa) {
            $scope.pessoa = pessoa;
        });
        $scope.mostrarFormulario = false;

        if (novoRegistro) {
            $scope.atualizar();
        }
    };

    $scope.cancelar = function() {
        $scope.mostrarFormulario = false;
    };

    $scope.remover = function(pessoa) {
        $scope.mostrarFormulario = false;
        pessoa.$remove();
    };

    $scope.atualizar();
}
