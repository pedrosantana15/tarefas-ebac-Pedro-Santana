package br.com.pedro.test;

import br.com.pedro.dao.ClienteDAO;
import br.com.pedro.dao.ClienteDAOMock;
import br.com.pedro.dao.IClienteDAO;
import br.com.pedro.service.ClienteService;
import org.junit.Assert;
import org.junit.Test;

public class ClienteServiceTeste {

    @Test
    public void salvarTeste(){
        ClienteDAOMock mock = new ClienteDAOMock();
        ClienteService clienteService = new ClienteService(mock);
        String retorno = clienteService.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroSalvarTeste(){
        IClienteDAO clienteDAO = new ClienteDAO();
        ClienteService clienteService = new ClienteService(clienteDAO);
        String retorno = clienteService.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

}
