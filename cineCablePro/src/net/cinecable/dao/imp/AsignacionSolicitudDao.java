package net.cinecable.dao.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;


import net.cinecable.dao.IAsignacionSolucitudDao;
import net.cinecable.model.base.ParamAsignacionOrden;

@Stateless
public class AsignacionSolicitudDao extends GenericDao<ParamAsignacionOrden, Long> implements IAsignacionSolucitudDao{

	public AsignacionSolicitudDao() {
		super(ParamAsignacionOrden.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ParamAsignacionOrden ValidaDias(int tipOperacion, Date fecha) {
		StringBuilder sql= new StringBuilder();
		sql.append("from ParamAsignacionOrden o ");
		sql.append("where o.tipoOperacion.idtipooperacion= :tipOp ");
		sql.append("and o.fechaasignacion= :fec");
		Query query=em.createQuery(sql.toString());
		query.setParameter("tipOp", tipOperacion);
		query.setParameter("fec", fecha);
		ParamAsignacionOrden datos=(ParamAsignacionOrden) query.uniqueResult();
		return datos;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ParamAsignacionOrden> AsigXfechas(int tipOperacion, Date fecha1,Date fecha2) {
		StringBuilder sql= new StringBuilder();
		sql.append("from ParamAsignacionOrden o ");
		sql.append("where o.tipoOperacion.idtipooperacion= :tipOp ");
		sql.append("and o.fechaasignacion>= :fecha1 ");
		sql.append("and o.fechaasignacion<= :fecha2 order by o.fechaasignacion");
		Query query=em.createQuery(sql.toString());
		query.setParameter("tipOp", tipOperacion);
		query.setParameter("fecha1", fecha1);
		query.setParameter("fecha2", fecha2);
		List<ParamAsignacionOrden> lisParamAsignacionOrden = query.list();
		return lisParamAsignacionOrden;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<String> DiasxMes(int tipOperacion,String mes) {
		List<String> meses= new ArrayList<String>();
		StringBuilder sql= new StringBuilder();
		sql.append("from ParamAsignacionOrden o ");
		sql.append("where o.tipoOperacion.idtipooperacion= :tipOp order by o.fechaasignacion");
		Query query=em.createQuery(sql.toString());
		query.setParameter("tipOp", tipOperacion);
		List<ParamAsignacionOrden> datos=query.list();
		for (int i=0;i<datos.size();i++){
			if (new SimpleDateFormat("MM").format(datos.get(i).getFechaasignacion()).equals(mes)){
				meses.add(new SimpleDateFormat("dd").format(datos.get(i).getFechaasignacion()));
			}
		}
		return meses;
	}
}
