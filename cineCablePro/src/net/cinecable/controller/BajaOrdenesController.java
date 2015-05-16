package net.cinecable.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import pojo.annotations.Costoservicio;
import net.cinecable.dao.IBajaOrdenesObservacionDao;
import net.cinecable.dao.ICostoServicioDao;
import net.cinecable.dao.IMaterialesDao;
import net.cinecable.dm.BajaOrdenesDM;
import net.cinecable.dm.ingOrdenesDM;
import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoMaterial;
import net.cinecable.enums.TipoPersona;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.BajaOrdenes;
import net.cinecable.model.base.BajaOrdenesObservacion;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.ReservacionesBodegaMateriales;
import net.cinecable.model.base.ResultadoObservacionBaja;
import net.cinecable.service.IBajaOrdenServices;
import net.cinecable.service.IOrdenesService;
import net.cinecable.service.IPersonaServices;
import net.cinecable.service.Inodos;

@ManagedBean(name = "bajaOrdenesController")
@ViewScoped
public class BajaOrdenesController extends BaseController {

	@EJB
	IOrdenesService iordenService;
	@EJB
	IPersonaServices ipersonaService;
	@EJB
	IMaterialesDao imaterialDao;
	@EJB
	IBajaOrdenServices ibajaOrdenService;
	@EJB
	IBajaOrdenesObservacionDao iBajaObservacionesDao;

	@EJB
	ICostoServicioDao icostoServicioDao;
	@EJB
	Inodos inodoService;

	@ManagedProperty(value = "#{bajaOrdenesDm}")
	private BajaOrdenesDM bajaOrdenesDm;

	@ManagedProperty(value = "#{ingOrdenesDM}")
	private ingOrdenesDM ingOrdenesDM;

	public BajaOrdenesDM getBajaOrdenesDm() {
		return bajaOrdenesDm;
	}

	public void setBajaOrdenesDm(BajaOrdenesDM bajaOrdenesDm) {
		this.bajaOrdenesDm = bajaOrdenesDm;
	}

	@PostConstruct
	public void init() {
		bajaOrdenesDm.setCodTecnicoSeleccionado(null);
		bajaOrdenesDm.setCodMaterialSeleccionadoCliente(null);
		bajaOrdenesDm.setMaterialSeleccionadoCliente(null);
		bajaOrdenesDm.setCodMaterialSeleccionadoTecnico(null);
		bajaOrdenesDm.setMaterialSeleccionadoTecnico(null);
		bajaOrdenesDm.setOrdenSeleccionada(null);
		bajaOrdenesDm.setCodOrdenSeleccionada(null);
		bajaOrdenesDm.setTecnicos(ipersonaService.getListaPersonasbyTipo(TipoPersona.TEC));
		bajaOrdenesDm.setCodBajaObservacion(null);
		bajaOrdenesDm.setObservacionesBaja(new ArrayList<BajaOrdenesObservacion>());
		bajaOrdenesDm.setListadoOrdenes(new ArrayList<Ordenes>());
		bajaOrdenesDm.setObservacionBaja(null);
		bajaOrdenesDm.setMaterialCliente(new ArrayList<ReservacionesBodegaMateriales>());
		bajaOrdenesDm.setMaterialTecnico(new ArrayList<ReservacionesBodegaMateriales>());
		ingOrdenesDM.setOrden(null);
		bajaOrdenesDm.setUtilUsados(null);
		bajaOrdenesDm.setCodNodoSeleccionado(null);
		bajaOrdenesDm.setListaNodos(inodoService.getAllNodos());
	}

	// Consultar las ordens Monitoradas
	public void getOrdenesTecnicosMonitoreadas() {
		bajaOrdenesDm.setListadoOrdenes(iordenService.consultaOrdenesTecnico(bajaOrdenesDm.getTecnicoSeleccionado(), Estados.MONITOREADA));
	}

	public void guardarBaja() {
		BajaOrdenes baja = new BajaOrdenes();
		baja.setObservacionBaja(bajaOrdenesDm.getObservacionBaja());
		baja.setResultadoObservacion(new ArrayList<ResultadoObservacionBaja>());

		if (!bajaOrdenesDm.getObservacionesBaja().isEmpty()) {
			if (bajaOrdenesDm.getObservacionSeleccionada() == null || bajaOrdenesDm.getObservacionBaja() == null || bajaOrdenesDm.getObservacionBaja().isEmpty()) {
				showInfo("Baja de Ordenes", "Seleccione una observación de la baja e ingrese el texto de la observación");
				return;
			}

		}

		// creacion de la observacion
		ResultadoObservacionBaja bajaObs = new ResultadoObservacionBaja();
		bajaObs.setBajaOrden(baja);
		bajaObs.setBajaOrdenesObservacion(bajaOrdenesDm.getObservacionSeleccionada());
		bajaObs.setObservacion(bajaOrdenesDm.getObservacionBaja());
		// anade observacion
		baja.getResultadoObservacion().add(bajaObs);
		try {
			//1 paso llamar funcion de cargos
			ibajaOrdenService.guardarBaja(baja, bajaOrdenesDm, hacerCostosServiciosporMaterialExcedido());
		} catch (EntidadNoGrabadaException e) {
			e.printStackTrace();
			setMessage("Baja de Ordenes", "Error al procesar la baja");
		}
		showInfo("Baja de Ordenes", "Orden dada de baja exitosamente");
		init();
	}

	private List<Costoservicio> hacerCostosServiciosporMaterialExcedido() {
		Costoservicio costo = null;
		List<Costoservicio> servicios = icostoServicioDao.consultarServiciosCostoPorIdTipoOperacionYTipoServicioAbrev("OTR");
		if (!servicios.isEmpty())
			costo = (Costoservicio) servicios.get(0);

		servicios.clear();
		for (ReservacionesBodegaMateriales mat : bajaOrdenesDm.getMaterialCliente()) {
			if (mat.getCantidadAgregada().equals(0D))
				continue;

			Costoservicio costotemp = new Costoservicio();
			costotemp.setIdservicio(costo.getIdservicio());
			costotemp.setServicio(costo.getServicio());
			costotemp.setCosto(mat.getValorPagarMaterialExtra().floatValue());
			servicios.add(costotemp);

		}
		return servicios;
	}

	public void mostrarInfoOrden() {
		ingOrdenesDM.setOrden(bajaOrdenesDm.getOrdenSeleccionada());
		bajaOrdenesDm.setMaterialTecnico(imaterialDao.getMaterialTecnicoBaja((long) bajaOrdenesDm.getTecnicoSeleccionado().getIdpersona(), bajaOrdenesDm.getOrdenSeleccionada()));
		bajaOrdenesDm.setMaterialCliente(imaterialDao.getMaterialPersonaCuentaBaja((long) bajaOrdenesDm.getOrdenSeleccionada().getCuentaCliente().getIdcuenta(), bajaOrdenesDm.getOrdenSeleccionada()));
		bajaOrdenesDm.setObservacionesBaja(iBajaObservacionesDao.getParametroBajaOrdenesObservacion(bajaOrdenesDm.getOrdenSeleccionada().getTipoOperacion()));
	}

	public ingOrdenesDM getIngOrdenesDM() {
		return ingOrdenesDM;
	}

	public void setIngOrdenesDM(ingOrdenesDM ingOrdenesDM) {
		this.ingOrdenesDM = ingOrdenesDM;
	}

	public void calculoTotal(AjaxBehaviorEvent event) {
		for (int i = 0; i < bajaOrdenesDm.getMaterialCliente().size(); i++) {
			ReservacionesBodegaMateriales mat = bajaOrdenesDm.getMaterialCliente().get(i);
			if (mat.getCantidadAgregada() == null)
				continue;
			mat.setCantidadTotal(mat.getCantidad() + mat.getCantidadAgregada());
			mat.setValorPagarMaterialExtra(mat.getCantidadTotal() > mat.getMaterial().getValorPorLimite() ? (mat.getCantidadAgregada() * mat.getMaterial().getValorPorLimite()) : 0D);
		}
	}

	// solo para los utiles como cable, los qeuipos se controlan
	// automaticamente en el control de ordenes
	public void materialUsadosaCliente() {
		ReservacionesBodegaMateriales bodMat = bajaOrdenesDm.getUtilUsados();
		if (!bodMat.getMaterial().getTipoMaterial().getTipMaterialGen().equals(TipoMaterial.UT)) {
			showInfo("Baja Ordenes", "Los equipos no pueden manipularse desde la baja de ordenes");
			return;
		}

		if (bajaOrdenesDm.isSeleccionTodo()) {
			bajaOrdenesDm.getMaterialCliente().add(bodMat);
			bajaOrdenesDm.getMaterialTecnico().remove(bodMat);
		} else {
			if (bajaOrdenesDm.getCantidadInsertada() == 0 || bajaOrdenesDm.getCantidadInsertada() > bodMat.getCantidad()) {
				showInfo("Baja Ordenes", "La cantidad insertada es 0 o supera al de la cantidad del material seleccionado");
				return;
			} else if (bajaOrdenesDm.getCantidadInsertada() == bodMat.getCantidad().doubleValue()) {
				bajaOrdenesDm.getMaterialCliente().add(bodMat);
				bajaOrdenesDm.getMaterialTecnico().remove(bodMat);
			} else {
				ReservacionesBodegaMateriales bodadd = new ReservacionesBodegaMateriales();
				bodadd.setCantidad(bajaOrdenesDm.getCantidadInsertada());
				bodadd.setIdReservacionBodegaMaterial(bodMat.getIdReservacionBodegaMaterial());
				bodadd.setIdtemp(bodMat.getIdtemp());
				bodadd.setMaterial(bodMat.getMaterial());
				bodadd.setModificadoPor(bodMat.getModificadoPor());
				bodadd.setObservaciones(bodMat.getObservaciones());
				bodadd.setProducto(bodMat.getProducto());
				bodadd.setReservaOrdenBodega(bodMat.getReservaOrdenBodega());
				bodadd.setValorPagarMaterialExtra(bodMat.getValorPagarMaterialExtra());
				bodMat.setModificadoBaja(true);
				bodadd.setModificadoBaja(true);
				bajaOrdenesDm.getMaterialCliente().add(bodadd);
				bodMat.setCantidad(bodMat.getCantidad() - bodadd.getCantidad());
			}

		}

	}

	public void hit() {
		bajaOrdenesDm.setCantidadInsertada(0);
		bajaOrdenesDm.setSeleccionTodo(false);
	}
}
