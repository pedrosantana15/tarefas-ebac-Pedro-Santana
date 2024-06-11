package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.GenericJpaDAO;
import br.com.spedro.domain.jpa.ProdutoJpa;

public class ProdutoJpaDAO extends GenericJpaDAO<ProdutoJpa, Long> implements IProdutoJpaDAO{

    public ProdutoJpaDAO() {
        super(ProdutoJpa.class);
    }

}
