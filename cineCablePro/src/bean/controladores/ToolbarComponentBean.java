package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Ctacliente;

import bo.negocio.CtaclienteBO;

import util.FacesUtil;
import util.FileUtil;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class ToolbarComponentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8935385963286962255L;
	private String nombre1;
    private String apellido1;
    private String numeroIdentificacion;
    private String idcliente;
    private int idcuenta;
    
    public ToolbarComponentBean() {
	}
    
    public void consultar() {
    	if( (nombre1 != null && nombre1.trim().length() > 0) || (apellido1 != null && apellido1.trim().length() > 0) || (numeroIdentificacion != null && numeroIdentificacion.trim().length() > 0) || (idcliente != null && idcliente.trim().length() > 0) || idcuenta > 0) {
			try {
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				List<Ctacliente> lisCtacliente = new ArrayList<Ctacliente>();
				
				lisCtacliente = ctaclienteBO.lisCtaclienteToolBar(nombre1, apellido1, numeroIdentificacion, idcliente, idcuenta);
				
				if(lisCtacliente != null && lisCtacliente.size() > 0){
					FacesUtil facesUtil = new FacesUtil();
					
					if(lisCtacliente.size() > 1){
						FileUtil fileUtil = new FileUtil();
						String home = fileUtil.getPropertyValue("home");
						facesUtil.redirect(home);
					}else{
						facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta="+lisCtacliente.get(0).getIdcuenta());
					}
					
				}else{
					new MessageUtil().showInfoMessage("No existen datos con los parámetros especificados", "");
				}
			} catch (Exception e) {
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error con la consulta. Vuelva a intentarlo, si continúa el problema comuníquese con sistemas", "");
			}
    	}
    }

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
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
	}
    
    
}
