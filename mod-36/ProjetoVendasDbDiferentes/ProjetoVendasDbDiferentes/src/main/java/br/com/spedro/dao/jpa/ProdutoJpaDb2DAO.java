package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.GenericJpaDb2DAO;
import br.com.spedro.domain.jpa.ProdutoJpa;

public class ProdutoJpaDb2DAO extends GenericJpaDb2DAO<ProdutoJpa, Long> implements IProdutoJpaDAO<ProdutoJpa>{

    public ProdutoJpaDb2DAO() {
        super(ProdutoJpa.class);
    }

}
