package pojo.annotations.custom;

import java.io.Serializable;

public class IngresosEgresosCierreCaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2662112738442775461L;
	private int idusuario;
	private int idfpago;
	private String nombre;
	private float valpago;
	private String ingresoegreso;
	
	public IngresosEgresosCierreCaja() {
		
	}
	
	public IngresosEgresosCierreCaja(int idusuario,int idfpago,String nombre,float valpago) {
		this.idusuario = idusuario;
		this.idfpago = idfpago;
		this.nombre = nombre;
		this.valpago = valpago;
	}
	
	public IngresosEgresosCierreCaja(int idusuario,int idfpago,String nombre,float valpago,String ingresoegreso) {
		this.idusuario = idusuario;
		this.idfpago = idfpago;
		this.nombre = nombre;
		this.valpago = valpago;
		this.ingresoegreso = ingresoegreso;
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

	public float getValpago() {
		return valpago;
	}

	public void setValpago(float valpago) {
		this.valpago = valpago;
	}

	public String getIngresoegreso() {
		return ingresoegreso;
	}

	public void setIngresoegreso(String ingresoegreso) {
		this.ingresoegreso = ingresoegreso;
	}

}
