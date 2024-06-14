package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.IGenericJpaDAO;
import br.com.spedro.domain.jpa.Persistente;
import br.com.spedro.domain.jpa.VendaJpa;
import br.com.spedro.exceptions.DAOException;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaJpaDAO<T extends Persistente> extends IGenericJpaDAO<T, Long> {

    public void finalizarVenda(VendaJpa venda) throws DAOException, TipoChaveNaoEncontradaException;

    public void cancelarVenda(VendaJpa venda) throws DAOException, TipoChaveNaoEncontradaException;

    public VendaJpa consultarComCriteria(Long id);

}
