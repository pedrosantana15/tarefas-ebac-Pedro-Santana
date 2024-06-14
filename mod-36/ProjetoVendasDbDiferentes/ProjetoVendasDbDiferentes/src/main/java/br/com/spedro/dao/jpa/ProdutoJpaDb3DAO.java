package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.GenericJpaDb3DAO;
import br.com.spedro.domain.jpa.ProdutoJpa;

public class ProdutoJpaDb3DAO extends GenericJpaDb3DAO<ProdutoJpa, Long> implements IProdutoJpaDAO<ProdutoJpa> {

    public ProdutoJpaDb3DAO() {
        super(ProdutoJpa.class);
    }

}
