package models.repository;

import java.util.List;

import models.entity.Anuncio;

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
	
	public List<Anuncio> findByTitle(String title) {
		return null;
	}
}
