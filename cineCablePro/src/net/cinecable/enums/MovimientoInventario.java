package net.cinecable.enums;

public enum MovimientoInventario {

	// R recepcion T traspaso
	RECEPT("R"), TRASPAS("T");

	private String id;

	MovimientoInventario(String t) {
		this.id = t;
	}

	public String getValue() {
		return this.id;
	}
}
