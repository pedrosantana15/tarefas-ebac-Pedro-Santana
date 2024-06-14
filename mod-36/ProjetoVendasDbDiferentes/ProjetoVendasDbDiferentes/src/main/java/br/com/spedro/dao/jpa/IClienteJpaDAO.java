package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.IGenericJpaDAO;
import br.com.spedro.domain.jpa.ClienteJpa;
import br.com.spedro.domain.jpa.Persistente;

public interface IClienteJpaDAO<T extends Persistente> extends IGenericJpaDAO<T, Long> {
}
