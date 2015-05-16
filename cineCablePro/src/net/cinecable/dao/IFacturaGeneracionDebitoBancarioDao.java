package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.model.extension.FacturasDebito;
import pojo.annotations.Bancos;
import pojo.annotations.Factura;

@Local
public interface IFacturaGeneracionDebitoBancarioDao extends IGenericDao<Factura, Long> {

	void getDebitosClientesBancoId(List<FacturasDebito> deb, List<Bancos> codBanco/*, Date fechaEjecucion*/, boolean todos);

}
