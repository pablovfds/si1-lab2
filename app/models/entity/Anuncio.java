package models.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "anuncio")
public class Anuncio implements Serializable, Comparable<Anuncio> {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "dataDeCriacao")
	@Temporal(value = TemporalType.DATE)
	private Calendar dataDeCriacao;

	@Column(name = "buscaPor", nullable = false)
	private String buscaPor;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "perfil_do_facebook", unique = true)
	private String perfil_do_facebook;

	@Column(name = "cidade", nullable = false)
	private String cidade;

	@Column(name = "bairro", nullable = false)
	private String bairro;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "instrumentos")
	private List<Instrumento> instrumentos;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "estilosQueGosta")
	private List<Estilo> estilosQueGosta;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "estilosQueNaoGosta")
	private List<Estilo> estilosQueNaoGosta;

	@Column(name = "codigoDoAnuncio", nullable = false)
	private String codigoDoAnuncio;

	@Transient
	private static final long serialVersionUID = 1L;

	public Anuncio() {
		dataDeCriacao = Calendar.getInstance();
	}

	public Anuncio(String titulo, String descricao, String codigoDoAnuncio, String cidade, String bairro,
				   String email, String perfil_do_facebook, String buscaPor, List<Instrumento> instrumentoList,
				   List<Estilo> estilosQueGosta, List<Estilo> estilosQueNaoGosta) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.codigoDoAnuncio = codigoDoAnuncio;
		this.cidade = cidade;
		this.bairro = bairro;
		this.email = email;
		this.perfil_do_facebook = perfil_do_facebook;
		this.buscaPor = buscaPor;
		this.instrumentos = instrumentoList;
		this.estilosQueGosta = estilosQueGosta;
		this.estilosQueNaoGosta = estilosQueNaoGosta;
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

	public List<Estilo> getEstilosQueGosta() {
		return estilosQueGosta;
	}

	public List<Estilo> getEstilosQueNaoGosta() {
		return estilosQueNaoGosta;
	}

	public List<Instrumento> getInstrumentos() { return instrumentos; }

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEmail() {
		return email;
	}

	public String getPerfil_do_facebook() {
		return perfil_do_facebook;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEstilosQueGosta(List<Estilo> estilosQueGosta) {
		this.estilosQueGosta = estilosQueGosta;
	}

	public void setEstilosQueNaoGosta(List<Estilo> estilosQueNaoGosta) {
		this.estilosQueNaoGosta = estilosQueNaoGosta;
	}

	public void setInstrumentos(List<Instrumento> instrumentos) {
		this.instrumentos = instrumentos;
	}

	public void setPerfil_do_facebook(String perfil_do_facebook) {
		this.perfil_do_facebook = perfil_do_facebook;
	}

	public String getCodigoDoAnuncio() { return codigoDoAnuncio; }

	public void setCodigoDoAnuncio(String codigoDoAnuncio) { this.codigoDoAnuncio = codigoDoAnuncio; }

	@Override
	public int compareTo(Anuncio outroAnuncio) {
		if (this.dataDeCriacao.before(outroAnuncio.getDataDeCriacao())){
			return -1;
		}
		else {
			return 1;
		}
	}
}