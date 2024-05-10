package br.com.pedro;

public class PessoaJuridica extends Pessoa{

    private String representantes;
    private String tipo;

    public String getRepresentantes() {
        return representantes;
    }

    public void setRepresentantes(String representantes) {
        this.representantes = representantes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
