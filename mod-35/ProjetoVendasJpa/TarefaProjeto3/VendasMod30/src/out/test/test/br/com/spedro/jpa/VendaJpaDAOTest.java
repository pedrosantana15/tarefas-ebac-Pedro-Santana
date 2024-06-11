package br.com.spedro.jpa;

import br.com.spedro.dao.VendaExclusaoJpaDAO;
import br.com.spedro.dao.jpa.*;
import br.com.spedro.domain.jpa.ClienteJpa;
import br.com.spedro.domain.jpa.ProdutoJpa;
import br.com.spedro.domain.jpa.VendaJpa;
import br.com.spedro.exceptions.DAOException;
import br.com.spedro.exceptions.MaisDeUmRegistroException;
import br.com.spedro.exceptions.TableException;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.Random;

public class VendaJpaDAOTest {

    private IVendaJpaDAO vendaDao;
    private IVendaJpaDAO vendaExclusaoDao;
    private IClienteJpaDAO clienteDao;
    private ClienteJpa cliente;
    private IProdutoJpaDAO produtoDao;
    private ProdutoJpa produto;
    private Random rd;

    public VendaJpaDAOTest(){
        this.vendaDao = new VendaJpaDAO();
        this.vendaExclusaoDao = new VendaExclusaoJpaDAO();
        this.clienteDao = new ClienteJpaDAO();
        this.produtoDao = new ProdutoJpaDAO();
        this.rd = new Random();
    }

    @Before
    public void init() throws DAOException, TipoChaveNaoEncontradaException {
        this.cliente = buildCliente();
        this.produto = buildProduto("F237", BigDecimal.valueOf(1000));
    }

    @After
    public void end() throws DAOException {
        Collection<VendaJpa> vendas = vendaDao.buscarTodos();
        vendas.forEach(v -> {
            try {
                vendaExclusaoDao.excluir(v);
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Test
    public void cadastrar() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda = buildVenda("A1");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);
        Assert.assertNotNull(vendaDb.getId());

        VendaJpa vendaConsultada = vendaDao.consultarComCriteria(vendaDb.getId());
        Assert.assertNotNull(vendaConsultada);
        Assert.assertTrue(vendaConsultada.getValorTotal().compareTo(BigDecimal.valueOf(2000)) == 0);
        Assert.assertTrue(vendaConsultada.getStatus().equals(VendaJpa.Status.INICIADA));
    }

    @Test
    public void alterar() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda = buildVenda("A2");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        venda.setStatus(VendaJpa.Status.CONCLUIDA);
        VendaJpa vendaAlterada = vendaDao.alterar(venda);
        Assert.assertNotNull(vendaAlterada);
        Assert.assertTrue(vendaAlterada.getStatus().equals(VendaJpa.Status.CONCLUIDA));
    }

    @Test
    public void consultar() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        VendaJpa venda = buildVenda("A3");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        VendaJpa vendaConsultada = vendaDao.consultar(vendaDb.getId());
        Assert.assertNotNull(vendaConsultada);
        Assert.assertEquals("A3", vendaConsultada.getCodigo());
    }

    @Test
    public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        VendaJpa venda = buildVenda("A4");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        vendaExclusaoDao.excluir(venda);

        VendaJpa vendaConsultada = vendaDao.consultar(vendaDb.getId());
        Assert.assertNull(vendaConsultada);
    }

    @Test
    public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda1 = buildVenda("A5");
        VendaJpa vendaDb1 = vendaDao.cadastrar(venda1);
        Assert.assertNotNull(vendaDb1);

        this.produto = buildProduto("P8", BigDecimal.ONE);
        VendaJpa venda2 = buildVenda("A6");
        VendaJpa vendaDb2 = vendaDao.cadastrar(venda2);
        Assert.assertNotNull(vendaDb2);

        Collection<VendaJpa> vendas = vendaDao.buscarTodos();
        Assert.assertNotNull(vendas);
        Assert.assertEquals(2, vendas.size());
    }

    @Test
    public void cancelarVenda() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda = buildVenda("A4");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        venda.setStatus(VendaJpa.Status.CANCELADA);
        VendaJpa vendaAlterada = vendaDao.alterar(venda);
        Assert.assertNotNull(vendaAlterada);
        Assert.assertTrue(vendaAlterada.getStatus().equals(VendaJpa.Status.CANCELADA));
    }

    @Test
    public void adicionarMesmoProduto() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda = buildVenda("A9");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        VendaJpa vendaConsultada = vendaDao.consultarComCriteria(vendaDb.getId());
        vendaConsultada.adicionarProduto(produto, 2);
        VendaJpa vendaAlterada = vendaDao.alterar(vendaConsultada);
        Assert.assertNotNull(vendaAlterada);

        Assert.assertEquals(4, (int) vendaAlterada.getQuantidadeTotalProdutos());
    }

    @Test
    public void adicionarProdutoDiferente() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda = buildVenda("A9");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        ProdutoJpa produto = buildProduto("YSL4", BigDecimal.TWO);
        vendaDb.adicionarProduto(produto, 5);
        VendaJpa vendaAlterada = vendaDao.alterar(vendaDb);
        Assert.assertNotNull(vendaAlterada);
        Assert.assertTrue(vendaAlterada.getQuantidadeTotalProdutos().equals(7));
        Assert.assertTrue(vendaAlterada.getValorTotal().compareTo(BigDecimal.valueOf(2010)) == 0);
    }

    @Test(expected = DAOException.class)
    public void cadastrarVendaComMesmoCodigo() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda1 = buildVenda("A9");
        VendaJpa vendaDb1 = vendaDao.cadastrar(venda1);
        Assert.assertNotNull(vendaDb1);

        VendaJpa venda2 = buildVenda("A9");
        VendaJpa vendaDb2 = vendaDao.cadastrar(venda2);
        Assert.assertNull(vendaDb2);
        Assert.assertTrue(venda1.getStatus().equals(VendaJpa.Status.INICIADA));
    }

    @Test
    public void finalizarVenda() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda = buildVenda("A4");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        venda.setStatus(VendaJpa.Status.CONCLUIDA);
        VendaJpa vendaAlterada = vendaDao.alterar(venda);
        Assert.assertNotNull(vendaAlterada);

        VendaJpa vendaConsultada = vendaDao.consultarComCriteria(vendaAlterada.getId());
        Assert.assertNotNull(vendaConsultada);
        Assert.assertTrue(vendaConsultada.getStatus().equals(VendaJpa.Status.CONCLUIDA));
    }

    @Test
    public void removerProduto() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda = buildVenda("A4");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        ProdutoJpa produto = buildProduto("237", BigDecimal.valueOf(100));
        vendaDb.adicionarProduto(produto, 2);
        VendaJpa vendaAlterada = vendaDao.alterar(vendaDb);
        Assert.assertNotNull(vendaAlterada);
        Assert.assertTrue(vendaAlterada.getQuantidadeTotalProdutos() == 4);

        vendaAlterada.removerProduto(produto, 1);
        VendaJpa vendaRemocao = vendaDao.alterar(vendaAlterada);
        Assert.assertNotNull(vendaRemocao);
        Assert.assertTrue(vendaRemocao.getQuantidadeTotalProdutos() == 3);
    }

    @Test
    public void removerTodosProdutos() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda = buildVenda("A4");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        VendaJpa vendaAlterada = vendaDao.alterar(vendaDb);
        Assert.assertNotNull(vendaAlterada);
        Assert.assertTrue(vendaAlterada.getQuantidadeTotalProdutos() == 2);
        vendaAlterada.removerTodosProdutos();

        VendaJpa vendaRemocao = vendaDao.alterar(vendaAlterada);
        Assert.assertNotNull(vendaRemocao);
        Assert.assertTrue(vendaRemocao.getQuantidadeTotalProdutos() == 0);
    }

    @Test
    public void removerMaisProdutosExistentes() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda = buildVenda("A4");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        ProdutoJpa produto = buildProduto("237", BigDecimal.valueOf(100));
        vendaDb.adicionarProduto(produto, 2);
        VendaJpa vendaAlterada = vendaDao.alterar(vendaDb);
        Assert.assertNotNull(vendaAlterada);
        Assert.assertTrue(vendaAlterada.getQuantidadeTotalProdutos() == 4);

        vendaAlterada.removerProduto(produto, 10);
        VendaJpa vendaRemocao = vendaDao.alterar(vendaAlterada);
        Assert.assertNotNull(vendaRemocao);
        Assert.assertTrue(vendaRemocao.getQuantidadeTotalProdutos() == 2);
        Assert.assertTrue(vendaRemocao.getProdutos().size() == 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void tentarAdicionarProdutosVendaFinalizada() throws DAOException, TipoChaveNaoEncontradaException {
        VendaJpa venda = buildVenda("A4");
        VendaJpa vendaDb = vendaDao.cadastrar(venda);
        Assert.assertNotNull(vendaDb);

        vendaDb.setStatus(VendaJpa.Status.CONCLUIDA);
        vendaDao.finalizarVenda(vendaDb);

        VendaJpa vendaConsultada = vendaDao.consultarComCriteria(vendaDb.getId());
        Assert.assertNotNull(vendaConsultada);

        vendaConsultada.adicionarProduto(this.produto, 2);
        Assert.assertTrue(vendaDb.getProdutos().size() == 1);
        Assert.assertTrue(vendaConsultada.getStatus().equals(VendaJpa.Status.CONCLUIDA));
    }

    private VendaJpa buildVenda(String codigo){
        VendaJpa venda = new VendaJpa();
        venda.setCodigo(codigo);
        venda.setDataVenda(Instant.now());
        venda.setCliente(this.cliente);
        venda.setStatus(VendaJpa.Status.INICIADA);
        venda.adicionarProduto(this.produto, 2);
        return venda;
    }

    private ClienteJpa buildCliente() throws DAOException, TipoChaveNaoEncontradaException {
        ClienteJpa cliente = new ClienteJpa();
        cliente.setCpf(rd.nextLong());
        cliente.setNome("Pedro");
        cliente.setTel(1192372372L);
        cliente.setEnd("Rua das Flores");
        cliente.setCidade("São Paulo");
        cliente.setNumero(3);
        cliente.setCep(1895123L);
        cliente.setEstado("São Paulo");
        clienteDao.cadastrar(cliente);
        return cliente;
    }

    private ProdutoJpa buildProduto(String codigo, BigDecimal valor) throws DAOException, TipoChaveNaoEncontradaException {
        ProdutoJpa produto = new ProdutoJpa();
        produto.setCodigo(codigo);
        produto.setNome("Monitor");
        produto.setDescricao("Monitor Gamer 144hz");
        produto.setValor(valor);
        produto.setDistribuidor("Amazon");
        produtoDao.cadastrar(produto);
        return produto;
    }


}
