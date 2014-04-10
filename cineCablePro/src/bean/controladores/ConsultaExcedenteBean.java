package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.negocio.BancosBO;
import bo.negocio.ExcedentesBO;
import bo.negocio.FpagoBO;

import pojo.annotations.Bancos;
import pojo.annotations.Excedentes;
import pojo.annotations.Fpago;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class ConsultaExcedenteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5846093440322075462L;

	//Ids por url
	private int idcuenta;
	
	//Otros
	private Date fechaDesde;
	private Date fechaHasta;
	private Excedentes excedenteSeleccionado;
	private List<Excedentes> lisExcedentes;
	private Fpago fpago;
	private Bancos bancos;
	private Bancos bancosTar;
	
	public ConsultaExcedenteBean() {
		fechaDesde = new Date();
		fechaHasta = new Date();
		excedenteSeleccionado = new Excedentes();
		lisExcedentes = new ArrayList<Excedentes>();
		fpago = new Fpago();
		bancos = new Bancos();
		bancosTar = new Bancos();
	}
	
	public void consultarExcedente(){
		if(validacionOk()){
			try{
				ExcedentesBO excedentesBO = new ExcedentesBO();
				lisExcedentes = excedentesBO.lisExcedentesByFechas(idcuenta, fechaDesde, fechaHasta);
				
				if(lisExcedentes == null || lisExcedentes.size() == 0){
					excedenteSeleccionado = new Excedentes(); 
					new MessageUtil().showInfoMessage("Sin datos", "No se han encontrado datos con los parámetros de consulta");
				}
			}catch(Exception e){
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}
	
	public void consultaReferenciales(){
		try{
			FpagoBO fpagoBO = new FpagoBO();
			fpago = fpagoBO.getFpagoById(excedenteSeleccionado.getIdfpago());
			
			BancosBO bancosBO = new BancosBO();
			bancos = bancosBO.getBancosById(excedenteSeleccionado.getIdbcoemisor());
			
			bancosTar = bancosBO.getBancosById(excedenteSeleccionado.getIdbcoemisortar());
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
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

	public Excedentes getExcedenteSeleccionado() {
		return excedenteSeleccionado;
	}

	public void setExcedenteSeleccionado(Excedentes excedenteSeleccionado) {
		this.excedenteSeleccionado = excedenteSeleccionado;
	}

	public List<Excedentes> getLisExcedentes() {
		return lisExcedentes;
	}

	public void setLisExcedentes(List<Excedentes> lisExcedentes) {
		this.lisExcedentes = lisExcedentes;
	}

	public Fpago getFpago() {
		return fpago;
	}

	public void setFpago(Fpago fpago) {
		this.fpago = fpago;
	}

	public Bancos getBancos() {
		return bancos;
	}

	public void setBancos(Bancos bancos) {
		this.bancos = bancos;
	}

	public Bancos getBancosTar() {
		return bancosTar;
	}

	public void setBancosTar(Bancos bancosTar) {
		this.bancosTar = bancosTar;
	}
}
