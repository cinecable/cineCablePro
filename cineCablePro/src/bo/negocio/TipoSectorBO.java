package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Tiposector;

import util.HibernateUtil;
import dao.datos.TipoSectorDAO;

public class TipoSectorBO {
	 private TipoSectorDAO tiposectorDAO;

	    public TipoSectorBO() {
	    	tiposectorDAO = new TipoSectorDAO();
	    }
	    
	    public List<Tiposector> ConsultarTsXTs(int idTipoSector) throws Exception {
	        List<Tiposector> lisTipoSector = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisTipoSector = tiposectorDAO.lisTipoSectorxTipoSector(session);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisTipoSector;
	    }
	    
	   
}
