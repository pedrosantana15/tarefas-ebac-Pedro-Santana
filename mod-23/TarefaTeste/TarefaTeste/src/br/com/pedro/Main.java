package br.com.pedro;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grupos grupos = new Grupos();

        System.out.println("Digite o nome das pessoas e seu gênero, separados por vírgula. Siga o modelo:" +
                "Pedro - M, Maria - F, João - M...");
        String strNomes = scanner.nextLine();

        String[] nomesSeparados = grupos.separarPorVirgula(strNomes);
        List<String> list = Arrays.asList(nomesSeparados);
        List<String> grupoFeminino = grupos.gerarListaFeminina(list);

        System.out.println("<----- GRUPO FEMININO ----->");
        grupoFeminino.forEach(System.out::println);

    }


}
