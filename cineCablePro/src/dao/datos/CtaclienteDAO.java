package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Ctacliente;

public class CtaclienteDAO {

	public int maxIdcuenta(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idcuenta) as max from Ctacliente ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Ctacliente getCtaclienteById(Session session, int idcuenta) throws Exception {
		Ctacliente ctacliente;
		
		Criteria criteria = session.createCriteria(Ctacliente.class)
				.add( Restrictions.eq("idcuenta", idcuenta))
				.createAlias("clientes", "cli");
		
		ctacliente = (Ctacliente) criteria.uniqueResult();
		
		return ctacliente;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ctacliente> lisCtacliente(Session session, String nombre1, String nombre2, String apellido1, String apellido2, String numeroIdentificacion, String empresa, int pageSize, int pageNumber, int[] args  ) throws Exception {
        List<Ctacliente> lisCtacliente;

        Criteria criteria = session.createCriteria(Ctacliente.class)
                .createAlias("clientes", "cli");
        
        if(nombre1 != null && nombre1.trim().length() > 0){
            criteria.add( Restrictions.like("cli.nombre1", "%"+nombre1.replaceAll(" ", "%")+"%").ignoreCase());
        }
        
        if(nombre2 != null && nombre2.trim().length() > 0){
            criteria.add( Restrictions.like("cli.nombre2", "%"+nombre2.replaceAll(" ", "%")+"%").ignoreCase());
        }
        
        if(apellido1 != null && apellido1.trim().length() > 0){
            criteria.add( Restrictions.like("cli.apellido1", "%"+apellido1.replaceAll(" ", "%")+"%").ignoreCase());
        }
        
        if(apellido2 != null && apellido2.trim().length() > 0){
            criteria.add( Restrictions.like("cli.apellido2", "%"+apellido2.replaceAll(" ", "%")+"%").ignoreCase());
        }
        
        if(numeroIdentificacion != null && numeroIdentificacion.trim().length() > 0){
            criteria.add( Restrictions.like("cli.idcliente", "%"+numeroIdentificacion.replaceAll(" ", "%")+"%").ignoreCase());
        }
        
        if(empresa != null && empresa.trim().length() > 0){
            criteria.add( Restrictions.like("cli.empresa_1", "%"+empresa.replaceAll(" ", "%")+"%").ignoreCase());
        }
        
        criteria.addOrder(Order.asc("cli.nombre1"))
        .setMaxResults(pageSize)
        .setFirstResult(pageNumber);
        
        lisCtacliente = (List<Ctacliente>) criteria.list();
        
        if(lisCtacliente != null && lisCtacliente.size() > 0)
        {
			Criteria criteriaCount = session.createCriteria(Ctacliente.class)
	            .setProjection( Projections.rowCount())
	            .createAlias("clientes", "cli");
			
			if(nombre1 != null && nombre1.trim().length() > 0){
	            criteria.add( Restrictions.like("cli.nombre1", "%"+nombre1.replaceAll(" ", "%")+"%").ignoreCase());
	        }
	        
	        if(nombre2 != null && nombre2.trim().length() > 0){
	            criteria.add( Restrictions.like("cli.nombre2", "%"+nombre2.replaceAll(" ", "%")+"%").ignoreCase());
	        }
	        
	        if(apellido1 != null && apellido1.trim().length() > 0){
	            criteria.add( Restrictions.like("cli.apellido1", "%"+apellido1.replaceAll(" ", "%")+"%").ignoreCase());
	        }
	        
	        if(apellido2 != null && apellido2.trim().length() > 0){
	            criteria.add( Restrictions.like("cli.apellido2", "%"+apellido2.replaceAll(" ", "%")+"%").ignoreCase());
	        }
	        
	        if(numeroIdentificacion != null && numeroIdentificacion.trim().length() > 0){
	            criteria.add( Restrictions.like("cli.idcliente", "%"+numeroIdentificacion.replaceAll(" ", "%")+"%").ignoreCase());
	        }
	        
	        if(empresa != null && empresa.trim().length() > 0){
	            criteria.add( Restrictions.like("cli.empresa_1", "%"+empresa.replaceAll(" ", "%")+"%").ignoreCase());
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

        return lisCtacliente;
	}
	
	public void actualizarCtacliente(Session session, Ctacliente ctacliente) throws Exception {
		session.update(ctacliente);
	}
	
	public void ingresarCtacliente(Session session, Ctacliente ctacliente) throws Exception {
		session.save(ctacliente);
	}
}
