package br.com.pedro;

public class Main {

    public static void main(String[] args) {
        Carro carro = CarroFactory.getInstance("fiat");

        System.out.println(carro);
    }

}
