package br.com.spedro.dao;

import br.com.spedro.domain.Produto;

import java.util.List;

public interface IProdutoDao {

    public Produto cadastrar(Produto produto);

    public Produto consultar(Long id);

    public Produto alterar(Produto produto);

    public void excluir(Produto produto);

    public List<Produto> buscarTodos();

}
