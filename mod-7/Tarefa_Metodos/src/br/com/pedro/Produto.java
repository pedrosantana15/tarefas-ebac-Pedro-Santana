package br.com.pedro;

/**
 * @author pedro
 */
public class Produto {

    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double retornaValorDoPedido(){
        double valor = this.preco * this.quantidade;
        return valor;
    }

    /**
     * Calcula o frete de acordo com o valor do pedido.
     * Se o valor do pedido for menor que 200, é adicionado uma taxa de 10% de frete.
     *
     * @return frete
     */
    public double calculaFrete(){
        double valorPedido = retornaValorDoPedido();
        double frete = 0;

        if(valorPedido < 200) {
            frete = valorPedido * 0.1;
        }

        return frete;
    }

    /**
     * Soma o valor do pedido com o frete, retornando o valor total da compra.
     *
     * @return total
     */
    public double retornaValorTotal(){
        double valorPedido = retornaValorDoPedido();
        double frete = calculaFrete();

        double total = valorPedido + frete;

        return total;
    }

    public void cadastraProduto(int id, String nome, String descricao, double preco, int quantidade){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}
