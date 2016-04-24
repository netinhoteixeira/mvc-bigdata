/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.francisco.servidor.java.service;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.mongodb.MongoClient;

/**
 * Mantém a instância do Morphia e Datastore.
 *
 * @author Francisco Ernesto Teixeira <fco.ernesto@gmail.com>
 */
public class MorphiaService {

    private final String databaseName = "mvc-bigdata";
    private Morphia morphia;
    private Datastore datastore;

    public MorphiaService() {
        MongoClient mongoClient = new MongoClient("127.0.0.1:27017");

        // Cria uma nova instância do Morphia
        this.morphia = new Morphia();

        this.datastore = morphia.createDatastore(mongoClient, databaseName);
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

}
