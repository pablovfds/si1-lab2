package models.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "anuncio")
public class Anuncio implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Size(min = 15)
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "dataDeCriacao")
	@Temporal(value = TemporalType.DATE)
	private Calendar dataDeCriacao;
	
	@Column(name = "buscaPor", nullable = false)
	private String buscaPor;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idDoUsuario", nullable = false)
	private Usuario usuario;
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	public Anuncio() {
		dataDeCriacao = Calendar.getInstance();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String subject) {
		this.descricao = subject;
	}

	public Calendar getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(Calendar createdOn) {
		this.dataDeCriacao = createdOn;
	}
	
	public String getBuscaPor() {
		return buscaPor;
	}

	public void setBuscaPor(String searchFor) {
		this.buscaPor = searchFor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
