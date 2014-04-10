package pojo.annotations.custom;

import java.io.Serializable;

public class ProductoId  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1832334524878886445L;
	private int idProducto;
	private String nombreProd;
	private int cantidad;
	
	
	
	public ProductoId() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductoId(int idProducto, String nombreProd, int cantidad) {

		this.idProducto = idProducto;
		this.nombreProd = nombreProd;
		this.cantidad = cantidad;
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
	
	

}
