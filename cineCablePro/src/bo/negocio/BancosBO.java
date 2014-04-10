package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Bancos;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.BancosDAO;

public class BancosBO {

	BancosDAO bancosDAO;
	
	public BancosBO() {
		bancosDAO = new BancosDAO();
	}
	
	public Bancos getBancosById(int idbanco) throws Exception {
		Bancos bancos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            bancos = bancosDAO.getBancosById(session, idbanco);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return bancos;
	}
	
	public List<Bancos> lisBancosByTipoEntidad(int idtipoentidad) throws Exception {
		List<Bancos> lisBancos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisBancos = bancosDAO.lisBancosByTipoEntidad(session, idempresa, idtipoentidad);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisBancos;
	}
		
}
