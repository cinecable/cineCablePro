package net.cinecable.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import net.cinecable.dao.IGeneracionBancoDepositoDao;
import net.cinecable.dm.DebitoBancarioDM;
import net.cinecable.dm.ParamDebitoDM;
import net.cinecable.enums.Estados;
import net.cinecable.enums.FileType;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.GeneracionBancoDepositos;
import net.cinecable.model.base.GeneracionDebitos;
import net.cinecable.model.base.GeneracionDebitosDetalle;
import net.cinecable.model.base.ParametroDatosBanco;
import net.cinecable.model.base.ParametrosBancos;
import net.cinecable.model.extension.DebitosDetalle;
import net.cinecable.model.extension.FacturasDebito;
import net.cinecable.service.IBancoServices;
import net.cinecable.service.IDebitosBancariosService;
import net.cinecable.service.IFacturaGeneracionDebitoBancarioService;
import net.cinecable.service.IFacturaService;
import net.cinecable.service.IParametrosBancoService;
import net.cinecable.util.StreamUtil;
import net.cinecable.util.ZipUtil;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import pojo.annotations.Bancos;
import pojo.annotations.Ctacliente;
import pojo.annotations.Estado;
import pojo.annotations.Factura;
import pojo.annotations.Fpago;
import pojo.annotations.Pagos;
import pojo.annotations.Tpagos;
import pojo.annotations.custom.DatosImport;
import util.MessageUtil;

@ManagedBean(name = "debitoController")
@RequestScoped
public class DebitoController extends BaseController {
	@ManagedProperty(value = "#{debitoBancarioDM}")
	private DebitoBancarioDM debitoDM;

	@ManagedProperty(value = "#{paramDebitoDM}")
	private ParamDebitoDM paramDebitoDM;
	@EJB
	IBancoServices iBancoService;
	@EJB
	IFacturaGeneracionDebitoBancarioService iFacturaGeneracion;
	@EJB
	IParametrosBancoService iparametroBanco;
	@EJB
	IDebitosBancariosService idebitos;

	@EJB
	IFacturaService ifacturaService;
	@EJB
	IGeneracionBancoDepositoDao igeneracionBanco;

	public void fileUpload(FileUploadEvent event) {
		try {
			debitoDM.setArchivoSubido(StreamUtil.IOStreamtoString(event
					.getFile().getInputstream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DebitoBancarioDM getDebitoDM() {
		return debitoDM;
	}

	public void setDebitoDM(DebitoBancarioDM debitoDM) {
		this.debitoDM = debitoDM;
	}

	@SuppressWarnings("serial")
	public void consultaBancos() {
		try {
			debitoDM.setTablaActiva(true);
			debitoDM.getDebitosClientes().clear();
			List<Bancos> temp = null;
			if (debitoDM.getBanco() != null) {
				temp = new ArrayList<Bancos>() {
					{
						add(debitoDM.getBanco());
					}
				};
			}

			iFacturaGeneracion.getDebitosClientesBancoId(debitoDM
					.getDebitosClientes(), debitoDM.getBanco() != null ? temp
					: paramDebitoDM.getBancos(), debitoDM.isDescargaTodo());
			
			debitoDM.calcularTotalGeneralExport();
			
			//Se comenta esto ya que aqui unicamente se consulta, esto pasa al boton proceso
			/*generaDescarga(debitoDM.getDebitosClientes(),
					debitoDM.getBanco() == null ? paramDebitoDM.getBancos()
							: temp);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("serial")
	public StreamedContent getFileDownload() {
		if(debitoDM.getDebitosClientes() != null && !debitoDM.getDebitosClientes().isEmpty()){
			//**inicio**Se agrega este codigo de consultaBancos() porque es aqui donde se procesa
			List<Bancos> temp = null;
			if (debitoDM.getBanco() != null) {
				temp = new ArrayList<Bancos>() {
					{
						add(debitoDM.getBanco());
					}
				};
			}
			
			generaDescarga(debitoDM.getDebitosClientes(),
					debitoDM.getBanco() == null ? paramDebitoDM.getBancos()
							: temp);
			//**fin**Se agrega este codigo de consultaBancos() porque es aqui donde se procesa
			
			if (debitoDM.getArchivoDescarga() != null) {
				debitoDM.setRawFile(new ByteArrayInputStream(debitoDM
						.getArchivoDescarga().getBytes()));
				debitoDM.setFileDown(new DefaultStreamedContent(debitoDM
						.getRawFile(), debitoDM.getFormatting().getDescription(),
						DateFormat.getDateInstance(DateFormat.SHORT).format(
								debitoDM.getFechaActual()) ));
			} else if (debitoDM.getRawFile() != null) {
				debitoDM.setFileDown(new DefaultStreamedContent(debitoDM
						.getRawFile(), debitoDM.getFormatting().getDescription(),
						DateFormat.getDateInstance(DateFormat.SHORT).format(
								debitoDM.getFechaActual()) ));
			}
			try {
				if (debitoDM != null)
					idebitos.guardar(debitoDM.getDebitos());
			} catch (EntidadNoGrabadaException e) {
				e.printStackTrace();
			}
			return debitoDM.getFileDown();
		}else{
			new MessageUtil().showWarnMessage("Debe presionar el botón Consultar antes de Procesar", "");
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void generaDescarga(List<FacturasDebito> debitosClientes,
			List<Bancos> banco) {
		try {
			if (debitoDM.getRawFile() != null)
				debitoDM.getRawFile().close();
			StreamUtil.cleanDirectory("temp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		debitoDM.setDebitos(new GeneracionDebitos());
		GeneracionDebitos debitos = debitoDM.getDebitos();
		// guarda los valores iniciales y finales de secuencia de debitos
		if (!debitosClientes.isEmpty()) {
			// Guarda la generacion de debitos
			debitos.setDetalles(new ArrayList<GeneracionDebitosDetalle>());
			debitos.setEstado(Estados.ACTIVO.getDescription());
			debitos.setFecha(Calendar.getInstance().getTime());
			debitos.setNroImpresiones(0);
			debitos.setReceptado(0);
			debitos.setEstado(Estados.ACTIVO.getDescription());
			debitos.setIdgenInicial((long) debitosClientes.get(0).getFactura()
					.getIdsecuencia());
			debitos.setIdgenfinal((long) debitosClientes
					.get(debitosClientes.size() - 1).getFactura()
					.getIdsecuencia());
		}

		StringBuilder data = null;
		String file = "debitos" + debitoDM.getFechaActual().getMonth()
				+ debitoDM.getFechaActual().getYear();
		for (Bancos ban : banco) {
			List<FacturasDebito> copiaDebitos = new ArrayList<FacturasDebito>();
			for (int a = 0; a < debitosClientes.size(); a++) {
				if (debitosClientes.get(a).getDebito().getBancos().getIdbanco() == ban
						.getIdbanco()) {
					copiaDebitos.add(debitosClientes.get(a));
				}
			}
			if (copiaDebitos.isEmpty())
				continue;

			ParametrosBancos parametroBanco = iparametroBanco
					.consultaParametrosbyIdBanco((long) ban.getIdbanco(), "SUB");
			data = new StringBuilder("");

			for (int h = 0; h < copiaDebitos.size(); h++) {
				// Guardar la Generacion de Debitos Detalles
				GeneracionDebitosDetalle detalle = new GeneracionDebitosDetalle();
				detalle.setDebito(debitos);
				detalle.setIdGeneracion((long) copiaDebitos.get(h).getFactura()
						.getIdgeneracion());
				detalle.setValorEnvio(copiaDebitos.get(h).getFactura()
						.getValpendiente().doubleValue());
				detalle.setNroFactura(copiaDebitos.get(h).getFactura()
						.getIdfactura());
				Ctacliente ctaClitmp = new Ctacliente();
				ctaClitmp.setIdcuenta(copiaDebitos.get(h).getFactura()
						.getIdcuenta());
				detalle.setCuentaCliente(ctaClitmp);
				detalle.setValorRecibido(0D);
				detalle.setEstado(Estados.ACTIVO.getDescription());
				debitos.getDetalles().add(detalle);
				data.append(getDataAppend(copiaDebitos.get(h), parametroBanco));
			}
			if (banco.size() > 1) {
				StreamUtil.dataStringToFile("temp", file + ban.getNombre(),
						data.toString());
			}
		}
		if (banco.size() > 1) {
			new ZipUtil(file, file);
			debitoDM.setRawFile(StreamUtil.getInputStreamFromPath("temp"
					+ File.separator + file + ".zip"));
			debitoDM.setFormatting(FileType.ZIP);
			StreamUtil.cleanDirectory("temp");
		} else {
			debitoDM.setArchivoDescarga(data.toString());
			debitoDM.setFormatting(FileType.TEXT);
		}

	}
	
	private int getNumeroPlano(Float numero){
		String num = numero.toString().replace(".", "");
		//23,30
		//>>2330
		return Integer.parseInt(num);
	}
	
	//Dentro hacia fuera
	private String getDataAppend(FacturasDebito debitosInfo, ParametrosBancos parametroBanco) {
		StringBuilder data = new StringBuilder();
		int maxpar = parametroBanco.getParametros().get(parametroBanco.getParametros().size() - 1).getTipoParametro();
		for (int i = 0; i < parametroBanco.getParametros().size(); i++) {
			ParametroDatosBanco par = parametroBanco.getParametros().get(i);
			int caselog = par.getOrden();
			if ((i + 1) == caselog) {

				switch (par.getTipoParametro()) {
				case ParametroDatosBanco.VALORDEBITO:
					if (parametroBanco.getParametrosCondicion().getToken() != null) {
						data.append(debitosInfo.getFactura().getValpendiente());
						data.append(parametroBanco.getParametrosCondicion().getToken());
					}else{
						int longText =parametroBanco.getParametros().get(i).getLongitudHasta()-parametroBanco.getParametros().get(i).getLongitudDesde()+1;
						String datoText = String.format("%0"+longText+"d", getNumeroPlano(debitosInfo.getFactura().getValpendiente()));
						data.append(datoText);
					}
					
					if (maxpar == ParametroDatosBanco.VALORDEBITO) {
						data.append("\r\n");
						break;
					}

					break;
				case ParametroDatosBanco.REFERENCIA:
					data.append(debitosInfo.getFactura().getIdsecuencia());
					if (maxpar == ParametroDatosBanco.REFERENCIA) {
						data.append("\r\n");
						break;
					}
					if (parametroBanco.getParametrosCondicion().getToken() != null) {
						data.append(parametroBanco.getParametrosCondicion().getToken());
					}
					break;
				case ParametroDatosBanco.TIPO_CUENTA:
					data.append(debitosInfo.getDebito().getIdtipocuenta());
					if (maxpar == ParametroDatosBanco.TIPO_CUENTA) {
						data.append("\r\n");
						break;
					}
					if (parametroBanco.getParametrosCondicion().getToken() != null) {
						data.append(parametroBanco.getParametrosCondicion().getToken());
					}
					break;
				case ParametroDatosBanco.CLIENTE_NOMBRE:
					data.append(debitosInfo.getDebito().getPropietario());
					if (maxpar == ParametroDatosBanco.CLIENTE_NOMBRE) {
						data.append("\r\n");
						break;
					}
					if (parametroBanco.getParametrosCondicion().getToken() != null) {
						data.append(parametroBanco.getParametrosCondicion().getToken());
					}
					break;
				case ParametroDatosBanco.CUENTA_CLIENTE:
					data.append(debitosInfo.getDebito().getNrodebito());
					if (maxpar == ParametroDatosBanco.CUENTA_CLIENTE) {
						data.append("\r\n");
						break;
					}
					if (parametroBanco.getParametrosCondicion().getToken() != null) {
						data.append(parametroBanco.getParametrosCondicion().getToken());
					}
					break;
				case ParametroDatosBanco.CUENTA_SEGURIDAD:
					data.append(debitosInfo.getDebito().getCodigoseguridad());
					if (maxpar == ParametroDatosBanco.CUENTA_SEGURIDAD) {
						data.append("\r\n");
						break;
					}
					if (parametroBanco.getParametrosCondicion().getToken() != null) {
						data.append(parametroBanco.getParametrosCondicion().getToken());
					}
					break;
				}
			} else {
				if (parametroBanco.getParametros().get(i) != null)
					i = 0;
			}
		}
		return data.toString();
	}

	//Fuera hacia dentro
	public void procesarSubida() {
		/*List<DebitosDetalle> debDet = new ArrayList<DebitosDetalle>();

		Bancos ban = debitoDM.getBancoGeneracion();
		ParametrosBancos parametroBanco = iparametroBanco
				.consultaParametrosbyIdBanco((long) ban.getIdbanco(), "GEN");
		int desdefila = ((parametroBanco.getParametros() != null || !parametroBanco
				.getParametros().isEmpty()) ? parametroBanco.getParametros()
				.get(0).getLineaDesde() : 0) - 1;
		if (desdefila < 0)
			desdefila = 0;

		String[] dataRaw = debitoDM.getArchivoSubido().split("\n");
		try {
			for (int i = desdefila; i < dataRaw.length; i++) {
				DebitosDetalle detalleFila = new DebitosDetalle();
				debDet.add(detalleFila);

				// info pago
				Pagos pago = new Pagos();
				Estado est = new Estado();
				est.setIdestado(Estados.ACTIVO.getDescription());
				pago.setEstado(est);

				Tpagos tpago = new Tpagos();
				detalleFila.setPagos(pago);

				GeneracionBancoDepositos depositos = new GeneracionBancoDepositos();
				detalleFila.setBancoDepositos(depositos);
				depositos.setBanco(ban);
				depositos.setEstado(Estados.ACTIVO.getDescription());

				// inicio nuevo proceso
				{
					// fill data

					// data previa de tpago

					tpago.setBancosByIdbcoemisor(ban);
					//
					pago.getTpagoses().add(tpago);
					pago.setUsuario(depositos.getUsuario());
					pago.setIp(depositos.getIp());
					pago.setEmpresa(depositos.getEmpresa());
					// forma pago debito
					Fpago fpago = new Fpago();
					fpago.setIdfpago(9);
					tpago.setFpago(fpago);
					tpago.setPagos(pago);
					tpago.setIdusuario(pago.getUsuario().getIdusuario());
					tpago.setIdempresa(pago.getEmpresa().getIdempresa());
					tpago.setIdestado(Estados.ACTIVO.getDescription());
					//
					debitoDM.getPagos().add(pago);
					//
				}
				for (ParametroDatosBanco datos : parametroBanco.getParametros()) {
					String token = parametroBanco.getParametrosCondicion()
							.getToken();
					String dataSplit[] = dataRaw[i].split(token == null ? ""
							: token);
					switch (datos.getTipoParametro()) {

					case ParametroDatosBanco.VALORDEBITO:

						if (token != null) {
							tpago.setValpago(Float.parseFloat(dataSplit[datos
									.getColumna() - 1]));
							depositos
									.setValorDebito(Double
											.parseDouble(dataSplit[datos
													.getColumna() - 1]));
						} else {
							tpago.setValpago(Float.parseFloat(dataSplit[0]
									.substring(datos.getLongitudDesde(),
											datos.getLongitudHasta())));
							depositos.setValorDebito(Double
									.parseDouble(dataSplit[0].substring(
											datos.getLongitudDesde(),
											datos.getLongitudHasta())));
						}

						break;

					case ParametroDatosBanco.REFERENCIA:
						Integer idSec = null;
						if (token != null) {
							idSec = Integer.parseInt(dataSplit[datos
									.getColumna() - 1]);
							depositos.setReferencia(dataSplit[datos
									.getColumna() - 1]);
						} else {
							idSec = Integer.parseInt(dataSplit[0].substring(
									datos.getLongitudDesde(),
									datos.getLongitudHasta()));
							depositos.setReferencia(dataSplit[0].substring(
									datos.getLongitudDesde(),
									datos.getLongitudHasta()));
						}
						// consulta factura para tener el idgeneracion
						Factura fac = ifacturaService
								.getFacturabyReferenciaSecuencia(idSec
										.longValue());
						detalleFila.setFactura(fac);
						pago.setIdfactura(fac.getIdfactura());
						pago.setIdgeneracion(fac.getIdgeneracion());
						pago.setValtotal(fac.getValpendiente());
						//tpago.setNrodocumento(fac.getIdcliente());
						tpago.setNrodocumento(fac.getClientes().getIdcliente());
						break;

					case ParametroDatosBanco.ESTADO:
						if (token != null) {
							depositos.setEstadoResp(dataSplit[datos
									.getColumna() - 1]);
						} else {
							depositos.setEstadoResp(dataSplit[0].substring(
									datos.getLongitudDesde(),
									datos.getLongitudHasta()));
						}

						break;

					case ParametroDatosBanco.TIPO_CUENTA:
						if (token != null) {
							depositos.setTipoCuenta(dataSplit[datos
									.getColumna() - 1]);
						} else {
							depositos.setTipoCuenta(dataSplit[0].substring(
									datos.getLongitudDesde(),
									datos.getLongitudHasta()));
						}

						break;

					case ParametroDatosBanco.OBSERVACION_TRANSACCION:
						if (token != null) {
							depositos.setObservacion(dataSplit[datos
									.getColumna() - 1]);
						} else {
							depositos.setObservacion(dataSplit[0].substring(
									datos.getLongitudDesde(),
									datos.getLongitudHasta()));
						}
						break;

					case ParametroDatosBanco.CLIENTE_NOMBRE:
						if (token != null) {
							depositos.setNombreCliente(dataSplit[datos
									.getColumna() - 1]);
						} else {
							depositos.setNombreCliente(dataSplit[0].substring(
									datos.getLongitudDesde(),
									datos.getLongitudHasta()));
						}
						break;

					case ParametroDatosBanco.ID_UNICO:
						if (token != null) {
							depositos
									.setIdgeneracionUnico(Long
											.valueOf(dataSplit[datos
													.getColumna() - 1]));
						} else {
							depositos.setIdgeneracionUnico(Long
									.valueOf(dataSplit[0].substring(
											datos.getLongitudDesde(),
											datos.getLongitudHasta())));
						}
						break;
					}
				}
			}
			igeneracionBanco.guardarDebitosRecibidos(debDet);
		} catch (Exception e) {
			e.printStackTrace();
			showInfo("Procesamiento de Debito", e.getMessage());
		}
		
		showInfo("Procesamiento de Debito", "Completado Exitosamente");
		*/

		//Se comenta lo de arriba para separar, ese código va en la consulta donde llena una lista DebitosDetalle
		//y aquí en el proceso se toma esa lista DebitosDetalle y se manda a grabar
		if(debitoDM.getLisDebitosDetalle() != null && debitoDM.getLisDebitosDetalle().size() > 0){
			try{
				igeneracionBanco.guardarDebitosRecibidos(debitoDM.getLisDebitosDetalle());
				showInfo("Procesamiento de Debito", "Completado Exitosamente");
			} catch (Exception e) {
				e.printStackTrace();
				showInfo("Procesamiento de Debito", e.getMessage());
			}
		}else{
			new MessageUtil().showWarnMessage("Debe presionar el botón consultar antes de procesar","");
		}
	}
	
	public void consultarSubida() {
		debitoDM.setTablaActiva(false);
		debitoDM.setTotalGeneralImport(0f);
		
		if(debitoDM.getArchivoSubido() != null && !debitoDM.getArchivoSubido().isEmpty()){
			List<DebitosDetalle> debDet = new ArrayList<DebitosDetalle>();
			debitoDM.setLisDatosImport(new ArrayList<DatosImport>());
	
			Bancos ban = debitoDM.getBancoGeneracion();
			ParametrosBancos parametroBanco = iparametroBanco
					.consultaParametrosbyIdBanco((long) ban.getIdbanco(), "GEN");
			
			//**inicio**poner nombre a las columnas de la vista previa
			List<String> lisTitulosColumnas = new ArrayList<String>();
			for (ParametroDatosBanco datos : parametroBanco.getParametros()) {
				if(datos.getTitulocolumna() != null && datos.getTitulocolumna().trim().length() > 0){
					lisTitulosColumnas.add(datos.getTitulocolumna());
				}
			}
			
			if(lisTitulosColumnas.size() >= 1){
				debitoDM.setTituloImport1(lisTitulosColumnas.get(0));
			}
			if(lisTitulosColumnas.size() >= 2){
				debitoDM.setTituloImport2(lisTitulosColumnas.get(1));
			}
			if(lisTitulosColumnas.size() >= 3){
				debitoDM.setTituloImport3(lisTitulosColumnas.get(2));
			}
			if(lisTitulosColumnas.size() >= 4){
				debitoDM.setTituloImport4(lisTitulosColumnas.get(3));
			}
			if(lisTitulosColumnas.size() >= 5){
				debitoDM.setTituloImport5(lisTitulosColumnas.get(4));
			}
			//**fin**poner nombre a las columnas de la vista previa
			
			int desdefila = ((parametroBanco.getParametros() != null || !parametroBanco
					.getParametros().isEmpty()) ? parametroBanco.getParametros()
					.get(0).getLineaDesde() : 0) - 1;
			if (desdefila < 0)
				desdefila = 0;
	
			String[] dataRaw = debitoDM.getArchivoSubido().split("\n");
			try {
				for (int i = desdefila; i < dataRaw.length; i++) {
					DebitosDetalle detalleFila = new DebitosDetalle();
					debDet.add(detalleFila);
	
					// info pago
					Pagos pago = new Pagos();
					Estado est = new Estado();
					est.setIdestado(Estados.ACTIVO.getDescription());
					pago.setEstado(est);
	
					Tpagos tpago = new Tpagos();
					detalleFila.setPagos(pago);
	
					GeneracionBancoDepositos depositos = new GeneracionBancoDepositos();
					detalleFila.setBancoDepositos(depositos);
					depositos.setBanco(ban);
					depositos.setEstado(Estados.ACTIVO.getDescription());
	
					// inicio nuevo proceso
					{
						// fill data
	
						// data previa de tpago
	
						tpago.setBancosByIdbcoemisor(ban);
						//
						pago.getTpagoses().add(tpago);
						pago.setUsuario(depositos.getUsuario());
						pago.setIp(depositos.getIp());
						pago.setEmpresa(depositos.getEmpresa());
						// forma pago debito
						Fpago fpago = new Fpago();
						fpago.setIdfpago(9);
						tpago.setFpago(fpago);
						tpago.setPagos(pago);
						tpago.setIdusuario(pago.getUsuario().getIdusuario());
						tpago.setIdempresa(pago.getEmpresa().getIdempresa());
						tpago.setIdestado(Estados.ACTIVO.getDescription());
						//
						debitoDM.getPagos().add(pago);
						//
					}
	
					List<String> lisDatosColumnas = new ArrayList<String>();
					for (ParametroDatosBanco datos : parametroBanco.getParametros()) {
						String token = parametroBanco.getParametrosCondicion()
								.getToken();
						String dataSplit[] = dataRaw[i].split(token == null ? ""
								: token);
						
						//**inicio**lleno cada fila de la vista previa
						if(datos.getTitulocolumna() != null && datos.getTitulocolumna().trim().length() > 0){
							if (token != null) {
								lisDatosColumnas.add(dataSplit[datos.getColumna() - 1]);
								
								if(datos.getTitulocolumna().trim().toLowerCase().equalsIgnoreCase("Valor")){
									debitoDM.setTotalGeneralImport(debitoDM.getTotalGeneralImport()+Float.parseFloat(dataSplit[datos.getColumna() - 1]));
								}
							} else {
								lisDatosColumnas.add(dataSplit[0].substring(datos.getLongitudDesde(),datos.getLongitudHasta()));
								
								if(datos.getTitulocolumna().trim().toLowerCase().equalsIgnoreCase("Valor")){
									debitoDM.setTotalGeneralImport(debitoDM.getTotalGeneralImport()+Float.parseFloat(dataSplit[0].substring(datos.getLongitudDesde(),datos.getLongitudHasta())));
								}
							}
						}
						//**fin**lleno cada fila de la vista previa
						
						switch (datos.getTipoParametro()) {
	
						case ParametroDatosBanco.VALORDEBITO:
	
							if (token != null) {
								tpago.setValpago(Float.parseFloat(dataSplit[datos
										.getColumna() - 1]));
								depositos
										.setValorDebito(Double
												.parseDouble(dataSplit[datos
														.getColumna() - 1]));
							} else {
								tpago.setValpago(Float.parseFloat(dataSplit[0]
										.substring(datos.getLongitudDesde(),
												datos.getLongitudHasta())));
								depositos.setValorDebito(Double
										.parseDouble(dataSplit[0].substring(
												datos.getLongitudDesde(),
												datos.getLongitudHasta())));
							}
	
							break;
	
						case ParametroDatosBanco.REFERENCIA:
							Integer idSec = null;
							if (token != null) {
								idSec = Integer.parseInt(dataSplit[datos
										.getColumna() - 1]);
								depositos.setReferencia(dataSplit[datos
										.getColumna() - 1]);
							} else {
								idSec = Integer.parseInt(dataSplit[0].substring(
										datos.getLongitudDesde(),
										datos.getLongitudHasta()));
								depositos.setReferencia(dataSplit[0].substring(
										datos.getLongitudDesde(),
										datos.getLongitudHasta()));
							}
							// consulta factura para tener el idgeneracion
							Factura fac = ifacturaService
									.getFacturabyReferenciaSecuencia(idSec
											.longValue());
							detalleFila.setFactura(fac);
							pago.setIdfactura(fac.getIdfactura());
							pago.setIdgeneracion(fac.getIdgeneracion());
							pago.setValtotal(fac.getValpendiente());
							//tpago.setNrodocumento(fac.getIdcliente());
							tpago.setNrodocumento(fac.getClientes().getIdcliente());
							break;
	
						case ParametroDatosBanco.ESTADO:
							if (token != null) {
								depositos.setEstadoResp(dataSplit[datos
										.getColumna() - 1]);
							} else {
								depositos.setEstadoResp(dataSplit[0].substring(
										datos.getLongitudDesde(),
										datos.getLongitudHasta()));
							}
	
							break;
	
						case ParametroDatosBanco.TIPO_CUENTA:
							if (token != null) {
								depositos.setTipoCuenta(dataSplit[datos
										.getColumna() - 1]);
							} else {
								depositos.setTipoCuenta(dataSplit[0].substring(
										datos.getLongitudDesde(),
										datos.getLongitudHasta()));
							}
	
							break;
	
						case ParametroDatosBanco.OBSERVACION_TRANSACCION:
							if (token != null) {
								depositos.setObservacion(dataSplit[datos
										.getColumna() - 1]);
							} else {
								depositos.setObservacion(dataSplit[0].substring(
										datos.getLongitudDesde(),
										datos.getLongitudHasta()));
							}
							break;
	
						case ParametroDatosBanco.CLIENTE_NOMBRE:
							if (token != null) {
								depositos.setNombreCliente(dataSplit[datos
										.getColumna() - 1]);
							} else {
								depositos.setNombreCliente(dataSplit[0].substring(
										datos.getLongitudDesde(),
										datos.getLongitudHasta()));
							}
							break;
	
						case ParametroDatosBanco.ID_UNICO:
							if (token != null) {
								depositos
										.setIdgeneracionUnico(Long
												.valueOf(dataSplit[datos
														.getColumna() - 1]));
							} else {
								depositos.setIdgeneracionUnico(Long
										.valueOf(dataSplit[0].substring(
												datos.getLongitudDesde(),
												datos.getLongitudHasta())));
							}
							break;
						}
					}
					
					//**inicio**paso la data de la fila a la lista de la vista previa
					DatosImport datosImport = new DatosImport();
					if(lisDatosColumnas.size() >= 1){
						datosImport.setDato1(lisDatosColumnas.get(0));
					}
					if(lisDatosColumnas.size() >= 2){
						datosImport.setDato2(lisDatosColumnas.get(1));
					}
					if(lisDatosColumnas.size() >= 3){
						datosImport.setDato3(lisDatosColumnas.get(2));
					}
					if(lisDatosColumnas.size() >= 4){
						datosImport.setDato4(lisDatosColumnas.get(3));
					}
					if(lisDatosColumnas.size() >= 5){
						datosImport.setDato5(lisDatosColumnas.get(4));
					}
					debitoDM.getLisDatosImport().add(datosImport);
					//**fin**paso la data de la fila a la lista de la vista previa
				}
				debitoDM.setLisDebitosDetalle(debDet);
			} catch (Exception e) {
				e.printStackTrace();
				showInfo("Consulta de Debito", "Archivo Incorrecto"/*e.getMessage()*/);
			}
		}else{
			new MessageUtil().showWarnMessage("Debe seleccionar un archivo", "");
		}
	}

	public void init() {
		try {
			debitoDM = new DebitoBancarioDM();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ParamDebitoDM getParamDebitoDM() {
		return paramDebitoDM;
	}

	public void setParamDebitoDM(ParamDebitoDM paramDebitoDM) {
		this.paramDebitoDM = paramDebitoDM;
	}

}
