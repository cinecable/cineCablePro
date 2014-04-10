package pojo.annotations.custom;

import java.io.Serializable;

public class TipoIdDoc  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1143047059425698871L;
	private int idTipoIdentidad;
	private String nombre;
	public TipoIdDoc(int idTipoIdentidad, String nombre) {
		this.idTipoIdentidad = idTipoIdentidad;
		this.nombre = nombre;
	}

	public int getIdTipoIdentidad() {
		return idTipoIdentidad;
	}

	public void setIdTipoIdentidad(int idTipoIdentidad) {
		this.idTipoIdentidad = idTipoIdentidad;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
