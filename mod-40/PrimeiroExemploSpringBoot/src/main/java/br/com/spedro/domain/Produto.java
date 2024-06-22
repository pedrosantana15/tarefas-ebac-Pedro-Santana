package br.com.spedro.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_sq")
    @SequenceGenerator(name = "produto_sq", sequenceName = "sq_produto", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "CODIGO", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Column(name = "PRECO", nullable = false)
    private BigDecimal preco;

}
