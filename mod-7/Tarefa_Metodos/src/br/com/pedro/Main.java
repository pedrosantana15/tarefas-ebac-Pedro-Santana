package br.com.pedro;

public class Main {
    public static void main (String args[]){
        Cliente cliente = new Cliente();
        Produto produto = new Produto();

        cliente.cadastraCliente(1, "Pedro", "Henrique", "pedro@email.com");
        produto.cadastraProduto(1, "Monitor", "Monitor Gamer 144hz Samsung", 849.90, 1);

        String nomeInteiro = cliente.getNomeInteiro();

        System.out.println("<------------- PEDIDO 1 ------------->");
        System.out.println("Olá " + nomeInteiro);
        System.out.println("Sua compra: ");
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("Quantidade: " + produto.getQuantidade());

        if(produto.calculaFrete() == 0){
            System.out.println("Frete: grátis");
        }
        else{
            System.out.println("Frete: " + produto.calculaFrete());
        }

        System.out.println("Valor total: " + produto.retornaValorTotal());
        System.out.println("<------------------------------------>");

        cliente.cadastraCliente(2, "João", "Silva", "joao@email.com");
        produto.cadastraProduto(2, "Mouse", "Mouse Gamer Logitech", 100, 1);

        System.out.println("<------------- PEDIDO 2 ------------->");
        System.out.println("Olá " + nomeInteiro);
        System.out.println("Sua compra: ");
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("Quantidade: " + produto.getQuantidade());

        //Verifica se há frete
        if(produto.calculaFrete() == 0){
            System.out.println("Frete: grátis");
        }
        else{
            System.out.println("Frete: " + produto.calculaFrete());
        }

        System.out.println("Valor total: " + produto.retornaValorTotal());
        System.out.println("<------------------------------------>");

    }
}
