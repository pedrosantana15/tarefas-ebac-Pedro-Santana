package br.com.pedro;


import br.com.pedro.annotations.Tabela;

public class Carros {

    @Tabela(value = "Tabela Ford")
    public void CarrosFord(){
        //Tabela
    }

    @Tabela(value = "Tabela Fiat")
    public void CarrosFiat(){
        //Tabela
    }

    @Tabela(value = "Tabela Honda")
    public void CarrosHonda(){
        //Tabela
    }

}
