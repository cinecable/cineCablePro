package bean.controladores;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.negocio.DireccionBO;


import pojo.annotations.Direccion;
import util.MessageUtil;


@ManagedBean
@ViewScoped
public class DirConsultasBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7335851525294576336L;
	
	private Direccion direccion;
	private int idcuenta;
	private String nombrePais;
	
	
	public DirConsultasBean() {
		direccion = new Direccion();		
	}
	
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public int getIdcuenta() {
		return idcuenta;
	}
	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
		if(idcuenta > 0){
			try{
				//Al recibir por parametro el idcuenta, consultamos
				DireccionBO direccionBO = new DireccionBO();
				direccion = direccionBO.getDirByCta(idcuenta);
				
				// PaisBO paisBO = new PaisBO();
				// Pais pais = new Pais();
				// pais = paisBO.paisxDir(direccion.);
				//nombrePais
			}
			catch(Exception re){
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
		
	}
	
	



}
