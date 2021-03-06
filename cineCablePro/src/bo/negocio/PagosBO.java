package bo.negocio;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Pagos;
import pojo.annotations.custom.IngresosEgresosCierreCaja;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.PagosDAO;
import dao.datos.VingresosDAO;

public class PagosBO {
	
	public PagosBO() {
	}
	
	public Pagos getPagosById(int idpago) throws Exception {
		Pagos pagos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            PagosDAO pagosDAO = new PagosDAO();
            
            pagos = pagosDAO.getPagosById(session, idpago);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return pagos;
	}
	
	public List<Pagos> lisPagosAbonosActivosByFactura(String idfactura) throws Exception {
		List<Pagos> lisPagos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            PagosDAO pagosDAO = new PagosDAO();
            
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisPagos = pagosDAO.lisPagosAbonosActivosByFactura(session, idfactura, idempresa);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisPagos;
	}
	
	public List<Pagos> lisPagosAbonosActivosByCuenta(int idcuenta) throws Exception {
		List<Pagos> lisPagos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            PagosDAO pagosDAO = new PagosDAO();
            
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisPagos = pagosDAO.lisPagosAbonosActivosByCuenta(session, idcuenta, idempresa);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisPagos;
	}
	
	public List<Pagos> lisPagosByFechas(int idcuenta, Date fechaDesde, Date fechaHasta) throws Exception {
		List<Pagos> lisPagos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            PagosDAO pagosDAO = new PagosDAO();
            
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisPagos = pagosDAO.lisPagosByFechas(session, idcuenta, idempresa, fechaDesde, fechaHasta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisPagos;
	}
	
	public List<IngresosEgresosCierreCaja> lisIngresosCierreCaja(int idusuario, Date fechaDesde, Date fechaHasta) throws RuntimeException {
		List<IngresosEgresosCierreCaja> lisIngresosCierreCaja = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            PagosDAO pagosDAO = new PagosDAO();
            VingresosDAO vingresosDAO = new VingresosDAO();
            
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisIngresosCierreCaja = pagosDAO.lisSumIngresosByFechas(session, idusuario, idempresa, fechaDesde, fechaHasta);
            
        	for(IngresosEgresosCierreCaja reg:lisIngresosCierreCaja){
        		reg.setLisVingresos(vingresosDAO.lisVingresos(session, idusuario, reg.getIdfpago(), fechaDesde, fechaHasta));
        	}
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
        finally{
            session.close();
        }
		
		return lisIngresosCierreCaja;
	}
}
