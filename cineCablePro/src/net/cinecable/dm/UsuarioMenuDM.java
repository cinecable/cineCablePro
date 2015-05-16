package net.cinecable.dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import pojo.annotations.Empresa;
import pojo.annotations.Menu;
import pojo.annotations.Tipomenu;
import pojo.annotations.Usuario;
import pojo.annotations.Usuariomenu;

@ManagedBean(name="usuarioMenuDM")
@SessionScoped
public class UsuarioMenuDM implements Serializable{
	private List<Usuario> Usuarios;
	private Usuario usuarioSeleccionado= new Usuario();
	private List<Menu> Modulos;
	private Menu moduloSeleccionado= new Menu();
	private List<Menu> SubMenus;
	private Menu SubMenuSeleccionado= new Menu();
	private List<Menu> Porcesos;
	private Menu procesoSeleccionado= new Menu();
	private List<Usuariomenu> usuarioMenu=new ArrayList<Usuariomenu>();
	private Tipomenu tMenuSeleccionado= new Tipomenu();
	private List<Tipomenu> tMenu= new ArrayList<Tipomenu>();
	private List<Empresa> empresas= new ArrayList<Empresa>();
	private Empresa empresaSeleccionada= new Empresa();
	
	
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public Tipomenu gettMenuSeleccionado() {
		return tMenuSeleccionado;
	}

	public void settMenuSeleccionado(Tipomenu tMenuSeleccionado) {
		this.tMenuSeleccionado = tMenuSeleccionado;
	}

	public List<Tipomenu> gettMenu() {
		return tMenu;
	}

	public void settMenu(List<Tipomenu> tMenu) {
		this.tMenu = tMenu;
	}

	public List<Usuariomenu> getUsuarioMenu() {
		return usuarioMenu;
	}

	public void setUsuarioMenu(List<Usuariomenu> usuarioMenu) {
		this.usuarioMenu = usuarioMenu;
	}

	public List<Menu> getPorcesos() {
		return Porcesos;
	}

	public void setPorcesos(List<Menu> porcesos) {
		Porcesos = porcesos;
	}

	public Menu getProcesoSeleccionado() {
		return procesoSeleccionado;
	}

	public void setProcesoSeleccionado(Menu procesoSeleccionado) {
		this.procesoSeleccionado = procesoSeleccionado;
	}

	public List<Menu> getSubMenus() {
		return SubMenus;
	}

	public void setSubMenus(List<Menu> subMenus) {
		SubMenus = subMenus;
	}

	public Menu getSubMenuSeleccionado() {
		return SubMenuSeleccionado;
	}

	public void setSubMenuSeleccionado(Menu subMenuSeleccionado) {
		SubMenuSeleccionado = subMenuSeleccionado;
	}

	public Menu getModuloSeleccionado() {
		return moduloSeleccionado;
	}

	public void setModuloSeleccionado(Menu moduloSeleccionado) {
		this.moduloSeleccionado = moduloSeleccionado;
	}

	public List<Menu> getModulos() {
		return Modulos;
	}

	public void setModulos(List<Menu> modulos) {
		Modulos = modulos;
	}

	public List<Usuario> getUsuarios() {
		return Usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		Usuarios = usuarios;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
}
