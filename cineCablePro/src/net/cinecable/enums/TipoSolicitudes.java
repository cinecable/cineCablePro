package net.cinecable.enums;

public enum TipoSolicitudes {
	Reconexion(20),
	CambioDireccion(21),
	Suspension(24),
	Cancelacion(27),
	Bloqueos(23),
	VisitaTec(22),
	InstNueva(25),
	InstTvAdi(26),
	Corte(28),
	Horarios(29),
	ReconexionCambioDireccion(30);
	private int tipo;

	private TipoSolicitudes(int tipo) {
		this.tipo = tipo;
	}

	public int getDescripcion() {
		return this.tipo;
	}
}
