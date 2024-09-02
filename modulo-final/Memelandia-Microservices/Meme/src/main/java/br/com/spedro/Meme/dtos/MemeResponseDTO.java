package br.com.spedro.Meme.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemeResponseDTO {

    private String title;

    private String description;

    private String url;

    private String userName;

    private String categoryName;

}
