package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.model.extension.FacturasDebito;
import pojo.annotations.Bancos;

@Local
public interface IFacturaGeneracionDebitoBancarioService {

	public void getDebitosClientesBancoId2(List<FacturasDebito> deb, List<Bancos> codBanco/*, Date fechaEjecucion*/, boolean todos) throws Exception;
	public void getDebitosClientesBancoId(List<FacturasDebito> deb, List<Bancos> codBanco/*, Date fechaEjecucion*/, boolean todos) throws Exception;

}
