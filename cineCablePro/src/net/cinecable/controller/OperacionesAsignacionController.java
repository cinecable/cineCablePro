package net.cinecable.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.dao.ITecnicoSupervisorDao;
import net.cinecable.dm.AsignacionOrdenesDM;
import net.cinecable.dm.OrdenesDM;
import net.cinecable.dm.TipoOperacionesDM;
import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoPersona;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.OrdenesAsignaciones;
import net.cinecable.model.base.TecnicoSupervisor;
import net.cinecable.service.IOrdenesAsignacionesService;
import net.cinecable.service.IOrdenesService;
import net.cinecable.service.IPersonaServices;
import net.cinecable.service.ITipoOperacionService;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import bo.negocio.TbordenesBO;

import pojo.annotations.Estado;
import util.MessageUtil;

@ManagedBean(name = "opAsignacionController")
@ViewScoped
public class OperacionesAsignacionController extends BaseController {

	@ManagedProperty(value = "#{asignacionOperacionesDM}")
	private AsignacionOrdenesDM asignacionOperaciones;
	@ManagedProperty(value = "#{tipoOperacionesDM}")
	private TipoOperacionesDM tipoOperaciones;
	@ManagedProperty(value = "#{ordenesDM}")
	private OrdenesDM ordenesDm;
	@EJB
	ITipoOperacionService iTipoService;
	@EJB
	IOrdenesService iOrdenesService;
	@EJB
	IPersonaServices ipersona;
	@EJB
	IOrdenesAsignacionesService iordenesServiceAs;
	@EJB
	ITecnicoSupervisorDao itecnicoSupDao;
	@EJB
	IOrdenesAsignacionesService iordenesAsignacionesService;

	private DualListModel<Ordenes> ordenes;

	boolean hasvalue = false;

	private TecnicoSupervisor tecsuptem;

	@PostConstruct
	public void init() {
		ordenes = new DualListModel<Ordenes>();
		asignacionOperaciones.setEstadoOrden(null);
		asignacionOperaciones.setTipoOperacion(null);
		asignacionOperaciones.setTecnicos(ipersona.getListaPersonasbyTipo(TipoPersona.TEC));
		asignacionOperaciones.setSupervisores(ipersona.getListaPersonasbyTipo(TipoPersona.SUP));
		tipoOperaciones.setTipoOperaciones(iTipoService.getAll());
		asignacionOperaciones.setOrdenes(new ArrayList<Ordenes>());
		asignacionOperaciones.setOrdenesTecnico(new ArrayList<Ordenes>());
		asignacionOperaciones.setTecnicoSeleccionado(null);
		asignacionOperaciones.setCodSupervisor(0L);
		asignacionOperaciones.setCodTecnico(0L);
		tipoOperaciones.setCodOperacionSeleccionada(0L);
		asignacionOperaciones.setEstadoOrden("EN");
		asignacionOperaciones.setTipoOperacion("OC");
		asignacionOperaciones.setSupervisorSeleccionado(null);
		ordenesDm.setOrdenes(iOrdenesService.consultaOrdenesporEstado(null));
		assing();
	}

	public void consultarValor() {
		asignacionOperaciones.setTipoOperacion("OC");
		consultarCoincidencia();
	}

	public void buscaSupervisor() {
		tecsuptem = itecnicoSupDao.getSupervisorbyIdTecnico(asignacionOperaciones.getCodTecnico());
		asignacionOperaciones.setCodSupervisor(tecsuptem != null ? (long) tecsuptem.getSupervisor().getIdpersona() : null);
		asignacionOperaciones.setSupervisorSeleccionado(tecsuptem != null ? tecsuptem.getSupervisor() : null);
	}

	public void setAnteriorSupervisor() {
		asignacionOperaciones.setSupervisorSeleccionado(tecsuptem != null ? tecsuptem.getSupervisor() : null);
		asignacionOperaciones.setCodSupervisor(tecsuptem != null ? (long) tecsuptem.getSupervisor().getIdpersona() : null);
	}

	private void assing() {
		ordenes.setSource(asignacionOperaciones.getOrdenes());
		ordenes.setTarget(asignacionOperaciones.getOrdenesTecnico());
	}

	public DualListModel<Ordenes> getOrdenes() {
		return ordenes;
	}

	public void onTransfer(TransferEvent event) {
		Ordenes pass = (Ordenes) event.getItems().get(0);

		if (ordenes.getSource().contains(pass) && event.isRemove()) {
			ordenes.getTarget().remove(pass);
			hasvalue = true;
		}

		if (ordenes.getTarget().contains(pass) && event.isAdd()) {
			hasvalue = true;
		}
	}

	public void setOrdenes(DualListModel<Ordenes> ordenes) {
		if (!hasvalue)
			this.ordenes = ordenes;
		update("form-contentpage:ordenesList");
		hasvalue = false;
	}

	public TipoOperacionesDM getTipoOperaciones() {
		return tipoOperaciones;
	}

	public void setTipoOperaciones(TipoOperacionesDM tipoOperaciones) {
		this.tipoOperaciones = tipoOperaciones;
	}

	public void consultarCoincidencia() {
		if (asignacionOperaciones.getEstadoOrden().equals("EN")) {
			asignacionOperaciones.setOrdenes(new ArrayList<Ordenes>());
			assing();
		}

		if (asignacionOperaciones.getTecnicoSeleccionado() != null)
			asignacionOperaciones.setOrdenesTecnico(iOrdenesService.consultaOrdenesAsignadaTecnico(asignacionOperaciones.getTecnicoSeleccionado(), Estados.ASIGNADA));

		if (asignacionOperaciones.getEstadoOrden() == null || asignacionOperaciones.getEstadoOrden().isEmpty())
			return;
		ordenesDm.setOrdenes(iOrdenesService.consultaOrdenesporEstadoTipo(asignacionOperaciones.getEstadoOrden().equals("EN") ? Estados.PENDIENTE : Estados.ASIGNADA, tipoOperaciones.getCodOperacionSeleccionada()));

		asignacionOperaciones.setOrdenes(ordenesDm.getOrdenes());

		assing();
	}

	public void guardar() {
		try{
			if (asignacionOperaciones.getTecnicoSeleccionado() == null) {
				setMessage("Seleccione un Tecnico", "No se ha seleccionado un técnico para realizar la asignación");
				return;
			}
	
			if (asignacionOperaciones.getSupervisorSeleccionado() == null) {
				setMessage("Seleccione un Supervisor", "No se ha seleccionado un supervisor para realizar la asignación");
				return;
			}
			
			asignacionOperaciones.setOrdenesTecnico(ordenes.getTarget());
			asignacionOperaciones.setOrdenes(ordenes.getSource());
			
			//Ya que solo se muestran los padres tbordenes.idproductoprincipal = 0, 
			//aca agregamos los hijos
			TbordenesBO tbordenesBO = new TbordenesBO();
			List<Ordenes> lisOrdenesHijas = new ArrayList<Ordenes>();
			for(Ordenes ordenes : asignacionOperaciones.getOrdenesTecnico()){
				List<Ordenes> lisOrdenes = tbordenesBO.lisOrdenesHijasByIdPadre(ordenes.getIdOrdenes());
				if(lisOrdenes != null && lisOrdenes.size() > 0){
					lisOrdenesHijas.addAll(lisOrdenes);
				}
			}
			if(lisOrdenesHijas != null && lisOrdenesHijas.size() > 0){
				asignacionOperaciones.getOrdenesTecnico().addAll(lisOrdenesHijas);
			}
			lisOrdenesHijas = new ArrayList<Ordenes>();
			for(Ordenes ordenes : asignacionOperaciones.getOrdenes()){
				List<Ordenes> lisOrdenes = tbordenesBO.lisOrdenesHijasByIdPadre(ordenes.getIdOrdenes());
				if(lisOrdenes != null && lisOrdenes.size() > 0){
					lisOrdenesHijas.addAll(lisOrdenes);
				}
			}
			if(lisOrdenesHijas != null && lisOrdenesHijas.size() > 0){
				asignacionOperaciones.getOrdenes().addAll(lisOrdenesHijas);
			}
			
			List<OrdenesAsignaciones> ordenesAsignadas = new ArrayList<OrdenesAsignaciones>();
			for (Ordenes ord : asignacionOperaciones.getOrdenesTecnico()) {
				Estado estado = new Estado();
				estado.setIdestado(Estados.ASIGNADA.getDescription());
				ord.setEstado(estado);
				
				ordenesAsignadas.add(getOrdenAsignada(ord, "A"));
				ord.setFechaAsignacion(Calendar.getInstance().getTime());
			}
			for (Ordenes ord : asignacionOperaciones.getOrdenes()) {
				if (asignacionOperaciones.getOrdenesTecnico().contains(ord))
					continue;
				Estado estado = new Estado();
				estado.setIdestado(Estados.PENDIENTE.getDescription());
				ord.setEstado(estado);
				
				ordenesAsignadas.add(getOrdenAsignada(ord, "N"));
			}
	
			List<Ordenes> total = new ArrayList<Ordenes>();
			total.addAll(asignacionOperaciones.getOrdenesTecnico());
			total.addAll(asignacionOperaciones.getOrdenes());
	
			if (tecsuptem != null && tecsuptem.getSupervisor() != null && !tecsuptem.getSupervisor().equals(asignacionOperaciones.getSupervisorSeleccionado())) {
				tecsuptem.setEstado(Estados.INACTIVO.getDescription());
	
			} else {
				if (tecsuptem == null)
					tecsuptem = new TecnicoSupervisor();
				tecsuptem.setSupervisor(asignacionOperaciones.getSupervisorSeleccionado());
				tecsuptem.setTecnico(asignacionOperaciones.getTecnicoSeleccionado());
				tecsuptem.setEstado(Estados.ACTIVO.getDescription());
			}
	
			iordenesServiceAs.guardarInfoAsignacion(ordenesAsignadas, total, tecsuptem);
			consultarCoincidencia();
			RequestContext.getCurrentInstance().update("form-contentpage:ordenesList");
			showInfo("Asignacion de Ordenes", "Completada con exito");
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!","");
		}
	}

	private OrdenesAsignaciones getOrdenAsignada(Ordenes ord, String tip) {
		OrdenesAsignaciones ordeAsign = null;
		if (tip.equals("A")) {
			ordeAsign = iordenesAsignacionesService.getOrdenbyId(ord);
		}
		if (!tip.equals("A") || ordeAsign == null) {
			ordeAsign = iordenesAsignacionesService.getOrdenbyId(ord);
			if (ordeAsign == null)
				ordeAsign = new OrdenesAsignaciones();
		}

		if (ordeAsign.getIdOrdAsignacion() != null && !tip.equals("A")) {
			ordeAsign.setEstado(Estados.INACTIVO.getDescription());
		} else {
			ordeAsign.setEstado(tip.equals("A") ? Estados.ASIGNADA.getDescription() : Estados.PENDIENTE.getDescription());
		}

		ordeAsign.setFechaAsignacion(Calendar.getInstance().getTime());
		ordeAsign.setOrden(ord);
		ordeAsign.setSupervisor(asignacionOperaciones.getSupervisorSeleccionado());
		ordeAsign.setTecnico(asignacionOperaciones.getTecnicoSeleccionado());
		return ordeAsign;
	}

	public AsignacionOrdenesDM getAsignacionOperaciones() {
		return asignacionOperaciones;
	}

	public void setAsignacionOperaciones(AsignacionOrdenesDM asignacionOperaciones) {
		this.asignacionOperaciones = asignacionOperaciones;
	}

	public OrdenesDM getOrdenesDm() {
		return ordenesDm;
	}

	public void setOrdenesDm(OrdenesDM ordenesDm) {
		this.ordenesDm = ordenesDm;
	}

}
