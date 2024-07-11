package br.com.spedro.test;

import br.com.spedro.Calculadora;
import org.junit.Assert;
import org.junit.Test;

public class CalculadoraTest {

    @Test
    public void testSomar_HappyPath() {
        Calculadora calculadora = new Calculadora();

        Integer result = calculadora.somar(10, 10);

        Assert.assertEquals((Integer) 20, result);
    }

    @Test
    public void testSubtrair_HappyPath() {
        Calculadora calculadora = new Calculadora();

        Integer result = calculadora.subtrair(20, 10);

        Assert.assertEquals((Integer) 10, result);
    }

    @Test
    public void testMultiplicar_HappyPath() {
        Calculadora calculadora = new Calculadora();

        Integer result = calculadora.multiplicar(2, 10);

        Assert.assertEquals((Integer) 20, result);
    }

    @Test
    public void testDividir_HappyPath() {
        Calculadora calculadora = new Calculadora();

        Integer result = calculadora.dividir(20, 2);

        Assert.assertEquals((Integer) 10, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDividir_DividirPorZero() {
        Calculadora calculadora = new Calculadora();

        Integer result = calculadora.dividir(20, 0);

        Assert.assertNull(result);
    }

}
