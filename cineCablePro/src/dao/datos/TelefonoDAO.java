package dao.datos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Telefono;

public class TelefonoDAO {

	public int maxIdtelefono(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idtelefono) as max from Telefono ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
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
	
	public void saveTelefono(Session session, Telefono telefono) throws Exception {
		session.save(telefono);
	}
	
	public void updateTelefono(Session session, Telefono telefono) throws Exception {
		session.update(telefono);
	}
}
