package br.com.pedro;

public class Main {

    public static void main(String args[]){
        Notas notas = new Notas();

        notas.inserirNotas(10, 5, 10, 8);
        System.out.println("Média das notas inseridas: " + notas.calcularMedia() );
    }

}
