package net.cinecable.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.Session;

import net.cinecable.dao.IAsignacionSolucitudDao;
import net.cinecable.dm.AsignacionSolicitudDM;
import net.cinecable.enums.Estados;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.ParamAsignacionOrden;
import net.cinecable.service.ITipoOperacionService;
import pojo.annotations.Estado;
import pojo.annotations.Tipooperacion;
import pojo.annotations.custom.TipoPersona;
import util.HibernateUtil;
import util.MessageUtil;

@ManagedBean(name="asignacionController")
@ViewScoped
public class AsignacionSolicitudController {
	@EJB 
	private ITipoOperacionService iTipoOperacionServices;
	@EJB
	private IAsignacionSolucitudDao iAsignacionSolucitudDao;
	@ManagedProperty(value="#{asignacionSolicitudDM}")
	private AsignacionSolicitudDM asignacionSolicitudDM;
	
	@PostConstruct
	public void init(){
		asignacionSolicitudDM.setTipoOperaciones(new ArrayList<Tipooperacion>());
		asignacionSolicitudDM.setParamAsigOrd(new ParamAsignacionOrden());
		asignacionSolicitudDM.setListaParametros(new ArrayList<ParamAsignacionOrden>());
		asignacionSolicitudDM.getParamAsigOrd().setTipoOperacion(new Tipooperacion());
		asignacionSolicitudDM.setFechaDesde(null);
		asignacionSolicitudDM.setFechaHasta(null);
	}
	public AsignacionSolicitudDM getAsignacionSolicitudDM() {
		return asignacionSolicitudDM;
	}

	public void setAsignacionSolicitudDM(AsignacionSolicitudDM asignacionSolicitudDM) {
		this.asignacionSolicitudDM = asignacionSolicitudDM;
	}
	
	public List<Tipooperacion> getTipoOperaciones(){
		asignacionSolicitudDM.setTipoOperaciones(iTipoOperacionServices.getAll());
		return asignacionSolicitudDM.getTipoOperaciones();
	}
	
	public void controlAsignacion(){
		if (asignacionSolicitudDM.getParamAsigOrd().getTipoOperacion().getIdtipooperacion()==0){
			new MessageUtil().showInfoMessage("Asignacion Ordenes", "Seleccione una operacion");
			return;
		}
	}
	
	public void controlFechas(){
		int dias=0;
		List<ParamAsignacionOrden> Asigxfechas=new ArrayList<ParamAsignacionOrden>();
		
		asignacionSolicitudDM.getListaParametros().clear();
		if (asignacionSolicitudDM.getFechaDesde()==null){
			new MessageUtil().showInfoMessage("Asignacion Ordenes", "Seleccione una Fecha hasta");
			return;
		}
		if (asignacionSolicitudDM.getFechaHasta()==null){
			new MessageUtil().showInfoMessage("Asignacion Ordenes", "Seleccione una Fecha hasta");
			return;
		}
		Calendar c1= Calendar.getInstance();
		Calendar c2= Calendar.getInstance();
		c1.setTime(asignacionSolicitudDM.getFechaDesde());
		c2.setTime(asignacionSolicitudDM.getFechaHasta());
		dias=c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR);
		if (dias<0){
			new MessageUtil().showInfoMessage("Asignacion Ordenes", "Rango de fechas invalidas: La fecha de inicio es mayor a la fecha final");
			return;
		}
		
		 Asigxfechas=iAsignacionSolucitudDao.AsigXfechas(asignacionSolicitudDM.getParamAsigOrd().getTipoOperacion().getIdtipooperacion(), asignacionSolicitudDM.getFechaDesde(), asignacionSolicitudDM.getFechaHasta());
		int cont=0;
		int tamAsigxfechas=Asigxfechas.size();
		int j=0;
		
		for (int i=0;i<=dias;i++){
			ParamAsignacionOrden param= new ParamAsignacionOrden();
			
			c1.add(Calendar.DAY_OF_YEAR, cont);
			param.setFechaasignacion(c1.getTime());
			param.setEstado(new Estado());
			param.getEstado().setIdestado(Estados.ACTIVO.getDescription());
			param.setTipoOperacion(asignacionSolicitudDM.getParamAsigOrd().getTipoOperacion());
		//	
			if (j<tamAsigxfechas) {
				param.setNoasignaciones(Asigxfechas.get(i).getNoasignaciones());
			} else
				param.setNoasignaciones(0);	
			cont=1;
			j=j+1;
			param.setIdparamasigord(i+1);
			
//			ParamAsignacionOrden parametro=iAsignacionSolucitudDao.ValidaDias(param.getTipoOperacion().getIdtipooperacion(), param.getFechaasignacion());
//			if (parametro!=null){
//				
//			}
			asignacionSolicitudDM.getListaParametros().add(param);
			
		}
	}
	public List<ParamAsignacionOrden> getAsignaciones(){
		return asignacionSolicitudDM.getListaParametros();
	}
	public void asignacionTotal(){
		if (asignacionSolicitudDM.getListaParametros().isEmpty()){
			new MessageUtil().showInfoMessage("Asignacion Ordenes", "No existen datos a generar");
			return;
		}
			
		if (asignacionSolicitudDM.getParamAsigOrd().getNoasignaciones()==0){
			for (int i=0;i<asignacionSolicitudDM.getListaParametros().size();i++){
				asignacionSolicitudDM.getListaParametros().get(i).setNoasignaciones(0);
			}
		}else{
			for (int i=0;i<asignacionSolicitudDM.getListaParametros().size();i++){
				asignacionSolicitudDM.getListaParametros().get(i).setNoasignaciones(asignacionSolicitudDM.getParamAsigOrd().getNoasignaciones());
			}
		}
	}
	public void grabar(){
		try {
			for (int i=0;i<asignacionSolicitudDM.getListaParametros().size();i++){
				Session sesion= new HibernateUtil().getSessionFactory().openSession();
				ParamAsignacionOrden asignaciones= (ParamAsignacionOrden) sesion.createQuery("from ParamAsignacionOrden o where o.fechaasignacion='" + new SimpleDateFormat("yyyy-MM-dd").format(asignacionSolicitudDM.getListaParametros().get(i).getFechaasignacion() )+ "' and o.tipoOperacion.idtipooperacion=" + asignacionSolicitudDM.getListaParametros().get(i).getTipoOperacion().getIdtipooperacion()).uniqueResult();
				if (asignaciones!=null)
					asignacionSolicitudDM.getListaParametros().get(i).setIdparamasigord(asignaciones.getIdparamasigord());
				else
					asignacionSolicitudDM.getListaParametros().get(i).setIdparamasigord(0);
				iAsignacionSolucitudDao.crear(asignacionSolicitudDM.getListaParametros().get(i));
			}
			new MessageUtil().showInfoMessage("Asignacion de solicitud por dia", "Ingreso exitoso");
			init();
		} catch (EntidadNoGrabadaException e) {
			new MessageUtil().showErrorMessage("Asignacion de solicitud por dia", "Error al ingresar: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	public void controlDias(ParamAsignacionOrden asignacion){
		for (int i=0;i<asignacionSolicitudDM.getListaParametros().size();i++){
			if (asignacionSolicitudDM.getListaParametros().get(i).getIdparamasigord()==asignacion.getIdparamasigord()){
				asignacionSolicitudDM.getListaParametros().get(i).setNoasignaciones(asignacion.getNoasignaciones());
			}
		}
	}
	
	
	
}
