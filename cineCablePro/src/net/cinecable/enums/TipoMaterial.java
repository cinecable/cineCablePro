package net.cinecable.enums;

public enum TipoMaterial {
	/**
	 * EQ EQUIPOS, UT MATERIAL DIFERENTE A UN EQUIPO
	 */
	EQ("EQUIPOS"), UT("UTILES");

	private String value;

	TipoMaterial(String id) {
		this.value = id;
	}

	public String getValue() {
		return this.value;
	}

}
