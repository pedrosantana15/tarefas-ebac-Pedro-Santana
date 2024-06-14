
package br.com.spedro.dao.factory;

import br.com.spedro.domain.jpa.ProdutoJpa;
import br.com.spedro.domain.jpa.ProdutoQuantidadeJpa;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoQuantidadeFactory {

	public static ProdutoQuantidadeJpa convert(ResultSet rs) throws SQLException {
		ProdutoJpa prod = ProdutoFactory.convert(rs);
		ProdutoQuantidadeJpa prodQ = new ProdutoQuantidadeJpa();
		prodQ.setProduto(prod);
		prodQ.setId(rs.getLong("ID"));
		prodQ.setQuantidade(rs.getInt("QUANTIDADE"));
		prodQ.setValorTotal(rs.getBigDecimal("VALOR_TOTAL"));
		return prodQ;
	}
}
