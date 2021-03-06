package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Pais;
import util.HibernateUtil;
import dao.datos.PaisDAO;

public class PaisBO {
	  private PaisDAO paisDAO;

	    public PaisBO() {
	        paisDAO = new PaisDAO();
	    }
	    
	    public Pais consultaPaisPorId(int idpais) throws Exception {
	        Pais pais = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            pais = paisDAO.paisById(session, idpais);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return pais;
	    }
	    
	    public List<Pais> consultarPaises() throws Exception {
	        List<Pais> lisPais = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisPais = paisDAO.lisPais(session);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisPais;
	    }

}
