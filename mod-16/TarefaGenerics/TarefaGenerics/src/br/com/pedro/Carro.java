package br.com.pedro;

import java.lang.reflect.Type;
import java.util.List;

public abstract class Carro<T> {

    private String marca;
    private String modelo;

    public Carro(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String toString() {
        return "<-- Carro -->: \n" +
                "Marca: '" + marca + '\'' +
                "\nModelo: '" + modelo + '\'';
    }

}
