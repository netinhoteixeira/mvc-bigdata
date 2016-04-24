/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.francisco.servidor.java.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import pro.francisco.servidor.java.model.dto.PessoaDTO;
import pro.francisco.servidor.java.model.pojo.Pessoa;

/**
 *
 * @author Francisco Ernesto Teixeira <fco.ernesto@gmail.com>
 */
public class PessoaDAO extends BasicDAO<Pessoa, String> implements BaseDAOInterface<Pessoa, PessoaDTO> {

    private final SimpleDateFormat formatoData;

    public PessoaDAO(Class<Pessoa> entityClass, Datastore dataStore) {
        super(entityClass, dataStore);
        this.formatoData = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public List<PessoaDTO> listar() throws Exception {
        List<PessoaDTO> lista = null;
        List<Pessoa> pessoas = this.createQuery().asList();

        if (pessoas != null) {
            lista = new ArrayList<>();

            for (Pessoa pessoa : pessoas) {
                lista.add(this.converterPessoaSaida(pessoa));
            }
        }

        return lista;
    }

    @Override
    public Pessoa obter(String id) throws Exception {
        return this.obter(new ObjectId(id));
    }

    @Override
    public Pessoa obter(ObjectId id) {
        Pessoa pessoa = this.findOne("_id", id);
        if (pessoa != null) {

            return pessoa;
        } else {
            return null;
        }
    }

    @Override
    public PessoaDTO salvar(PessoaDTO p) throws Exception {
        Pessoa pessoa;

        if (p.getId() != null) {
            pessoa = this.obter(p.getId());
        } else {
            pessoa = new Pessoa();
            pessoa.setCadastro(new Date());
        }

        pessoa.setNome(p.getNome());
        pessoa.setNascimento(formatoData.parse(p.getNascimento()));

        Key<Pessoa> chave = this.save(pessoa);

        return this.converterPessoaSaida(this.obter((ObjectId) chave.getId()));
    }

    private PessoaDTO converterPessoaSaida(Pessoa pessoa) {
        PessoaDTO p = new PessoaDTO();
        p.setId(pessoa.getId().toString());
        p.setCadastro(formatoData.format(pessoa.getCadastro()));
        p.setNome(pessoa.getNome());
        p.setNascimento(formatoData.format(pessoa.getNascimento()));
        p.setIdade(pessoa.getIdade());

        return p;
    }

    @Override
    public void remover(String id) throws Exception {
        this.delete(this.obter(id));
    }

}
