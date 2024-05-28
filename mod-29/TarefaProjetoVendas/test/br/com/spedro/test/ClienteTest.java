package br.com.spedro.test;

import br.com.spedro.dao.ClienteDao;
import br.com.spedro.dao.IClienteDao;
import br.com.spedro.domain.Cliente;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class ClienteTest {

    @Test
    public void cadastrarTest() throws Exception {
        Cliente cliente = new Cliente();
        ClienteDao clienteDao = new ClienteDao();

        //Setando as informações
        cliente.setNome("Pedro");
        cliente.setCodigo("1507");
        clienteDao.cadastrar(cliente);

        //Buscando o cliente cadastrado no banco e verificando se os campos estão corretos
        Cliente clienteDb = clienteDao.consultar(cliente.getCodigo());
        Assert.assertNotNull(clienteDb);
        Assert.assertNotNull(clienteDb.getId());
        Assert.assertEquals(clienteDb.getCodigo(), cliente.getCodigo());
        Assert.assertEquals(clienteDb.getNome(), cliente.getNome());

        //Excluindo o cliente
        Integer qtd = clienteDao.excluir(clienteDb);
        Assert.assertNotNull(qtd);
    }

    @Test
    public void excluirTest() throws Exception {
        Cliente cliente = new Cliente();
        IClienteDao clienteDao = new ClienteDao();

        cliente.setNome("Gustavo");
        cliente.setCodigo("1489");
        clienteDao.cadastrar(cliente);
        Integer qtd = clienteDao.excluir(cliente);

        Assert.assertNotNull(qtd);
        Assert.assertEquals(1, (int) qtd);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        Cliente cliente = new Cliente();
        Cliente cliente2 = new Cliente();
        IClienteDao clienteDao = new ClienteDao();

        cliente.setNome("Lucas");
        cliente.setCodigo("7589");
        cliente2.setNome("Pedro");
        cliente2.setCodigo("5587");
        clienteDao.cadastrar(cliente);
        clienteDao.cadastrar(cliente2);

        List<Cliente> clienteList = clienteDao.buscarTodos();
        Assert.assertNotNull(clienteList);

        List<Cliente> clientesEncontrados = clienteList.stream()
                .filter(c -> c.getCodigo().equals("7589") || c.getCodigo().equals("5587"))
                .toList();

        Assert.assertEquals(2, clientesEncontrados.size());

        Integer qtd = clienteDao.excluir(cliente);
        qtd += clienteDao.excluir(cliente2);

        Assert.assertEquals(2, (int) qtd);
    }

    @Test
    public void alterarTest() throws Exception {
        Cliente cliente = new Cliente();
        IClienteDao clienteDao = new ClienteDao();

        cliente.setNome("João");
        cliente.setCodigo("1245");
        clienteDao.cadastrar(cliente);

        Cliente clienteAlterado = new Cliente();
        clienteAlterado.setNome("João Carlos");
        clienteAlterado.setCodigo("1245");
        Integer qtd = clienteDao.alterar(clienteAlterado, clienteAlterado.getCodigo());

        Assert.assertEquals(1, (int) qtd);
        Assert.assertNotEquals(clienteAlterado.getNome(), cliente.getNome());
        Assert.assertEquals(clienteAlterado.getNome(), "João Carlos");
        Assert.assertEquals(clienteAlterado.getCodigo(), cliente.getCodigo());

        Integer qtdDel = clienteDao.excluir(clienteAlterado);
        Assert.assertNotNull(qtdDel);
    }


}
