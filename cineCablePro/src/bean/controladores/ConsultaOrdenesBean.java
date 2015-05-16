package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Empresa;
import pojo.annotations.Tipooperacion;

import util.FacesUtil;
import util.MessageUtil;
import bo.negocio.CtaclienteBO;
import bo.negocio.ImpoOrdenesBO;
import bo.negocio.TipooperacionBO;

import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.OrdenesAsignaciones;

@ManagedBean
@ViewScoped
public class ConsultaOrdenesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1327902446024107923L;

	// Ids por url
	private int idcuenta;

	private Date fechaDesde;
	private Date fechaHasta;
	private List<Ordenes> lisOrdenes;
	//private Ordenes ordenSelected;
	private List<Tipooperacion> lisTipoOrden;
	private LazyDataModel<OrdenesAsignaciones> lisOrdenesAsignaciones;
	private Tipooperacion tipoOrdenSelected;
	private Ctacliente ctacliente;
	private OrdenesAsignaciones ordenesAsignacionesSelected;

	public ConsultaOrdenesBean() {
		 fechaDesde=new Date();
		 fechaHasta =new Date();
		 ctacliente = new Ctacliente(0, new Empresa(), new Clientes());
		 ordenesAsignacionesSelected = new OrdenesAsignaciones();
		 ordenesAsignacionesSelected.setOrden(new Ordenes());

		lisTipoOrden = new ArrayList<Tipooperacion>();
		//consultarOrdenes();
		consultaTipoOrdenes();
	}
	
	@PostConstruct
	public void PostConsultaOrdenesBean(){
		FacesUtil facesUtil = new FacesUtil();
		idcuenta = Integer
				.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
						.getParametroUrl("idcuenta").toString() : "0");

		if (idcuenta > 0) {
			try{
				//Al recibir por parametro el idcuenta, consultamos
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
				
				consultarOrdenes();
			}
			catch(Exception re){
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!","");
			}
		}
	}

	public void validaFecha() {
		if (fechaDesde != null && fechaHasta != null){
			if (fechaHasta.before(fechaDesde)) {
				new MessageUtil().showWarnMessage("Error en Rango",
						"Fechas ingresadas no son correctas!");
			}
		}
	}
	
	@SuppressWarnings("serial")
	public void consultarOrdenes() {
		try {
			lisOrdenesAsignaciones = new LazyDataModel<OrdenesAsignaciones>() {

				@Override
				public List<OrdenesAsignaciones> load(int first, int pageSize,
						String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					List<OrdenesAsignaciones> data = new ArrayList<OrdenesAsignaciones>();

					// Si no hay filtros que no consulte
					
					if ((fechaDesde != null && fechaHasta != null)
							|| (tipoOrdenSelected != null && tipoOrdenSelected
									.getIdtipooperacion() > 0)) {
						if (fechaHasta.before(fechaDesde)) {
							new MessageUtil().showWarnMessage("Error en Rango",
									"Fechas ingresadas no son correctas!");
						}
						if (idcuenta > 0) {
							ImpoOrdenesBO impoOrdenesBO = new ImpoOrdenesBO();
							int args[] = { 0 };
							/*data = impoOrdenesBO.lisOrdenesAsignaciones(
									pageSize, first, args, null, null,
									fechaDesde, fechaHasta, 0,
									tipoOrdenSelected.getIdtipooperacion(), 0,
									0, idcuenta);*/
							//lisOrdenesAsignaciones(int pageSize, int pageNumber, int args[], String identificacion, Date fSolicitud, Date fDesde,Date fHasta, int idsector, int idtipooperacion, int idestado, int idtecnico,int idcuenta) throws RuntimeException {
							data = impoOrdenesBO.lisOrdenesAsignaciones(tipoOrdenSelected.getIdtipooperacion(), 0, 0, null,
									fechaDesde, fechaHasta, pageSize, first, args);

							this.setRowCount(args[0]);
						}

					}
					return data;
				}

				@Override
				public void setRowIndex(int rowIndex) {
					/*
					 * The following is in ancestor (LazyDataModel):
					 * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex %
					 * pageSize);
					 */
					if (rowIndex == -1 || getPageSize() == 0) {
						super.setRowIndex(-1);
					} else {
						super.setRowIndex(rowIndex % getPageSize());
					}
				}
			};
		} catch (Exception re) {
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!",
					"Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}

	}

	public void consultaTipoOrdenes() {
		try {
			tipoOrdenSelected = new Tipooperacion(0, null, 0, 0, 0, null);
			
			// TipoOperacion
			Tipooperacion tipooperacion = new Tipooperacion();
			tipooperacion.setIdtipooperacion(0);
			tipooperacion.setNombre("Todas ...");

			tipoOrdenSelected.setIdtipooperacion(0);
				
			lisTipoOrden = new ArrayList<Tipooperacion>();
			lisTipoOrden.add(tipooperacion);

			TipooperacionBO tipooperacionBO = new TipooperacionBO();

			List<Tipooperacion> lisTOperacionTmp = tipooperacionBO
					.TipooperacionPorId();
			if (lisTOperacionTmp != null && lisTOperacionTmp.size() > 0) {
				lisTipoOrden.addAll(lisTOperacionTmp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage(
					"Ha ocurrido un error inesperado. Comunicar al Webmaster!",
					"");
		}
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

	public List<Ordenes> getLisOrdenes() {
		return lisOrdenes;
	}

	public void setLisOrdenes(List<Ordenes> lisOrdenes) {
		this.lisOrdenes = lisOrdenes;
	}

	/*public Ordenes getOrdenSelected() {
		return ordenSelected;
	}

	public void setOrdenSelected(Ordenes ordenSelected) {
		this.ordenSelected = ordenSelected;
	}*/

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public LazyDataModel<OrdenesAsignaciones> getLisOrdenesAsignaciones() {
		return lisOrdenesAsignaciones;
	}

	public void setLisOrdenesAsignaciones(
			LazyDataModel<OrdenesAsignaciones> lisOrdenesAsignaciones) {
		this.lisOrdenesAsignaciones = lisOrdenesAsignaciones;
	}

	public List<Tipooperacion> getLisTipoOrden() {
		return lisTipoOrden;
	}

	public void setLisTipoOrden(List<Tipooperacion> lisTipoOrden) {
		this.lisTipoOrden = lisTipoOrden;
	}

	public Tipooperacion getTipoOrdenSelected() {
		return tipoOrdenSelected;
	}

	public void setTipoOrdenSelected(Tipooperacion tipoOrdenSelected) {
		this.tipoOrdenSelected = tipoOrdenSelected;
	}

	public Ctacliente getCtacliente() {
		return ctacliente;
	}

	public void setCtacliente(Ctacliente ctacliente) {
		this.ctacliente = ctacliente;
	}

	public OrdenesAsignaciones getOrdenesAsignacionesSelected() {
		return ordenesAsignacionesSelected;
	}

	public void setOrdenesAsignacionesSelected(
			OrdenesAsignaciones ordenesAsignacionesSelected) {
		this.ordenesAsignacionesSelected = ordenesAsignacionesSelected;
	}

}
