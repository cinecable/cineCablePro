package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Nodos;
import util.HibernateUtil;
import dao.datos.NodosDAO;

public class NodosBO {
	private NodosDAO nodosDAO;

    public NodosBO() {
        nodosDAO = new NodosDAO();
    }
    
    public List<Nodos> ConsultaNodosxSector(int idSector) throws Exception {
        List<Nodos> lisNodos = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisNodos = nodosDAO.lisNodoBySector(session, idSector);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisNodos;
    }
    
    public List<Nodos> ConsultarNodoxNodo(int idNodos) throws Exception {
        List<Nodos> lisNodos = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisNodos = nodosDAO.lisNodoxNodo(session, idNodos);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisNodos;
    }
    
    
}
