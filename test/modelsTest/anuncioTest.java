package modelsTest;

import models.Anuncio;
import org.junit.*;

/**
 * Created by PabloVictor on 23/05/2015.
 */
public class anuncioTest {
    Anuncio anuncio;

    @Test
    public void testaListaDeContatos(){
        anuncio = new Anuncio();
        Assert.assertEquals("Campo não Preenchido", anuncio.getListaParaContato()[0]);
        Assert.assertEquals("Campo não Preenchido", anuncio.getListaParaContato()[1]);

        try {
            anuncio.setContato(null, null);
        } catch (Exception e) {
            Assert.assertEquals("Preencha ao menos um campo para contato", e.getMessage());
        }

        try {
            anuncio.setContato("contato@contato.com", null);
            Assert.assertEquals("contato@contato.com", anuncio.getListaParaContato()[0]);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            anuncio.setContato(null, "www.facebook.com/contato");
            Assert.assertEquals("www.facebook.com/contato", anuncio.getListaParaContato()[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            anuncio.setContato("contato@contato.com", "www.facebook.com/contato");
            Assert.assertEquals("contato@contato.com", anuncio.getListaParaContato()[0]);
            Assert.assertEquals("www.facebook.com/contato", anuncio.getListaParaContato()[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaOpcaoProcuraBanda(){
        anuncio = new Anuncio();
        Assert.assertEquals(null, anuncio.getOpcaoProcuraBanda());

        try {
            anuncio.setOpcaoProcuraBanda(null);
        } catch (Exception e) {
            Assert.assertEquals("Campo não preenchido: Procuro uma banda", e.getMessage());
        }

        try {
            anuncio.setOpcaoProcuraBanda("Sim");
            Assert.assertEquals("SIM", anuncio.getOpcaoProcuraBanda());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaOpcaoDeTocarOcasionalmente(){
        anuncio = new Anuncio();

        Assert.assertEquals(null, anuncio.getOpcaoDeTocarOcasionalmente());

        try {
            anuncio.setOpcaoDeTocarOcasionalmente(null);
        } catch (Exception e) {
            Assert.assertEquals("Campo não preenchido: Tocar ocasionalmente", e.getMessage());
        }


        try {
            anuncio.setOpcaoDeTocarOcasionalmente("Sim");
            Assert.assertEquals("SIM", anuncio.getOpcaoDeTocarOcasionalmente());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaEstilosQueAnuncianteNaoGosta(){
        anuncio = new Anuncio();

        Assert.assertTrue(anuncio.getEstilosQueAnuncianteNaoGosta().size() == 0);

        try {
            anuncio.setEstilosQueAnuncianteNaoGosta("Rock");

            Assert.assertTrue(anuncio.getEstilosQueAnuncianteNaoGosta().size() == 1);
            Assert.assertEquals("ROCK", anuncio.getEstilosQueAnuncianteNaoGosta().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            anuncio.setEstilosQueAnuncianteNaoGosta("samba");

            Assert.assertTrue(anuncio.getEstilosQueAnuncianteNaoGosta().size() == 2);
            Assert.assertEquals("SAMBA", anuncio.getEstilosQueAnuncianteNaoGosta().get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testaEstilosQueAnuncianteGosta(){
        anuncio = new Anuncio();
        Assert.assertTrue(anuncio.getEstilosQueAnuncianteGosta().size() == 0);

        try {
            anuncio.setEstilosQueAnuncianteGosta("Rock");

            Assert.assertTrue(anuncio.getEstilosQueAnuncianteGosta().size() == 1);
            Assert.assertEquals("ROCK", anuncio.getEstilosQueAnuncianteGosta().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            anuncio.setEstilosQueAnuncianteNaoGosta("samba");

            Assert.assertTrue(anuncio.getEstilosQueAnuncianteGosta().size() == 2);
            Assert.assertEquals("SAMBA", anuncio.getEstilosQueAnuncianteGosta().get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaInstrumentosQueOAnuncianteToca(){
        anuncio = new Anuncio();

        Assert.assertTrue(anuncio.getListaDeInstrumentosQueOAnuncianteToca().size() == 0);
        try {
            anuncio.setInstrumentosQueOAnuncianteToca("");
        } catch (Exception e) {
            Assert.assertEquals("Campo não preenchido: Instrumentos que o anunciante toca", e.getMessage());
        }
        Assert.assertTrue(anuncio.getListaDeInstrumentosQueOAnuncianteToca().size() == 0);
        try {
            anuncio.setInstrumentosQueOAnuncianteToca(null);
        } catch (Exception e) {
            Assert.assertEquals("Campo não preenchido: Instrumentos que o anunciante toca", e.getMessage());
        }
        Assert.assertTrue(anuncio.getListaDeInstrumentosQueOAnuncianteToca().size() == 0);
        try {
            anuncio.setInstrumentosQueOAnuncianteToca("Baixo");
            Assert.assertTrue(anuncio.getListaDeInstrumentosQueOAnuncianteToca().size() == 1);
            Assert.assertEquals("BAIXO", anuncio.getListaDeInstrumentosQueOAnuncianteToca().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaBairroDoAnunciante(){
        anuncio = new Anuncio();
        Assert.assertEquals(null, anuncio.getBairroDoAnunciante());

        try {
            anuncio.setBairroDoAnunciante("");
        } catch (Exception e) {
            Assert.assertEquals("Campo não preenchido: bairro do anunciante", e.getMessage());
        }
        Assert.assertEquals(null, anuncio.getBairroDoAnunciante());
        try {
            anuncio.setBairroDoAnunciante(null);
        } catch (Exception e) {
            Assert.assertEquals("Campo não preenchido: bairro do anunciante", e.getMessage());
        }
        Assert.assertEquals(null, anuncio.getBairroDoAnunciante());

        try {
            anuncio.setBairroDoAnunciante("Bairro Azul");
            Assert.assertEquals("BAIRRO AZUL", anuncio.getBairroDoAnunciante());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testaCidadeDoAnunciante(){
        anuncio = new Anuncio();
        Assert.assertEquals(null, anuncio.getCidadeDoAnunciante());

        try {
            anuncio.setCidadeDoAnunciante("");
        } catch (Exception e) {
            Assert.assertEquals("Campo não preenchido: Cidade do anunciante", e.getMessage());
        }
        Assert.assertEquals(null, anuncio.getCidadeDoAnunciante());
        try {
            anuncio.setCidadeDoAnunciante(null);
        } catch (Exception e) {
            Assert.assertEquals("Campo não preenchido: Cidade do anunciante", e.getMessage());
        }
        Assert.assertEquals(null, anuncio.getCidadeDoAnunciante());

        try {
            anuncio.setCidadeDoAnunciante("Cidade Azul");
            Assert.assertEquals("CIDADE AZUL", anuncio.getCidadeDoAnunciante());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testaDescricao(){
        anuncio = new Anuncio();

        Assert.assertEquals(null, anuncio.getDescricaoDoAnuncio());

        try {
            anuncio.setDescricaoDoAnuncio("");
        } catch (Exception e) {
            Assert.assertEquals("Campo não preenchido: Descrição do anuncio", e.getMessage());
        }
        Assert.assertEquals(null, anuncio.getDescricaoDoAnuncio());
        try {
            anuncio.setDescricaoDoAnuncio(null);
        } catch (Exception e) {
            Assert.assertEquals("Campo não preenchido: Descrição do anuncio", e.getMessage());
        }
        Assert.assertEquals(null, anuncio.getDescricaoDoAnuncio());

        try {
            anuncio.setDescricaoDoAnuncio("Descrição Teste");
            Assert.assertEquals("DESCRIÇÃO TESTE", anuncio.getDescricaoDoAnuncio());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
