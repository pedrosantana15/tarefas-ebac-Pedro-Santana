
package br.com.spedro.dao.factory;

import br.com.spedro.domain.jpa.ClienteJpa;
import br.com.spedro.domain.jpa.VendaJpa;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VendaFactory {

	public static VendaJpa convert(ResultSet rs) throws SQLException {
		ClienteJpa cliente = ClienteFactory.convert(rs);
		VendaJpa venda = new VendaJpa();
		venda.setCliente(cliente);
		venda.setId(rs.getLong("ID_VENDA"));
		venda.setCodigo(rs.getString("CODIGO"));
		venda.setValorTotal(rs.getBigDecimal("VALOR_TOTAL"));
		venda.setDataVenda(rs.getTimestamp("DATA_VENDA").toInstant());
		venda.setStatus(VendaJpa.Status.getByName(rs.getString("STATUS_VENDA")));
		return venda;
	}
}
