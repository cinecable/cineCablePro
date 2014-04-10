package bean.controladores;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bo.negocio.CtaclienteBO;

import pojo.annotations.Ctacliente;

import util.MessageUtil;

@ManagedBean
@ViewScoped
public class ClientesNuevosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6075128135422100812L;
	
	@ManagedProperty(value="#{dbasCliBean}")
	private DbasCliBean dbasCliBean;
	
	@ManagedProperty(value="#{productosBean}") 
	private ProductosBean productosBean;
	
	@ManagedProperty(value="#{direccionBean}") 
	private DireccionBean direccionBean;
	
	@ManagedProperty(value="#{debitosBancariosBean}")
	private DebitosBancariosBean debitosBancariosBean;
	
	@ManagedProperty(value="#{telefonosBean}")
	private TelefonosBean telefonosBean;
	
	private String nombreCuenta;
	
	
	public ClientesNuevosBean() {
		nombreCuenta = "";
	}

	public ProductosBean getProductosBean() {
		return productosBean;
	}

	public void setProductosBean(ProductosBean productosBean) {
		this.productosBean = productosBean;
	}
	
	public DbasCliBean getDbasCliBean() {
		return dbasCliBean;
	}

	public void setDbasCliBean(DbasCliBean dbasCliBean) {
		this.dbasCliBean = dbasCliBean;
	}

	public DireccionBean getDireccionBean() {
		return direccionBean;
	}

	public void setDireccionBean(DireccionBean direccionBean) {
		this.direccionBean = direccionBean;
	}

	public DebitosBancariosBean getDebitosBancariosBean() {
		return debitosBancariosBean;
	}

	public void setDebitosBancariosBean(DebitosBancariosBean debitosBancariosBean) {
		this.debitosBancariosBean = debitosBancariosBean;
	}

	public TelefonosBean getTelefonosBean() {
		return telefonosBean;
	}

	public void setTelefonosBean(TelefonosBean telefonosBean) {
		this.telefonosBean = telefonosBean;
	}

	public void grabarNuevoCliente(){
		try{
			CtaclienteBO ctaclienteBO = new CtaclienteBO();
			
			//Asignamos data a ctacliente
			Ctacliente ctacliente = new Ctacliente();
			ctacliente.setClientes(dbasCliBean.getClientes());
			ctacliente.setNombre(this.nombreCuenta);
			
			ctaclienteBO.grabarCliente(ctacliente, dbasCliBean.getClientes(), productosBean.getLisProductosId());
			
			new MessageUtil().showInfoMessage("Listo!", "Grabado con exito");
			/*if(productosBean.getLisProductosId() != null && productosBean.getLisProductosId().size() > 0){
				String productos = "";
				for(ProductoId productoId : productosBean.getLisProductosId()){
					productos += productoId.getNombreProd() + "-";
				}
				new MessageUtil().showInfoMessage("Listo!", "Productos leidos de ProductosBean: -" + productos);
			}else{
				new MessageUtil().showWarnMessage("No!", "Agregue unos productos para ejemplo");
			}*/
		} catch(Exception re) {
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	
}
