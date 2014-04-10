package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Ctasprod;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.CtasprodDAO;

public class CtasprodBO {

	CtasprodDAO ctasprodDAO;
	
	public CtasprodBO() {
		ctasprodDAO = new CtasprodDAO();
	}
	
	public List<Ctasprod> lisCtasprod(int idcuenta) throws Exception {
		List<Ctasprod> lisCtasprod = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisCtasprod = ctasprodDAO.lisCtasprod(session, idcuenta, idempresa);
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			session.close();
		}
		
		return lisCtasprod;
	}
}
