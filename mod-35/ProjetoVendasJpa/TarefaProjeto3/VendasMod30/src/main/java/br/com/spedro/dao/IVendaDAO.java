/**
 * 
 */
package br.com.spedro.dao;

import br.com.spedro.dao.generic.IGenericDAO;
import br.com.spedro.domain.Venda;
import br.com.spedro.exceptions.DAOException;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, String> {

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}
