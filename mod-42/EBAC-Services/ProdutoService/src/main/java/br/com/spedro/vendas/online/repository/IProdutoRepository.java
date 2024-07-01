package br.com.spedro.vendas.online.repository;

import br.com.spedro.vendas.online.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProdutoRepository extends MongoRepository<Produto, String> {

    Page<Produto> findByNomeIsLikeIgnoreCase(String nome, Pageable pageable);

    Optional<Produto> findByCodigo(String codigo);

    Page<Produto> findByStatus(Produto.Status status, Pageable pageable);

    Page<Produto> findAllByStatus(Pageable pageable, Produto.Status status);
}
