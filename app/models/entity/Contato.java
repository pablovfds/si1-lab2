package models.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Transient;

@Entity(name = "contato")
public class Contato implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "perfil_do_facebook", unique = true)
	private String perfil_do_facebook;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Usuario user;
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	public Contato() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerfil_do_facebook() {
		return perfil_do_facebook;
	}

	public void setPerfil_do_facebook(String phofile) {
		this.perfil_do_facebook = phofile;
	}
}
