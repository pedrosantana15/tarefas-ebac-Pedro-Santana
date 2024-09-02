package br.com.spedro.User.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @NotNull
    @Field(name = "username")
    @Size(min = 3, max = 100)
    private String username;

    @NotNull
    @Field(name = "email")
    @Indexed(unique = true, background = true)
    @Size(min = 5, max = 50)
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email")
    private String email;

    @Field(name = "registration_date")
    private Instant registrationDate;

}
