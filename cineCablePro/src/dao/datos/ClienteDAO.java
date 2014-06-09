/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.datos;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Clientes;

/**
 *
 * @author lyance
 */
public class ClienteDAO {
	
	public int maxIdcliente(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idcliente) as max from Clientes ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Clientes getClientesById(Session session, String idcliente) throws Exception {
		Clientes clientes = null;
		
		Criteria criteria = session.createCriteria(Clientes.class)
				.add( Restrictions.eq("idcliente",idcliente));
		
		clientes = (Clientes) criteria.uniqueResult();
		
		return clientes;
	}
	
    @SuppressWarnings("unchecked")
	public List<Clientes> lisClientes(Session session, String nombre1, String Apellido1, String empresa, int pageSize, int pageNumber, int[] args  ) throws Exception {
            List<Clientes> lisClientes;

            Criteria criteria = session.createCriteria(Clientes.class)
                    .createAlias("empresa", "emp");
            
            if(nombre1 != null && nombre1.trim().length() > 0){
                criteria.add( Restrictions.like("nombre1", "%"+nombre1.replaceAll(" ", "%")+"%").ignoreCase());
            }
            
            if(Apellido1 != null && Apellido1.trim().length() > 0){
                criteria.add( Restrictions.like("apellido1", "%"+Apellido1.replaceAll(" ", "%")+"%").ignoreCase());
            }
            
            if(empresa != null && empresa.trim().length() > 0){
                criteria.add( Restrictions.eq("empresa_1", empresa));
            }
            
            criteria.addOrder(Order.asc("nombre1"))
            .setMaxResults(pageSize)
            .setFirstResult(pageNumber);
            
            lisClientes = (List<Clientes>) criteria.list();
            
            if(lisClientes != null && lisClientes.size() > 0)
            {
				Criteria criteriaCount = session.createCriteria(Clientes.class)
	                                .setProjection( Projections.rowCount())
	                                .createAlias("empresa", "emp");
				
				if(nombre1 != null && nombre1.trim().length() > 0){
                    criteria.add( Restrictions.like("nombre1", "%"+nombre1.replaceAll(" ", "%")+"%").ignoreCase());
                }

                if(Apellido1 != null && Apellido1.trim().length() > 0){
                    criteria.add( Restrictions.like("apellido1", "%"+Apellido1.replaceAll(" ", "%")+"%").ignoreCase());
                }

                if(empresa != null && empresa.trim().length() > 0){
                    criteria.add( Restrictions.eq("empresa_1", empresa));
                }
			
				criteriaCount.setMaxResults(pageSize)
				.setFirstResult(pageNumber);
				
				Object object = criteriaCount.uniqueResult();
				int count = (object==null?0:Integer.parseInt(object.toString()));
				args[0] = count;
			}
			else
			{
				args[0] = 0;
			}

            return lisClientes;
	}
    
    @SuppressWarnings("unchecked")
	public List<Clientes> lisClientesByPageNombres(Session session, String[] nombres, int pageSize, int pageNumber, int[] args) throws Exception {
		List<Clientes> lisClientes = null;
		
		Criteria criteria = session.createCriteria(Clientes.class);
		
		if(nombres != null && nombres.length > 0){
			String query = "(";
			for(int i=0;i<nombres.length;i++)
			{
				query += "(lower({alias}.apellido1) like lower('%"+nombres[i]+"%') or ";
				query += "lower({alias}.apellido2) like lower('%"+nombres[i]+"%') or ";
				query += "lower({alias}.nombre1) like lower('%"+nombres[i]+"%') or ";
				query += "lower({alias}.nombre2) like lower('%"+nombres[i]+"%')) ";
				if(i<nombres.length-1){
					query += "and ";
				}
			}
			query += ")";
			
			criteria.add(Restrictions.sqlRestriction(query));
		}
		
		criteria.addOrder(Order.asc("apellido1"))
		.addOrder(Order.asc("apellido2"))
		.addOrder(Order.asc("nombre1"))
		.addOrder(Order.asc("nombre2"))
		.setMaxResults(pageSize)
		.setFirstResult(pageNumber);
		
		lisClientes = (List<Clientes>) criteria.list();
		
		if(lisClientes != null && lisClientes.size() > 0){
			Criteria criteriaCount = session.createCriteria(Clientes.class)
			.setProjection( Projections.rowCount());
			
			if(nombres != null && nombres.length > 0){
				String query = "(";
				for(int i=0;i<nombres.length;i++)
				{
					query += "(lower({alias}.apellido1) like lower('%"+nombres[i]+"%') or ";
					query += "lower({alias}.apellido2) like lower('%"+nombres[i]+"%') or ";
					query += "lower({alias}.nombre1) like lower('%"+nombres[i]+"%') or ";
					query += "lower({alias}.nombre2) like lower('%"+nombres[i]+"%')) ";
					if(i<nombres.length-1){
						query += "and ";
					}
				}
				query += ")";
				
				criteriaCount.add(Restrictions.sqlRestriction(query));
			}
			
			Object object = criteriaCount.uniqueResult();
			int count = (object==null?0:Integer.parseInt(object.toString()));
			args[0] = count;
		} else {
			args[0] = 0;
		}
		
		return lisClientes;
	}
    
    public void ingresarClientes(Session session, Clientes clientes) throws Exception {
    	session.save(clientes);
    }
    
    public void actualizarClientes(Session session, Clientes clientes) throws Exception {
    	session.update(clientes);
    }
}
