package models.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "endereco")
public class Endereco implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "cidade", nullable = false)
	private String cidade;
	
	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Usuario usuario;
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	public Endereco() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
