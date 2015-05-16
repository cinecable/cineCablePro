package net.cinecable.service.imp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pojo.annotations.Persona;

import net.cinecable.dao.IOrdenesDao;
import net.cinecable.enums.Estados;
import net.cinecable.model.base.Ordenes;
import net.cinecable.service.IOrdenesService;

@Stateless
public class OrdenesService implements IOrdenesService {

	@EJB
	IOrdenesDao iordenesDao;

	@Override
	public List<Ordenes> consultaOrdenesporEstado(Estados estado) {
		List<Ordenes> result = iordenesDao.consultaOrdenesporEstado(estado);
		return result;
	}

	@Override
	public List<Ordenes> consultaOrdenesAsignadaTecnico(Persona Tecnico, Estados... estados) {
		List<Ordenes> q = iordenesDao.consultaOrdenesAsignadaTecnico(Tecnico, estados);
		return q;
	}

	@Override
	public List<Ordenes> consultaOrdenesAsignadaTecnicoReporte(Persona Tecnico, Estados... estados) {
		List<Ordenes> q = iordenesDao.consultaOrdenesAsignadaTecnicoReporte(Tecnico, estados);
		return q;
	}

	@Override
	public List<Ordenes> consultaOrdenesAsignadaTecnicoReporte2(Persona Tecnico, Estados... estados) {
		List<Ordenes> q = iordenesDao.consultaOrdenesAsignadaTecnicoReporte2(Tecnico, estados);
		return q;
	}

	@Override
	public List<Ordenes> consultaOrdenesporEstadoTipo(Estados estado, Long tipOperacion) {
		List<Ordenes> result = iordenesDao.consultaOrdenesporEstadoTipo(estado, tipOperacion);
		return result;
	}

	@Override
	public List<Ordenes> consultaOrdenesTecnico(Persona Tecnico, Estados... estados) {
		List<Ordenes> q = iordenesDao.consultaOrdenesTecnico(Tecnico, estados);
		return q;
	}

}
