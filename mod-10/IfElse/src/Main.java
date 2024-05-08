import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        double nota = solicitarNotas();
        double media = calcularMedia(nota);
        verificarResultado(media);
    }

    public static Double solicitarNotas() {
        double nota, somaNotas = 0;
        Scanner read = new Scanner(System.in);

        for (int i = 1; i <= 4; i++) {
            System.out.println("Insira a " + i + " nota do aluno: ");
            nota = read.nextDouble();

            somaNotas += nota;
        }

        return somaNotas;
    }

    public static Double calcularMedia(double nota){
        double media;

        media = nota / 4;

        return media;
    }

    public static void verificarResultado(double media){
        if(media >= 7){
            System.out.println("Média: " + media);
            System.out.println("Resultado: Aprovado");
        }
        else if(media >= 5){
            System.out.println("Média: " + media);
            System.out.println("Resultado: Recuperação");
        }
        else {
            System.out.println("Média: " + media);
            System.out.println("Resultado: Reprovado");
        }
    }

}
