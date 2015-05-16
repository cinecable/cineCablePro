package net.cinecable.service.imp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.IFacturaGeneracionDebitoBancarioDao;
import net.cinecable.model.extension.FacturasDebito;
import net.cinecable.service.IFacturaGeneracionDebitoBancarioService;
import pojo.annotations.Bancos;

@Stateless
public class FacturaGeneracionDebitoService implements IFacturaGeneracionDebitoBancarioService {

	@EJB
	IFacturaGeneracionDebitoBancarioDao iFacturaDebitoDao;

	@Override
	public void getDebitosClientesBancoId2(List<FacturasDebito> deb, List<Bancos> codBanco, boolean todos) throws Exception {
		iFacturaDebitoDao.getDebitosClientesBancoId(deb, codBanco, todos);
	}
	@Override
	public void getDebitosClientesBancoId(List<FacturasDebito> deb, List<Bancos> codBanco, boolean todos) throws Exception {
		iFacturaDebitoDao.getDebitosClientesBancoId(deb, codBanco, todos);
	}
	

}
