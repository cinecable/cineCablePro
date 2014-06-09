package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Ciudad;
import util.HibernateUtil;
import dao.datos.CiudadDAO;

public class CiudadBO {
	 private CiudadDAO ciudadDAO;

	    public CiudadBO() {
	        ciudadDAO = new CiudadDAO();
	    }
	    
	    public Ciudad ciudadById(int idCiudad) throws Exception {
	        Ciudad ciudad = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            ciudad = ciudadDAO.ciudadById(session, idCiudad);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return ciudad;
	    }
	    
	    public List<Ciudad> consultarCiudadPorProvincia(int idprovincia) throws Exception {
	        List<Ciudad> lisCiudad = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisCiudad = ciudadDAO.lisCiudadByProvincia(session, idprovincia);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisCiudad;
	    }
	    
	    public List<Ciudad> consultarCiudadXCiudad(int idCiudad) throws Exception {
	        List<Ciudad> lisCiudad = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisCiudad = ciudadDAO.lisCiudadByCiudad(session, idCiudad);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisCiudad;
	    }
	    
	    
}
