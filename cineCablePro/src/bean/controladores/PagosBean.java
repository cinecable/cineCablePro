package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TabChangeEvent;

import exceptions.SecuenciaFacturaException;
import global.Parametro;

import bo.negocio.BancosBO;
import bo.negocio.CargosBO;
import bo.negocio.CreditosBO;
import bo.negocio.CtaclienteBO;
import bo.negocio.ExcedentesBO;
import bo.negocio.FacturaBO;
import bo.negocio.FpagoBO;
import bo.negocio.ImpserviciosBO;
import bo.negocio.ProductoBO;
import bo.negocio.PromocionesBO;
import bo.negocio.ServicioBO;
import bo.negocio.TpagosBO;

import pojo.annotations.Bancos;
import pojo.annotations.Cargos;
import pojo.annotations.Clientes;
import pojo.annotations.Creditos;
import pojo.annotations.Ctacliente;
import pojo.annotations.Empresa;
import pojo.annotations.Excedentes;
import pojo.annotations.Factura;
import pojo.annotations.Fpago;
import pojo.annotations.Impservicios;
import pojo.annotations.Pagos;
import pojo.annotations.Producto;
import pojo.annotations.Promociones;
import pojo.annotations.Servicio;
import pojo.annotations.Tipocliente;
import pojo.annotations.Tpagos;
import pojo.annotations.Usuario;
import pojo.annotations.custom.DetalleContratoPojo;
import pojo.annotations.custom.DetalleFacturaPojo;
import pojo.annotations.custom.ImpuestoValor;
import pojo.annotations.custom.ServicioValor;

import util.MessageUtil;

@ManagedBean
@ViewScoped
public class PagosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6769525191844087015L;
	//Ids por url
	private String idcliente;
	private int idcuenta;
	
	//Objetos del Tab
	private Ctacliente ctacliente;
	private List<Factura> lisFacturaCliente;
	private final String TAB_ANTICIPO_TITLE = "Pagos Anticipados";
	private final String TAB_FACTURA_TITLE = "Facturas";
	private final String TAB_GENERACION_TITLE = "Otros Cargos";
	private final String TAB_CONTRATOS_TITLE = "Contratos";
	private int activeIndex;
	private String activeTab;
	private Factura facturaSeleccionada;
	private List<DetalleFacturaPojo> lisDetalleFacturaPojo;
	private List<Creditos> lisCreditosCuenta;
	private float totalCreditosCuenta;
	private float totalCreditosFacturasSeleccionadas;
	private List<Pagos> lisPagosCuenta;
	private List<Excedentes> lisExcedentes;
	private float totalFacturas;
	private float totalAbonosCuenta;
	private float totalExcedentesCuenta;
	private float totalGeneral;
	private List<DetalleContratoPojo> lisDetalleContratoPojo;
	private float totalServiciosContrato;
	private float totalDescuentosContrato;
	private float totalImpuestosContrato;
	private float totalPagarContrato;
	
	//Objetos del editor
	private List<Fpago> lisFpago;
	private Fpago fpagoSelected;
	private List<Bancos> lisEntidadBancos;
	private List<Bancos> lisEntidadTarjeta;
	private Bancos entidadBancosSelected;
	private Bancos entidadTarjetaSelected;
	private Date fechaCaducidad;
	private final int TIPO_ENTIDAD_BANCOS = 1;
	private final int TIPO_ENTIDAD_TARJETAS = 2;
	private Tpagos tpagosEditor;
	
	//Objetos del Grid Formas de Pago
	private List<Tpagos> lisTpagos;
	private Tpagos tpagosSelected;
	private float totalFormasPago;
	
	//Otros
	private boolean isBotonRendered;
	private String focusId;
	private boolean isTabAnticipadosRendered;
	private boolean isTabFacturaRendered;
	private boolean isTabGeneracionRendered;
	private boolean isTabContratosRendered;
	private String numeroFactura;

	public PagosBean() {
		//Inicializa objetos del tab
		activeIndex = 0;
		activeTab = TAB_FACTURA_TITLE;
		ctacliente = new Ctacliente(0, new Empresa(), new Clientes(null, new Tipocliente(),new Usuario(),new Empresa(),null,null,null));
		facturaSeleccionada = new Factura();
		lisDetalleFacturaPojo = new ArrayList<DetalleFacturaPojo>();
		lisCreditosCuenta = new ArrayList<Creditos>();
		totalCreditosCuenta = 0;
		totalCreditosFacturasSeleccionadas = 0;
		lisPagosCuenta = new ArrayList<Pagos>();
		lisExcedentes = new ArrayList<Excedentes>();
		totalFacturas = 0;
		totalAbonosCuenta = 0;
		totalExcedentesCuenta = 0;
		totalGeneral = 0;
		lisDetalleContratoPojo = new ArrayList<DetalleContratoPojo>();
		totalServiciosContrato = 0;
		totalDescuentosContrato = 0;
		totalImpuestosContrato = 0;
		totalPagarContrato = 0;
		
		//Inicializa objetos del editor
		tpagosEditor = new Tpagos(0, new Fpago(), new Bancos(), new Pagos(), new Bancos(), 0, Parametro.TPAGOS_ESTADO_PENDIENTE, 0, 0);
		lisFpago = new ArrayList<Fpago>();
		lisEntidadBancos = new ArrayList<Bancos>();
		lisEntidadTarjeta = new ArrayList<Bancos>();
		fpagoSelected = new Fpago();
		entidadBancosSelected = new Bancos();
		entidadTarjetaSelected = new Bancos();
		fechaCaducidad = new Date();
		llenarComboFormaPago();
		llenarComboEntidadBanco();
		llenarComboEntidadTarjeta();
		
		//Inicializa objetos del grid formas de pagos
		lisTpagos = new ArrayList<Tpagos>();
		tpagosSelected = new Tpagos(0, new Fpago(), new Bancos(), new Pagos(), new Bancos(), 0, 0, 0, 0);
		totalFormasPago = 0;
		
		//Otros
		isBotonRendered = true;
		focusId = "idTabTipoPagos";
		isTabAnticipadosRendered = true;
		isTabFacturaRendered = true;
		isTabGeneracionRendered = true;
		isTabContratosRendered = true;
		numeroFactura = null;
		
		//imprimirFactura("001-001-0000007");
	}
	
	private void llenarComboFormaPago(){
		try{
			Fpago fpago = new Fpago();
			fpago.setIdfpago(0);
			fpago.setNombre("Seleccione");
			
			lisFpago.add(fpago);
			
			FpagoBO fpagoBO = new FpagoBO();
			List<Fpago> lisTmp = fpagoBO.lisFpago();
			
			if(lisTmp != null && lisTmp.size() > 0){
				lisFpago.addAll(lisTmp);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}
	
	public void llenarComboEntidadBanco(){
		try{
			Bancos bancos = new Bancos();
			bancos.setIdbanco(0);
			bancos.setNombre("Seleccione");
			
			lisEntidadBancos.add(bancos);
			
			BancosBO bancosBO = new BancosBO();
			List<Bancos> lisTmp = bancosBO.lisBancosByTipoEntidad(TIPO_ENTIDAD_BANCOS);
			
			if(lisTmp != null && lisTmp.size() > 0){
				lisEntidadBancos.addAll(lisTmp);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}
	
	public void llenarComboEntidadTarjeta(){
		try{
			Bancos bancos = new Bancos();
			bancos.setIdbanco(0);
			bancos.setNombre("Seleccione");
			
			lisEntidadTarjeta.add(bancos);
			
			BancosBO bancosBO = new BancosBO();
			List<Bancos> lisTmp = bancosBO.lisBancosByTipoEntidad(TIPO_ENTIDAD_TARJETAS);
			
			if(lisTmp != null && lisTmp.size() > 0){
				lisEntidadTarjeta.addAll(lisTmp);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}

	public String getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
		if(idcuenta > 0){
			try{
				//Al recibir por parametro el idcuenta, consultamos
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
				
				//si cuenta cliente tiene estado 3 = impago entonces activo pestaña contratos e inactivo las demas pestañas
				if(ctacliente != null && ctacliente.getIdestado() == Parametro.CUENTA_CLIENTE_IMPAGO){
					isTabAnticipadosRendered = false;
					isTabFacturaRendered = false;
					isTabGeneracionRendered = false;
					isTabContratosRendered = true;
					
					totalServiciosContrato = 0;
					totalDescuentosContrato = 0;
					totalImpuestosContrato = 0;
					totalPagarContrato = 0;
					lisDetalleContratoPojo = new ArrayList<DetalleContratoPojo>();
					
					tabContratos();
					activeTab = TAB_CONTRATOS_TITLE;
				}else{
					isTabAnticipadosRendered = true;
					isTabFacturaRendered = true;
					isTabGeneracionRendered = true;
					isTabContratosRendered = false;
					
					activeTab = TAB_FACTURA_TITLE;
				}
			}
			catch(Exception re){
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}

	public void tabChangeListener(TabChangeEvent tabChangeEvent){
		//tab 0 - Pagos Anticipados 
		//tab 1 - Factura
		//tab 2 - Otros Cargos
		//tab 3 - Contratos
		
		activeTab = tabChangeEvent.getTab().getTitle();
		
		lisFacturaCliente = new ArrayList<Factura>(); 
		lisDetalleFacturaPojo = new ArrayList<DetalleFacturaPojo>();
		lisCreditosCuenta = new ArrayList<Creditos>();
		lisExcedentes = new ArrayList<Excedentes>();
		
		totalFacturas = 0;
		totalCreditosCuenta = 0;
		totalAbonosCuenta = 0;
		totalExcedentesCuenta = 0;
		totalGeneral = 0;

		if(activeTab.compareTo(TAB_FACTURA_TITLE) == 0){
			tabFacturaGeneracion();
		}else{
			if(activeTab.compareTo(TAB_GENERACION_TITLE) == 0){
				tabFacturaGeneracion();
			}else{
				if(activeTab.compareTo(TAB_CONTRATOS_TITLE) == 0){
					tabContratos();
				}
			}
		}
		
	}
	
	private void tabFacturaGeneracion(){
		try{
			FacturaBO facturaBO = new FacturaBO();
			CreditosBO creditosBO = new CreditosBO();
			//PagosBO pagosBO = new PagosBO();
			ExcedentesBO excedentesBO = new ExcedentesBO();
			
			//Consulto las facturas/generaciones de la cuenta
			if(activeTab.compareTo(TAB_FACTURA_TITLE) == 0){
				lisFacturaCliente = facturaBO.lisFacturaParaFacturacion(idcuenta);
			}else{
				if(activeTab.compareTo(TAB_GENERACION_TITLE) == 0){
					lisFacturaCliente = facturaBO.lisFacturaParaCargosGenerados(idcuenta);
				}
			}
			
			if(lisFacturaCliente == null){
				lisFacturaCliente = new ArrayList<Factura>();
			}
			
			//Consulto los creditos de la cuenta
			lisCreditosCuenta = creditosBO.lisCreditosActivosByCuenta(idcuenta); 
			//Sumo los creditos de la cuenta
			for(Creditos creditos : lisCreditosCuenta){
				totalCreditosCuenta += creditos.getVacredito();
			}
			
			//SE COMENTA PORQUE EN LOS ABONOS CONSULTADOS A LA TABLA PAGOS VIENE INCLUIDO CREDITOS Y EXCEDENTES, LO QUE HACE DUPLICAR CON LOS CREDITOS Y EXCEDENTES POR FACTURA
			/*
			//Consulto los abonos de la cuenta
			lisPagosCuenta = pagosBO.lisPagosAbonosActivosByCuenta(ctacliente.getIdcuenta());
			//Sumo los abonos de la cuenta
			for(Pagos pagos : lisPagosCuenta){
				totalAbonosCuenta += pagos.getValtotal();
			}*/
			
			//Consulto los excedentes de la cuenta
			lisExcedentes = excedentesBO.lisExcedentesActivosByCuenta(idcuenta);
			//Sumo los excedentes de la cuenta
			for(Excedentes excedentes : lisExcedentes){
				totalExcedentesCuenta += excedentes.getValpendiente();
			}
			
			//Actualizo Total General
			totalGeneral += - totalCreditosCuenta - /*totalAbonosCuenta - */totalExcedentesCuenta; 
		}
		catch(Exception re){
            re.printStackTrace();
            new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
        }
	}
	
	private void tabContratos(){
		try{
			ImpserviciosBO impserviciosBO = new ImpserviciosBO();
			PromocionesBO promocionesBO = new PromocionesBO();
			ProductoBO productoBO = new ProductoBO();
			ServicioBO servicioBO = new ServicioBO();
			
			
			//consultar los productos de la cuenta
			List<Producto> lisProducto = productoBO.lisProductoByIdCuenta(idcuenta);
			
			for(Producto producto : lisProducto){
				float totalPagarProducto = 0;
				DetalleContratoPojo detalleContratoPojo = new DetalleContratoPojo();
				detalleContratoPojo.setProducto(producto);
				
				//consultar los descuentos del producto: promociones
				Promociones promociones = promocionesBO.getPromocionesVip(ctacliente.getClientes().getTipocliente().getIdtipocliente(), producto.getIdproducto());
				
				//consultar los servicios del producto: prodservicio
				List<Servicio> lisServicio = servicioBO.lisServicioByIdProducto(producto.getIdproducto());
				List<ServicioValor> lisServicioValor = new ArrayList<ServicioValor>();
				float totalServicios = 0;
				float totalDescuentos = 0;
				float totalImpuestos = 0;
				for(Servicio servicio : lisServicio){
					ServicioValor servicioValor = new ServicioValor();
					servicioValor.setServicio(servicio);
					totalPagarProducto += servicio.getCostoservicio().getCosto();
					totalServicios += servicio.getCostoservicio().getCosto();
					totalServiciosContrato += servicio.getCostoservicio().getCosto();
					
					//si hubo una promocion se calcula el descuento
					if(promociones != null && promociones.getIdpromocion() > 0){
						servicioValor.setValorDescuento(-1 * servicio.getCostoservicio().getCosto() * promociones.getDescuento());
					}else{
						servicioValor.setValorDescuento(0);
					}
					servicioValor.setNombreDescuento("Descuento " + servicio.getNombre());
					totalPagarProducto += servicioValor.getValorDescuento();
					totalDescuentos += servicioValor.getValorDescuento();
					totalDescuentosContrato += servicioValor.getValorDescuento();
					
					//consultar los impuestos del servicio: impservicios
					List<Impservicios> lisImpservicios = impserviciosBO.lisImpserviciosByIdTipoServicio(servicio.getTiposervicio().getIdtiposervicio());
					List<ImpuestoValor> lisImpuestoValor = new ArrayList<ImpuestoValor>();
					float valoriva = 0;
					float valorice = 0;
					for(Impservicios impservicios : lisImpservicios){
						float valor = 0;
						ImpuestoValor impuestoValor = new ImpuestoValor();
						//basandose en que el orden es: primero iva, segundo ice, ....
						//primero obtenemos el iva ya que el ice depende del iva (servicio - descuentos + iva)*%ice
						if(impservicios.getDescripcion().compareToIgnoreCase("iva") == 0){
							//iva = (servicio - descuentos)*%iva
							valoriva = (servicio.getCostoservicio().getCosto() + servicioValor.getValorDescuento()) * impservicios.getValimpuesto();
							valor = valoriva;
						}else{
							if(impservicios.getDescripcion().compareToIgnoreCase("ice") == 0){
								valorice = (servicio.getCostoservicio().getCosto() + servicioValor.getValorDescuento() + valoriva) * impservicios.getValimpuesto();
								valor = valorice;
							}
						}
						
						impuestoValor.setImpservicios(impservicios);
						impuestoValor.setValor(valor);
						
						lisImpuestoValor.add(impuestoValor);
						
						totalPagarProducto += valor;
						totalImpuestos += valor;
						totalImpuestosContrato += valor;
						/*ImpuestoValor impuestoValor = new ImpuestoValor();
						impuestoValor.setImpservicios(impservicios);
						float valor = (servicio.getCostoservicio().getCosto() + servicioValor.getValorDescuento()) * impservicios.getValimpuesto();
						impuestoValor.setValor(valor);
						
						lisImpuestoValor.add(impuestoValor);
						
						totalPagarProducto += valor;
						totalImpuestos += valor;
						totalImpuestosContrato += valor;*/
					}
					servicioValor.setLisImpuestoValor(lisImpuestoValor);
					lisServicioValor.add(servicioValor);
				}
				
				detalleContratoPojo.setLisServicioValor(lisServicioValor);
				detalleContratoPojo.setTotalServicios(totalServicios);
				detalleContratoPojo.setTotalDescuentos(totalDescuentos);
				detalleContratoPojo.setTotalImpuestos(totalImpuestos);
				detalleContratoPojo.setTotalPagar(totalPagarProducto);
				
				totalPagarContrato += totalPagarProducto;
				lisDetalleContratoPojo.add(detalleContratoPojo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
            new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}
	
	public void agregarQuitarFactura(){
		try{
			FacturaBO facturaBO = new FacturaBO();
			
			//Busca la factura en el detalle
			boolean existe = false;
			DetalleFacturaPojo detalleEncontrado = new DetalleFacturaPojo();
			for(DetalleFacturaPojo tmp : lisDetalleFacturaPojo){
				if(tmp.getFactura().equals(facturaSeleccionada)){
					existe = true;
					detalleEncontrado = tmp;
					break;
				}
			}
			
			if(existe){
				//QUITAR
				//Si la factura ya se encuentra en el detalle entonces se la quita del detalle
				lisDetalleFacturaPojo.remove(detalleEncontrado);
				//Se restaura el total de abonos
				//totalAbonosCuenta += detalleEncontrado.getTotalPagosByFactura();
				//Se resta del total de facturas el total de la factura seleccionada
				totalFacturas -= detalleEncontrado.getFactura().getValpendiente();
			}
			else{
				//AGREGRAR
				//Si la factura no se encuentra en el detalle entonces se la agrega al detalle
				DetalleFacturaPojo detalleFacturaPojo = new DetalleFacturaPojo();
				detalleFacturaPojo.setFactura(facturaSeleccionada);

				//Consulto los cargos de la factura
				CargosBO cargosBO = new CargosBO();
				List<Cargos> lisCargosNivelDetalle = null;
				List<Cargos> lisCargosNivelDescuento = null;
				List<Cargos> lisCargosNivelImpuesto = null;
				if(activeTab.compareTo(TAB_FACTURA_TITLE) == 0){
					lisCargosNivelDetalle = cargosBO.lisCargosFacturaNivelDetalle(facturaSeleccionada.getIdfactura());
					lisCargosNivelDescuento = cargosBO.lisCargosFacturaNivelDescuento(facturaSeleccionada.getIdfactura());
					lisCargosNivelImpuesto = cargosBO.lisCargosFacturaNivelImpuestoSum(facturaSeleccionada.getIdfactura());
					
					//Nuevo - Si factura esta en mora
					if(facturaSeleccionada.getIdestado() == Parametro.FACTURA_ESTADO_MORA){
						//Funcion postgres retorna data
						Object args[] = new Object[1];
						facturaBO.obtenerRubrosMora(2, args);
						String[][] rubros = (String[][]) args[0];
						
						/*
						[0][0] descripción
						[0][1] valor del cargo fraccionado
						[0][2] valor del descuento fraccionado
						[0][3] código de línea de la transacción
						 */
						
						float totalRubrosProductos = 0;
						float totalRubrosDescuento = 0;
						float totalRubrosImpuesto = 0;
						//Agrego rubros de detalle nuevos
						boolean finCiclo = false;
						int fila = 0;
						while(!finCiclo && fila < rubros.length){
							//linea de transaccion en cero quiere decir fin
							if(Integer.parseInt(rubros[fila][3]) > 0 ){
								
								//linea de transaccion = 1 es el producto
								if(Integer.parseInt(rubros[fila][3]) == 1){
									float valorServicio = Float.parseFloat(rubros[fila][1]);
									float valorDescuento = Float.parseFloat(rubros[fila][2]);
									
									Cargos cargosNivelDetalle = new Cargos();
									cargosNivelDetalle.setIdcargo(-1);
									cargosNivelDetalle.setIdfactura(facturaSeleccionada.getIdfactura());
									cargosNivelDetalle.setFactura(facturaSeleccionada);
									cargosNivelDetalle.setValcargo(valorServicio);
									cargosNivelDetalle.setMotivo(rubros[fila][0]);
									cargosNivelDetalle.setValpendiente(valorServicio);
									cargosNivelDetalle.setValbase(valorServicio);
									cargosNivelDetalle.setDescuento(valorDescuento);
									cargosNivelDetalle.setIdrubropadre(0);
									cargosNivelDetalle.setIdcuenta(idcuenta);
									totalRubrosProductos += valorServicio;
									
									lisCargosNivelDetalle.add(cargosNivelDetalle);
									
									
									Cargos cargosNivelDescuento = new Cargos();
									cargosNivelDescuento.setIdcargo(-1);
									cargosNivelDescuento.setIdfactura(facturaSeleccionada.getIdfactura());
									cargosNivelDescuento.setFactura(facturaSeleccionada);
									cargosNivelDescuento.setValcargo(valorDescuento);
									cargosNivelDescuento.setMotivo("Descuento "+rubros[fila][0]);
									cargosNivelDescuento.setValpendiente(valorDescuento);
									cargosNivelDescuento.setValbase(valorServicio);
									cargosNivelDescuento.setDescuento(0f);
									cargosNivelDescuento.setIdrubropadre(0);
									cargosNivelDescuento.setIdcuenta(idcuenta);
									totalRubrosDescuento += valorDescuento;
									
									lisCargosNivelDescuento.add(cargosNivelDescuento);
								}else{
									//linea de transaccion = 2 es el impuesto
									if(Integer.parseInt(rubros[fila][3]) == 2){
										float valorImpuesto = Float.parseFloat(rubros[fila][1]);
										
										Cargos cargosNivelImpuesto = new Cargos();
										cargosNivelImpuesto.setIdcargo(-1);
										cargosNivelImpuesto.setIdfactura(facturaSeleccionada.getIdfactura());
										cargosNivelImpuesto.setFactura(facturaSeleccionada);
										cargosNivelImpuesto.setValcargo(valorImpuesto);
										cargosNivelImpuesto.setMotivo(rubros[fila][0]);
										cargosNivelImpuesto.setValpendiente(valorImpuesto);
										cargosNivelImpuesto.setValbase(0f);
										cargosNivelImpuesto.setDescuento(0f);
										cargosNivelImpuesto.setIdrubropadre(0);
										cargosNivelImpuesto.setIdcuenta(idcuenta);
										totalRubrosImpuesto += valorImpuesto;
										
										lisCargosNivelImpuesto.add(cargosNivelImpuesto);
									}
								}
							}else{
								finCiclo = true;
							}
							
							fila++;
						}
						
						//Actualizo factura
						float valbruto = facturaSeleccionada.getValbruto() + totalRubrosProductos;
						facturaSeleccionada.setValbruto(valbruto);
						
						float valdescuentos = facturaSeleccionada.getValdescuentos() + totalRubrosDescuento;
						facturaSeleccionada.setValdescuentos(valdescuentos);
						
						float subtotal = facturaSeleccionada.getValbase() + totalRubrosProductos - totalRubrosDescuento;
						facturaSeleccionada.setValbase(subtotal);
						
						float valimpuestos = facturaSeleccionada.getValimpuestos() + totalRubrosImpuesto;
						facturaSeleccionada.setValimpuestos(valimpuestos);
						
						float valtotal = facturaSeleccionada.getValbase() + facturaSeleccionada.getValimpuestos();
						facturaSeleccionada.setValtotal(valtotal);
						
						//Actualizo el total de la factura le sumo rubros de detalle e impuestos
						float valpendiente = facturaSeleccionada.getValpendiente() + totalRubrosProductos + totalRubrosImpuesto - totalRubrosDescuento;
						facturaSeleccionada.setValpendiente(valpendiente);
					}
				}else{
					if(activeTab.compareTo(TAB_GENERACION_TITLE) == 0){
						lisCargosNivelDetalle = cargosBO.lisCargosGeneracionNivelDetalle(facturaSeleccionada.getIdsecuencia());
						lisCargosNivelDescuento = cargosBO.lisCargosGeneracionNivelDescuento(facturaSeleccionada.getIdsecuencia());
						lisCargosNivelImpuesto = cargosBO.lisCargosGeneracionNivelImpuestoSum(facturaSeleccionada.getIdsecuencia());
					}
				}
				detalleFacturaPojo.setLisCargosNivelDetalle(lisCargosNivelDetalle);
				detalleFacturaPojo.setLisCargosNivelDescuento(lisCargosNivelDescuento);
				detalleFacturaPojo.setLisCargosNivelImpuesto(lisCargosNivelImpuesto);
				
				//SE COMENTA PORQUE EN LOS ABONOS CONSULTADOS A LA TABLA PAGOS VIENE INCLUIDO CREDITOS Y EXCEDENTES, LO QUE HACE DUPLICAR CON LOS CREDITOS Y EXCEDENTES POR FACTURA
				/*
				//Consulto los abonos de la factura
				PagosBO pagosBO = new PagosBO(); 
				List<Pagos> lisPagosByFactura = pagosBO.lisPagosAbonosActivosByFactura(facturaSeleccionada.getIdfactura());
				detalleFacturaPojo.setLisPagosByFactura(lisPagosByFactura);
				//Sumo los abonos
				float totalPagosByFactura = 0;
				for(Pagos pagos : lisPagosByFactura){
					totalPagosByFactura += pagos.getValtotal();
				}
				//Sumo los abonos de las facturas seleccionadas
				totalAbonosFacturasSeleccionadas += totalPagosByFactura;
				detalleFacturaPojo.setTotalPagosByFactura(totalPagosByFactura);
				//Resto el abono de la factura seleccionada del total de abonos de la cuenta
				totalAbonosCuenta -= totalPagosByFactura;
				*/
				
				//Consulto los abonos de la factura que no sean creditos ni excedentes
				TpagosBO tpagosBO = new TpagosBO();
				List<Tpagos> lisTpagosByFactura = tpagosBO.lisTpagosAbonosByFactura(facturaSeleccionada.getIdfactura());
				detalleFacturaPojo.setLisTpagosDetalleAbonosByFactura(lisTpagos);
				//Sumo los abonos
				float totalPagosByFactura = 0;
				for(Tpagos tpagos : lisTpagosByFactura){
					totalPagosByFactura += tpagos.getValpago();
				}
				detalleFacturaPojo.setTotalTpagosAbonosByFactura(totalPagosByFactura);
				
				//Sumo el total de la factura seleccionada al total de las facturas
				totalFacturas += facturaSeleccionada.getValpendiente();
				
				lisDetalleFacturaPojo.add(detalleFacturaPojo);
			}
			
			//Recalcula el total general
			totalGeneral = 0;
			for(DetalleFacturaPojo tmp : lisDetalleFacturaPojo ){
				totalGeneral += tmp.getFactura().getValpendiente();
			}
			totalGeneral += - totalCreditosCuenta - /*totalAbonosCuenta - */totalExcedentesCuenta;
		}
		catch(Exception re){
            re.printStackTrace();
            new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
        }
	}
	
	public void agregarFormaPago(){
		
		if(validacionEditorOk()){
			lisTpagos.add(tpagosEditor);
			totalFormasPago = 0;
			
			//Al agregar formas de pago obtengo el total de las formas de pago
			for(Tpagos tpagos : lisTpagos){
				totalFormasPago += tpagos.getValpago();
			}
			
			limpiarEditor();
		}
	}
	
	private boolean validacionEditorOk(){
		boolean ok = false;
		
		//focusId = "idTabTipoPagos";
		
		if(tpagosEditor.getFpago() != null && tpagosEditor.getFpago().getIdfpago() > 0){
			ok = true;
		}else{
			focusId = "idFormaPago";
			new MessageUtil().showWarnMessage("Aviso", "Debe especificar la forma de pago");
		}
		
		if(ok){
			if(tpagosEditor.getValpago() <= 0){
				ok = false;
				focusId = "idValorPago";
				new MessageUtil().showWarnMessage("Aviso", "Debe especificar un valor en la forma de pago");
			}
		}
		
		return ok;
	}
	
	private void limpiarEditor(){
		tpagosEditor = new Tpagos(0, new Fpago(), new Bancos(), new Pagos(), new Bancos(), 0, 3, 0, 0);
	}
	
	public void quitarFormaPago(){
		try {
			lisTpagos.remove(tpagosSelected);
			totalFormasPago -= tpagosSelected.getValpago();
			
			new MessageUtil().showInfoMessage("Listo!", "Forma de pago excluida!");
		} catch(Exception re) {
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}
	
	public void grabarFactura(){
		if(isBotonRendered){
			if(validacionGrabarOk()){
				
				try{
					FacturaBO facturaBO = new FacturaBO();
					boolean ok = false;
					String numeroFacturaParam[] = {""};
					
					if(activeTab.compareTo(TAB_FACTURA_TITLE) == 0){
						ok = facturaBO.procesarFacturasCreditosExcedentes(lisDetalleFacturaPojo, lisTpagos, idcuenta);
					}else{
						if(activeTab.compareTo(TAB_GENERACION_TITLE) == 0){
							ok = facturaBO.procesarGeneracionCreditosExcedentes(lisDetalleFacturaPojo, lisTpagos, idcuenta, numeroFacturaParam);
						}else{
							if(activeTab.compareTo(TAB_CONTRATOS_TITLE) == 0){
								ok = facturaBO.procesarContratosFormasPago(lisDetalleContratoPojo, lisTpagos, idcuenta, numeroFacturaParam);
								/*if(ok){
									imprimirFactura(numeroFacturaParam[0]);
								}*/
							}
						}
					}
					
					if(ok){
						//new MessageUtil().showInfoMessage("Aviso!", "Transacción completada con Exito!");
						numeroFactura = numeroFacturaParam[0];
						
						isBotonRendered = false;
						//limpiar todo, se reinvoca la pagina
						//FacesUtil facesUtil = new FacesUtil();
						//facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta=" + idcuenta);
						//redirect = "cliente.jsf?faces-redirect=true&idcuenta=" + idcuenta;
					}else{
						new MessageUtil().showErrorMessage("Aviso!", "No se ha podido pagar la Factura!");
					}
					
				}catch(SecuenciaFacturaException e){
					e.printStackTrace();
					new MessageUtil().showErrorMessage("Atención!", "Secuencias de Facturas agotadas o caducadas!");
				}catch(Exception re) {
					re.printStackTrace();
					new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
				}
			}
		}
		else{
			new MessageUtil().showInfoMessage("Aviso!", "Factura ya ha sido procesada!");
		}
	}
	
	private boolean validacionGrabarOk(){
		boolean ok = false;
		
		if(activeTab.compareTo(TAB_FACTURA_TITLE) == 0 || activeTab.compareTo(TAB_GENERACION_TITLE) == 0){
			//Debe haber seleccionado al menos una factura para pagar
			if(lisDetalleFacturaPojo != null && lisDetalleFacturaPojo.size() > 0){
				ok = true;
			}else{
				new MessageUtil().showWarnMessage("Aviso", "Debe seleccionar la(s) factura(s) a pagar");
			}
		}else{
			ok = true;
		}
		
		if(ok){
			if(activeTab.compareTo(TAB_FACTURA_TITLE) == 0 || activeTab.compareTo(TAB_GENERACION_TITLE) == 0){
				if(totalGeneral > 0){
					//Si total general es positivo, es decir, hay un monto a pagar
					//debe seleccionar la forma de pago
					if(totalFormasPago <= 0 && totalCreditosCuenta <= 0 && totalExcedentesCuenta <= 0){
						ok = false;
						new MessageUtil().showWarnMessage("Aviso", "Debe ingresar la(s) forma(s) de pago");
					}
				}else{
					//Si total general es negativo o cero, es decir, factura se paga con saldo a favor
					//no es necesario especificar formas de pago, y si especifica sera tomado como excedente
				}
			}else{
				if(totalPagarContrato > 0){
					//Si total contrato es positivo, es decir, hay un monto a pagar
					//debe seleccionar la forma de pago
					if(totalFormasPago <= 0){
						ok = false;
						new MessageUtil().showWarnMessage("Aviso", "Debe ingresar la(s) forma(s) de pago");
					}
				}else{
					//Si total general es negativo o cero, es decir, factura se paga con saldo a favor
					//no es necesario especificar formas de pago, y si especifica sera tomado como excedente
				}
			}
		}
		
		return ok;
	}
	
	/*public void imprimirFactura(String numeroFactura){
		try {
			String nombreReporte = "factura";
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("P_IDFACTURA", numeroFactura);
				
			new Utilities().imprimirJasperPdf(nombreReporte, parametros);
		}catch (Exception e) {
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}*/

	public Ctacliente getCtacliente() {
		return ctacliente;
	}

	public void setCtacliente(Ctacliente ctacliente) {
		this.ctacliente = ctacliente;
	}

	public List<Factura> getLisFacturaCliente() {
		return lisFacturaCliente;
	}

	public void setLisFacturaCliente(List<Factura> lisFacturaCliente) {
		this.lisFacturaCliente = lisFacturaCliente;
	}

	public String getTAB_ANTICIPO_TITLE() {
		return TAB_ANTICIPO_TITLE;
	}

	public String getTAB_FACTURA_TITLE() {
		return TAB_FACTURA_TITLE;
	}

	public String getTAB_GENERACION_TITLE() {
		return TAB_GENERACION_TITLE;
	}

	public String getTAB_CONTRATOS_TITLE() {
		return TAB_CONTRATOS_TITLE;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
	
	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}

	public Factura getFacturaSeleccionada() {
		return facturaSeleccionada;
	}

	public void setFacturaSeleccionada(Factura facturaSeleccionada) {
		this.facturaSeleccionada = facturaSeleccionada;
	}

	public List<DetalleFacturaPojo> getLisDetalleFacturaPojo() {
		return lisDetalleFacturaPojo;
	}

	public void setLisDetalleFacturaPojo(
			List<DetalleFacturaPojo> lisDetalleFacturaPojo) {
		this.lisDetalleFacturaPojo = lisDetalleFacturaPojo;
	}

	public List<Creditos> getLisCreditosCuenta() {
		return lisCreditosCuenta;
	}

	public void setLisCreditosCuenta(List<Creditos> lisCreditosCuenta) {
		this.lisCreditosCuenta = lisCreditosCuenta;
	}

	public float getTotalCreditosCuenta() {
		return totalCreditosCuenta;
	}

	public void setTotalCreditosCuenta(float totalCreditosCuenta) {
		this.totalCreditosCuenta = totalCreditosCuenta;
	}

	public float getTotalCreditosFacturasSeleccionadas() {
		return totalCreditosFacturasSeleccionadas;
	}

	public void setTotalCreditosFacturasSeleccionadas(float totalCreditosFacturasSeleccionadas) {
		this.totalCreditosFacturasSeleccionadas = totalCreditosFacturasSeleccionadas;
	}

	public List<Pagos> getLisPagosCuenta() {
		return lisPagosCuenta;
	}

	public void setLisPagosCuenta(List<Pagos> lisPagosCuenta) {
		this.lisPagosCuenta = lisPagosCuenta;
	}

	public float getTotalAbonosCuenta() {
		return totalAbonosCuenta;
	}

	public void setTotalAbonosCuenta(float totalAbonosCuenta) {
		this.totalAbonosCuenta = totalAbonosCuenta;
	}

	public float getTotalExcedentesCuenta() {
		return totalExcedentesCuenta;
	}

	public void setTotalExcedentesCuenta(float totalExcedentesCuenta) {
		this.totalExcedentesCuenta = totalExcedentesCuenta;
	}

	public float getTotalGeneral() {
		return totalGeneral;
	}

	public void setTotalGeneral(float totalGeneral) {
		this.totalGeneral = totalGeneral;
	}

	public List<Fpago> getLisFpago() {
		return lisFpago;
	}

	public void setLisFpago(List<Fpago> lisFpago) {
		this.lisFpago = lisFpago;
	}

	public List<Bancos> getLisEntidadBancos() {
		return lisEntidadBancos;
	}

	public void setLisEntidadBancos(List<Bancos> lisEntidadBancos) {
		this.lisEntidadBancos = lisEntidadBancos;
	}

	public Fpago getFpagoSelected() {
		return fpagoSelected;
	}

	public void setFpagoSelected(Fpago fpagoSelected) {
		this.fpagoSelected = fpagoSelected;
	}

	public Bancos getEntidadBancosSelected() {
		return entidadBancosSelected;
	}

	public void setEntidadBancosSelected(Bancos entidadBancosSelected) {
		this.entidadBancosSelected = entidadBancosSelected;
	}

	public Bancos getEntidadTarjetaSelected() {
		return entidadTarjetaSelected;
	}

	public void setEntidadTarjetaSelected(Bancos entidadTarjetaSelected) {
		this.entidadTarjetaSelected = entidadTarjetaSelected;
	}

	public List<Bancos> getLisEntidadTarjeta() {
		return lisEntidadTarjeta;
	}

	public void setLisEntidadTarjeta(List<Bancos> lisEntidadTarjeta) {
		this.lisEntidadTarjeta = lisEntidadTarjeta;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Tpagos getTpagosEditor() {
		return tpagosEditor;
	}

	public void setTpagosEditor(Tpagos tpagosEditor) {
		this.tpagosEditor = tpagosEditor;
	}

	public List<Tpagos> getLisTpagos() {
		return lisTpagos;
	}

	public void setLisTpagos(List<Tpagos> lisTpagos) {
		this.lisTpagos = lisTpagos;
	}

	public Tpagos getTpagosSelected() {
		return tpagosSelected;
	}

	public void setTpagosSelected(Tpagos tpagosSelected) {
		this.tpagosSelected = tpagosSelected;
	}

	public float getTotalFormasPagos() {
		return totalFormasPago;
	}

	public void setTotalFormasPagos(float totalFormasPago) {
		this.totalFormasPago = totalFormasPago;
	}

	public float getTotalFacturas() {
		return totalFacturas;
	}

	public void setTotalFacturas(float totalFacturas) {
		this.totalFacturas = totalFacturas;
	}

	public boolean isBotonRendered() {
		return isBotonRendered;
	}

	public String getFocusId() {
		return focusId;
	}

	public void setFocusId(String focusId) {
		this.focusId = focusId;
	}

	public void setBotonRendered(boolean isBotonRendered) {
		this.isBotonRendered = isBotonRendered;
	}

	public boolean isTabAnticipadosRendered() {
		return isTabAnticipadosRendered;
	}

	public void setTabAnticipadosRendered(boolean isTabAnticipadosRendered) {
		this.isTabAnticipadosRendered = isTabAnticipadosRendered;
	}

	public boolean isTabFacturaRendered() {
		return isTabFacturaRendered;
	}

	public void setTabFacturaRendered(boolean isTabFacturaRendered) {
		this.isTabFacturaRendered = isTabFacturaRendered;
	}

	public boolean isTabGeneracionRendered() {
		return isTabGeneracionRendered;
	}

	public void setTabGeneracionRendered(boolean isTabGeneracionRendered) {
		this.isTabGeneracionRendered = isTabGeneracionRendered;
	}

	public boolean isTabContratosRendered() {
		return isTabContratosRendered;
	}

	public void setTabContratosRendered(boolean isTabContratosRendered) {
		this.isTabContratosRendered = isTabContratosRendered;
	}

	public List<DetalleContratoPojo> getLisDetalleContratoPojo() {
		return lisDetalleContratoPojo;
	}

	public void setLisDetalleContratoPojo(
			List<DetalleContratoPojo> lisDetalleContratoPojo) {
		this.lisDetalleContratoPojo = lisDetalleContratoPojo;
	}

	public float getTotalServiciosContrato() {
		return totalServiciosContrato;
	}

	public void setTotalServiciosContrato(float totalServiciosContrato) {
		this.totalServiciosContrato = totalServiciosContrato;
	}

	public float getTotalDescuentosContrato() {
		return totalDescuentosContrato;
	}

	public void setTotalDescuentosContrato(float totalDescuentosContrato) {
		this.totalDescuentosContrato = totalDescuentosContrato;
	}

	public float getTotalImpuestosContrato() {
		return totalImpuestosContrato;
	}

	public void setTotalImpuestosContrato(float totalImpuestosContrato) {
		this.totalImpuestosContrato = totalImpuestosContrato;
	}

	public float getTotalPagarContrato() {
		return totalPagarContrato;
	}

	public void setTotalPagarContrato(float totalPagarContrato) {
		this.totalPagarContrato = totalPagarContrato;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

}
