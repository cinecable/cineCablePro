package pojo.annotations.custom;

import java.io.Serializable;

public class TipoEstadoCivil  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4370644328179697600L;
	private int idEstadocivil;
	private String nombre;
	
	public TipoEstadoCivil(int idEstadocivil, String nombre) {
		this.idEstadocivil = idEstadocivil;
		this.nombre = nombre;
	}

	public int getIdEstadocivil() {
		return idEstadocivil;
	}

	public void setIdEstadocivil(int idEstadocivil) {
		this.idEstadocivil = idEstadocivil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
