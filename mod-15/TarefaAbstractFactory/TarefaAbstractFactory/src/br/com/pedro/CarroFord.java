package br.com.pedro;

import java.util.List;

public class CarroFord extends Carro{

    public CarroFord(){
        this.modelos = List.of(
                new Modelo("Fiesta"),
                new Modelo("Fusion"),
                new Modelo("Ka"),
                new Modelo("Ranger"),
                new Modelo("EcoSport")
        );
    }

}
