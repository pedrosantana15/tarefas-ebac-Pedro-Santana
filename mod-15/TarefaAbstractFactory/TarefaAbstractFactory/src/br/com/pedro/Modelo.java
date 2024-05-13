package br.com.pedro;

public class Modelo {

    private String nome;

    public Modelo(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return "Modelo \n" +
                "Nome: " + nome;
    }
}
