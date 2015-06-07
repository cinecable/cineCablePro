package pojo.annotations.custom;

public class ConsultaCliente implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669708171191334148L;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private String empresa;
	private String identificacion;
	private int idcuenta;
	private String idcliente;
	private String nrodebito;
	private String vineta;
	
	public ConsultaCliente(String nombre1, String nombre2, String apellido1, String apellido2, 
			String empresa, String identificacion, int idcuenta, String idcliente, String nrodebito,
			String vineta) {
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.empresa = empresa;
		this.identificacion = identificacion;
		this.idcuenta = idcuenta;
		this.idcliente = idcliente;
		this.nrodebito = nrodebito;
		this.vineta = vineta;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public String getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	public String getNrodebito() {
		return nrodebito;
	}

	public void setNrodebito(String nrodebito) {
		this.nrodebito = nrodebito;
	}

	public String getVineta() {
		return vineta;
	}

	public void setVineta(String vineta) {
		this.vineta = vineta;
	}
	
}
