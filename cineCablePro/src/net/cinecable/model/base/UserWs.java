package net.cinecable.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pojo.annotations.Controlador;

@Entity
@Table(name = "usuariows")
public class UserWs extends EntityBase {

	private String nombreUser, passWd;
	private Long idUser;
	private Controlador controlador;

	@Column(name = "nombreusuario")
	public String getNombreUser() {
		return nombreUser;
	}

	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}

	@Column(name = "password")
	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	@Id
	@Column(name = "iduser")
	@SequenceGenerator(sequenceName = "sequserws", name = "secuserws", allocationSize = 1)
	@GeneratedValue(generator = "secuserws", strategy = GenerationType.SEQUENCE)
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	@OneToOne
	@JoinColumn(name = "idcontrolador", nullable = true)
	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

}
