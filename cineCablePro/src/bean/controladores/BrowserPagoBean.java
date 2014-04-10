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
public class BrowserPagoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6001193691542531756L;
	private int idpago;
	
	public BrowserPagoBean() {
		
	}

	public void imprimirPago(){
		InputStream inputStream = null;
		
		try {
			String nombreReporte = "consultaPago";
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("P_IDPAGO", idpago);
			
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
	
	public int getIdpago() {
		return idpago;
	}

	public void setIdpago(int idpago) {
		this.idpago = idpago;
		
		if(this.idpago > 0){
			imprimirPago();
		}
	}
	
}
