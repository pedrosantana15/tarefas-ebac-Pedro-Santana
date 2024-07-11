package br.com.spedro;

public class Calculadora {

    public Integer somar(Integer x, Integer y) {
        return x + y;
    }

    public Integer subtrair(Integer x, Integer y) {
        return x - y;
    }

    public Integer multiplicar(Integer x, Integer y) {
        return x * y;
    }

    public Integer dividir(Integer x, Integer y) {
        if(y == 0){
            throw new IllegalArgumentException("Não pode ser dividido por zero");
        }

        return x / y;
    }

}
