package br.com.spedro.domain.jpa;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO_QUANTIDADE")
public class ProdutoQuantidadeJpa implements Persistente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_quantidade_sq")
    @SequenceGenerator(name = "produto_quantidade_sq", sequenceName = "sq_produto_quantidade", allocationSize = 1, initialValue = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProdutoJpa produto;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    @Column(name = "VALOR_TOTAL", nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venda_fk",
            foreignKey = @ForeignKey(name = "produto_qtd_venda_fk"),
            referencedColumnName = "id", nullable = false)
    private VendaJpa venda;

    public ProdutoQuantidadeJpa(){
        this.valorTotal = BigDecimal.ZERO;
        this.quantidade = 0;
    }

    public void add(Integer quantidade){
        this.quantidade += quantidade;
        BigDecimal novoValor = produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        BigDecimal novoTotal = this.valorTotal.add(novoValor);
        this.valorTotal = novoTotal;
    }

    public void remover(Integer quantidade) {
        this.quantidade -= quantidade;
        BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        this.valorTotal = this.valorTotal.subtract(novoValor);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ProdutoJpa getProduto() {
        return produto;
    }
    public void setProduto(ProdutoJpa produto) {
        this.produto = produto;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    public VendaJpa getVenda() {
        return venda;
    }
    public void setVenda(VendaJpa venda) {
        this.venda = venda;
    }

}
