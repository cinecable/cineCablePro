package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Costoservicio;
import net.cinecable.dm.BajaOrdenesDM;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.BajaOrdenes;

@Local
public interface IBajaOrdenServices {

	void guardarBaja(BajaOrdenes baja, BajaOrdenesDM bajaOrden, List<Costoservicio> serviciosAdicc) throws EntidadNoGrabadaException;

}
