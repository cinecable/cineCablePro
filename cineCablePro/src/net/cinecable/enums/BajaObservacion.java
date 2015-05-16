package net.cinecable.enums;

public enum BajaObservacion {

	IC("Instalacion Correcta"), IF("Instalacion Fallida");

	private String value;

	BajaObservacion(String id) {
		this.value = id;
	}

	public String getValue() {
		return this.value;
	}

}
