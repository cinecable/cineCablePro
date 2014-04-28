package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Telefono;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class TelefonosBean implements Serializable {

	/**
	 * prueba
	 */
	private static final long serialVersionUID = -2948329960675459182L;
	private List<Telefono> lisTelefonos;
	
	private Telefono telefonoSelected;
	private Telefono telefono;
	
	public TelefonosBean() {
		telefono = new Telefono();
		telefonoSelected = new Telefono();
		lisTelefonos = new ArrayList<Telefono>();
	}
	
	public void agregarTelefonos(){
				
		lisTelefonos.add(telefono);
								
		//inicializar
		telefono = new Telefono();
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

	
}
