/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.francisco.servidor.java.dao;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Francisco Ernesto Teixeira <fco.ernesto@gmail.com>
 */
public interface BaseDAOInterface<O, DTO> {

    public List<DTO> listar() throws Exception;

    public O obter(String id) throws Exception;

    public O obter(ObjectId id) throws Exception;

    public DTO salvar(DTO dto) throws Exception;

    public void remover(String id) throws Exception;

}
