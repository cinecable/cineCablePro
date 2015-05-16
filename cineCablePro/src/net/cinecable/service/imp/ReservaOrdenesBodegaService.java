package net.cinecable.service.imp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.IReservaOrdenesBodegaDao;
import net.cinecable.model.base.ReservacionesOrdenesBodega;
import net.cinecable.service.IReservaOrdenesBodegaService;

@Stateless
public class ReservaOrdenesBodegaService implements IReservaOrdenesBodegaService {

	@EJB
	IReservaOrdenesBodegaDao ireservaOrdenesDao;

	@Override
	public List<ReservacionesOrdenesBodega> getReservasOrdenesBodegaByTecnicoAndTipoOrden(Long codTecnico, Long TipOrden) {
		List<ReservacionesOrdenesBodega> result = ireservaOrdenesDao.getReservasOrdenesBodegaByTecnicoAndTipoOrden(codTecnico, TipOrden);
		return result;
	}

}
