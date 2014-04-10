/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controladores;

import bo.negocio.CtaclienteBO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pojo.annotations.*;
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

    public ClienteBean() {
    	ctacliente = new Ctacliente(0, new Empresa(), new Clientes());
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
			}
			catch(Exception re){
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}

	public Ctacliente getCtacliente() {
		return ctacliente;
	}

	public void setCtacliente(Ctacliente ctacliente) {
		this.ctacliente = ctacliente;
	}
}
