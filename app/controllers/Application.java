package controllers;

import models.entity.Instrument;
import models.entity.Poster;
import models.entity.Style;
import models.entity.User;
import models.exception.NewAdException;
import models.repository.InstrumentRepository;
import models.repository.PosterRepository;
import models.repository.StyleRepository;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.listadeanuncios;
import views.html.index;
import views.html.criarAnuncio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class

		Application extends Controller {
	
	private static final int FIRST_PAGE = 1;
	private static List<Poster> listaDeAnuncios;
	private static InstrumentRepository instrumentos;
	private static StyleRepository estilos;
	private static PosterRepository anuncios;

	
	@Transactional
    public static Result index() {
        return ok(index.render());
    }
	
	@Transactional
	public static Result publique() {
		instrumentos = InstrumentRepository.getInstance();
		estilos = StyleRepository.getInstance();
		return ok(criarAnuncio.render(estilos.findAll(), instrumentos.findAll()));
	}
			
	@Transactional
	public static Result criarAnuncio() {
		anuncios = PosterRepository.getInstance();
		Map<String, String> data = Form.form().bindFromRequest().data();
		

		String titulo = data.get("titulo");
		String descrição = data.get("descricao");
		String email = data.get("email");
		String cidade = data.get("city");
		String bairro = data.get("bairro");
		String perfil = data.get("perfil");
		String interesse = data.get("interesse");
		List<Style> badStyles = getStyleSelectedData("badSty[]");
		List<Style> goodStyles = getStyleSelectedData("goodSty[]");
		List<Instrument> myInstruments = getInstrumentSelectedData(); 
		
		try {			
			User user = new User(email, perfil, cidade, bairro, myInstruments, badStyles, goodStyles);
			Poster poster = new Poster(titulo, descrição, interesse, user);
			anuncios.persist(poster);
			anuncios.flush();
			
			flash("success", String.valueOf(poster.getCode()));
		} catch(NewAdException e) {
			flash("erro", "Anúncio não publicado, pois: " + e.getMessage());
			return redirect("publique");
		} 
		
		return redirect("anuncios");
	}
			
	@Transactional
	private static List<Style> getStyleSelectedData(String key) {
		estilos = StyleRepository.getInstance();
		Map<String, String[]> multipleData = request().body().asFormUrlEncoded();
		
		List<Style> styleList = new ArrayList<Style>();
		String[] requestStyleArray = multipleData.get(key);
		
		if(requestStyleArray != null) {
			for(int i = 0; i < requestStyleArray.length; i++) {
				long id = Long.parseLong(requestStyleArray[i]);
				Style style = estilos.findByEntityId(id);
				if(!styleList.contains(style)) {
					styleList.add(style);
				}
			}
		}
		return styleList;
	}
	
	@Transactional
	private static List<Instrument> getInstrumentSelectedData() {
		instrumentos = InstrumentRepository.getInstance();
		Map<String, String[]> multipleData = request().body().asFormUrlEncoded();
		
		List<Instrument> instrumentList = new ArrayList<>();
		String[] requestInstrumentArray = multipleData.get("myInst[]");
		
		if(requestInstrumentArray != null) {
			for(int i = 0; i < requestInstrumentArray.length; i++) {
				long id = Long.parseLong(requestInstrumentArray[i]);
				Instrument instrument = instrumentos.findByEntityId(id);
				if(!instrumentList.contains(instrument)) {
					instrumentList.add(instrument);
				}
			}
		}
		return instrumentList;
	}
	
	@Transactional
    public static Result anuncio(Long id) {
        return ok(index.render());
    }
	
	@Transactional
	public static Result anuncios(int page, int pageSize, boolean check) {
		if(check) {
			anuncios = PosterRepository.getInstance();
			listaDeAnuncios = anuncios.findAll();
		}
		
		page = page >= FIRST_PAGE ? page : FIRST_PAGE;
		pageSize = pageSize >= FIRST_PAGE ? pageSize : PosterRepository.DEFAULT_RESULTS;
				
		long posterNumber = listaDeAnuncios.size();
		if(page > (posterNumber / pageSize)) {
			page = (int) 
				(Math.ceil(posterNumber / Float.parseFloat(String.valueOf(pageSize))));
		}
		
		Collections.sort(listaDeAnuncios);
		session("actualPage", String.valueOf(page));
		
		int fromIndex = (page - 1) * pageSize;
		int toIndex = 
			fromIndex + pageSize < listaDeAnuncios.size() ? fromIndex + pageSize : listaDeAnuncios.size();
				
		return ok(listadeanuncios.render(listaDeAnuncios.subList(fromIndex, toIndex)));
	}
	
	@Transactional
	public static Result buscarAnuncio() {
		anuncios = PosterRepository.getInstance();
		Map<String, String> data = Form.form().bindFromRequest().data();
		
		String search = data.get("search");
		String titulo = data.get("titulo");
		String cidade = data.get("cidade");
		String date = data.get("data");
		String instrumento = data.get("instrumento");
		String estilo = data.get("estilo");
		String formarBanda = data.get("formar uma banda");
		String tocarOcasionalmente = data.get("tocar ocasionalmente");
		
		if((search == null || search.trim().isEmpty()) && 
				(formarBanda == null && tocarOcasionalmente == null)) {
			flash("erro", " Pesquisa inválida, tente novamente.");
			return redirect("anuncios");
		}
		
		listaDeAnuncios = anuncios.findAll();
		if(search != null && !search.trim().toLowerCase().isEmpty()) {
			if(titulo != null) {
				listaDeAnuncios = listaDeAnuncios.stream().filter(p ->
				search.trim().toLowerCase().contains(p.getTitle().trim().toLowerCase())).
				collect(Collectors.toList());
			}
			if(cidade != null) {
				listaDeAnuncios = listaDeAnuncios.stream().filter(p ->
				search.trim().toLowerCase().toLowerCase().contains(p.getUser().
				getCity().trim().toLowerCase())).collect(Collectors.toList());
			}
			if(date != null) {
				listaDeAnuncios = listaDeAnuncios.stream().filter(p ->
				search.trim().toLowerCase().contains(p.getDateFormat())).
				collect(Collectors.toList());
			}
			if(instrumento != null) {
				List<Poster> posterList = new ArrayList<>();
				for(Poster poster : listaDeAnuncios) {
					for(Instrument instrument : poster.getUser().getInstruments()) {
						if(search.trim().toLowerCase().contains(
								instrument.getNome().trim().toLowerCase())) {
							
							posterList.add(poster);
							break;
						}
					}
				}
				listaDeAnuncios.clear();
				listaDeAnuncios.addAll(posterList);
				
			}
			if(estilo != null) {
				List<Poster> posterList = new ArrayList<>();
				for(Poster poster : listaDeAnuncios) {
					for(Style style : poster.getUser().getGoodStyles()) {
						if(search.trim().toLowerCase().contains(
								style.getNome().trim().toLowerCase())) {
							
							posterList.add(poster);
							break;
						}
					}
				}
				listaDeAnuncios.clear();
				listaDeAnuncios.addAll(posterList);
			}
		}
		if(formarBanda != null && tocarOcasionalmente == null) {
			listaDeAnuncios = listaDeAnuncios.stream().filter(p ->
			formarBanda.trim().contains(p.getSearchFor().trim()))
			.collect(Collectors.toList());
		}
		if(formarBanda == null && tocarOcasionalmente != null) {
			listaDeAnuncios = listaDeAnuncios.stream().filter(p ->
			tocarOcasionalmente.trim().contains(p.getSearchFor().trim()))
			.collect(Collectors.toList());
		}
		
		if(listaDeAnuncios.isEmpty()) {
			flash("notFound", "Nenhum anúncio encontrado com os parâmetros informados");
			return redirect("anuncios");
		}
		
		return ok(listadeanuncios.render(listaDeAnuncios));
	}
}