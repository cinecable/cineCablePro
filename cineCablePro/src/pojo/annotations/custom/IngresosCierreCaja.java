package pojo.annotations.custom;

import java.io.Serializable;

public class IngresosCierreCaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2662112738442775461L;
	private int idusuario;
	private int idfpago;
	private String nombre;
	private Double valpago;
	
	public IngresosCierreCaja() {
		
	}
	
	public IngresosCierreCaja(int idusuario,int idfpago,String nombre,Double valpago) {
		this.idusuario = idusuario;
		this.idfpago = idfpago;
		this.nombre = nombre;
		this.valpago = valpago;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public int getIdfpago() {
		return idfpago;
	}

	public void setIdfpago(int idfpago) {
		this.idfpago = idfpago;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getValpago() {
		return valpago;
	}

	public void setValpago(Double valpago) {
		this.valpago = valpago;
	}

}
