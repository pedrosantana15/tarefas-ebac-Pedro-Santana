package br.com.pedro;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Carro> listCarros = new ArrayList<>();
        listCarros.add(new CarroFord("EcoSport"));
        listCarros.add(new CarroFord("Fiesta"));
        listCarros.add(new CarroFord("Edge"));
        listCarros.add(new CarroFiat("Uno"));
        listCarros.add(new CarroFiat("Mobi"));
        listCarros.add(new CarroHonda("Civic"));
        listCarros.add(new CarroHonda("City"));

        for (Object li : listCarros){
            System.out.println(li);
        }

    }

}
