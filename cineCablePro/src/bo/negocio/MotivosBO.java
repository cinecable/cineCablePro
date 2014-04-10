package bo.negocio;

import org.hibernate.Session;

import pojo.annotations.Motivos;
import util.HibernateUtil;
import dao.datos.MotivosDAO;

public class MotivosBO {

	MotivosDAO motivosDAO;
	
	public MotivosBO() {
		motivosDAO = new MotivosDAO();
	}
	
	public Motivos getMotivosById(int idmotivo) throws Exception {
		Motivos motivos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            motivos = motivosDAO.getMotivosById(session, idmotivo);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return motivos;
	}
}
