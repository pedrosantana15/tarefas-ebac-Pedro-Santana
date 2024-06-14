package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.IGenericJpaDAO;
import br.com.spedro.domain.jpa.Persistente;
import br.com.spedro.domain.jpa.ProdutoJpa;

public interface IProdutoJpaDAO<T extends Persistente> extends IGenericJpaDAO<T, Long> {
}
