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
@Table(name = "tbreservacionesordenesbodega")
public class ReservacionesOrdenesBodega extends EntityBase {

	private Long idReservaOrden;
	private Ordenes orden;
	private Persona tecnico;
	private Date fechaIngreso;
	private List<ReservacionesBodegaMateriales> reservaMateriales;
	private ControlBodega controlBodega;

	@Id
	@Column(name = "idreservaorden")
	@SequenceGenerator(name = "sec_reserva_orden_bodega", sequenceName = "seq_reserva_orden_bodega", allocationSize = 1)
	@GeneratedValue(generator = "sec_reserva_orden_bodega", strategy = GenerationType.SEQUENCE)
	public Long getIdReservaOrden() {
		return idReservaOrden;
	}

	public void setIdReservaOrden(Long idReservaOrden) {
		this.idReservaOrden = idReservaOrden;
	}

	@ManyToOne
	@JoinColumn(name = "idordenes")
	public Ordenes getOrden() {
		return orden;
	}

	public void setOrden(Ordenes orden) {
		this.orden = orden;
	}

	@ManyToOne
	@JoinColumn(name = "idpersona")
	public Persona getTecnico() {
		return tecnico;
	}

	public void setTecnico(Persona tecnico) {
		this.tecnico = tecnico;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaingreso")
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@OneToMany(mappedBy = "reservaOrdenBodega", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<ReservacionesBodegaMateriales> getReservaMateriales() {
		for (int i = 0; i < reservaMateriales.size(); i++) {
			if (reservaMateriales.get(i).getIdtemp() == null) {
				reservaMateriales.get(i).setIdtemp(reservaMateriales.get(i).getIdReservacionBodegaMaterial() + "");
			}
		}
		return reservaMateriales;
	}

	public void setReservaMateriales(List<ReservacionesBodegaMateriales> reservaMateriales) {
		this.reservaMateriales = reservaMateriales;
	}

	@ManyToOne
	@JoinColumn(name = "idControl")
	public ControlBodega getControlBodega() {
		return controlBodega;
	}

	public void setControlBodega(ControlBodega controlBodega) {
		this.controlBodega = controlBodega;
	}

}
