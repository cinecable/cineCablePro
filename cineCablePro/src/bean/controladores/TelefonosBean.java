package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.negocio.CtaclienteBO;
import bo.negocio.TelefonoBO;

import pojo.annotations.Ctacliente;
import pojo.annotations.Telefono;
import util.FacesUtil;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class TelefonosBean implements Serializable {

	/**
	 * prueba
	 */
	private static final long serialVersionUID = -2948329960675459182L;
	private List<Telefono> lisTelefonos;
	private List<Telefono> lisTelefonosClon;
	
	private Telefono telefonoSelected;
	private Telefono telefono;
	
	private int idcuenta;
	private Ctacliente ctacliente;
	
	public TelefonosBean() {
		telefono = new Telefono();
		telefonoSelected = new Telefono();
		lisTelefonos = new ArrayList<Telefono>();
		lisTelefonosClon = new ArrayList<Telefono>();
		ctacliente = new Ctacliente();
	}
	
	@PostConstruct
	public void initTelefonosBean() {
		try {
			FacesUtil facesUtil = new FacesUtil();
			idcuenta = Integer
					.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
							.getParametroUrl("idcuenta").toString() : "0");
	
			if (idcuenta > 0) {
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
			}
		} catch(Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Error!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}
	
	public void consultarTelefonos(){
		if(this.idcuenta > 0){
			try {
				TelefonoBO telefonoBO = new TelefonoBO();
				lisTelefonos = telefonoBO.lisTelefonoPorCuenta(idcuenta);
				
				if(lisTelefonos != null && lisTelefonos.size() > 0){
					for(Telefono telefono : lisTelefonos){
						lisTelefonosClon.add(telefono.clonar());
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Error!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}
	
	public void agregarTelefonos(){
		if(validacionOk()){
			telefono.setCtacliente(ctacliente);
			lisTelefonos.add(telefono);
									
			//inicializar
			telefono = new Telefono();
		}
	}
	
	private boolean validacionOk(){
		boolean ok = false;
		
		if(telefono.getNombre() != null && telefono.getNombre().trim().length() > 0){
			if(telefono.getNumero() != null && telefono.getNumero().trim().length() > 0){
				ok = true;
			}else{
				new MessageUtil().showWarnMessage("Ingrese el numero de telefono del propietario.", "");
			}
		}else{
			new MessageUtil().showWarnMessage("Ingrese el nombre del propietario del telefono.", "");
		}
		
		return ok;
	}
	
	public void quitarTelefono(){
		try {
			lisTelefonos.remove(telefonoSelected);			
			new MessageUtil().showInfoMessage("Telefono excluido!", null);
		} catch(Exception re) {
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}

	public List<Telefono> getLisTelefonos() {
		return lisTelefonos;
	}

	public void setLisTelefonos(List<Telefono> lisTelefonos) {
		this.lisTelefonos = lisTelefonos;
	}

	public Telefono getTelefonoSelected() {
		return telefonoSelected;
	}

	public void setTelefonoSelected(Telefono telefonoSelected) {
		this.telefonoSelected = telefonoSelected;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public List<Telefono> getLisTelefonosClon() {
		return lisTelefonosClon;
	}

	public void setLisTelefonosClon(List<Telefono> lisTelefonosClon) {
		this.lisTelefonosClon = lisTelefonosClon;
	}

	
}
