package br.com.spedro.domain.jpa;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoJpa implements Persistente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_sq")
    @SequenceGenerator(name = "produto_sq", sequenceName = "sq_produto", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "CODIGO", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", length = 100)
    private String descricao;

    @Column(name = "VALOR", nullable = false)
    private BigDecimal valor;

    @Column(name = "DISTRIBUIDOR", length = 50, nullable = false)
    private String distribuidor;

    public void addProdutos(){

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public String getDistribuidor() {
        return distribuidor;
    }
    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

}
