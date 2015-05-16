package net.cinecable.dm;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.ControlBodega;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.ReservacionesBodegaMateriales;
import net.cinecable.model.base.ReservacionesOrdenesBodega;
import pojo.annotations.Persona;

@ManagedBean(name = "controlBodegaDM")
@SessionScoped
public class ControlBodegaDm {

	private int modificar;

	private List<Materiales> materialesFiltrados;

	private boolean proceso;

	private boolean todosSeleccion;

	private Long nroGeneracion;

	private Persona tecnicoSeleccionado;
	private Long codTecnicoSeleccionado;

	private List<Persona> listaTecnico = new ArrayList<Persona>();

	private Ordenes[] ordenesSeleccionadas;

	private List<Ordenes> ordenesTecnico;

	private boolean finalizadaSeleccion;

	private List<ReservacionesOrdenesBodega> ordenesBodega = new ArrayList<ReservacionesOrdenesBodega>();

	private ReservacionesBodegaMateriales ReservacionSeleccionada;

	private List<Materiales> materialesConsulta;

	private Materiales materialSeleccionado;

	private ReservacionesOrdenesBodega reservaAfectada;

	private Long codOrdenReservacionAdd;

	private ControlBodega controlBodegaSeleccionada;

	private List<ControlBodega> listadoControlBodega;

	private List<ReservacionesOrdenesBodega> ordenesBodegaEliminadas = new ArrayList<ReservacionesOrdenesBodega>();

	public Long getCodOrdenReservacionAdd() {
		return codOrdenReservacionAdd;
	}

	public void setCodOrdenReservacionAdd(Long codOrdenReservacionAdd) {
		this.codOrdenReservacionAdd = codOrdenReservacionAdd;
		this.reservaAfectada = null;
		if (codOrdenReservacionAdd == null)
			return;
		for (ReservacionesOrdenesBodega resOrd : ordenesBodega) {
			if (resOrd.getOrden().getIdOrdenes().equals(codOrdenReservacionAdd)) {
				reservaAfectada = resOrd;
				break;
			}
		}
	}

	public ReservacionesOrdenesBodega getReservaAfectada() {
		return reservaAfectada;
	}

	public void setReservaAfectada(ReservacionesOrdenesBodega reservaAfectada) {
		this.reservaAfectada = reservaAfectada;
	}

	public ReservacionesBodegaMateriales getReservacionSeleccionada() {
		return ReservacionSeleccionada;
	}

	public void setReservacionSeleccionada(ReservacionesBodegaMateriales reservacionSeleccionada) {
		ReservacionSeleccionada = reservacionSeleccionada;
	}

	public void setReservacionSeleccionadaAux(ReservacionesBodegaMateriales reservacionSeleccionada) {
		if (reservacionSeleccionada == null)
			return;
		ReservacionSeleccionada = reservacionSeleccionada;
	}
	
	public ReservacionesBodegaMateriales getReservacionSeleccionadaAux() {
		return ReservacionSeleccionada;
	}

	public List<ReservacionesOrdenesBodega> getOrdenesBodega() {
		return ordenesBodega;
	}

	public void setOrdenesBodega(List<ReservacionesOrdenesBodega> ordenesBodega) {
		this.ordenesBodega = ordenesBodega;
	}

	public List<Ordenes> getOrdenesTecnico() {
		return ordenesTecnico;
	}

	public void setOrdenesTecnico(List<Ordenes> ordenesTecnico) {
		this.ordenesTecnico = ordenesTecnico;
	}

	public Persona getTecnicoSeleccionado() {
		return tecnicoSeleccionado;
	}

	public void setTecnicoSeleccionado(Persona tecnicoSeleccionado) {
		this.tecnicoSeleccionado = tecnicoSeleccionado;
	}

	public Long getCodTecnicoSeleccionado() {
		return codTecnicoSeleccionado;
	}

	public void setCodTecnicoSeleccionado(Long codTecnicoSeleccionado) {
		this.codTecnicoSeleccionado = codTecnicoSeleccionado;
		this.tecnicoSeleccionado = null;
		for (int i = 0; i < listaTecnico.size(); i++) {
			if (listaTecnico.get(i).getIdpersona() == codTecnicoSeleccionado.intValue()) {
				tecnicoSeleccionado = listaTecnico.get(i);
				break;
			}
		}
	}

	public List<Persona> getListaTecnico() {
		return listaTecnico;
	}

	public void setListaTecnico(List<Persona> listaTecnico) {
		this.listaTecnico = listaTecnico;
	}

	public Ordenes[] getOrdenesSeleccionadas() {
		return ordenesSeleccionadas;
	}

	public void setOrdenesSeleccionadas(Ordenes[] ordenesSeleccionadas) {
		this.ordenesSeleccionadas = ordenesSeleccionadas;
	}

	public boolean isFinalizadaSeleccion() {
		return finalizadaSeleccion;
	}

	public void setFinalizadaSeleccion(boolean finalizadaSeleccion) {
		this.finalizadaSeleccion = finalizadaSeleccion;
	}

	public List<Materiales> getMaterialesConsulta() {
		return materialesConsulta;
	}

	public void setMaterialesConsulta(List<Materiales> materialesConsulta) {
		this.materialesConsulta = materialesConsulta;
		this.materialesFiltrados = materialesConsulta;
	}

	public Materiales getMaterialSeleccionado() {
		return materialSeleccionado;
	}

	public void setMaterialSeleccionado(Materiales materialSeleccionado) {
		this.materialSeleccionado = materialSeleccionado;
	}

	public Long getNroGeneracion() {
		return nroGeneracion;
	}

	public void setNroGeneracion(Long nroGeneracion) {
		this.nroGeneracion = nroGeneracion;
	}

	public boolean isProceso() {
		return proceso;
	}

	public void setProceso(boolean proceso) {
		this.proceso = proceso;
	}

	public List<Materiales> getMaterialesFiltrados() {
		return materialesFiltrados;
	}

	public void setMaterialesFiltrados(List<Materiales> materialesFiltrados) {
		this.materialesFiltrados = materialesFiltrados;
	}

	public boolean isTodosSeleccion() {
		return todosSeleccion;
	}

	public void setTodosSeleccion(boolean todosSeleccion) {
		this.todosSeleccion = todosSeleccion;
	}

	public ControlBodega getControlBodegaSeleccionada() {
		return controlBodegaSeleccionada;
	}

	public void setControlBodegaSeleccionada(ControlBodega controlBodegaSeleccionada) {
		this.controlBodegaSeleccionada = controlBodegaSeleccionada;
	}

	public List<ReservacionesOrdenesBodega> getOrdenesBodegaEliminadas() {
		return ordenesBodegaEliminadas;
	}

	public void setOrdenesBodegaEliminadas(List<ReservacionesOrdenesBodega> ordenesBodegaEliminadas) {
		this.ordenesBodegaEliminadas = ordenesBodegaEliminadas;
	}

	public int getModificar() {
		return modificar;
	}

	public void setModificar(int modificar) {
		this.modificar = modificar;
	}

	public List<ControlBodega> getListadoControlBodega() {
		return listadoControlBodega;
	}

	public void setListadoControlBodega(List<ControlBodega> listadoControlBodega) {
		this.listadoControlBodega = listadoControlBodega;
	}

}
