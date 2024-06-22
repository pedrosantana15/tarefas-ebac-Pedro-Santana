package br.com.spedro.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO_QUANTIDADE")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProdutoQuantidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtoqtd_sq")
    @SequenceGenerator(name = "produtoqtd_sq", sequenceName = "sq_produtoqtd", allocationSize = 1, initialValue = 1)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Produto produto;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    @Column(name = "VALOR_TOTAL", nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_venda_fk",
           foreignKey = @ForeignKey(name = "fk_produtoqtd_venda"),
            referencedColumnName = "id", nullable = false)
    private Venda venda;

    public ProdutoQuantidade(){
        this.valorTotal = BigDecimal.ZERO;
        this.quantidade = 0;
    }

    public void add(Integer quantidade){
        this.quantidade += quantidade;
        BigDecimal novoValor = produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
        BigDecimal novoTotal = this.valorTotal.add(novoValor);
        this.valorTotal = novoTotal;
    }

    public void remover(Integer quantidade) {
        this.quantidade -= quantidade;
        BigDecimal novoValor = this.produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
        this.valorTotal = this.valorTotal.subtract(novoValor);
    }

}
