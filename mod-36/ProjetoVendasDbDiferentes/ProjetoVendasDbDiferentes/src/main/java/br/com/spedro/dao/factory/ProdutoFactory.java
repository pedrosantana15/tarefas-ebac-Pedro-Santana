
package br.com.spedro.dao.factory;

import br.com.spedro.domain.jpa.ProdutoJpa;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoFactory {

	
	public static ProdutoJpa convert(ResultSet rs) throws SQLException {
		ProdutoJpa prod = new ProdutoJpa();
		prod.setId(rs.getLong("ID_PRODUTO"));
		prod.setCodigo(rs.getString("CODIGO"));
		prod.setNome(rs.getString("NOME"));
		prod.setDescricao(rs.getString("DESCRICAO"));
		prod.setValor(rs.getBigDecimal("VALOR"));
		return prod;
	}
}
