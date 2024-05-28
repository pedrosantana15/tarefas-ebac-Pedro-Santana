package br.com.spedro.dao;

import br.com.spedro.domain.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IClienteDao {

    Integer cadastrar(Cliente cliente) throws Exception;

    Cliente consultar(String codigo) throws Exception;

    Integer excluir(Cliente cliente) throws Exception;

    List<Cliente> buscarTodos() throws SQLException;

    Integer alterar(Cliente cliente, String codigo) throws SQLException;

}
