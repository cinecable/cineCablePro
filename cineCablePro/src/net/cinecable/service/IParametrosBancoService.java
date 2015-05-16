package net.cinecable.service;

import javax.ejb.Local;

import net.cinecable.model.base.ParametrosBancos;

@Local
public interface IParametrosBancoService {
	public ParametrosBancos consultaParametrosbyIdBanco(Long codBanco,String tipGen);
}
