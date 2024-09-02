package br.com.spedro.Meme.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemeRequestDTO{

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String url;

    @NotNull
    private String idUser;

    @NotNull
    private String idCategory;

}

