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
public class BrowserExcedenteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1758232598692642738L;
	private int idexcedente;
	
	public BrowserExcedenteBean() {
		
	}

	public void imprimirExcedente(){
		InputStream inputStream = null;
		
		try {
			String nombreReporte = "consultaExcedente";
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("P_IDEXCEDENTE", idexcedente);
			
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

	public int getIdexcedente() {
		return idexcedente;
	}

	public void setIdexcedente(int idexcedente) {
		this.idexcedente = idexcedente;
		
		if(this.idexcedente > 0){
			imprimirExcedente();
		}
	}
	
}
