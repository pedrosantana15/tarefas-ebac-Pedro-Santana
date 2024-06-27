package br.com.spedro.vendas.online.ClienteService.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Cliente", description = "Cliente")
public class Cliente {

    @Id
    @Schema(description = "Identificador")
    private String id;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Nome", minLength = 1, maxLength = 50, nullable = false)
    private String nome;

    @NotNull
    @Indexed(unique = true, background = true)
    @Schema(description = "CPF", nullable = false)
    private Long cpf;

    @NotNull
    @Schema(description = "Telefone", nullable = false)
    private Long tel;

    @NotNull
    @Size(min = 1, max = 50)
    @Indexed(unique = true, background = true)
    @Pattern(regexp = ".+@.+\\..+", message = "Email inválido")
    @Schema(description = "Email", minLength = 1, maxLength = 50, nullable = false)
    private String email;

    @NotNull
    @Size(min = 1, max = 100)
    @Schema(description = "Endereço", minLength = 1, maxLength = 100, nullable = false)
    private String end;

    @NotNull
    @Schema(description = "Número residencial", nullable = false)
    private Integer num;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Cidade", minLength = 1, maxLength = 50, nullable = false)
    private String cidade;

    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Estado", minLength = 1, maxLength = 50, nullable = false)
    private String estado;

}
