/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.francisco.servidor.java.controller;

import java.util.List;
import javax.ws.rs.DELETE;
import pro.francisco.servidor.java.model.pojo.Pessoa;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pro.francisco.servidor.java.dao.PessoaDAO;
import pro.francisco.servidor.java.model.dto.PessoaDTO;
import pro.francisco.servidor.java.service.MorphiaService;

/**
 *
 * @author Francisco Ernesto Teixeira <fco.ernesto@gmail.com>
 */
@Path("/cadastro/pessoa")
public class PessoaController {

    private final MorphiaService morphiaService;
    private final PessoaDAO pessoaDAO;

    public PessoaController() {
        this.morphiaService = new MorphiaService();
        this.pessoaDAO = new PessoaDAO(Pessoa.class, morphiaService.getDatastore());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PessoaDTO> listar() throws Exception {
        return pessoaDAO.listar();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public PessoaDTO adicionar(PessoaDTO pessoa) throws Exception {
        return pessoaDAO.salvar(pessoa);
    }

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PessoaDTO editar(@PathParam("id") final String id, PessoaDTO pessoa) throws Exception {
        return pessoaDAO.salvar(pessoa);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean remover(@PathParam("id") final String id) throws Exception {
        try {
            pessoaDAO.remover(id);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
