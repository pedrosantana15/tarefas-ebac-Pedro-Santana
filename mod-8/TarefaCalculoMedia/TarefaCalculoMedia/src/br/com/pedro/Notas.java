package br.com.pedro;

public class Notas {

    private double nota1;
    private double nota2;
    private double nota3;
    private double nota4;

    public void inserirNotas(double nota1, double nota2, double nota3, double nota4){
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
    }

    public double calcularMedia(){
        double media = (this.nota1 + this.nota2 + this.nota3 + this.nota4) / 4;

        return media;
    }

}
