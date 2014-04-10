package pojo.annotations.custom;

import java.io.Serializable;

public class TipoPersona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 332860300416342923L;
	private int idTipoPersona;
	private String nombre;
	
	public TipoPersona(int idTipoPersona, String nombre) {
		
		this.idTipoPersona = idTipoPersona;
		this.nombre = nombre;
	}

	public int getIdTipoPersona() {
		return idTipoPersona;
	}

	public void setIdTipoPersona(int idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
