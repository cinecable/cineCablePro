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
public class BrowserFacturaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7369746648821645094L;
	private int idsecuencia;
	
	public BrowserFacturaBean() {
		
	}

	public void imprimirFactura(){
		InputStream inputStream = null;
		
		try {
			String nombreReporte = "consultaFactura";
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("P_IDSECUENCIA", idsecuencia);
			
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
	
	public int getIdsecuencia() {
		return idsecuencia;
	}

	public void setIdsecuencia(int idsecuencia) {
		this.idsecuencia = idsecuencia;
		
		if(this.idsecuencia > 0){
			imprimirFactura();
		}
	}
	
}
