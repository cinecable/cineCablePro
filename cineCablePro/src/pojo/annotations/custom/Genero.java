package pojo.annotations.custom;

import java.io.Serializable;

public class Genero  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3645462937124129967L;
	private int idGenero;
	private String nombre;
	public Genero(int idGenero, String nombre) {
		this.idGenero = idGenero;
		this.nombre = nombre;
	}
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
