package br.com.spedro.jpa;

import br.com.spedro.dao.jpa.IProdutoJpaDAO;
import br.com.spedro.dao.jpa.ProdutoJpaDAO;
import br.com.spedro.domain.jpa.ClienteJpa;
import br.com.spedro.domain.jpa.ProdutoJpa;
import br.com.spedro.exceptions.DAOException;
import br.com.spedro.exceptions.MaisDeUmRegistroException;
import br.com.spedro.exceptions.TableException;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProdutoJpaDAOTest {

    private IProdutoJpaDAO produtoDao;

    public ProdutoJpaDAOTest(){
        produtoDao = new ProdutoJpaDAO();
    }

    @Test
    public void cadastrar() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ProdutoJpa produto = buildProduto();
        ProdutoJpa produtoDb = produtoDao.cadastrar(produto);
        Assert.assertNotNull(produtoDb);
        Assert.assertNotNull(produtoDb.getId());

        ProdutoJpa produtoConsultado = produtoDao.consultar(produtoDb.getId());
        Assert.assertNotNull(produtoConsultado);
        Assert.assertEquals(produtoConsultado.getId(), produtoDb.getId());

        produtoDao.excluir(produto);
    }

    @Test
    public void alterar() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ProdutoJpa produto = buildProduto();
        ProdutoJpa produtoDb = produtoDao.cadastrar(produto);

        produtoDb.setNome("Pedro Henrique");
        ProdutoJpa produtoAlterado = produtoDao.alterar(produtoDb);
        Assert.assertNotNull(produtoAlterado);

        ProdutoJpa produtoConsultado = produtoDao.consultar(produtoAlterado.getId());
        Assert.assertNotNull(produtoConsultado);
        Assert.assertEquals(produtoConsultado.getId(), produtoConsultado.getId());
        Assert.assertEquals(produtoConsultado.getNome(), "Pedro Henrique");

        produtoDao.excluir(produto);
    }

    @Test
    public void consultar() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ProdutoJpa produto = buildProduto();
        ProdutoJpa produtoDb = produtoDao.cadastrar(produto);

        ProdutoJpa produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNotNull(produtoConsultado);
        Assert.assertEquals(produtoConsultado.getId(), produtoDb.getId());

        produtoDao.excluir(produto);
    }

    private ProdutoJpa buildProduto(){
        ProdutoJpa produto = new ProdutoJpa();
        produto.setCodigo("Y237");
        produto.setNome("Monitor");
        produto.setDescricao("Monitor Gamer 144hz");
        produto.setValor(BigDecimal.valueOf(1200));
        produto.setDistribuidor("Amazon");
        return produto;
    }

    @Test
    public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ProdutoJpa produto = buildProduto();
        ProdutoJpa produtoDb = produtoDao.cadastrar(produto);
        Assert.assertNotNull(produtoDb);
        Assert.assertNotNull(produtoDb.getId());

        produtoDao.excluir(produtoDb);

        ProdutoJpa produtoConsultado = produtoDao.consultar(produtoDb.getId());
        Assert.assertNull(produtoConsultado);
    }

}
