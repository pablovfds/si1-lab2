package models.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "anuncio")
public class Anuncio implements Serializable, Comparable<Anuncio> {

	@Id
	@GeneratedValue
	private long id;
	@Max(100)
	@Min(1)
	@Column(name = "titulo", nullable = false, columnDefinition = "")
	private String titulo;

	@Max(200)
	@Min(10)
	@Column(name = "descricao", nullable = false, columnDefinition = "")
	private String descricao;

	@Column(name = "dataDeCriacao")
	@Temporal(value = TemporalType.DATE)
	private Date dataDeCriacao;

	@Column(name = "buscaPor", nullable = false, columnDefinition = "")
	private String buscaPor;

	@Max(100)
	@Column(name = "email", unique = true, columnDefinition = "")
	private String email;

	@Max(100)
	@Column(name = "perfilDoFacebook", unique = true, columnDefinition = "")
	private String perfilDoFacebook;

	@Min(2)
	@Max(50)
	@Column(name = "cidade", nullable = false)
	private String cidade;

	@Min(2)
	@Max(100)
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

	@Max(5)
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
		this.dataDeCriacao = new Date();
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

	public void setTitulo(String titulo) throws Exception {
		isValidTitle(titulo);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String subject) throws Exception {
		isValidDescription(subject);
	}

	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public String getBuscaPor() {
		return buscaPor;
	}

	public void setBuscaPor(String searchFor) throws Exception {
		isValidBySearch(searchFor);
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

	public void setBairro(String bairro) throws Exception {
		isValidDistrict(bairro);
	}

	public void setCidade(String cidade) throws Exception {
		isValidCity(cidade);
	}

	public void setEmail(String email) throws Exception {
		isValidEmail(email);
	}

	public void setEstilosQueGosta(List<Estilo> estilosQueGosta) {
		this.estilosQueGosta = estilosQueGosta;
	}

	public void setEstilosQueNaoGosta(List<Estilo> estilosQueNaoGosta) {
		this.estilosQueNaoGosta = estilosQueNaoGosta;
	}

	public void setInstrumentos(List<Instrumento> instrumentos) throws Exception {
		isValidListInstrument(instrumentos);
	}

	public void setPerfilDoFacebook(String perfilDoFacebook) throws Exception {
		isValidProfile(perfilDoFacebook);
	}

	public String getCodigoDoAnuncio() { return this.codigoDoAnuncio; }

	public void setCodigoDoAnuncio(String codigoDoAnuncio) throws Exception {
		isValidCodigoDoAnuncio(codigoDoAnuncio);
	}

	private void isValidTitle(String titulo) throws Exception {
		if (titulo == null || titulo.trim().isEmpty()){
			throw new Exception("Preencha o campo: Titulo");
		}

		if (titulo.length() > 0 && titulo.length() < 100){
			throw new Exception("Preencha o campo: 'Titulo' com no máximo 100 caracteres.");
		}

		this.titulo = titulo.toUpperCase();
	}

	private void isValidDescription(String descricao) throws Exception {
		if (descricao == null || descricao.trim().isEmpty()){
			throw new Exception("Preencha o campo: Descricao");
		}

		if (descricao.length() >= 10 && descricao.length() < 200){
			throw new Exception("Preencha o campo: 'Descricao' com no máximo 200 caracteres.");
		}

		this.descricao = descricao.toUpperCase();
	}

	private void isValidCodigoDoAnuncio(String codigoDoAnuncio) throws Exception {
		if (codigoDoAnuncio == null || codigoDoAnuncio.trim().isEmpty()){
			throw new Exception("Preencha o campo: Codigo do anuncio");
		}
		else if (codigoDoAnuncio.length() > 5){
			throw new Exception("Seu codigo do anuncio deve ter no maximo 5 caracteres.");
		}
		else {
			this.codigoDoAnuncio = codigoDoAnuncio;
		}
	}

	private void isValidCity(String cidade) throws Exception {
		if ( cidade == null || cidade.trim().isEmpty() ){
			throw new Exception("Preencha o campo: Cidade");
		}
		if (cidade.length() > 2 && cidade.length() < 50){
			throw new Exception("Preencha o campo: 'Cidade' com no máximo 50 caracteres.");
		}

		this.cidade = cidade.toUpperCase();
	}

	private void isValidDistrict(String bairro) throws Exception {
		if (bairro == null || bairro.trim().isEmpty()){
			throw new Exception("Preencha o campo: Bairro");
		}
		if (bairro.length() > 2 && bairro.length() < 100){
			throw new Exception("Preencha o campo: 'Bairro' com no máximo 100 caracteres.");
		}

		this.bairro = bairro.toUpperCase();
	}

	private void isValidEmail(String email) throws Exception {
		if (email == null || email.trim().isEmpty()){
			throw new Exception("Preencha o campo: E-mail");
		}
		Pattern p = Pattern.compile(".+@.+/.[a-z]+");
		Matcher m = p.matcher(email);

		if (m.matches()){
			throw new Exception("Preencha o campo: E-mail");
		}
		this.email = email;
	}

	private void isValidProfile(String perfilDoFacebook) throws Exception {
		if (perfilDoFacebook == null || perfilDoFacebook.trim().isEmpty()){
			throw new Exception("Preencha o campo: Perfil do Facebook");
		}
		this.perfilDoFacebook = perfilDoFacebook.toLowerCase();
	}

	private void isValidBySearch(String searchFor) throws Exception {
		if (searchFor == null || searchFor.trim().isEmpty()){
			throw new Exception("Preencha o campo: Busca por");
		}
		this.buscaPor = searchFor.toUpperCase();
	}

	private void isValidListInstrument(List<Instrumento> instrumentoList) throws Exception {
		if (instrumentoList == null){
			throw new Exception("Preencha o campo: Instrumento que toco");
		}
		this.instrumentos = instrumentoList;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Anuncio)){
			return false;
		}

		Anuncio obj1 = (Anuncio) obj;
		return this.hashCode() == obj1.hashCode();
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

	@Override
	public int hashCode() {
		return getTitulo().hashCode() + getCodigoDoAnuncio().hashCode() + getDataDeCriacao().hashCode();
	}
}