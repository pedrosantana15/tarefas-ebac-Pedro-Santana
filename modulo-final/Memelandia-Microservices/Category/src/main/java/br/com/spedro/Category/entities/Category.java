package br.com.spedro.Category.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    @NotNull
    @Size(min = 3, max = 100)
    @Field(name = "name")
    private String name;

    @NotNull
    @Size(min = 3, max = 150)
    @Field(name = "description")
    private String description;

    @Field(name = "registration_date")
    private Instant registrationDate;

}
