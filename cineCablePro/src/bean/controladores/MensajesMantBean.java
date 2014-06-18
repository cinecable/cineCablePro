package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.negocio.ClienteBO;
import bo.negocio.CtaclienteBO;
import bo.negocio.MensajesBO;

import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Mensajes;
import util.FacesUtil;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class MensajesMantBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4538532599965233883L;
	private Clientes clientesSelected;
	private Mensajes mensajes;
	
	private int idcuenta;
	private Ctacliente ctacliente;
	
	private boolean modificable;
	
	public MensajesMantBean() {
		mensajes = new Mensajes();
	}

	@PostConstruct
	public void initDireccionMantBean() {
		try{
			FacesUtil facesUtil = new FacesUtil();
			idcuenta = Integer
					.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
							.getParametroUrl("idcuenta").toString() : "0");
			
			if(idcuenta > 0){
				consultarMensaje();
			}
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showErrorMessage("Ha ocurrido un error comunicar al webmaster", "");
		}
	}
	
	public List<Clientes> buscarClientes(String query) {
		List<Clientes> lisClientes = new ArrayList<Clientes>();
		
		ClienteBO clienteBO = new ClienteBO();
		int args[] = {0};
		String[] nombres = null;
		if(query != null && query.trim().length() > 0){
			nombres = query.split(" ");
		}
		lisClientes = clienteBO.lisClientesByPageNombres(nombres, 10, 0, args);
		
		if(lisClientes == null){
			lisClientes = new ArrayList<Clientes>();
		}
		
		return lisClientes;
	}

	public void consultarMensaje(){
		try{
			MensajesBO mensajesBO = new MensajesBO();
			
			CtaclienteBO ctaclienteBO = new CtaclienteBO();
			ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
			
			mensajes = mensajesBO.getMensajesByIdcliente(ctacliente.getClientes().getIdcliente());
			
			if(mensajes != null){
				modificable = true;
				clientesSelected = ctacliente.getClientes();
			}else{
				modificable = false;
				new MessageUtil().showWarnMessage("Cliente no posee Mensajes ingresados!", "");
				mensajes = new Mensajes();
			}
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showErrorMessage("Ah ocurrido un error comunique al webmaster", "");
		}
	}
	
	public void grabar(){
		if(validarOk()){
			try{
				MensajesBO mensajesBO = new MensajesBO();
				boolean ok = false;
				
				if(mensajes.getIdcliente() != null && mensajes.getIdcliente().trim().length() > 0){
					ok = mensajesBO.modificarMensaje(mensajes);
				}else{
					//mensajes.setIdcliente(ctacliente.getClientes().getIdcliente());
					//ok = mensajesBO.ingresarMensaje(mensajes);
				}
				
				if(ok){
					FacesUtil facesUtil = new FacesUtil();
					try {
						facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta="+idcuenta);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					new MessageUtil().showWarnMessage("No hay datos que guardar", "");
				}
			}catch(Exception e){
				e.printStackTrace();
				new MessageUtil().showErrorMessage("Ah ocurrido un error comunique al webmaster", "");
			}
		}
	}
	
	private boolean validarOk(){
		boolean ok = false;
		
		if(mensajes.getDescripcion() != null && mensajes.getDescripcion().trim().length() > 0){
			if(mensajes.getFechacaducidad() != null){
				ok = true;
			}else{
				new MessageUtil().showWarnMessage("Ingrese la fecha de caducidad", "");
			}
		}else{
			new MessageUtil().showWarnMessage("Ingrese el mensaje", "");
		}
		
		return ok;
	}
	
	public Clientes getClientesSelected() {
		return clientesSelected;
	}

	public void setClientesSelected(Clientes clientesSelected) {
		this.clientesSelected = clientesSelected;
	}

	public Mensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(Mensajes mensajes) {
		this.mensajes = mensajes;
	}

	public Ctacliente getCtacliente() {
		return ctacliente;
	}

	public void setCtacliente(Ctacliente ctacliente) {
		this.ctacliente = ctacliente;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public boolean isModificable() {
		return modificable;
	}

	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}
}
