package br.com.spedro.Meme.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "meme")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meme {

    @Id
    private String id;

    @NotNull
    @Size(min = 3, max = 50)
    @Field(name = "title")
    private String title;

    @NotNull
    @Size(min = 3, max = 100)
    @Field(name = "description")
    private String description;

    @NotNull
    @Field(name = "meme_url")
    private String url;

    @Field(name = "meme_owner")
    private User memeOwner;

    @Field(name = "meme_category")
    private Category memeCategory;

}
