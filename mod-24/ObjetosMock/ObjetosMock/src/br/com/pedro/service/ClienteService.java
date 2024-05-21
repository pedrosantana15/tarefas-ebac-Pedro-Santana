package br.com.pedro.service;

import br.com.pedro.dao.IClienteDAO;

public class ClienteService{

    private IClienteDAO clienteDAO;

    public ClienteService(IClienteDAO clienteDAO){
        this.clienteDAO = clienteDAO;
    }

    public String salvar(){
        clienteDAO.salvar();
        return "Sucesso";
    }

}
