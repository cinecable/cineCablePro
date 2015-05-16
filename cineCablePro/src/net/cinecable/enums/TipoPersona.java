package net.cinecable.enums;

public enum TipoPersona {

	TEC("TEC"), SUP("SUP");

	private String tipoPersona;

	TipoPersona(String tipo) {
		this.tipoPersona = tipo;
	}

	public String getValue() {
		return this.tipoPersona;
	}

}
