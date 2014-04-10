package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Provincia;
import util.HibernateUtil;
import dao.datos.ProvinciaDAO;

public class ProvinciaBO {
	private ProvinciaDAO provinciaDAO;

    public ProvinciaBO() {
        provinciaDAO = new ProvinciaDAO();
    }
    
    public List<Provincia> consultarProvinciaPorPais(int idpais) throws Exception {
        List<Provincia> lisProvincia = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisProvincia = provinciaDAO.lisProvinciaByPais(session, idpais);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisProvincia;
    }
    
    
    public List<Provincia> consultarProvinciaPorProvincia(int idProvincia) throws Exception {
        List<Provincia> lisProvincia = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisProvincia = provinciaDAO.lisProvinciaByProvincia(session, idProvincia);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisProvincia;
    }
}
