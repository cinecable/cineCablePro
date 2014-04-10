package bo.negocio;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;

import pojo.annotations.Nrosfactura;

import util.FacesUtil;
import util.HibernateUtil;
import bean.controladores.UsuarioBean;

import dao.datos.NrosfacturaDAO;
import exceptions.SecuenciaFacturaException;

public class NrosfacturaBO {

	NrosfacturaDAO nrosfacturaDAO;
	
	public NrosfacturaBO() {
		nrosfacturaDAO = new NrosfacturaDAO();
	}
	
	public boolean hayFacturasDisponibles() throws Exception {
		boolean ok = false;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            String idptovta = usuarioBean.getUsuario().getPtovta();
            ok = nrosfacturaDAO.hayFacturasDisponibles(session, idempresa, idptovta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return ok;
	}
	
	public String getNumeroFacturaForUpdate(Session session, Nrosfactura nrosfacturaParam[]) throws Exception {
		String numeroFactura = null;
		
        UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
        int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
        String serieptovta = usuarioBean.getUsuario().getPtovta();
        
        //Consulto número de factura activo para el punto de venta
        Nrosfactura nrosfactura = nrosfacturaDAO.getNrosfacturaByIdptovtaForUpdate(session, idempresa, serieptovta, 1);
        
        if(nrosfactura != null && nrosfactura.getIdnrofactura() > 0){
        	
        	Calendar hoy = Calendar.getInstance();
        	if(nrosfactura.getMinfactura() < nrosfactura.getMaxfactura() && nrosfactura.getFcaducidad().compareTo(hoy.getTime()) >= 0){
        		nrosfactura.setMinfactura(nrosfactura.getMinfactura() + 1);
        		
        		//si encontré una secuencia disponible que no haya caducado armo el número de factura
        		numeroFactura = nrosfactura.getSerieempresa() + "-" + nrosfactura.getSerieptovta() + "-" + String.format("%07d", nrosfactura.getMinfactura());
        		
        		//Auditoria
            	Date fecharegistro = new Date();
            	nrosfactura.setFecha(fecharegistro);
            	nrosfactura.setIp(usuarioBean.getIp());
            	nrosfactura.setIdusuario(usuarioBean.getUsuario().getIdusuario());
            	
            	//Actualizo número de factura
            	nrosfacturaDAO.actualizarNrosfactura(session, nrosfactura);
            	
            	nrosfacturaParam[0] = nrosfactura;
        	}else{
        		
        		//Consulto si hay números de factura en reserva para el punto de venta
        		Nrosfactura nrosfactura2 = nrosfacturaDAO.getNrosfacturaByIdptovtaForUpdate(session, idempresa, serieptovta, 2);
        		
	        	if(nrosfactura2 != null && nrosfactura2.getIdnrofactura() > 0){
	        		
	            	if(nrosfactura2.getMinfactura() < nrosfactura2.getMaxfactura() && nrosfactura2.getFcaducidad().compareTo(hoy.getTime()) >= 0){
	            		nrosfactura2.setMinfactura(nrosfactura2.getMinfactura() + 1);
	            		
	            		//si encontré una secuencia reservada que no haya caducado armo el número de factura
		            	numeroFactura = nrosfactura2.getSerieempresa() + "-" + nrosfactura2.getSerieptovta() + "-" + String.format("%07d", nrosfactura2.getMinfactura());
		            	
		            	//inactivo el registro que ya no tiene secuencia
		            	nrosfactura.setIdestado(4);
		            	
		            	//Auditoria
		            	Date fecharegistro = new Date();
		            	nrosfactura.setFecha(fecharegistro);
		            	nrosfactura.setIp(usuarioBean.getIp());
		            	nrosfactura.setIdusuario(usuarioBean.getUsuario().getIdusuario());
		            	
		            	//Actualizo número de factura
		            	nrosfacturaDAO.actualizarNrosfactura(session, nrosfactura);
		            	
		            	//activo el registro que estaba reservado con las nuevas secuencias
		            	nrosfactura2.setIdestado(1);
		            	
		            	//Auditoria
		            	fecharegistro = new Date();
		            	nrosfactura2.setFecha(fecharegistro);
		            	nrosfactura2.setIp(usuarioBean.getIp());
		            	nrosfactura2.setIdusuario(usuarioBean.getUsuario().getIdusuario());
		            	
		            	//Actualizo número de factura
		            	nrosfacturaDAO.actualizarNrosfactura(session, nrosfactura2);
		            	
		            	nrosfacturaParam[0] = nrosfactura2;
	            	}else{
	            		throw new SecuenciaFacturaException("No se ha podido obtener el número de factura. Secuencias agotadas o caducadas.");
	            	}
	        	}else{
	        		throw new SecuenciaFacturaException("No se ha podido obtener el número de factura. Secuencias agotadas o caducadas.");
	            }
            }
        }else{
        	throw new SecuenciaFacturaException("No se ha podido obtener el número de factura. Favor verifique.");
        }
		
		return numeroFactura;
	}
}
