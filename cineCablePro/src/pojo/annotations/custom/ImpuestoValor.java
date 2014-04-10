package pojo.annotations.custom;

import java.io.Serializable;

import pojo.annotations.Impservicios;

public class ImpuestoValor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9135992534845697501L;
	private Impservicios impservicios;
	private float valor;
	
	public ImpuestoValor() {
		impservicios = new Impservicios();
		valor = 0;
	}

	public Impservicios getImpservicios() {
		return impservicios;
	}

	public void setImpservicios(Impservicios impservicios) {
		this.impservicios = impservicios;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
