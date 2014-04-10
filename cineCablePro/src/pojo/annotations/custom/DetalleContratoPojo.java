package pojo.annotations.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pojo.annotations.Producto;

public class DetalleContratoPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8445648210852542458L;
	private Producto producto;
	private List<ServicioValor> lisServicioValor;
	private float totalServicios;
	private float totalDescuentos;
	private float totalImpuestos;
	private float totalPagar;
	private float totalServicioAbono;
	private float totalDescuentoAbono;
	
	public DetalleContratoPojo() {
		producto = new Producto();
		lisServicioValor = new ArrayList<ServicioValor>();
		totalServicios = 0;
		totalDescuentos = 0;
		totalImpuestos = 0;
		totalPagar = 0;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<ServicioValor> getLisServicioValor() {
		return lisServicioValor;
	}

	public void setLisServicioValor(List<ServicioValor> lisServicioValor) {
		this.lisServicioValor = lisServicioValor;
	}

	public float getTotalServicios() {
		return totalServicios;
	}

	public void setTotalServicios(float totalServicios) {
		this.totalServicios = totalServicios;
	}

	public float getTotalDescuentos() {
		return totalDescuentos;
	}

	public void setTotalDescuentos(float totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public float getTotalImpuestos() {
		return totalImpuestos;
	}

	public void setTotalImpuestos(float totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	public float getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(float totalPagar) {
		this.totalPagar = totalPagar;
	}

	public float getTotalServicioAbono() {
		return totalServicioAbono;
	}

	public void setTotalServicioAbono(float totalServicioAbono) {
		this.totalServicioAbono = totalServicioAbono;
	}

	public float getTotalDescuentoAbono() {
		return totalDescuentoAbono;
	}

	public void setTotalDescuentoAbono(float totalDescuentoAbono) {
		this.totalDescuentoAbono = totalDescuentoAbono;
	}


}
