package pojo.annotations.custom;

import java.io.Serializable;

public class DescuentoValor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7287897273930064193L;
	private String nombre;
	private float valor;
	
	public DescuentoValor() {
		nombre = null;
		valor = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

}
