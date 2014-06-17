package bean.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import util.FacesUtil;
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
	
	private int idcuenta;
	
	public AdicionarProductosBean() {
		idcuenta = 0;
	}
	
	@PostConstruct
	public void initAdicionarProductosBean() {
		FacesUtil facesUtil = new FacesUtil();
		idcuenta = Integer
				.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
						.getParametroUrl("idcuenta").toString() : "0");

		if (idcuenta > 0) {
			//consultarProductos();
		}
	}

	public void grabar(){
		if(validacionProductoOk()){
			try{
				CtasprodBO ctasprodBO = new CtasprodBO();
				boolean ok = ctasprodBO.grabarProductos(productosBean.getIdcuenta(), productosBean.getLisProductosId(), productosBean.getLisProductosIdClon());
				
				if(ok){
					FacesUtil facesUtil = new FacesUtil();
					try {
						facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta="+idcuenta);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					new MessageUtil().showInfoMessage("No existen cambios que guardar", "");
				}
			}catch(Exception e){
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
			}
		}
	}

	private boolean validacionProductoOk(){
		boolean ok = false;
		
		if(productosBean.getLisProductosId() != null && productosBean.getLisProductosId().size() > 0){
			ok = true;
		}else{
			new MessageUtil().showWarnMessage("Debe seleccionar al menos un producto en seccion Productos Cliente", "");
		}
		
		return ok;
	}
	public ProductosBean getProductosBean() {
		return productosBean;
	}

	public void setProductosBean(ProductosBean productosBean) {
		this.productosBean = productosBean;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}
}
