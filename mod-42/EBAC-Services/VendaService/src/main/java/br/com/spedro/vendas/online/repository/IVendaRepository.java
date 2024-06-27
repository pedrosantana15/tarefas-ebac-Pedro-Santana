package br.com.spedro.vendas.online.repository;

import br.com.spedro.vendas.online.domain.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVendaRepository extends MongoRepository<Venda, String> {

    Page<Venda> findByStatus(Venda.Status status, Pageable pageable);

}
