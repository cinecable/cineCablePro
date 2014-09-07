package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Motivos;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.MotivosDAO;

public class MotivosBO {

	MotivosDAO motivosDAO;
	
	public MotivosBO() {
		motivosDAO = new MotivosDAO();
	}
	
	public Motivos getMotivosById(int idmotivo) throws Exception {
		Motivos motivos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            motivos = motivosDAO.getMotivosById(session, idmotivo);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return motivos;
	}
	
	public List<Motivos> lisMotivosByTipoMotivo(int idtipomotivo) throws Exception {
		List<Motivos> lisMotivos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisMotivos = motivosDAO.lisMotivosByTipoMotivo(session, idtipomotivo, idempresa);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisMotivos;
	}
}
