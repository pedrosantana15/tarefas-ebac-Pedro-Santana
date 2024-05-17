package br.com.pedro.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome das pessoas e seu gênero, separados por vírgula. Siga o modelo:" +
                "Pedro - M, Maria - F, João - M...");
        String strNomes = scanner.nextLine();

        String[] nomesSeparados =  separarPorVirgula(strNomes);

        List<String> list = Arrays.asList(nomesSeparados);

        Map<Boolean, List<String>> mapGrupos = list.stream()
                .collect(Collectors.groupingBy(nome -> nome.endsWith("- F")));

        mapGrupos.forEach((k,v) -> {
            if(k.equals(true)){
                System.out.println("GRUPO FEMININO \n" + v);
            }
            if(k.equals(false)){
                System.out.println("GRUPO MASCULINO \n" + v);
            }
        });

    }

    public static String[] separarPorVirgula(String string){
        String[] nomesSeparados = string.split(",");
        return nomesSeparados;
    }

}
