package br.com.spedro.Meme.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String id;

    @NotNull
    private String username;

    @NotNull
    private String email;

}