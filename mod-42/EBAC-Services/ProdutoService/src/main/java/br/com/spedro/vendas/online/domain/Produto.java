package br.com.spedro.vendas.online.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Document(collection = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Produto", description = "Produto")
public class Produto {

    public enum Status{
        ATIVO, INATIVO;
    }

    @Id
    @Schema(description = "Identificador")
    private String id;

    @NotNull
    @Size(min = 1, max = 10)
    @Schema(description = "Código do produto", minLength = 1, maxLength = 10, nullable = false)
    private String codigo;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Nome", minLength = 1, maxLength = 50, nullable = false)
    private String nome;

    @NotNull
    @Size(min = 1, max = 100)
    @Schema(description = "Descrição", minLength = 1, maxLength = 100, nullable = false)
    private String descricao;

    @NotNull
    @Schema(description = "Valor", nullable = false)
    private BigDecimal valor;

    @NotNull
    @Schema(description = "Status", nullable = false)
    private Status status;


}
