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

	public boolean modificar(int idcuenta, Direccion direccionInstalacion, Direccion direccionCorrespondencia, Direccion direccionCobranza, Referenciadir referenciadirInstalacion, Referenciadir referenciadirCorrespondencia, Referenciadir referenciadirCobranza) throws Exception {
		boolean ok = false;
    	Session session = null;
    	
    	try{
    		DireccionDAO direccionDAO = new DireccionDAO();
    		ReferenciadirDAO referenciadirDAO = new ReferenciadirDAO();
    		
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			//direccion de instalacion
			//grabar
			direccionDAO.updateDireccion(session, direccionInstalacion);
			
			//referenciadir de instalacion
			//grabar
			referenciadirDAO.updateReferenciadir(session, referenciadirInstalacion);
			
			
			//direccion de correspondencia
			//grabar
			direccionDAO.updateDireccion(session, direccionCorrespondencia);
			
			//referenciadir de correspondencia
			//grabar
			referenciadirDAO.updateReferenciadir(session, referenciadirCorrespondencia);
			
			
			//direccion de cobranza
			//grabar
			direccionDAO.updateDireccion(session, direccionCobranza);
			
			//referenciadir de cobranza
			//grabar
			referenciadirDAO.updateReferenciadir(session, referenciadirCobranza);
			
    		session.getTransaction().commit();
			ok = true;
    	}catch(Exception e){
    		session.getTransaction().rollback();
            throw new Exception(e);
        }finally{
        	session.close();
        }
    	
    	return ok;
	}
}
