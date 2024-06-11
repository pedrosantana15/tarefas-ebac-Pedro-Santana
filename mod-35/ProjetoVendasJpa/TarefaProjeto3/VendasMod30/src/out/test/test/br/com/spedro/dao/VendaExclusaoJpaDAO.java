package br.com.spedro.dao;

import br.com.spedro.dao.generic.jpa.GenericJpaDAO;
import br.com.spedro.dao.jpa.IVendaJpaDAO;
import br.com.spedro.domain.jpa.VendaJpa;
import br.com.spedro.exceptions.DAOException;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;

public class VendaExclusaoJpaDAO extends GenericJpaDAO<VendaJpa, Long> implements IVendaJpaDAO {

    public VendaExclusaoJpaDAO() {
        super(VendaJpa.class);
    }

    @Override
    public void finalizarVenda(VendaJpa venda) throws DAOException, TipoChaveNaoEncontradaException {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    public void cancelarVenda(VendaJpa venda) throws DAOException, TipoChaveNaoEncontradaException {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    public VendaJpa consultarComCriteria(Long id) {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

}
