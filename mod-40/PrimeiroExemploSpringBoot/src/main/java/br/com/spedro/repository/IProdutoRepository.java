package br.com.spedro.repository;

import br.com.spedro.domain.Produto;
import org.springframework.data.repository.CrudRepository;

public interface IProdutoRepository extends CrudRepository<Produto, Long> {

}

