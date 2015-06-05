package controllers;

import models.entity.*;
import models.repository.*;
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
    private static List<Instrumento> instrumentosList;
    private static List<Estilo> estiloList;
    private static RepositorioDeAnuncios repositorioDeAnuncios = RepositorioDeAnuncios.getInstance();
    private static RepositorioDeEstilos repositorioDeEstilos = RepositorioDeEstilos.getInstance();
    private static RepositorioDeInstrumentos repositorioDeInstrumentos = RepositorioDeInstrumentos.getInstance();

    @Transactional
    public static Result index() {
        return ok(index.render());
    }

    @Transactional
    public static Result criarAnuncio() {
        estiloList = repositorioDeEstilos.findAll();
        instrumentosList = repositorioDeInstrumentos.findAll();

        return ok(criarAnuncio.render(estiloList, instrumentosList));
    }

    @Transactional
    public static Result listaDeAnuncios() {
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
            return ok(criarAnuncio.render(estiloList, instrumentosList));
        }
        anuncioList = repositorioDeAnuncios.getInstance().findAll();
        return ok(listaDeAnuncios.render(anuncioList));
    }

    @Transactional
    public static Result verAnuncio(Long id){
        return ok(verAnuncio.render(repositorioDeAnuncios.findByEntityId(id)));
    }

    @Transactional
    public static Result removerAnuncio(Long id){
        repositorioDeAnuncios.removeById(id);
        repositorioDeAnuncios.flush();
        anuncioList = repositorioDeAnuncios.getInstance().findAll();
        return ok(listaDeAnuncios.render(anuncioList));
    }

    @Transactional
    public static Result pesquisaAnuncios(){
        List<Anuncio> list;
        List<Anuncio> listResult = new ArrayList<>();
        Map<String, String> dados = Form.form().bindFromRequest().data();
        String dadosDaPesquisa = dados.get("textoDaPesquisa");
        String pesquisaPorEstilo = dados.get("pesquisaPorEstilo");
        String pesquisaPalavraChave = dados.get("pesquisaPalavraChave");
        String pesquisaPorInstrumento = dados.get("pesquisaPorInstrumento");
        String pesquisaTocarOcasionalmente = dados.get("pesquisaTocarOcasionalmente");
        String pesquisaPorFormarBanda = dados.get("pesquisaPorFormarBanda");

        if (dadosDaPesquisa == null || dadosDaPesquisa.trim().isEmpty()){
            flash("Pesquisa invalida");
        }

        if (dadosDaPesquisa != null) {
            if (!dadosDaPesquisa.trim().isEmpty()){
                if (pesquisaPorEstilo != null && !pesquisaPorEstilo.trim().isEmpty()){
                    list = RepositorioDeAnuncios.getInstance().findByAttributeName("estilosQueGosta", dadosDaPesquisa);
                    filter(listResult, list);
                    list = RepositorioDeAnuncios.getInstance().findByAttributeName("estilosQueNaoGosta", dadosDaPesquisa);
                    filter(listResult, list);
                }

                if (pesquisaPalavraChave != null && !pesquisaPalavraChave.trim().isEmpty()){
                    list = RepositorioDeAnuncios.getInstance().findByAttributeName("titulo", dadosDaPesquisa);
                    filter(listResult, list);
                    list = RepositorioDeAnuncios.getInstance().findByAttributeName("descricao", dadosDaPesquisa);
                    filter(listResult, list);
                }

                if (pesquisaPorInstrumento != null && !pesquisaPorInstrumento.trim().isEmpty()){
                    list = RepositorioDeAnuncios.getInstance().findByAttributeName("instrumentos", dadosDaPesquisa);
                    filter(listResult, list);
                }
                if (pesquisaTocarOcasionalmente != null && !pesquisaTocarOcasionalmente.trim().isEmpty()){
                    list = RepositorioDeAnuncios.getInstance().findByAttributeName("buscaPor", dadosDaPesquisa);
                    filter(listResult, list);
                }
                if (pesquisaPorFormarBanda != null && !pesquisaPorFormarBanda.trim().isEmpty()){
                    list = RepositorioDeAnuncios.getInstance().findByAttributeName("buscaPor", dadosDaPesquisa);
                    filter(listResult, list);
                }

                if (listResult.isEmpty()){
                    flash("Não foi encontrado nenhuma referência com os dados.");
                }

                Collections.sort(listResult);
            }
        }

        return ok(listaDeAnuncios.render(listResult));
    }

    @Transactional
    private static List<Anuncio> filter(List<Anuncio> anuncioList, List<Anuncio> anuncioList1){
        for(Anuncio anuncio : anuncioList1){
            if (!anuncioList.contains(anuncio)){
                anuncioList.add(anuncio);
            }
        }
        return anuncioList;
    }

    @Transactional
    private static List<Estilo> getDadosDeEstilos(String palavra) {
        repositorioDeEstilos = RepositorioDeEstilos.getInstance();
        Map<String, String[]> multipleData = request().body().asFormUrlEncoded();

        List<Estilo> listaDeEstilos = new ArrayList<>();
        String[] arrayDeEstilos = multipleData.get(palavra);

        if(arrayDeEstilos != null) {
            for (String arrayDeEstilo : arrayDeEstilos) {
                long id = Long.parseLong(arrayDeEstilo);
                Estilo estilo = repositorioDeEstilos.findByEntityId(id);
                if (!listaDeEstilos.contains(estilo)) {
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
            for (String arrayformInstrumento : arrayformInstrumentos) {
                long id = Long.parseLong(arrayformInstrumento);
                Instrumento instrumento = repositorioDeInstrumentos.findByEntityId(id);
                if (!listaDeInstrumentos.contains(instrumento)) {
                    listaDeInstrumentos.add(instrumento);
                }
            }
        }
        return listaDeInstrumentos;
    }
}