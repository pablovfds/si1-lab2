package entityTest;

import models.entity.Estilo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class estiloTest {

    private Estilo estilo;
    private String nomeDoEstilo = "Rock";
    private String nomeDoEstilo1 = "Eletronica";

    @Before
    public void criaObj(){
        try {
            estilo = new Estilo(nomeDoEstilo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaEstilo(){

        try {
            assertEquals(nomeDoEstilo.toUpperCase(), estilo.getNomeDoEstilo());
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            estilo.setNomeDoEstilo(null);
        }catch (Exception e){
            assertTrue("Preencha o campo: Nome do estilo" == e.getMessage());
        }

        try {
            estilo.setNomeDoEstilo("");
        }catch (Exception e){
            assertEquals("Preencha o campo: Nome do estilo", e.getMessage());
        }

        try {
            estilo.setNomeDoEstilo(nomeDoEstilo1);
        }catch (Exception e){
            assertEquals(nomeDoEstilo1.toUpperCase(), estilo.getNomeDoEstilo());
        }
    }
}
