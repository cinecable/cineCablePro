package bo.negocio;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import dao.datos.CierrecajaDAO;
import dao.datos.CierrecajadetDAO;
import dao.datos.SaldoscierreDAO;

import bean.controladores.UsuarioBean;

import pojo.annotations.Cierrecaja;
import pojo.annotations.Cierrecajadet;
import pojo.annotations.CierrecajadetId;
import pojo.annotations.Estado;
import pojo.annotations.Fpago;
import pojo.annotations.Saldoscierre;
import pojo.annotations.Usuario;
import pojo.annotations.custom.IngresosEgresosCierreCaja;
import util.FacesUtil;
import util.HibernateUtil;

public class CierreCajaBO {

	public boolean procesoCierreCaja(int idusuariocaja, Date fechaDesde,
			Date fechaHasta, boolean tienesaldoinicial, Saldoscierre saldoscierre, float totalIngresos, float totalEgresos, List<IngresosEgresosCierreCaja> lisIngresosCierreCaja, 
			List<IngresosEgresosCierreCaja> lisEgresosCierreCaja) throws Exception {
		
		boolean ok = false;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			CierrecajaDAO cierrecajaDAO = new CierrecajaDAO();
			SaldoscierreDAO saldoscierreDAO = new SaldoscierreDAO();
			CierrecajadetDAO cierrecajadetDAO = new CierrecajadetDAO();
			Date fecharegistro = new Date();
			
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
		
			//Cierrecaja
			Cierrecaja cierrecaja = new Cierrecaja();
			
			int idcierrecaja = cierrecajaDAO.maxId(session) + 1;
			cierrecaja.setIdcierrecaja(idcierrecaja);
			cierrecaja.setFechacierre(fechaDesde);
			//cierrecaja.setFechadesde(fechaDesde);
			//cierrecaja.setFechahasta(fechaHasta);
			
			Usuario usuario = new Usuario();
			usuario.setIdusuario(idusuariocaja);
			cierrecaja.setUsuariocaja(usuario);
			
			//obtener el saldo inicial/anterior
			//boolean tienesaldoinicial = false;
			//Saldoscierre saldoscierre = saldoscierreDAO.getByIdUsuario(session, idusuariocaja);
			
			/*if(saldoscierre != null){
				cierrecaja.setSaldoinicial(saldoscierre.getSaldo());
				tienesaldoinicial = true;
			}else{
				cierrecaja.setSaldoinicial(0f);
				tienesaldoinicial = false;
			}*/
			
			float saldofinal = cierrecaja.getSaldoinicial() + totalIngresos - totalEgresos;
			cierrecaja.setSaldofinal(saldofinal);
			
			//Auditoria 
			fecharegistro = new Date();
			cierrecaja.setFecha(fecharegistro);
			cierrecaja.setIplog(usuarioBean.getIp());
			cierrecaja.setUsuario(usuarioBean.getUsuario());
			cierrecaja.setEmpresa(usuarioBean.getUsuario().getEmpresa());
			
			//grabar Cierrecaja
			cierrecajaDAO.ingresar(session, cierrecaja);
			

			//Saldoscierre
			if(tienesaldoinicial){
				//si existe saldo se actualiza
				saldoscierre.setSaldo(saldofinal);
				
				//actualizar saldos cierre
				saldoscierreDAO.actualizar(session, saldoscierre);
			}else{
				//si no existe saldo se ingresa
				saldoscierre = new Saldoscierre();
				int idsaldoscierre = saldoscierreDAO.maxId(session) + 1;
				saldoscierre.setIdsaldoscierre(idsaldoscierre);
				
				Usuario usuarioByIdusuariocaja = new Usuario();
				usuarioByIdusuariocaja.setIdusuario(idusuariocaja);
				saldoscierre.setUsuarioByIdusuariocaja(usuarioByIdusuariocaja);
				
				saldoscierre.setSaldo(saldofinal);
				
				//Auditoria 
				fecharegistro = new Date();
				saldoscierre.setFecha(fecharegistro);
				saldoscierre.setIplog(usuarioBean.getIp());
				saldoscierre.setUsuarioByIdusuario(usuarioBean.getUsuario());
				saldoscierre.setEmpresa(usuarioBean.getUsuario().getEmpresa());
				
				//grabar saldos cierre
				saldoscierreDAO.ingresar(session, saldoscierre);
			}
			
			
			//Cierrecajadet
			
			//ingresos
			for(IngresosEgresosCierreCaja detalle : lisIngresosCierreCaja){
				grabarDetalle(session, idcierrecaja, cierrecajadetDAO, detalle, "I", usuarioBean);
			}
			
			//egresos
			for(IngresosEgresosCierreCaja detalle : lisEgresosCierreCaja){
				grabarDetalle(session, idcierrecaja, cierrecajadetDAO, detalle, "E", usuarioBean);
			}
		
			session.getTransaction().commit();
			ok = true;
		}catch(Exception he){
			session.getTransaction().rollback();
			throw new Exception(he);
		}finally{
			session.close();
		}
		
		return ok;
	}
	
	protected void grabarDetalle(Session session, int idcierrecaja, CierrecajadetDAO cierrecajadetDAO, IngresosEgresosCierreCaja detalle, String ingresoEgreso, UsuarioBean usuarioBean) throws Exception {
		CierrecajadetId cierrecajadetId = new CierrecajadetId();
		int idsecuencia = cierrecajadetDAO.maxId(session, idcierrecaja) + 1;
		
		cierrecajadetId.setIdcierrecaja(idcierrecaja);
		cierrecajadetId.setIdsecuencia(idsecuencia);
		
		Cierrecajadet cierrecajadet = new Cierrecajadet();
		cierrecajadet.setId(cierrecajadetId);
		cierrecajadet.setTotal(detalle.getValpago().floatValue());
		cierrecajadet.setIngresoegreso(ingresoEgreso);
		Fpago fpago = new Fpago();
		fpago.setIdfpago(detalle.getIdfpago());
		cierrecajadet.setFpago(fpago);
		Estado estado = new Estado();
		estado.setIdestado(1);
		cierrecajadet.setEstado(estado);
		
		//Auditoria 
		Date fecharegistro = new Date();
		cierrecajadet.setFecha(fecharegistro);
		cierrecajadet.setIplog(usuarioBean.getIp());
		cierrecajadet.setUsuario(usuarioBean.getUsuario());
		cierrecajadet.setEmpresa(usuarioBean.getUsuario().getEmpresa());
		
		//grabar detalle del cierre
		cierrecajadetDAO.ingresar(session, cierrecajadet);
	}

}
