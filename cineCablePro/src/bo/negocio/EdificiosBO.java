package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Edificio;
import util.HibernateUtil;
import dao.datos.EdificiosDAO;

public class EdificiosBO {
	private EdificiosDAO edificiosDAO;

    public EdificiosBO() {
        edificiosDAO = new EdificiosDAO();
    }
    
    public List<Edificio> EdificioxSector(int idSector) throws Exception {
        List<Edificio> lisEdificio = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisEdificio = edificiosDAO.EdificioxSector(session, idSector);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisEdificio;
    }
    
    
    public List<Edificio> EdificioxEdificio(int idEdificio) throws Exception {
        List<Edificio> lisEdificio = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisEdificio = edificiosDAO.EdificioxEdificio(session, idEdificio);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisEdificio;
    }
}
