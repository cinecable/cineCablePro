package net.cinecable.enums;

public enum TipoPropietario {
	CLI("CLI"), BOD("BOD"), TEC("TEC");

	private String id;

	TipoPropietario(String val) {
		this.id = val;
	}

	public String getValue() {
		return this.id;
	}
}
