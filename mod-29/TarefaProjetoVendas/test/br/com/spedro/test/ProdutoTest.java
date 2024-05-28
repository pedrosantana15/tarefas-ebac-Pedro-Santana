package br.com.spedro.test;

import br.com.spedro.dao.IProdutoDao;
import br.com.spedro.dao.ProdutoDao;
import br.com.spedro.domain.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoTest {

    private Produto produto = new Produto();
    private IProdutoDao produtoDao = new ProdutoDao();

    public Integer cadastrar() throws SQLException {
        produto.setCodigo("7895");
        produto.setNome("Monitor");
        produto.setDescricao("Monitor Gamer Samsung 144hz");
        produto.setValor(890D);
        return produtoDao.cadastrar(produto);
    }

    @Test
    public void cadastrarTest() throws SQLException {
        Integer qtd = cadastrar();

        Assert.assertNotNull(qtd);
        Assert.assertEquals(1, (int) qtd);

        Produto produtoDb = produtoDao.consultar(produto.getCodigo());
        Assert.assertNotNull(produtoDb);
        Assert.assertNotNull(produtoDb.getId());
        Assert.assertEquals(produtoDb.getCodigo(), produto.getCodigo());
        Assert.assertEquals(produtoDb.getNome(), produto.getNome());
        Assert.assertEquals(produtoDb.getDescricao(), produto.getDescricao());
        Assert.assertEquals(produtoDb.getValor(), produto.getValor());

        Integer qtdDel = produtoDao.excluir(produtoDb);
        Assert.assertNotNull(qtdDel);
    }

    @Test
    public void excluirTest() throws SQLException {
        cadastrar();
        Integer qtdDel = produtoDao.excluir(produto);
        Assert.assertNotNull(qtdDel);
        Assert.assertEquals(1, (int) qtdDel);
    }

    @Test
    public void buscarTodosTest() throws SQLException {
        List<Produto> produtoList = new ArrayList<>();
        Produto produto2 = new Produto();

        produto2.setCodigo("1235");
        produto2.setNome("Mouse");
        produto2.setDescricao("Mouse Gamer Logitech");
        produto2.setValor(400D);
        produtoDao.cadastrar(produto2);
        cadastrar();

        produtoList = produtoDao.buscarTodos();
        produtoList.stream()
                .filter(p -> p.getCodigo().equals(produto.getCodigo()) || p.getCodigo().equals(produto2.getCodigo()))
                .toList();

        Assert.assertNotNull(produtoList);
        Assert.assertEquals(2, produtoList.size());

        Integer qtdDel = produtoDao.excluir(produto);
        qtdDel += produtoDao.excluir(produto2);
        Assert.assertEquals(2, (int) qtdDel);
    }

    @Test
    public void alterarTest() throws SQLException {
        cadastrar();

        Produto produtoAlterado = new Produto();
        produtoAlterado.setCodigo("7895");
        produtoAlterado.setNome("Mousepad");
        produtoAlterado.setDescricao("Mousepad de vidro");
        produtoAlterado.setValor(800D);

        Integer qtdAlt = produtoDao.alterar(produtoAlterado, produto.getCodigo());
        Assert.assertEquals(1, (int) qtdAlt);
        Assert.assertEquals(produto.getCodigo(), produtoAlterado.getCodigo());
        Assert.assertNotEquals(produto.getNome(), produtoAlterado.getNome());
        Assert.assertNotEquals(produto.getDescricao(), produtoAlterado.getDescricao());
        Assert.assertNotEquals(produto.getValor(), produtoAlterado.getValor());

        Integer qtdDel = produtoDao.excluir(produto);
        Assert.assertEquals(1, (int) qtdDel);
    }

}
