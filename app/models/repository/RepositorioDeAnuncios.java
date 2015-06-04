package models.repository;

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

	public List<Anuncio> findByAttributeName(String attributeValue) {
		String hql = "FROM " + "anuncio" + " c" + " WHERE c."
				+ "titulo" + " = '" + attributeValue + "' or "
				+ "descricao" + " = '" + attributeValue + "' or "
				+ "buscaPor" + " = '" + attributeValue + "' or "
				+ "estilosQueGosta" + " = '" + attributeValue + "' or "
				+ "estilosQueNaoGosta" + " = '" + attributeValue + "'";
		Query query = getEm().createQuery(hql);
		return query.getResultList();
	}
}
