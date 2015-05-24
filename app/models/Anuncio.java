package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PabloVictor on 23/05/2015.
 */
public class Anuncio {

    private String descricao;
    private String cidadeDoAnunciante;
    private String bairroDoAnunciante;
    private List<String> listaDeInstrumentosQueOAnuncianteToca;
    private List<String> listaDeEstilosQueAnuncianteGosta;
    private List<String> listaDeEstilosQueAnuncianteNaoGosta;
    private String opcaoDeTocarOcasionalmente;
    private String opcaoProcuraBanda;
    private String[] listaParaContato;

    public Anuncio(){
        listaDeEstilosQueAnuncianteGosta = new ArrayList<>();
        listaDeEstilosQueAnuncianteNaoGosta = new ArrayList<>();
        listaDeInstrumentosQueOAnuncianteToca = new ArrayList<>();
        listaParaContato = new String[2];
        listaParaContato[0] = "Campo não Preenchido";
        listaParaContato[1] = "Campo não Preenchido";
    }

    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws Exception {
        if (titulo == null || titulo.trim() == ""){
            throw new Exception("Campo n?o foi preenchido: Titulo do anuncio");
        }
        this.titulo = titulo;
    }

    public String getDescricaoDoAnuncio() {
        return descricao;
    }

    public void setDescricaoDoAnuncio(String descricao) throws Exception {
        if (descricao == null || descricao.trim() == ""){
            throw new Exception("Campo não preenchido: Descrição do anuncio");
        }
        this.descricao = descricao.toUpperCase();
    }

    public String getCidadeDoAnunciante() {
        return cidadeDoAnunciante;
    }

    public void setCidadeDoAnunciante(String cidadeDoAnunciante) throws Exception {
        if (cidadeDoAnunciante == null || cidadeDoAnunciante.trim() == ""){
            throw new Exception("Campo não preenchido: Cidade do anunciante");
        }

        this.cidadeDoAnunciante = cidadeDoAnunciante.toUpperCase();
    }

    public String getBairroDoAnunciante() {
        return bairroDoAnunciante;
    }

    public void setBairroDoAnunciante(String bairroDoAnunciante) throws Exception {
        if (bairroDoAnunciante == null || bairroDoAnunciante.trim() == ""){
            throw new Exception("Campo não preenchido: bairro do anunciante");
        }

        this.bairroDoAnunciante = bairroDoAnunciante.toUpperCase();
    }

    public List<String> getListaDeInstrumentosQueOAnuncianteToca() {
        return listaDeInstrumentosQueOAnuncianteToca;
    }

    public void setInstrumentosQueOAnuncianteToca(String instrumentosQueOAnuncianteToca) throws Exception {
        if (instrumentosQueOAnuncianteToca == null || instrumentosQueOAnuncianteToca.trim() == "") {
            throw new Exception("Campo não preenchido: Instrumentos que o anunciante toca");
        }
        listaDeInstrumentosQueOAnuncianteToca.add(instrumentosQueOAnuncianteToca.toUpperCase());
    }

    public List<String> getEstilosQueAnuncianteGosta() {
        return listaDeEstilosQueAnuncianteGosta;
    }

    public void setEstilosQueAnuncianteGosta(String estilosQueEleGosta) {
        if (!estilosQueEleGosta.equals(null)) {
            listaDeEstilosQueAnuncianteGosta.add(estilosQueEleGosta);
        }
    }

    public List<String> getEstilosQueAnuncianteNaoGosta() {
        return listaDeEstilosQueAnuncianteNaoGosta;
    }

    public void setEstilosQueAnuncianteNaoGosta(String estilosQueEleNaoGosta) throws Exception {
        if (listaDeEstilosQueAnuncianteNaoGosta.contains(estilosQueEleNaoGosta)){
            throw new Exception("O estilo já foi adicionado");
        }
        else {
            listaDeEstilosQueAnuncianteNaoGosta.add(estilosQueEleNaoGosta.toUpperCase());
        }
    }

    public String getOpcaoDeTocarOcasionalmente() {
        if (opcaoDeTocarOcasionalmente == null) {
            return opcaoDeTocarOcasionalmente;
        }
        return opcaoDeTocarOcasionalmente.toUpperCase();
    }

    public void setOpcaoDeTocarOcasionalmente(String opcaoDeTocarOcasionalmente) throws Exception{
        if (opcaoDeTocarOcasionalmente == null){
            throw new Exception("Campo não preenchido: Tocar ocasionalmente");
        }
        this.opcaoDeTocarOcasionalmente = opcaoDeTocarOcasionalmente;
    }

    public String getOpcaoProcuraBanda() {
        if (opcaoProcuraBanda == null){
            return null;
        }
        return opcaoProcuraBanda.toUpperCase();
    }

    public void setOpcaoProcuraBanda(String opcaoProcuraBanda) throws Exception{
        if (opcaoProcuraBanda == null){
            throw new Exception("Campo não preenchido: Procuro uma banda");
        }
        this.opcaoProcuraBanda = opcaoProcuraBanda;
    }

    public String[] getListaParaContato() {
        return listaParaContato;
    }

    public void setContato(String contatoEmail, String contatoFacebook) throws Exception {
        if ((contatoEmail == null || contatoEmail.trim() == "") &&
                (contatoFacebook == null || contatoFacebook.trim() == "") ){
            throw new Exception("Preencha ao menos um campo para contato");
        }

        if (contatoEmail == null){
            listaParaContato[1] = contatoFacebook;
        }
        else if (contatoFacebook == null){
            listaParaContato[0] = contatoEmail;
        }else {
            listaParaContato[0] = contatoEmail;
            listaParaContato[1] = contatoFacebook;
        }

    }
}
