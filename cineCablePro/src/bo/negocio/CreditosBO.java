package bo.negocio;

import global.Parametro;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Cargos;
import pojo.annotations.Creditos;
import pojo.annotations.Estado;
import pojo.annotations.Factura;
import pojo.annotations.Fpago;
import pojo.annotations.Pagos;
import pojo.annotations.Tpagos;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.CargosDAO;
import dao.datos.CreditosDAO;
import dao.datos.FacturaDAO;
import dao.datos.PagosDAO;
import dao.datos.TpagosDAO;

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
	
	public boolean grabarCredito(Creditos creditos, int idsecuenciafactura) throws Exception {
		boolean ok = false;
    	Session session = null;
    	
    	try{
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			FacturaDAO facturaDAO = new FacturaDAO();
			PagosDAO pagosDAO = new PagosDAO();
			TpagosDAO tpagosDAO = new TpagosDAO();
			
			Date fecharegistro = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			Factura factura = facturaDAO.getFacturaById(session, idsecuenciafactura);

			//cuando factura ya está pagada, no afecto tabla pagos ni tpagos
			if(factura.getIdestado() != Parametro.FACTURA_ESTADO_PAGADA){
				//Obtengo Secuencia
				int IdpagopagosCredito = pagosDAO.maxIdPagos(session)+1;
				
				//creo el pago
				Pagos pagosCredito = new Pagos();
				pagosCredito.setIdpago(IdpagopagosCredito);
				pagosCredito.setIdfactura(factura.getIdfactura());
				pagosCredito.setIdgeneracion(factura.getIdgeneracion());
				pagosCredito.setValtotal(creditos.getVacredito());
				pagosCredito.setEmpresa(usuarioBean.getUsuario().getEmpresa());
				Estado estado = new Estado();
				estado.setIdestado(Parametro.PAGOS_ESTADO_ACTIVO);
				pagosCredito.setEstado(estado);
				pagosCredito.setIdcuenta(creditos.getIdcuenta());
				
				//Auditoria del pago
				fecharegistro = new Date();
				pagosCredito.setFecha(fecharegistro);
				pagosCredito.setIp(usuarioBean.getIp());
				pagosCredito.setUsuario(usuarioBean.getUsuario());
				
				//Creo la cabecera de pagos
				pagosDAO.ingresarPago(session, pagosCredito);
				
				//Obtengo Secuencia
				int IdtpagotpagosCredito = tpagosDAO.maxIdTpagos(session)+1;
				
				//Ingresar el credito en el detalle del pago
				Tpagos tpagosCredito = new Tpagos();
				tpagosCredito.setPagos(pagosCredito);
				tpagosCredito.setIdtpago(IdtpagotpagosCredito);
				tpagosCredito.setValpago(creditos.getVacredito());
				Fpago fpago = new Fpago();
				fpago.setIdfpago(Parametro.TIPO_FORMA_PAGO_CREDITO);
				tpagosCredito.setFpago(fpago);
				tpagosCredito.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
				tpagosCredito.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
				
				//Auditoria
				tpagosCredito.setIdusuario(usuarioBean.getUsuario().getIdusuario());
				
				//Creo el pago del credito en el detalle de pagos
				tpagosDAO.ingresarTpago(session, tpagosCredito);
			}
			
			//afecto factura valpendiente resto y valcredito se suma, estado consumido
			float valpendiente = factura.getValpendiente() - creditos.getVacredito();
			factura.setValpendiente(valpendiente);
			float valcreditos = factura.getValcreditos() + creditos.getVacredito();
			factura.setValcreditos(valcreditos);
			factura.setIdestado(Parametro.FACTURA_ESTADO_PAGADA);
			
			facturaDAO.actualizarFactura(session, factura);
			
			//grabo credito 
			//Obtengo Secuencia o para facturación electrónica obtener de webservice 
			int idcredito = creditosDAO.maxIdCreditos(session)+1;
			creditos.setIdcredito(idcredito);
			creditos.setIdfactura(factura.getIdfactura());
			creditos.setValrealfijo(creditos.getVacredito());
			
			//Auditoria
			creditos.setEmpresa(usuarioBean.getUsuario().getEmpresa());
			creditos.setUsuario(usuarioBean.getUsuario());
			Estado estado = new Estado();
			estado.setIdestado(Parametro.CREDITO_ESTADO_CONSUMIDO);
			creditos.setEstado(estado);
			fecharegistro = new Date();
			creditos.setFecha(fecharegistro);
			creditos.setIp(usuarioBean.getIp());
			
			creditosDAO.ingresarCreditos(session, creditos);
			
    		session.getTransaction().commit();
			ok = true;
    	}catch(Exception e){
    		session.getTransaction().rollback();
            throw new Exception(e);
        }finally{
        	session.close();
        }
    	
    	return ok;
	}
	
	public boolean grabarCargosFavor(Creditos creditos, int idsecuenciafactura, String motivo) throws Exception {
		boolean ok = false;
    	Session session = null;
    	
    	try{
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			FacturaDAO facturaDAO = new FacturaDAO();
			CargosDAO cargosDAO = new CargosDAO();
			
			Date fecharegistro = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			Factura factura = facturaDAO.getFacturaById(session, idsecuenciafactura);
			
			//en factura se suma en credito y resto en saldo base
			float valcreditos = factura.getValcreditos() + creditos.getVacredito();
			factura.setValcreditos(valcreditos);
			float valbase = factura.getValbase() - creditos.getVacredito();
			factura.setValbase(valbase);
			
			//Aunque recuerde en lo que es cargos a favor usted ya no va a hacer nada en la factura, solo graba en la tabla cargos
			//facturaDAO.actualizarFactura(session, factura);
			
			//cargos 
			Cargos cargos = new Cargos();
			
			//Obtengo Secuencia  
			int idcargo = cargosDAO.maxIdCargos(session)+1;
			cargos.setIdcargo(idcargo);
			cargos.setFactura(factura);
			cargos.setValcargo(creditos.getVacredito() * -1);
			cargos.setNivel(Parametro.CARGO_NIVEL_SERVICIO_MIN);
			cargos.setMotivo(motivo);
			cargos.setValpendiente(creditos.getVacredito() * -1);
			cargos.setValbase(0f);
			cargos.setDescuento(0f);
			cargos.setIdrubropadre(0);
			cargos.setIdcuenta(creditos.getIdcuenta());
			
			//Auditoria
			cargos.setUsuario(usuarioBean.getUsuario());
			Estado estado = new Estado();
			estado.setIdestado(Parametro.CARGOS_ESTADO_PENDIENTE);
			cargos.setEstado(estado);
			fecharegistro = new Date();
			cargos.setFecha(fecharegistro);
			cargos.setEmpresa(usuarioBean.getUsuario().getEmpresa());
			
			cargosDAO.ingresarCargos(session, cargos);
			
    		session.getTransaction().commit();
			ok = true;
    	}catch(Exception e){
    		session.getTransaction().rollback();
            throw new Exception(e);
        }finally{
        	session.close();
        }
    	
    	return ok;
	}
	
	public boolean grabarMultas(Creditos creditos, int idsecuenciafactura, String motivo) throws Exception {
		boolean ok = false;
    	Session session = null;
    	
    	try{
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			FacturaDAO facturaDAO = new FacturaDAO();
			CargosDAO cargosDAO = new CargosDAO();
			
			Date fecharegistro = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			Factura factura = facturaDAO.getFacturaById(session, idsecuenciafactura);
			
			//lo mismo que en cargos a favor pero positivo: en factura se suma en credito y sumo en saldo base
			float valcreditos = factura.getValcreditos() + creditos.getVacredito();
			factura.setValcreditos(valcreditos);
			float valbase = factura.getValbase() - creditos.getVacredito();
			factura.setValbase(valbase);
			
			//En este tipo de cargos Carlos usted no afecta a la factura solo crea el registro en la tabla cargos,
			//facturaDAO.actualizarFactura(session, factura);
			
			//cargos 
			Cargos cargos = new Cargos();
			
			//asi que el procedimiento es el mismo que en cargos a favor sino que con signo positivo
			//Obtengo Secuencia  
			int idcargo = cargosDAO.maxIdCargos(session)+1;
			cargos.setIdcargo(idcargo);
			cargos.setFactura(factura);
			cargos.setValcargo(creditos.getVacredito());
			cargos.setNivel(Parametro.CARGO_NIVEL_SERVICIO_MIN);
			cargos.setMotivo(motivo);
			cargos.setValpendiente(creditos.getVacredito());
			cargos.setValbase(0f);
			cargos.setDescuento(0f);
			cargos.setIdrubropadre(0);
			cargos.setIdcuenta(creditos.getIdcuenta());
			
			//Auditoria
			cargos.setUsuario(usuarioBean.getUsuario());
			Estado estado = new Estado();
			estado.setIdestado(Parametro.CARGOS_ESTADO_PENDIENTE);
			cargos.setEstado(estado);
			fecharegistro = new Date();
			cargos.setFecha(fecharegistro);
			cargos.setEmpresa(usuarioBean.getUsuario().getEmpresa());
			
			cargosDAO.ingresarCargos(session, cargos);
			
    		session.getTransaction().commit();
			ok = true;
    	}catch(Exception e){
    		session.getTransaction().rollback();
            throw new Exception(e);
        }finally{
        	session.close();
        }
    	
    	return ok;
	}
}
