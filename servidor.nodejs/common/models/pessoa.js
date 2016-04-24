module.exports = function (Pessoa) {

    Pessoa.beforeCreate = function (next, data) {
        data.cadastro = new Date();
        next();
    };
};