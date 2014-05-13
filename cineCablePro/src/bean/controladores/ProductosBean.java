package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.negocio.CtasprodBO;
import bo.negocio.ProductoBO;

import pojo.annotations.Ctasprod;
import pojo.annotations.Producto;
import pojo.annotations.custom.ProductoId;
import util.FacesUtil;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class ProductosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9182986633529911766L;

	private List<Producto> lisProductos;
	private Producto productoSelected;
	private Producto adicionalSelected;
	private ProductoId productoIdSelected;
	private int cantidad;
	private List<ProductoId> lisProductosId;
	private List<ProductoId> lisProductosIdClon;
	private int idcuenta;

	public ProductosBean() {
		lisProductosId = new ArrayList<ProductoId>();
		lisProductosIdClon = new ArrayList<ProductoId>();
		productoSelected = new Producto();
		adicionalSelected = new Producto();
		productoIdSelected = new ProductoId();
		cantidad = 1;
		idcuenta = 0;
	}

	@PostConstruct
	public void initProductosBean() {
		FacesUtil facesUtil = new FacesUtil();
		idcuenta = Integer
				.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
						.getParametroUrl("idcuenta").toString() : "0");

		if (idcuenta > 0) {
			//consultarProductos();
		}
	}

	private void consultarProductos() {
		if (idcuenta > 0) {
			try {
				CtasprodBO ctasprodBO = new CtasprodBO();
				List<Ctasprod> lisCtasprod = ctasprodBO.lisCtasprod(idcuenta);

				for (Ctasprod ctasprod : lisCtasprod) {
					ProductoId productosID = new ProductoId(0, null, 0, 0);

					productosID.setIdprodcuenta(ctasprod.getIdprodcuentas());
					productosID.setIdProducto(ctasprod.getProducto()
							.getIdproducto());
					productosID.setNombreProd(ctasprod.getProducto()
							.getNombre());
					productosID.setCantidad(1);

					lisProductosId.add(productosID);
					lisProductosIdClon.add(productosID.clonar());
				}
			} catch (Exception e) {
				e.printStackTrace();
				new MessageUtil()
						.showFatalMessage("Error!",
								"Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}

	public List<Producto> CompletarProductos(String query) {

		lisProductos = new ArrayList<Producto>();

		ProductoBO productoBO = new ProductoBO();
		List<Producto> listProdcutoT;
		try {

			listProdcutoT = productoBO.ConsultarCPxQuery(query);

			if (listProdcutoT != null && listProdcutoT.size() > 0) {
				lisProductos.addAll(listProdcutoT);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lisProductos;
	}

	public List<Producto> buscarProductosPrincipal(String query) {
		List<Producto> lisProducto = new ArrayList<Producto>();

		lisProducto = buscarProductos(query, 1, null);

		return lisProducto;
	}

	public List<Producto> buscarProductosAdicional(String query) {
		List<Producto> lisProducto = new ArrayList<Producto>();

		if (productoSelected != null
				&& productoSelected.getTipojerarquia() != null
				&& productoSelected.getTipojerarquia().trim().length() > 0) {
			lisProducto = buscarProductos(query, 2, productoSelected
					.getTipojerarquia().split(","));
		} else {
			new MessageUtil().showWarnMessage(
					"Debe seleccionar el producto principal", null);
		}

		return lisProducto;
	}

	private List<Producto> buscarProductos(String query, int jerarquia,
			String[] filtroIn) {
		List<Producto> lisProducto = new ArrayList<Producto>();

		try {
			ProductoBO productoBO = new ProductoBO();
			int args[] = { 0 };
			lisProducto = productoBO.lisProductoByNombreJerarquia(query,
					jerarquia, filtroIn, 10, 0, args);
		} catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!",
					"Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}

		return lisProducto;
	}

	public void agregarProductos() {

		ProductoId productosID = new ProductoId(0, null, 0, 0);

		productosID.setIdProducto(productoSelected.getIdproducto());
		productosID.setNombreProd(productoSelected.getNombre());
		productosID.setCantidad(1);

		lisProductosId.add(productosID);
	}

	public void agregarAdicional() {

		ProductoId productosID = new ProductoId(0, null, 0, 0);

		productosID.setIdProducto(adicionalSelected.getIdproducto());
		productosID.setNombreProd(adicionalSelected.getNombre());
		productosID.setCantidad(cantidad);

		lisProductosId.add(productosID);

		// inicializar
		adicionalSelected = new Producto();
		cantidad = 1;
	}

	public void quitarProducto() {
		try {
			lisProductosId.remove(productoIdSelected);

			new MessageUtil().showInfoMessage("Listo!", "Producto excluida!");
		} catch (Exception re) {
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!",
					"Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}
	
	public List<Producto> getLisProductos() {
		return lisProductos;
	}

	public void setLisProductos(List<Producto> lisProductos) {
		this.lisProductos = lisProductos;
	}

	public Producto getProductoSelected() {
		return productoSelected;
	}

	public void setProductoSelected(Producto productoSelected) {
		this.productoSelected = productoSelected;
	}

	public List<ProductoId> getLisProductosId() {
		return lisProductosId;
	}

	public void setLisProductosId(List<ProductoId> lisProductosId) {
		this.lisProductosId = lisProductosId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ProductoId getProductoIdSelected() {
		return productoIdSelected;
	}

	public void setProductoIdSelected(ProductoId productoIdSelected) {
		this.productoIdSelected = productoIdSelected;
	}

	public Producto getAdicionalSelected() {
		return adicionalSelected;
	}

	public void setAdicionalSelected(Producto adicionalSelected) {
		this.adicionalSelected = adicionalSelected;
	}

	public List<ProductoId> getLisProductosIdClon() {
		return lisProductosIdClon;
	}

	public void setLisProductosIdClon(List<ProductoId> lisProductosIdClon) {
		this.lisProductosIdClon = lisProductosIdClon;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

}
