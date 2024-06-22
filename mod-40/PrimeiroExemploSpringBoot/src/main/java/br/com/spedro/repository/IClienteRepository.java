package br.com.spedro.repository;

import br.com.spedro.domain.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteRepository extends CrudRepository<Cliente, Long> {

}

