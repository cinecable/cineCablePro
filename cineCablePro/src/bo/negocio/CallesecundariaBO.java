package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Callesecundaria;
import util.HibernateUtil;
import dao.datos.CalleSecundariaDAO;

public class CallesecundariaBO {
	 private CalleSecundariaDAO callesecundariaDAO;

	    public CallesecundariaBO() {
	    	callesecundariaDAO = new CalleSecundariaDAO();
	    }
	    
	    public List<Callesecundaria> ConsultarCalleSxSector(int idSector) throws Exception {
	        List<Callesecundaria> lisCalleSecundaria = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisCalleSecundaria = callesecundariaDAO.lisCalleSxSector(session, idSector);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisCalleSecundaria;
	    }
	    
	    public List<Callesecundaria> ConsultarCalleSxCalleS(int idCalleSecundaria) throws Exception {
	        List<Callesecundaria> lisCalleSecundaria = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisCalleSecundaria = callesecundariaDAO.lisCalleSxCalleS(session, idCalleSecundaria);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisCalleSecundaria;
	    }
	    
	    public List<Callesecundaria> ConsultarCSxQuery(int idSector,String query) throws Exception {
	        List<Callesecundaria> lisCalleSecundaria = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisCalleSecundaria = callesecundariaDAO.lisCalleSxQuery(session, idSector, query);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisCalleSecundaria;
	    }
}
