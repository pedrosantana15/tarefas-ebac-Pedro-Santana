package br.com.pedro.test;

import br.com.pedro.dao.ContratoDAO;
import br.com.pedro.dao.ContratoDAOMock;
import br.com.pedro.dao.IContratoDAO;
import br.com.pedro.service.ContratoService;
import br.com.pedro.service.IContratoService;
import org.junit.Assert;
import org.junit.Test;

public class ContratoServiceTeste {

    @Test
    public void salvarTeste(){
        IContratoDAO mock = new ContratoDAOMock();
        IContratoService service = new ContratoService(mock);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroNoBanco(){
        IContratoDAO contratoDAO = new ContratoDAO();
        IContratoService service = new ContratoService(contratoDAO);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test
    public void atualizarTeste(){
        IContratoDAO mock = new ContratoDAOMock();
        IContratoService contratoService = new ContratoService(mock);
        String retorno = contratoService.atualizar();
        Assert.assertEquals("Contrato atualizado com sucesso", retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroAoAtualizar(){
        IContratoDAO contratoDAO = new ContratoDAO();
        IContratoService contratoService = new ContratoService(contratoDAO);
        String retorno = contratoService.atualizar();
        Assert.assertEquals("Contrato atualizado com sucesso", retorno);
    }

    @Test
    public void buscarTeste(){
        IContratoDAO mock = new ContratoDAOMock();
        IContratoService contratoService = new ContratoService(mock);
        String retorno = contratoService.buscar();
        Assert.assertEquals("Contrato encontrado", retorno);
    }

    @Test
    public void excluirTeste(){
        IContratoDAO mock = new ContratoDAOMock();
        IContratoService contratoService = new ContratoService(mock);
        String retorno = contratoService.excluir();
        Assert.assertEquals("Contrato excluido", retorno);
    }

}
