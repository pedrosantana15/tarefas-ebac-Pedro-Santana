package br.com.spedro.User.repositories;

import br.com.spedro.User.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<User, String> { }
