import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        listarNomes();
        separarPorSexo();
    }

    public static void listarNomes(){
        Scanner read = new Scanner(System.in);

        System.out.println("<-------- EXERCICIO 1 -------->");

        System.out.println("Digite uma sequência de nomes, separados por vírgula, sem espaço:");
        String nomes = read.nextLine();

        List<String> nomesFormatados = new ArrayList<>();

        nomesFormatados = Arrays.asList(nomes.split(","));
        nomesFormatados.sort(String.CASE_INSENSITIVE_ORDER);

        for(String nome : nomesFormatados){
            System.out.println(nome);
        }
    }

    public static void separarPorSexo(){
        Scanner read = new Scanner(System.in);

        System.out.println("<-------- EXERCICIO 2 -------->");

        System.out.println("Digite uma sequência de nomes de pessoas e seus respectivos sexos. Siga o exemplo: Pedro - M, Maria - F, Julia - F...");
        String str = read.nextLine();

        List<String> arrGrupos = new ArrayList<>();

        arrGrupos = Arrays.asList(str.split(","));

        System.out.println("<------ GRUPO MASCULINO ------>");
        for(String masculino : arrGrupos){
            if(masculino.contains("- M")){
                System.out.println(masculino);
            }
        }
        System.out.println("<------ GRUPO FEMININO ------>");
        for(String feminino : arrGrupos){
            if(feminino.contains("- F")){
                System.out.println(feminino);
            }
        }
    }
}