package br.com.spedro.dao.jpa;

import br.com.spedro.domain.jpa.ProdutoJpa;
import br.com.spedro.exceptions.DAOException;
import br.com.spedro.exceptions.MaisDeUmRegistroException;
import br.com.spedro.exceptions.TableException;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.Collection;
import static org.junit.Assert.*;

public class ProdutoJpaDb2DAOTest {
    private IProdutoJpaDAO<ProdutoJpa> produtoDao;
    private IProdutoJpaDAO<ProdutoJpa> produtoDb2Dao;

    public ProdutoJpaDb2DAOTest() {
        this.produtoDao = new ProdutoJpaDAO();
        this.produtoDb2Dao = new ProdutoJpaDb2DAO();
    }

    @After
    public void end() throws DAOException {
        //DB 1
        Collection<ProdutoJpa> list = produtoDao.buscarTodos();
        list.forEach(cli -> {
            try {
                produtoDao.excluir(cli);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
        //DB 2
        Collection<ProdutoJpa> list2 = produtoDb2Dao.buscarTodos();
        list2.forEach(cli -> {
            try {
                produtoDb2Dao.excluir(cli);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisar() throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
        //DB 1
        ProdutoJpa produto = criarProduto("A1");
        ProdutoJpa retorno = produtoDao.cadastrar(produto);
        Assert.assertNotNull(retorno);
        ProdutoJpa produtoDB = this.produtoDao.consultar(retorno.getId());
        Assert.assertNotNull(produtoDB);
        //DB 2
        ProdutoJpa produto2 = criarProduto("A2");
        ProdutoJpa retorno2 = produtoDb2Dao.cadastrar(produto2);
        Assert.assertNotNull(retorno2);
        ProdutoJpa produtoDb2 = this.produtoDb2Dao.consultar(retorno2.getId());
        Assert.assertNotNull(produtoDb2);
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
        //DB 1
        ProdutoJpa produto = criarProduto("A2");
        ProdutoJpa retorno = produtoDao.cadastrar(produto);
        Assert.assertNotNull(retorno);
        //DB 2
        ProdutoJpa produto2 = criarProduto("A3");
        ProdutoJpa retorno2 = produtoDb2Dao.cadastrar(produto2);
        Assert.assertNotNull(retorno2);
    }

    @Test
    public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        //DB 1
        ProdutoJpa produto = criarProduto("A3");
        produto = produtoDao.cadastrar(produto);
        Assert.assertNotNull(produto);
        produtoDao.excluir(produto);
        ProdutoJpa produtoDb = produtoDao.consultar(produto.getId());
        Assert.assertNull(produtoDb);
        //DB 2
        ProdutoJpa produto2 = criarProduto("A3");
        produto2 = produtoDb2Dao.cadastrar(produto2);
        Assert.assertNotNull(produto2);
        produtoDb2Dao.excluir(produto2);
        ProdutoJpa produtoDb2 = produtoDb2Dao.consultar(produto2.getId());
        assertNull(produtoDb2);
    }

    @Test
    public void alterarProduto() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        //DB 1
        ProdutoJpa produto = criarProduto("A4");
        ProdutoJpa retorno = produtoDao.cadastrar(produto);
        retorno.setNome("Pedro");
        produtoDao.alterar(retorno);
        ProdutoJpa produtoDb = this.produtoDao.consultar(retorno.getId());
        assertNotNull(produtoDb);
        Assert.assertEquals("Pedro", produtoDb.getNome());
        //DB 2
        ProdutoJpa produto2 = criarProduto("A4");
        ProdutoJpa retorno2 = produtoDb2Dao.cadastrar(produto2);
        retorno2.setNome("Pedro");
        produtoDb2Dao.alterar(retorno2);
        ProdutoJpa produtoDb2 = this.produtoDb2Dao.consultar(retorno2.getId());
        assertNotNull(produtoDb2);
        Assert.assertEquals("Pedro", produtoDb2.getNome());
    }

    @Test
    public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException {
        //DB 1
        produtoDao.cadastrar(criarProduto("A5"));
        produtoDao.cadastrar(criarProduto("A6"));
        Collection<ProdutoJpa> list = produtoDao.buscarTodos();
        Assert.assertTrue(list != null);
        Assert.assertTrue(list.size() == 2);
        //DB 2
        produtoDb2Dao.cadastrar(criarProduto("A5"));
        produtoDb2Dao.cadastrar(criarProduto("A6"));
        Collection<ProdutoJpa> list2 = produtoDb2Dao.buscarTodos();
        Assert.assertTrue(list2 != null);
        Assert.assertTrue(list2.size() == 2);
    }

    private ProdutoJpa criarProduto(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
        ProdutoJpa produto = new ProdutoJpa();
        produto.setCodigo(codigo);
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
        produto.setDistribuidor("Amazon");
        return produto;
    }
}
