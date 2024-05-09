package br.com.pedro;

import java.util.*;

public class Main {

    public static void main (String args[]){
        List<String> arr = listarPessoas();
        separarPorGrupo(arr);

    }

    public static List<String> listarPessoas(){
        Scanner read = new Scanner(System.in);

        System.out.println("Digite o nome da pessoa, e em seguida seu sexo.");
        System.out.println("ATENÇÃO: Siga o modelo -> Pedro - M, Maria - F, João - M...");
        String str = read.nextLine();

        String[] nomesFormatados = str.split(",");

        List<String> arrNomes = Arrays.asList(nomesFormatados);

        return arrNomes;
    }

    public static void separarPorGrupo(List<String> arr){
        Map<Integer, String> grupoMasculino = new HashMap<>();
        Map<Integer, String> grupoFeminino = new HashMap<>();

        System.out.println("<------ GRUPO MASCULINO ------>" );
        for(String pessoa : arr) {
            if (pessoa.contains("- M")) {
                grupoMasculino.put(1, pessoa);
                for (String masculino : grupoMasculino.values()) {
                    System.out.println(masculino);
                }
            }
        }
        System.out.println("<------ GRUPO FEMININO ------>");
        for(String pessoa : arr) {
            if(pessoa.contains("- F")){
                grupoFeminino.put(2, pessoa);
                for(String feminino : grupoFeminino.values()) {
                    System.out.println(feminino);
                }
            }
        }
    }


}
