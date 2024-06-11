package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.GenericJpaDAO;
import br.com.spedro.domain.jpa.ClienteJpa;

public class ClienteJpaDAO extends GenericJpaDAO<ClienteJpa, Long> implements IClienteJpaDAO{

    public ClienteJpaDAO() {
        super(ClienteJpa.class);
    }

}
