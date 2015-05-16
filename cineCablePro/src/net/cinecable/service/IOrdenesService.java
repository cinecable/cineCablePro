package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Persona;

import net.cinecable.enums.Estados;
import net.cinecable.model.base.Ordenes;

@Local
public interface IOrdenesService {
	List<Ordenes> consultaOrdenesporEstado(Estados estado);

	/**
	 * Consulta las ordenes comprobando que no existan en la tabla de reserva de
	 * ordenes
	 */
	List<Ordenes> consultaOrdenesAsignadaTecnico(Persona Tecnico, Estados... estados);

	/**
	 * Consulta todas las ordenes de la tabla de orden segun los estados
	 * enviados
	 */
	List<Ordenes> consultaOrdenesTecnico(Persona Tecnico, Estados... estados);

	List<Ordenes> consultaOrdenesporEstadoTipo(Estados estado, Long tipOperacion);

	List<Ordenes> consultaOrdenesAsignadaTecnicoReporte(Persona Tecnico, Estados... estados);

	List<Ordenes> consultaOrdenesAsignadaTecnicoReporte2(Persona Tecnico, Estados... estados);

}
