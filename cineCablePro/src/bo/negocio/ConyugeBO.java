package bo.negocio;

import org.hibernate.Session;

import pojo.annotations.Conyuge;
import util.HibernateUtil;
import dao.datos.ConyugeDAO;

public class ConyugeBO {

	ConyugeDAO conyugeDAO;
	
	public ConyugeBO() {
		conyugeDAO = new ConyugeDAO();
	}
	
	public Conyuge getConyugeByIdcliente(String idcliente) throws Exception {
		Conyuge conyuge = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            conyuge = conyugeDAO.getConyugeByIdcliente(session, idcliente);
        }catch(Exception he){
			throw new Exception(he);
        }finally{
			session.close();
        }

        return conyuge;
	}
}
