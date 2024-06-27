package br.com.spedro.vendas.online.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaDTO {

    @NotNull
    @Size(min = 2, max = 10)
    private String codigo;

    @NotNull
    private String clienteId;

    @NotNull
    private Instant dataVenda;

}
