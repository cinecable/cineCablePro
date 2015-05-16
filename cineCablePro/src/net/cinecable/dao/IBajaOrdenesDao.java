package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.BajaOrdenes;
import net.cinecable.model.base.Ordenes;
import pojo.annotations.Costoservicio;
import pojo.annotations.Usuario;

@Local
public interface IBajaOrdenesDao extends IGenericDao<BajaOrdenes, Long> {

	public void guardaCargos(Ordenes orden, List<Costoservicio> cargosAdicionales, Usuario usuario) throws EntidadNoGrabadaException;

}
