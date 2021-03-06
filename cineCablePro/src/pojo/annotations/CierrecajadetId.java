package pojo.annotations;

// Generated 04/07/2015 13:17:26 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CierrecajadetId generated by hbm2java
 */
@Embeddable
public class CierrecajadetId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5535043933473894200L;
	private int idcierrecaja;
	private int idsecuencia;

	public CierrecajadetId() {
	}

	public CierrecajadetId(int idcierrecaja, int idsecuencia) {
		this.idcierrecaja = idcierrecaja;
		this.idsecuencia = idsecuencia;
	}

	@Column(name = "idcierrecaja", nullable = false)
	public int getIdcierrecaja() {
		return this.idcierrecaja;
	}

	public void setIdcierrecaja(int idcierrecaja) {
		this.idcierrecaja = idcierrecaja;
	}

	@Column(name = "idsecuencia", nullable = false)
	public int getIdsecuencia() {
		return this.idsecuencia;
	}

	public void setIdsecuencia(int idsecuencia) {
		this.idsecuencia = idsecuencia;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CierrecajadetId))
			return false;
		CierrecajadetId castOther = (CierrecajadetId) other;

		return (this.getIdcierrecaja() == castOther.getIdcierrecaja())
				&& (this.getIdsecuencia() == castOther.getIdsecuencia());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdcierrecaja();
		result = 37 * result + this.getIdsecuencia();
		return result;
	}

}
