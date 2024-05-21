package br.com.spedro.dao;

import br.com.spedro.dao.generics.GenericDAO;
import br.com.spedro.domain.Cliente;
import br.com.spedro.domain.IPersistente;

public class ClienteDao extends GenericDAO<Cliente,Long> implements IClienteDao{
    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public void atualizarDados(Cliente entity, Cliente entityCadastrado) {

    }

}
