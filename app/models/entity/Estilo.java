package models.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "estilo")
public class Estilo implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "nomeDoEstilo", nullable = false)
    private String nomeDoEstilo;

    @Transient
    private static final long serialVersionUID = 1L;

    public Estilo() {
    }

    public Estilo(String nomeDoEstilo) {
        this.nomeDoEstilo = nomeDoEstilo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeDoEstilo() {
        return nomeDoEstilo;
    }

    public void setNomeDoEstilo(String nome) {
        this.nomeDoEstilo = nome;
    }
}