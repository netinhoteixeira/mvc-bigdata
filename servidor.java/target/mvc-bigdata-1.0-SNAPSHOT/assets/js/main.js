// o módulo principal (app)
var app = angular.module('app', ['ngResource']);

// adiciona um serviço
app.service('DataService', function () {

    /**
     * Calcula a idade com a data de nascimento fornecida.
     * 
     * @param {String} nascimento
     * @return {Number}
     */
    this.calcularIdade = function (nascimento) {
        var today = new Date();
        var birthDate = new Date(nascimento);
        var age = today.getFullYear() - birthDate.getFullYear();
        var m = today.getMonth() - birthDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }

        return age;
    };

    this.formatarParaEditar = function (data) {
        var pedacos = data.split('-');

        return pedacos[2] + '/' + pedacos[1] + '/' + pedacos[0];
    };

    this.formatarParaSalvar = function (data) {
        var pedacos = data.split('/');

        return pedacos[2] + '-' + pedacos[1] + '-' + pedacos[0];
    };

    this.processarSalvo = function (data) {
        return data.substring(0, 10)
    };
});

// adiciona um controlador
app.controller('PrincipalController', function ($scope, $resource, DataService) {
    var Pessoa = $resource('/api/Pessoas/:id', {id: '@id'}, {
        // o loopback.io não possui o POST para salvar
        'save': {
            method: 'PUT'
        }
    });

    $scope.pessoas = [];
    $scope.atualizado = false;
    $scope.mostrarFormulario = false;
    $scope.pessoa = {
        id: null,
        nome: null,
        nascimento: null,
        idade: null
    };

    $scope.atualizar = function () {
        $scope.mostrarFormulario = false;
        $scope.pessoas = Pessoa.query(function (dados) {

            // varre os dados encontrados
            for (var i = 0; i < dados.length; i++) {
                if (dados[i].cadastro !== null) {
                    dados[i].cadastro = new Date(dados[i].cadastro);
                }

                // caso haja data de nascimento
                if (dados[i].nascimento !== null) {
                    dados[i].idade = DataService.calcularIdade(dados[i].nascimento);

                    dados[i].nascimento = dados[i].nascimento.split('T');
                    dados[i].nascimento = dados[i].nascimento[0];
                }
            }

            return dados;
        });
    };

    $scope.adicionar = function () {
        $scope.pessoa = new Pessoa();
        $scope.mostrarFormulario = true;
    };

    $scope.editar = function (pessoa) {
        $scope.pessoa = pessoa;
        $scope.pessoa.nascimento = DataService.formatarParaEditar($scope.pessoa.nascimento);

        $scope.mostrarFormulario = true;
    };

    $scope.salvar = function () {
        var novoRegistro = (typeof $scope.pessoa.id === 'undefined');

        $scope.pessoa.nascimento = DataService.formatarParaSalvar($scope.pessoa.nascimento);

        $scope.pessoa.$save(function (pessoa) {
            pessoa.nascimento = DataService.processarSalvo(pessoa.nascimento);
            pessoa.idade = DataService.calcularIdade(pessoa.nascimento);
            $scope.pessoa = pessoa;

            if (novoRegistro) {
                $scope.atualizar();
            }
        });
        $scope.mostrarFormulario = false;
    };

    $scope.cancelar = function () {
        $scope.mostrarFormulario = false;
    };

    $scope.remover = function (pessoa) {
        $scope.mostrarFormulario = false;
        pessoa.$remove();
    };

    $scope.atualizar();
});