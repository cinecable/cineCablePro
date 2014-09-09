package bean.controladores;

import global.Parametro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.negocio.CreditosBO;
import bo.negocio.FacturaBO;
import bo.negocio.MotivosBO;

import pojo.annotations.Creditos;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Factura;
import pojo.annotations.Motivos;
import pojo.annotations.Usuario;

import util.FacesUtil;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class DebitoCreditoBean implements Serializable {

	private static final long serialVersionUID = -2492131965686310734L;
	private int idcuenta;
	private int idmotivoselected;
	private List<Motivos> lisMotivos;
	private Creditos creditos;
	private List<Factura> lisFactura;
	private int idfacturaselected;
	private int idtipomotivoseleccionado;

	public DebitoCreditoBean() {
		creditos = new Creditos(0, new Estado(), new Usuario(), new Empresa(), new Motivos(), null, null, 0, null, null, 0, 0, 0);
		lisMotivos = new ArrayList<Motivos>();
		lisFactura = new ArrayList<Factura>();
		
		llenarMotivo();
		llenarFactura();
	}
	
	@PostConstruct
	public void initDebitoCreditoBean() {
		try{
			FacesUtil facesUtil = new FacesUtil();
			idcuenta = Integer
					.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
							.getParametroUrl("idcuenta").toString() : "0");
			
			if(idcuenta > 0){
			}
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showErrorMessage("Ha ocurrido un error comunicar al webmaster", "");
		}
	}
	
	public void grabar(){
		if(validacionOk()){
			try{
				
				boolean ok = false;
				
				CreditosBO creditosBO = new CreditosBO();
				
				creditos.setIdcuenta(idcuenta);
				
				if(idtipomotivoseleccionado == Parametro.TIPO_MOTIVO_CREDITO){
					ok = creditosBO.grabarCredito(creditos, idfacturaselected);
				}else{
					if(idtipomotivoseleccionado == Parametro.TIPO_MOTIVO_CREDITO_INTERNO){
						ok = creditosBO.grabarCargosFavor(creditos, idfacturaselected);
					}else{
						if(idtipomotivoseleccionado == Parametro.TIPO_MOTIVO_MULTAS){
							ok = creditosBO.grabarMultas(creditos, idfacturaselected);
						}
					}
				}
				
				if(ok){
					FacesUtil facesUtil = new FacesUtil();
					try {
						facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta="+idcuenta);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					new MessageUtil().showInfoMessage("No existen cambios que guardar", "");
				}
			} catch(Exception re) {
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
			}
		}
	}
	
	private boolean validacionOk(){
		boolean ok = true;
		
		try{
			FacturaBO facturaBO = new FacturaBO();
		
			//el credito junto con el q esta en la factura no exceda del total de la factura
			Factura factura = facturaBO.getFacturaById(idfacturaselected);
			float totalCredito = creditos.getVacredito() + factura.getValcreditos();
			
			if(totalCredito > factura.getValtotal()){
				ok = false;
				new MessageUtil().showWarnMessage("Total de créditos exceden del total de la factura. Corrija e intente nuevamente.", "");
			}
			
		} catch(Exception re) {
			ok = false;
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
		}
		
		return ok;
	}
	
	public void seleccionTransaccion() {
		//al seleccionar un tipo de tansaccion se llena la lista de motivos
		/*if(idtipomotivoseleccionado > 0) {
			try{
				MotivosBO motivosBO = new MotivosBO();
				lisMotivos = motivosBO.lisMotivosByTipoMotivo(idtipomotivoseleccionado);
			} catch(Exception re) {
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
			}
		}*/
		llenarMotivo();
		
		llenarFactura();
	}
	
	private void llenarMotivo(){
		Motivos motivosSeleccione = new Motivos();
		motivosSeleccione.setIdmotivo(0);
		motivosSeleccione.setNombre("Seleccione");
		
		lisMotivos = new ArrayList<Motivos>();
		
		lisMotivos.add(motivosSeleccione);
		
		if(idtipomotivoseleccionado > 0) {
			try{
				MotivosBO motivosBO = new MotivosBO();
				List<Motivos> lisTmp = motivosBO.lisMotivosByTipoMotivo(idtipomotivoseleccionado);
				
				if(lisTmp != null && lisTmp.size() > 0){
					lisMotivos.addAll(lisTmp);
				}
			} catch(Exception re) {
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
			}
		}
	}
	
	private void llenarFactura(){
		Factura facturaSeleccione = new Factura();
		facturaSeleccione.setIdsecuencia(0);
		facturaSeleccione.setIdfactura("Seleccione");
		
		lisFactura = new ArrayList<Factura>();
		
		lisFactura.add(facturaSeleccione);
		
		if(idtipomotivoseleccionado == Parametro.TIPO_MOTIVO_CREDITO ||
			idtipomotivoseleccionado == Parametro.TIPO_MOTIVO_FACTURA) {
			try{
				FacturaBO facturaBO = new FacturaBO();
				List<Factura> lisTmp = facturaBO.lisFacturaParaCreditosByIdcuenta(idcuenta);
				
				if(lisTmp != null && lisTmp.size() > 0){
					lisFactura.addAll(lisTmp);
				}
			} catch(Exception re) {
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
			}
		}else{
			if(idtipomotivoseleccionado == Parametro.TIPO_MOTIVO_CREDITO_INTERNO){
				try{
					FacturaBO facturaBO = new FacturaBO();
					List<Factura> lisTmp = facturaBO.lisFacturaGeneracionParaCreditosByIdcuenta(idcuenta);
					
					if(lisTmp != null && lisTmp.size() > 0){
						lisFactura.addAll(lisTmp);
					}
				} catch(Exception re) {
					re.printStackTrace();
					new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
				}
			}
		}
	}
	
	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public int getIdmotivoselected() {
		return idmotivoselected;
	}

	public void setIdmotivoselected(int idmotivoselected) {
		this.idmotivoselected = idmotivoselected;
	}

	public List<Motivos> getLisMotivos() {
		return lisMotivos;
	}

	public void setLisMotivos(List<Motivos> lisMotivos) {
		this.lisMotivos = lisMotivos;
	}

	public Creditos getCreditos() {
		return creditos;
	}

	public void setCreditos(Creditos creditos) {
		this.creditos = creditos;
	}

	public List<Factura> getLisFactura() {
		return lisFactura;
	}

	public void setLisFactura(List<Factura> lisFactura) {
		this.lisFactura = lisFactura;
	}

	public int getIdfacturaselected() {
		return idfacturaselected;
	}

	public void setIdfacturaselected(int idfacturaselected) {
		this.idfacturaselected = idfacturaselected;
	}

	public int getIdtipomotivoseleccionado() {
		return idtipomotivoseleccionado;
	}

	public void setIdtipomotivoseleccionado(int idtipomotivoseleccionado) {
		this.idtipomotivoseleccionado = idtipomotivoseleccionado;
	}
	
	public int getTIPO_MOTIVO_CREDITO() {
		return Parametro.TIPO_MOTIVO_CREDITO;
	}
	
	public int getTIPO_MOTIVO_MULTAS() {
		return Parametro.TIPO_MOTIVO_MULTAS;
	}
	
	public int getTIPO_MOTIVO_CREDITO_INTERNO() {
		return Parametro.TIPO_MOTIVO_CREDITO_INTERNO;
	}
	
	public int getTIPO_MOTIVO_FACTURA() {
		return Parametro.TIPO_MOTIVO_FACTURA;
	}
}
