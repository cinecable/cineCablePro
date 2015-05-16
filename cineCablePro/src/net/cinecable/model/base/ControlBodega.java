package net.cinecable.model.base;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import pojo.annotations.Persona;

@Entity
@Table(name = "tcontrolbodega")
public class ControlBodega extends EntityBase {

	private Long idControl;

	private Persona tecnico;

	private List<ReservacionesOrdenesBodega> reservas;

	private Date fechaControl;

	@Id
	@Column(name = "idcontrolbodega")
	@SequenceGenerator(name = "sec_orden_bodega", sequenceName = "seq_orden_bodega", allocationSize = 1)
	@GeneratedValue(generator = "sec_orden_bodega", strategy = GenerationType.SEQUENCE)
	public Long getIdControl() {
		return idControl;
	}

	public void setIdControl(Long idControl) {
		this.idControl = idControl;
	}

	@OneToMany(mappedBy = "controlBodega", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<ReservacionesOrdenesBodega> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservacionesOrdenesBodega> reservas) {
		this.reservas = reservas;
	}

	@ManyToOne
	@JoinColumn(name = "idpersona")
	public Persona getTecnico() {
		return tecnico;
	}

	public void setTecnico(Persona tecnico) {
		this.tecnico = tecnico;
	}

	@Column(name = "fechacontrol")
	@Temporal(TemporalType.DATE)
	public Date getFechaControl() {
		return fechaControl;
	}

	public void setFechaControl(Date fechaControl) {
		this.fechaControl = fechaControl;
	}

}
