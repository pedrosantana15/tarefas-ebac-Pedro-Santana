package br.com.spedro.jpa;

import br.com.spedro.dao.jpa.ClienteJpaDAO;
import br.com.spedro.dao.jpa.IClienteJpaDAO;
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

public class ClienteJpaDAOTest {

    private IClienteJpaDAO clienteDao;
    private Random rd;

    public ClienteJpaDAOTest(){
        clienteDao = new ClienteJpaDAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<ClienteJpa> clientes = clienteDao.buscarTodos();
        clientes.forEach(c -> {
            try {
                clienteDao.excluir(c);
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void cadastrar() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ClienteJpa cliente = buildCliente();
        ClienteJpa clienteDb = clienteDao.cadastrar(cliente);
        Assert.assertNotNull(clienteDb);
        Assert.assertNotNull(clienteDb.getId());

        ClienteJpa clienteConsultado = clienteDao.consultar(clienteDb.getId());
        Assert.assertNotNull(clienteConsultado);

    }

    @Test
    public void alterar() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ClienteJpa cliente = buildCliente();
        ClienteJpa clienteDb = clienteDao.cadastrar(cliente);

        clienteDb.setNome("Pedro Henrique");
        ClienteJpa clienteAlterado = clienteDao.alterar(clienteDb);
        Assert.assertNotNull(clienteAlterado);

        ClienteJpa clienteConsultado = clienteDao.consultar(clienteAlterado.getId());
        Assert.assertNotNull(clienteConsultado);
        Assert.assertEquals(clienteAlterado.getId(), clienteConsultado.getId());
        Assert.assertEquals(clienteConsultado.getNome(), "Pedro Henrique");

    }

    @Test
    public void consultar() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ClienteJpa cliente = buildCliente();
        ClienteJpa clienteDb = clienteDao.cadastrar(cliente);

        ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
        Assert.assertNotNull(clienteConsultado);
        Assert.assertEquals(clienteConsultado.getId(), clienteDb.getId());
    }

    @Test
    public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ClienteJpa cliente = buildCliente();
        ClienteJpa clienteDb = clienteDao.cadastrar(cliente);
        Assert.assertNotNull(clienteDb);
        Assert.assertNotNull(clienteDb.getId());

        clienteDao.excluir(clienteDb);

        ClienteJpa clienteConsultado = clienteDao.consultar(clienteDb.getId());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException {
        ClienteJpa cliente1 = buildCliente();
        ClienteJpa clienteDb1 = clienteDao.cadastrar(cliente1);
        Assert.assertNotNull(clienteDb1);

        ClienteJpa cliente2 = buildCliente();
        ClienteJpa clienteDb2 = clienteDao.cadastrar(cliente2);
        Assert.assertNotNull(clienteDb2);

        Collection<ClienteJpa> list;
        list = clienteDao.buscarTodos();
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
    }

    private ClienteJpa buildCliente(){
        ClienteJpa cliente = new ClienteJpa();
        cliente.setCpf(rd.nextLong());
        cliente.setNome("Pedro");
        cliente.setTel(1192372372L);
        cliente.setEnd("Rua das Flores");
        cliente.setCidade("São Paulo");
        cliente.setNumero(3);
        cliente.setCep(1895123L);
        cliente.setEstado("São Paulo");
        return cliente;
    }

}
