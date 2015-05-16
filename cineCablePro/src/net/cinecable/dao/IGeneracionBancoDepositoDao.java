package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.model.base.GeneracionBancoDepositos;
import net.cinecable.model.extension.DebitosDetalle;
import pojo.annotations.Bancos;

@Local
public interface IGeneracionBancoDepositoDao extends IGenericDao<GeneracionBancoDepositos, Long> {

	void guardarDebitosRecibidos(List<DebitosDetalle> debitosDetalle) throws Exception;

	GeneracionBancoDepositos getGeneracionbyIdUnicoAndBanco(Long idUnico, Bancos banco);
}
