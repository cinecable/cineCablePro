package net.cinecable.model.extension;

import pojo.annotations.Persona;
import net.cinecable.model.base.Ordenes;

public class OrdenesConsulta extends Ordenes {
	private Persona tecnico;

	public Persona getTecnico() {
		return tecnico;
	}

	public void setTecnico(Persona tecnico) {
		this.tecnico = tecnico;
	}

}
