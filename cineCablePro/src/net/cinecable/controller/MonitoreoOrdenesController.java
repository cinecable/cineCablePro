package net.cinecable.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Estado;

import net.cinecable.dao.IComandosDao;
import net.cinecable.dao.IMaterialesDao;
import net.cinecable.dao.IMonitoreoDao;
import net.cinecable.dao.IOrdenesDao;
import net.cinecable.dao.IinventarioDao;
import net.cinecable.dm.MonitoreoOrdenesDM;
import net.cinecable.enums.Estados;
import net.cinecable.enums.MovimientoInventario;
import net.cinecable.enums.TipoPersona;
import net.cinecable.enums.TipoPropietario;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.hm.MonitoreoEjecucionComandoHM;
import net.cinecable.model.base.Inventario;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.MonitoreoOrden;
import net.cinecable.model.base.MonitoreoTraza;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.TipoMaterial;
import net.cinecable.service.IComandoActivacionService;
import net.cinecable.service.IOrdenesService;
import net.cinecable.service.IPersonaServices;
import net.cinecable.ws.WebServiceException;

@ManagedBean(name = "monitoreoOrdenController")
@ViewScoped
public class MonitoreoOrdenesController extends BaseController {

	@ManagedProperty(value = "#{monitoreoOrdenDM}")
	private MonitoreoOrdenesDM monitoreoDm;

	@ManagedProperty(value = "#{monitoreoEjecucionHm}")
	private MonitoreoEjecucionComandoHM monitoreoHm;

	@EJB
	IPersonaServices ipersonaService;
	@EJB
	IOrdenesService iOrdenesService;
	@EJB
	IMonitoreoDao imonitoreoDao;
	@EJB
	IMaterialesDao imaterialesDao;
	@EJB
	IOrdenesDao iordenDao;
	@EJB
	IComandosDao icomandoDao;
	@EJB
	IComandoActivacionService icomandoActivacion;
	@EJB
	IinventarioDao iInventario;

	private boolean ismodificado = false;

	@PostConstruct
	public void init() {
		ismodificado = false;
		monitoreoDm.setTecnicos(ipersonaService.getListaPersonasbyTipo(TipoPersona.TEC));
		monitoreoDm.setCodOrdenSelecc(null);
		monitoreoDm.setCodTecSelecc(null);
		monitoreoDm.setFechaInicio(null);
		monitoreoDm.setFechaFin(null);
		monitoreoDm.setTecnicoSeleecionado(null);
		monitoreoDm.setOrdenSeleccionada(null);
		monitoreoDm.setOrdenesTecnico(new ArrayList<Ordenes>());
		monitoreoDm.setMaterialCliente(new ArrayList<Materiales>());
		monitoreoDm.setMaterialTecnico(new ArrayList<Materiales>());
		monitoreoDm.setCodComando(null);
		monitoreoDm.setComandoSeleccionado(null);
		monitoreoDm.setComandosEquipo(null);
		monitoreoDm.setObservacionMonitoreo(null);
		monitoreoDm.setRespuestaControlador("");
	}

	public void selectedOrden() {
		ismodificado = false;
		monitoreoDm.setFechaFin(null);
		monitoreoDm.setMaterialCliente(new ArrayList<Materiales>());
		monitoreoDm.setMaterialTecnico(new ArrayList<Materiales>());
		monitoreoDm.setCodComando(null);
		monitoreoDm.setComandoSeleccionado(null);

		monitoreoDm.setMonitoreo(imonitoreoDao.getMonitoreobyOrden(monitoreoDm.getOrdenSeleccionada().getIdOrdenes()));
		if (monitoreoDm.getMonitoreo() == null) {
			monitoreoDm.setFechaInicio(Calendar.getInstance().getTime());
		} else {
			monitoreoDm.setFechaInicio(monitoreoDm.getMonitoreo().getFechaInicioVisita());
			monitoreoDm.setFechaFin(Calendar.getInstance().getTime());
		}

		monitoreoDm.setMaterialTecnico(imaterialesDao.getMaterialTecnicoControl((long) monitoreoDm.getTecnicoSeleecionado().getIdpersona(), monitoreoDm.getOrdenSeleccionada(), net.cinecable.enums.TipoMaterial.EQ));
		monitoreoDm.setMaterialCliente(imaterialesDao.getMaterialPersonaCuentaControl((long) monitoreoDm.getOrdenSeleccionada().getCuentaCliente().getIdcuenta(), net.cinecable.enums.TipoMaterial.EQ));
	}

	public void consultarOrdenesTecnico() {
		monitoreoDm.setOrdenesTecnico(iOrdenesService.consultaOrdenesTecnico(monitoreoDm.getTecnicoSeleecionado(), Estados.PROCESO, Estados.MONITOREO));
	}

	public MonitoreoOrdenesDM getMonitoreoDm() {
		return monitoreoDm;
	}

	public void setMonitoreoDm(MonitoreoOrdenesDM monitoreoDm) {
		this.monitoreoDm = monitoreoDm;
	}

	public void guardarEjecucion() {

		MonitoreoOrden monitoreo = monitoreoDm.getMonitoreo();
		if (monitoreo == null) {
			monitoreo = new MonitoreoOrden();
			monitoreo.setObservacionMonitoreo(monitoreoDm.getObservacionMonitoreo());
			monitoreo.setOrden(monitoreoDm.getOrdenSeleccionada());
			monitoreo.setFechaInicioVisita(monitoreoDm.getFechaInicio());
			monitoreo.setTecnico(monitoreoDm.getTecnicoSeleecionado());
			monitoreo.setEstado(Estados.MONITOREO.getDescription());

		} else {
			if (ismodificado == false) {
				setMessage("Control de bodega", "Se registro con exito,\n no se ha realizado ninguna peticion al controlador");
			}
			monitoreo.setObservacionMonitoreo(monitoreoDm.getObservacionMonitoreo());
			monitoreo.setFechaFinVisita(monitoreoDm.getFechaFin());
			monitoreo.setEstado(Estados.MONITOREADA.getDescription());
		}

		for (MonitoreoTraza traza : monitoreoDm.getMonitoreoTraza()) {
			traza.setMonitoreo(monitoreo);
		}
		monitoreo.setMonitoreoTrazas(monitoreoDm.getMonitoreoTraza());
		try {
			if (monitoreo.getIdMonitoreo() == null) {
				Ordenes orden = monitoreo.getOrden();
				Estado estado = new Estado();
				estado.setIdestado(Estados.MONITOREO.getDescription());
				orden.setEstado(estado);
				iordenDao.actualizar(orden);
				imonitoreoDao.crear(monitoreo);
				setMessage("Control de bodega", "Entrada Registrada");

				return;
			} else {
				Ordenes orden = monitoreo.getOrden();
				Estado estado = new Estado();
				estado.setIdestado(Estados.MONITOREADA.getDescription());
				orden.setEstado(estado);
				orden.setFechaMonitoreo(new Date());
				iordenDao.actualizar(orden);
				imonitoreoDao.crear(monitoreo);
			}
		} catch (EntidadNoGrabadaException e) {
			e.printStackTrace();
			showInfo("Control de bodega", "Error al guardar datos del monitoreo");
			return;
		}

		for (Materiales material : monitoreoDm.getMaterialTecnico()) {
			if (!material.isModificadoMaterial())
				continue;
			Inventario inv = iInventario.getInventarioActivobyMaterial(material);
			inv.setEstado(Estados.INACTIVO.getDescription());

			try {
				iInventario.actualizar(inv);
			} catch (EntidadNoGrabadaException e) {
				e.printStackTrace();
			}

			inv.setIdInventario(null);
			inv.setEstado(Estados.ACTIVO.getDescription());
			inv.setFechaMovimiento(Calendar.getInstance().getTime());
			inv.setPropietario(TipoPropietario.TEC);
			inv.setTipMovimiento(MovimientoInventario.RECEPT);

			try {
				iInventario.crear(inv);
			} catch (EntidadNoGrabadaException e) {
				e.printStackTrace();
			}
			init();
		}

		for (Materiales material : monitoreoDm.getMaterialCliente()) {
			if (!material.isModificadoMaterial())
				continue;
			Inventario inv = iInventario.getInventarioActivobyMaterial(material);
			inv.setEstado(Estados.INACTIVO.getDescription());

			try {
				iInventario.actualizar(inv);
			} catch (EntidadNoGrabadaException e) {
				e.printStackTrace();
			}

			inv.setIdInventario(null);
			inv.setEstado(Estados.ACTIVO.getDescription());
			inv.setFechaMovimiento(Calendar.getInstance().getTime());
			inv.setPropietario(TipoPropietario.CLI);
			inv.setTipMovimiento(MovimientoInventario.TRASPAS);

			try {
				iInventario.crear(inv);
			} catch (EntidadNoGrabadaException e) {
				e.printStackTrace();
			}

		}

		showInfo("Control de bodega", "Completada con éxito");
		init();
	}

	public void listarComandoSegunTipoMaterialSeleccionado() {
		TipoMaterial material = monitoreoDm.getMaterialMonitoreoSeleccionado().getTipoMaterial();
		if (material.getTipMaterialGen().equals(net.cinecable.enums.TipoMaterial.UT)) {
			monitoreoDm.setComandosEquipo(null);
			return;
		}
		monitoreoDm.setComandosEquipo(icomandoDao.listarPorTipoMaterial(material));
	}

	public void retirarEquipoCliente() {
		ismodificado = true;
		Materiales material = monitoreoDm.getMaterialMonitoreoSeleccionadoCliente();
		material.setModificadoMaterial(true);

		MonitoreoTraza monTraza = new MonitoreoTraza();
		try {
			icomandoActivacion.borrarDispositivo(material, "");
			monitoreoDm.getMaterialCliente().remove(material);
			monitoreoDm.getMaterialTecnico().add(material);

		} catch (WebServiceException e) {
			e.printStackTrace();
			String err = e.getFaultString().replace("com.promptlink.ips.web.webservice.WebServiceException:", "");
			setMessage("Error en Monitoreo de Ordenes", err);
			monitoreoDm.setRespuestaControlador(err);
			monTraza.setActivadoSinError(false);
			monTraza.setMenError(err);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// guardaTraza
		monTraza.setCliente(monitoreoDm.getOrdenSeleccionada().getCuentaCliente().getClientes());
		monTraza.setCuentaCliente(monitoreoDm.getOrdenSeleccionada().getCuentaCliente());
		monTraza.setMaterial(monitoreoDm.getMaterialMonitoreoSeleccionado());
		monTraza.setOrden(monitoreoDm.getOrdenSeleccionada());
		monTraza.setTiempoComandoEjecucion(Calendar.getInstance());
		monitoreoDm.getMonitoreoTraza().add(monTraza);

	}

	public void ejecutarComando() {
		MonitoreoTraza monTraza = new MonitoreoTraza();
		try {
			icomandoActivacion.ejecutarComando(monitoreoDm.getOrdenSeleccionada().getCuentaCliente(), monitoreoDm.getComandoSeleccionado(), monitoreoDm.getMaterialMonitoreoSeleccionado(), "", monitoreoDm.getOrdenSeleccionada());
			showInfo("Monitoreo de Ordenes", "Activación enviada con éxito");
			Materiales material = monitoreoDm.getMaterialMonitoreoSeleccionado();
			material.setModificadoMaterial(true);

			monitoreoDm.getMaterialTecnico().remove(material);
			monitoreoDm.getMaterialCliente().add(material);

			monitoreoDm.setCodMonitoreoEquipoSeleccionado(null);
			ismodificado = true;
			monTraza.setActivadoSinError(true);
			monTraza.setMenError("OK");
		} catch (WebServiceException e) {
			e.printStackTrace();
			String err = e.getFaultString().replace("com.promptlink.ips.web.webservice.WebServiceException:", "");
			setMessage("Error en Monitoreo de Ordenes", err);
			monitoreoDm.setRespuestaControlador(err);
			monTraza.setActivadoSinError(false);
			monTraza.setMenError(err);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// guardaTraza
		monTraza.setCliente(monitoreoDm.getOrdenSeleccionada().getCuentaCliente().getClientes());
		monTraza.setCuentaCliente(monitoreoDm.getOrdenSeleccionada().getCuentaCliente());
		monTraza.setMaterial(monitoreoDm.getMaterialMonitoreoSeleccionado());
		monTraza.setOrden(monitoreoDm.getOrdenSeleccionada());
		monTraza.setTiempoComandoEjecucion(Calendar.getInstance());
		monitoreoDm.getMonitoreoTraza().add(monTraza);
	}

	public MonitoreoEjecucionComandoHM getMonitoreoHm() {
		return monitoreoHm;
	}

	public void setMonitoreoHm(MonitoreoEjecucionComandoHM monitoreoHm) {
		this.monitoreoHm = monitoreoHm;
	}

	public boolean isIsmodificado() {
		return ismodificado;
	}

	public void setIsmodificado(boolean ismodificado) {
		this.ismodificado = ismodificado;
	}

}
