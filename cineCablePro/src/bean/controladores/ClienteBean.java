/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controladores;

import bo.negocio.ConyugeBO;
import bo.negocio.CtaclienteBO;
import bo.negocio.CtasprodBO;
import bo.negocio.MensajesBO;


import global.Parametro;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pojo.annotations.*;
import util.FacesUtil;
import util.MessageUtil;

/**
 *
 * @author user
 */
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4517052200556838916L;
    private int idcuenta;
    private Ctacliente ctacliente;
	private Clientes clientes;
	private String tipopersona;
	private String genero;
	private String estadoCuenta;

	private Mensajes mensajes;

	private Conyuge conyuge;
    private boolean mostrarMensaje;
    private List<Ctasprod> lisCtasprod;

    public ClienteBean() {
    	ctacliente = new Ctacliente(0, new Empresa(), new Clientes());
    	mensajes = new Mensajes();
    	mostrarMensaje = false;
    }
    
    @PostConstruct
	public void initClienteBean() {
    	
		FacesUtil facesUtil = new FacesUtil();
		idcuenta = Integer
				.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
						.getParametroUrl("idcuenta").toString() : "0");

		if (idcuenta > 0) {
			try{
				//Al recibir por parametro el idcuenta, consultamos
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
				
				if(ctacliente != null && ctacliente.getIdcuenta() > 0){
					// para mostrar el tipo de persona JURIDICO o NATURAL
					if (ctacliente.getClientes().getIdtipopersona() >0) {
						if (ctacliente.getClientes().getIdtipopersona()==1) {
							tipopersona="Natural";
						} else {
							if (ctacliente.getClientes().getIdtipopersona()==2) {
								tipopersona="Juridica";
							} else {
								tipopersona="No Definido";
							}
						}
						
					} else {
						tipopersona="No-Definido";
					}
					
					
					// para mostrar el genero del cliente
					if (ctacliente.getClientes().getGenero() >0) {
						if (ctacliente.getClientes().getGenero()==1) {
							genero="Masculino";
						} else {
							if (ctacliente.getClientes().getIdtipopersona()==2) {
								genero="Femenino";
							} else {
								tipopersona="Otros generos";
							}
						}
						
					} else {
						tipopersona="No-Definido";
					}
							
					//Consultar mensajes para el cliente
					MensajesBO mensajesBO = new MensajesBO();
					mensajes = mensajesBO.getMensajesByIdcliente(ctacliente.getClientes().getIdcliente());
					
					Calendar ahorita = Calendar.getInstance();
					
					if(mensajes != null && mensajes.getDescripcion() != null && mensajes.getDescripcion().trim().length() > 0){
						if(mensajes.getFechacaducidad().compareTo(ahorita.getTime()) >= 0){
							mostrarMensaje = true;
						}
					}else{
						mostrarMensaje = false;
					}
				}
				
				//mostramos los productos con sus servicios
				muestraProductosServicios();
				consultaConyuge();
				
				//Consultar estado de la cuenta
				boolean estadoEncontrado = false;
				for(Ctasprod ctasprod : lisCtasprod){
					//Si por lo menos hay un producto activo entonces la cuenta esta activa
					if(ctasprod.getEstado() != null && ctasprod.getEstado().getIdestado() == Parametro.ESTADO_ACTIVO){
						estadoEncontrado = true;
						break;
					}
				}
				if(estadoEncontrado){
					estadoCuenta = "ACTIVA";
				}else{
					estadoEncontrado = true;
					for(Ctasprod ctasprod : lisCtasprod){
						//Si todos los productos estan cancelados entonces la cuenta esta cancelada
						if(ctasprod.getEstado() != null && ctasprod.getEstado().getIdestado() != Parametro.ESTADO_INACTIVO){
							estadoEncontrado = false;
							break;
						}
					}
					if(estadoEncontrado){
						estadoCuenta = "CANCELADA";
					}else{
						estadoCuenta = "ESPERA";
					}
				}
			}
			catch(Exception re){
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}
    
    public void consultaConyuge(){
		if(this.idcuenta > 0){
			try {
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				Ctacliente ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
				
				if(ctacliente != null && ctacliente.getClientes() != null && ctacliente.getClientes().getIdcliente() != null && ctacliente.getClientes().getIdcliente().trim().length() > 0){
					clientes = ctacliente.getClientes();										
					
					//consultar conyuge
					ConyugeBO conyugeBO = new ConyugeBO();
					conyuge = conyugeBO.getConyugeByIdcliente(clientes.getIdcliente());
					
					if(conyuge != null){
						if(conyuge.getClientes() == null){
							conyuge.setClientes(new Clientes(null, new Tipocliente(), new Usuario(), new Empresa(), null, null, null, null, null, null, new Date(), null, new Estadocivil(), 1, 1, new Date(), null, new Tipoidentidad()));
						}								
					}else{
						conyuge = new Conyuge(0, new Clientes(),"", "", "", "", "");						
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Error!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	} 
    
   

    public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
		
		/*if(idcuenta > 0){
			try{
				//Al recibir por parametro el idcuenta, consultamos
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
			}
			catch(Exception re){
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}*/
	}
	
	public void muestraProductosServicios(){
		try{
			CtasprodBO ctasprodBO = new CtasprodBO();
			lisCtasprod = ctasprodBO.lisCtasprod(idcuenta);
		}catch(Exception re){
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!","");
		}
	}
	
	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public Conyuge getConyuge() {
		return conyuge;
	}

	public void setConyuge(Conyuge conyuge) {
		this.conyuge = conyuge;
	}

	public Ctacliente getCtacliente() {
		return ctacliente;
	}

	public void setCtacliente(Ctacliente ctacliente) {
		this.ctacliente = ctacliente;
	}

	public Mensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(Mensajes mensajes) {
		this.mensajes = mensajes;
	}

	public boolean isMostrarMensaje() {
		return mostrarMensaje;
	}

	public void setMostrarMensaje(boolean mostrarMensaje) {
		this.mostrarMensaje = mostrarMensaje;
	}

	public List<Ctasprod> getLisCtasprod() {
		return lisCtasprod;
	}

	public void setLisCtasprod(List<Ctasprod> lisCtasprod) {
		this.lisCtasprod = lisCtasprod;
	}
	
	 public String getTipopersona() {
			return tipopersona;
		}

		public void setTipopersona(String tipopersona) {
			this.tipopersona = tipopersona;
		}

		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}

		public String getEstadoCuenta() {
			return estadoCuenta;
		}

		public void setEstadoCuenta(String estadoCuenta) {
			this.estadoCuenta = estadoCuenta;
		}
}
