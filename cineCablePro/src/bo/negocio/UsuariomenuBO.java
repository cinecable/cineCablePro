package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Usuariomenu;
import util.HibernateUtil;
import dao.datos.UsuariomenuDAO;

public class UsuariomenuBO {

	UsuariomenuDAO usuariomenuDAO;
	
	public UsuariomenuBO() {
		usuariomenuDAO = new UsuariomenuDAO();
	}
	
	public List<Usuariomenu> lisUsuariomenu(int idusuario,int idtipomenu) throws Exception {
		List<Usuariomenu> lisUsuariomenu = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			lisUsuariomenu = usuariomenuDAO.lisUsuariomenu(session, idusuario,idtipomenu);
		}
		catch(Exception ex){
			throw new Exception(ex);
		}
		finally{
			session.close();
		}
		
		return lisUsuariomenu;
	}
}
