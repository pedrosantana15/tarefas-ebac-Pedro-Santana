package br.com.spedro.test;

import br.com.spedro.dao.IClienteDao;
import br.com.spedro.domain.Cliente;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;
import br.com.spedro.mock.ClienteDaoMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteDaoTest {

    private IClienteDao clienteDao;
    private Cliente cliente;

    public ClienteDaoTest(){
        clienteDao = new ClienteDaoMock();
    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        cliente = new Cliente();
        cliente.setCpf(12345678911L);
        cliente.setNome("Pedro");
        cliente.setEnd("Rua das Flores");
        cliente.setCidade("Franco da Rocha");
        cliente.setEstado("SP");
        cliente.setNum(3);
        cliente.setTel(11945685215L);
        clienteDao.cadastrar(cliente);
    }

    @Test
    public void pesquisarCliente(){
        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);
    }

    @Test
    public void excluirCliente(){
        clienteDao.excluir(cliente.getCpf());
    }

    @Test
    public void atualizarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Pedro Henrique");
        clienteDao.alterar(cliente);
        Assert.assertEquals("Pedro Henrique", cliente.getNome());
    }

}
