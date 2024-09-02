package br.com.spedro.Category.repositories;

import br.com.spedro.Category.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends MongoRepository<Category, String> { }
