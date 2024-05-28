package br.com.spedro.dao;

import br.com.spedro.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements IClienteDao {

    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO tb_cliente(id, codigo, nome) VALUES (" +
                    "nextval('sq_cliente'), ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getCodigo());
            stmt.setString(2, cliente.getNome());
            return stmt.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        finally {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Cliente consultar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        Cliente cliente = null;
        ResultSet rs = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_cliente WHERE codigo = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();

            if(rs.next()){
                cliente = new Cliente();
                cliente.setCodigo(rs.getString("codigo"));
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
            }

            return cliente;
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        finally {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM tb_cliente WHERE codigo = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getCodigo());
            return stmt.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        finally {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public List<Cliente> buscarTodos() throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clienteList = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_cliente";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getString("codigo"));
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));

                clienteList.add(cliente);
            }

            return clienteList;
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        finally {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public Integer alterar(Cliente cliente, String codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE tb_cliente SET nome = ? WHERE codigo = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, codigo);
            return stmt.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        finally {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

}
