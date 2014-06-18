/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controladores;

import bo.negocio.CtaclienteBO;
import bo.negocio.MensajesBO;

import java.io.Serializable;
import java.util.Calendar;

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
    private Mensajes mensajes;
    private boolean mostrarMensaje;

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
			}
			catch(Exception re){
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
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
}
