package br.com.pedro;

import java.util.List;

public abstract class Carro {

    public List<Modelo> modelos;

    public List<Modelo> listModelo(){
        return modelos;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Carros:\n");
        for (Modelo modelo : modelos) {
            sb.append(" - ").append(modelo).append("\n");
        }
        return sb.toString();
    }
}
