package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Bancos;



@Local
public interface IBancoServices {
	public Bancos getBancosById(int idbanco) throws Exception;

	public List<Bancos> lisBancosByTipoEntidad(int idempresa, int idtipoentidad) throws Exception;
}
