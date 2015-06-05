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

    public Estilo(String nomeDoEstilo) throws Exception {
        isValidNameStyle(nomeDoEstilo);
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

    public void setNomeDoEstilo(String nome) throws Exception {
        isValidNameStyle(nome);
    }

    private void isValidNameStyle(String nomeDoEstilo) throws Exception {
        if (nomeDoEstilo == null || nomeDoEstilo.trim().isEmpty()){
            throw new Exception ("Preencha o campo: Nome do estilo");
        }

        this.nomeDoEstilo = nomeDoEstilo.toUpperCase();
    }

    @Override
    public String toString() {
        return nomeDoEstilo;
    }
}