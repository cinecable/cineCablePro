package net.cinecable.dao;

import javax.ejb.Local;

import net.cinecable.model.base.ParametrosBancos;

@Local
public interface IParametrosBancoDao extends IGenericDao<ParametrosBancos, Long> {

	ParametrosBancos consultaParametrosbyIdBanco(Long bancoId,String TipGen);
}
