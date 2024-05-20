package br.com.pedro;

import java.util.List;

public class Grupos {

    public String[] separarPorVirgula(String string){
        String[] nomesSeparados = string.split(",");
        return nomesSeparados;
    }

    public List<String> gerarListaFeminina(List<String> list){
        List<String> listaFeminina = list.stream()
                .filter(pessoa -> pessoa.endsWith("- F"))
                .toList();
        return listaFeminina;
    }

}
