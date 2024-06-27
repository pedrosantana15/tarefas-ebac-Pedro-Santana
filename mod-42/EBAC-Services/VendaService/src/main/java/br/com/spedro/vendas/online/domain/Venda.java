package br.com.spedro.vendas.online.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Document(collection = "venda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Venda", description = "Venda")
public class Venda {

    public enum Status{
        INICIADA, CONCLUIDA, CANCELADA;

        public Status getByName(String value){
            for(Status status : Status.values()){
                if(status.name().equals(value)){
                    return status;
                }
            }
            return null;
        }
    }

    @Id
    @Schema(description = "Identificador")
    private String id;

    @NotNull
    @Schema(description = "Código da venda")
    private String codigo;

    @NotNull
    @Schema(description = "Cliente")
    private String clienteId;

    @NotNull
    @Schema(description = "Produtos")
    private Set<ProdutoQuantidade> produtos;

    @NotNull
    @Schema(description = "Valor total")
    private BigDecimal valorTotal;

    @NotNull
    @Schema(description = "Data da venda")
    private Instant dataVenda;

    @NotNull
    @Schema(description = "Status")
    private Status status;

    public void validarStatus(){
        if(this.status == Status.CONCLUIDA || this.status == Status.CANCELADA){
            throw new UnsupportedOperationException("NÃO É POSSÍVEL ALTERAR UMA VENDA FINALIZADA/CANCELADA");
        }
    }

    public void adicionarProduto(Produto produto, Integer quantidade){
        validarStatus();

        Optional<ProdutoQuantidade> op = produtos.stream()
                .filter(p -> p.getProduto().getCodigo().equals(produto.getCodigo())).findFirst();

        if(op.isPresent()) {
            ProdutoQuantidade produtoQuantidade = op.get();
            produtoQuantidade.add(quantidade);
        }
        else {
            ProdutoQuantidade produtoQuantidade =
            ProdutoQuantidade.builder()
                    .produto(produto)
                    .quantidade(0)
                    .valorTotal(BigDecimal.ZERO)
                    .build();

            produtos.add(produtoQuantidade);
            produtoQuantidade.add(quantidade);
        }
        recalcularValorTotal();
    }

    public void removerProduto(Produto produto, Integer quantidade) {
        validarStatus();
        Optional<ProdutoQuantidade> op =
                produtos.stream().filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();

        if(op.isPresent()) {
            ProdutoQuantidade produtoQuantidade = op.get();
            if(produtoQuantidade.getQuantidade() > quantidade) {
                produtoQuantidade.remove(quantidade);
                recalcularValorTotal();
            } else {
                produtos.remove(op.get());
                recalcularValorTotal();
            }
        }
    }

    public void recalcularValorTotal(){
        BigDecimal valorTotal = BigDecimal.ZERO;
        for(ProdutoQuantidade produtoQuantidade : this.produtos){
            valorTotal = valorTotal.add(produtoQuantidade.getValorTotal());
        }
        this.valorTotal = valorTotal;
    }

    public void removerTodosProdutos() {
        validarStatus();
        produtos.clear();
        valorTotal = BigDecimal.ZERO;
    }

    public Integer getQuantidadeTotalProdutos() {
        return produtos.stream()
                .reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantidade(), Integer::sum);
    }

}
