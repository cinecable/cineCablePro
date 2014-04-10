package dao.datos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Telefono;

public class TelefonoDAO {

	@SuppressWarnings("unchecked")
	public List<Telefono> lisTelefonoByIdcuenta(Session session, int idcuenta){
		List<Telefono> lisTelefono = null;
		
		String hql = " from Telefono ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and idestado = :idestado ";
		hql += " order by idtelefono ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setInteger("idestado", 1);
		
		lisTelefono = (List<Telefono>) query.list();
		
		return lisTelefono;
	}
}
