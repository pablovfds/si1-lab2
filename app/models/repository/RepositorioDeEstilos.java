package models.repository;

import models.entity.Estilo;

public class RepositorioDeEstilos extends GenericRepository<Estilo> {

	private static RepositorioDeEstilos instance;

	private RepositorioDeEstilos() {
		super(Estilo.class);
	}
	
	public static RepositorioDeEstilos getInstance() {
		if(instance == null) {
			instance = new RepositorioDeEstilos();
		}
		return instance;
	}
}
