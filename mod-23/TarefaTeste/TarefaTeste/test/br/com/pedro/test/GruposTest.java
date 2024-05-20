package br.com.pedro.test;

import br.com.pedro.Grupos;
import br.com.pedro.Main;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GruposTest {

    @Test
    public void gerarListaFemininaTeste(){
        Grupos grupos = new Grupos();

        List<String> list = List.of("Pedro - M" , "Jorge - M", "Maria - F", "Edna - F");

        List<String> listaFeminina = grupos.gerarListaFeminina(list);
        List<String> listaEsperada = List.of("Maria - F", "Edna - F");
        Assert.assertEquals(listaFeminina, listaEsperada);
    }

    @Test
    public void separarPorVirgulaTeste(){
        Grupos grupos = new Grupos();
        String nomes = "Pedro - M,João - M,Gustavo - M";
        String[] nomesSeparados = grupos.separarPorVirgula(nomes);

        String[] arrNomesEsperados = {"Pedro - M", "João - M", "Gustavo - M"};

        Assert.assertArrayEquals(nomesSeparados, arrNomesEsperados);
    }

}
