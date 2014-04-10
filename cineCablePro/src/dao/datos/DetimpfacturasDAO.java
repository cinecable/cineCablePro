package dao.datos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Detimpfacturas;

public class DetimpfacturasDAO {

	public int maxIddetfacturas(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(iddetfacturas) as max from Detimpfacturas").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	@SuppressWarnings("unchecked")
	public List<Detimpfacturas> lisDetimpfacturasByPage(Session session, String idfactura, int idempresa, int pageSize, int pageNumber, int[] args  ) throws Exception {
            List<Detimpfacturas> lisDetimpfacturas;
            
            String hql = " select d from Cabimpfacturas c, Detimpfacturas d ";
            hql += " where c.idfactura = :idfactura ";
            hql += " and d.idcabfacturas = c.idcabfactura ";
            hql += " and d.idempresa = :idempresa";
            hql += " order by d.orden asc ";
            
            Query query = session.createQuery(hql)
            		.setString("idfactura", idfactura)
            		.setInteger("idempresa", idempresa)
            		.setMaxResults(pageSize)
            		.setFirstResult(pageNumber);

            
            lisDetimpfacturas = (List<Detimpfacturas>) query.list();
            
            if(lisDetimpfacturas != null && lisDetimpfacturas.size() > 0)
            {
            	String hqlCount = " select count(d.iddetfacturas) from Cabimpfacturas c, Detimpfacturas d ";
            	hqlCount += " where c.idfactura = :idfactura ";
            	hqlCount += " and d.idempresa = :idempresa";
            	hqlCount += " and d.idcabfacturas = c.idcabfactura ";
                
                Query queryCount = session.createQuery(hqlCount)
                		.setString("idfactura", idfactura)
                		.setInteger("idempresa", idempresa)
                		.setMaxResults(pageSize)
                		.setFirstResult(pageNumber);
                
				Object object = queryCount.uniqueResult();
				int count = (object==null?0:Integer.parseInt(object.toString()));
				args[0] = count;
			}
			else
			{
				args[0] = 0;
			}

            return lisDetimpfacturas;
	}
	
	public void actualizarDetimpfacturas(Session session, Detimpfacturas detimpfacturas) throws Exception {
		session.update(detimpfacturas);
	}
	
	public void ingresarDetimpfacturas(Session session, Detimpfacturas detimpfacturas) throws Exception {
		session.save(detimpfacturas);
	}
}
