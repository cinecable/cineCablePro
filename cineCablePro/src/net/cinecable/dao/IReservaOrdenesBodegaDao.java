package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.model.base.ReservacionesOrdenesBodega;

@Local
public interface IReservaOrdenesBodegaDao extends IGenericDao<ReservacionesOrdenesBodega, Long> {

	List<ReservacionesOrdenesBodega> getReservasOrdenesBodegaByTecnicoAndTipoOrden(Long codTecnico, Long TipOrden);

}
