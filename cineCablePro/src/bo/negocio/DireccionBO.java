package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import dao.datos.DireccionDAO;
import dao.datos.ReferenciadirDAO;

import pojo.annotations.Direccion;
import pojo.annotations.Referenciadir;
import util.HibernateUtil;

public class DireccionBO {

	private DireccionDAO direccionDAO;
	
	public DireccionBO() {
		direccionDAO = new DireccionDAO();
	}
	
	public String consultarDireccionPorCuenta(int idcuenta) throws Exception {
		String direccion = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            List<Direccion> lisDireccion = direccionDAO.lisDireccionByIdcuenta(session, idcuenta);
            
            if(lisDireccion != null && lisDireccion.size() > 0){
            	direccion = lisDireccion.get(0).getCalleprincipal().getNombre() + " " + lisDireccion.get(0).getNumero() + " " + lisDireccion.get(0).getCallesecundaria().getNombre();
            }
        }
        catch(Exception ex){
            throw new Exception();
        }
        finally{
            session.close();
        }
        
        return direccion;
	}
	
	public Direccion consultarDireccionPorCuentaTipo(int idcuenta, String tipo) throws Exception {
		Direccion direccion = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            direccion = direccionDAO.direccionByIdcuentaTipo(session, idcuenta, tipo);
        }
        catch(Exception ex){
            throw new Exception();
        }
        finally{
            session.close();
        }
        
        return direccion;
	}
	
	public Direccion getDirByCta(int idcuenta) throws Exception {
		Direccion direccion = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            direccion = direccionDAO.dirxCuenta(session, idcuenta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return direccion;
	}

	public boolean modificar(int idcuenta, Direccion direccionInstalacion, Direccion direccionInstalacionClon, Direccion direccionCorrespondencia, Direccion direccionCorrespondenciaClon, Direccion direccionCobranza, Direccion direccionCobranzaClon, Referenciadir referenciadirInstalacion, Referenciadir referenciadirInstalacionClon, Referenciadir referenciadirCorrespondencia, Referenciadir referenciadirCorrespondenciaClon, Referenciadir referenciadirCobranza, Referenciadir referenciadirCobranzaClon) throws Exception {
		boolean ok = false;
    	Session session = null;
    	
    	try{
    		DireccionDAO direccionDAO = new DireccionDAO();
    		ReferenciadirDAO referenciadirDAO = new ReferenciadirDAO();
    		
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			boolean cambios = false;
			cambios = false;
			
			//direccion de instalacion
			//grabar
			if(!direccionInstalacion.equals(direccionInstalacionClon)){
				//calles
				if(direccionInstalacion.getCalleprincipal() != null && direccionInstalacion.getCalleprincipal().getIdcalleprincipal() == 0){
					direccionInstalacion.setCalleprincipal(null);
				}
				if(direccionInstalacion.getCallesecundaria() != null && direccionInstalacion.getCallesecundaria().getIdcallesecundaria() == 0){
					direccionInstalacion.setCallesecundaria(null);
				}
				//ubicacion
				if(direccionInstalacion.getUbicacion() != null && direccionInstalacion.getUbicacion().getIdubicacion() == 0){
					direccionInstalacion.setUbicacion(null);
				}
				//edificio
				if(direccionInstalacion.getEdificio() != null && direccionInstalacion.getEdificio().getIdedificio() == 0){
					direccionInstalacion.setEdificio(null);
				}
				
				direccionDAO.updateDireccion(session, direccionInstalacion);
				cambios = true;
			}
			
			//referenciadir de instalacion
			//grabar
			if(!referenciadirInstalacion.equals(referenciadirInstalacionClon)){
				//referencia
				if(referenciadirInstalacion != null && referenciadirInstalacion.getIdreferencia() == 0){
					referenciadirInstalacion = null;
				}
				
				referenciadirDAO.updateReferenciadir(session, referenciadirInstalacion);
				cambios = true;
			}
			
			//direccion de correspondencia
			//grabar
			if(!direccionCorrespondencia.equals(direccionCorrespondenciaClon)){
				//calles
				if(direccionCorrespondencia.getCalleprincipal() != null && direccionCorrespondencia.getCalleprincipal().getIdcalleprincipal() == 0){
					direccionCorrespondencia.setCalleprincipal(null);
				}
				if(direccionCorrespondencia.getCallesecundaria() != null && direccionCorrespondencia.getCallesecundaria().getIdcallesecundaria() == 0){
					direccionCorrespondencia.setCallesecundaria(null);
				}
				//ubicacion
				if(direccionCorrespondencia.getUbicacion() != null && direccionCorrespondencia.getUbicacion().getIdubicacion() == 0){
					direccionCorrespondencia.setUbicacion(null);
				}
				//edificio
				if(direccionCorrespondencia.getEdificio() != null && direccionCorrespondencia.getEdificio().getIdedificio() == 0){
					direccionCorrespondencia.setEdificio(null);
				}
				
				direccionDAO.updateDireccion(session, direccionCorrespondencia);
				cambios = true;
			}
			
			//referenciadir de correspondencia
			//grabar
			if(!referenciadirCorrespondencia.equals(referenciadirCorrespondenciaClon)){
				//referencia
				if(referenciadirCorrespondencia != null && referenciadirCorrespondencia.getIdreferencia() == 0){
					referenciadirCorrespondencia = null;
				}
				
				referenciadirDAO.updateReferenciadir(session, referenciadirCorrespondencia);
				cambios = true;
			}
			
			//direccion de cobranza
			//grabar
			if(!direccionCobranza.equals(direccionCobranzaClon)){
				//calles
				if(direccionCobranza.getCalleprincipal() != null && direccionCobranza.getCalleprincipal().getIdcalleprincipal() == 0){
					direccionCobranza.setCalleprincipal(null);
				}
				if(direccionCobranza.getCallesecundaria() != null && direccionCobranza.getCallesecundaria().getIdcallesecundaria() == 0){
					direccionCobranza.setCallesecundaria(null);
				}
				//ubicacion
				if(direccionCobranza.getUbicacion() != null && direccionCobranza.getUbicacion().getIdubicacion() == 0){
					direccionCobranza.setUbicacion(null);
				}
				//edificio
				if(direccionCobranza.getEdificio() != null && direccionCobranza.getEdificio().getIdedificio() == 0){
					direccionCobranza.setEdificio(null);
				}
				
				direccionDAO.updateDireccion(session, direccionCobranza);
				cambios = true;
			}
			
			//referenciadir de cobranza
			//grabar
			if(!referenciadirCobranza.equals(referenciadirCobranzaClon)){
				//referencia
				if(referenciadirCobranza != null && referenciadirCobranza.getIdreferencia() == 0){
					referenciadirCobranza = null;
				}
				
				referenciadirDAO.updateReferenciadir(session, referenciadirCobranza);
				cambios = true;
			}
			
			if(cambios){
	    		session.getTransaction().commit();
				ok = true;
			}
    	}catch(Exception e){
    		session.getTransaction().rollback();
            throw new Exception(e);
        }finally{
        	session.close();
        }
    	
    	return ok;
	}
}
