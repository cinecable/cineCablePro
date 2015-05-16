package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Ctacliente;
import pojo.annotations.Producto;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.ControlBodega;
import net.cinecable.model.base.ReservacionesBodegaMateriales;

@Local
public interface IReservacionBodegaMaterialesDao extends IGenericDao<ReservacionesBodegaMateriales, Long> {

	void guardarReservacionesBodegaMateriales(ControlBodega control) throws EntidadNoGrabadaException;

	void guardarReservacionesBodegaMaterialesModificada(ControlBodega control) throws EntidadNoGrabadaException;

	List<ReservacionesBodegaMateriales> getMaterialesbyProductoCuenta(Ctacliente cuenta, Producto producto);
}
