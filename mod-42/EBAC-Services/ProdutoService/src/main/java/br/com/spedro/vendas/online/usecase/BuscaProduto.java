package br.com.spedro.vendas.online.usecase;

import br.com.spedro.vendas.online.domain.Produto;
import br.com.spedro.vendas.online.exception.EntityNotFoundException;
import br.com.spedro.vendas.online.repository.IProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscaProduto {

    IProdutoRepository produtoRepository;

    public BuscaProduto(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto buscarPorId(String id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Produto.class, id));
    }

    public Produto buscarPorCodigo(String codigo) {
        return produtoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException(Produto.class, codigo));
    }

    public Page<Produto> buscarTodos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Page<Produto> buscarPorStatus(Produto.Status status, Pageable pageable) {
        return produtoRepository.findByStatus(status, pageable);
    }

    public Page<Produto> buscarPorNome(String nome, Pageable pageable) {
        return produtoRepository.findByNomeIsLikeIgnoreCase(nome, pageable);
    }

}
