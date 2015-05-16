package net.cinecable.service.imp;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.IParametrosBancoDao;
import net.cinecable.model.base.ParametrosBancos;
import net.cinecable.service.IParametrosBancoService;

@Stateless
public class ParametrosBancoService implements IParametrosBancoService {

	@EJB
	IParametrosBancoDao iParametroBanco;

	@Override
	public ParametrosBancos consultaParametrosbyIdBanco(Long codBanco, String tipGen) {
		return iParametroBanco.consultaParametrosbyIdBanco(codBanco, tipGen);
	}

}
