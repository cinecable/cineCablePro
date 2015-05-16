package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Tipooperacion;
import net.cinecable.model.base.BajaOrdenesObservacion;

@Local
public interface IBajaOrdenesObservacionDao extends IGenericDao<BajaOrdenesObservacion, Long> {

	List<BajaOrdenesObservacion> getParametroBajaOrdenesObservacion(Tipooperacion tipop);
}
