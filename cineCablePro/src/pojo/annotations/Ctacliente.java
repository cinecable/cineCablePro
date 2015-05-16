package pojo.annotations;

// Generated 09/02/2014 10:20:13 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Ctacliente generated by hbm2java
 */
@Entity
@Table(name = "ctacliente", schema = "public")
public class Ctacliente implements java.io.Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4567323109737024243L;
	private int idcuenta;
	private Empresa empresa;
	private Clientes clientes;
	private String nombre;
	private String ip;
	private int idestado;
	private int idcobrador;
	private int idvendedor;
	private int impfact;
	private int idusuario;
	private Date fecha;
	private Direccion direccion;
	private Debitobco debitobco;
	/*private Set<?> ctasprods = new HashSet<Object>(0);
	private Set<?> direccions = new HashSet<Object>(0);
	private Set<?> telefonos = new HashSet<Object>(0);
	private Set<?> pagosants = new HashSet<Object>(0);*/

	public Ctacliente() {
	}

	public Ctacliente(int idcuenta,  Empresa empresa,
			Clientes clientes) {
		this.idcuenta = idcuenta;
		this.empresa = empresa;
		this.clientes = clientes;
	}

	public Ctacliente(int idcuenta,  Empresa empresa,
			Clientes clientes, String nombre, String ip, int idestado/*, Set<?> ctasprods,
			Set<?> direccions, Set<?> telefonos, Set<?> pagosants*/) {
		this.idcuenta = idcuenta;
		this.empresa = empresa;
		this.clientes = clientes;
		this.nombre = nombre;
		this.ip = ip;
		this.idestado = idestado;
		/*this.ctasprods = ctasprods;
		this.direccions = direccions;
		this.telefonos = telefonos;
		this.pagosants = pagosants;*/
	}

	@Id
	@Column(name = "idcuenta", unique = true, nullable = false)
	public int getIdcuenta() {
		return this.idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idempresa", nullable = false)
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente", nullable = false)
	public Clientes getClientes() {
		return this.clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	@Column(name = "nombre", length = 25)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "ip", length = 100)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "idestado")
	public int getIdestado() {
		return idestado;
	}

	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}

	@Column(name = "idusuario", nullable = false)
	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", length = 29, nullable = false)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "idcobrador", nullable = false)
	public int getIdcobrador() {
		return idcobrador;
	}

	public void setIdcobrador(int idcobrador) {
		this.idcobrador = idcobrador;
	}
	
	@Column(name = "idvendedor", nullable = false)
	public int getIdvendedor() {
		return idvendedor;
	}

	public void setIdvendedor(int idvendedor) {
		this.idvendedor = idvendedor;
	}
	@Column(name = "impfact", nullable = false)
	public int getImpfact() {
		return impfact;
	}

	public void setImpfact(int impfact) {
		this.impfact = impfact;
	}

	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "ctacliente")
	public Set<?> getCtasprods() {
		return this.ctasprods;
	}

	public void setCtasprods(Set<?> ctasprods) {
		this.ctasprods = ctasprods;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ctacliente")
	public Set<?> getDireccions() {
		return this.direccions;
	}

	public void setDireccions(Set<?> direccions) {
		this.direccions = direccions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ctacliente")
	public Set<?> getTelefonos() {
		return this.telefonos;
	}

	public void setTelefonos(Set<?> telefonos) {
		this.telefonos = telefonos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ctacliente")
	public Set<?> getPagosants() {
		return this.pagosants;
	}

	public void setPagosants(Set<?> pagosants) {
		this.pagosants = pagosants;
	}*/
	
	@Transient
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public Ctacliente clonar() throws Exception {
		return (Ctacliente)clone();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iddebitobco", nullable = false)
	public Debitobco getDebitobco() {
		return debitobco;
	}

	public void setDebitobco(Debitobco debitobco) {
		this.debitobco = debitobco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresa == null) ? 0 : empresa.getIdempresa());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + idcobrador;
		result = prime * result + idcuenta;
		result = prime * result + idestado;
		result = prime * result + idusuario;
		result = prime * result + idvendedor;
		result = prime * result + impfact;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ctacliente other = (Ctacliente) obj;
		if (clientes == null) {
			if (other.clientes != null)
				return false;
		} else if (clientes.getIdcliente() != other.clientes.getIdcliente())
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (empresa.getIdempresa() != other.empresa.getIdempresa())
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idcobrador != other.idcobrador)
			return false;
		if (idcuenta != other.idcuenta)
			return false;
		if (idestado != other.idestado)
			return false;
		if (idusuario != other.idusuario)
			return false;
		if (idvendedor != other.idvendedor)
			return false;
		if (impfact != other.impfact)
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	
}
