package br.com.spedro.test;


import br.com.spedro.Fibonacci;
import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {

    @Test
    public void findElement() {
        Fibonacci.findElementDP(3);
        Long result = Fibonacci.findElement(3);
        Assert.assertEquals((Long) 2L, result);
    }

}
