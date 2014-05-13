package bean.controladores;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import util.MessageUtil;
import bo.negocio.CtasprodBO;

@ManagedBean
@ViewScoped
public class AdicionarProductosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2966559444716178868L;
	
	@ManagedProperty(value="#{productosBean}") 
	private ProductosBean productosBean;
	
	public AdicionarProductosBean() {
	}

	public void grabar(){
		
		try{
			CtasprodBO ctasprodBO = new CtasprodBO();
			boolean ok = ctasprodBO.grabarProductos(productosBean.getIdcuenta(), productosBean.getLisProductosId(), productosBean.getLisProductosIdClon());
			
			if(ok){
				new MessageUtil().showInfoMessage("Listo!", "Datos grabados con Exito!");
			}else{
				new MessageUtil().showInfoMessage("Aviso", "No existen cambios que guardar");
			}
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", null);
		}
		
	}

	public ProductosBean getProductosBean() {
		return productosBean;
	}

	public void setProductosBean(ProductosBean productosBean) {
		this.productosBean = productosBean;
	}
}
