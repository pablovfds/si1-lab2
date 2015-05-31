package models.repository;

import models.entity.Instrumento;

public class RepositorioDeInstrumentos extends GenericRepository<Instrumento> {
	
	private static RepositorioDeInstrumentos instance;

	private RepositorioDeInstrumentos() {
		super(Instrumento.class);
	}
	
	public static RepositorioDeInstrumentos getInstance() {
		if(instance == null) {
			instance = new RepositorioDeInstrumentos();
		}
		return instance;
	}

}
