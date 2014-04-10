package bo.negocio;

import dao.datos.UsuarioDAO;
import org.hibernate.Session;
import pojo.annotations.Usuario;
import util.HibernateUtil;

public class UsuarioBO {
	
	private Session session = null;
	private UsuarioDAO usuarioDAO;
	
	public UsuarioBO() {
            usuarioDAO = new UsuarioDAO();
	}

	public Usuario getByUserPasswd(String idusuario, String clave ) throws Exception {
		Usuario usuario = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			usuario = usuarioDAO.getByUserPasswd(session, idusuario, clave);
		}catch(Exception he){
			throw new Exception(he);
		}finally{
			session.close();
		}
		
		return usuario;
	}
	
}
