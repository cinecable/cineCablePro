package bo.negocio;

import global.Parametro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.OrdenesAsignaciones;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Direccion;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Sector;

import dao.datos.ClienteDAO;
import dao.datos.CtaclienteDAO;
import dao.datos.DireccionDAO;
import dao.datos.ImpOrdenesDAO;
import dao.datos.OrdasignacionesDAO;
import dao.datos.TbordenesDAO;

import util.HibernateUtil;

public class ImpoOrdenesBO {
	
	 private ImpOrdenesDAO impOrdenesDAO;

	    public ImpoOrdenesBO() {
	    	impOrdenesDAO = new ImpOrdenesDAO();
	    }

	//public List<OrdenesAsignaciones> lisOrdenesAsignaciones(int pageSize, int pageNumber, int args[], String identificacion, Date fSolicitud, Date fDesde,Date fHasta, int idsector, int idtipooperacion, int idestado, int idtecnico,int idcuenta) throws RuntimeException {
	    public List<OrdenesAsignaciones> lisOrdenesAsignaciones(int idtipooperacion, int idtecnico, int idsector, String identificacion, Date fDesde, Date fHasta, int pageSize, int pageNumber, int[] args) throws RuntimeException {
        List<OrdenesAsignaciones> lisOrdenesAsignaciones = null;
        Session session = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
           
            lisOrdenesAsignaciones = impOrdenesDAO.lisOrdenesAsignacionesConsulta(session, idtipooperacion, idtecnico, idsector, identificacion, fDesde, fHasta, pageSize, pageNumber, args  );
            
            //llenar objetos nulos
            for(OrdenesAsignaciones ordenesAsignaciones : lisOrdenesAsignaciones){
            	if(!Hibernate.isInitialized(ordenesAsignaciones.getOrden())){
            		Hibernate.initialize(ordenesAsignaciones.getOrden());
            	}
            	if(ordenesAsignaciones.getOrden() != null){
            		if(!Hibernate.isInitialized(ordenesAsignaciones.getOrden().getEstado())){
            			Hibernate.initialize(ordenesAsignaciones.getOrden().getEstado());
            			if(ordenesAsignaciones.getOrden().getEstado() == null){
            				ordenesAsignaciones.getOrden().setEstado(new Estado());
            			}
            		}
	            	if(!Hibernate.isInitialized(ordenesAsignaciones.getOrden().getCuentaCliente())){
	        			Hibernate.initialize(ordenesAsignaciones.getOrden().getCuentaCliente());
	        		}
	            	if(ordenesAsignaciones.getOrden().getCuentaCliente() != null){
	            		if(!Hibernate.isInitialized(ordenesAsignaciones.getOrden().getCuentaCliente().getClientes())){
	            			Hibernate.initialize(ordenesAsignaciones.getOrden().getCuentaCliente().getClientes());
	            		}
	            		if(ordenesAsignaciones.getOrden().getCuentaCliente().getClientes() == null){
	            			ordenesAsignaciones.getOrden().getCuentaCliente().setClientes(new Clientes());
	            		}
	            	}else{
	            		ordenesAsignaciones.getOrden().setCuentaCliente(new Ctacliente());
	            		ordenesAsignaciones.getOrden().getCuentaCliente().setClientes(new Clientes());
	            	}
            	}else{
            		ordenesAsignaciones.setOrden(new Ordenes());
            		ordenesAsignaciones.getOrden().setEstado(new Estado());
            		ordenesAsignaciones.getOrden().setCuentaCliente(new Ctacliente());
            		ordenesAsignaciones.getOrden().getCuentaCliente().setClientes(new Clientes());
            	}
            	
            	//llenar objeto direccion
            	Direccion direccion = new Direccion();
            	if(ordenesAsignaciones.getOrden() != null && ordenesAsignaciones.getOrden().getCuentaCliente() != null && ordenesAsignaciones.getOrden().getCuentaCliente().getIdcuenta() > 0){
            		direccion = new DireccionDAO().dirxCuentaxSector(session, ordenesAsignaciones.getOrden().getCuentaCliente().getIdcuenta(), idsector);
            	}
            	if(direccion == null){
        			direccion = new Direccion();
        			direccion.setSector(new Sector());
        		}else{
        			if(direccion.getSector() == null){
        				direccion.setSector(new Sector());
        			}
        		}
        		ordenesAsignaciones.getOrden().getCuentaCliente().setDireccion(direccion);
            }
        }catch(Exception he){
                throw new RuntimeException(he);
        }finally{
                session.close();
        }

        return lisOrdenesAsignaciones;
    }
    
	public boolean actualizarEstadoOrdenes(List<OrdenesAsignaciones> lisOrdenesAsignaciones) throws Exception {
		Session session = null;
		boolean ok = false;
		
		//ordasignaciones.idestado
		//tbordenes.idestado

        try{
        	session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
            TbordenesDAO tbordenesDAO = new TbordenesDAO();
            OrdasignacionesDAO ordasignacionesDAO = new OrdasignacionesDAO();
            
            //recorrer la lista
            for(OrdenesAsignaciones ordenesAsignaciones:lisOrdenesAsignaciones){
            	if(ordenesAsignaciones.getOrden() != null && ordenesAsignaciones.getOrden().getCuentaCliente() != null && ordenesAsignaciones.getOrden().getCuentaCliente().getIdcuenta() == 0){
            		ordenesAsignaciones.getOrden().setCuentaCliente(null);
            	}
            	//si ya esta cambiado no actualizar
            	if(ordenesAsignaciones.getEstado() != Parametro.ESTADO_ORDEN_IMPRESA){
	            	//grabamos asignaciones
	            	ordenesAsignaciones.setEstado(Parametro.ESTADO_ORDEN_IMPRESA);
		            ordasignacionesDAO.actualizarOrdenesAsignaciones(session, ordenesAsignaciones);
	            	ok = true;
		            
		            //grabamos orden
		            if(ordenesAsignaciones.getOrden() != null && ordenesAsignaciones.getOrden().getIdOrdenes() > 0 && ordenesAsignaciones.getOrden().getEstado().getIdestado() != Parametro.ESTADO_ORDEN_IMPRESA){
		            	Estado estado = new Estado();
		    			estado.setIdestado(Parametro.ESTADO_ORDEN_IMPRESA);
			            ordenesAsignaciones.getOrden().setEstado(estado);
			            //ordenesAsignaciones.getOrden().setNoCopias(ordenesAsignaciones.getOrden().getNoCopias()++);
			            tbordenesDAO.actualizarOrdenes(session, ordenesAsignaciones.getOrden());
		            }
            	}
            }
            
            if(ok){
            	session.getTransaction().commit();
            }
            ok = true;
        }catch(Exception e){
        	ok = false;
        	session.getTransaction().rollback();
            throw new Exception(e);
        }finally{
            session.close();
        }
        
        return ok;
	}


}
