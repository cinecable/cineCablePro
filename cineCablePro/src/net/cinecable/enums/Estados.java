package net.cinecable.enums;

public enum Estados {

	ACTIVO(1), INACTIVO(2),ASIGNADA(8),PENDIENTE(3),REALIZADA(4),ESPERA(5),MONITOREO(6),PROCESO(7),
	BLOQUEADO(9),INGRESADOPAGADO(10),MONITOREADA(11);
	


	private int _desc;

	Estados(int des) {
		this._desc = des;
	}

	public int getDescription() {
		return this._desc;
	}
}
