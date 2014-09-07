package bo.negocio;

import global.Parametro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Cabimpfacturas;
import pojo.annotations.Cargos;
import pojo.annotations.Creditos;
import pojo.annotations.Ctacliente;
import pojo.annotations.Detimpfacturas;
import pojo.annotations.Estado;
import pojo.annotations.Excedentes;
import pojo.annotations.Factura;
import pojo.annotations.Fpago;
import pojo.annotations.Nrosfactura;
import pojo.annotations.Pagos;
import pojo.annotations.Tpagos;
import pojo.annotations.custom.DetalleContratoPojo;
import pojo.annotations.custom.DetalleFacturaPojo;
import pojo.annotations.custom.ImpuestoValor;
import pojo.annotations.custom.ServicioValor;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.CabimpfacturasDAO;
import dao.datos.CargosDAO;
import dao.datos.CreditosDAO;
import dao.datos.CtaclienteDAO;
import dao.datos.DetimpfacturasDAO;
import dao.datos.ExcedentesDAO;
import dao.datos.FacturaDAO;
import dao.datos.PagosDAO;
import dao.datos.TpagosDAO;
import exceptions.SecuenciaFacturaException;

public class FacturaBO {

	FacturaDAO facturaDAO;
	
	public FacturaBO() {
		facturaDAO = new FacturaDAO();
	}
	
	public Factura getFacturaById(int idsecuencia) throws Exception {
		Factura factura = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            factura = facturaDAO.getFacturaById(session, idsecuencia);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return factura;
	}
	
	public List<Factura> lisFacturaParaFacturacion(int idcuenta) throws Exception {
		List<Factura> lisFactura = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisFactura = facturaDAO.lisFacturaParaFacturacion(session, idcuenta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisFactura;
	}
	
	public List<Factura> lisFacturaParaCargosGenerados(int idcuenta) throws Exception {
		List<Factura> lisFactura = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisFactura = facturaDAO.lisFacturaParaCargosGenerados(session, idcuenta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisFactura;
	}
	
	public List<Factura> lisFacturaByFechas(int idcuenta, Date fechaDesde, Date fechaHasta) throws Exception {
		List<Factura> lisFactura = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisFactura = facturaDAO.lisFacturaByFechas(session, idcuenta, fechaDesde, fechaHasta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisFactura;
	}
	
	public List<Factura> lisFacturaParaCreditosByIdcuenta(int idcuenta) throws Exception {
		List<Factura> lisFactura = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisFactura = facturaDAO.lisFacturaParaCreditosByIdcuenta(session, idcuenta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisFactura;
	}

	public boolean procesarFacturasCreditosExcedentes(List<DetalleFacturaPojo> lisDetalleFacturaPojo, List<Tpagos> lisTpagos, int idcuenta) throws Exception {
		boolean ok = false;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			CreditosDAO creditosDAO = new CreditosDAO();
			PagosDAO pagosDAO = new PagosDAO();
			TpagosDAO tpagosDAO = new TpagosDAO();
			ExcedentesDAO excedentesDAO = new ExcedentesDAO();
			CargosDAO cargosDAO = new CargosDAO();
			
			CreditosBO creditosBO = new CreditosBO();
			ExcedentesBO excedentesBO = new ExcedentesBO();
			
			List<Tpagos> lisFormasPagoCuenta = new ArrayList<Tpagos>(lisTpagos);
			Date fecharegistro = new Date();
			
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			for(DetalleFacturaPojo detalleFacturaPojo : lisDetalleFacturaPojo){
				//RECORRO MIS FACTURAS PARA APLICARLE LOS CREDITOS, EXCEDENTES
				Factura factura = detalleFacturaPojo.getFactura().clonar();
				float facturaValpendiente_Credito = new Float(factura.getValpendiente());
				float facturaValpendiente_Excedente = new Float(factura.getValpendiente());
				float facturaValpendiente_FormaPago = new Float(factura.getValpendiente());
				float totalCreditosUsar = 0;
				float totalExcedentesUsar = 0;
				float totalFormasPagoUsar = 0;
				
				//Consulto los creditos disponibles de la cuenta
				List<Creditos> lisCreditosCuenta = creditosBO.lisCreditosActivosByCuenta(idcuenta); 
				//Sumo los creditos de la cuenta
				float totalCreditosCuenta = 0;
				for(Creditos creditos : lisCreditosCuenta){
					totalCreditosCuenta += creditos.getVacredito();
				}
				
				//Consulto los excedentes disponibles de la cuenta
				List<Excedentes> lisExcedentesCuenta = excedentesBO.lisExcedentesActivosByCuenta(idcuenta);
				//Sumo los excedentes de la cuenta
				float totalExcedentesCuenta = 0;
				for(Excedentes excedentes : lisExcedentesCuenta){
					totalExcedentesCuenta += excedentes.getValpendiente();
				}
				
				//Consulto las formas de pago disponibles de la cuenta
				List<Tpagos> lisTpagosTmp2 = new ArrayList<Tpagos>();
				for(Tpagos tpagos : lisFormasPagoCuenta){
					if(tpagos.getIdestado() == Parametro.TPAGOS_ESTADO_PENDIENTE){
						lisTpagosTmp2.add(tpagos.clonar());
					}
				}
				lisFormasPagoCuenta = new ArrayList<Tpagos>(lisTpagosTmp2);
				//Sumo las formas de pago de la cuenta
				float totalFormasPagoCuenta = 0;
				for(Tpagos tpagos : lisFormasPagoCuenta){
					totalFormasPagoCuenta += tpagos.getValpago();
				}
				
				//Si la factura está en mora, mando a grabar los cargos
				if(factura.getIdestado() == Parametro.FACTURA_ESTADO_MORA){
					short nivelServicio = Parametro.CARGO_NIVEL_SERVICIO_MIN;
					short nivelDescuento = Parametro.CARGO_NIVEL_DESCUENTO_MIN;
					short nivelImpuesto = Parametro.CARGO_NIVEL_IMPUESTO_MIN;
					
					for(Cargos cargoDetalle : detalleFacturaPojo.getLisCargosNivelDetalle()){
						if(cargoDetalle.getIdcargo() == -1){
							int idcargodetalle = cargosDAO.maxIdCargos(session) + 1;
							cargoDetalle.setIdcargo(idcargodetalle);
							cargoDetalle.setNivel(nivelServicio);
							Estado estado = new Estado();
							estado.setIdestado(Parametro.CARGOS_ESTADO_PAGADO);
							cargoDetalle.setEstado(estado);
							
							nivelServicio++;
							
							//Auditoria
							fecharegistro = new Date();
							cargoDetalle.setFecha(fecharegistro);
							cargoDetalle.setUsuario(usuarioBean.getUsuario());
							cargoDetalle.setEmpresa(usuarioBean.getUsuario().getEmpresa());
							
							//grabo productos en cargos
							cargosDAO.ingresarCargos(session, cargoDetalle);
						}
					}
					for(Cargos cargoDescuento : detalleFacturaPojo.getLisCargosNivelDescuento()){
						if(cargoDescuento.getIdcargo() == -1){
							int idcargodescuento = cargosDAO.maxIdCargos(session) + 1;
							cargoDescuento.setIdcargo(idcargodescuento);
							cargoDescuento.setNivel(nivelDescuento);
							Estado estado = new Estado();
							estado.setIdestado(Parametro.CARGOS_ESTADO_PAGADO);
							cargoDescuento.setEstado(estado);
							
							nivelDescuento++;
							
							//Auditoria
							fecharegistro = new Date();
							cargoDescuento.setFecha(fecharegistro);
							cargoDescuento.setUsuario(usuarioBean.getUsuario());
							cargoDescuento.setEmpresa(usuarioBean.getUsuario().getEmpresa());
							
							//grabo descuentos en cargos
							cargosDAO.ingresarCargos(session, cargoDescuento);
						}
					}
					for(Cargos cargoImpuesto : detalleFacturaPojo.getLisCargosNivelImpuesto()){
						if(cargoImpuesto.getIdcargo() == -1){
							int idcargoimpuesto = cargosDAO.maxIdCargos(session) + 1;
							cargoImpuesto.setIdcargo(idcargoimpuesto);
							cargoImpuesto.setNivel(nivelImpuesto);
							Estado estado = new Estado();
							estado.setIdestado(Parametro.CARGOS_ESTADO_PAGADO);
							cargoImpuesto.setEstado(estado);
							
							nivelImpuesto++;
							
							//Auditoria
							fecharegistro = new Date();
							cargoImpuesto.setFecha(fecharegistro);
							cargoImpuesto.setUsuario(usuarioBean.getUsuario());
							cargoImpuesto.setEmpresa(usuarioBean.getUsuario().getEmpresa());
							
							//grabo impuestos en cargos
							cargosDAO.ingresarCargos(session, cargoImpuesto);
						}
					}
				}
				
				if(totalCreditosCuenta >= facturaValpendiente_Credito){
					//SI MIS CREDITOS CUBREN EL TOTAL DE LA FACTURA O MAS
					//al valor del credito en la factura se le sumará el total de la factura
					factura.setValcreditos(factura.getValcreditos() + facturaValpendiente_Credito);
					//El estado de la factura pasa a pagada
					factura.setIdestado(Parametro.FACTURA_ESTADO_PAGADA);
					//Ya no hay valor pendiente
					factura.setValpendiente((float)0);
					//el total de creditos que voy a usar sera el total de la factura
					totalCreditosUsar = facturaValpendiente_Credito;

				}else{
					//SI MIS CREDITOS CUBREN UNA PARTE DE LA FACTURA
					//al valor del credito en la factura se le sumará el total de mis creditos 
					factura.setValcreditos(factura.getValcreditos() + totalCreditosCuenta);
					//al valor pendiente en la factura se le resta el valor del total de mis creditos
					factura.setValpendiente(facturaValpendiente_Credito - totalCreditosCuenta);
					//pasamos el valor pendiente de la factura para ser tomada por el excedente
					facturaValpendiente_Excedente = factura.getValpendiente();
					//el total de creditos que voy a usar sera el total de creditos de la cuenta
					totalCreditosUsar = totalCreditosCuenta;
					
					//APLICAMOS LOS EXCEDENTES SI MIS CREDITOS NO CUBRIERON EL TOTAL DE LA FACTURA
					if(totalExcedentesCuenta >= facturaValpendiente_Excedente){
						//SI MIS EXCEDENTES CUBREN EL TOTAL DE LA FACTURA O MAS
						//al valor del excedente en la factura se le sumará el total de la factura
						factura.setValorexcedentes(factura.getValorexcedentes() + facturaValpendiente_Excedente);
						//El estado de la factura pasa a pagada
						factura.setIdestado(Parametro.FACTURA_ESTADO_PAGADA);
						//Ya no hay valor pendiente
						factura.setValpendiente((float)0);
						//el total de excedentes que voy a usar sera el total de la factura
						totalExcedentesUsar = facturaValpendiente_Excedente;
					}else{
						//SI MIS EXCEDENTES CUBREN UNA PARTE DE LA FACTURA
						//al valor del excedente en la factura se le sumará el total de mis excedentes 
						factura.setValorexcedentes(factura.getValorexcedentes() + totalExcedentesCuenta);
						//al valor pendiente en la factura se le resta el valor del total de mis creditos
						factura.setValpendiente(facturaValpendiente_Excedente - totalExcedentesCuenta);
						//pasamos el valor pendiente de la factura para ser tomada por las formas de pago
						facturaValpendiente_FormaPago = factura.getValpendiente();
						//el total de creditos que voy a usar sera el total de creditos de la cuenta
						totalExcedentesUsar = totalExcedentesCuenta;
						
						//APLICAMOS LAS FORMAS DE PAGO SI MIS EXCEDENTES NO CUBRIERON EL TOTAL DE LA FACTURA
						if(totalFormasPagoCuenta >= facturaValpendiente_FormaPago){
							//SI MIS FORMAS DE PAGO CUBREN EL TOTAL DE LA FACTURA O MAS
							//El estado de la factura pasa a pagada
							factura.setIdestado(Parametro.FACTURA_ESTADO_PAGADA);
							//Ya no hay valor pendiente
							factura.setValpendiente((float)0);
							//el total de formas de pago que voy a usar sera el total de la factura
							totalFormasPagoUsar = facturaValpendiente_FormaPago;
						}else{
							//SI MIS FORMAS DE PAGO CUBREN UNA PARTE DE LA FACTURA
							//al valor pendiente en la factura se le resta el valor del total de mis formas de pago
							factura.setValpendiente(facturaValpendiente_FormaPago - totalFormasPagoCuenta);
							//el total de creditos que voy a usar sera el total de creditos de la cuenta
							totalFormasPagoUsar = totalFormasPagoCuenta;
						}
					}
				}
				
				//Auditoria
				factura.setFecha(fecharegistro);
				factura.setIp(usuarioBean.getIp());
				factura.setIdusuario(usuarioBean.getUsuario().getIdusuario());
				
				//Actualizo la factura
				facturaDAO.actualizarFactura(session, factura);
				
				//CREDITOS
				if(totalCreditosUsar > 0){
					
					//Obtengo Secuencia
					int IdpagopagosCredito = pagosDAO.maxIdPagos(session)+1;
					
					//si existieron creditos a usar creo el pago
					Pagos pagosCredito = new Pagos();
					pagosCredito.setIdpago(IdpagopagosCredito);
					pagosCredito.setIdfactura(factura.getIdfactura());
					pagosCredito.setIdgeneracion(factura.getIdgeneracion());
					pagosCredito.setValtotal(totalCreditosUsar);
					pagosCredito.setEmpresa(usuarioBean.getUsuario().getEmpresa());
					Estado estado = new Estado();
					estado.setIdestado(Parametro.PAGOS_ESTADO_ACTIVO);
					pagosCredito.setEstado(estado);
					pagosCredito.setIdcuenta(idcuenta);
					
					//Auditoria del pago
					fecharegistro = new Date();
					pagosCredito.setFecha(fecharegistro);
					pagosCredito.setIp(usuarioBean.getIp());
					pagosCredito.setUsuario(usuarioBean.getUsuario());
					
					//Creo la cabecera de pagos
					pagosDAO.ingresarPago(session, pagosCredito);
				
					for(Creditos creditos : lisCreditosCuenta){
						//RECORRO MIS CREDITOS A DAR DE BAJA
						if(totalCreditosUsar == 0){
							//Si ya no tengo que usar mas creditos salimos del ciclo y me sobrarian creditos para otra factura
							break;
						}
						float creditoUsado = 0;
						if(totalCreditosCuenta >= facturaValpendiente_Credito){
							//SI MIS CREDITOS CUBRIERON EL TOTAL DE LA FACTURA O MAS
							if(creditos.getVacredito() <= totalCreditosUsar){
								//si mi credito es menor o igual al total que debo usar
							
								//obtengo mi credito usado
								creditoUsado = creditos.getVacredito();
								//el valor del credito pasa a cero
								creditos.setVacredito(0);
								//El estado del credito pasa a consumido
								Estado estadoCredito = new Estado();
								estadoCredito.setIdestado(Parametro.CREDITO_ESTADO_CONSUMIDO);
								creditos.setEstado(estadoCredito);
								//descuento el valor del credito usado del total de creditos a usar
								totalCreditosUsar -= creditoUsado;
							}else{
								//si mi credito es mayor que el total de creditos a usar
								
								//obtengo mi credito usado
								creditoUsado = totalCreditosUsar;
								//al valor del credito se le resta el total de creditos a usar y me queda un saldo en el credito
								creditos.setVacredito(creditos.getVacredito() - totalCreditosUsar);
								//el total de creditos a usar queda en cero
								totalCreditosUsar = 0;
							}
						}else{
							//SI MIS CREDITOS CUBRIERON UNA PARTE DE LA FACTURA
							//obtengo mi credito usado
							creditoUsado = creditos.getVacredito();
							//el valor del credito pasa a cero
							creditos.setVacredito(0);
							//El estado del credito pasa a consumido
							Estado estadoCredito = new Estado();
							estadoCredito.setIdestado(Parametro.CREDITO_ESTADO_CONSUMIDO);
							creditos.setEstado(estadoCredito);
						}
						
						creditos.setIdpago(IdpagopagosCredito);
	
						//Auditoria
						fecharegistro = new Date();
						creditos.setFecha(fecharegistro);
						creditos.setIp(usuarioBean.getIp());
						creditos.setUsuario(usuarioBean.getUsuario());
						
						//Actualizo el credito
						creditosDAO.actualizarCreditos(session, creditos);
						
						//Obtengo Secuencia
						int IdtpagotpagosCredito = tpagosDAO.maxIdTpagos(session)+1;
						
						//Ingresar el credito en el detalle del pago
						Tpagos tpagosCredito = new Tpagos();
						tpagosCredito.setPagos(pagosCredito);
						tpagosCredito.setIdtpago(IdtpagotpagosCredito);
						tpagosCredito.setValpago(creditoUsado);
						Fpago fpago = new Fpago();
						fpago.setIdfpago(Parametro.TIPO_FORMA_PAGO_CREDITO);
						tpagosCredito.setFpago(fpago);
						tpagosCredito.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
						tpagosCredito.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
						
						//Auditoria
						tpagosCredito.setIdusuario(usuarioBean.getUsuario().getIdusuario());
						
						//Creo el pago del credito en el detalle de pagos
						tpagosDAO.ingresarTpago(session, tpagosCredito);
					}
				}
				
				//EXCEDENTES
				if(totalExcedentesUsar > 0){
					//Obtengo Secuencia
					int IdpagopagosExcedente = pagosDAO.maxIdPagos(session)+1;
					
					//si existieron excedentes a usar creo el pago
					Pagos pagosExcedente = new Pagos();
					pagosExcedente.setIdpago(IdpagopagosExcedente);
					pagosExcedente.setIdfactura(factura.getIdfactura());
					pagosExcedente.setIdgeneracion(factura.getIdgeneracion());
					pagosExcedente.setValtotal(totalExcedentesUsar);
					pagosExcedente.setEmpresa(usuarioBean.getUsuario().getEmpresa());
					Estado estado = new Estado();
					estado.setIdestado(Parametro.PAGOS_ESTADO_ACTIVO);
					pagosExcedente.setEstado(estado);
					pagosExcedente.setIdcuenta(idcuenta);
					
					//Auditoria del pago
					fecharegistro = new Date();
					pagosExcedente.setFecha(fecharegistro);
					pagosExcedente.setIp(usuarioBean.getIp());
					pagosExcedente.setUsuario(usuarioBean.getUsuario());
					
					//Creo la cabecera de pagos
					pagosDAO.ingresarPago(session, pagosExcedente);
				
					for(Excedentes excedentes : lisExcedentesCuenta){
						//RECORRO MIS EXCEDENTES A DAR DE BAJA
						if(totalExcedentesUsar == 0){
							//Si ya no tengo que usar mas excedentes salimos del ciclo y me sobrarian excedentes para otra factura
							break;
						}
						float excedenteUsado = 0;
						if(totalExcedentesCuenta >= facturaValpendiente_Excedente){
							//SI MIS EXCEDENTES CUBRIERON EL TOTAL DE LA FACTURA O MAS
							if(excedentes.getValpendiente() <= totalExcedentesUsar){
								//si mi excedente es menor o igual al total que debo usar
							
								//obtengo mi excedente usado
								excedenteUsado = excedentes.getValpendiente();
								//el valor del excedente pasa a cero
								excedentes.setValpendiente(0);
								//El estado del excedente pasa a consumido
								excedentes.setIdestado(Parametro.EXCEDENTE_ESTADO_CONSUMIDO);
								//descuento el valor del excedente usado del total de excedentes a usar
								totalExcedentesUsar -= excedenteUsado;
							}else{
								//si mi excedente es mayor que el total de excedentes a usar
								
								//obtengo mi excedente usado
								excedenteUsado = totalExcedentesUsar;
								//al valor del excedente se le resta el total de excedentes a usar y me queda un saldo en el excedente
								excedentes.setValpendiente(excedentes.getValpendiente() - totalExcedentesUsar);
								//el total de excedentes a usar queda en cero
								totalExcedentesUsar = 0;
							}
						}else{
							//SI MIS EXCEDENTES CUBRIERON UNA PARTE DE LA FACTURA
							//obtengo mi excedente usado
							excedenteUsado = excedentes.getValpendiente();
							//el valor del credito pasa a cero
							excedentes.setValpendiente(0);
							//El estado del excedente pasa a consumido
							excedentes.setIdestado(Parametro.EXCEDENTE_ESTADO_CONSUMIDO);
						}
	
						excedentes.setIdpago(IdpagopagosExcedente);
						
						//Auditoria
						fecharegistro = new Date();
						excedentes.setFecha(fecharegistro);
						excedentes.setIp(usuarioBean.getIp());
						excedentes.setIdusuario(usuarioBean.getUsuario().getIdusuario());
						
						//Actualizo el excedente
						excedentesDAO.actualizarExcedentes(session, excedentes);
						
						//Obtengo Secuencia
						int IdtpagotpagosExcedente = tpagosDAO.maxIdTpagos(session)+1;
						
						//Ingresar el excedente en el detalle del pago
						Tpagos tpagosExcedente = new Tpagos();
						tpagosExcedente.setPagos(pagosExcedente);
						tpagosExcedente.setIdtpago(IdtpagotpagosExcedente);
						tpagosExcedente.setValpago(excedenteUsado);
						Fpago fpago = new Fpago();
						fpago.setIdfpago(Parametro.TIPO_FORMA_PAGO_EXCEDENTE);
						tpagosExcedente.setFpago(fpago);
						tpagosExcedente.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
						tpagosExcedente.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
						
						//Auditoria
						tpagosExcedente.setIdusuario(usuarioBean.getUsuario().getIdusuario());
						
						//Creo el pago del credito en el detalle de pagos
						tpagosDAO.ingresarTpago(session, tpagosExcedente);
					}
				}
				
				//FORMAS DE PAGO
				if(totalFormasPagoUsar > 0){
					//Obtengo Secuencia
					int IdpagopagosFormasPago = pagosDAO.maxIdPagos(session)+1;
					
					//si existieron formas de pago a usar creo el pago
					Pagos pagosFormasPago = new Pagos();
					pagosFormasPago.setIdpago(IdpagopagosFormasPago);
					pagosFormasPago.setIdfactura(factura.getIdfactura());
					pagosFormasPago.setIdgeneracion(factura.getIdgeneracion());
					pagosFormasPago.setValtotal(totalFormasPagoUsar);
					pagosFormasPago.setEmpresa(usuarioBean.getUsuario().getEmpresa());
					Estado estado = new Estado();
					estado.setIdestado(Parametro.PAGOS_ESTADO_ACTIVO);
					pagosFormasPago.setEstado(estado);
					pagosFormasPago.setIdcuenta(idcuenta);
					
					//Auditoria del pago
					fecharegistro = new Date();
					pagosFormasPago.setFecha(fecharegistro);
					pagosFormasPago.setIp(usuarioBean.getIp());
					pagosFormasPago.setUsuario(usuarioBean.getUsuario());
					
					//Creo la cabecera de pagos
					pagosDAO.ingresarPago(session, pagosFormasPago);
				
					for(Tpagos tpagos : lisFormasPagoCuenta){
						//RECORRO MIS FORMAS DE PAGO A DAR DE BAJA
						if(totalFormasPagoUsar == 0){
							//Si ya no tengo que usar mas formas de pago salimos del ciclo y me sobrarian formas de pago para otra factura
							break;
						}
						float formaPagoUsado = 0;
						if(totalFormasPagoCuenta >= facturaValpendiente_FormaPago){
							//SI MIS FORMAS DE PAGO CUBRIERON EL TOTAL DE LA FACTURA O MAS
							if(tpagos.getValpago() <= totalFormasPagoUsar){
								//si mi forma de pago es menor o igual al total que debo usar
							
								//obtengo mi forma de pago usado
								formaPagoUsado = tpagos.getValpago();
								//el valor de la forma de pago pasa a cero
								tpagos.setValpago(0);
								//El estado de la forma de pago pasa a consumido
								tpagos.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
								//descuento el valor de la forma de pago usado del total de formas de pago a usar
								totalFormasPagoUsar -= formaPagoUsado;
							}else{
								//si mi forma de pago es mayor que el total de formas de pago a usar
								
								//obtengo mi forma de pago usado
								formaPagoUsado = totalFormasPagoUsar;
								//al valor de la forma de pago se le resta el total de formas de pago a usar y me queda un saldo para la siguiente factura
								tpagos.setValpago(tpagos.getValpago() - totalFormasPagoUsar);
								//el total de formas de pago a usar queda en cero
								totalFormasPagoUsar = 0;
							}
						}else{
							//SI MIS FORMAS DE PAGO CUBRIERON UNA PARTE DE LA FACTURA
							//obtengo mi forma de pago usado
							formaPagoUsado = tpagos.getValpago();
							//el valor de la forma de pago pasa a cero
							tpagos.setValpago(0);
							//El estado de la forma de pago pasa a consumido
							tpagos.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
						}

						//Obtengo Secuencia
						int IdtpagotpagosFormasPago = tpagosDAO.maxIdTpagos(session)+1;
						
						//Ingresar la forma de pago en el detalle del pago
						Tpagos tpagosFormasPago = new Tpagos();
						tpagosFormasPago.setIdtpago(IdtpagotpagosFormasPago);
						tpagosFormasPago.setPagos(pagosFormasPago);
						tpagosFormasPago.setValpago(formaPagoUsado);
						tpagosFormasPago.setFpago(tpagos.getFpago());
						tpagosFormasPago.setNrodocumento(tpagos.getNrodocumento());
						tpagosFormasPago.setBancosByIdbcoemisor(tpagos.getBancosByIdbcoemisor());
						tpagosFormasPago.setBancosByIdbcoemisortar(tpagos.getBancosByIdbcoemisortar());
						tpagosFormasPago.setFcaducidad(tpagos.getFcaducidad());
						tpagosFormasPago.setCodseguridad(tpagos.getCodseguridad());
						tpagosFormasPago.setPropietario(tpagos.getPropietario());
						tpagosFormasPago.setNrocuenta(tpagos.getNrocuenta());
						tpagosFormasPago.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
						tpagosFormasPago.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
						tpagosFormasPago.setIdnexo(0);
						
						//Auditoria
						tpagosFormasPago.setIdusuario(usuarioBean.getUsuario().getIdusuario());
						
						//Creo el pago de la forma de pago en el detalle de pagos
						tpagosDAO.ingresarTpago(session, tpagosFormasPago);
					}
					
				}
			}
			
			//Consulto las formas de pago sobrantes disponibles de la cuenta
			List<Tpagos> lisTpagosTmp2 = new ArrayList<Tpagos>();
			for(Tpagos tpagos : lisFormasPagoCuenta){
				if(tpagos.getIdestado() == Parametro.TPAGOS_ESTADO_PENDIENTE){//1
					lisTpagosTmp2.add(tpagos);
				}
			}
			lisFormasPagoCuenta = new ArrayList<Tpagos>(lisTpagosTmp2);
			
			for(Tpagos tpagos : lisFormasPagoCuenta){
				//RECORRO MIS FORMAS DE PAGO SOBRANTES, DESPUES DE PAGAR LAS FACTURAS EL SALDO PASA A EXCEDENTE
				
				//Obtengo Secuencia
				int IdexcedenteExcedentes = excedentesDAO.maxIdexcedente(session)+1;
				
				//Ingresar el excedente
				Excedentes excedentes = new Excedentes();
				excedentes.setIdexcedente(IdexcedenteExcedentes);
				excedentes.setValpago(tpagos.getValpago());
				excedentes.setIdfpago(tpagos.getFpago().getIdfpago());
				if(tpagos.getNrodocumento() !=null && tpagos.getNrodocumento().trim().length() > 0){
					excedentes.setNrodocumento(tpagos.getNrodocumento());
				}
				if(tpagos.getBancosByIdbcoemisor() != null && tpagos.getBancosByIdbcoemisor().getIdbanco() > 0){
					excedentes.setIdbcoemisor(tpagos.getBancosByIdbcoemisor().getIdbanco());
				}
				if(tpagos.getBancosByIdbcoemisortar() != null && tpagos.getBancosByIdbcoemisortar().getIdbanco() > 0){
					excedentes.setIdbcoemisortar(tpagos.getBancosByIdbcoemisortar().getIdbanco());
				}
				if(tpagos.getFcaducidad() != null){
					excedentes.setFcaducidad(tpagos.getFcaducidad());
				}
				if(tpagos.getCodseguridad() != null && tpagos.getCodseguridad().trim().length() > 0){
					excedentes.setCodseguridad(tpagos.getCodseguridad());
				}
				if(tpagos.getPropietario() != null && tpagos.getPropietario().trim().length() > 0){
					excedentes.setPropietario(tpagos.getPropietario());
				}
				if(tpagos.getNrocuenta() != null && tpagos.getNrocuenta().trim().length() > 0){
					excedentes.setNrocuenta(tpagos.getNrocuenta());
				}
				excedentes.setIdestado(Parametro.EXCEDENTE_ESTADO_PENDIENTE);
				excedentes.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
				excedentes.setIdcuenta(idcuenta);
				excedentes.setValpendiente(tpagos.getValpago());
				
				//Auditoria
				fecharegistro = new Date();
				excedentes.setFecha(fecharegistro);
				excedentes.setIdusuario(usuarioBean.getUsuario().getIdusuario());
				excedentes.setIp(usuarioBean.getIp());
				
				//Creo el excedente
				excedentesDAO.ingresarExcedentes(session, excedentes);
			}
			
			session.getTransaction().commit();
			ok = true;
		}catch(Exception he){
			session.getTransaction().rollback();
			throw new Exception(he);
		}finally{
			session.close();
		}
		
		return ok;
	}
	
	public boolean procesarGeneracionCreditosExcedentes(List<DetalleFacturaPojo> lisDetalleFacturaPojo, List<Tpagos> lisTpagos, int idcuenta, String numeroFacturaParam[]) throws Exception {
		boolean ok = false;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			CreditosDAO creditosDAO = new CreditosDAO();
			PagosDAO pagosDAO = new PagosDAO();
			TpagosDAO tpagosDAO = new TpagosDAO();
			ExcedentesDAO excedentesDAO = new ExcedentesDAO();
			CargosDAO cargosDAO = new CargosDAO();
			CabimpfacturasDAO cabimpfacturasDAO = new CabimpfacturasDAO();
			
			CreditosBO creditosBO = new CreditosBO();
			ExcedentesBO excedentesBO = new ExcedentesBO();
			CargosBO cargosBO = new CargosBO();
			NrosfacturaBO nrosfacturaBO = new NrosfacturaBO();
			
			List<Tpagos> lisFormasPagoCuenta = new ArrayList<Tpagos>(lisTpagos);
			Date fecharegistro = new Date();
			String numeroFactura = "";
			String idAutorizacion = "";
			String direccionCinecable = "";
			String direccionCliente = "";
			String idCliente = "";
			String nombreCliente = "";
			String resolucion = "";
			
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			for(DetalleFacturaPojo detalleFacturaPojo : lisDetalleFacturaPojo){//TODO aqui me quedé, ver que aqui se pagan varias generaciones y por cada una factura
				//RECORRO MIS FACTURAS PARA APLICARLE LOS CREDITOS, EXCEDENTES
				Factura factura = detalleFacturaPojo.getFactura().clonar();
				direccionCliente = factura.getDircliente();
				float facturaValpendiente = new Float(factura.getValpendiente());
				float facturaValpendiente_Credito = new Float(factura.getValpendiente());
				float facturaValpendiente_Excedente = new Float(factura.getValpendiente());
				float facturaValpendiente_FormaPago = new Float(factura.getValpendiente());
				float totalCreditosUsar = 0;
				float totalExcedentesUsar = 0;
				float totalFormasPagoUsar = 0;
				float facturaServiciosAbono = 0;
				float facturaDescuentosAbono = 0;
				float facturaImpuestosAbono = 0;
				float facturaSubtotalAbono = 0;
				float facturaTotalAbono = 0;
				
				//Consulto los creditos disponibles de la cuenta
				List<Creditos> lisCreditosCuenta = creditosBO.lisCreditosActivosByCuenta(idcuenta); 
				//Sumo los creditos de la cuenta
				float totalCreditosCuenta = 0;
				for(Creditos creditos : lisCreditosCuenta){
					totalCreditosCuenta += creditos.getVacredito();
				}
				
				//Consulto los excedentes disponibles de la cuenta
				List<Excedentes> lisExcedentesCuenta = excedentesBO.lisExcedentesActivosByCuenta(idcuenta);
				//Sumo los excedentes de la cuenta
				float totalExcedentesCuenta = 0;
				for(Excedentes excedentes : lisExcedentesCuenta){
					totalExcedentesCuenta += excedentes.getValpendiente();
				}
				
				//Consulto las formas de pago disponibles de la cuenta
				List<Tpagos> lisTpagosTmp2 = new ArrayList<Tpagos>();
				for(Tpagos tpagos : lisFormasPagoCuenta){
					if(tpagos.getIdestado() == Parametro.TPAGOS_ESTADO_PENDIENTE){//1
						lisTpagosTmp2.add(tpagos.clonar());
					}
				}
				lisFormasPagoCuenta = new ArrayList<Tpagos>(lisTpagosTmp2);
				//Sumo las formas de pago de la cuenta
				float totalFormasPagoCuenta = 0;
				for(Tpagos tpagos : lisFormasPagoCuenta){
					totalFormasPagoCuenta += tpagos.getValpago();
				}
				
				if(totalCreditosCuenta >= facturaValpendiente_Credito){
					//SI MIS CREDITOS CUBREN EL TOTAL DE LA FACTURA O MAS
					//al valor del credito en la factura se le sumará el total de la factura
					factura.setValcreditos(factura.getValcreditos() + facturaValpendiente_Credito);
					//El estado de la factura pasa a pagada
					factura.setIdestado(Parametro.FACTURA_ESTADO_PAGADA);
					//Ya no hay valor pendiente
					factura.setValpendiente((float)0);
					//el total de creditos que voy a usar sera el total de la factura
					totalCreditosUsar = facturaValpendiente_Credito;

					//RECORRO LOS CARGOS DE DETALLE, DESCUENTOS, IMPUESTOS DE LA GENERACION PARA ANULARLOS
					anularCargosByIdGeneracion(factura.getIdsecuencia(), session);
					
					//Bloqueo el numero de factura a usar
					Nrosfactura nrosfacturaParam[] = {new Nrosfactura()};
					numeroFactura = nrosfacturaBO.getNumeroFacturaForUpdate(session, nrosfacturaParam);
					if(numeroFactura == null || numeroFactura.trim().length() == 0){
						throw new SecuenciaFacturaException("No se ha podido obtener el número de factura. Favor verifique.");
					}
					Nrosfactura nrosfactura = nrosfacturaParam[0];
					
					resolucion = nrosfactura.getResolucion();
					idAutorizacion = String.valueOf(nrosfactura.getAutorizacion());
					direccionCinecable = nrosfactura.getDireccion();
					
					factura.setIdfactura(numeroFactura);
					
				}else{
					//SI MIS CREDITOS CUBREN UNA PARTE DE LA FACTURA
					//al valor del credito en la factura se le sumará el total de mis creditos 
					factura.setValcreditos(factura.getValcreditos() + totalCreditosCuenta);
					//al valor pendiente en la factura se le resta el valor del total de mis creditos
					factura.setValpendiente(facturaValpendiente_Credito - totalCreditosCuenta);
					//pasamos el valor pendiente de la factura para ser tomada por el excedente
					facturaValpendiente_Excedente = factura.getValpendiente();
					//el total de creditos que voy a usar sera el total de creditos de la cuenta
					totalCreditosUsar = totalCreditosCuenta;
					
					//APLICAMOS LOS EXCEDENTES SI MIS CREDITOS NO CUBRIERON EL TOTAL DE LA FACTURA
					if(totalExcedentesCuenta >= facturaValpendiente_Excedente){
						//SI MIS EXCEDENTES CUBREN EL TOTAL DE LA FACTURA O MAS
						//al valor del excedente en la factura se le sumará el total de la factura
						factura.setValorexcedentes(factura.getValorexcedentes() + facturaValpendiente_Excedente);
						//El estado de la factura pasa a pagada
						factura.setIdestado(Parametro.FACTURA_ESTADO_PAGADA);
						//Ya no hay valor pendiente
						factura.setValpendiente((float)0);
						//el total de excedentes que voy a usar sera el total de la factura
						totalExcedentesUsar = facturaValpendiente_Excedente;
						
						//RECORRO LOS CARGOS DE DETALLE, DESCUENTOS, IMPUESTOS DE LA GENERACION PARA ANULARLOS
						anularCargosByIdGeneracion(factura.getIdsecuencia(), session);
						
						//Bloqueo el numero de factura a usar
						Nrosfactura nrosfacturaParam[] = {new Nrosfactura()};
						numeroFactura = nrosfacturaBO.getNumeroFacturaForUpdate(session, nrosfacturaParam);
						if(numeroFactura == null || numeroFactura.trim().length() == 0){
							throw new Exception("No se ha podido obtener el número de factura. Favor verifique.");
						}
						Nrosfactura nrosfactura = nrosfacturaParam[0];
						
						resolucion = nrosfactura.getResolucion();
						idAutorizacion = String.valueOf(nrosfactura.getAutorizacion());
						direccionCinecable = nrosfactura.getDireccion();
						
						factura.setIdfactura(numeroFactura);
						
					}else{
						//SI MIS EXCEDENTES CUBREN UNA PARTE DE LA FACTURA
						//al valor del excedente en la factura se le sumará el total de mis excedentes 
						factura.setValorexcedentes(factura.getValorexcedentes() + totalExcedentesCuenta);
						//al valor pendiente en la factura se le resta el valor del total de mis creditos
						factura.setValpendiente(facturaValpendiente_Excedente - totalExcedentesCuenta);
						//pasamos el valor pendiente de la factura para ser tomada por las formas de pago
						facturaValpendiente_FormaPago = factura.getValpendiente();
						//el total de creditos que voy a usar sera el total de creditos de la cuenta
						totalExcedentesUsar = totalExcedentesCuenta;
						
						//APLICAMOS LAS FORMAS DE PAGO SI MIS EXCEDENTES NO CUBRIERON EL TOTAL DE LA FACTURA
						if(totalFormasPagoCuenta >= facturaValpendiente_FormaPago){
							//SI MIS FORMAS DE PAGO CUBREN EL TOTAL DE LA FACTURA O MAS
							//El estado de la factura pasa a pagada
							factura.setIdestado(Parametro.FACTURA_ESTADO_PAGADA);
							//Ya no hay valor pendiente
							factura.setValpendiente((float)0);
							//el total de formas de pago que voy a usar sera el total de la factura
							totalFormasPagoUsar = facturaValpendiente_FormaPago;
							
							//RECORRO LOS CARGOS DE DETALLE, DESCUENTOS, IMPUESTOS DE LA GENERACION PARA ANULARLOS
							anularCargosByIdGeneracion(factura.getIdsecuencia(), session);
							
							//Bloqueo el numero de factura a usar
							Nrosfactura nrosfacturaParam[] = {new Nrosfactura()};
							numeroFactura = nrosfacturaBO.getNumeroFacturaForUpdate(session, nrosfacturaParam);
							if(numeroFactura == null || numeroFactura.trim().length() == 0){
								throw new Exception("No se ha podido obtener el número de factura. Favor verifique.");
							}
							Nrosfactura nrosfactura = nrosfacturaParam[0];
							
							resolucion = nrosfactura.getResolucion();
							idAutorizacion = String.valueOf(nrosfactura.getAutorizacion());
							direccionCinecable = nrosfactura.getDireccion();
							
							factura.setIdfactura(numeroFactura);
							
						}else{
							//SI MIS FORMAS DE PAGO CUBREN UNA PARTE DE LA FACTURA
							//al valor pendiente en la factura se le resta el valor del total de mis formas de pago
							factura.setValpendiente(facturaValpendiente_FormaPago - totalFormasPagoCuenta);
							//el total de creditos que voy a usar sera el total de creditos de la cuenta
							totalFormasPagoUsar = totalFormasPagoCuenta;
							
							
							//obtengo valor proporcional de los valores a favor del cliente
							float proporcional = (totalFormasPagoCuenta + totalCreditosCuenta + totalExcedentesCuenta)/facturaValpendiente;
							float subtotalSaldo = 0;
						
							List<Cargos> lisCargos = cargosBO.lisCargosGeneracionById(factura.getIdsecuencia());
							for(Cargos cargos : lisCargos){
								//RECORRO LOS CARGOS DE DETALLE, DESCUENTOS, IMPUESTOS DE LA GENERACION PARA REBAJAR, APLICO FORMULA YANCE
								//el valor de los cargos de la generacion quedan rebajados
								float abonoCargoProporcion = cargos.getValpendiente() * proporcional;
								
								//obtengo el total de lo abonado
								facturaTotalAbono += abonoCargoProporcion;
								
								//aprovecho ciclo para obtener el total del servicio abonado
								if(cargos.getNivel() >= Parametro.CARGO_NIVEL_SERVICIO_MIN && cargos.getNivel() <= Parametro.CARGO_NIVEL_SERVICIO_MAX){
									facturaServiciosAbono += abonoCargoProporcion;
								}
								
								//aprovecho ciclo para obtener el total del descuento abonado
								if(cargos.getNivel() >= Parametro.CARGO_NIVEL_DESCUENTO_MIN && cargos.getNivel() <= Parametro.CARGO_NIVEL_DESCUENTO_MAX){
									facturaDescuentosAbono += abonoCargoProporcion;
								}
								
								//aprovecho ciclo para obtener el total del impuesto abonado
								if(cargos.getNivel() >= Parametro.CARGO_NIVEL_IMPUESTO_MIN && cargos.getNivel() <= Parametro.CARGO_NIVEL_IMPUESTO_MAX){
									facturaImpuestosAbono += abonoCargoProporcion;
								}
								
								//aprovecho ciclo para obtener subtotal del abono, sumo los cargos de detalle y de descuentos, abonados, sin considerar los de impuestos
								if(cargos.getNivel() < Parametro.CARGO_NIVEL_IMPUESTO_MIN || cargos.getNivel() > Parametro.CARGO_NIVEL_IMPUESTO_MAX){
									facturaSubtotalAbono += abonoCargoProporcion;
								}
								
								cargos.setValpendiente( cargos.getValpendiente() - abonoCargoProporcion );
								
								//aprovecho ciclo para obtener subtotal del saldo, sumo los cargos de detalle y de descuentos, descontados, sin considerar los de impuestos 
								if(cargos.getNivel() < Parametro.CARGO_NIVEL_IMPUESTO_MIN || cargos.getNivel() > Parametro.CARGO_NIVEL_IMPUESTO_MAX){
									subtotalSaldo += cargos.getValpendiente();
								}
								
								//auditoria
								fecharegistro = new Date();
								cargos.setFecha(fecharegistro);
								cargos.setUsuario(usuarioBean.getUsuario());
								
								//actualizo los cargos
								cargosDAO.actualizarCargos(session, cargos);
							}
							
							factura.setValbase(subtotalSaldo);
						}
					}
				}
				
				//Auditoria
				fecharegistro = new Date();
				factura.setFecha(fecharegistro);
				factura.setIp(usuarioBean.getIp());
				factura.setIdusuario(usuarioBean.getUsuario().getIdusuario());
				
				//Actualizo la factura
				facturaDAO.actualizarFactura(session, factura);
				
				if(facturaTotalAbono > 0){
					//SI SE HICIERON ABONOS CREO FACTURA
					//Obtengo Secuencia 
					int idsecuencia = facturaDAO.maxIdSecuencia(session)+1;
					
					//Bloqueo el numero de factura a usar
					Nrosfactura nrosfacturaParam[] = {new Nrosfactura()};
					numeroFactura = nrosfacturaBO.getNumeroFacturaForUpdate(session, nrosfacturaParam);
					if(numeroFactura == null || numeroFactura.trim().length() == 0){
						throw new Exception("No se ha podido obtener el número de factura. Favor verifique.");
					}
					Nrosfactura nrosfactura = nrosfacturaParam[0];
					
					resolucion = nrosfactura.getResolucion();
					idAutorizacion = String.valueOf(nrosfactura.getAutorizacion());
					direccionCinecable = nrosfactura.getDireccion();
					
					//CREO NUEVA FACTURA CON EL MISMO ID GENERACION POR EL ABONO
					Factura facturaAbono = factura.clonar();
					facturaAbono.setIdsecuencia(idsecuencia);
					facturaAbono.setValbruto(facturaServiciosAbono);
					facturaAbono.setValdescuentos(facturaDescuentosAbono);
					facturaAbono.setValcreditos(0f);
					facturaAbono.setValbase(facturaSubtotalAbono);
					facturaAbono.setValimpuestos(facturaImpuestosAbono);
					facturaAbono.setValtotal(facturaTotalAbono);
					facturaAbono.setValpendiente(facturaTotalAbono);
					facturaAbono.setValorexcedentes(0f);
					facturaAbono.setIdfactura(numeroFactura);
					facturaAbono.setIdestado(Parametro.FACTURA_ESTADO_PAGADA);
					
					//Auditoria
					fecharegistro = new Date();
					facturaAbono.setFecha(fecharegistro);
					facturaAbono.setIp(usuarioBean.getIp());
					facturaAbono.setIdusuario(usuarioBean.getUsuario().getIdusuario());
					
					//Creo la factura
					facturaDAO.ingresarFactura(session, facturaAbono);
				}
				
				//CREDITOS
				if(totalCreditosUsar > 0){
					
					//Obtengo Secuencia
					int IdpagopagosCredito = pagosDAO.maxIdPagos(session)+1;
					
					//si existieron creditos a usar creo el pago
					Pagos pagosCredito = new Pagos();
					pagosCredito.setIdpago(IdpagopagosCredito);
					pagosCredito.setIdfactura(factura.getIdfactura());
					pagosCredito.setIdgeneracion(factura.getIdgeneracion());
					pagosCredito.setValtotal(totalCreditosUsar);
					pagosCredito.setEmpresa(usuarioBean.getUsuario().getEmpresa());
					Estado estado = new Estado();
					estado.setIdestado(Parametro.PAGOS_ESTADO_ACTIVO);
					pagosCredito.setEstado(estado);
					pagosCredito.setIdcuenta(idcuenta);
					
					//Auditoria del pago
					fecharegistro = new Date();
					pagosCredito.setFecha(fecharegistro);
					pagosCredito.setIp(usuarioBean.getIp());
					pagosCredito.setUsuario(usuarioBean.getUsuario());
					
					//Creo la cabecera de pagos
					pagosDAO.ingresarPago(session, pagosCredito);
				
					for(Creditos creditos : lisCreditosCuenta){
						//RECORRO MIS CREDITOS A DAR DE BAJA
						if(totalCreditosUsar == 0){
							//Si ya no tengo que usar mas creditos salimos del ciclo y me sobrarian creditos para otra factura
							break;
						}
						float creditoUsado = 0;
						if(totalCreditosCuenta >= facturaValpendiente_Credito){
							//SI MIS CREDITOS CUBRIERON EL TOTAL DE LA FACTURA O MAS
							if(creditos.getVacredito() <= totalCreditosUsar){
								//si mi credito es menor o igual al total que debo usar
							
								//obtengo mi credito usado
								creditoUsado = creditos.getVacredito();
								//el valor del credito pasa a cero
								creditos.setVacredito(0);
								//El estado del credito pasa a consumido
								Estado estadoCredito = new Estado();
								estadoCredito.setIdestado(Parametro.CREDITO_ESTADO_CONSUMIDO);
								creditos.setEstado(estadoCredito);
								//descuento el valor del credito usado del total de creditos a usar
								totalCreditosUsar -= creditoUsado;
							}else{
								//si mi credito es mayor que el total de creditos a usar
								
								//obtengo mi credito usado
								creditoUsado = totalCreditosUsar;
								//al valor del credito se le resta el total de creditos a usar y me queda un saldo en el credito
								creditos.setVacredito(creditos.getVacredito() - totalCreditosUsar);
								//el total de creditos a usar queda en cero
								totalCreditosUsar = 0;
							}
						}else{
							//SI MIS CREDITOS CUBRIERON UNA PARTE DE LA FACTURA
							//obtengo mi credito usado
							creditoUsado = creditos.getVacredito();
							//el valor del credito pasa a cero
							creditos.setVacredito(0);
							//El estado del credito pasa a consumido
							Estado estadoCredito = new Estado();
							estadoCredito.setIdestado(Parametro.CREDITO_ESTADO_CONSUMIDO);
							creditos.setEstado(estadoCredito);
						}
						
						creditos.setIdpago(IdpagopagosCredito);
	
						//Auditoria
						fecharegistro = new Date();
						creditos.setFecha(fecharegistro);
						creditos.setIp(usuarioBean.getIp());
						creditos.setUsuario(usuarioBean.getUsuario());
						
						//Actualizo el credito
						creditosDAO.actualizarCreditos(session, creditos);
						
						//Obtengo Secuencia
						int IdtpagotpagosCredito = tpagosDAO.maxIdTpagos(session)+1;
						
						//Ingresar el credito en el detalle del pago
						Tpagos tpagosCredito = new Tpagos();
						tpagosCredito.setPagos(pagosCredito);
						tpagosCredito.setIdtpago(IdtpagotpagosCredito);
						tpagosCredito.setValpago(creditoUsado);
						Fpago fpago = new Fpago();
						fpago.setIdfpago(Parametro.TIPO_FORMA_PAGO_CREDITO);
						tpagosCredito.setFpago(fpago);
						tpagosCredito.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
						tpagosCredito.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
						
						//Auditoria
						tpagosCredito.setIdusuario(usuarioBean.getUsuario().getIdusuario());
						
						//Creo el pago del credito en el detalle de pagos
						tpagosDAO.ingresarTpago(session, tpagosCredito);
					}
				}
				
				//EXCEDENTES
				if(totalExcedentesUsar > 0){
					//Obtengo Secuencia
					int IdpagopagosExcedente = pagosDAO.maxIdPagos(session)+1;
					
					//si existieron excedentes a usar creo el pago
					Pagos pagosExcedente = new Pagos();
					pagosExcedente.setIdpago(IdpagopagosExcedente);
					pagosExcedente.setIdfactura(factura.getIdfactura());
					pagosExcedente.setIdgeneracion(factura.getIdgeneracion());
					pagosExcedente.setValtotal(totalExcedentesUsar);
					pagosExcedente.setEmpresa(usuarioBean.getUsuario().getEmpresa());
					Estado estado = new Estado();
					estado.setIdestado(Parametro.PAGOS_ESTADO_ACTIVO);
					pagosExcedente.setEstado(estado);
					pagosExcedente.setIdcuenta(idcuenta);
					
					//Auditoria del pago
					fecharegistro = new Date();
					pagosExcedente.setFecha(fecharegistro);
					pagosExcedente.setIp(usuarioBean.getIp());
					pagosExcedente.setUsuario(usuarioBean.getUsuario());
					
					//Creo la cabecera de pagos
					pagosDAO.ingresarPago(session, pagosExcedente);
				
					for(Excedentes excedentes : lisExcedentesCuenta){
						//RECORRO MIS EXCEDENTES A DAR DE BAJA
						if(totalExcedentesUsar == 0){
							//Si ya no tengo que usar mas excedentes salimos del ciclo y me sobrarian excedentes para otra factura
							break;
						}
						float excedenteUsado = 0;
						if(totalExcedentesCuenta >= facturaValpendiente_Excedente){
							//SI MIS EXCEDENTES CUBRIERON EL TOTAL DE LA FACTURA O MAS
							if(excedentes.getValpendiente() <= totalExcedentesUsar){
								//si mi excedente es menor o igual al total que debo usar
							
								//obtengo mi excedente usado
								excedenteUsado = excedentes.getValpendiente();
								//el valor del excedente pasa a cero
								excedentes.setValpendiente(0);
								//El estado del excedente pasa a consumido
								excedentes.setIdestado(Parametro.EXCEDENTE_ESTADO_CONSUMIDO);
								//descuento el valor del excedente usado del total de excedentes a usar
								totalExcedentesUsar -= excedenteUsado;
							}else{
								//si mi excedente es mayor que el total de excedentes a usar
								
								//obtengo mi excedente usado
								excedenteUsado = totalExcedentesUsar;
								//al valor del excedente se le resta el total de excedentes a usar y me queda un saldo en el excedente
								excedentes.setValpendiente(excedentes.getValpendiente() - totalExcedentesUsar);
								//el total de excedentes a usar queda en cero
								totalExcedentesUsar = 0;
							}
						}else{
							//SI MIS EXCEDENTES CUBRIERON UNA PARTE DE LA FACTURA
							//obtengo mi excedente usado
							excedenteUsado = excedentes.getValpendiente();
							//el valor del credito pasa a cero
							excedentes.setValpendiente(0);
							//El estado del excedente pasa a consumido
							excedentes.setIdestado(Parametro.EXCEDENTE_ESTADO_CONSUMIDO);
						}
	
						excedentes.setIdpago(IdpagopagosExcedente);
						
						//Auditoria
						fecharegistro = new Date();
						excedentes.setFecha(fecharegistro);
						excedentes.setIp(usuarioBean.getIp());
						excedentes.setIdusuario(usuarioBean.getUsuario().getIdusuario());
						
						//Actualizo el excedente
						excedentesDAO.actualizarExcedentes(session, excedentes);
						
						//Obtengo Secuencia
						int IdtpagotpagosExcedente = tpagosDAO.maxIdTpagos(session)+1;
						
						//Ingresar el excedente en el detalle del pago
						Tpagos tpagosExcedente = new Tpagos();
						tpagosExcedente.setPagos(pagosExcedente);
						tpagosExcedente.setIdtpago(IdtpagotpagosExcedente);
						tpagosExcedente.setValpago(excedenteUsado);
						Fpago fpago = new Fpago();
						fpago.setIdfpago(Parametro.TIPO_FORMA_PAGO_EXCEDENTE);
						tpagosExcedente.setFpago(fpago);
						tpagosExcedente.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
						tpagosExcedente.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
						
						//Auditoria
						tpagosExcedente.setIdusuario(usuarioBean.getUsuario().getIdusuario());
						
						//Creo el pago del credito en el detalle de pagos
						tpagosDAO.ingresarTpago(session, tpagosExcedente);
					}
				}
				
				//FORMAS DE PAGO
				if(totalFormasPagoUsar > 0){
					//Obtengo Secuencia
					int IdpagopagosFormasPago = pagosDAO.maxIdPagos(session)+1;
					
					//si existieron formas de pago a usar creo el pago
					Pagos pagosFormasPago = new Pagos();
					pagosFormasPago.setIdpago(IdpagopagosFormasPago);
					pagosFormasPago.setIdfactura(factura.getIdfactura());
					pagosFormasPago.setIdgeneracion(factura.getIdgeneracion());
					pagosFormasPago.setValtotal(totalFormasPagoUsar);
					pagosFormasPago.setEmpresa(usuarioBean.getUsuario().getEmpresa());
					Estado estado = new Estado();
					estado.setIdestado(Parametro.PAGOS_ESTADO_ACTIVO);
					pagosFormasPago.setEstado(estado);
					pagosFormasPago.setIdcuenta(idcuenta);
					
					//Auditoria del pago
					fecharegistro = new Date();
					pagosFormasPago.setFecha(fecharegistro);
					pagosFormasPago.setIp(usuarioBean.getIp());
					pagosFormasPago.setUsuario(usuarioBean.getUsuario());
					
					//Creo la cabecera de pagos
					pagosDAO.ingresarPago(session, pagosFormasPago);
				
					for(Tpagos tpagos : lisFormasPagoCuenta){
						//RECORRO MIS FORMAS DE PAGO A DAR DE BAJA
						if(totalFormasPagoUsar == 0){
							//Si ya no tengo que usar mas formas de pago salimos del ciclo y me sobrarian formas de pago para otra factura
							break;
						}
						float formaPagoUsado = 0;
						if(totalFormasPagoCuenta >= facturaValpendiente_FormaPago){
							//SI MIS FORMAS DE PAGO CUBRIERON EL TOTAL DE LA FACTURA O MAS
							if(tpagos.getValpago() <= totalFormasPagoUsar){
								//si mi forma de pago es menor o igual al total que debo usar
							
								//obtengo mi forma de pago usado
								formaPagoUsado = tpagos.getValpago();
								//el valor de la forma de pago pasa a cero
								tpagos.setValpago(0);
								//El estado de la forma de pago pasa a consumido
								tpagos.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
								//descuento el valor de la forma de pago usado del total de formas de pago a usar
								totalFormasPagoUsar -= formaPagoUsado;
							}else{
								//si mi forma de pago es mayor que el total de formas de pago a usar
								
								//obtengo mi forma de pago usado
								formaPagoUsado = totalFormasPagoUsar;
								//al valor de la forma de pago se le resta el total de formas de pago a usar y me queda un saldo para la siguiente factura
								tpagos.setValpago(tpagos.getValpago() - totalFormasPagoUsar);
								//el total de formas de pago a usar queda en cero
								totalFormasPagoUsar = 0;
							}
						}else{
							//SI MIS FORMAS DE PAGO CUBRIERON UNA PARTE DE LA FACTURA
							//obtengo mi forma de pago usado
							formaPagoUsado = tpagos.getValpago();
							//el valor de la forma de pago pasa a cero
							tpagos.setValpago(0);
							//El estado de la forma de pago pasa a consumido
							tpagos.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
							
						}

						//Obtengo Secuencia
						int IdtpagotpagosFormasPago = tpagosDAO.maxIdTpagos(session)+1;
						
						//Ingresar la forma de pago en el detalle del pago
						Tpagos tpagosFormasPago = new Tpagos();
						tpagosFormasPago.setIdtpago(IdtpagotpagosFormasPago);
						tpagosFormasPago.setPagos(pagosFormasPago);
						tpagosFormasPago.setValpago(formaPagoUsado);
						tpagosFormasPago.setFpago(tpagos.getFpago());
						tpagosFormasPago.setNrodocumento(tpagos.getNrodocumento());
						tpagosFormasPago.setBancosByIdbcoemisor(tpagos.getBancosByIdbcoemisor());
						tpagosFormasPago.setBancosByIdbcoemisortar(tpagos.getBancosByIdbcoemisortar());
						tpagosFormasPago.setFcaducidad(tpagos.getFcaducidad());
						tpagosFormasPago.setCodseguridad(tpagos.getCodseguridad());
						tpagosFormasPago.setPropietario(tpagos.getPropietario());
						tpagosFormasPago.setNrocuenta(tpagos.getNrocuenta());
						tpagosFormasPago.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
						tpagosFormasPago.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
						tpagosFormasPago.setIdnexo(0);
						
						//Auditoria
						tpagosFormasPago.setIdusuario(usuarioBean.getUsuario().getIdusuario());
						
						//Creo el pago de la forma de pago en el detalle de pagos
						tpagosDAO.ingresarTpago(session, tpagosFormasPago);
					}
					
				}
			}
			
			//Consulto las formas de pago sobrantes disponibles de la cuenta
			List<Tpagos> lisTpagosTmp2 = new ArrayList<Tpagos>();
			for(Tpagos tpagos : lisFormasPagoCuenta){
				if(tpagos.getIdestado() == Parametro.TPAGOS_ESTADO_PENDIENTE){//1
					lisTpagosTmp2.add(tpagos);
				}
			}
			lisFormasPagoCuenta = new ArrayList<Tpagos>(lisTpagosTmp2);
			
			for(Tpagos tpagos : lisFormasPagoCuenta){
				//RECORRO MIS FORMAS DE PAGO SOBRANTES, DESPUES DE PAGAR LAS FACTURAS EL SALDO PASA A EXCEDENTE
				
				//Obtengo Secuencia
				int IdexcedenteExcedentes = excedentesDAO.maxIdexcedente(session)+1;
				
				//Ingresar el excedente
				Excedentes excedentes = new Excedentes();
				excedentes.setIdexcedente(IdexcedenteExcedentes);
				excedentes.setValpago(tpagos.getValpago());
				excedentes.setIdfpago(tpagos.getFpago().getIdfpago());
				if(tpagos.getNrodocumento() !=null && tpagos.getNrodocumento().trim().length() > 0){
					excedentes.setNrodocumento(tpagos.getNrodocumento());
				}
				if(tpagos.getBancosByIdbcoemisor() != null && tpagos.getBancosByIdbcoemisor().getIdbanco() > 0){
					excedentes.setIdbcoemisor(tpagos.getBancosByIdbcoemisor().getIdbanco());
				}
				if(tpagos.getBancosByIdbcoemisortar() != null && tpagos.getBancosByIdbcoemisortar().getIdbanco() > 0){
					excedentes.setIdbcoemisortar(tpagos.getBancosByIdbcoemisortar().getIdbanco());
				}
				if(tpagos.getFcaducidad() != null){
					excedentes.setFcaducidad(tpagos.getFcaducidad());
				}
				if(tpagos.getCodseguridad() != null && tpagos.getCodseguridad().trim().length() > 0){
					excedentes.setCodseguridad(tpagos.getCodseguridad());
				}
				if(tpagos.getPropietario() != null && tpagos.getPropietario().trim().length() > 0){
					excedentes.setPropietario(tpagos.getPropietario());
				}
				if(tpagos.getNrocuenta() != null && tpagos.getNrocuenta().trim().length() > 0){
					excedentes.setNrocuenta(tpagos.getNrocuenta());
				}
				excedentes.setIdestado(Parametro.EXCEDENTE_ESTADO_PENDIENTE);
				excedentes.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
				excedentes.setIdcuenta(idcuenta);
				excedentes.setValpendiente(tpagos.getValpago());
				
				//Auditoria
				fecharegistro = new Date();
				excedentes.setFecha(fecharegistro);
				excedentes.setIdusuario(usuarioBean.getUsuario().getIdusuario());
				excedentes.setIp(usuarioBean.getIp());
				
				//Creo el excedente
				excedentesDAO.ingresarExcedentes(session, excedentes);
			}
			
			//TODO definir cuantas facturas se van a imprimir
			//GRABO LOS DATOS PARA LA IMPRESION DE LA FACTURA
			
			//CABECERA IMPRESION
			/*numeroFacturaParam[0] = numeroFactura;
			Cabimpfacturas cabimpfacturas = new Cabimpfacturas();
			int idcabfactura = cabimpfacturasDAO.maxIdcabfactura(session) + 1;
			cabimpfacturas.setIdcabfactura(idcabfactura);
			cabimpfacturas.setResolucion(resolucion);
			cabimpfacturas.setAutorizacion(idAutorizacion);
			cabimpfacturas.setIdfactura(numeroFactura);
			cabimpfacturas.setDircinecable(direccionCinecable);
			cabimpfacturas.setDircliente(direccionCliente);
			cabimpfacturas.setIdcliente(idCliente);
			cabimpfacturas.setNomscliente(nombreCliente);
			
			String telefono = telefonoBO.consultarTelefonoPorCuenta(idcuenta);
			if(telefono != null && telefono.trim().length() > 0){
				cabimpfacturas.setTelcliente(telefono);
			}
			
			cabimpfacturas.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
			cabimpfacturas.setIdestado(1);
			cabimpfacturas.setTotalfactura(totalAbono);
			cabimpfacturas.setSubtotal(facturaSubtotalAbono);
			cabimpfacturas.setValimpiva(facturaIvaAbono);
			cabimpfacturas.setValimpice(facturaIceAbono);
			cabimpfacturas.setValdescre(facturaDescuentosAbono);
			cabimpfacturas.setIdcuenta(idcuenta);
			
			//grabo cabimpfacturas
			cabimpfacturasDAO.ingresarCabimpfacturas(session, cabimpfacturas);
			
			int orden = 1;
			for(DetalleContratoPojo detalleContratoPojo : lisDetalleContratoPojo){
				//DETALLE IMPRESION
				Detimpfacturas detimpfacturas = new Detimpfacturas();
				detimpfacturas.setIdcabfacturas(cabimpfacturas.getIdcabfactura());
				detimpfacturas.setDetalle(detalleContratoPojo.getProducto().getNombre());
				float valor = detalleContratoPojo.getTotalServicioAbono() + detalleContratoPojo.getTotalDescuentoAbono();//facturaServiciosAbono + facturaDescuentosAbono; 
				detimpfacturas.setValor(valor);
				detimpfacturas.setOrden(orden);
				int iddetfacturas = detimpfacturasDAO.maxIddetfacturas(session) + 1;
				detimpfacturas.setIddetfacturas(iddetfacturas);
				detimpfacturas.setIdcuenta(cabimpfacturas.getIdcuenta());
				detimpfacturas.setIdempresa(cabimpfacturas.getIdempresa());
				detimpfacturas.setIdestado(1);
				
				//grabo detimpfacturas
				detimpfacturasDAO.ingresarDetimpfacturas(session, detimpfacturas);
				
				orden++;
			}*/
			
			session.getTransaction().commit();
			ok = true;
		}catch(SecuenciaFacturaException e){
			session.getTransaction().rollback();
			throw new SecuenciaFacturaException(e);
		}catch(Exception he){
			session.getTransaction().rollback();
			throw new Exception(he);
		}finally{
			session.close();
		}
		
		return ok;
	}
	
	public boolean procesarContratosFormasPago(List<DetalleContratoPojo> lisDetalleContratoPojo, List<Tpagos> lisTpagos, int idcuenta, String numeroFacturaParam[]) throws Exception {
		boolean ok = false;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			String numeroFactura = "";
			String idAutorizacion = "";
			String direccionCinecable = "";
			String direccionCliente = "";
			String idCliente = "";
			String nombreCliente = "";
			String resolucion = "";
			float valorBrutoPendiente = 0;
			float valorDescuentoPendiente = 0;
			
			PagosDAO pagosDAO = new PagosDAO();
			TpagosDAO tpagosDAO = new TpagosDAO();
			ExcedentesDAO excedentesDAO = new ExcedentesDAO();
			CargosDAO cargosDAO = new CargosDAO();
			CtaclienteDAO ctaclienteDAO = new CtaclienteDAO();
			CabimpfacturasDAO cabimpfacturasDAO = new CabimpfacturasDAO();
			DetimpfacturasDAO detimpfacturasDAO = new DetimpfacturasDAO();
			
			CtaclienteBO ctaclienteBO = new CtaclienteBO();
			TelefonoBO telefonoBO = new TelefonoBO();
			DireccionBO direccionBO = new DireccionBO();
			NrosfacturaBO nrosfacturaBO = new NrosfacturaBO();
			
			List<Tpagos> lisFormasPagoCuenta = new ArrayList<Tpagos>(lisTpagos);
			Date fecharegistro = new Date();
			
			//total abono
			float totalFormasPagoCuenta = 0;
			for(Tpagos tpagos : lisFormasPagoCuenta){
				totalFormasPagoCuenta += tpagos.getValpago();
			}
			
			Ctacliente ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
			
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			//SI PAGA TO-DO 
			//RECORRER LISTA DE SERVICIOS INGRESAR EN CARGOS, VALPENDIENTE = 0, ESTADO PAGADO = 4
			//RECORRER LISTA DE IMPUESTOS INGRESAR EN CARGOS, VALPENDIENTE = 0, ESTADO PAGADO = 4
			//RECORRER LISTA DE DESCUENTEOS INGRESAR EN CARGOS, VALPENDIENTE = 0, ESTADO PAGADO = 4
			//OK-OBTENER SECUENCIA FACTURA
			//OK-CREAR FACTURA, ID GENERACION SIG SECUENCIA, CON ESTADO PAGADO = 4 SALDO PENDIENTE = 0
			//OK-CREAR PAGOS TPAGOS
			
			float totalFactura = 0;
			float valbruto = 0;
			float valdescuentos = 0;
			float valimpuestos = 0;
			
			//obtener totales de la factura
			for(DetalleContratoPojo detalleContratoPojo : lisDetalleContratoPojo){
				totalFactura += detalleContratoPojo.getTotalPagar();
				valbruto += detalleContratoPojo.getTotalServicios();
				valdescuentos += detalleContratoPojo.getTotalDescuentos();
				valimpuestos += detalleContratoPojo.getTotalImpuestos();
			}
			
			Factura factura = new Factura();
			float totalFormasPagoUsar = 0;
			float totalAbono = 0;
			float proporcional = 0;
			
			float facturaServiciosAbono = 0;
			float facturaDescuentosAbono = 0;
			float facturaImpuestosAbono = 0;
			float facturaIvaAbono = 0;
			float facturaIceAbono = 0;
			float facturaSubtotalAbono = 0;
			float facturaTotalAbono = 0;
			
			BigDecimal totalFormasPagoCuentaBD = new BigDecimal(Float.toString(totalFormasPagoCuenta));
			BigDecimal totalFacturaBD = new BigDecimal(Float.toString(totalFactura));
			totalFormasPagoCuentaBD = totalFormasPagoCuentaBD.setScale(2, BigDecimal.ROUND_HALF_UP);
			totalFacturaBD = totalFacturaBD.setScale(2, BigDecimal.ROUND_HALF_UP);
			totalFormasPagoCuenta = totalFormasPagoCuentaBD.floatValue();
			totalFactura = totalFacturaBD.floatValue();
			
			if(totalFormasPagoCuenta >= totalFactura){
				//SI MIS FORMAS DE PAGO CUBREN EL TOTAL DE LA FACTURA O MAS
				//El estado de la factura pasa a pagada
				factura.setIdestado(Parametro.FACTURA_ESTADO_PAGADA);
				//Ya no hay valor pendiente
				factura.setValpendiente((float)0);
				//el total de formas de pago que voy a usar sera el total de la factura
				totalFormasPagoUsar = totalFactura;
				totalAbono = totalFactura;
				
				//Bloqueo el numero de factura a usar
				Nrosfactura nrosfactura[] = {new Nrosfactura()};
				numeroFactura = nrosfacturaBO.getNumeroFacturaForUpdate(session, nrosfactura);
				if(numeroFactura == null || numeroFactura.trim().length() == 0){
					throw new Exception("No se ha podido obtener el número de factura. Favor verifique.");
				}
				Nrosfactura nrosfacturaParam = nrosfactura[0];
				
				factura.setIdfactura(numeroFactura);
				factura.setFcaducidad(nrosfacturaParam.getFcaducidad());
				idAutorizacion = String.valueOf(nrosfacturaParam.getAutorizacion());
				factura.setIdautorizacion(idAutorizacion);
				factura.setImprenta(nrosfacturaParam.getImprenta());
				resolucion = nrosfacturaParam.getResolucion();
				direccionCinecable = nrosfacturaParam.getDireccion();
				factura.setDircinecable(direccionCinecable);
				factura.setIdcinecable(nrosfacturaParam.getRuc());
				
				facturaServiciosAbono = valbruto;
				facturaDescuentosAbono = valdescuentos;
				facturaImpuestosAbono = valimpuestos;
				facturaSubtotalAbono = facturaServiciosAbono + facturaDescuentosAbono;
				facturaTotalAbono = facturaSubtotalAbono + facturaImpuestosAbono;
			}else{
				//SI MIS FORMAS DE PAGO CUBREN UNA PARTE DE LA FACTURA
				//El estado de la factura queda pendiente
				factura.setIdestado(Parametro.FACTURA_ESTADO_PENDIENTE);
				//el valor pendiente en la factura será la diferencia entre el valor del total de la factura y el total de mis formas de pago
				factura.setValpendiente(totalFactura - totalFormasPagoCuenta);
				//el total de creditos que voy a usar sera el total de creditos de la cuenta
				totalFormasPagoUsar = totalFormasPagoCuenta;
				totalAbono = totalFormasPagoCuenta;
				
				fecharegistro = new Date();
				factura.setFcaducidad(fecharegistro);//mas adelante se hace update
				idAutorizacion = "pendiente";
				factura.setIdautorizacion(idAutorizacion);//mas adelante se hace update
				direccionCinecable = "pendiente";
				factura.setDircinecable(direccionCinecable);//mas adelante se hace update
				factura.setIdcinecable("pendiente");//mas adelante se hace update
				
				proporcional = totalFormasPagoCuenta / totalFactura;
			}
			
			//codigo secuencial
			int idsecuencia = facturaDAO.maxIdSecuencia(session) + 1;
			factura.setIdsecuencia(idsecuencia);
			
			//Número de generación
			int idgeneracion = facturaDAO.maxIdGeneracion(session) + 1;
			factura.setIdgeneracion(idgeneracion);
			nombreCliente = ctacliente.getClientes().getNombre1() + " " + ctacliente.getClientes().getNombre2() + " " + ctacliente.getClientes().getApellido1() + " " + ctacliente.getClientes().getApellido2();
			factura.setNombre(nombreCliente);
			idCliente = ctacliente.getClientes().getIdcliente();
			factura.setIdcliente(idCliente);
			
			direccionCliente = direccionBO.consultarDireccionPorCuenta(idcuenta);
			factura.setDircliente(direccionCliente);
			factura.setEmpresa(usuarioBean.getUsuario().getEmpresa());
			factura.setValbruto(valbruto);
			factura.setValdescuentos(valdescuentos);
			factura.setValcreditos(0f);
			factura.setValbase(factura.getValbruto() + factura.getValdescuentos());
			factura.setValimpuestos(valimpuestos);
			factura.setValtotal(factura.getValbase() + factura.getValimpuestos());
			factura.setIdcuenta(ctacliente.getIdcuenta());
			factura.setValorexcedentes(0f);
			
			//Auditoria
			fecharegistro = new Date();
			factura.setFecha(fecharegistro);
			factura.setIp(usuarioBean.getIp());
			factura.setIdusuario(usuarioBean.getUsuario().getIdusuario());
			
			//creo la factura
			facturaDAO.ingresarFactura(session, factura);
			
			//ingresar los servicios, descuentos, impuestos en la tabla cargos
			for(DetalleContratoPojo detalleContratoPojo : lisDetalleContratoPojo){
				//Cargos cargos = new Cargos();
				
				if(totalFormasPagoCuenta >= totalFactura){
					//si mi abono es mayor o igual que la factura
					//Subtotal
					facturaServiciosAbono += detalleContratoPojo.getTotalServicios();
					detalleContratoPojo.setTotalServicioAbono(detalleContratoPojo.getTotalServicios());
					//Descuentos
					facturaDescuentosAbono += detalleContratoPojo.getTotalDescuentos();
					detalleContratoPojo.setTotalDescuentoAbono(detalleContratoPojo.getTotalDescuentos());
					//Impuestos
					facturaImpuestosAbono += detalleContratoPojo.getTotalImpuestos();
				}else{
					//Subtotal
					facturaServiciosAbono += detalleContratoPojo.getTotalServicios() * proporcional;
					detalleContratoPojo.setTotalServicioAbono(detalleContratoPojo.getTotalServicios() * proporcional);
					//Descuentos
					facturaDescuentosAbono += detalleContratoPojo.getTotalDescuentos() * proporcional;
					detalleContratoPojo.setTotalDescuentoAbono(detalleContratoPojo.getTotalDescuentos() * proporcional);
					//Impuestos
					facturaImpuestosAbono += detalleContratoPojo.getTotalImpuestos() * proporcional;
				}
				
				//servicios, descuentos, impuestos en la tabla cargos
				short nivelServicio = Parametro.CARGO_NIVEL_SERVICIO_MIN;
				short nivelDescuento = Parametro.CARGO_NIVEL_DESCUENTO_MIN;
				for(ServicioValor servicioValor : detalleContratoPojo.getLisServicioValor()){
					//SE GRABA SERVICIOS
					Cargos cargosServicio = new Cargos();
					int idrubropadre = cargosDAO.maxIdCargos(session) + 1;
					int idcargoservicio = cargosDAO.maxIdCargos(session) + 1;
					cargosServicio.setIdcargo(idcargoservicio);
					cargosServicio.setIdfactura(factura.getIdfactura());
					cargosServicio.setFactura(factura);
					cargosServicio.setValcargo(servicioValor.getServicio().getCostoservicio().getCosto());
					cargosServicio.setNivel(nivelServicio);
					cargosServicio.setMotivo(detalleContratoPojo.getProducto().getNombre() + " - " + servicioValor.getServicio().getNombre());
					Estado estado = new Estado();
					if(totalFormasPagoCuenta >= totalFactura){
						//si mi abono es mayor o igual que la factura, mi servicio se graba como pagado
						cargosServicio.setValpendiente(0f);
						estado = new Estado();
						estado.setIdestado(Parametro.CARGOS_ESTADO_PAGADO);
						cargosServicio.setEstado(estado);
					}else{
						//si mi abono es menor que la factura, mi servicio se graba rebajado a la proporcion
						cargosServicio.setValpendiente(servicioValor.getServicio().getCostoservicio().getCosto() - (servicioValor.getServicio().getCostoservicio().getCosto() * proporcional));
						
						estado = new Estado();
						estado.setIdestado(Parametro.CARGOS_ESTADO_PENDIENTE);
						cargosServicio.setEstado(estado);
					}
					valorBrutoPendiente += cargosServicio.getValpendiente();
					cargosServicio.setValbase(servicioValor.getServicio().getCostoservicio().getCosto());
					cargosServicio.setDescuento(servicioValor.getValorDescuento() - (servicioValor.getValorDescuento() * proporcional));
					cargosServicio.setIdrubropadre(0);
					cargosServicio.setProducto(detalleContratoPojo.getProducto().getNombre());
					
					nivelServicio++;
					
					//Auditoria
					fecharegistro = new Date();
					cargosServicio.setFecha(fecharegistro);
					cargosServicio.setUsuario(usuarioBean.getUsuario());
					cargosServicio.setEmpresa(usuarioBean.getUsuario().getEmpresa());
					
					//grabo servicio en cargos
					cargosDAO.ingresarCargos(session, cargosServicio);
					
					//SE GRABA DESCUENTOS
					if(servicioValor.getValorDescuento() < 0){
						Cargos cargosDescuento = new Cargos();
						int idcargodescuento = cargosDAO.maxIdCargos(session) + 1;
						cargosDescuento.setIdcargo(idcargodescuento);
						cargosDescuento.setIdfactura(factura.getIdfactura());
						cargosDescuento.setFactura(factura);
						cargosDescuento.setValcargo(servicioValor.getValorDescuento());
						cargosDescuento.setNivel(nivelDescuento);
						cargosDescuento.setMotivo(detalleContratoPojo.getProducto().getNombre() + " - " + servicioValor.getNombreDescuento());
						if(totalFormasPagoCuenta >= totalFactura){
							//si mi abono es mayor o igual que la factura, mi descuento se graba como pagado
							cargosDescuento.setValpendiente(0f);
							estado = new Estado();
							estado.setIdestado(Parametro.CARGOS_ESTADO_PAGADO);
							cargosDescuento.setEstado(estado);
						}else{
							//si mi abono es menor que la factura, mi descuento se graba rebajado a la proporcion
							cargosDescuento.setValpendiente(servicioValor.getValorDescuento() - (servicioValor.getValorDescuento() * proporcional));
							estado = new Estado();
							estado.setIdestado(Parametro.CARGOS_ESTADO_PENDIENTE);
							cargosDescuento.setEstado(estado);
						}
						valorDescuentoPendiente += cargosDescuento.getValpendiente();
						cargosDescuento.setValbase(servicioValor.getServicio().getCostoservicio().getCosto());
						cargosDescuento.setDescuento(0f);
						cargosDescuento.setIdrubropadre(idrubropadre);
						
						nivelDescuento++;
						
						//Auditoria
						fecharegistro = new Date();
						cargosDescuento.setFecha(fecharegistro);
						cargosDescuento.setUsuario(usuarioBean.getUsuario());
						cargosDescuento.setEmpresa(usuarioBean.getUsuario().getEmpresa());
						
						//grabo descuento en cargos
						cargosDAO.ingresarCargos(session, cargosDescuento);
					}
					
					//SE GRABA IMPUESTOS
					if(servicioValor.getLisImpuestoValor() != null && servicioValor.getLisImpuestoValor().size() > 0){
						short nivelImpuesto = Parametro.CARGO_NIVEL_IMPUESTO_MIN;
						for(ImpuestoValor impuestoValor : servicioValor.getLisImpuestoValor()){
							Cargos cargosImpuesto = new Cargos();
							int idcargoimpuesto = cargosDAO.maxIdCargos(session) + 1;
							cargosImpuesto.setIdcargo(idcargoimpuesto);
							cargosImpuesto.setIdfactura(factura.getIdfactura());
							cargosImpuesto.setFactura(factura);
							cargosImpuesto.setValcargo(impuestoValor.getValor());
							cargosImpuesto.setNivel(nivelImpuesto);
							cargosImpuesto.setMotivo(detalleContratoPojo.getProducto().getNombre() + " - " + impuestoValor.getImpservicios().getDescripcion() + " " + servicioValor.getServicio().getNombre());
							if(totalFormasPagoCuenta >= totalFactura){
								//si mi abono es mayor o igual que la factura, mi impuesto se graba como pagado
								cargosImpuesto.setValpendiente(0f);
								estado = new Estado();
								estado.setIdestado(Parametro.CARGOS_ESTADO_PAGADO);
								cargosImpuesto.setEstado(estado);
								
								if(impuestoValor.getImpservicios().getDescripcion().compareToIgnoreCase("iva") == 0){
									facturaIvaAbono += impuestoValor.getValor();
								}else{
									if(impuestoValor.getImpservicios().getDescripcion().compareToIgnoreCase("ice") == 0){
										facturaIceAbono += impuestoValor.getValor();
									}
								}
							}else{
								//si mi abono es menor que la factura, mi impuesto se graba rebajado a la proporcion
								cargosImpuesto.setValpendiente(impuestoValor.getValor() - (impuestoValor.getValor() * proporcional));
								estado = new Estado();
								estado.setIdestado(Parametro.CARGOS_ESTADO_PENDIENTE);
								cargosImpuesto.setEstado(estado);
								
								if(impuestoValor.getImpservicios().getDescripcion().compareToIgnoreCase("iva") == 0){
									facturaIvaAbono += impuestoValor.getValor() * proporcional;
								}else{
									if(impuestoValor.getImpservicios().getDescripcion().compareToIgnoreCase("ice") == 0){
										facturaIceAbono += impuestoValor.getValor() * proporcional;
									}
								}
							}
							cargosImpuesto.setValbase(servicioValor.getServicio().getCostoservicio().getCosto());
							cargosImpuesto.setDescuento(0f);
							cargosImpuesto.setIdrubropadre(idrubropadre);
							
							nivelImpuesto++;
							
							//Auditoria
							fecharegistro = new Date();
							cargosImpuesto.setFecha(fecharegistro);
							cargosImpuesto.setUsuario(usuarioBean.getUsuario());
							cargosImpuesto.setEmpresa(usuarioBean.getUsuario().getEmpresa());
							
							//grabo impuesto en cargos
							cargosDAO.ingresarCargos(session, cargosImpuesto);
						}
					}
				}
			}
			
			if(proporcional > 0){
				//SI SE HICIERON ABONOS CREO FACTURA
				facturaSubtotalAbono = facturaServiciosAbono + facturaDescuentosAbono;
				facturaTotalAbono = facturaSubtotalAbono + facturaImpuestosAbono;
				
				//Obtengo Secuencia 
				idsecuencia = facturaDAO.maxIdSecuencia(session)+1;
				
				//Bloqueo el numero de factura a usar
				Nrosfactura nrosfacturaParam[] = {new Nrosfactura()};
				numeroFactura = nrosfacturaBO.getNumeroFacturaForUpdate(session, nrosfacturaParam);
				if(numeroFactura == null || numeroFactura.trim().length() == 0){
					throw new Exception("No se ha podido obtener el número de factura. Favor verifique.");
				}
				Nrosfactura nrosfactura = nrosfacturaParam[0];
				
				//CREO NUEVA FACTURA CON EL MISMO ID GENERACION POR EL ABONO
				Factura facturaAbono = factura.clonar();
				facturaAbono.setIdsecuencia(idsecuencia);
				facturaAbono.setIdcinecable(nrosfactura.getRuc());
				facturaAbono.setDircinecable(nrosfactura.getDireccion());
				facturaAbono.setFcaducidad(nrosfactura.getFcaducidad());
				facturaAbono.setIdautorizacion(String.valueOf(nrosfactura.getAutorizacion()));
				facturaAbono.setValbruto(facturaServiciosAbono);
				facturaAbono.setValdescuentos(facturaDescuentosAbono);
				facturaAbono.setValcreditos(0f);
				facturaAbono.setValbase(facturaSubtotalAbono);
				facturaAbono.setValimpuestos(facturaImpuestosAbono);
				facturaAbono.setValtotal(facturaTotalAbono);
				facturaAbono.setValpendiente(facturaTotalAbono);
				facturaAbono.setValorexcedentes(0f);
				facturaAbono.setIdfactura(numeroFactura);
				facturaAbono.setIdestado(Parametro.FACTURA_ESTADO_PAGADA);
				
				//Auditoria
				fecharegistro = new Date();
				facturaAbono.setFecha(fecharegistro);
				facturaAbono.setIp(usuarioBean.getIp());
				facturaAbono.setIdusuario(usuarioBean.getUsuario().getIdusuario());
				
				//Creo la factura
				facturaDAO.ingresarFactura(session, facturaAbono);
				
				//Actualizo la factura principal
				factura.setValbase(valorBrutoPendiente + valorDescuentoPendiente);
				factura.setIdcinecable(nrosfactura.getRuc());
				direccionCinecable = nrosfactura.getDireccion();
				factura.setDircinecable(direccionCinecable);
				factura.setFcaducidad(nrosfactura.getFcaducidad());
				factura.setIdautorizacion(String.valueOf(nrosfactura.getAutorizacion()));
				resolucion = nrosfactura.getResolucion();
				facturaDAO.actualizarFactura(session, factura);
			}
			
			//FORMAS DE PAGO
			if(totalFormasPagoUsar > 0){
				//Obtengo Secuencia
				int IdpagopagosFormasPago = pagosDAO.maxIdPagos(session)+1;
				
				//si existieron formas de pago a usar creo el pago
				Pagos pagosFormasPago = new Pagos();
				pagosFormasPago.setIdpago(IdpagopagosFormasPago);
				pagosFormasPago.setIdfactura(numeroFactura);
				pagosFormasPago.setIdgeneracion(factura.getIdgeneracion());
				pagosFormasPago.setValtotal(totalFormasPagoUsar);
				pagosFormasPago.setEmpresa(usuarioBean.getUsuario().getEmpresa());
				Estado estado = new Estado();
				estado.setIdestado(Parametro.PAGOS_ESTADO_ACTIVO);
				pagosFormasPago.setEstado(estado);
				pagosFormasPago.setIdcuenta(idcuenta);
				
				//Auditoria del pago
				fecharegistro = new Date();
				pagosFormasPago.setFecha(fecharegistro);
				pagosFormasPago.setIp(usuarioBean.getIp());
				pagosFormasPago.setUsuario(usuarioBean.getUsuario());
				
				//Creo la cabecera de pagos
				pagosDAO.ingresarPago(session, pagosFormasPago);
			
				for(Tpagos tpagos : lisFormasPagoCuenta){
					//RECORRO MIS FORMAS DE PAGO A DAR DE BAJA
					if(totalFormasPagoUsar == 0){
						//Si ya no tengo que usar mas formas de pago salimos del ciclo y me sobrarian formas de pago para otra factura
						break;
					}
					float formaPagoUsado = 0;
					if(totalFormasPagoCuenta >= totalFactura){
						//SI MIS FORMAS DE PAGO CUBRIERON EL TOTAL DE LA FACTURA O MAS
						if(tpagos.getValpago() <= totalFormasPagoUsar){
							//si mi forma de pago es menor o igual al total que debo usar
						
							//obtengo mi forma de pago usado
							formaPagoUsado = tpagos.getValpago();
							//el valor de la forma de pago pasa a cero
							tpagos.setValpago(0);
							//El estado de la forma de pago pasa a consumido
							tpagos.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
							//descuento el valor de la forma de pago usado del total de formas de pago a usar
							totalFormasPagoUsar -= formaPagoUsado;
						}else{
							//si mi forma de pago es mayor que el total de formas de pago a usar
							
							//obtengo mi forma de pago usado
							formaPagoUsado = totalFormasPagoUsar;
							//al valor de la forma de pago se le resta el total de formas de pago a usar y me queda un saldo para la siguiente factura
							tpagos.setValpago(tpagos.getValpago() - totalFormasPagoUsar);
							//El estado de la forma de pago queda pendiente
							tpagos.setIdestado(Parametro.TPAGOS_ESTADO_PENDIENTE);
							//el total de formas de pago a usar queda en cero
							totalFormasPagoUsar = 0;
						}
					}else{
						//SI MIS FORMAS DE PAGO CUBRIERON UNA PARTE DE LA FACTURA
						//obtengo mi forma de pago usado
						formaPagoUsado = tpagos.getValpago();
						//el valor de la forma de pago pasa a cero
						tpagos.setValpago(0);
						//El estado de la forma de pago pasa a consumido
						tpagos.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
					}

					//Obtengo Secuencia
					int IdtpagotpagosFormasPago = tpagosDAO.maxIdTpagos(session)+1;
					
					//Ingresar la forma de pago en el detalle del pago
					Tpagos tpagosFormasPago = new Tpagos();
					tpagosFormasPago.setIdtpago(IdtpagotpagosFormasPago);
					tpagosFormasPago.setPagos(pagosFormasPago);
					tpagosFormasPago.setValpago(formaPagoUsado);
					tpagosFormasPago.setFpago(tpagos.getFpago());
					tpagosFormasPago.setNrodocumento(tpagos.getNrodocumento());
					tpagosFormasPago.setBancosByIdbcoemisor(tpagos.getBancosByIdbcoemisor());
					tpagosFormasPago.setBancosByIdbcoemisortar(tpagos.getBancosByIdbcoemisortar());
					tpagosFormasPago.setFcaducidad(tpagos.getFcaducidad());
					tpagosFormasPago.setCodseguridad(tpagos.getCodseguridad());
					tpagosFormasPago.setPropietario(tpagos.getPropietario());
					tpagosFormasPago.setNrocuenta(tpagos.getNrocuenta());
					tpagosFormasPago.setIdestado(Parametro.TPAGOS_ESTADO_CONSUMIDO);
					tpagosFormasPago.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
					tpagosFormasPago.setIdnexo(0);
					
					//Auditoria
					tpagosFormasPago.setIdusuario(usuarioBean.getUsuario().getIdusuario());
					
					//Creo el pago de la forma de pago en el detalle de pagos
					tpagosDAO.ingresarTpago(session, tpagosFormasPago);
				}
				
			}
			
			//Consulto las formas de pago sobrantes disponibles de la cuenta
			List<Tpagos> lisTpagosTmp2 = new ArrayList<Tpagos>();
			for(Tpagos tpagos : lisFormasPagoCuenta){
				if(tpagos.getIdestado() == Parametro.TPAGOS_ESTADO_PENDIENTE){//1
					lisTpagosTmp2.add(tpagos);
				}
			}
			lisFormasPagoCuenta = new ArrayList<Tpagos>(lisTpagosTmp2);
			
			for(Tpagos tpagos : lisFormasPagoCuenta){
				//RECORRO MIS FORMAS DE PAGO SOBRANTES, DESPUES DE PAGAR LAS FACTURAS EL SALDO PASA A EXCEDENTE
				
				//Obtengo Secuencia
				int IdexcedenteExcedentes = excedentesDAO.maxIdexcedente(session)+1;
				
				//Ingresar el excedente
				Excedentes excedentes = new Excedentes();
				excedentes.setIdexcedente(IdexcedenteExcedentes);
				excedentes.setValpago(tpagos.getValpago());
				excedentes.setIdfpago(tpagos.getFpago().getIdfpago());
				if(tpagos.getNrodocumento() !=null && tpagos.getNrodocumento().trim().length() > 0){
					excedentes.setNrodocumento(tpagos.getNrodocumento());
				}
				if(tpagos.getBancosByIdbcoemisor() != null && tpagos.getBancosByIdbcoemisor().getIdbanco() > 0){
					excedentes.setIdbcoemisor(tpagos.getBancosByIdbcoemisor().getIdbanco());
				}
				if(tpagos.getBancosByIdbcoemisortar() != null && tpagos.getBancosByIdbcoemisortar().getIdbanco() > 0){
					excedentes.setIdbcoemisortar(tpagos.getBancosByIdbcoemisortar().getIdbanco());
				}
				if(tpagos.getFcaducidad() != null){
					excedentes.setFcaducidad(tpagos.getFcaducidad());
				}
				if(tpagos.getCodseguridad() != null && tpagos.getCodseguridad().trim().length() > 0){
					excedentes.setCodseguridad(tpagos.getCodseguridad());
				}
				if(tpagos.getPropietario() != null && tpagos.getPropietario().trim().length() > 0){
					excedentes.setPropietario(tpagos.getPropietario());
				}
				if(tpagos.getNrocuenta() != null && tpagos.getNrocuenta().trim().length() > 0){
					excedentes.setNrocuenta(tpagos.getNrocuenta());
				}
				excedentes.setIdestado(Parametro.EXCEDENTE_ESTADO_PENDIENTE);
				excedentes.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
				excedentes.setIdcuenta(idcuenta);
				excedentes.setValpendiente(tpagos.getValpago());
				
				//Auditoria
				fecharegistro = new Date();
				excedentes.setFecha(fecharegistro);
				excedentes.setIdusuario(usuarioBean.getUsuario().getIdusuario());
				excedentes.setIp(usuarioBean.getIp());
				
				//Creo el excedente
				excedentesDAO.ingresarExcedentes(session, excedentes);
			}
			
			//cambio el estado de la cuenta del cliente
			ctacliente.setIdestado(Parametro.CUENTA_CLIENTE_PENDIENTE);
			ctaclienteDAO.actualizarCtacliente(session, ctacliente); 
			
			//GRABO LOS DATOS PARA LA IMPRESION DE LA FACTURA
			
			//CABECERA IMPRESION
			numeroFacturaParam[0] = numeroFactura;
			Cabimpfacturas cabimpfacturas = new Cabimpfacturas();
			int idcabfactura = cabimpfacturasDAO.maxIdcabfactura(session) + 1;
			cabimpfacturas.setIdcabfactura(idcabfactura);
			cabimpfacturas.setResolucion(resolucion);
			cabimpfacturas.setAutorizacion(idAutorizacion);
			cabimpfacturas.setIdfactura(numeroFactura);
			cabimpfacturas.setDircinecable(direccionCinecable);
			cabimpfacturas.setDircliente(direccionCliente);
			cabimpfacturas.setIdcliente(idCliente);
			cabimpfacturas.setNomscliente(nombreCliente);
			
			String telefono = telefonoBO.consultarTelefonoPorCuenta(idcuenta);
			if(telefono != null && telefono.trim().length() > 0){
				cabimpfacturas.setTelcliente(telefono);
			}
			
			cabimpfacturas.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
			cabimpfacturas.setIdestado(1);
			cabimpfacturas.setTotalfactura(totalAbono);
			cabimpfacturas.setSubtotal(facturaSubtotalAbono);
			cabimpfacturas.setValimpiva(facturaIvaAbono);
			cabimpfacturas.setValimpice(facturaIceAbono);
			cabimpfacturas.setValdescre(facturaDescuentosAbono);
			cabimpfacturas.setIdcuenta(idcuenta);
			
			//grabo cabimpfacturas
			cabimpfacturasDAO.ingresarCabimpfacturas(session, cabimpfacturas);
			
			int orden = 1;
			for(DetalleContratoPojo detalleContratoPojo : lisDetalleContratoPojo){
				//DETALLE IMPRESION
				Detimpfacturas detimpfacturas = new Detimpfacturas();
				detimpfacturas.setIdcabfacturas(cabimpfacturas.getIdcabfactura());
				detimpfacturas.setDetalle(detalleContratoPojo.getProducto().getNombre());
				float valor = detalleContratoPojo.getTotalServicioAbono() + detalleContratoPojo.getTotalDescuentoAbono();//facturaServiciosAbono + facturaDescuentosAbono; 
				detimpfacturas.setValor(valor);
				detimpfacturas.setOrden(orden);
				int iddetfacturas = detimpfacturasDAO.maxIddetfacturas(session) + 1;
				detimpfacturas.setIddetfacturas(iddetfacturas);
				detimpfacturas.setIdcuenta(cabimpfacturas.getIdcuenta());
				detimpfacturas.setIdempresa(cabimpfacturas.getIdempresa());
				detimpfacturas.setIdestado(1);
				
				//grabo detimpfacturas
				detimpfacturasDAO.ingresarDetimpfacturas(session, detimpfacturas);
				
				orden++;
			}
			
			session.getTransaction().commit();
			ok = true;
		}catch(SecuenciaFacturaException e){
			session.getTransaction().rollback();
			throw new SecuenciaFacturaException(e);
		}catch(Exception he){
			session.getTransaction().rollback();
			throw new Exception(he);
		}finally{
			session.close();
		}
		
		return ok;
	}
	
	private void anularCargosByIdGeneracion(int idsecuencia, Session session) throws Exception {
		
		CargosBO cargosBO = new CargosBO();
		CargosDAO cargosDAO = new CargosDAO();
		Date fecharegistro = new Date();
		UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean"); 
		
		//RECORRO LOS CARGOS DETALLE, DESCUENTOS, IMPUESTOS DE LA GENERACION
		List<Cargos> lisCargos = cargosBO.lisCargosGeneracionById(idsecuencia);
		for(Cargos cargos : lisCargos){
			//el valor de los cargos de la generacion quedan en cero
			cargos.setValpendiente((float)0);
			
			//el estado del cargo pasa a pagado
			Estado estado = new Estado();
			estado.setIdestado(Parametro.CARGOS_ESTADO_PAGADO);
			cargos.setEstado(estado);
			
			//auditoria
			fecharegistro = new Date();
			cargos.setFecha(fecharegistro);
			cargos.setUsuario(usuarioBean.getUsuario());
			
			//actualizo los cargos
			cargosDAO.actualizarCargos(session, cargos);
		}
	}
	
	public void obtenerRubrosMora(int idcuenta, Object args[]) throws Exception {
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            facturaDAO.obtenerRubrosMora(session, idcuenta, args);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
	}
}
