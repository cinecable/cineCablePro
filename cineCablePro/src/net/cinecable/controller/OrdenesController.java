package net.cinecable.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.dao.IAsignacionSolucitudDao;
import net.cinecable.dao.IClienteDao;
import net.cinecable.dao.ICtaClienteDao;
import net.cinecable.dao.IMotivosDao;
import net.cinecable.dao.IOrdenesDao;
import net.cinecable.dao.IctasProductoDao;
import net.cinecable.dm.ingOrdenesDM;
import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoSolicitudes;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.ParamAsignacionOrden;
import net.cinecable.service.ITipoOperacionService;

import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;

import pojo.annotations.Calleprincipal;
import pojo.annotations.Callesecundaria;
import pojo.annotations.Ciudad;
import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Ctasprod;
import pojo.annotations.Direccion;
import pojo.annotations.Edificio;
import pojo.annotations.Motivos;
import pojo.annotations.Nodos;
import pojo.annotations.Pais;
import pojo.annotations.Provincia;
import pojo.annotations.Referenciadir;
import pojo.annotations.Sector;
import pojo.annotations.Tipooperacion;
import pojo.annotations.Tiposector;
import pojo.annotations.Ubicacion;
import util.FacesUtil;
import util.HibernateUtil;
import util.MessageUtil;
import bean.controladores.CalendarOrdenesBean;
import bean.controladores.UsuarioBean;
import bo.negocio.CallePrincipalBO;
import bo.negocio.CallesecundariaBO;
import bo.negocio.CiudadBO;
import bo.negocio.CtaclienteBO;
import bo.negocio.EdificiosBO;
import bo.negocio.NodosBO;
import bo.negocio.PaisBO;
import bo.negocio.ProvinciaBO;
import bo.negocio.SectorBO;
import bo.negocio.TipoSectorBO;
import bo.negocio.UbicacionBO;
import dao.datos.CiudadDAO;

@ManagedBean(name = "ordenesController")
@ViewScoped
public class OrdenesController implements Serializable {

	private static final long serialVersionUID = 116793638423244514L;
	
	@EJB
	private IClienteDao iclienteDao;
	@ManagedProperty(value = "#{ingOrdenesDM}")
	private ingOrdenesDM ingOrdenesDM;

	@EJB
	private ICtaClienteDao iCtaClienteDao;
	@EJB
	private ITipoOperacionService iTipoOperacionServices;
	@EJB
	private IMotivosDao iMotivoDao;
	@EJB
	private IOrdenesDao iOrdenesDao;
	@EJB
	private IctasProductoDao iCtasProductoDao;
	@EJB
	private IAsignacionSolucitudDao iAsignacion;

	private FacesUtil faces;
	private UsuarioBean usuarioBean;
	public boolean cancelacion = false;
	public boolean direccion = false;

	private int idcuenta;
	private Ctacliente ctacliente;
	private boolean bandcliente;
	private String strSpecialDates = "2015/01/09,2015/01/10";
	private Calendar primeraFechaMes;
	private Calendar ultimaFechaMes;
	private boolean tomaPrimeraFecha = true;
	@ManagedProperty(value = "#{calendarOrdenesBean}")
	private CalendarOrdenesBean calendarOrdenesBean;
	private int mes;
	private int anio;
	private boolean mostrar;
	
	public OrdenesController() {
		mes = 0;
		anio = Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public void muestraCalendario(){
		if(mes > 0){
			//Si he seleccionado un mes, busco fechas del mes y muestro calendario
			Calendar fecha = Calendar.getInstance();
			fecha.set(anio,mes-1,1,0,0,0);
			calendarOrdenesBean.setFecha(fecha.getTime());
			
			calendarOrdenesBean.mostrarEventosAgendados(ingOrdenesDM.getOrden().getTipoOperacion().getIdtipooperacion(), mes);
			
			calendarOrdenesBean.setMostrarCalendar(true);
		}else{
			//se no he seleccionado mes oculto calendario
			calendarOrdenesBean.setMostrarCalendar(false);
		}
	}
	
	@PostConstruct
	public void init() throws HibernateException, Exception {
		calendarOrdenesBean.inicializarAnios();
		calendarOrdenesBean.inicializarMeses();
		faces = new FacesUtil();
		if (faces.getSessionBean("usuarioBean") != null) {
			usuarioBean = (UsuarioBean) faces.getSessionBean("usuarioBean");
		}
		bandcliente = false;
		ingOrdenesDM.setCliente(new Clientes());
		ingOrdenesDM.getCliente().setCtaclientes(new ArrayList<Ctacliente>());
		ingOrdenesDM.setDocumento(null);
		ingOrdenesDM.setListaCta(new ArrayList<Ctacliente>());
		ingOrdenesDM.setTipoOperacion(new ArrayList<Tipooperacion>());
		ingOrdenesDM.setOrden(new Ordenes());
		ingOrdenesDM.getOrden().setCuentaCliente(new Ctacliente());
		ingOrdenesDM.getOrden().setTipoOperacion(new Tipooperacion());
		ingOrdenesDM.getOrden().setMotivo(new Motivos());
		ingOrdenesDM.getOrden().setHorario(new Motivos());
		ingOrdenesDM.setMotivos(new ArrayList<Motivos>());
		ingOrdenesDM.setListaCtaProd(new ArrayList<Ctasprod>());
		ingOrdenesDM.setListaciudad(new ArrayList<Ciudad>());
		ingOrdenesDM.setListapais(new ArrayList<Pais>());
		ingOrdenesDM.setListaprovincia(new ArrayList<Provincia>());
		ingOrdenesDM.setPais(new Pais());
		ingOrdenesDM.setCiudad(new Ciudad());
		ingOrdenesDM.setProvincia(new Provincia());
		ingOrdenesDM.setSector(new Sector());
		ingOrdenesDM.setListaSector(new ArrayList<Sector>());
		ingOrdenesDM.setCallePrincipal(new Calleprincipal());
		ingOrdenesDM.setCalleSecundaria(new Callesecundaria());
		ingOrdenesDM.setUbicacion(new Ubicacion());
		ingOrdenesDM.setEdificio(new Edificio());
		ingOrdenesDM.setNodo(new Nodos());
		ingOrdenesDM.setListaEdificio(new ArrayList<Edificio>());
		ingOrdenesDM.setListaNodos(new ArrayList<Nodos>());
		ingOrdenesDM.setListaPrincipal(new ArrayList<Calleprincipal>());
		ingOrdenesDM.setListaSecundaria(new ArrayList<Callesecundaria>());
		ingOrdenesDM.setListaUbicacion(new ArrayList<Ubicacion>());
		ingOrdenesDM.setListaTipoSector(new ArrayList<Tiposector>());
		ingOrdenesDM.setDireccion(new Direccion());
		ingOrdenesDM.getDireccion().setCalleprincipal(new Calleprincipal());
		ingOrdenesDM.getDireccion().setCallesecundaria(new Callesecundaria());
		ingOrdenesDM.getDireccion().setCtacliente(new Ctacliente());
		ingOrdenesDM.getDireccion().setEdificio(new Edificio());
		ingOrdenesDM.getDireccion().setNodos(new Nodos());
		ingOrdenesDM.getDireccion().setSector(new Sector());
		ingOrdenesDM.getDireccion().setUbicacion(new Ubicacion());
		ingOrdenesDM.getDireccion().setTiposector(new Tiposector());
		ingOrdenesDM.getDireccion().setReferenciadir(new Referenciadir());
		ingOrdenesDM.setListadireccioes(new ArrayList<Direccion>());
		ingOrdenesDM.setReferencia(new Referenciadir());

		List<Ciudad> ciudad = new CiudadDAO().lisCiudadByCiudad(HibernateUtil
				.getSessionFactory().openSession(), usuarioBean.getUsuario()
				.getEmpresa().getIdciudad());
		if (!ciudad.isEmpty()) {
			ingOrdenesDM.setCiudad(ciudad.get(0));
			ingOrdenesDM.setProvincia(ciudad.get(0).getProvincia());
			ingOrdenesDM.setPais(ciudad.get(0).getProvincia().getPais());
			llenarLisProvinciaXEmpresa();
			llenarLisCiudadXEmpresa();
			llenarDependientes();
		}

		Ctacliente ctacliente = new Ctacliente();
		ctacliente.setIdcuenta(0);
		ctacliente.setNombre("Seleccione una cuenta");
		ingOrdenesDM.getListaCta().add(ctacliente);

		// RECIBE POR PARAMETRO
		idcuenta = Integer
				.parseInt(faces.getParametroUrl("idcuenta") != null ? faces
						.getParametroUrl("idcuenta").toString() : "0");

		if (idcuenta > 0) {
			// Al recibir por parametro el idcuenta, consultamos
			try {
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);

				if (ctacliente != null && ctacliente.getIdcuenta() > 0) {
					// INHABILITAR COMPONENTES
					bandcliente = true;

					// LLENAR IDENTIFICACION DEL CLIENTE, NOMBRES, COMBO CUENTA,
					// LLENAR TABLA PRODUCTOS
					ingOrdenesDM.setDocumento(ctacliente.getClientes()
							.getIdcliente());
					ingOrdenesDM.setCliente(ctacliente.getClientes());

					// item cero
					/*
					 * Ctacliente ctacliente = new Ctacliente();
					 * ctacliente.setIdcuenta(0);
					 * ctacliente.setNombre("Seleccione una cuenta");
					 * ingOrdenesDM.getListaCta().add(ctacliente);
					 */

					// los demas items
					ingOrdenesDM.getListaCta().addAll(
							iCtaClienteDao.getCtaByCliente(ingOrdenesDM
									.getCliente().getIdcliente()));

					// setea cuenta
					ingOrdenesDM.getOrden().setCuentaCliente(ctacliente);

					// getProductos();
					// new BaseController().update("dtbProducto");
				} else {
					new MessageUtil()
							.showErrorMessage("Ordenes",
									"No se encontro al cliente con este numero de documento");
				}
			} catch (Exception re) {
				re.printStackTrace();
				new MessageUtil()
						.showFatalMessage("Esto es Vergonzoso!",
								"Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}
	
	public void borrarProducto(){
		ingOrdenesDM.getListaCtaProd().clear();
	}

	public ingOrdenesDM getIngOrdenesDM() {
		return ingOrdenesDM;
	}

	public void setIngOrdenesDM(ingOrdenesDM ingOrdenesDM) {
		this.ingOrdenesDM = ingOrdenesDM;
	}

	public boolean isDireccion() {
		direccion = false;
		if (ingOrdenesDM.getOrden().getTipoOperacion().getIdtipooperacion() == TipoSolicitudes.CambioDireccion
				.getDescripcion()) {
			direccion = true;
		}
		return direccion;
	}

	public void setDireccion(boolean direccion) {
		this.direccion = direccion;
	}
	
	public void buscaCliente() {
		if (!(ingOrdenesDM.getDocumento() == null || ingOrdenesDM
				.getDocumento().equals(""))) {

			try {
				ingOrdenesDM
						.setCliente(iclienteDao
								.getClienteByIdentificacion(ingOrdenesDM
										.getDocumento()));
				if (ingOrdenesDM.getCliente() == null) {					
					new MessageUtil()
							.showInfoMessage("Ordenes",
									"No se encontro al cliente con este numero de documento");
				} else {
					// ingOrdenesDM.setListaCta(iCtaClienteDao.getCtaByCliente(ingOrdenesDM.getCliente().getIdcliente()));
					// item cero

					// los demas items
					if (ingOrdenesDM.getListaCta().size() <= 1)
						ingOrdenesDM.getListaCta().addAll(
								iCtaClienteDao.getCtaByCliente(ingOrdenesDM
										.getCliente().getIdcliente()));
					// ingOrdenesDM.getOrden().setCuentaCliente(ctacliente);
				}
			} catch (Exception e) {
				new MessageUtil().showErrorMessage("Ordenes",
						"Error al buscar al cliente: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public List<Ctacliente> getCtaClient() {
		return ingOrdenesDM.getListaCta();
	}

	public List<Tipooperacion> getTipoOperaciones() {
		ingOrdenesDM.setTipoOperacion(iTipoOperacionServices.getAll());
		return ingOrdenesDM.getTipoOperacion();
	}

	public List<Motivos> getMotivos() {
		ingOrdenesDM.setMotivos(new ArrayList<Motivos>());
		if (ingOrdenesDM.getOrden().getTipoOperacion().getIdtipooperacion() != 0) {
			ingOrdenesDM.setMotivos(iMotivoDao
					.getMotivosByTipoOperacion(ingOrdenesDM.getOrden()
							.getTipoOperacion().getIdtipooperacion()));
		}
		return ingOrdenesDM.getMotivos();
	}

	public List<Motivos> getHorarios() {
		ingOrdenesDM.setHorarios(new ArrayList<Motivos>());

		ingOrdenesDM.setHorarios(iMotivoDao
				.getMotivosByTipoOperacion(TipoSolicitudes.Horarios
						.getDescripcion()));

		return ingOrdenesDM.getHorarios();
	}

	public List<Ctasprod> getProductos() {
		//ingOrdenesDM.setListaCtaProd(new ArrayList<Ctasprod>());

		
		if(ingOrdenesDM.getListaCtaProd()==null||ingOrdenesDM.getListaCtaProd().isEmpty()){	
		
		if (ingOrdenesDM.getOrden().getCuentaCliente().getIdcuenta() != 0
				&& ingOrdenesDM.getOrden().getTipoOperacion()
						.getIdtipooperacion() != 0) {
				if (TipoSolicitudes.InstNueva.getDescripcion() == ingOrdenesDM
						.getOrden().getTipoOperacion().getIdtipooperacion()) {
					ingOrdenesDM.setListaCtaProd(iCtasProductoDao
							.getProductoByIdCta(ingOrdenesDM.getOrden()
									.getCuentaCliente().getIdcuenta(),
									Estados.INGRESADOPAGADO.getDescription()));
				} else if (TipoSolicitudes.InstTvAdi.getDescripcion() == ingOrdenesDM
						.getOrden().getTipoOperacion().getIdtipooperacion()) {
					ingOrdenesDM.setListaCtaProd(iCtasProductoDao
							.getProductoByIdCta(ingOrdenesDM.getOrden()
									.getCuentaCliente().getIdcuenta(),
									Estados.INGRESADOPAGADO.getDescription()));
				} else if (TipoSolicitudes.Reconexion.getDescripcion() == ingOrdenesDM
						.getOrden().getTipoOperacion().getIdtipooperacion()) {
					ingOrdenesDM.setListaCtaProd(iCtasProductoDao
							.getProductoByIdCta(ingOrdenesDM.getOrden()
									.getCuentaCliente().getIdcuenta(),
									Estados.BLOQUEADO.getDescription()));
				} else {
					ingOrdenesDM.setListaCtaProd(iCtasProductoDao
							.getProductoByIdCta(ingOrdenesDM.getOrden()
									.getCuentaCliente().getIdcuenta(),
									Estados.ACTIVO.getDescription()));
				}
			}
		}
		return ingOrdenesDM.getListaCtaProd();
	}

	
	public void grabar() {
		/**
		 * Validaciones
		 */
		
		if (ingOrdenesDM.getOrden().getCuentaCliente().getIdcuenta() == 0) {
			new MessageUtil().showInfoMessage("Ordenes",
					"Seleccione una cuenta");
			return;
		}
		if (ingOrdenesDM.getOrden().getProducto() == null) {
			new MessageUtil().showInfoMessage("Ordenes",
					"Seleccione un producto");
			return;
		}

		if (ingOrdenesDM.getOrden().getMotivo().getIdmotivo() == 0) {
			new MessageUtil()
					.showInfoMessage("Ordenes", "Seleccione un motivo");
			return;
		}
		if (calendarOrdenesBean.getFecha() == null) {
			new MessageUtil().showInfoMessage("Ordenes",
					"Falta la fecha de ejecucion");
			return;
		}else{
			ingOrdenesDM.getOrden().setFechaEjecucion(calendarOrdenesBean.getFecha());
		}
		if (ingOrdenesDM.getOrden().getTipoOperacion().getIdtipooperacion() == 0) {
			new MessageUtil().showInfoMessage("Ordenes",
					"Seleccione el tipo de operacion");
			return;
		}
		if (ingOrdenesDM.getOrden().getHorario().getIdmotivo() == 0) {
			new MessageUtil().showInfoMessage("Ordenes",
					"Seleccione el horario");
			return;
		}
			if (!(ingOrdenesDM.getDocumento() == null || ingOrdenesDM
					.getDocumento().equals(""))) {
				if (iOrdenesDao.consultaOrdenesxStsTipoCli(ingOrdenesDM
						.getOrden().getTipoOperacion().getIdtipooperacion(),
						ingOrdenesDM.getDocumento())) {
				
					new MessageUtil()
							.showInfoMessage("Ordenes",
									"El cliente ya tiene ingresada una orden de este tipo");
					return;

				}
			}
		

		try {

			/**
			 * Insertamos direcciones en caso de que sea por tipo de cambio de
			 * direccion
			 */
			if (ingOrdenesDM.getOrden().getTipoOperacion().getIdtipooperacion() == TipoSolicitudes.CambioDireccion
					.getDescripcion()) {
				if (ingOrdenesDM.getListadireccioes().isEmpty()
						|| ingOrdenesDM.getListadireccioes().size() != 3) {
					new MessageUtil().showInfoMessage("Ordenes",
							"Debe de ingresar los tres tipos de direcciones");
					return;
				}
			}

			/**
			 * Dependiendo de el tipo de producto (Manual, automatico) se
			 * realiza la accion
			 */
			iOrdenesDao.insOperacion(ingOrdenesDM.getListadireccioes(),
					ingOrdenesDM.getOrden());

			new MessageUtil().showInfoMessage("Ordenes", "Ingreso exitoso");
			init();
		} catch (Exception e) {
			new MessageUtil().showErrorMessage("Ordenes", "Error al insertar: "
					+ e.getMessage());

			ingOrdenesDM.getOrden().setIdOrdenes((long) 0);
			if (!ingOrdenesDM.getListadireccioes().isEmpty()) {
				for (int i = 0; i < ingOrdenesDM.getListadireccioes().size(); i++) {
					ingOrdenesDM.getListadireccioes().get(i).setIddireccion(0);
					if (ingOrdenesDM.getListadireccioes().get(i)
							.getReferenciadir() != null)
						ingOrdenesDM.getListadireccioes().get(i)
								.getReferenciadir().setIdreferencia(0);
				}
			}
		}

	}

	public void cambioOperacion() {
		this.mes = 0;
		calendarOrdenesBean.setMostrarCalendar(false);
		if(ingOrdenesDM.getOrden().getTipoOperacion().getIdtipooperacion() > 0){
			mostrar = true;
		}else{
			mostrar = false;
		}
		
	}
	
	public void validaFecha() {
		if (ingOrdenesDM.getOrden().getTipoOperacion().getIdtipooperacion() == 0
				|| ingOrdenesDM.getOrden().getFechaEjecucion() == null)
			return;
		List<String> meses = iAsignacion.DiasxMes(ingOrdenesDM.getOrden()
				.getTipoOperacion().getIdtipooperacion(), new SimpleDateFormat(
				"MM").format(ingOrdenesDM.getOrden().getFechaEjecucion()));
		Long total = iOrdenesDao.CountByFechaTipoOpe(ingOrdenesDM.getOrden()
				.getFechaEjecucion(), ingOrdenesDM.getOrden()
				.getTipoOperacion().getIdtipooperacion());
		ParamAsignacionOrden param = iAsignacion.ValidaDias(ingOrdenesDM
				.getOrden().getTipoOperacion().getIdtipooperacion(),
				ingOrdenesDM.getOrden().getFechaEjecucion());
		if (param == null) {
			String mensaje = "No se a parametrizado ordenes para este dia \\n";
			String mensajeDias = "Los días disponibles para el mes seleccionado son: ";
			String dias = "";
			for (int i = 0; i < meses.size(); i++) {
				if (meses.size() == (i + 1))
					dias += meses.get(i);
				else
					dias += meses.get(i) + "-";
			}
			if (dias != null && !dias.equals("")) {
				RequestContext.getCurrentInstance().execute(
						"alert('" + mensaje + mensajeDias + "\\n" + dias
								+ "');");
				ingOrdenesDM.getOrden().setFechaEjecucion(null);
				return;
			} else {
				RequestContext.getCurrentInstance().execute(
						"alert('" + mensaje + "');");
				//ingOrdenesDM.getOrden().setFechaEjecucion(null);
				return;
			}
		}
		if (total >= param.getNoasignaciones()) {
			RequestContext.getCurrentInstance().execute(
					"alert('No se pueden asignar mas solicitudes este dia');");
			ingOrdenesDM.getOrden().setFechaEjecucion(null);
		}

	}

	public boolean isCancelacion() {
		cancelacion = false;
		if (ingOrdenesDM.getOrden().getTipoOperacion().getIdtipooperacion() == TipoSolicitudes.Cancelacion
				.getDescripcion()) {
			cancelacion = true;
		}
		return cancelacion;
	}

	public void setCancelacion(boolean cancelacion) {
		this.cancelacion = cancelacion;
	}

	public List<Pais> getPais() throws Exception {
		PaisBO paisBO = new PaisBO();
		ingOrdenesDM.setListapais(paisBO.consultarPaises());
		return ingOrdenesDM.getListapais();

	}

	public void llenarLisProvincia() throws Exception {
		ingOrdenesDM.setListaprovincia(new ArrayList<Provincia>());
		ingOrdenesDM.setListaciudad(new ArrayList<Ciudad>());
		ingOrdenesDM.setProvincia(new Provincia());
		ingOrdenesDM.setCiudad(new Ciudad());
		if (ingOrdenesDM.getPais().getIdpais() != 0) {

			Provincia provincia = new Provincia();
			provincia.setIdprovincia(0);
			provincia.setNombre("Seleccione Provincia...");

			ProvinciaBO provinciaBO = new ProvinciaBO();
			ingOrdenesDM.setListaprovincia(provinciaBO
					.consultarProvinciaPorPais(ingOrdenesDM.getPais()
							.getIdpais()));

			SeteaTiposSectores(4);
		}

	}

	public void llenarLisCiudad() throws Exception {
		ingOrdenesDM.setListaciudad(new ArrayList<Ciudad>());
		ingOrdenesDM.setCiudad(new Ciudad());
		if (ingOrdenesDM.getProvincia().getIdprovincia() != 0) {
			CiudadBO ciudadBO = new CiudadBO();
			ingOrdenesDM.setListaciudad(ciudadBO
					.consultarCiudadPorProvincia(ingOrdenesDM.getProvincia()
							.getIdprovincia()));
			SeteaTiposSectores(4);
		}
	}

	private void SeteaTiposSectores(int codigo) {
		ingOrdenesDM.setCallePrincipal(new Calleprincipal());
		ingOrdenesDM.setCalleSecundaria(new Callesecundaria());
		ingOrdenesDM.setUbicacion(new Ubicacion());
		ingOrdenesDM.setEdificio(new Edificio());
		ingOrdenesDM.setNodo(new Nodos());

		ingOrdenesDM.setListaEdificio(new ArrayList<Edificio>());
		ingOrdenesDM.setListaNodos(new ArrayList<Nodos>());
		ingOrdenesDM.setListaPrincipal(new ArrayList<Calleprincipal>());
		ingOrdenesDM.setListaSecundaria(new ArrayList<Callesecundaria>());
		ingOrdenesDM.setListaUbicacion(new ArrayList<Ubicacion>());
		ingOrdenesDM.setListaTipoSector(new ArrayList<Tiposector>());

		if (codigo == 4) {
		}
		if (codigo != 3) {
			TipoSectorBO tiposectorBO = new TipoSectorBO();
			try {
				ingOrdenesDM.setListaTipoSector(tiposectorBO.ConsultarTsXTs(1));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (codigo == 2 || codigo == 3) {
			if (codigo == 2) {
				NodosBO nodosBO = new NodosBO();
				try {
					ingOrdenesDM.setListaNodos(nodosBO
							.ConsultaNodosxSector(ingOrdenesDM.getDireccion()
									.getSector().getIdsector()));
				} catch (Exception e) {
					e.printStackTrace();
				}

				EdificiosBO edificiosBO = new EdificiosBO();
				try {
					ingOrdenesDM.setListaEdificio(edificiosBO
							.EdificioxSector(ingOrdenesDM.getDireccion()
									.getSector().getIdsector()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

			UbicacionBO ubicacionBO = new UbicacionBO();

			try {
				ingOrdenesDM.setListaUbicacion(ubicacionBO
						.ConsultarUbicacionxSector(ingOrdenesDM.getDireccion()
								.getSector().getIdsector()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void llenarDependientes() {
		try {
			ingOrdenesDM.setSector(new Sector());
			SectorBO sectorBO = new SectorBO();
			ingOrdenesDM.setListaSector(sectorBO.SectorxCiudad(ingOrdenesDM
					.getCiudad().getIdciudad()));
			SeteaTiposSectores(1);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void llenarDependientesSector() {
		SeteaTiposSectores(2);
		CompletarCallesPrincipales("");
	}

	public List<Calleprincipal> CompletarCallesPrincipales(String query) {

		ingOrdenesDM.setListaPrincipal(new ArrayList<Calleprincipal>());

		CallePrincipalBO calleprincipalBO = new CallePrincipalBO();

		try {

			ingOrdenesDM.setListaPrincipal(calleprincipalBO.ConsultarCPxQuery(
					ingOrdenesDM.getDireccion().getSector().getIdsector(),
					query));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ingOrdenesDM.getListaPrincipal();
	}

	public List<Callesecundaria> CompletarCallesSecundarias(String query) {
		ingOrdenesDM.setListaSecundaria(new ArrayList<Callesecundaria>());
		CallesecundariaBO CallesecundariaBO = new CallesecundariaBO();
		try {
			ingOrdenesDM.setListaSecundaria(CallesecundariaBO
					.ConsultarCSxQuery(ingOrdenesDM.getDireccion().getSector()
							.getIdsector(), query));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ingOrdenesDM.getListaSecundaria();
	}

	public List<Tiposector> getTipoSector() throws Exception {
		ingOrdenesDM.setListaTipoSector(new ArrayList<Tiposector>());
		ingOrdenesDM.setListaTipoSector(new TipoSectorBO().ConsultarTsXTs(0));
		return ingOrdenesDM.getListaTipoSector();
	}

	public void agregaDireccion() throws Exception {
		if (ingOrdenesDM.getDireccion().getIdtipodireccion() == 0) {
			new MessageUtil().showInfoMessage("Ordenes",
					"Seleccione el Tipo de dirección");
			return;
		}
		if (ingOrdenesDM.getDireccion().getSector().getIdsector() == 0) {
			new MessageUtil()
					.showInfoMessage("Ordenes", "Seleccione el sector");
			return;
		}
		if (ingOrdenesDM.getDireccion().getTiposector().getIdtiposector() == 0) {
			new MessageUtil().showInfoMessage("Ordenes",
					"Seleccione el tipo de sector");
			return;
		}
		if (ingOrdenesDM.getDireccion().getNodos().getIdnodo() == 0) {
			new MessageUtil().showInfoMessage("Ordenes", "Seleccione el nodo");
			return;
		}
		if (ingOrdenesDM.getDireccion().getTiposector().getIdtiposector() == 1) {
			if (ingOrdenesDM.getDireccion().getCalleprincipal() == null
					|| ingOrdenesDM.getDireccion().getCalleprincipal()
							.getIdcalleprincipal() == 0) {
				new MessageUtil().showInfoMessage("Ordenes",
						"Seleccione la calle principal");
				return;
			}
			if (ingOrdenesDM.getDireccion().getCallesecundaria() == null
					|| ingOrdenesDM.getDireccion().getCallesecundaria()
							.getIdcallesecundaria() == 0) {
				new MessageUtil().showInfoMessage("Ordenes",
						"Seleccione la calle secundaria");
				return;
			}

		}
		if (ingOrdenesDM.getDireccion().getTiposector().getIdtiposector() == 2) {
			if (ingOrdenesDM.getDireccion().getNumero() == 0) {
				new MessageUtil().showInfoMessage("Ordenes",
						"Ingrese el número de dirección");
				return;
			}
			if (ingOrdenesDM.getDireccion().getSolar() == null
					|| ingOrdenesDM.getDireccion().getSolar().equals("")) {
				new MessageUtil().showInfoMessage("Ordenes",
						"Ingrese el número de dirección");
				return;
			}
		}

		if (ingOrdenesDM.getDireccion().getUbicacion().getIdubicacion() == 0) {
			new MessageUtil().showInfoMessage("Ordenes",
					"Seleccione la ubicación");
			return;
		}
		if (ingOrdenesDM.getDireccion().getEdificio().getIdedificio() == 0) {
			new MessageUtil().showInfoMessage("Ordenes",
					"Seleccione el edificio");
			return;
		} else {
			if (ingOrdenesDM.getDireccion().getPiso() == 0) {
				new MessageUtil().showInfoMessage("Ordenes",
						"Agregar el número de piso");
				return;
			}
		}

		for (Direccion direccion : ingOrdenesDM.getListadireccioes()) {
			if (direccion.getIdtipodireccion() == ingOrdenesDM.getDireccion()
					.getIdtipodireccion()) {
				new MessageUtil()
						.showInfoMessage("Ordenes",
								"Este tipo de direccion ya se encuentra actualmente ingresada");
				return;
			}
		}
		ingOrdenesDM.getDireccion().setSector(
				new SectorBO().SectorxSector(
						ingOrdenesDM.getDireccion().getSector().getIdsector())
						.get(0));
		ingOrdenesDM.getDireccion().setUbicacion(
				new UbicacionBO().ConsultarUbicacionxid(
						ingOrdenesDM.getDireccion().getUbicacion()
								.getIdubicacion()).get(0));

		ingOrdenesDM.getDireccion().setReferenciadir(
				ingOrdenesDM.getReferencia());
		ingOrdenesDM.getListadireccioes().add(ingOrdenesDM.getDireccion());
		enseraDirecciones();

	}

	public void eliminarDireccion(int id) {
		for (int i = 0; i < ingOrdenesDM.getListadireccioes().size(); i++) {
			if (ingOrdenesDM.getListadireccioes().get(i).getIdtipodireccion() == id) {
				ingOrdenesDM.getListadireccioes().remove(i);
			}
		}

	}

	public void enseraDirecciones() {

		Calleprincipal cap = ingOrdenesDM.getDireccion().getCalleprincipal();
		Callesecundaria cas = ingOrdenesDM.getDireccion().getCallesecundaria();
		Edificio edificio = ingOrdenesDM.getDireccion().getEdificio();
		Nodos nodo = ingOrdenesDM.getDireccion().getNodos();
		Sector sector = ingOrdenesDM.getDireccion().getSector();
		Tiposector tsector = ingOrdenesDM.getDireccion().getTiposector();
		Ubicacion ubi = ingOrdenesDM.getDireccion().getUbicacion();
		String solar;
		int piso, numero = 0;
		piso = ingOrdenesDM.getDireccion().getPiso();
		solar = ingOrdenesDM.getDireccion().getSolar();
		numero = ingOrdenesDM.getDireccion().getNumero();

		ingOrdenesDM.setDireccion(new Direccion());
		ingOrdenesDM.getDireccion().setCalleprincipal(new Calleprincipal());
		ingOrdenesDM.getDireccion().setCallesecundaria(new Callesecundaria());
		ingOrdenesDM.getDireccion().setCtacliente(new Ctacliente());
		ingOrdenesDM.getDireccion().setEdificio(new Edificio());
		ingOrdenesDM.getDireccion().setNodos(new Nodos());
		ingOrdenesDM.getDireccion().setSector(new Sector());
		ingOrdenesDM.getDireccion().setUbicacion(new Ubicacion());
		ingOrdenesDM.getDireccion().setTiposector(new Tiposector());

		ingOrdenesDM.getDireccion().setSolar(solar);
		ingOrdenesDM.getDireccion().setNumero(numero);
		ingOrdenesDM.getDireccion().setPiso(piso);
		ingOrdenesDM.getDireccion().setCalleprincipal(cap);
		ingOrdenesDM.getDireccion().setCallesecundaria(cas);
		ingOrdenesDM.getDireccion().setEdificio(edificio);
		ingOrdenesDM.getDireccion().setNodos(nodo);
		ingOrdenesDM.getDireccion().setSector(sector);
		ingOrdenesDM.getDireccion().setUbicacion(ubi);
		ingOrdenesDM.getDireccion().setTiposector(tsector);

		ingOrdenesDM.setReferencia(new Referenciadir());

	}

	public void llenarLisProvinciaXEmpresa() throws Exception {
		ingOrdenesDM.setListaprovincia(new ArrayList<Provincia>());
		ingOrdenesDM.setListaciudad(new ArrayList<Ciudad>());
		if (ingOrdenesDM.getPais().getIdpais() != 0) {

			Provincia provincia = new Provincia();
			provincia.setIdprovincia(0);
			provincia.setNombre("Seleccione Provincia...");

			ProvinciaBO provinciaBO = new ProvinciaBO();
			ingOrdenesDM.setListaprovincia(provinciaBO
					.consultarProvinciaPorPais(ingOrdenesDM.getPais()
							.getIdpais()));

			SeteaTiposSectores(4);
		}

	}

	public void llenarLisCiudadXEmpresa() throws Exception {
		ingOrdenesDM.setListaciudad(new ArrayList<Ciudad>());
		if (ingOrdenesDM.getProvincia().getIdprovincia() != 0) {
			CiudadBO ciudadBO = new CiudadBO();
			ingOrdenesDM.setListaciudad(ciudadBO
					.consultarCiudadPorProvincia(ingOrdenesDM.getProvincia()
							.getIdprovincia()));
			SeteaTiposSectores(4);
		}
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public Ctacliente getCtacliente() {
		return ctacliente;
	}

	public void setCtacliente(Ctacliente ctacliente) {
		this.ctacliente = ctacliente;
	}

	public boolean isBandcliente() {
		return bandcliente;
	}

	public void setBandcliente(boolean bandcliente) {
		this.bandcliente = bandcliente;
	}

	public String getStrSpecialDates() {
		return strSpecialDates;
	}

	public void setStrSpecialDates(String strSpecialDates) {
		this.strSpecialDates = strSpecialDates;
	}

	public Calendar getPrimeraFechaMes() {
		return primeraFechaMes;
	}

	public void setPrimeraFechaMes(Calendar primeraFechaMes) {
		this.primeraFechaMes = primeraFechaMes;
	}

	public Calendar getUltimaFechaMes() {
		return ultimaFechaMes;
	}

	public void setUltimaFechaMes(Calendar ultimaFechaMes) {
		this.ultimaFechaMes = ultimaFechaMes;
	}

	public boolean isTomaPrimeraFecha() {
		return tomaPrimeraFecha;
	}

	public void setTomaPrimeraFecha(boolean tomaPrimeraFecha) {
		this.tomaPrimeraFecha = tomaPrimeraFecha;
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

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

}
