package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.GenericJpaDb1DAO;
import br.com.spedro.domain.jpa.ClienteJpa;

public class ClienteJpaDAO extends GenericJpaDb1DAO<ClienteJpa, Long> implements IClienteJpaDAO<ClienteJpa> {

    public ClienteJpaDAO() {
        super(ClienteJpa.class);
    }

}
