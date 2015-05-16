package bo.negocio;

import java.security.MessageDigest;
import java.util.List;

import dao.datos.UsuarioDAO;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;
import pojo.annotations.Usuario;
import util.FacesUtil;
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
			usuario = usuarioDAO.getByUserPasswd(session, idusuario, encriptar(clave));
		}catch(Exception he){
			throw new Exception(he);
		}finally{
			session.close();
		}
		
		return usuario;
	}
	
	public List<Usuario> lisUsuario() throws Exception {
		List<Usuario> lisUsuario = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
			lisUsuario = usuarioDAO.lisUsuario(session, idempresa);
		}catch(Exception he){
			throw new Exception(he);
		}finally{
			session.close();
		}
		 
		return lisUsuario;
	}
	
	private String encriptar(String clear) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(clear.getBytes());
		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
			int u = b[i] & 255;
			if (u < 16) {
				h.append("0" + Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();
	}

	
}
