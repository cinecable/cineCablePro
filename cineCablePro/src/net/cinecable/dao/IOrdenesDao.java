package net.cinecable.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import net.cinecable.enums.Estados;
import net.cinecable.model.base.Ordenes;
import pojo.annotations.Direccion;
import pojo.annotations.Persona;

@Local
public interface IOrdenesDao extends IGenericDao<Ordenes, Long> {

	List<Ordenes> consultaOrdenesporEstado(Estados estado);
	
	List<Ordenes> consultaOrdenesporEstadoTipo(Estados estado, Long tipOperacion);

	List<Ordenes> consultaOrdenesAsignadaTecnico(Persona Tecnico, Estados... estado);

	List<Ordenes> consultaOrdenesTecnico(Persona Tecnico, Estados... estado);

	List<Ordenes> consultaOrdenesAsignadaTecnicoReporte(Persona Tecnico, Estados... estados);

	Long CountByFechaTipoOpe(Date fecha, int tipOpe);
	
	boolean consultaOrdenesxStsTipoCli( int tipOperacion, String idDoc);

	void insOperacion(List<Direccion> listadireccion, Ordenes orden) throws Exception;

	public List<Ordenes> consultaOrdenesAsignadaTecnicoReporte2(Persona Tecnico, Estados... estados);

}
