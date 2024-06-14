package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.GenericJpaDb2DAO;
import br.com.spedro.dao.generic.jpa.GenericJpaDb3DAO;
import br.com.spedro.domain.jpa.ClienteJpa;
import br.com.spedro.domain.jpa.Persistente;

import java.io.Serializable;

public class ClienteJpaDb3DAO extends GenericJpaDb3DAO<ClienteJpa, Long> implements IClienteJpaDAO<ClienteJpa>{

    public ClienteJpaDb3DAO() {
        super(ClienteJpa.class);
    }

}
