package br.com.spedro.dao.generic.jpa;

import br.com.spedro.domain.jpa.Persistente;

import java.io.Serializable;

public abstract class GenericJpaDb1DAO <T extends Persistente, E extends Serializable>
        extends GenericJpaDAO<T,E> {

    public GenericJpaDb1DAO(Class<T> persistenteClass) {
        super(persistenteClass, "Postgre1");
    }

}
