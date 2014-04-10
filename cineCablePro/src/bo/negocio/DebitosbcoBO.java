package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Debitobco;
import util.HibernateUtil;

import dao.datos.DebitosbcoDAO;

public class DebitosbcoBO {
	private DebitosbcoDAO debitosbcoDAO;
	
	public DebitosbcoBO() {
		debitosbcoDAO = new DebitosbcoDAO();		
	}
	
	public List<Debitobco> DebitosbcoxTipodebito(int idTipodebito) throws Exception {
		List<Debitobco> listDebitobco =null;
		Session session=null;
		
		 try{
		   session = HibernateUtil.getSessionFactory().openSession();
		   listDebitobco=debitosbcoDAO.DebitosbcoxTipoDebito(session, idTipodebito);		   
		 }
	    catch(Exception ex){
	        throw new Exception(ex);
	    }
	    finally{
	        session.close();
	    }
		   return listDebitobco;
	}
}
	