package br.com.spedro.Meme.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String description;

}