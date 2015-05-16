package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.model.base.Tidentidad;

@Local
public interface ITipoIdentidadServices {
	List<Tidentidad> getIdentidad();
}
