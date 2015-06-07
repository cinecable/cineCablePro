package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Ctacliente;
import pojo.annotations.custom.ConsultaCliente;

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
				.createAlias("clientes", "cli")
				.createAlias("cli.estadocivil", "ec")
				.createAlias("cli.tipoidentidad", "ti")
				.createAlias("cli.tipocliente", "tc");
		
		ctacliente = (Ctacliente) criteria.uniqueResult();
		
		return ctacliente;
	}
	
	public Ctacliente getCtaclienteByNombre(Session session, String nombre) throws Exception {
		Ctacliente ctacliente;
		
		String hql = " from Ctacliente ";
		hql += " where nombre = :nombre ";
		
		Query query = session.createQuery(hql)
				.setString("nombre", nombre);
		
		ctacliente = (Ctacliente) query.uniqueResult();
		
		return ctacliente;
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsultaCliente> lisConsultaCliente(Session session, String nombre1, String nombre2, String apellido1, String apellido2, String numeroIdentificacion, String empresa, String vineta, String nrodebito, int pageSize, int pageNumber, int[] args  ) throws Exception {
        List<ConsultaCliente> lisConsultaCliente;

        String hql = " select new pojo.annotations.custom.ConsultaCliente(cli.nombre1, cli.nombre2, cli.apellido1, cli.apellido2, cli.empresa_1, cli.identificacion, cta.idcuenta, cli.idcliente, dto.nrodebito, dir.vineta ) ";
        hql += " from Clientes cli, Ctacliente cta, Debitobco dto, Direccion dir ";
        hql += " where cta.clientes.idcliente = cli.idcliente ";
        hql += " and dto.ctacliente.idcuenta = cta.idcuenta ";
        hql += " and dir.ctacliente.idcuenta = cta.idcuenta ";
        hql += " and dir.correspondencia = 'I' ";
        
        if(nombre1 != null && nombre1.trim().length() > 0){
            hql += " and lower(cli.nombre1) like " + "'%"+nombre1.toLowerCase().replaceAll(" ", "%")+"%' ";
        }
        
        if(nombre2 != null && nombre2.trim().length() > 0){
            hql += " and lower(cli.nombre2) like " + "'%"+nombre2.toLowerCase().replaceAll(" ", "%")+"%' ";
        }
        
        if(apellido1 != null && apellido1.trim().length() > 0){
            hql += " and lower(cli.apellido1) like " + "'%"+apellido1.toLowerCase().replaceAll(" ", "%")+"%' ";
        }
        
        if(apellido2 != null && apellido2.trim().length() > 0){
            hql += " and lower(cli.apellido2) like " + "'%"+apellido2.toLowerCase().replaceAll(" ", "%")+"%' ";
        }
        
        if(numeroIdentificacion != null && numeroIdentificacion.trim().length() > 0){
            hql += " and cli.identificacion like " + "'%"+numeroIdentificacion.replaceAll(" ", "%")+"%' ";
        }
        
        if(empresa != null && empresa.trim().length() > 0){
            hql += " and lower(cli.empresa_1) like " + "'%"+empresa.toLowerCase().replaceAll(" ", "%")+"%' ";
        }
        
        if(vineta != null && vineta.trim().length() > 0){
            hql += " and lower(dir.vineta) like " + "'%"+vineta.toLowerCase().replaceAll(" ", "%")+"%' ";
        }
        
        if(nrodebito != null && nrodebito.trim().length() > 0){
            hql += " and dto.nrodebito like " + "'%"+nrodebito.replaceAll(" ", "%")+"%' ";
        }
        
        hql += " order by cli.nombre1 asc ";
        
        Query query = session.createQuery(hql);
        
        query.setMaxResults(pageSize)
        .setFirstResult(pageNumber);
		
        lisConsultaCliente = (List<ConsultaCliente>) query.list();
        
        if(lisConsultaCliente != null && lisConsultaCliente.size() > 0)
        {
        	String hqlCount = " select count(cli.idcliente) ";
        	hqlCount += " from Clientes cli, Ctacliente cta, Debitobco dto, Direccion dir ";
        	hqlCount += " where cta.clientes.idcliente = cli.idcliente ";
        	hqlCount += " and dto.ctacliente.idcuenta = cta.idcuenta ";
        	hqlCount += " and dir.ctacliente.idcuenta = cta.idcuenta ";
        	hqlCount += " and dir.correspondencia = 'I' ";
            
            if(nombre1 != null && nombre1.trim().length() > 0){
            	hqlCount += " and lower(cli.nombre1) like " + "'%"+nombre1.toLowerCase().replaceAll(" ", "%")+"%' ";
            }
            
            if(nombre2 != null && nombre2.trim().length() > 0){
            	hqlCount += " and lower(cli.nombre2) like " + "'%"+nombre2.toLowerCase().replaceAll(" ", "%")+"%' ";
            }
            
            if(apellido1 != null && apellido1.trim().length() > 0){
            	hqlCount += " and lower(cli.apellido1) like " + "'%"+apellido1.toLowerCase().replaceAll(" ", "%")+"%' ";
            }
            
            if(apellido2 != null && apellido2.trim().length() > 0){
            	hqlCount += " and lower(cli.apellido2) like " + "'%"+apellido2.toLowerCase().replaceAll(" ", "%")+"%' ";
            }
            
            if(numeroIdentificacion != null && numeroIdentificacion.trim().length() > 0){
            	hqlCount += " and cli.identificacion like " + "'%"+numeroIdentificacion.replaceAll(" ", "%")+"%' ";
            }
            
            if(empresa != null && empresa.trim().length() > 0){
            	hqlCount += " and lower(cli.empresa_1) like " + "'%"+empresa.toLowerCase().replaceAll(" ", "%")+"%' ";
            }
            
            if(vineta != null && vineta.trim().length() > 0){
            	hqlCount += " and lower(dir.vineta) like " + "'%"+vineta.toLowerCase().replaceAll(" ", "%")+"%' ";
            }
            
            if(nrodebito != null && nrodebito.trim().length() > 0){
            	hqlCount += " and dto.nrodebito like " + "'%"+nrodebito.replaceAll(" ", "%")+"%' ";
            }
            
            Query queryCount = session.createQuery(hqlCount);
		
			Object object = queryCount.uniqueResult();
			int count = (object==null?0:Integer.parseInt(object.toString()));
			args[0] = count;
		}
		else
		{
			args[0] = 0;
		}

        return lisConsultaCliente;
	}
	
	public void actualizarCtacliente(Session session, Ctacliente ctacliente) throws Exception {
		session.update(ctacliente);
	}
	
	public void ingresarCtacliente(Session session, Ctacliente ctacliente) throws Exception {
		session.save(ctacliente);
	}
}
