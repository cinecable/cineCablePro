package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Calleprincipal;
import util.HibernateUtil;
import dao.datos.CallePrincipalDAO;

public class CallePrincipalBO {
	private CallePrincipalDAO calleprincipalDAO;

    public CallePrincipalBO() {
    	calleprincipalDAO = new CallePrincipalDAO();
    }
    
    public List<Calleprincipal> ConsultarCPxSector(int idSector) throws Exception {
        List<Calleprincipal> lisCallePrincipal = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCallePrincipal = calleprincipalDAO.lisCallePxSector(session, idSector);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisCallePrincipal;
    }
    
    public List<Calleprincipal> ConsultaCallePxCalleP(int idCallePrincipal) throws Exception {
        List<Calleprincipal> lisCallePrincipal = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCallePrincipal = calleprincipalDAO.lisCallePxCalleP(session, idCallePrincipal);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisCallePrincipal;
    }
    
    
    public List<Calleprincipal> ConsultarCPxQuery(int idSector,String query) throws Exception {
        List<Calleprincipal> lisCallePrincipal = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCallePrincipal = calleprincipalDAO.lisCallePxQuery(session, idSector, query);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisCallePrincipal;
    }
    
}
