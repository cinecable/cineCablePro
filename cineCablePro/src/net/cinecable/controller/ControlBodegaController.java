package net.cinecable.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.dao.IControlBodegaDao;
import net.cinecable.dao.IMaterialesDao;
import net.cinecable.dao.IParametroMaterialOrdenesDao;
import net.cinecable.dao.IReservacionBodegaMaterialesDao;
import net.cinecable.dao.ITipoMaterialDao;
import net.cinecable.dm.ControlBodegaDm;
import net.cinecable.dm.TipoMaterialDM;
import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoMaterial;
import net.cinecable.enums.TipoModificacionReservaMateriales;
import net.cinecable.enums.TipoPersona;
import net.cinecable.enums.TipoUnidadMedida;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.hm.JrHM;
import net.cinecable.model.base.ControlBodega;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.ParametroMaterialOrdenes;
import net.cinecable.model.base.ReservacionesBodegaMateriales;
import net.cinecable.model.base.ReservacionesOrdenesBodega;
import net.cinecable.model.base.TipoMaterialCantidad;
import net.cinecable.service.IOrdenesService;
import net.cinecable.service.IPersonaServices;
import net.cinecable.service.IReservaOrdenesBodegaService;
import net.cinecable.service.ITipoOperacionService;

@ManagedBean(name = "controlBodegaController")
@ViewScoped
public class ControlBodegaController extends BaseController {

	@ManagedProperty(value = "#{controlBodegaDM}")
	ControlBodegaDm controlBodegaDM;

	@ManagedProperty(value = "#{tipoMaterialDM}")
	TipoMaterialDM tipoMaterialDm;

	@ManagedProperty(value = "#{jrreport}")
	JrHM report;

	@EJB
	IPersonaServices iPersona;
	@EJB
	ITipoOperacionService iTipoOperacionService;
	@EJB
	IReservaOrdenesBodegaService ireservaService;
	@EJB
	IOrdenesService iordenesService;
	@EJB
	IParametroMaterialOrdenesDao iParametroMaterialOrdenes;
	@EJB
	IMaterialesDao imaterialesDao;
	@EJB
	IReservacionBodegaMaterialesDao iReservaMaterialDao;
	@EJB
	ITipoMaterialDao itipoMaterialDao;
	@EJB
	IControlBodegaDao icontrolBodegaDao;

	int add;

	@PostConstruct
	public void init() {
		add = 0;
		tipoMaterialDm.setTipoMateriales(itipoMaterialDao.getAllTipoMaterial(Estados.ACTIVO));
		tipoMaterialDm.setCodMaterialSeleccionado(null);
		tipoMaterialDm.setMaterialSeleccionado(null);
		controlBodegaDM.setFinalizadaSeleccion(false);
		controlBodegaDM.setReservaAfectada(null);
		controlBodegaDM.setCodOrdenReservacionAdd(null);
		controlBodegaDM.setCodTecnicoSeleccionado(0L);
		controlBodegaDM.setOrdenesSeleccionadas(null);
		controlBodegaDM.setOrdenesTecnico(new ArrayList<Ordenes>());
		controlBodegaDM.setListaTecnico(iPersona.getListaPersonasbyTipo(TipoPersona.TEC));
		controlBodegaDM.setOrdenesBodega(new ArrayList<ReservacionesOrdenesBodega>());
		controlBodegaDM.setTecnicoSeleccionado(null);
		controlBodegaDM.setReservacionSeleccionada(null);
		controlBodegaDM.setMaterialesConsulta(new ArrayList<Materiales>());
		controlBodegaDM.setProceso(false);
		controlBodegaDM.setTodosSeleccion(false);
		controlBodegaDM.setControlBodegaSeleccionada(null);
		controlBodegaDM.setControlBodegaSeleccionada(null);
		controlBodegaDM.setOrdenesBodegaEliminadas(new ArrayList<ReservacionesOrdenesBodega>());
		controlBodegaDM.setModificar(0);
		controlBodegaDM.setListadoControlBodega(new ArrayList<ControlBodega>());
	}

	public void modificarControl() {

		if (controlBodegaDM.getTecnicoSeleccionado() == null) {
			showInfo("Control de Bodega", "Seleccione el Técnico primero");
			return;
		}
		controlBodegaDM.setListadoControlBodega(icontrolBodegaDao.getControlBodegabyTecnicoYestado(controlBodegaDM.getTecnicoSeleccionado()));
		controlBodegaDM.setModificar(1);
		controlBodegaDM.setProceso(true);
	}

	// opciones de agregar y quitar
	public void guardarReservarOrdenBodega() {

		Long idShow = null;
		for (ReservacionesOrdenesBodega ordBod : controlBodegaDM.getOrdenesBodega()) {
			if (ordBod.getReservaMateriales().isEmpty()) {
				showInfo("No hay Materiales Asignados en la orden :" + ordBod.getOrden().getMotivo().getNombre(), "revise la lista de materiales");
				return;
			}
		}

		for (ReservacionesOrdenesBodega ordenBog : controlBodegaDM.getOrdenesBodega()) {
			for (ReservacionesBodegaMateriales mat : ordenBog.getReservaMateriales()) {
				if (!mat.getCantidad().equals(mat.getCantidadTotal()) && mat.getObservaciones().isEmpty() || mat.getMaterial().getNroSerie() == null) {
					showInfo("Faltan información por llenar", "ingrese la observación donde los totales entregados sean diferentes a los iniciales");
					return;
				} else if (!mat.getCantidad().equals(mat.getCantidadTotal())) {
					mat.setModificadoPor(TipoModificacionReservaMateriales.BOD.getValue());
				} else if (mat.getCantidad().equals(mat.getCantidadTotal())) {
					mat.setModificadoPor(TipoModificacionReservaMateriales.NO.getValue());
				}
			}
		}

		try {
			if (controlBodegaDM.getOrdenesBodega().isEmpty()) {
				showInfo("Asignacion de Materiales", "No se han registrado cambios");
				init();
				return;
			}

			ControlBodega control = null;
			if (controlBodegaDM.getModificar() == 0) {
				control = new ControlBodega();
				control.setReservas(controlBodegaDM.getOrdenesBodega());
				control.setTecnico(controlBodegaDM.getTecnicoSeleccionado());
				iReservaMaterialDao.guardarReservacionesBodegaMateriales(control);
				idShow = control.getIdControl();
			} else {
				control = controlBodegaDM.getControlBodegaSeleccionada();

				ControlBodega controlNuevo = new ControlBodega();
				controlNuevo.setFechaControl(new Date());
				controlNuevo.setTecnico(controlBodegaDM.getTecnicoSeleccionado());

				List<ReservacionesOrdenesBodega> listNuevo = new ArrayList<ReservacionesOrdenesBodega>();
				for (int i = 0; i < control.getReservas().size(); i++) {
					ReservacionesOrdenesBodega resordNuevo = new ReservacionesOrdenesBodega();
					listNuevo.add(resordNuevo);
					resordNuevo.setControlBodega(controlNuevo);
					resordNuevo.setFechaIngreso(new Date());
					resordNuevo.setOrden(control.getReservas().get(i).getOrden());
					resordNuevo.setTecnico(control.getReservas().get(i).getTecnico());

					Iterator<ReservacionesBodegaMateriales> it = control.getReservas().get(i).getReservaMateriales().iterator();
					List<ReservacionesBodegaMateriales> materialesNuevo = new ArrayList<ReservacionesBodegaMateriales>();
					while (it.hasNext()) {
						ReservacionesBodegaMateriales bod = it.next();
						if (bod.getEstado() == Estados.ACTIVO.getDescription()) {
							ReservacionesBodegaMateriales tbod = bod;
							bod = new ReservacionesBodegaMateriales();
							bod.setIdReservacionBodegaMaterial(null);
							bod.setCantidad(tbod.getCantidad());
							bod.setCantidadAgregada(tbod.getCantidadAgregada());
							bod.setCantidadTotal(tbod.getCantidadTotal());
							bod.setIdtemp(tbod.getIdtemp());
							bod.setMaterial(tbod.getMaterial());
							bod.setModificadoPor(tbod.getModificadoPor());
							bod.setObservaciones(tbod.getObservaciones());
							bod.setProducto(tbod.getProducto());
							bod.setReservaOrdenBodega(resordNuevo);
							bod.setValorPagarMaterialExtra(tbod.getValorPagarMaterialExtra());
							materialesNuevo.add(bod);
						}
					}
					listNuevo.get(i).setReservaMateriales(materialesNuevo);
				}
				controlNuevo.setReservas(listNuevo);

				control.setEstado(Estados.INACTIVO.getDescription());
				iReservaMaterialDao.guardarReservacionesBodegaMaterialesModificada(control);
				iReservaMaterialDao.guardarReservacionesBodegaMateriales(controlNuevo);
				idShow = controlNuevo.getIdControl();
			}

			controlBodegaDM.setNroGeneracion(idShow);
			showInfo("Asignacion de Materiales", "Completada con exito, codigo generado " + idShow);
			init();
		} catch (EntidadNoGrabadaException e) {
			e.printStackTrace();
		}

	}

	public void seleccionarTodos() {
		if (controlBodegaDM.isTodosSeleccion())
			controlBodegaDM.setOrdenesSeleccionadas(controlBodegaDM.getOrdenesTecnico().toArray(new Ordenes[0]));
		else
			controlBodegaDM.setOrdenesSeleccionadas(null);
	}

	public void showReport() {
		report.setReportFile("materialesOrdenes.jasper");
		if (controlBodegaDM.getTecnicoSeleccionado() == null)
			return;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idtecnico", (long) controlBodegaDM.getTecnicoSeleccionado().getIdpersona());
		report.setParams(params);
	}

	public void finalizarSeleccion() {

		if (controlBodegaDM.getModificar() == 0) {
			controlBodegaDM.setFinalizadaSeleccion(true);
			controlBodegaDM.setOrdenesBodega(new ArrayList<ReservacionesOrdenesBodega>());
			for (Ordenes ord : controlBodegaDM.getOrdenesSeleccionadas()) {
				ReservacionesOrdenesBodega ordBod = new ReservacionesOrdenesBodega();
				ordBod.setReservaMateriales(new ArrayList<ReservacionesBodegaMateriales>());
				ordBod.setOrden(ord);
				ordBod.setFechaIngreso(Calendar.getInstance().getTime());
				ordBod.setEstado(Estados.ACTIVO.getDescription());
				ordBod.setTecnico(controlBodegaDM.getTecnicoSeleccionado());
				ParametroMaterialOrdenes parOrd = iParametroMaterialOrdenes.getParametroByIdTipOperacion((long) ord.getTipoOperacion().getIdtipooperacion());
				if (parOrd == null)
					continue;
				int tempid = 0;
				for (TipoMaterialCantidad tipcant : parOrd.getMateriales()) {
					tempid++;
					if (tipcant.getTipoMaterial().getTipMaterialGen().equals(TipoMaterial.EQ)) {
						for (int i = 0; i < tipcant.getCantMaterial(); i++) {
							Materiales mat = new Materiales();
							mat.setTipoMaterial(tipcant.getTipoMaterial());

							ReservacionesBodegaMateriales rbmat = new ReservacionesBodegaMateriales();
							rbmat.setEstado(Estados.ACTIVO.getDescription());
							rbmat.setReservaOrdenBodega(ordBod);
							rbmat.setMaterial(mat);
							rbmat.setIdtemp(ord.getIdOrdenes() + "-" + i + "-" + tempid);
							rbmat.setCantidad(1D);
							rbmat.setCantidadTotal(1D);
							rbmat.setProducto(ord.getProducto().getProducto());
							ordBod.getReservaMateriales().add(rbmat);
						}
					} else {
						Materiales mat = new Materiales();
						mat.setTipoMaterial(tipcant.getTipoMaterial());

						ReservacionesBodegaMateriales rbmat = new ReservacionesBodegaMateriales();
						rbmat.setEstado(Estados.ACTIVO.getDescription());
						rbmat.setReservaOrdenBodega(ordBod);
						rbmat.setMaterial(mat);
						rbmat.setIdtemp(ord.getIdOrdenes() + "-" + tipcant.getTipoMaterial().getTipMaterialGen().getValue() + "-" + tempid);
						rbmat.setCantidad(tipcant.getCantMaterial());
						rbmat.setCantidadTotal(tipcant.getCantMaterial());
						rbmat.setProducto(ord.getProducto().getProducto());
						ordBod.getReservaMateriales().add(rbmat);
					}

				}
				controlBodegaDM.getOrdenesBodega().add(ordBod);
			}
		} else {
			controlBodegaDM.setOrdenesBodega(controlBodegaDM.getControlBodegaSeleccionada().getReservas());

			Ordenes[] ordTemp = new Ordenes[controlBodegaDM.getOrdenesBodega().size()];
			for (int i = 0; i < controlBodegaDM.getOrdenesBodega().size(); i++) {
				ordTemp[i] = controlBodegaDM.getOrdenesBodega().get(i).getOrden();
			}
			controlBodegaDM.setOrdenesSeleccionadas(ordTemp);
			controlBodegaDM.setFinalizadaSeleccion(true);
			controlBodegaDM.setNroGeneracion(controlBodegaDM.getControlBodegaSeleccionada().getIdControl());
		}
	}

	public ControlBodegaDm getControlBodegaDM() {
		return controlBodegaDM;
	}

	public void setControlBodegaDM(ControlBodegaDm controlBodegaDM) {
		this.controlBodegaDM = controlBodegaDM;
	}

	public void removerReserva() {

		for (ReservacionesOrdenesBodega ordBod : controlBodegaDM.getOrdenesBodega()) {
			Iterator<ReservacionesBodegaMateriales> it = ordBod.getReservaMateriales().iterator();
			while (it.hasNext()) {
				ReservacionesBodegaMateriales itn = it.next();
				if (controlBodegaDM.getReservacionSeleccionada().equals(itn) && controlBodegaDM.getModificar() == 0) {
					it.remove();
				} else if (controlBodegaDM.getReservacionSeleccionada().equals(itn) && controlBodegaDM.getModificar() == 1) {
					controlBodegaDM.getReservacionSeleccionada().setEstado(Estados.INACTIVO.getDescription());
				}
			}

		}
	}

	// consultar ordenes asignadas a tecnico y segun parametro generar la lista
	// de ingreso de los materiales
	public void consultar() {
		controlBodegaDM.setProceso(true);
		controlBodegaDM.setModificar(0);
		if (controlBodegaDM.getCodTecnicoSeleccionado() == 0 ) {
			setMessage("Faltan datos", "Seleccione el técnico");
			return;
		}
		controlBodegaDM.setOrdenesTecnico(iordenesService.consultaOrdenesAsignadaTecnico(controlBodegaDM.getTecnicoSeleccionado(), Estados.ASIGNADA));
	}

	public void consultarReporte() {
		controlBodegaDM.setProceso(false);
		if (controlBodegaDM.getCodTecnicoSeleccionado() == 0) {
			//setMessage("Faltan datos", "Seleccione el técnico");
			showInfo("Control de Bodega", "Seleccione el Técnico primero");
			return;
		}
		controlBodegaDM.setOrdenesTecnico(iordenesService.consultaOrdenesAsignadaTecnicoReporte(controlBodegaDM.getTecnicoSeleccionado(), Estados.PROCESO));
	}

	public void addMaterial() {
		add++;

		if (tipoMaterialDm.getMaterialSeleccionado().getTipMaterialGen().equals(TipoMaterial.EQ)) {
			Materiales mat = new Materiales();
			mat.setTipoMaterial(tipoMaterialDm.getMaterialSeleccionado());
			ReservacionesBodegaMateriales rbmat = new ReservacionesBodegaMateriales();
			rbmat.setEstado(Estados.ACTIVO.getDescription());
			rbmat.setReservaOrdenBodega(controlBodegaDM.getReservaAfectada());
			rbmat.setMaterial(mat);
			rbmat.setIdtemp(controlBodegaDM.getReservaAfectada().getOrden().getIdOrdenes() + "-" + add);
			rbmat.setCantidad(1D);
			rbmat.setCantidadTotal(1D);
			controlBodegaDM.getReservaAfectada().getReservaMateriales().add(rbmat);
		} else {
			Materiales mat = new Materiales();
			mat.setTipoMaterial(tipoMaterialDm.getMaterialSeleccionado());
			ReservacionesBodegaMateriales rbmat = new ReservacionesBodegaMateriales();
			rbmat.setEstado(Estados.ACTIVO.getDescription());
			rbmat.setReservaOrdenBodega(controlBodegaDM.getReservaAfectada());
			rbmat.setMaterial(mat);
			rbmat.setIdtemp(controlBodegaDM.getReservaAfectada().getOrden().getIdOrdenes() + "-" + add);
			rbmat.setCantidad(1D);
			rbmat.setCantidadTotal(1D);
			controlBodegaDM.getReservaAfectada().getReservaMateriales().add(rbmat);
		}

	}

	public void asignarMaterial() {
		controlBodegaDM.getReservacionSeleccionada().setMaterial(controlBodegaDM.getMaterialSeleccionado());
		if (controlBodegaDM.getMaterialSeleccionado().getTipoUnidad().equals(TipoUnidadMedida.NA)) {
			controlBodegaDM.getReservacionSeleccionada().setCantidadTotal(controlBodegaDM.getReservacionSeleccionada().getCantidad());
		}
	}

	public void loadMaterial() {
		controlBodegaDM.setMaterialesConsulta(imaterialesDao.getAllbyTipoEquipo(controlBodegaDM.getReservacionSeleccionada().getMaterial().getTipoMaterial().getTipEquipoMaterial()));
		for (ReservacionesOrdenesBodega ordenBog : controlBodegaDM.getOrdenesBodega()) {
			for (ReservacionesBodegaMateriales mat : ordenBog.getReservaMateriales()) {
				if (mat.getMaterial().getIdUnidad() == null)
					continue;
				if (mat.getMaterial().getTipoMaterial().getTipMaterialGen().equals(TipoMaterial.EQ)) {
					controlBodegaDM.getMaterialesConsulta().remove(mat.getMaterial());
				} else if (mat.getMaterial().getTipoMaterial().getTipMaterialGen().equals(TipoMaterial.UT)) {
					for (int i = 0; i < controlBodegaDM.getMaterialesConsulta().size(); i++) {
						Materiales mattemp = controlBodegaDM.getMaterialesConsulta().get(i);
						if (mat.getMaterial().equals(mattemp)) {
							mattemp.setCantidad(mattemp.getCantidad() - mat.getCantidadTotal());
						}
					}
				}

			}
		}
	}

	public TipoMaterialDM getTipoMaterialDm() {
		return tipoMaterialDm;
	}

	public void setTipoMaterialDm(TipoMaterialDM tipoMaterialDm) {
		this.tipoMaterialDm = tipoMaterialDm;
	}

	public JrHM getReport() {
		return report;
	}

	public void setReport(JrHM report) {
		this.report = report;
	}

}
