package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Tipodebito;

import util.HibernateUtil;
import dao.datos.TiposDebitoDAO;

public class TiposDebitoBO {
private TiposDebitoDAO tiposDebitoDAO;
	
	public TiposDebitoBO() {
		tiposDebitoDAO = new TiposDebitoDAO();		
	}
	public List<Tipodebito> Tipodebitos() throws Exception {
		List<Tipodebito> listTd =null;
		Session session=null;
		
		 try{
		   session = HibernateUtil.getSessionFactory().openSession();
		   listTd=tiposDebitoDAO.TipoDebitos(session);		   
		 }
	    catch(Exception ex){
	        throw new Exception(ex);
	    }
	    finally{
	        session.close();
	    }
		   return listTd;
	}
}
