package net.cinecable.model.base;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import net.cinecable.enums.Estados;
import bean.controladores.UsuarioBean;
import pojo.annotations.Empresa;
import pojo.annotations.Usuario;
import util.FacesUtil;

@MappedSuperclass
public class EntityBase {

	protected int estado;
	protected String ip;
	protected Usuario usuario;
	protected Empresa empresa;

	private FacesUtil faces;
	private UsuarioBean usuarioBean;
	{
		faces = new FacesUtil();
		if (faces.getSessionBean("usuarioBean") != null) {
			usuarioBean = (UsuarioBean) faces.getSessionBean("usuarioBean");

			if (estado == 0)
				estado = Estados.ACTIVO.getDescription();
			if (ip == null)
				ip = usuarioBean.getIp();
			if (usuario == null) {
				usuario = usuarioBean.getUsuario();
				empresa = this.usuario.getEmpresa();
			}
		}

	}

	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	@Column(name = "idestado")
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@ManyToOne
	@JoinColumn(name = "idusuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne
	@JoinColumn(name = "idempresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
