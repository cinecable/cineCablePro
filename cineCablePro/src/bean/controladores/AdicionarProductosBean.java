package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.dao.IMotivosDao;
import net.cinecable.enums.TipoSolicitudes;
import net.cinecable.model.base.Ordenes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Ctasprod;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Motivos;
import pojo.annotations.Tipooperacion;
import pojo.annotations.Usuario;

import util.FacesUtil;
import util.MessageUtil;
import bo.negocio.CtasprodBO;

@ManagedBean
@ViewScoped
public class AdicionarProductosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2966559444716178868L;
	
	@ManagedProperty(value="#{productosBean}") 
	private ProductosBean productosBean;
	
	private int idcuenta;
	
	@ManagedProperty(value = "#{calendarOrdenesBean}")
	private CalendarOrdenesBean calendarOrdenesBean;
	private int mes;
	private int anio;
	private Ordenes ordenes;
	private List<Motivos> lisMotivosHorarios;
	@EJB
	private IMotivosDao iMotivoDao;
	
	public AdicionarProductosBean() {
		idcuenta = 0;
		mes = 0;
		anio = Calendar.getInstance().get(Calendar.YEAR);
		ordenes = new Ordenes();
		ordenes.setCuentaCliente(new Ctacliente());
		ordenes.setEmpresa(new Empresa());
		ordenes.setEstado(new Estado());
		ordenes.setHorario(new Motivos());
		ordenes.setMotivo(new Motivos());
		ordenes.setProducto(new Ctasprod());
		ordenes.setTipoOperacion(new Tipooperacion());
		ordenes.setUsuario(new Usuario());
		lisMotivosHorarios = new ArrayList<Motivos>();
	}
	
	@PostConstruct
	public void initAdicionarProductosBean() {
		calendarOrdenesBean.inicializarAnios();
		calendarOrdenesBean.inicializarMeses();
		llenarHorarios();
		FacesUtil facesUtil = new FacesUtil();
		idcuenta = Integer
				.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
						.getParametroUrl("idcuenta").toString() : "0");

		if (idcuenta > 0) {
			//consultarProductos();
		}
	}
	
	public void muestraCalendario(){
		if(mes > 0){
			//Si he seleccionado un mes, busco fechas del mes y muestro calendario
			Calendar fecha = Calendar.getInstance();
			fecha.set(anio,mes-1,1,0,0,0);
			calendarOrdenesBean.setFecha(fecha.getTime());
			
			calendarOrdenesBean.mostrarEventosAgendados(TipoSolicitudes.InstNueva.getDescripcion(), mes);
			
			calendarOrdenesBean.setMostrarCalendar(true);
		}else{
			//si no he seleccionado mes oculto calendario
			calendarOrdenesBean.setMostrarCalendar(false);
			calendarOrdenesBean.setFecha(null);
		}
	}
	
	public void llenarHorarios() {
		lisMotivosHorarios = new ArrayList<Motivos>();
		lisMotivosHorarios = iMotivoDao.getMotivosByTipoOperacion(TipoSolicitudes.Horarios.getDescripcion());
	}

	public void grabar(){
		if(validacionProductoOk()){
			if(validacionOrden()){
				try{
					CtasprodBO ctasprodBO = new CtasprodBO();
					
					//orden
					ordenes.setFechaEjecucion(calendarOrdenesBean.getFecha());
					
					if(ordenes.getCuentaCliente() != null && ordenes.getCuentaCliente().getIdcuenta() == 0){
						ordenes.setCuentaCliente(null);
					}
					
					boolean ok = ctasprodBO.grabarProductos(productosBean.getIdcuenta(), productosBean.getLisProductosId(), productosBean.getLisProductosIdClon(), ordenes);
					
					if(ok){
						FacesUtil facesUtil = new FacesUtil();
						try {
							facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta="+idcuenta);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						new MessageUtil().showInfoMessage("No existen cambios que guardar", "");
					}
				}catch(Exception e){
					e.printStackTrace();
					new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
				}
			}
		}
	}

	private boolean validacionProductoOk(){
		boolean ok = false;
		
		if(productosBean.getLisProductosId() != null && productosBean.getLisProductosId().size() > 0){
			ok = true;
		}else{
			new MessageUtil().showWarnMessage("Debe seleccionar al menos un producto en seccion Productos Cliente", "");
		}
		
		return ok;
	}
	
	private boolean validacionOrden(){
		boolean ok = false;
		
		if(calendarOrdenesBean.getFecha() != null){
			ok = true;
		}else{
			new MessageUtil().showWarnMessage("Debe seleccionar la fecha de Instalacion en seccion Ordenes de Instalacion", null);
		}
		
		if(ok && ordenes.getHorario().getIdmotivo() > 0){
			ok = true;
		}else{
			ok = false;
			new MessageUtil().showWarnMessage("Debe seleccionar el horario en seccion Ordenes de Instalacion", null);
		}
		
		return ok;
	}
	
	public ProductosBean getProductosBean() {
		return productosBean;
	}

	public void setProductosBean(ProductosBean productosBean) {
		this.productosBean = productosBean;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public CalendarOrdenesBean getCalendarOrdenesBean() {
		return calendarOrdenesBean;
	}

	public void setCalendarOrdenesBean(CalendarOrdenesBean calendarOrdenesBean) {
		this.calendarOrdenesBean = calendarOrdenesBean;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Ordenes getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(Ordenes ordenes) {
		this.ordenes = ordenes;
	}

	public List<Motivos> getLisMotivosHorarios() {
		return lisMotivosHorarios;
	}

	public void setLisMotivosHorarios(List<Motivos> lisMotivosHorarios) {
		this.lisMotivosHorarios = lisMotivosHorarios;
	}
}
