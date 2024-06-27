package br.com.spedro.vendas.online.ClienteService.repository;

import br.com.spedro.vendas.online.ClienteService.domain.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository extends MongoRepository<Cliente, String> {

    Optional<Cliente> findByCpf(Long cpf);

}
