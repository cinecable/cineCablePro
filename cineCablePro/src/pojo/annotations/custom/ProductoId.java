package pojo.annotations.custom;

import java.io.Serializable;

public class ProductoId  implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1832334524878886445L;
	private int idprodcuenta;
	private int idProducto;
	private String nombreProd;
	private int cantidad;
	private int idproductoprincipal;
	
	
	public ProductoId() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductoId(int idProducto, String nombreProd, int cantidad, int idprodcuenta) {

		this.idProducto = idProducto;
		this.nombreProd = nombreProd;
		this.cantidad = cantidad;
		this.idprodcuenta = idprodcuenta;
	}

	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombreProd() {
		return nombreProd;
	}
	public void setNombreProd(String nombreProd) {
		this.nombreProd = nombreProd;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getIdprodcuenta() {
		return idprodcuenta;
	}

	public void setIdprodcuenta(int idprodcuenta) {
		this.idprodcuenta = idprodcuenta;
	}

	public int getIdproductoprincipal() {
		return idproductoprincipal;
	}

	public void setIdproductoprincipal(int idproductoprincipal) {
		this.idproductoprincipal = idproductoprincipal;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public ProductoId clonar() throws Exception {
		ProductoId productoId = (ProductoId) this.clone();
		
		return productoId;
	}

}
