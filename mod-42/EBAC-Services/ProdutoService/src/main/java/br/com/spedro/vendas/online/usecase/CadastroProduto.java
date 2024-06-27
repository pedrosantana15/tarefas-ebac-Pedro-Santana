package br.com.spedro.vendas.online.usecase;

import br.com.spedro.vendas.online.domain.Produto;
import br.com.spedro.vendas.online.exception.EntityNotFoundException;
import br.com.spedro.vendas.online.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroProduto {

    private IProdutoRepository produtoRepository;

    @Autowired
    public CadastroProduto(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(Produto produto) {
        return produtoRepository.insert(produto);
    }

    public Produto atualizar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void remover(String id) {
        Produto p = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Produto.class, id));
        p.setStatus(Produto.Status.INATIVO);
        produtoRepository.save(p);
    }

}
