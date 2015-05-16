package bean.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Bancos;
import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Debitobco;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Tipodebito;
import pojo.annotations.Tipoentidad;

import bo.negocio.CtaclienteBO;
import bo.negocio.DebitosbcoBO;

import exceptions.VerificarIdException;
import global.Parametro;

import util.FacesUtil;
import util.MessageUtil;
import util.VerificarId;

@ManagedBean
@ViewScoped
public class DebitoBancarioMantBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675277387540314618L;
	
	@ManagedProperty(value="#{debitosBancariosBean}")
	private DebitosBancariosBean debitosBancariosBean;
	private String vacio;
	
	private int idcuenta;
	private Ctacliente ctacliente;
	
	private boolean modificable;
	
	public DebitoBancarioMantBean() {
		vacio = "";
		ctacliente = new Ctacliente(0, new Empresa(), new Clientes());
	}
	
	@PostConstruct
	public void initDebitoBancarioMantBean() {
		try{
			FacesUtil facesUtil = new FacesUtil();
			idcuenta = Integer
					.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
							.getParametroUrl("idcuenta").toString() : "0");
	
			if (idcuenta > 0) {
				//Al recibir por parametro el idcuenta, consultamos
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
				
				//debitosBancariosBean.consultarDebitoBancario();
				
				if(debitosBancariosBean.getDebitobco() != null && debitosBancariosBean.getDebitobco().getIddebitobco() > 0){
					modificable = true;
					
					debitosBancariosBean.cargaBcoTar();
				//	debitosBancariosBean.consultarDebitoBancario();
				}else{
					modificable = false;
					//debitosBancariosBean.setDebitobco(new Debitobco(0, new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null), new Tipodebito(), null, null, null, new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null), null, 0, 0, null));
					//debitosBancariosBean.cargaBcoTar();
					//debitosBancariosBean.consultarDebitoBancario();
					debitosBancariosBean.setDebitobco(new Debitobco(0, new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null), new Tipodebito(), null, null, null, new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null), null, 0, 0, null));
					new MessageUtil().showWarnMessage("Cliente no posee Debitos ingresados!", "");
				}
			}
		}
		catch(Exception re){
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
		}
	}
	
	public void grabar(){
		if(validacionDebitoBcoOk()){
			try{
				boolean ok = false;
				DebitosbcoBO debitosbcoBO = new DebitosbcoBO();
				
				if(debitosBancariosBean.getIdcuenta() > 0){
					//modificacion
					ok = debitosbcoBO.modificar(debitosBancariosBean.getDebitobco(), debitosBancariosBean.getDebitobcoClon());
				}
				
				if(ok){
					FacesUtil facesUtil = new FacesUtil();
					try {
						facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta="+idcuenta);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					new MessageUtil().showInfoMessage("No hay cambios que guardar","");
				}
			} catch(Exception re) {
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
			}
		}
	}
	
	public boolean validacionDebitoBcoOk(){
		boolean ok = false;
		
		try{
			
			VerificarId verificarId  = new VerificarId();
			
			if(debitosBancariosBean.getDebitobco().getTipodebito().getIdtipodebito() > 0){
				ok = true;
			}else{
				new MessageUtil().showWarnMessage("Debe seleccionar una forma de cobro en seccion Debito Bancario", "");
			}
			
			if(ok){
				if(debitosBancariosBean.getDebitobco().getTipodebito().getIdtipodebito() == Parametro.TIPO_DEBITO_BANCARIO || debitosBancariosBean.getDebitobco().getTipodebito().getIdtipodebito() == Parametro.TIPO_DEBITO_TARJETA){
					if(debitosBancariosBean.getDebitobco().getBancos().getIdbanco() > 0){
						if(debitosBancariosBean.getDebitobco().getPropietario() != null && debitosBancariosBean.getDebitobco().getPropietario().trim().length() > 0){
							if(debitosBancariosBean.getDebitobco().getNrodebito() != null && debitosBancariosBean.getDebitobco().getNrodebito().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								new MessageUtil().showWarnMessage("Debe ingresar Nro Doc en seccion Debito Bancario", "");
							}
						}else{
							ok = false;
							new MessageUtil().showWarnMessage("Debe ingresar propietario en seccion Debito Bancario", "");
						}
					}else{
						ok = false;
						new MessageUtil().showWarnMessage("Debe seleccionar banco/tarjeta en seccion Debito Bancario", "");
					}
				}
			}
			
			if(ok){
				if(debitosBancariosBean.getDebitobco().getTipodebito().getIdtipodebito() == Parametro.TIPO_DEBITO_BANCARIO){
					if(debitosBancariosBean.getDebitobco().getIdtipocuenta() > 0){
						ok = true;
					}else{
						ok = false;
						new MessageUtil().showWarnMessage("Debe seleccionar tipo de cuenta en seccion Debito Bancario", "");
					}
				}
			}
			
			if(ok){
				if(debitosBancariosBean.getDebitobco().getIdtipoidentificacion() > 0){
					if(debitosBancariosBean.getDebitobco().getIdentificacion() != null && debitosBancariosBean.getDebitobco().getIdentificacion().trim().length() > 0){
						if(debitosBancariosBean.getDebitobco().getIdtipoidentificacion() == Parametro.TIPO_IDENTIFICACION_OTRO || verificarId.verificarId(debitosBancariosBean.getDebitobco().getIdentificacion())){
							ok = true;
						}else{
							ok = false;
						}
					}else{
						ok = false;
						new MessageUtil().showWarnMessage("Debe ingresar # identidad en seccion Debito Bancario", "");
					}
				}else{
					ok = false;
					new MessageUtil().showWarnMessage("Debe seleccionar tipo identidad en seccion Debito Bancario", "");
				}
			}
		}catch(VerificarIdException e){
			ok = false;
			e.printStackTrace();
			new MessageUtil().showWarnMessage(e.getMessage(), e.getMessage());
		}
		
		return ok;
	}

	public DebitosBancariosBean getDebitosBancariosBean() {
		return debitosBancariosBean;
	}

	public void setDebitosBancariosBean(DebitosBancariosBean debitosBancariosBean) {
		this.debitosBancariosBean = debitosBancariosBean;
	}

	public String getVacio() {
		return vacio;
	}

	public void setVacio(String vacio) {
		this.vacio = vacio;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public Ctacliente getCtacliente() {
		return ctacliente;
	}

	public void setCtacliente(Ctacliente ctacliente) {
		this.ctacliente = ctacliente;
	}

	public boolean isModificable() {
		return modificable;
	}

	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}

}
