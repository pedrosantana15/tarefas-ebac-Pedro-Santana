package br.com.spedro.services;

import br.com.spedro.dao.ClienteDao;
import br.com.spedro.dao.IClienteDao;
import br.com.spedro.domain.Cliente;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;

public class ClienteService implements IClienteService{

    private IClienteDao clienteDao;

    public ClienteService(IClienteDao clienteDao){
        this.clienteDao = clienteDao;
    }

    @Override
    public Boolean salvar(Cliente cliente) throws TipoChaveNaoEncontradaException {
        clienteDao.cadastrar(cliente);
        return true;
    }

    @Override
    public Cliente buscarPorCpf(Long cpf) {
        return clienteDao.consultar(cpf);
    }

    @Override
    public void excluir(Long cpf) {
        clienteDao.excluir(cpf);
    }

    @Override
    public void atualizar(Cliente cliente) throws TipoChaveNaoEncontradaException {
        clienteDao.alterar(cliente);
    }
}
