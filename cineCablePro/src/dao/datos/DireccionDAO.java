package dao.datos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Direccion;

public class DireccionDAO {

	@SuppressWarnings("unchecked")
	public List<Direccion> lisDireccionByIdcuenta(Session session, int idcuenta) throws Exception {
		List<Direccion> lisDireccion = null;
		
		String hql = " select d from Direccion as d inner join d.calleprincipal inner join d.callesecundaria ";
		hql += " where d.ctacliente.idcuenta = :idcuenta ";
		hql += " order by iddireccion ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta);
		
		lisDireccion = (List<Direccion>) query.list();
		
		return lisDireccion;
	}
	
	public Direccion dirxCuenta(Session session,int idcuenta) throws Exception {
		Direccion direccion = null;
		String corresp ="I";
		
		String hql = " from Direccion ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and idestado = :idestado ";
		hql += " and correspondencia = :correspondencia ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setInteger("idestado", 1)
				.setString("correspondencia", corresp);
		direccion = (Direccion) query.uniqueResult();
		
		return direccion;
	}
}
