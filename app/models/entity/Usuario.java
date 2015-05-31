package models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity(name = "usuario")
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
		
	@OneToOne(mappedBy = "usuario")
	private Endereco endereco;
	
	@OneToOne(mappedBy = "user")
	private Contato contato;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Instrumento> instrumentos;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Estilo> estilosQueGosta;


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Estilo> estilosQueNaoGosta;
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	public Usuario() {
		instrumentos = new ArrayList<Instrumento>();
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

	public void setNome(String name) {
		this.nome = name;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Instrumento> getInstrumentos() {
		return instrumentos;
	}

	public void setInstrumentos(List<Instrumento> instrumentos) {
		this.instrumentos = instrumentos;
	}

	public List<Estilo> getEstilosQueGosta() {
		return estilosQueGosta;
	}

	public void setEstilosQueGosta(List<Estilo> estilosQueGosta) {
		this.estilosQueGosta = estilosQueGosta;
	}

	public List<Estilo> getEstilosQueNaoGosta() {
		return estilosQueNaoGosta;
	}

	public void setEstilosQueNaoGosta(List<Estilo> estilosQueNaoGosta) {
		this.estilosQueNaoGosta = estilosQueNaoGosta;
	}
}
