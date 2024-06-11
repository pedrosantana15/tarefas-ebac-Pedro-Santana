/**
 * 
 */
package br.com.spedro.services;

import br.com.spedro.dao.IProdutoDAO;
import br.com.spedro.domain.Produto;
import br.com.spedro.services.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

}
