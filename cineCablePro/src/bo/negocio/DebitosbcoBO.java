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
	
	public Debitobco getDebitobcoByIdcuenta(int idcuenta) throws Exception {
		Debitobco debitobco = new Debitobco();
    	Session session = null;
    	
    	try{
            session = HibernateUtil.getSessionFactory().openSession();
            debitobco = debitosbcoDAO.getDebitobcoByIdcuenta(session, idcuenta);
        }catch(Exception he){
			throw new Exception(he);
        }finally{
			session.close();
        }

        return debitobco;
    }
	
	public boolean modificar(Debitobco debitobco) throws Exception {
    	boolean ok = false;
    	Session session = null;
    	
    	try{
    		DebitosbcoDAO debitosbcoDAO = new DebitosbcoDAO();
    		
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			//grabar
			debitosbcoDAO.updateDebitobco(session, debitobco);
			
			session.getTransaction().commit();
			ok = true;
    	}catch(Exception e){
    		session.getTransaction().rollback();
            throw new Exception(e);
        }finally{
        	session.close();
        }
    	
    	return ok;
    }
}
	