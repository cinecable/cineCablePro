package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.enums.TipoPersona;
import net.cinecable.hm.JrHM;
import net.cinecable.model.base.OrdenesAsignaciones;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import bo.negocio.EmpresaBO;
import bo.negocio.ImpoOrdenesBO;
import bo.negocio.PersonaBO;
import bo.negocio.SectorBO;
import bo.negocio.TipooperacionBO;

import pojo.annotations.Empresa;
import pojo.annotations.Persona;
import pojo.annotations.Sector;
import pojo.annotations.Tipooperacion;
import util.FacesUtil;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class ImpOrdenesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9151078910487784781L;
	private List<Tipooperacion> lisTOperacion;
	private Tipooperacion TOperacionSelected;
	private Persona personaSelected;
	private List<Persona> lisPersona;
	private Sector sectorSelected;
	private List<Sector> lisSector;
	private Date fSolicitud;
	private Date fDesde;
	private Date fHasta;
	private String idClientes;
	private LazyDataModel<OrdenesAsignaciones> lisOrdenesAsignaciones;
	private OrdenesAsignaciones OrdenesAsignacionesSelected;
	private int idtecnico;
	@ManagedProperty(value = "#{jrreport}")
	JrHM report;
	private boolean isconsultaejecutada;
	List<OrdenesAsignaciones> data;

	public LazyDataModel<OrdenesAsignaciones> getLisOrdenesAsignaciones() {
		return lisOrdenesAsignaciones;
	}

	public void setLisOrdenesAsignaciones(
			LazyDataModel<OrdenesAsignaciones> lisOrdenesAsignaciones) {
		this.lisOrdenesAsignaciones = lisOrdenesAsignaciones;
	}

	public ImpOrdenesBean() {

		// TODO Auto-generated constructor stub
		lisTOperacion = new ArrayList<Tipooperacion>();
		lisPersona = new ArrayList<Persona>();
		lisSector = new ArrayList<Sector>();
		consultatOperaciones();
		consultarOrdenes();
		consultaPersonas();
		consultaSectores();
	}

	public void consultatOperaciones() {
		try {
			TOperacionSelected = new Tipooperacion(0, null, 0, 0, 0, null);
			// TipoOperacion
			Tipooperacion tipooperacion = new Tipooperacion();
			tipooperacion.setIdtipooperacion(0);
			tipooperacion.setNombre("Todas ...");

			lisTOperacion = new ArrayList<Tipooperacion>();
			lisTOperacion.add(tipooperacion);

			TipooperacionBO tipooperacionBO = new TipooperacionBO();

			List<Tipooperacion> lisTOperacionTmp = tipooperacionBO
					.TipooperacionPorId();
			if (lisTOperacionTmp != null && lisTOperacionTmp.size() > 0) {
				lisTOperacion.addAll(lisTOperacionTmp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage(
					"Ha ocurrido un error inesperado. Comunicar al Webmaster!",
					"");
		}
	}

	public void consultaPersonas() {
		try {
			personaSelected = new Persona();
			// Personas Tecnicos
			Persona tecnicos = new Persona();
			tecnicos.setIdpersona(0);
			tecnicos.setApellido1("Seleccione Operacion...");

			lisPersona = new ArrayList<Persona>();
			lisPersona.add(tecnicos);

			PersonaBO personaBO = new PersonaBO();

			List<Persona> lisPersonaTmp = personaBO
					.getListaPersonasbyCargo(TipoPersona.TEC);
			if (lisPersonaTmp != null && lisPersonaTmp.size() > 0) {
				lisPersona.addAll(lisPersonaTmp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage(
					"Ha ocurrido un error inesperado. Comunicar al Webmaster!",
					"");
		}
	}

	
	public void consultaSectores() {
		
		sectorSelected = new Sector();
		UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
        int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
		try {
			
			int idCiudad=0;
			EmpresaBO EmpresaBO = new EmpresaBO();
            List<Empresa> lisTmp = EmpresaBO.consultarEmpresasId(idempresa);
            for( Empresa empresaReg : lisTmp){
            	 idCiudad = empresaReg.getIdciudad();
            }
			
			// Sectores
			Sector sector = new Sector();
			sector.setIdsector(0);
			sector.setNombre("Seleccione Sector...");

			lisSector = new ArrayList<Sector>();
			lisSector.add(sector);

			SectorBO sectorBO = new SectorBO();
			
			List<Sector> lisSectorTmp = sectorBO.SectorxCiudad(idCiudad);
			if (lisSectorTmp != null && lisSectorTmp.size() > 0) {
				lisSector.addAll(lisSectorTmp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage(
					"Ha ocurrido un error inesperado. Comunicar al Webmaster!",
					"");
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
					/*List<OrdenesAsignaciones>*/ data = new ArrayList<OrdenesAsignaciones>();

					if(validacion()){
						// Si no hay filtros que no consulte
						if (isconsultaejecutada || 
								TOperacionSelected.getIdtipooperacion() > 0 ||
								personaSelected.getIdpersona() > 0 || 
								(idClientes != null && idClientes.length() > 0) || 
								sectorSelected.getIdsector() > 0 ||
								(fDesde != null && fHasta != null)) {
							ImpoOrdenesBO impoOrdenesBO = new ImpoOrdenesBO();
							int args[] = { 0 };
							// lisCtaclienteByPage(pageSize, first, args, nombre1,
							// nombre2, apellido1, apellido2, numeroIdentificacion,
							// empresa);
							//lisOrdenesAsignaciones(int idtipooperacion, int idtecnico, int idsector, String identificacion, Date fDesde, Date fHasta, int pageSize, int pageNumber, int[] args)
							data = impoOrdenesBO.lisOrdenesAsignaciones(TOperacionSelected.getIdtipooperacion(),
									personaSelected.getIdpersona(),
									sectorSelected.getIdsector(),
									idClientes,
									fDesde,
									fHasta,
									pageSize,
									first, args);
							
							this.setRowCount(args[0]);
							
							//isconsultaejecutada = false;
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
	
	private boolean validacion(){
		boolean ok = false;
		
		if((fDesde != null && fHasta != null) || (fDesde == null && fHasta == null)){
			ok = true;
		}else{
			new MessageUtil().showWarnMessage("Si desea filtrar por fechas, ambas son obligatorias","");
		}
		
		return ok;
	}

	public void onRowSelect(SelectEvent event) {
		FacesUtil facesUtil = new FacesUtil();
		try {
			facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta="
					+ OrdenesAsignacionesSelected.getOrden().getCuentaCliente()
							.getIdcuenta());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void imprimir() {
		boolean ok = false;

		if(data != null && data.size() > 0 ){
			// update
			try {
				ImpoOrdenesBO impoOrdenesBO = new ImpoOrdenesBO();
	
				// actualiza estados
				ok = impoOrdenesBO
						.actualizarEstadoOrdenes(data);
				if (!ok) {
					new MessageUtil().showWarnMessage(
							"Ha ocurrido un error al imprimir", "");
				}
	
				// imprimir
				if (ok) {
					try {
						report.setReportFile("rptvisitatecnica.jasper");
	
						Map<String, Object> parametros = new HashMap<String, Object>();
	
						if(personaSelected.getIdpersona() > 0){
							parametros.put("p_idtecnico", personaSelected.getIdpersona());
						}
						//parametros.put("p_idestado", 0);
						if(sectorSelected.getIdsector() > 0){
							parametros.put("p_idsector", sectorSelected.getIdsector());
						}
						if(TOperacionSelected.getIdtipooperacion() > 0){
							parametros.put("p_idtipooperacion", TOperacionSelected.getIdtipooperacion());
						}
						if(idClientes  != null && idClientes.trim().length() > 0){
							parametros.put("p_identificacion", idClientes);
						}
						parametros.put("p_fSolicitud", fSolicitud);
						parametros.put("p_fDesde", fDesde);
						parametros.put("p_fHasta", fHasta);
	
						report.setParams(parametros);
					} catch (Exception e) {
						e.printStackTrace();
						new MessageUtil()
								.showFatalMessage("Esto es Vergonzoso!",
										"Ha ocurrido un error inesperado. Comunicar al Webmaster!");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				new MessageUtil().showFatalMessage(
						"Ha ocurrido un error inesperado.", "");
			}
		}else{
			new MessageUtil().showWarnMessage("Debe ejecutar una consulta a imprimir", "");
		}
	}

	public List<Tipooperacion> getLisTOperacion() {
		return lisTOperacion;
	}

	public void setLisTOperacion(List<Tipooperacion> lisTOperacion) {
		this.lisTOperacion = lisTOperacion;
	}

	public Tipooperacion getTOperacionSelected() {
		return TOperacionSelected;
	}

	public void setTOperacionSelected(Tipooperacion tOperacionSelected) {
		TOperacionSelected = tOperacionSelected;
	}

	public int getIdtecnico() {
		return idtecnico;
	}

	public void setIdtecnico(int idtecnico) {
		this.idtecnico = idtecnico;
	}

	public Persona getPersonaSelected() {
		return personaSelected;
	}

	public void setPersonaSelected(Persona personaSelected) {
		this.personaSelected = personaSelected;
	}

	public List<Persona> getLisPersona() {
		return lisPersona;
	}

	public void setLisPersona(List<Persona> lisPersona) {
		this.lisPersona = lisPersona;
	}

	public Sector getSectorSelected() {
		return sectorSelected;
	}

	public void setSectorSelected(Sector sectorSelected) {
		this.sectorSelected = sectorSelected;
	}

	public List<Sector> getLisSector() {
		return lisSector;
	}

	public void setLisSector(List<Sector> lisSector) {
		this.lisSector = lisSector;
	}

	public Date getfSolicitud() {
		return fSolicitud;
	}

	public void setfSolicitud(Date fSolicitud) {
		this.fSolicitud = fSolicitud;
	}

	public Date getfDesde() {
		return fDesde;
	}

	public void setfDesde(Date fDesde) {
		this.fDesde = fDesde;
	}

	public Date getfHasta() {
		return fHasta;
	}

	public void setfHasta(Date fHasta) {
		this.fHasta = fHasta;
	}

	public String getIdClientes() {
		return idClientes;
	}

	public void setIdClientes(String idClientes) {
		this.idClientes = idClientes;
	}

	public JrHM getReport() {
		return report;
	}

	public void setReport(JrHM report) {
		this.report = report;
	}

	public boolean isIsconsultaejecutada() {
		return isconsultaejecutada;
	}

	public void setIsconsultaejecutada(boolean isconsultaejecutada) {
		this.isconsultaejecutada = isconsultaejecutada;
	}

	public OrdenesAsignaciones getOrdenesAsignacionesSelected() {
		return OrdenesAsignacionesSelected;
	}

	public void setOrdenesAsignacionesSelected(
			OrdenesAsignaciones ordenesAsignacionesSelected) {
		OrdenesAsignacionesSelected = ordenesAsignacionesSelected;
	}

}
