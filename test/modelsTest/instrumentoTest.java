package modelsTest;

import models.entity.Instrumento;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class instrumentoTest {

    private Instrumento instrumento;
    private String nomeDoInstrumento = "Violão";
    private String nomeDoInstrumento1 = "Guitarra";

    @Before
    public void criaObj(){
        try {
            instrumento = new Instrumento(nomeDoInstrumento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaInstrumento(){

        try {
            assertEquals(nomeDoInstrumento.toUpperCase(), instrumento.getNomeDoInstrumento());
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            instrumento.setNomeDoInstrumento(null);
        }catch (Exception e){
            assertTrue("Preencha o campo: Nome do instrumento" == e.getMessage());
        }

        try {
            instrumento.setNomeDoInstrumento("");
        }catch (Exception e){
            assertEquals("Preencha o campo: Nome do instrumento", e.getMessage());
        }

        try {
            instrumento.setNomeDoInstrumento(nomeDoInstrumento1);
        }catch (Exception e){
            assertEquals(nomeDoInstrumento1.toUpperCase(), instrumento.getNomeDoInstrumento());
        }
    }

}
