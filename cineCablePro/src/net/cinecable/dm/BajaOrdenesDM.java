package net.cinecable.dm;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.BajaOrdenesObservacion;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.ReservacionesBodegaMateriales;
import pojo.annotations.Nodos;
import pojo.annotations.Persona;

@ManagedBean(name = "bajaOrdenesDm")
@SessionScoped
public class BajaOrdenesDM {

	private Nodos nodoSeleecionado;

	private Long codNodoSeleccionado;

	private List<Nodos> listaNodos;

	private boolean seleccionTodo;

	private double cantidadInsertada;

	private ReservacionesBodegaMateriales utilUsados;

	private Persona tecnicoSeleccionado;
	private List<Persona> tecnicos;
	private Long codTecnicoSeleccionado;

	private List<Ordenes> listadoOrdenes;
	private Ordenes ordenSeleccionada = new Ordenes();
	private Long codOrdenSeleccionada;

	private List<ReservacionesBodegaMateriales> materialTecnico;
	private List<ReservacionesBodegaMateriales> materialCliente;

	private Materiales materialSeleccionadoCliente;
	private Long codMaterialSeleccionadoCliente;

	private Materiales materialSeleccionadoTecnico;
	private Long codMaterialSeleccionadoTecnico;

	private List<BajaOrdenesObservacion> observacionesBaja;

	private String observacionBaja;

	private Long codBajaObservacion;

	private BajaOrdenesObservacion observacionSeleccionada;

	public Long getCodBajaObservacion() {
		return codBajaObservacion;
	}

	public BajaOrdenesObservacion getObservacionSeleccionada() {
		return observacionSeleccionada;
	}

	public void setObservacionSeleccionada(BajaOrdenesObservacion observacionSeleccionada) {
		this.observacionSeleccionada = observacionSeleccionada;
	}

	public void setCodBajaObservacion(Long codBajaObservacion) {
		this.observacionSeleccionada = null;
		this.codBajaObservacion = codBajaObservacion;
		if (codBajaObservacion == null)
			return;
		for (int i = 0; i < observacionesBaja.size(); i++) {
			if (observacionesBaja.get(i).getIdBajaOrdenObservacion().equals(codBajaObservacion)) {
				this.observacionSeleccionada = observacionesBaja.get(i);
				break;
			}
		}

	}

	public List<BajaOrdenesObservacion> getObservacionesBaja() {
		return observacionesBaja;
	}

	public void setObservacionesBaja(List<BajaOrdenesObservacion> observacionesBaja) {
		this.observacionesBaja = observacionesBaja;
	}

	public Persona getTecnicoSeleccionado() {
		return tecnicoSeleccionado;
	}

	public void setTecnicoSeleccionado(Persona tecnicoSeleccionado) {
		this.tecnicoSeleccionado = tecnicoSeleccionado;
	}

	public List<Persona> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<Persona> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public Long getCodTecnicoSeleccionado() {
		return codTecnicoSeleccionado;
	}

	public void setCodTecnicoSeleccionado(Long codTecnicoSeleccionado) {
		this.codTecnicoSeleccionado = codTecnicoSeleccionado;
		this.tecnicoSeleccionado = null;
		if (codTecnicoSeleccionado == null)
			return;
		for (int i = 0; i < tecnicos.size(); i++) {
			if (this.codTecnicoSeleccionado.intValue() == tecnicos.get(i).getIdpersona()) {
				this.tecnicoSeleccionado = tecnicos.get(i);
				break;
			}
		}
	}

	public List<Ordenes> getListadoOrdenes() {
		return listadoOrdenes;
	}

	public void setListadoOrdenes(List<Ordenes> listadoOrdenes) {
		this.listadoOrdenes = listadoOrdenes;
	}

	public Ordenes getOrdenSeleccionada() {
		return ordenSeleccionada;
	}

	public void setOrdenSeleccionada(Ordenes ordenSeleccionada) {
		this.ordenSeleccionada = ordenSeleccionada;
	}

	public Long getCodOrdenSeleccionada() {
		return codOrdenSeleccionada;
	}

	public void setCodOrdenSeleccionada(Long codOrdenSeleccionada) {
		this.codOrdenSeleccionada = codOrdenSeleccionada;
		this.ordenSeleccionada = null;
		if (codOrdenSeleccionada == null)
			return;
		for (int i = 0; i < listadoOrdenes.size(); i++) {
			if (this.codOrdenSeleccionada.equals(listadoOrdenes.get(i).getIdOrdenes())) {
				this.ordenSeleccionada = listadoOrdenes.get(i);
				break;
			}
		}
	}

	public List<ReservacionesBodegaMateriales> getMaterialTecnico() {
		return materialTecnico;
	}

	public void setMaterialTecnico(List<ReservacionesBodegaMateriales> materialTecnico) {
		this.materialTecnico = materialTecnico;
	}

	public List<ReservacionesBodegaMateriales> getMaterialCliente() {
		return materialCliente;
	}

	public void setMaterialCliente(List<ReservacionesBodegaMateriales> materialCliente) {
		this.materialCliente = materialCliente;
	}

	public Materiales getMaterialSeleccionadoCliente() {
		return materialSeleccionadoCliente;
	}

	public void setMaterialSeleccionadoCliente(Materiales materialSeleccionadoCliente) {
		this.materialSeleccionadoCliente = materialSeleccionadoCliente;
	}

	public Long getCodMaterialSeleccionadoCliente() {
		return codMaterialSeleccionadoCliente;
	}

	public void setCodMaterialSeleccionadoCliente(Long codMaterialSeleccionadoCliente) {
		this.codMaterialSeleccionadoCliente = codMaterialSeleccionadoCliente;
		this.materialSeleccionadoCliente = null;
		if (codMaterialSeleccionadoCliente == null)
			return;
		for (int i = 0; i < materialCliente.size(); i++) {
			if (this.codMaterialSeleccionadoCliente.equals(materialCliente.get(i).getMaterial().getIdUnidad())) {
				this.materialSeleccionadoCliente = materialCliente.get(i).getMaterial();
				break;
			}
		}
	}

	public Materiales getMaterialSeleccionadoTecnico() {
		return materialSeleccionadoTecnico;
	}

	public void setMaterialSeleccionadoTecnico(Materiales materialSeleccionadoTecnico) {
		this.materialSeleccionadoTecnico = materialSeleccionadoTecnico;
	}

	public Long getCodMaterialSeleccionadoTecnico() {
		return codMaterialSeleccionadoTecnico;
	}

	public void setCodMaterialSeleccionadoTecnico(Long codMaterialSeleccionadoTecnico) {
		this.codMaterialSeleccionadoTecnico = codMaterialSeleccionadoTecnico;
		this.materialSeleccionadoTecnico = null;
		if (codMaterialSeleccionadoTecnico == null)
			return;
		for (int i = 0; i < materialTecnico.size(); i++) {
			if (this.codMaterialSeleccionadoTecnico.equals(materialTecnico.get(i).getMaterial().getIdUnidad())) {
				this.materialSeleccionadoTecnico = materialTecnico.get(i).getMaterial();
				break;
			}
		}
	}

	public String getObservacionBaja() {
		return observacionBaja;
	}

	public void setObservacionBaja(String observacionBaja) {
		this.observacionBaja = observacionBaja;
	}

	public ReservacionesBodegaMateriales getUtilUsados() {
		return utilUsados;
	}

	public void setUtilUsados(ReservacionesBodegaMateriales utilUsados) {
		this.utilUsados = utilUsados;
	}

	public boolean isSeleccionTodo() {
		return seleccionTodo;
	}

	public void setSeleccionTodo(boolean seleccionTodo) {
		this.seleccionTodo = seleccionTodo;
	}

	public double getCantidadInsertada() {
		return cantidadInsertada;
	}

	public void setCantidadInsertada(double cantidadInsertada) {
		this.cantidadInsertada = cantidadInsertada;
	}

	public Nodos getNodoSeleecionado() {
		return nodoSeleecionado;
	}

	public void setNodoSeleecionado(Nodos nodoSeleecionado) {
		this.nodoSeleecionado = nodoSeleecionado;
	}

	public Long getCodNodoSeleccionado() {
		return codNodoSeleccionado;
	}

	public void setCodNodoSeleccionado(Long codNodoSeleccionado) {
		this.codNodoSeleccionado = codNodoSeleccionado;
		this.nodoSeleecionado = null;
		if (codNodoSeleccionado == null)
			return;
		for (int i = 0; i < listaNodos.size(); i++) {
			if (listaNodos.get(i).getIdnodo() == codNodoSeleccionado.intValue()) {
				this.nodoSeleecionado = listaNodos.get(i);
				break;
			}
		}
	}

	public List<Nodos> getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(List<Nodos> listaNodos) {
		this.listaNodos = listaNodos;
	}

}
