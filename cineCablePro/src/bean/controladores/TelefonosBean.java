package bean.controladores;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pojo.annotations.Telefono;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class TelefonosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2948329960675459182L;
	private String nombreTelefono;
	private String numeroTelefono;
	private List<Telefono> lisTelefonos;
	
	private Telefono telefonoSelected;
	private Telefono telefono;
	
	public TelefonosBean() {
	}
	
	public TelefonosBean(String nombreTelefono, String numeroTelefono) {
		this.nombreTelefono = nombreTelefono;
		this.numeroTelefono = numeroTelefono;
	}
	
	public void agregarTelefonos(){
				
		lisTelefonos.add(telefonoSelected);
								
	}
	
	public void quitarTelefono(ActionEvent actionEvent){
		try {
			lisTelefonos.remove(telefonoSelected);			
			
			new MessageUtil().showInfoMessage("Listo!", "Forma de pago excluida!");
		} catch(Exception re) {
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}

	public String getNombreTelefono() {
		return nombreTelefono;
	}

	public void setNombreTelefono(String nombreTelefono) {
		this.nombreTelefono = nombreTelefono;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
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

	
}
