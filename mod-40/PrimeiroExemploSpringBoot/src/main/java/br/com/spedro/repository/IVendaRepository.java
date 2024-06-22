package br.com.spedro.repository;

import br.com.spedro.domain.Venda;
import org.springframework.data.repository.CrudRepository;

public interface IVendaRepository extends CrudRepository<Venda, Long> {

}
