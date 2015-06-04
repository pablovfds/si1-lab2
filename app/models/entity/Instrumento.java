package models.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity(name = "instrumento")
public class Instrumento implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "nomeDoInstrumento", nullable = false)
    private String nomeDoInstrumento;

    @Transient
    private static final long serialVersionUID = 1L;

    public Instrumento() {
    }

    public Instrumento(String nome) {
        this.nomeDoInstrumento = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeDoInstrumento() {
        return nomeDoInstrumento;
    }

    public void setNomeDoInstrumento(String nome) {
        this.nomeDoInstrumento = nome;
    }
}