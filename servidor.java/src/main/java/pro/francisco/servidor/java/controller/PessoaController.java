/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.francisco.servidor.java.controller;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import pro.francisco.servidor.java.model.Pessoa;

/**
 *
 * @author Francisco Ernesto Teixeira <fco.ernesto@gmail.com>
 */ 
@Path("/Pessoas")
public class PessoaController {
 
    @GET
    public List<Pessoa> listContatos() throws Exception {
        /*
        List<ContatoBean> ret = new ArrayList<ContatoBean>();
        DataSource ds = (DataSource) new InitialContext()
                .lookup("java:comp/env/jdbc/agenda");
        Connection con = ds.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm
                .executeQuery("select id,nome,endereco,telefone,data_nascimento from tb_contatos");
        while (rs.next()) {
            ret.add(new ContatoBean(rs.getInt(1), //
                    rs.getString(2), //
                    rs.getString(3), //
                    rs.getString(4), //
                    rs.getDate(5)));
        }
        rs.close();
        stm.close();
        con.close();
        return ret;
        */
        return null;
    }
 
    @POST
    public void addContato(Pessoa c) throws Exception {/*
        DataSource ds = (DataSource) new InitialContext()
                .lookup("java:comp/env/jdbc/agenda");
        Connection con = ds.getConnection();
        PreparedStatement pst = con
                .prepareStatement("insert into tb_contatos(nome,endereco,telefone, data_nascimento) values (?,?,?,?)");
        pst.setString(1, c.getNome());
        pst.setString(2, c.getEndereco());
        pst.setString(3, c.getTelefone());
        pst.setDate(4, new Date(c.getDataNasc().getTime()));
        pst.executeUpdate();
        pst.close();
        con.close();*/
    }
}