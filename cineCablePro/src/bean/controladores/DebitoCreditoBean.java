package bean.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.FacesUtil;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class DebitoCreditoBean implements Serializable {

	private static final long serialVersionUID = -2492131965686310734L;
	private int idcuenta;

	public DebitoCreditoBean() {
	}
	
	@PostConstruct
	public void initDebitoCreditoBean() {
		try{
			FacesUtil facesUtil = new FacesUtil();
			idcuenta = Integer
					.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
							.getParametroUrl("idcuenta").toString() : "0");
			
			if(idcuenta > 0){
			}
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showErrorMessage("Ha ocurrido un error comunicar al webmaster", "");
		}
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}
}
