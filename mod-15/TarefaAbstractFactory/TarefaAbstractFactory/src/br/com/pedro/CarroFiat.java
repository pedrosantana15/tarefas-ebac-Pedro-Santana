package br.com.pedro;

import java.util.List;

public class CarroFiat extends Carro{

    public CarroFiat(){
        this.modelos = List.of(
                new Modelo("Uno"),
                new Modelo("Mobi"),
                new Modelo("Cronos"),
                new Modelo("Argo"),
                new Modelo("Titano")
        );
    }

}
