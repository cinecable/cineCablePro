package net.cinecable.enums;

public enum TipoEjecucionComando {

	ACT("ACTIVACION"), DES("DESACTIVACION"), BLQ("BLOQUEO"), UBLQ("DESBLOQUEO"), DEL("ELIMINAR");

	private String value;

	TipoEjecucionComando(String id) {
		this.value = id;
	}

	public String getvalue() {
		return this.value;
	}

}
