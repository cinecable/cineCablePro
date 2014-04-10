package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.negocio.CreditosBO;
import bo.negocio.MotivosBO;

import pojo.annotations.Creditos;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Motivos;
import pojo.annotations.Usuario;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class ConsultaCreditoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9107329228088624420L;

	//Ids por url
	private int idcuenta;
	
	//Otros
	private Date fechaDesde;
	private Date fechaHasta;
	private Creditos creditoSeleccionado;
	private List<Creditos> lisCreditos;
	private Motivos motivos;
	
	public ConsultaCreditoBean() {
		fechaDesde = new Date();
		fechaHasta = new Date();
		creditoSeleccionado = new Creditos(0, new Estado(), new Usuario(), new Empresa(), new Motivos(), "", 0, null, 0, 0);
		lisCreditos = new ArrayList<Creditos>();
		motivos = new Motivos();
	}
	
	public void consultarCredito(){
		if(validacionOk()){
			try{
				CreditosBO creditosBO = new CreditosBO();
				lisCreditos = creditosBO.lisCreditosByFechas(idcuenta, fechaDesde, fechaHasta);
				
				if(lisCreditos == null || lisCreditos.size() == 0){
					new MessageUtil().showInfoMessage("Sin datos", "No se han encontrado datos con los parámetros de consulta");
				}
			}catch(Exception e){
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}

	private boolean validacionOk(){
		boolean ok = false;
		
		if(fechaDesde != null){
			if(fechaHasta != null){
				ok = true;
			}else{
				new MessageUtil().showInfoMessage("Campo requerido", "Favor ingrese la fecha hasta");
			}
		}else{
			new MessageUtil().showInfoMessage("Campo requerido", "Favor ingrese la fecha desde");
		}
		
		if(ok){
			if(fechaDesde.compareTo(fechaHasta) > 0){
				ok = false;
				new MessageUtil().showInfoMessage("Corregir", "La fecha desde no puede ser mayor que la fecha hasta");
			}
		}
		
		return ok;
	}
	
	public void consultaReferenciales(){
		try{
			MotivosBO motivosBO = new MotivosBO();
			motivos = motivosBO.getMotivosById(creditoSeleccionado.getMotivos().getIdmotivo());
			
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}
	
	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Creditos getCreditoSeleccionado() {
		return creditoSeleccionado;
	}

	public void setCreditoSeleccionado(Creditos creditoSeleccionado) {
		this.creditoSeleccionado = creditoSeleccionado;
	}

	public List<Creditos> getLisCreditos() {
		return lisCreditos;
	}

	public void setLisCreditos(List<Creditos> lisCreditos) {
		this.lisCreditos = lisCreditos;
	}

	public Motivos getMotivos() {
		return motivos;
	}

	public void setMotivos(Motivos motivos) {
		this.motivos = motivos;
	}
}
