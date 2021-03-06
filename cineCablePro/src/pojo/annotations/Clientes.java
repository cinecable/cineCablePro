package pojo.annotations;

// Generated 09/02/2014 10:20:13 AM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clientes generated by hbm2java
 */
@Entity
@Table(name = "clientes", schema = "public")
public class Clientes implements java.io.Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4519133304385704972L;
	private String idcliente;
	private Tipocliente tipocliente;
	private Usuario usuario;
	private Empresa empresa;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private String empresa_1;
	private Date fecha;
	
	private Date hora;
	private Date fechanacimiento;
	private String email;
	private String identificacion;
	private Estadocivil estadocivil;
	private int genero;
	

	private Tipoidentidad tipoidentidad;
	private int idtipopersona;
	private String ip;
	private List<Ctacliente> ctaclientes = new ArrayList<Ctacliente>();

	//private Set<?> conyuges = new HashSet<Object>(0);
	//private Set<?> ctaclientes = new HashSet<Object>(0);

	public Clientes() {
	}

	public Clientes(String idcliente, Tipocliente tipocliente, Usuario usuario,
			Empresa empresa, String nombre1, String apellido1, Date fecha) {
		this.idcliente = idcliente;
		this.tipocliente = tipocliente;
		this.usuario = usuario;
		this.empresa = empresa;
		this.nombre1 = nombre1;
		this.apellido1 = apellido1;
		this.fecha = fecha;
	}

	public Clientes(String idcliente, Tipocliente tipocliente, Usuario usuario,
			Empresa empresa, String nombre1, String nombre2, String apellido1,
			String apellido2, String empresa_1, Date fecha, Date fechanacimiento, String email, Estadocivil estadocivil, int genero/*,
			Set<?> conyuges, */,   int idtipopersona, Date hora, String identificacion,Tipoidentidad tipoidentidad) {
		this.idcliente = idcliente;
		this.tipocliente = tipocliente;
		this.usuario = usuario;
		this.empresa = empresa;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa_1 = empresa_1;
		this.fecha = fecha;
		this.fechanacimiento = fechanacimiento;
		this.email = email;
		this.estadocivil = estadocivil;
		this.genero = genero;
		this.tipoidentidad = tipoidentidad;
		this.idtipopersona = idtipopersona;
		this.hora = hora;
		this.identificacion = identificacion;
		/*this.conyuges = conyuges;*/
		//this.ctaclientes = ctaclientes;
	}

	@Id
	@Column(name = "idcliente", unique = true, nullable = false, length = 20)
	public String getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idtipocliente", nullable = false)
	public Tipocliente getTipocliente() {
		return this.tipocliente;
	}

	public void setTipocliente(Tipocliente tipocliente) {
		this.tipocliente = tipocliente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	@JoinColumn(name = "estadocivil", nullable = false)
	public Estadocivil getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(Estadocivil estadocivil) {
		this.estadocivil = estadocivil;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idtipoidentificacion", nullable = false)
	public Tipoidentidad getTipoidentidad() {
		return tipoidentidad;
	}

	public void setTipoidentidad(Tipoidentidad tipoidentidad) {
		this.tipoidentidad = tipoidentidad;
	}
	
	@Column(name = "nombre1", nullable = false, length = 25)
	public String getNombre1() {
		return this.nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	@Column(name = "nombre2", length = 25)
	public String getNombre2() {
		return this.nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	@Column(name = "apellido1", nullable = false, length = 25)
	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	@Column(name = "apellido2", length = 25)
	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	@Column(name = "empresa", length = 30)
	public String getEmpresa_1() {
		return this.empresa_1;
	}

	public void setEmpresa_1(String empresa_1) {
		this.empresa_1 = empresa_1;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", length = 29, nullable = false)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hora", length = 29, nullable = false)
	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechanacimiento", length = 29)
	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	@Column(name = "genero")
	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	
	@Column(name = "idtipopersona")
	public int getIdtipopersona() {
		return idtipopersona;
	}

	public void setIdtipopersona(int idtipopersona) {
		this.idtipopersona = idtipopersona;
	}

	@Column(name = "ip", length = 20)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name = "identificacion", length = 30)
	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientes", cascade = CascadeType.ALL)
	public List<Ctacliente> getCtaclientes() {
		return ctaclientes;
	}

	public void setCtaclientes(List<Ctacliente> ctaclientes) {
		this.ctaclientes = ctaclientes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellido1 == null) ? 0 : apellido1.hashCode());
		result = prime * result
				+ ((apellido2 == null) ? 0 : apellido2.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((empresa_1 == null) ? 0 : empresa_1.hashCode());
		result = prime * result + estadocivil.getIdestadocivil();
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result
				+ ((fechanacimiento == null) ? 0 : fechanacimiento.hashCode());
		result = prime * result + genero;
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result
				+ ((idcliente == null) ? 0 : idcliente.hashCode());
		result = prime * result
				+ ((identificacion == null) ? 0 : identificacion.hashCode());
		result = prime * result + tipoidentidad.getIdtidentidad();
		result = prime * result + idtipopersona;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((nombre1 == null) ? 0 : nombre1.hashCode());
		result = prime * result + ((nombre2 == null) ? 0 : nombre2.hashCode());
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
		Clientes other = (Clientes) obj;
		if (apellido1 == null) {
			if (other.apellido1 != null)
				return false;
		} else if (!apellido1.equals(other.apellido1))
			return false;
		if (apellido2 == null) {
			if (other.apellido2 != null)
				return false;
		} else if (!apellido2.equals(other.apellido2))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empresa_1 == null) {
			if (other.empresa_1 != null)
				return false;
		} else if (!empresa_1.equals(other.empresa_1))
			return false;
		if (estadocivil != other.estadocivil)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (fechanacimiento == null) {
			if (other.fechanacimiento != null)
				return false;
		} else if (!fechanacimiento.equals(other.fechanacimiento))
			return false;
		if (genero != other.genero)
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (idcliente == null) {
			if (other.idcliente != null)
				return false;
		} else if (!idcliente.equals(other.idcliente))
			return false;
		if (identificacion == null) {
			if (other.identificacion != null)
				return false;
		} else if (!identificacion.equals(other.identificacion))
			return false;
		if (tipoidentidad == null) {
			if (other.tipoidentidad != null)
				return false;
		} else if (tipoidentidad.getIdtidentidad() != other.tipoidentidad.getIdtidentidad())
			return false;
		if (idtipopersona != other.idtipopersona)
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (nombre1 == null) {
			if (other.nombre1 != null)
				return false;
		} else if (!nombre1.equals(other.nombre1))
			return false;
		if (nombre2 == null) {
			if (other.nombre2 != null)
				return false;
		} else if (!nombre2.equals(other.nombre2))
			return false;
		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public Clientes clonar() throws Exception {
		return (Clientes)this.clone();
	}



}
