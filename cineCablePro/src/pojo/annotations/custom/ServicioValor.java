package pojo.annotations.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pojo.annotations.Servicio;

public class ServicioValor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1261839051293932062L;
	private Servicio servicio;
	private List<ImpuestoValor> lisImpuestoValor;
	private String nombreDescuento;
	private float valorDescuento;

	public ServicioValor() {
		servicio = new Servicio();
		lisImpuestoValor = new ArrayList<ImpuestoValor>();
		nombreDescuento = "";
		valorDescuento = 0;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public float getValorDescuento() {
		return valorDescuento;
	}

	public void setValorDescuento(float valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public List<ImpuestoValor> getLisImpuestoValor() {
		return lisImpuestoValor;
	}

	public void setLisImpuestoValor(List<ImpuestoValor> lisImpuestoValor) {
		this.lisImpuestoValor = lisImpuestoValor;
	}

	public String getNombreDescuento() {
		return nombreDescuento;
	}

	public void setNombreDescuento(String nombreDescuento) {
		this.nombreDescuento = nombreDescuento;
	}

}
