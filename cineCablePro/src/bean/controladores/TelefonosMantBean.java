package bean.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Empresa;

import util.FacesUtil;
import util.MessageUtil;
import bo.negocio.CtaclienteBO;
import bo.negocio.TelefonoBO;

@ManagedBean
@ViewScoped
public class TelefonosMantBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -543115716737660786L;
	
	@ManagedProperty(value="#{telefonosBean}")
	private TelefonosBean telefonosBean;
	private String vacio;
	
	private int idcuenta;
	private Ctacliente ctacliente;
	
	private boolean modificable;

	public TelefonosMantBean() {
		vacio = "";
		
		ctacliente = new Ctacliente(0, new Empresa(), new Clientes());
	}

	@PostConstruct
	public void initTelefonosBean() {
		try{
			FacesUtil facesUtil = new FacesUtil();
			idcuenta = Integer
					.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
							.getParametroUrl("idcuenta").toString() : "0");
	
			if (idcuenta > 0) {
				//Al recibir por parametro el idcuenta, consultamos
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
				
				//telefonosBean.consultarTelefonos();
				
				if(telefonosBean.getLisTelefonos() != null && telefonosBean.getLisTelefonos().size() > 0){
					modificable = true;
				}else{
					modificable = false;
					new MessageUtil().showWarnMessage("Cliente no posee telefonos ingresados!", "");
				}
			}
		}
		catch(Exception re){
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}
	
	public void grabar(){
		if(validacionTelefonoOk()){
			try{
				boolean ok = false;
				TelefonoBO telefonoBO = new TelefonoBO();
				
				if(telefonosBean.getIdcuenta() > 0){
					//modificacion
					ok = telefonoBO.modificar(telefonosBean.getLisTelefonos(), telefonosBean.getLisTelefonosClon());
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
	
	private boolean validacionTelefonoOk(){
		boolean ok = false;
		
		if(telefonosBean.getLisTelefonos() != null && telefonosBean.getLisTelefonos().size() > 0){
			ok = true;
		}else{
			new MessageUtil().showWarnMessage("Debe agregar al menos un telefono en seccion Telefonos Cliente", "");
		}
		
		return ok;
	}
	
	public TelefonosBean getTelefonosBean() {
		return telefonosBean;
	}

	public void setTelefonosBean(TelefonosBean telefonosBean) {
		this.telefonosBean = telefonosBean;
	}

	public String getVacio() {
		return vacio;
	}

	public void setVacio(String vacio) {
		this.vacio = vacio;
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

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}
	
}
