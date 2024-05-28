package br.com.spedro.dao;

import br.com.spedro.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao implements  IProdutoDao{
    @Override
    public Integer cadastrar(Produto produto) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConnectionFactory.getConnection();

            String sql = "INSERT INTO tb_produto(id, codigo, nome, descricao, valor) VALUES (" +
                    "nextval('sq_produto'), ?, ?, ?, ?)";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getCodigo());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.setDouble(4, produto.getValor());

            return stmt.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException();
        }
        finally {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public Produto consultar(String codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto produto = new Produto();

        try {
            connection = ConnectionFactory.getConnection();

            String sql = "SELECT * FROM tb_produto WHERE codigo = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();

            if(rs.next()){
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValor(rs.getDouble("valor"));
            }

            return produto;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
        finally {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public Integer excluir(Produto produto) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM tb_produto WHERE codigo = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getCodigo());
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
    public List<Produto> buscarTodos() throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto produto = new Produto();
        List<Produto> produtoList = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();

            String sql = "SELECT * FROM tb_produto";

            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()){
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValor(rs.getDouble("valor"));

                produtoList.add(produto);
            }

            return produtoList;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
        finally {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public Integer alterar(Produto produto, String codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConnectionFactory.getConnection();

            String sql = "UPDATE tb_produto SET nome = ?, descricao = ?, valor = ? WHERE codigo = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getValor());
            stmt.setString(4, codigo);

            return stmt.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException();
        }
        finally {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }
}
