package bean.controladores;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.MessageUtil;
import util.Utilities;

@ManagedBean
@ViewScoped
public class BrowserFacturaCajaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7249394335232020769L;
	private String idfactura;
	
	public BrowserFacturaCajaBean() {
		
	}

	public void imprimirFactura(){
		try {
			String nombreReporte = "factura";
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("P_IDFACTURA", idfactura);
				
			new Utilities().imprimirJasperPdf(nombreReporte, parametros);
		}catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}

	public String getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(String idfactura) {
		this.idfactura = idfactura;
		
		if(this.idfactura != null && this.idfactura.trim().length() > 0){
			imprimirFactura();
		}
	}
	
	
}
