package br.com.spedro.Meme.repositories;

import br.com.spedro.Meme.entities.Meme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemeRepository extends MongoRepository<Meme, String> { }
