/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.francisco.servidor.java;

import pro.francisco.servidor.java.controller.PessoaController;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Francisco Ernesto Teixeira <fco.ernesto@gmail.com>
 */
@ApplicationPath("/api")
public class Main extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new TreeSet<Class<?>>();
        set.add(PessoaController.class);

        return set;
    }
}
