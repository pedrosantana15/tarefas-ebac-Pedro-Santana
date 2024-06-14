package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.GenericJpaDb2DAO;
import br.com.spedro.domain.jpa.ClienteJpa;

public class ClienteJpaDb2DAO extends GenericJpaDb2DAO<ClienteJpa, Long> implements IClienteJpaDAO<ClienteJpa> {

    public ClienteJpaDb2DAO() {
        super(ClienteJpa.class);
    }


}
