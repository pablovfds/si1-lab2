package models.repository;

import java.util.ArrayList;
import java.util.List;

import models.entity.Anuncio;

import javax.persistence.Query;

public class RepositorioDeAnuncios extends GenericRepository<Anuncio> {
	
	private static RepositorioDeAnuncios instance;

	private RepositorioDeAnuncios() {
		super(Anuncio.class);
	}
	
	public static RepositorioDeAnuncios getInstance() {
		if(instance == null) {
			instance = new RepositorioDeAnuncios();
		}

		return instance;
	}
}
