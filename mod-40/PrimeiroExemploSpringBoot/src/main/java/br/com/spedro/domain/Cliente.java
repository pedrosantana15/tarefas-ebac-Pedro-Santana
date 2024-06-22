package br.com.spedro.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_CLIENTE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_sq")
    @SequenceGenerator(name = "cliente_sq", sequenceName = "sq_cliente", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "NOME", length = 50, nullable = false)
    private String nome;

    @Column(name = "CPF", nullable = false, unique = true)
    private Long cpf;

    @Column(name = "TELEFONE", nullable = false)
    private Long tel;

    @Column(name = "EMAIL", length = 50, nullable = false)
    private String email;

    @Column(name = "ENDERECO", length = 100, nullable = false)
    private String end;

    @Column(name = "NUMERO", nullable = false)
    private Integer numero;

    @Column(name = "CIDADE", length = 50, nullable = false)
    private String cidade;

    @Column(name = "ESTADO", length = 50, nullable = false)
    private String estado;

}
