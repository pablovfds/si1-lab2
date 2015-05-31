package models.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity(name = "instruments")
public class Instrumento implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;

	@Transient
	private static final long serialVersionUID = 1L;
	
	public Instrumento() {
	} 

	public Instrumento(String nome) {
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
