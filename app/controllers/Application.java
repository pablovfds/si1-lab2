package controllers;

import models.entity.*;
import models.repository.RepositorioDeAnuncios;
import models.repository.RepositorioDeEstilos;
import models.repository.RepositorioDeInstrumentos;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.*;

public class Application extends Controller {

    static Form<Anuncio> anuncioForm = Form.form(Anuncio.class);
    private static List<Anuncio> anuncioList;
    private static List<Instrumento> instrumentoList;
    private static List<Estilo> estiloList;
    private static RepositorioDeAnuncios repositorioDeAnuncios;
    private static RepositorioDeEstilos repositorioDeEstilos;
    private static RepositorioDeInstrumentos repositorioDeInstrumentos;

    @Transactional
    public static Result index() {
        return ok(index.render());
    }

    @Transactional
    public static Result criarAnuncio() {
        repositorioDeEstilos = RepositorioDeEstilos.getInstance();
        repositorioDeInstrumentos = RepositorioDeInstrumentos.getInstance();

        estiloList = repositorioDeEstilos.findAll();
        instrumentoList = repositorioDeInstrumentos.findAll();

        return ok(criarAnuncio.render(estiloList, instrumentoList));
    }

    @Transactional
    public static Result listaDeAnuncios() {
        repositorioDeAnuncios = RepositorioDeAnuncios.getInstance();
        anuncioList = repositorioDeAnuncios.findAll();
        Collections.sort(anuncioList);

        return ok(listaDeAnuncios.render(anuncioList));
    }

    @Transactional
    public static Result novoAnuncio() {
        repositorioDeAnuncios = RepositorioDeAnuncios.getInstance();
        Map<String, String> dados = Form.form().bindFromRequest().data();

        String titulo = dados.get("titulo");
        String descricao = dados.get("descricao");
        String codigoDoAnuncio = dados.get("codigoDoAnuncio");
        String email = dados.get("email");
        String cidade = dados.get("cidade");
        String bairro = dados.get("bairro");
        String perfil = dados.get("perfilDoFacebook");
        String opcaoDeBusca = dados.get("opcaoDeBusca");

        //Multi selected Option about advertiser
        List<Estilo> estilosQueNaoGosto = getDadosDeEstilos("estilosQueNaoGosto");
        List<Estilo> estilosQueGosto = getDadosDeEstilos("estilosQueGosto");
        List<Instrumento> instrumentoList = getDadosInstrumentosSelecionados();

        try {

            Logger.debug(Arrays.toString(estilosQueNaoGosto.toArray()));
            Logger.debug(Arrays.toString(estilosQueGosto.toArray()));
            Logger.debug(Arrays.toString(instrumentoList.toArray()));

            Anuncio anuncio = new Anuncio(titulo, descricao, codigoDoAnuncio, cidade, bairro,
                    email, perfil, opcaoDeBusca, instrumentoList,
                    estilosQueGosto, estilosQueNaoGosto);
            repositorioDeAnuncios.persist(anuncio);
            repositorioDeAnuncios.flush();

        } catch(Exception e) {
            flash("erro",e.getMessage());
            return redirect("publique");
        }
        anuncioList = repositorioDeAnuncios.getInstance().findAll();
        return ok(listaDeAnuncios.render(anuncioList));
    }

    @Transactional
    public static Result verAnuncio(Long id){

        Anuncio anuncio = repositorioDeAnuncios.findByEntityId(id);

        return ok(verAnuncio.render(anuncio));
    }

    @Transactional
    public static Result removerAnuncio(Long id){
        repositorioDeAnuncios.removeById(id);
        repositorioDeAnuncios.flush();
        anuncioList = repositorioDeAnuncios.getInstance().findAll();
        return ok(listaDeAnuncios.render(anuncioList));
    }
    /**
     @Transactional
     public static Result pesquisaAnuncios(){

     Form<Anuncio> anuncioForm =

     List<Anuncio> anuncioList = repositorioDeAnuncios.findByAttributeName(item);

     return redirect(routes.Application.listaDeAnuncios.render(anuncioList));
     }
     **/

    @Transactional
    private static List<Estilo> getDadosDeEstilos(String palavra) {
        repositorioDeEstilos = RepositorioDeEstilos.getInstance();
        Map<String, String[]> multipleData = request().body().asFormUrlEncoded();

        List<Estilo> listaDeEstilos = new ArrayList<>();
        String[] arrayDeEstilos = multipleData.get(palavra);

        if(arrayDeEstilos != null) {
            for(int i = 0; i < arrayDeEstilos.length; i++) {
                long id = Long.parseLong(arrayDeEstilos[i]);
                Estilo estilo = repositorioDeEstilos.findByEntityId(id);
                if(!listaDeEstilos.contains(estilo)) {
                    listaDeEstilos.add(estilo);
                }
            }
        }
        return listaDeEstilos;
    }

    @Transactional
    private static List<Instrumento> getDadosInstrumentosSelecionados() {
        repositorioDeInstrumentos = RepositorioDeInstrumentos.getInstance();
        Map<String, String[]> multipleData = request().body().asFormUrlEncoded();

        List<Instrumento> listaDeInstrumentos = new ArrayList<>();
        String[] arrayformInstrumentos = multipleData.get("instrumentos");

        if(arrayformInstrumentos != null) {
            for(int i = 0; i < arrayformInstrumentos.length; i++) {
                long id = Long.parseLong(arrayformInstrumentos[i]);
                Instrumento instrumento = repositorioDeInstrumentos.findByEntityId(id);
                if(!listaDeInstrumentos.contains(instrumento)) {
                    listaDeInstrumentos.add(instrumento);
                }
            }
        }
        return listaDeInstrumentos;
    }
}