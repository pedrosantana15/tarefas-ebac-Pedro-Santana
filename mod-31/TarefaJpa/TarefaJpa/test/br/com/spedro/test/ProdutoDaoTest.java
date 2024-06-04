package br.com.spedro.test;

import br.com.spedro.dao.IProdutoDao;
import br.com.spedro.dao.ProdutoDao;
import br.com.spedro.domain.Produto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class ProdutoDaoTest {

    IProdutoDao produtoDao;
    Produto produto;

    public ProdutoDaoTest(){
        produtoDao = new ProdutoDao();
        produto = new Produto();
    }

    @Before
    public void buildProduto(){
        produto.setCodigo("A1");
        produto.setNome("Monitor");
        produto.setDescricao("Monitor Gamer 144hz");
        produtoDao.cadastrar(produto);
    }

    @After
    public void end(){
        List<Produto> produtos = produtoDao.buscarTodos();
        Assert.assertNotNull(produtos);

        produtos.forEach(p -> produtoDao.excluir(p));
    }

    @Test
    public void cadastrar(){
        Assert.assertNotNull(produto);

        //Testando também o método consultar
        Produto produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNotNull(produtoConsultado);
        Assert.assertNotNull(produtoConsultado.getId());
    }

    @Test
    public void alterar(){
        produto.setCodigo("B1");
        produto.setNome("Mouse");
        produto.setDescricao("Mouse Gamer Logitech");

        Produto produtoAlterado = produtoDao.alterar(produto);
        Assert.assertNotNull(produto);
        Assert.assertEquals("B1", produto.getCodigo());
        Assert.assertEquals(produto.getId(), produtoAlterado.getId());
    }

    @Test
    public void excluir(){
        produtoDao.excluir(produto);

        Produto produtoConsultado = produtoDao.consultar(produto.getId());
        Assert.assertNull(produtoConsultado);
    }

    @Test
    public void buscarTodos(){
        Produto produto1 = new Produto();
        produto1.setCodigo("C4");
        produto1.setNome("Teclado");
        produto1.setDescricao("Teclado Mecânico");
        produtoDao.cadastrar(produto1);
        Assert.assertNotNull(produto1);

        Produto produto2 = new Produto();
        produto2.setCodigo("B7");
        produto2.setNome("Caixa de Som");
        produto2.setDescricao("Caixa de som da JBL");
        produtoDao.cadastrar(produto2);
        Assert.assertNotNull(produto2);

        List<Produto> produtoList = produtoDao.buscarTodos();
        //O tamanho da lista tem que ser 3 porque um objeto já está sendo cadastrado no Before
        Assert.assertEquals(3, produtoList.size());
    }
}
