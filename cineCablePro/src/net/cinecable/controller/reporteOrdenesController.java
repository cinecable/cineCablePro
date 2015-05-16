package net.cinecable.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Estado;
import pojo.annotations.Nodos;
import pojo.annotations.Persona;
import pojo.annotations.Tipooperacion;
import util.FacesUtil;
import util.HibernateUtil;

import net.cinecable.dm.reporteOrdenesDM;
import net.cinecable.enums.TipoPersona;
import net.cinecable.hm.JrHM;
import net.cinecable.service.IPersonaServices;
import net.cinecable.service.ITipoOperacionService;
import net.cinecable.service.Inodos;

@ManagedBean(name="reporteOrdenesController")
@ViewScoped
public class reporteOrdenesController {
	@ManagedProperty(value="#{reporteOrdenesDM}")
	private reporteOrdenesDM reporteDM;
	@EJB
	private ITipoOperacionService iTipoOperacionServices;
	@EJB
	private Inodos nodosservices;
	@EJB
	private IPersonaServices personaServices;
	@ManagedProperty(value = "#{jrreport}")
	JrHM report;
	@PostConstruct
	public void init(){
		reporteDM.setFechaDesde(new Date());
		reporteDM.setFechaHasta(new Date());
		
	}
	
	public void nombreEstado(String estado){
		reporteDM.setEstadoNombre(estado);
		
	}
	
	public void showReport() {
		FacesUtil faces = new FacesUtil();
		UsuarioBean usuarioBean =null;
		if (faces.getSessionBean("usuarioBean") != null) {
			usuarioBean = (UsuarioBean) faces.getSessionBean("usuarioBean");
		}
		if (usuarioBean==null){
			return;
		}
		report.setReportFile("operacionesOperacionales.jasper");
		Map<String, Object> params = new HashMap<String, Object>();
		if (reporteDM.getFechaDesde()!=null) params.put("fechadesde", new SimpleDateFormat("yyyy-MM-dd").format(reporteDM.getFechaDesde())); else params.put("fechadesde", null);
		if (reporteDM.getFechaHasta()!=null) params.put("fechaHasta", new SimpleDateFormat("yyyy-MM-dd").format(reporteDM.getFechaHasta())); else params.put("fechaHasta", null);
		params.put("estado", reporteDM.getEstado().intValue());
		params.put("toperacion", reporteDM.getTipooperacion().intValue());
		params.put("idtec", reporteDM.getTecnico().intValue());
		params.put("nodoid", reporteDM.getNodo().intValue());
		if (reporteDM.getTipooperacion()>0){
			for (Tipooperacion tipoOp:getToperaciones()){
				if (tipoOp.getIdtipooperacion()==reporteDM.getTipooperacion()){
					params.put("tipooperacion", tipoOp.getNombre());
				}
			}
		}else{
			params.put("tipooperacion", null);
		}
		
		if (reporteDM.getEstado()>0){
			for (Estado estado:getEstados()){
				if (estado.getIdestado()==reporteDM.getEstado()){
					params.put("est", estado.getNombre());
				}
			}
			
		}else{
			params.put("est", null);
		}
		
		if (reporteDM.getTecnico()>0){
			for (Persona persona:getTecnico()){
				if (persona.getIdpersona()==reporteDM.getTecnico()){
					params.put("tecnico", (persona.getNombre1() + " " + persona.getNombre2() + " " + persona.getApellido1() + " " + persona.getApellido2()));
				}
			}
			
		}else{
			params.put("tecnico", null);
		}
		
		if (reporteDM.getNodo()>0){
			for (Nodos nodo:getNodos()){
				if (nodo.getIdnodo()==reporteDM.getNodo()){
					params.put("nodo", reporteDM.getNodoNombre());
				}
			}
		}else{
			params.put("nodo", null);
		}
		
		
		
		
		params.put("usuario", usuarioBean.getUsuario().getNombre());
		report.setParams(params);
		
		
		
	}
	
	
	public JrHM getReport() {
		return report;
	}

	public void setReport(JrHM report) {
		this.report = report;
	}

	public List<Tipooperacion> getToperaciones(){
		return iTipoOperacionServices.getAll();
	}
	public List<Nodos> getNodos(){
		return nodosservices.getAllNodos();
	}
	
	
	
	public List<Estado> getEstados(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session.createQuery("from Estado").list();
		
	}
	public List<Persona> getTecnico(){
		return personaServices.getListaPersonasbyTipo(TipoPersona.TEC);
		
	}
	public reporteOrdenesDM getReporteDM() {
		return reporteDM;
	}
	public void setReporteDM(reporteOrdenesDM reporteDM) {
		this.reporteDM = reporteDM;
	}
	

}
