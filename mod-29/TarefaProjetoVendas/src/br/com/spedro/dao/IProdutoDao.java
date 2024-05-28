package br.com.spedro.dao;

import br.com.spedro.domain.Produto;

import java.sql.SQLException;
import java.util.List;

public interface IProdutoDao {

    Integer cadastrar(Produto produto) throws SQLException;

    Produto consultar(String codigo) throws SQLException;

    Integer excluir(Produto produto) throws SQLException;

    List<Produto> buscarTodos() throws SQLException;

    Integer alterar(Produto produto, String codigo) throws SQLException;


}
