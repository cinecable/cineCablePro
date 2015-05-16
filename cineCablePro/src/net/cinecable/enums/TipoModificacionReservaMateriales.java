package net.cinecable.enums;

public enum TipoModificacionReservaMateriales {
	// no moficiado
	// BODEGA
	NO("N"), BOD("BODEGA");

	private String value;

	TipoModificacionReservaMateriales(String val) {
		this.value = val;
	}

	public String getValue() {
		return this.value;
	}
}
