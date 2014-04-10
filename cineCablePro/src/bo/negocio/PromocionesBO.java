package bo.negocio;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import dao.datos.PromocionesDAO;

import pojo.annotations.Promociones;
import util.FacesUtil;
import util.HibernateUtil;


public class PromocionesBO {

	PromocionesDAO promocionesDAO;
	
	public PromocionesBO() {
		promocionesDAO = new PromocionesDAO();
	}
	
	public Promociones getPromocionesVip(int idtipocliente, int idproducto) throws Exception {
		Promociones promociones = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            promociones = promocionesDAO.getPromocionesVip(session, idtipocliente, idproducto, idempresa);
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			session.close();
		}
		
		return promociones;
	}
}
