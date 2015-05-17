package dao.datos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.OrdenesAsignaciones;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Ctacliente;

public class ImpOrdenesDAO {

	
	public Ctacliente getCtaclienteById(Session session, int idtipooperacion) throws Exception {
		Ctacliente ctacliente;
		
		Criteria criteria = session.createCriteria(Ordenes.class)
				//.add( Restrictions.eq("idOrdenes", idcuenta))
				.createAlias("tipoOperacion", "top")
				.createAlias("cuentaCliente", "cta")
				.createAlias("cta.clientes", "clte")
				.createAlias("producto", "pr")
				.createAlias("pr.producto", "pro")
				
				.add( Restrictions.eq("top.idtipooperacion",idtipooperacion));
		
		ctacliente = (Ctacliente) criteria.uniqueResult();
		
		return ctacliente;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdenesAsignaciones> lisOrdenesAsignaciones(Session session, String identificacion, Date fSolicitud, Date fDesde, Date fHasta,int idcuenta, int idtipooperacion, int idestado, int idtecnico, int pageSize, int pageNumber, int[] args  ) throws Exception {
        List<OrdenesAsignaciones> lisOrdenesAsignaciones;
        Calendar fSolicitudTmp = Calendar.getInstance();
        Calendar fDesdeTmp= Calendar.getInstance();
        Calendar fHastaTmp= Calendar.getInstance();
        
        Criteria criteria = session.createCriteria(OrdenesAsignaciones.class)
        		 .createAlias("orden", "ord")
        		 .createAlias("tecnico", "tec")
        		 .createAlias("ord.cuentaCliente", "cta")
        		 .createAlias("cta.clientes", "clte")
                 .createAlias("ord.tipoOperacion", "tpo");
                
                
        if(fSolicitud != null){
        	fSolicitudTmp.setTime(fSolicitud);
        	fSolicitudTmp.set(Calendar.HOUR_OF_DAY, 0);
        	fSolicitudTmp.set(Calendar.MINUTE, 0);
        	fSolicitudTmp.set(Calendar.SECOND, 0);
    		
        	criteria.add( Restrictions.eq("ord.fechaEjecucion",fSolicitudTmp.getTime()));
        }
        if(fDesde != null){
        	fDesdeTmp.setTime(fDesde);
        	fDesdeTmp.set(Calendar.HOUR_OF_DAY, 0);
        	fDesdeTmp.set(Calendar.MINUTE, 0);
        	fDesdeTmp.set(Calendar.SECOND, 0);
    		
        	criteria.add( Restrictions.ge("ord.fechaEjecucion", fDesdeTmp.getTime()));
        }
       
        if(fHasta != null){
        	fHastaTmp.setTime(fHasta);
        	fHastaTmp.set(Calendar.HOUR_OF_DAY, 0);
        	fHastaTmp.set(Calendar.MINUTE, 0);
        	fHastaTmp.set(Calendar.SECOND, 0);
    		
        	criteria.add( Restrictions.le("ord.fechaEjecucion",  fHastaTmp.getTime()));
        
        }
        if(idtipooperacion > 0){
        	criteria.add( Restrictions.eq("tpo.idtipooperacion",idtipooperacion));
        }
        
        if(identificacion != null && identificacion.length() > 0){
        	criteria.add( Restrictions.eq("clte.identificacion",identificacion));
        }
        
        if(idtecnico > 0){
        	criteria.add( Restrictions.eq("tec.idpersona",idtecnico));
        }
        
        if(idcuenta > 0){
        	criteria.add( Restrictions.eq("cta.idcuenta",idcuenta));
        }
        
        criteria.addOrder(Order.asc("ord.fechaEjecucion"))
        .setMaxResults(pageSize)
        .setFirstResult(pageNumber);
        
        lisOrdenesAsignaciones = (List<OrdenesAsignaciones>) criteria.list();
        
        if(lisOrdenesAsignaciones != null && lisOrdenesAsignaciones.size() > 0)
        {
			Criteria criteriaCount = session.createCriteria(OrdenesAsignaciones.class)
					 .setProjection( Projections.rowCount())
	        		 .createAlias("orden", "ord")
	        		 .createAlias("tecnico", "tec")
	        		 .createAlias("ord.cuentaCliente", "cta")
	        		 .createAlias("cta.clientes", "clte")
	                 .createAlias("ord.tipoOperacion", "tpo");
	        
			if(fSolicitud != null){
				criteriaCount.add( Restrictions.eq("ord.fechaEjecucion",fSolicitudTmp));
	        }
	        
	        if(idtipooperacion > 0){
	        	criteriaCount.add( Restrictions.eq("tpo.idtipooperacion",idtipooperacion));
	        }
	        
	        if(identificacion != null && identificacion.length() > 0){
	        	criteriaCount.add( Restrictions.eq("clte.identificacion",identificacion));
	        }
	        
	        if(idtecnico > 0){
	        	criteriaCount.add( Restrictions.eq("tec.idpersona",idtecnico));
	        }
			
	        if(idcuenta > 0){
	        	criteriaCount.add( Restrictions.eq("cta.idcuenta",idcuenta));
	        }
	        
			Object object = criteriaCount.uniqueResult();
			int count = (object==null?0:Integer.parseInt(object.toString()));
			args[0] = count;
		}
		else
		{
			args[0] = 0;
		}

        return lisOrdenesAsignaciones;
	}
	
	@SuppressWarnings("unchecked")
	//public List<OrdenesAsignaciones> lisOrdenesAsignacionesConsulta(Session session, String identificacion, Date fSolicitud, Date fDesde, Date fHasta,int idcuenta, int idtipooperacion, int idestado, int idtecnico, int pageSize, int pageNumber, int[] args  ) throws Exception {
	public List<OrdenesAsignaciones> lisOrdenesAsignacionesConsulta(Session session, int idtipooperacion, int idtecnico, int idsector, String identificacion, Date fDesde, Date fHasta, int pageSize, int pageNumber, int[] args  ) throws Exception {
        List<OrdenesAsignaciones> lisOrdenesAsignaciones;
        Calendar fDesdeTmp= Calendar.getInstance();
        Calendar fHastaTmp= Calendar.getInstance();
        
		/*String sql = " select {oas.*} ";
		sql += " from tbordenes tbo ";
		sql += " inner join tipooperacion top "; 
		sql += " on tbo.idtipooperacion = top.idtipooperacion "; 
		sql += " inner join estado est "; 
		sql += " on tbo.idestado = est.idestado ";
		sql += " inner join ordasignaciones oas ";
		sql += " on tbo.idordenes = oas.idordenes ";*/
		//sql += " where idcuenta = " + idcuenta;
        
		String sql = " select {oas.*} ";
		sql += " from tbordenes tbo ";
		sql += " inner join tipooperacion top ";
		sql += " on tbo.idtipooperacion = top.idtipooperacion ";
		sql += " inner join estado est ";
		sql += " on tbo.idestado = est.idestado ";
		sql += " inner join ordasignaciones oas ";
		sql += " on tbo.idordenes = oas.idordenes ";
		sql += " left outer join motivos mot ";
		sql += " on mot.idmotivo = tbo.idmotivo ";
		sql += " left outer join persona pers ";
		sql += " on oas.idpersonat = pers.idpersona ";
		sql += " left outer join ctacliente cta ";
		sql += " on tbo.idcuenta = cta.idcuenta ";
		sql += " left outer join clientes cli ";
		sql += " on cli.idcliente = cta.idcliente ";
		sql += " left outer join direccion dir ";
		sql += " on dir.idcuenta = cta.idcuenta ";
		sql += " and dir.idestado = 1 ";
		sql += " and dir.correspondencia ='I' ";
		sql += " left outer join sector sec ";
		sql += " on sec.idsector = dir.idsector ";
		sql += " left outer join nodos nod ";
		sql += " on nod.idnodo = dir.idnodo ";
		sql += " left outer join referenciadir rdir ";
		sql += " on rdir.iddireccion = dir.iddireccion ";
		sql += " left outer join tipooperacion tope ";
		sql += " on tope.idtipooperacion = tbo.idtipooperacion ";
		sql += " where 1 = 1 ";
		
		if(idtipooperacion > 0){
			sql += " and coalesce(tbo.idtipooperacion,0) = coalesce(coalesce(:idtipooperacion, tbo.idtipooperacion), 0) ";
        }
		if(idtecnico > 0){
			sql += " and coalesce(oas.idpersonat,0) = coalesce(coalesce(:idtecnico,oas.idpersonat),0) ";
		}
		if(fDesde != null){
        	sql += " and coalesce(tbo.fechaejecucion,'01-01-9000') >= coalesce(coalesce(:fdesde,tbo.fechaejecucion),'01-01-9000') ";
		}
		if(fHasta != null){
        	sql += " and coalesce(tbo.fechaejecucion,'01-01-9000') <= coalesce(coalesce(:fhasta,tbo.fechaejecucion),'01-01-9000') ";
		}
		if(idsector > 0){
			sql += " and coalesce(sec.idsector,0) = coalesce(coalesce(:idsector, sec.idsector),0) ";
        }
		if(identificacion != null && identificacion.trim().length() > 0){
			sql += " and coalesce(cli.identificacion,'x') = coalesce(coalesce(:identificacion, cli.identificacion),'x') ";
        }
		
		sql += " limit " + pageSize + " offset " + pageNumber;
        
		SQLQuery sqlquery = session.createSQLQuery(sql)
				.addEntity("oas", OrdenesAsignaciones.class);
		
		if(idtipooperacion > 0){
			sqlquery.setInteger("idtipooperacion", idtipooperacion);
        }
		if(idtecnico > 0){
			sqlquery.setInteger("idtecnico", idtecnico);
		}
		if(fDesde != null){
			fDesdeTmp.setTime(fDesde);
        	fDesdeTmp.set(Calendar.HOUR_OF_DAY, 0);
        	fDesdeTmp.set(Calendar.MINUTE, 0);
        	fDesdeTmp.set(Calendar.SECOND, 0);
			sqlquery.setDate("fdesde", fDesdeTmp.getTime());
		}
		if(fHasta != null){
			fHastaTmp.setTime(fHasta);
        	fHastaTmp.set(Calendar.HOUR_OF_DAY, 0);
        	fHastaTmp.set(Calendar.MINUTE, 0);
        	fHastaTmp.set(Calendar.SECOND, 0);
			sqlquery.setDate("fhasta", fHastaTmp.getTime());
		}
		if(idsector > 0){
			sqlquery.setInteger("idsector", idsector);
        }
		if(identificacion != null && identificacion.trim().length() > 0){
			sqlquery.setString("identificacion", identificacion);
        }
		
        lisOrdenesAsignaciones = (List<OrdenesAsignaciones>) sqlquery.list();
        
        if(lisOrdenesAsignaciones != null && lisOrdenesAsignaciones.size() > 0)
        {
        	/*String sqlCount = " select count(*) ";
    		sqlCount += " from tbordenes tbo ";
    		sqlCount += " inner join tipooperacion top "; 
    		sqlCount += " on tbo.idtipooperacion = top.idtipooperacion "; 
    		sqlCount += " inner join estado est "; 
    		sqlCount += " on tbo.idestado = est.idestado ";
    		sqlCount += " inner join ordasignaciones oas ";
    		sqlCount += " on tbo.idordenes = oas.idordenes ";*/
    		//sqlCount += " where idcuenta = " + idcuenta; 
        	
        	String sqlCount = " select count(*) ";
        	sqlCount += " from tbordenes tbo ";
        	sqlCount += " inner join tipooperacion top ";
        	sqlCount += " on tbo.idtipooperacion = top.idtipooperacion ";
        	sqlCount += " inner join estado est ";
        	sqlCount += " on tbo.idestado = est.idestado ";
        	sqlCount += " inner join ordasignaciones oas ";
        	sqlCount += " on tbo.idordenes = oas.idordenes ";
        	sqlCount += " left outer join motivos mot ";
        	sqlCount += " on mot.idmotivo = tbo.idmotivo ";
        	sqlCount += " left outer join persona pers ";
        	sqlCount += " on oas.idpersonat = pers.idpersona ";
        	sqlCount += " left outer join ctacliente cta ";
        	sqlCount += " on tbo.idcuenta = cta.idcuenta ";
        	sqlCount += " left outer join clientes cli ";
        	sqlCount += " on cli.idcliente = cta.idcliente ";
        	sqlCount += " left outer join direccion dir ";
        	sqlCount += " on dir.idcuenta = cta.idcuenta ";
        	sqlCount += " and dir.idestado = 1 ";
        	sqlCount += " and dir.correspondencia ='I' ";
        	sqlCount += " left outer join sector sec ";
        	sqlCount += " on sec.idsector = dir.idsector ";
        	sqlCount += " left outer join nodos nod ";
        	sqlCount += " on nod.idnodo = dir.idnodo ";
        	sqlCount += " left outer join referenciadir rdir ";
        	sqlCount += " on rdir.iddireccion = dir.iddireccion ";
        	sqlCount += " left outer join tipooperacion tope ";
        	sqlCount += " on tope.idtipooperacion = tbo.idtipooperacion ";
        	sqlCount += " where 1 = 1 ";
    		
    		if(idtipooperacion > 0){
    			sqlCount += " and coalesce(tbo.idtipooperacion,0) = coalesce(coalesce(:idtipooperacion, tbo.idtipooperacion), 0) ";
            }
    		if(idtecnico > 0){
    			sqlCount += " and coalesce(oas.idpersonat,0) = coalesce(coalesce(:idtecnico,oas.idpersonat),0) ";
    		}
    		if(fDesde != null){
    			sqlCount += " and coalesce(tbo.fechaejecucion,'01-01-9000') >= coalesce(coalesce(:fdesde,tbo.fechaejecucion),'01-01-9000') ";
    		}
    		if(fHasta != null){
    			sqlCount += " and coalesce(tbo.fechaejecucion,'01-01-9000') <= coalesce(coalesce(:fhasta,tbo.fechaejecucion),'01-01-9000') ";
    		}
    		if(idsector > 0){
    			sqlCount += " and coalesce(sec.idsector,0) = coalesce(coalesce(:idsector, sec.idsector),0) ";
            }
    		if(identificacion != null && identificacion.trim().length() > 0){
    			sqlCount += " and coalesce(cli.identificacion,'x') = coalesce(coalesce(:identificacion, cli.identificacion),'x') ";
            }
    		
    		SQLQuery sqlqueryCount = session.createSQLQuery(sqlCount);
    			
    		if(idtipooperacion > 0){
    			sqlqueryCount.setInteger("idtipooperacion", idtipooperacion);
            }
    		if(idtecnico > 0){
    			sqlqueryCount.setInteger("idtecnico", idtecnico);
    		}
    		if(fDesde != null){
    			fDesdeTmp.setTime(fDesde);
            	fDesdeTmp.set(Calendar.HOUR_OF_DAY, 0);
            	fDesdeTmp.set(Calendar.MINUTE, 0);
            	fDesdeTmp.set(Calendar.SECOND, 0);
            	sqlqueryCount.setDate("fdesde", fDesdeTmp.getTime());
    		}
    		if(fHasta != null){
    			fHastaTmp.setTime(fHasta);
            	fHastaTmp.set(Calendar.HOUR_OF_DAY, 0);
            	fHastaTmp.set(Calendar.MINUTE, 0);
            	fHastaTmp.set(Calendar.SECOND, 0);
            	sqlqueryCount.setDate("fhasta", fHastaTmp.getTime());
    		}
    		if(idsector > 0){
    			sqlqueryCount.setInteger("idsector", idsector);
            }
    		if(identificacion != null && identificacion.trim().length() > 0){
    			sqlqueryCount.setString("identificacion", identificacion);
            }
    		
			Object object = sqlqueryCount.uniqueResult();
			int count = (object==null?0:Integer.parseInt(object.toString()));
			args[0] = count;
		}
		else
		{
			args[0] = 0;
		}

        /*for(OrdenesAsignaciones ordenesAsignaciones : lisOrdenesAsignaciones){
        	ordenesAsignaciones.getOrden().getIdOrdenes();
        	if(ordenesAsignaciones.getOrden() == null){
        		ordenesAsignaciones.setOrden(new Ordenes());
        		ordenesAsignaciones.getOrden().setEstado(new Estado());
        	}else{
        		if(ordenesAsignaciones.getOrden().getEstado() == null) {
        			ordenesAsignaciones.getOrden().setEstado(new Estado());
        		}
        	}
        }*/
        
        return lisOrdenesAsignaciones;
	}
}
