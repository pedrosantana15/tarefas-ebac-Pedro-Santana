package br.com.spedro.domain;

import jakarta.persistence.*;
import jakarta.transaction.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "TB_VENDA")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Venda {

    public enum Status {
        INICIADA, CONCLUIDA, CANCELADA;

        public static Status getByName(String value){
            for (Status status : Status.values()){
                if(status.name().equals(value)){
                    return status;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_sq")
    @SequenceGenerator(name = "venda_sq", sequenceName = "sq_venda", allocationSize = 1, initialValue = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_cliente_fk",
                foreignKey = @ForeignKey(name = "fk_venda_cliente"),
                referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    @Column(name = "CODIGO", length = 10, nullable = false)
    private String codigo;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.MERGE)
    private Set<ProdutoQuantidade> produtos;

    @Column(name = "DATA_VENDA", nullable = false)
    private Instant dataVenda;

    @Column(name = "VALOR_TOTAL", nullable = false)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_VENDA", nullable = false)
    private Status status;

    public Venda(){
        this.produtos = new HashSet<>();
    }

    public void adicionarProduto(Produto produto, Integer quantidade){
        validarStatus();
        Optional<ProdutoQuantidade> op =
                produtos.stream()
                        .filter(p -> p.getProduto().getCodigo().equals(produto.getCodigo()))
                        .findAny();

        if(op.isPresent()){
            ProdutoQuantidade produtoQuantidade = op.get();
            produtoQuantidade.add(quantidade);
        } else {
            ProdutoQuantidade produtoQuantidade = new ProdutoQuantidade();
            produtoQuantidade.setProduto(produto);
            produtoQuantidade.add(quantidade);
            produtoQuantidade.setVenda(this);
            produtos.add(produtoQuantidade);
        }
        recalcularValorVenda();
    }

    private void recalcularValorVenda(){
        BigDecimal valorTotal = BigDecimal.ZERO;
        for(ProdutoQuantidade p : this.produtos){
            valorTotal = valorTotal.add(p.getValorTotal());
        }
        this.valorTotal = valorTotal;
    }

    private void validarStatus(){
        if(this.status == Status.CONCLUIDA){
            throw new UnsupportedOperationException("NÃO É POSSÍVEL ALTERAR UMA VENDA FINALIZADA");
        }
    }

    public void removerProduto(Produto produto, Integer quantidade){
        validarStatus();
        Optional<ProdutoQuantidade> op =
                produtos.stream()
                        .filter(p -> p.getProduto().getCodigo().equals(produto.getCodigo()))
                        .findAny();
        if(op.isPresent()){
            ProdutoQuantidade produtoQuantidade = op.get();
            if(produtoQuantidade.getQuantidade() > quantidade){
                produtoQuantidade.remover(quantidade);
                recalcularValorVenda();
            }
            else{
                produtos.remove(op.get());
                recalcularValorVenda();
            }
        }
    }

    public void removerTodosProdutos(){
        validarStatus();
        produtos.clear();
        valorTotal = BigDecimal.ZERO;
    }

    public Integer getQuantidadeTotalProdutos(){
        Integer result = produtos.stream()
                .reduce(0, (qtd, p) -> qtd + p.getQuantidade(), Integer::sum);
        return result;
    }
}
