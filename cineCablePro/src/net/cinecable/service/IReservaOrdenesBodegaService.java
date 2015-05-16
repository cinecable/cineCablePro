package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.model.base.ReservacionesOrdenesBodega;

@Local
public interface IReservaOrdenesBodegaService {

	List<ReservacionesOrdenesBodega> getReservasOrdenesBodegaByTecnicoAndTipoOrden(Long codTecnico, Long TipOrden);

}
