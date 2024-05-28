package br.com.spedro.test;

import br.com.spedro.dao.IClienteDao;
import br.com.spedro.domain.Cliente;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;
import br.com.spedro.mock.ClienteDaoMock;
import br.com.spedro.services.ClienteService;
import br.com.spedro.services.IClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteServiceTest {

    private IClienteService clienteService;
    private Cliente cliente;

    public ClienteServiceTest(){
        IClienteDao mock = new ClienteDaoMock();
        clienteService = new ClienteService(mock);
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
        clienteService.salvar(cliente);
    }

    @Test
    public void pesquisarCliente(){
        Cliente clienteConsultado = clienteService.buscarPorCpf(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCLiente() throws TipoChaveNaoEncontradaException {
        Boolean retorno = clienteService.salvar(cliente);
        Assert.assertTrue(retorno);
    }

    @Test
    public void excluirCliente(){
        clienteService.excluir(cliente.getCpf());
    }

    @Test
    public void atualizarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Pedro Henrique");
        clienteService.atualizar(cliente);
        Assert.assertEquals("Pedro Henrique", cliente.getNome());
    }

}
