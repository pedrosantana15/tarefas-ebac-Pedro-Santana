package br.com.pedro;

public class Cliente {

    private int id;
    private String nome;
    private String sobrenome;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * Retorna o nome inteiro do cliente, concatenando nome e sobrenome
     *
     * @return nomeInteiro
     */
    public String getNomeInteiro(){
        String nomeInteiro = this.nome + " " + this.sobrenome;
        return nomeInteiro;
    }

    public void cadastraCliente(int id, String nome, String sobrenome, String email){
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
    }

}
