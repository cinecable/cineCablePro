package net.cinecable.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import net.cinecable.dm.ParamDebitoDM;
import net.cinecable.service.IBancoServices;
import pojo.annotations.Bancos;

@ManagedBean(name = "paramDebitoController")
@RequestScoped
public class ParamDebitoController {
	@ManagedProperty(value = "#{paramDebitoDM}")
	private ParamDebitoDM paramDebitoDM;
	@EJB
	IBancoServices iBancoService;

	/**
	 * 1 Bancos 2 Tarjetas de credito 1 Empresa por defecto
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Bancos> getBancos() throws Exception {
		paramDebitoDM.setBancos(iBancoService.lisBancosByTipoEntidad(1, 1));
		return paramDebitoDM.getBancos();
	}

	public ParamDebitoDM getParamDebitoDM() {
		return paramDebitoDM;
	}

	public void setParamDebitoDM(ParamDebitoDM paramDebitoDM) {
		this.paramDebitoDM = paramDebitoDM;
	}

}
