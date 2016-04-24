/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.francisco.servidor.java.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francisco Ernesto Teixeira <fco.ernesto@gmail.com>
 */
@Entity
@Table(name = "Pessoa")
@GenericGenerator(name = "mongodb_uuidgg", strategy = "uuid2")
@XmlRootElement
public class Pessoa implements Comparable<Pessoa>, Serializable {

    @Id
    @GeneratedValue(generator = "mongodb_uuidgg")
    private String id;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date cadastro;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nascimento;

    @Version
    private Integer versao;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Pessoa other = (Pessoa) obj;

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int compareTo(Pessoa o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
