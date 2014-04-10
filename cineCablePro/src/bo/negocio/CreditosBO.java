package bo.negocio;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Creditos;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.CreditosDAO;

public class CreditosBO {

	CreditosDAO creditosDAO;
	
	public CreditosBO() {
		creditosDAO = new CreditosDAO();
	}
	
	public Creditos getCreditoById(int idcredito) throws Exception {
		Creditos creditos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            creditos = creditosDAO.getCreditoById(session, idcredito);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return creditos;
	}
	
	public List<Creditos> lisCreditosActivosByFactura(String idfactura) throws Exception {
		List<Creditos> lisCreditos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisCreditos = creditosDAO.lisCreditosActivosByFactura(session, idfactura, idempresa);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCreditos;
	}
	
	public List<Creditos> lisCreditosActivosByCuenta(int idcuenta) throws Exception {
		List<Creditos> lisCreditos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisCreditos = creditosDAO.lisCreditosActivosByCuenta(session, idcuenta, idempresa);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCreditos;
	}
	
	public List<Creditos> lisCreditosUsadosByFactura(String idfactura) throws Exception {
		List<Creditos> lisCreditos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisCreditos = creditosDAO.lisCreditosUsadosByFactura(session, idfactura, idempresa);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCreditos;
	}
	
	public List<Creditos> lisCreditosUsadosByCuenta(int idcuenta) throws Exception {
		List<Creditos> lisCreditos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisCreditos = creditosDAO.lisCreditosUsadosByCuenta(session, idcuenta, idempresa);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCreditos;
	}
	
	public List<Creditos> lisCreditosByFechas(int idcuenta, Date fechaDesde, Date fechaHasta) throws Exception {
		List<Creditos> lisCreditos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisCreditos = creditosDAO.lisCreditosByFechas(session, idcuenta, idempresa, fechaDesde, fechaHasta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCreditos;
	}
}
