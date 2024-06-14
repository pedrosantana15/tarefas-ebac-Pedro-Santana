package br.com.spedro.dao.jpa;

import br.com.spedro.domain.jpa.ClienteJpa;
import br.com.spedro.exceptions.DAOException;
import br.com.spedro.exceptions.MaisDeUmRegistroException;
import br.com.spedro.exceptions.TableException;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import java.util.Collection;
import java.util.Random;


public class ClienteJpaDb2DAOTest {

    private IClienteJpaDAO<ClienteJpa> clienteDao;

    private IClienteJpaDAO<ClienteJpa> clienteDb2Dao;

    private Random rd;

    public ClienteJpaDb2DAOTest() {
        this.clienteDao = new ClienteJpaDAO();
        this.clienteDb2Dao = new ClienteJpaDb2DAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<ClienteJpa> list = clienteDao.buscarTodos();
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
        Collection<ClienteJpa> listDb2 = clienteDb2Dao.buscarTodos();
        listDb2.forEach(cli -> {
            try {
                clienteDb2Dao.excluir(cli);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        //DB 1
        ClienteJpa cliente = criarCliente();
        clienteDao.cadastrar(cliente);
        ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);
        //DB 2
        cliente.setId(null);
        clienteDb2Dao.cadastrar(cliente);
        ClienteJpa clienteConsultado2 = clienteDb2Dao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado2);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        //DB 1
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDao.cadastrar(cliente);
        Assert.assertNotNull(retorno);
        ClienteJpa clienteConsultado = clienteDao.consultar(retorno.getId());
        Assert.assertNotNull(clienteConsultado);
        //DB 2
        cliente.setId(null);
        ClienteJpa retorno2 = clienteDb2Dao.cadastrar(cliente);
        Assert.assertNotNull(retorno2);
        ClienteJpa clienteConsultadoDb2 = clienteDb2Dao.consultar(retorno2.getId());
        Assert.assertNotNull(clienteConsultadoDb2);
    }

    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        //DB 1
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDao.cadastrar(cliente);
        Assert.assertNotNull(retorno);
        ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);
        cliente.setId(null);
        //DB 2
        ClienteJpa retorno2 = clienteDb2Dao.cadastrar(cliente);
        Assert.assertNotNull(retorno2);
        ClienteJpa clienteConsultado2 = clienteDb2Dao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado2);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {//DB 1
        //DB 1
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDao.cadastrar(cliente);
        Assert.assertNotNull(retorno);
        ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);
        clienteConsultado.setNome("Pedro Henrique");
        clienteDao.alterar(clienteConsultado);
        ClienteJpa clienteAlterado = clienteDao.consultar(clienteConsultado.getId());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("Pedro Henrique", clienteAlterado.getNome());
        cliente.setId(null);
        //DB 2
        ClienteJpa retorno2 = clienteDb2Dao.cadastrar(cliente);
        Assert.assertNotNull(retorno2);
        clienteConsultado = clienteDb2Dao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);
        clienteConsultado.setNome("Pedro Henrique");
        clienteDb2Dao.alterar(clienteConsultado);
        clienteAlterado = clienteDb2Dao.consultar(clienteConsultado.getId());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("Pedro Henrique", clienteAlterado.getNome());
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        //DB 1
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDao.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        ClienteJpa cliente1 = criarCliente();
        ClienteJpa retorno1 = clienteDao.cadastrar(cliente1);
        Assert.assertNotNull(retorno1);

        Collection<ClienteJpa> list = clienteDao.buscarTodos();
        Assert.assertTrue(list != null);
        Assert.assertTrue(list.size() == 2);
        cliente.setId(null);
        cliente1.setId(null);
        //DB 2
        retorno = clienteDb2Dao.cadastrar(cliente);
        Assert.assertNotNull(retorno);

        retorno1 = clienteDb2Dao.cadastrar(cliente1);
        Assert.assertNotNull(retorno1);

        Collection<ClienteJpa> list2 = clienteDb2Dao.buscarTodos();
        Assert.assertTrue(list2 != null);
        Assert.assertTrue(list2.size() == 2);
    }

    private ClienteJpa criarCliente() {
        ClienteJpa cliente = new ClienteJpa();
        cliente.setCpf(rd.nextLong());
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setCep(1144453L);
        cliente.setTel(1199999999L);
        return cliente;
    }
}
