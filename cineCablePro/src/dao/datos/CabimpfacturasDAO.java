package dao.datos;

import org.hibernate.Session;

import pojo.annotations.Cabimpfacturas;

public class CabimpfacturasDAO {

	public int maxIdcabfactura(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idcabfactura) as max from Cabimpfacturas").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public void actualizarCabimpfacturas(Session session, Cabimpfacturas cabimpfacturas) throws Exception {
		session.update(cabimpfacturas);
	}
	
	public void ingresarCabimpfacturas(Session session, Cabimpfacturas cabimpfacturas) throws Exception {
		session.save(cabimpfacturas);
	}
}
