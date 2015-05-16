package net.cinecable.enums;

public enum EstadosUnidades {
	// new nuevo,damage dmg/baja dwn
	NEW("NUEVO"), DMG("DAÑADO"), DWN("DE BAJA");

	private String value;

	EstadosUnidades(String id) {
		this.value = id;
	}

	public String getValue() {
		return this.value;
	}

}
