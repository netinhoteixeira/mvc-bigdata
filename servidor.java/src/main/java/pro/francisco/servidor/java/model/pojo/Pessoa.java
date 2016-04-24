/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.francisco.servidor.java.model.pojo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author Francisco Ernesto Teixeira <fco.ernesto@gmail.com>
 */
@Entity("Pessoa")
public class Pessoa extends BaseEntity {

    private Date cadastro;
    private String nome;
    private Date nascimento;

    /**
     * Um construtor vazio é necessário para que o Morphia possa recriar essa
     * entidade quando você deseje obter ela do banco de dados.
     */
    public Pessoa() {
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public int getIdade() {
        if (this.nascimento != null) {
            Instant instant = Instant.ofEpochMilli(this.nascimento.getTime());
            LocalDate start = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
            LocalDate end = LocalDate.now();

            return (int) ChronoUnit.YEARS.between(start, end);
        } else {
            return -1;
        }
    }

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

}
