package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Tipocliente;
import util.HibernateUtil;
import dao.datos.TipoclienteDAO;

public class TipoclienteBO {

	TipoclienteDAO tipoclienteDAO;
	
	public TipoclienteBO() {
		tipoclienteDAO = new TipoclienteDAO();
	}
	
	public List<Tipocliente> lisTipocliente() throws Exception {
        List<Tipocliente> lisTipocliente = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisTipocliente = tipoclienteDAO.lisTipocliente(session);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisTipocliente;
	}
}
