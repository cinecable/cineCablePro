package pojo.annotations.custom;

import java.io.Serializable;

public class TipoCuenta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3394980801323500947L;

	private int idtipoCuenta;
	private String nombre;
	
	public TipoCuenta() {		
	}
	public TipoCuenta(int idtipoCuenta, String nombre) {
		this.idtipoCuenta = idtipoCuenta;
		this.nombre = nombre;
	}

	public int getIdtipoCuenta() {
		return idtipoCuenta;
	}

	public void setIdtipoCuenta(int idtipoCuenta) {
		this.idtipoCuenta = idtipoCuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
