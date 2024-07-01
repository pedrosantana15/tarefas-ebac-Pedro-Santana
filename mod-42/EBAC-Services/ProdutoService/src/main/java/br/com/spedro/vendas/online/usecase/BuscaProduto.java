package br.com.spedro.vendas.online.usecase;

import br.com.spedro.vendas.online.domain.Produto;
import br.com.spedro.vendas.online.exception.EntityNotFoundException;
import br.com.spedro.vendas.online.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscaProduto {

    private IProdutoRepository produtoRepository;

    @Autowired
    public BuscaProduto(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Page<Produto> buscar(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Page<Produto> buscar(Pageable pageable, Produto.Status status) {
        return produtoRepository.findAllByStatus(pageable, status);
    }

    public Produto buscarPorCodigo(String codigo) {
        return produtoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException(Produto.class, "codigo", codigo));
    }

    public Page<Produto> buscarPeloNome(String nome, Pageable pageable) {
        return produtoRepository.findByNomeIsLikeIgnoreCase(nome, pageable);
    }

}
