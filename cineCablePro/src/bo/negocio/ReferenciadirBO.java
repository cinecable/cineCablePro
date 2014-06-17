package bo.negocio;

import org.hibernate.Session;

import dao.datos.ReferenciadirDAO;

import pojo.annotations.Referenciadir;
import util.HibernateUtil;

public class ReferenciadirBO {

	ReferenciadirDAO referenciadirDAO;
	
	public ReferenciadirBO() {
		referenciadirDAO = new ReferenciadirDAO();
	}
	
	public Referenciadir getReferenciadirByIdDireccion(int iddireccion) throws Exception {
		Referenciadir referenciadir = null;
		Session session = null;
	
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            referenciadir = referenciadirDAO.getReferenciadirByIdDireccion(session, iddireccion);
        }catch(Exception he){
            throw new Exception(he);
        }finally{
    		session.close();
        }
		
		return referenciadir;
	}
}
