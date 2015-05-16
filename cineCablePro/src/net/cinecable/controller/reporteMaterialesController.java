package net.cinecable.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.dao.ITipoMaterialDao;
import net.cinecable.dm.reporteMaterialesDM;
import net.cinecable.enums.Estados;
import net.cinecable.exception.EntidadNoEncontradaException;
import net.cinecable.hm.JrHM;
import net.cinecable.model.base.TipoMaterial;

import org.primefaces.context.RequestContext;

import util.FacesUtil;
import bean.controladores.UsuarioBean;

@ManagedBean(name="reporteMaterialesController")
@ViewScoped
public class reporteMaterialesController {
	@ManagedProperty(value="#{reporteMaterialesDM}")
	private reporteMaterialesDM reporteDM;
	@EJB
	private ITipoMaterialDao itipodao;
	@ManagedProperty(value = "#{jrreport}")
	JrHM report;
	@PostConstruct
	public void init(){
		reporteDM.setFechaDesde(new Date());
		reporteDM.setFechaHasta(new Date());
		reporteDM.setTipoMaterial(new TipoMaterial());
		reporteDM.setListaMaterial(new ArrayList<TipoMaterial>());
		
	}
	
	
	public void agregaMateriales(){
		boolean encuentro=false;
		if (reporteDM.getListaMaterial().size()==10){
			RequestContext.getCurrentInstance().execute("alert('Solo se puede escoger un maximo de 10 materiales');");
			return;
		}
		if(reporteDM.getTipoMaterial().getIdTipMaterial()!=0){
			for (TipoMaterial material:reporteDM.getListaMaterial()){
				if(material.getIdTipMaterial()==reporteDM.getTipoMaterial().getIdTipMaterial())
					encuentro=true;
			}
			try {
				if(encuentro){
					RequestContext.getCurrentInstance().execute("alert('Este tipo de material ya fue seleccionado');");
					return;
				}
				
				reporteDM.getListaMaterial().add(itipodao.recuperar(reporteDM.getTipoMaterial().getIdTipMaterial()));
			} catch (EntidadNoEncontradaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void eliminar(Long idMaterial){
		for (int i=0;i<reporteDM.getListaMaterial().size();i++){
			if (reporteDM.getListaMaterial().get(i).getIdTipMaterial()==idMaterial){
				reporteDM.getListaMaterial().remove(i);
				break;
				
			}
		}
		
	}
	public void showReport() {
		if (reporteDM.getListaMaterial().isEmpty()){
			RequestContext.getCurrentInstance().execute("alert('Seleccione al menos un material');");
			return;
		}
		FacesUtil faces = new FacesUtil();
		UsuarioBean usuarioBean =null;
		if (faces.getSessionBean("usuarioBean") != null) {
			usuarioBean = (UsuarioBean) faces.getSessionBean("usuarioBean");
		}
		if (usuarioBean==null){
			return;
		}
		report.setReportFile("materiales.jasper");
		Map<String, Object> params = new HashMap<String, Object>();
		if (reporteDM.getFechaDesde()!=null) params.put("fechadesde", new SimpleDateFormat("yyyy-MM-dd").format(reporteDM.getFechaDesde())); else params.put("fechadesde", null);
		if (reporteDM.getFechaHasta()!=null) params.put("fechaHasta", new SimpleDateFormat("yyyy-MM-dd").format(reporteDM.getFechaHasta())); else params.put("fechaHasta", null);
		params.put("usuario", usuarioBean.getUsuario().getNombre());
		if (reporteDM.getListaMaterial().isEmpty()){
			params.put("tipMaterial", null);
		}else{
			String cadena="";
			for (int i=0;i<reporteDM.getListaMaterial().size();i++){
				if (i+1==reporteDM.getListaMaterial().size()){
					cadena+=reporteDM.getListaMaterial().get(i).getIdTipMaterial();
				}else{
					cadena+=reporteDM.getListaMaterial().get(i).getIdTipMaterial() + ",";
				}
			}
			params.put("tipMaterial", cadena);
			
		}
			
		report.setParams(params);
		
	}
	
	
	public List<TipoMaterial> getMateriales(){
		return itipodao.getAllTipoMaterial(Estados.ACTIVO);
	}
	
	public JrHM getReport() {
		return report;
	}

	public void setReport(JrHM report) {
		this.report = report;
	}

	

	public reporteMaterialesDM getReporteDM() {
		return reporteDM;
	}

	public void setReporteDM(reporteMaterialesDM reporteDM) {
		this.reporteDM = reporteDM;
	}

	
	
	

}
