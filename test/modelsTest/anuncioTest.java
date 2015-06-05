package modelsTest;

import models.entity.Anuncio;
import models.entity.Estilo;
import models.entity.Instrumento;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class anuncioTest {
    private Anuncio anuncio;
    private String titulo = "Titulo Teste";
    private String descricao = "Descrição Teste";
    private String codigo = "12345";
    private String perfilDoFacebook = "https://www.facebook.com/contateste123";
    private String email = "contato@contato.com";
    private String buscaPor = "formar banda";
    private List<Instrumento> lista = new ArrayList<>();
    private List<Estilo> listaEstilosLike = new ArrayList<>();
    private Estilo estilo1;
    private Estilo estilo2;
    private List<Estilo> listaEstilosNotLike = new ArrayList<>();
    private Instrumento instrument;
    private String bairro = "Bairro Azul";
    private String cidade = "Cidade Azul";

    @Before
    public void criaAnuncio(){
        anuncio = new Anuncio();
        try {
            estilo1 = new Estilo("Samba");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            estilo2 = new Estilo("Rock");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            instrument = new Instrumento("Baixo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaTitulo(){
        try {
            anuncio.setTitulo(null);
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Titulo", e.getMessage());
        }

        try {
            anuncio.setTitulo("");
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Titulo", e.getMessage());
        }

        try {
            anuncio.setTitulo(titulo);
            Assert.assertTrue(titulo.toUpperCase().equals(anuncio.getTitulo()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void testaDescricao(){
        try {
            anuncio.setDescricao(null);
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Descricao", e.getMessage());
        }

        try {
            anuncio.setDescricao("");
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Descricao", e.getMessage());
        }

        try {
            anuncio.setDescricao(descricao);
            Assert.assertTrue(descricao.toUpperCase().equals(anuncio.getDescricao()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void testaCodigoDoAnuncio(){
        try {
            anuncio.setCodigoDoAnuncio(null);
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Codigo do anuncio", e.getMessage());
        }

        try {
            anuncio.setCodigoDoAnuncio("");
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Codigo do anuncio", e.getMessage());
        }

        try {
            anuncio.setCodigoDoAnuncio("codigoTeste");
        } catch (Exception e) {
            Assert.assertEquals("Seu codigo do anuncio deve ter no maximo 5 caracteres.", e.getMessage());
        }

        try {
            anuncio.setCodigoDoAnuncio(codigo);

            Assert.assertTrue(codigo.equals(anuncio.getCodigoDoAnuncio()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @Test
    public void testaPerfilDoFacebook(){

        try {
            anuncio.setPerfilDoFacebook(null);
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Perfil do Facebook", e.getMessage());
        }

        try {
            anuncio.setPerfilDoFacebook("");
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Perfil do Facebook", e.getMessage());
        }

        try {
            anuncio.setPerfilDoFacebook(perfilDoFacebook);
            Assert.assertTrue(perfilDoFacebook.equals(anuncio.getPerfilDoFacebook()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @Test
    public void testaEmail(){

        Assert.assertEquals(null, anuncio.getEmail());

        try {
            anuncio.setEmail(null);
        } catch (Exception e) {
            Assert.assertTrue("Preencha o campo: E-mail".equals(e.getMessage()));
        }

        try {
            anuncio.setEmail("");
        } catch (Exception e) {
            Assert.assertTrue("Preencha o campo: E-mail".equals(e.getMessage()));
        }

        try {
            anuncio.setEmail("emailinvalido");
        } catch (Exception e) {
            Assert.assertTrue("Preencha o campo: E-mail".equals(e.getMessage()));
        }

        try {
            anuncio.setEmail(email);
            Assert.assertTrue(email.equals(anuncio.getEmail()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void testaOpcaoProcuraBanda(){
        Assert.assertEquals(null, anuncio.getBuscaPor());

        try {
            anuncio.setBuscaPor(null);
        } catch (Exception e) {
            Assert.assertTrue("Preencha o campo: Busca por".equals(e.getMessage()));
        }

        try {
            anuncio.setBuscaPor("");
        } catch (Exception e) {
            Assert.assertTrue("Preencha o campo: Busca por".equals(e.getMessage()));
        }

        try {
            anuncio.setBuscaPor(buscaPor);
            Assert.assertEquals(buscaPor.toUpperCase(), anuncio.getBuscaPor());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String buscaPor1 = "tocar ocasionalmente";
            anuncio.setBuscaPor(buscaPor1);
            Assert.assertEquals(buscaPor1.toUpperCase(), anuncio.getBuscaPor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaEstilosQueAnuncianteNaoGosta(){

        Assert.assertTrue(anuncio.getEstilosQueNaoGosta().size() == 0);

        try {
            listaEstilosNotLike.add(estilo2);
            anuncio.setEstilosQueNaoGosta(listaEstilosNotLike);

            Assert.assertTrue(anuncio.getEstilosQueNaoGosta().size() == 1);
            Assert.assertEquals(estilo2, anuncio.getEstilosQueNaoGosta().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            listaEstilosNotLike.add(estilo1);
            anuncio.setEstilosQueNaoGosta(listaEstilosNotLike);

            Assert.assertTrue(anuncio.getEstilosQueNaoGosta().size() == 2);
            Assert.assertEquals(estilo1, anuncio.getEstilosQueNaoGosta().get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaEstilosQueAnuncianteGosta(){

        Assert.assertTrue(anuncio.getEstilosQueGosta().size() == 0);

        try {

            listaEstilosLike.add(estilo2);
            anuncio.setEstilosQueGosta(listaEstilosLike);

            Assert.assertTrue(anuncio.getEstilosQueGosta().size() == 1);
            Assert.assertEquals(estilo2, anuncio.getEstilosQueGosta().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            listaEstilosLike.add(estilo1);
            anuncio.setEstilosQueGosta(listaEstilosLike);

            Assert.assertTrue(anuncio.getEstilosQueGosta().size() == 2);
            Assert.assertEquals(estilo1, anuncio.getEstilosQueGosta().get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaInstrumentosQueOAnuncianteToca(){

        Assert.assertTrue(anuncio.getInstrumentos().size() == 0);
        try {
            anuncio.setInstrumentos(null);
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Instrumento que toco", e.getMessage());
        }
        Assert.assertTrue(anuncio.getInstrumentos().size() == 0);
        try {
            lista.add(instrument);
            anuncio.setInstrumentos(lista);
            Assert.assertTrue(anuncio.getInstrumentos().size() == 1);
            Assert.assertEquals(instrument, anuncio.getInstrumentos().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaBairroDoAnunciante(){

        try {
            anuncio.setBairro("");
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Bairro", e.getMessage());
        }

        try {
            anuncio.setBairro(null);
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Bairro", e.getMessage());
        }

        try {
            anuncio.setBairro(bairro);
            Assert.assertEquals(bairro.toUpperCase(), anuncio.getBairro());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testaCidadeDoAnunciante(){

        Assert.assertEquals(null, anuncio.getCidade());

        try {
            anuncio.setCidade("");
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Cidade", e.getMessage());
        }
        Assert.assertEquals(null, anuncio.getCidade());
        try {
            anuncio.setCidade(null);
        } catch (Exception e) {
            Assert.assertEquals("Preencha o campo: Cidade", e.getMessage());
        }
        Assert.assertEquals(null, anuncio.getCidade());

        try {
            anuncio.setCidade(cidade);
            Assert.assertEquals("CIDADE AZUL", anuncio.getCidade());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testaCriacao(){
        try {
            anuncio = new Anuncio(titulo, descricao, codigo, cidade, bairro,
                    email, perfilDoFacebook, buscaPor, lista,
                    listaEstilosLike, listaEstilosNotLike);
            Assert.assertEquals(titulo.toUpperCase(), anuncio.getTitulo());
            Assert.assertEquals(descricao.toUpperCase(), anuncio.getDescricao());
            Assert.assertEquals(buscaPor.toUpperCase(), anuncio.getBuscaPor());
            Assert.assertEquals(codigo, anuncio.getCodigoDoAnuncio());
            Assert.assertEquals(cidade.toUpperCase(), anuncio.getCidade());
            Assert.assertEquals(bairro.toUpperCase(), anuncio.getBairro());
            Assert.assertEquals(email, anuncio.getEmail());
            Assert.assertEquals(perfilDoFacebook, anuncio.getPerfilDoFacebook());
            Assert.assertEquals(lista, anuncio.getInstrumentos());
            Assert.assertEquals(listaEstilosLike, anuncio.getEstilosQueGosta());
            Assert.assertEquals(listaEstilosNotLike, anuncio.getEstilosQueNaoGosta());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaEquals(){
        Anuncio anuncio1;
        try {
            anuncio1 = new Anuncio("outroTitulo", descricao, codigo, cidade, bairro,
                    email, perfilDoFacebook, buscaPor, lista,
                    listaEstilosLike, listaEstilosNotLike);
            anuncio = new Anuncio(titulo, descricao, codigo, cidade, bairro,
                    email, perfilDoFacebook, buscaPor, lista,
                    listaEstilosLike, listaEstilosNotLike);

            Assert.assertFalse(anuncio.equals(anuncio1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            anuncio1 = new Anuncio(titulo, descricao, "54321", cidade, bairro,
                    email, perfilDoFacebook, buscaPor, lista,
                    listaEstilosLike, listaEstilosNotLike);
            anuncio = new Anuncio(titulo, descricao, codigo, cidade, bairro,
                    email, perfilDoFacebook, buscaPor, lista,
                    listaEstilosLike, listaEstilosNotLike);

            Assert.assertFalse(anuncio.equals(anuncio1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            anuncio1 = new Anuncio(titulo, descricao, codigo, cidade, bairro,
                    email, perfilDoFacebook, buscaPor, lista,
                    listaEstilosLike, listaEstilosNotLike);
            anuncio = new Anuncio(titulo, descricao, codigo, cidade, bairro,
                    email, perfilDoFacebook, buscaPor, lista,
                    listaEstilosLike, listaEstilosNotLike);

            Assert.assertTrue(anuncio.equals(anuncio1));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
