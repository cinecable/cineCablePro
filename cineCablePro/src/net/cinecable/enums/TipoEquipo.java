package net.cinecable.enums;

public enum TipoEquipo {

	MOD("MODEM"), DEC("DECODIFICADOR"),NA("NO EQUIPO");// ......

	private String value;

	TipoEquipo(String id) {
		this.value = id;
	}

	public String getValue() {
		return this.value;
	}
}
