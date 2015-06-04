package models.repository;

import java.util.ArrayList;
import java.util.List;

import models.entity.Anuncio;

import javax.persistence.Query;

public class RepositorioDeAnuncios extends GenericRepository<Anuncio> {
	
	private static RepositorioDeAnuncios instance;
	private List<Anuncio> listaResultado;
	private List<String> listaDeAssuntos;
	private List<Anuncio> listaDeAnuncios;
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
		listaResultado = new ArrayList<>();
		listaDeAssuntos = new ArrayList<>();

		listaDeAssuntos.add("titulo");
		listaDeAssuntos.add("descricao");
		listaDeAssuntos.add("buscaPor");
		listaDeAssuntos.add("estilosQueGosta");
		listaDeAssuntos.add("estilosQueNaoGosta");

		for (String assunto : listaDeAssuntos){
			listaDeAnuncios = instance.findByAttributeName(assunto, attributeValue);
			for (Anuncio anuncio : listaDeAnuncios){
				if	(!listaResultado.contains(anuncio)){
					listaResultado.add(anuncio);
				}
			}
		}

		return listaResultado;
	}
}
