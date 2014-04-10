package bean.controladores;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.FileUtil;
import util.MessageUtil;
import util.Utilities;

@ManagedBean
@ViewScoped
public class BrowserCreditoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 761124678656901341L;
	private String idfactura;
	
	public BrowserCreditoBean() {
		
	}

	public void imprimirCredito(){
		InputStream inputStream = null;
		
		try {
			String nombreReporte = "consultaCredito";
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("P_IDFACTURA", idfactura);
			
			FileUtil fileUtil = new FileUtil();
			inputStream = fileUtil.getLogoEmpresaAsStream();
			if(inputStream != null){
				parametros.put("P_LOGO", inputStream);
			}
			
			new Utilities().imprimirJasperPdf(nombreReporte, parametros);
		}catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}finally {
			if(inputStream != null){
				try{
					inputStream.close();
				}catch(Exception e){
					
				}
			}
		}
	}

	public String getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(String idfactura) {
		this.idfactura = idfactura;
		
		if(this.idfactura != null){
			imprimirCredito();
		}
	}
	
}
