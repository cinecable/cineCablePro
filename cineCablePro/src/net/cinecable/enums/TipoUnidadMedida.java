package net.cinecable.enums;

public enum TipoUnidadMedida {

	NA("NINGUNO"), MTR("METRO"), CM("CENTIMETRO");

	private String value;

	TipoUnidadMedida(String id) {
		this.value = id;
	}

	public String getValue() {
		return this.value;
	}

}
