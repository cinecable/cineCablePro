package pojo.annotations;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mensajes", schema = "public")
public class Mensajes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7027072044909205916L;
	private int idmensajes;
	private String descripcion;
	private String idcliente;
	private boolean estado;
	private Date fecharegistro;
	private String iplog;
	private Date fechamodificacion;
	private Date fechacaducidad;
	
	public Mensajes() {
	}
	
	public Mensajes(int idmensajes, String descripcion, String idcliente, boolean estado, Date fecharegistro, 
			String iplog, Date fechamodificacion, Date fechacaducidad) {
		this.idmensajes = idmensajes;
		this.descripcion = descripcion;
		this.idcliente = idcliente;
		this.estado = estado;
		this.fecharegistro = fecharegistro;
		this.iplog = iplog;
		this.fechamodificacion = fechamodificacion;
		this.fechacaducidad = fechacaducidad;
	}

	@Id
	@Column(name = "idmensajes", unique = true, nullable = false)
	public int getIdmensajes() {
		return idmensajes;
	}

	public void setIdmensajes(int idmensajes) {
		this.idmensajes = idmensajes;
	}

	@Column(name = "descripcion", nullable = false, length = 1000)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "idcliente", unique = true, nullable = false)
	public String getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	@Column(name = "estado")
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecharegistro", nullable = false, length = 29)
	public Date getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	@Column(name = "iplog", length = 20)
	public String getIplog() {
		return iplog;
	}

	public void setIplog(String iplog) {
		this.iplog = iplog;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechamodificacion", length = 29)
	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechacaducidad", length = 29)
	public Date getFechacaducidad() {
		return fechacaducidad;
	}

	public void setFechacaducidad(Date fechacaducidad) {
		this.fechacaducidad = fechacaducidad;
	}
	  
}
