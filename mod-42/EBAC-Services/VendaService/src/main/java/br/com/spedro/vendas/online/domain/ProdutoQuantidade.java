package br.com.spedro.vendas.online.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Schema(name = "ProdutoQuantidade", description = "ProdutoQuantidade")
public class ProdutoQuantidade {

    private Produto produto;
    private BigDecimal valorTotal;
    private Integer quantidade;

    public ProdutoQuantidade(){
        this.valorTotal = BigDecimal.ZERO;
        this.quantidade = 0;
    }

    public void add(Integer quantidade){
        this.quantidade += quantidade;
        BigDecimal novoValor = produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        this.valorTotal = this.valorTotal.add(novoValor);
    }

    public void remove(Integer quantidade){
        this.quantidade -= quantidade;
        BigDecimal novoValor = produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        this.valorTotal = this.valorTotal.subtract(novoValor);
    }

}
