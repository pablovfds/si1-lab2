package models.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "anuncio")
public class Anuncio implements Serializable, Comparable<Anuncio> {

	@Id
	@GeneratedValue
	private long id;

	@Min(1)
	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Min(10)
	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "dataDeCriacao")
	@Temporal(value = TemporalType.DATE)
	private Calendar dataDeCriacao;

	@Column(name = "buscaPor", nullable = false)
	private String buscaPor;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "perfilDoFacebook", unique = true)
	private String perfilDoFacebook;

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
	}

	public Anuncio(String titulo, String descricao, String codigoDoAnuncio, String cidade, String bairro,
				   String email, String perfil_do_facebook, String buscaPor, List<Instrumento> instrumentoList,
				   List<Estilo> estilosQueGosta, List<Estilo> estilosQueNaoGosta) throws Exception {
		isValidTitle(titulo);
		isValidDescription(descricao);
		isValidCodigoDoAnuncio(codigoDoAnuncio);
		isValidCity(cidade);
		isValidDistrict(bairro);
		isValidEmail(email);
		isValidProfile(perfil_do_facebook);
		isValidBySearch(buscaPor);
		isValidListInstrument(instrumentoList);
		this.estilosQueGosta = estilosQueGosta;
		this.estilosQueNaoGosta = estilosQueNaoGosta;
		this.dataDeCriacao = Calendar.getInstance();
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

	public String getPerfilDoFacebook() {
		return perfilDoFacebook;
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

	public void setPerfil_do_facebook(String perfilDoFacebook) {
		this.perfilDoFacebook = perfilDoFacebook;
	}

	public String getCodigoDoAnuncio() { return codigoDoAnuncio; }

	public void setCodigoDoAnuncio(String codigoDoAnuncio) { this.codigoDoAnuncio = codigoDoAnuncio; }

	private void isValidTitle(String titulo) throws Exception {
		if (titulo.trim().isEmpty() || titulo == null){
			throw new Exception("Preencha o campo: Título");
		}
		this.titulo = titulo.toUpperCase();
	}

	private void isValidDescription(String descricao) throws Exception {
		if (descricao.trim().isEmpty() || descricao == null){
			throw new Exception("Preencha o campo: Descrição");
		}
		this.descricao = descricao.toUpperCase();
	}

	private void isValidCodigoDoAnuncio(String codigoDoAnuncio) throws Exception {
		if (codigoDoAnuncio.trim().isEmpty() || codigoDoAnuncio == null){
			throw new Exception("Preencha o campo: Codigo do anúncio");
		}
		this.codigoDoAnuncio = codigoDoAnuncio.toUpperCase();
	}

	private void isValidCity(String cidade) throws Exception {
		if (cidade.trim().isEmpty() || cidade == null){
			throw new Exception("Preencha o campo: Cidade");
		}
		this.cidade = cidade.toUpperCase();
	}

	private void isValidDistrict(String bairro) throws Exception {
		if (bairro.trim().isEmpty() || bairro == null){
			throw new Exception("Preencha o campo: Bairro");
		}
		this.titulo = titulo.toUpperCase();
	}

	private void isValidEmail(String email) throws Exception {
		Pattern p = Pattern.compile(".+@.+/.[a-z]+");

		Matcher m = p.matcher(email);

		boolean matchFound = m.matches();

		if (matchFound || email.trim().isEmpty() || email == null){
			throw new Exception("Preencha o campo: E-mail");
		}
		this.email = email.toUpperCase();
	}

	private void isValidProfile(String perfilDoFacebook) throws Exception {
		if (perfilDoFacebook.trim().isEmpty() || perfilDoFacebook == null){
			throw new Exception("Preencha o campo: Perfil do Facebook");
		}
		this.titulo = titulo.toUpperCase();
	}

	private void isValidBySearch(String buscaPor) throws Exception {
		if (buscaPor.trim().isEmpty() || buscaPor == null){
			throw new Exception("Preencha o campo: Busca por");
		}
		this.buscaPor = buscaPor.toUpperCase();
	}

	private void isValidListInstrument(List<Instrumento> instrumentoList) throws Exception {
		if (instrumentoList == null){
			throw new Exception("Preencha o campo: Instrumento que toco");
		}
		this.instrumentos = instrumentoList;
	}

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