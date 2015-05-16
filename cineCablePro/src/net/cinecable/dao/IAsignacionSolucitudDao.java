package net.cinecable.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import net.cinecable.model.base.ParamAsignacionOrden;

@Local
public interface IAsignacionSolucitudDao extends IGenericDao<ParamAsignacionOrden, Long>{
	ParamAsignacionOrden ValidaDias(int tipOperacion, Date fecha);
	List<String> DiasxMes(int tipOperacion,String mes);
	List<ParamAsignacionOrden> AsigXfechas(int tipOperacion, Date fecha1,
			Date fecha2);
}
