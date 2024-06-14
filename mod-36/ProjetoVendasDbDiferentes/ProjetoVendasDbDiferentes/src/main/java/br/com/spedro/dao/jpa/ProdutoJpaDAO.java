package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.GenericJpaDAO;
import br.com.spedro.dao.generic.jpa.GenericJpaDb1DAO;
import br.com.spedro.domain.jpa.ProdutoJpa;

public class ProdutoJpaDAO extends GenericJpaDb1DAO<ProdutoJpa, Long> implements IProdutoJpaDAO<ProdutoJpa>{

    public ProdutoJpaDAO() {
        super(ProdutoJpa.class);
    }

}
