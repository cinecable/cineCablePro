package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Ubicacion;

import util.HibernateUtil;
import dao.datos.UbicacionDAO;

public class UbicacionBO {
	 private UbicacionDAO ubicacionDAO;

	    public UbicacionBO() {
	        ubicacionDAO = new UbicacionDAO();
	    }
	    
	    public Ubicacion ubicacionPorId(int idubicacion) throws Exception {
	        Ubicacion ubicacion = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            ubicacion = ubicacionDAO.ubicacionPorId(session, idubicacion);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return ubicacion;
	    }
	    
	    public List<Ubicacion> ConsultarUbicacionxSector(int idSector) throws Exception {
	        List<Ubicacion> lisUbicacion = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisUbicacion = ubicacionDAO.lisUbicaccionxSector(session, idSector);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisUbicacion;
	    }
	    
	    public List<Ubicacion> ConsultarUbicacionxUbicacion(int idUbicacion) throws Exception {
	        List<Ubicacion> lisUbicacion = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisUbicacion = ubicacionDAO.liUbicacionxUbicacion(session, idUbicacion);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisUbicacion;
	    }
}
