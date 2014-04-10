package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Fpago;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.FpagoDAO;

public class FpagoBO {

	FpagoDAO fpagoDAO;
	
	public FpagoBO() {
		fpagoDAO = new FpagoDAO();
	}
	
	public Fpago getFpagoById(int idfpago) throws Exception {
		Fpago fpago = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            fpago = fpagoDAO.getFpagoById(session, idfpago);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return fpago;
	}
	
	public List<Fpago> lisFpago() throws Exception {
		List<Fpago> lisFpago = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisFpago = fpagoDAO.lisFpago(session, idempresa);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisFpago;
	}
}
