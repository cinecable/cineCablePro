package net.cinecable.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.dm.UsuarioMenuDM;
import net.cinecable.exception.EntidadNoEncontradaException;
import net.cinecable.service.IEmpresaServices;
import net.cinecable.service.IMenuServices;
import net.cinecable.service.ITipoMenuServices;
import net.cinecable.service.IUsuarioMenuServices;
import net.cinecable.service.IUsuarioServices;

import org.primefaces.model.TreeNode;

import pojo.annotations.Empresa;
import pojo.annotations.Menu;
import pojo.annotations.Tipomenu;
import pojo.annotations.Usuario;
import pojo.annotations.Usuariomenu;
import util.MessageUtil;

/**
 * @author javier
 *
 */
@ManagedBean(name="usuarioMenuController")
@ViewScoped
public class UsuarioMenuController {
	@EJB 
	private IUsuarioServices iUsuarioServices;
	@EJB
	private IMenuServices iMenuServices;
	@EJB
	private IUsuarioMenuServices iUsuarioMenuServices;
	@EJB
	private ITipoMenuServices iTipoMenuServices;
	@EJB
	private IEmpresaServices iEmpresaServices;
	
	@ManagedProperty(value="#{usuarioMenuDM}")
	private UsuarioMenuDM usuarioMenuDM;

	public UsuarioMenuDM getUsuarioMenuDM() {
		return usuarioMenuDM;
	}
	public void setUsuarioMenuDM(UsuarioMenuDM usuarioMenuDM) {
		this.usuarioMenuDM = usuarioMenuDM;
	}
	@PostConstruct
	public void initPantalla(){
		usuarioMenuDM.setUsuarios(new ArrayList<Usuario>());
		usuarioMenuDM.setUsuarioSeleccionado(new Usuario());
		usuarioMenuDM.setUsuarioMenu(new ArrayList<Usuariomenu>());
		usuarioMenuDM.setModulos(new ArrayList<Menu>());
		usuarioMenuDM.setModuloSeleccionado(new Menu());
		usuarioMenuDM.setSubMenus(new ArrayList<Menu>());
		usuarioMenuDM.setSubMenuSeleccionado(new Menu());
		usuarioMenuDM.setPorcesos(new ArrayList<Menu>());
		usuarioMenuDM.setProcesoSeleccionado(new Menu());
		usuarioMenuDM.settMenu(new ArrayList<Tipomenu>());
		usuarioMenuDM.settMenuSeleccionado(new Tipomenu());
		usuarioMenuDM.setEmpresas(new ArrayList<Empresa>());
		usuarioMenuDM.setEmpresaSeleccionada(new Empresa());
	}
	public List<Usuario> getUsuarios(){
		usuarioMenuDM.setUsuarios(iUsuarioServices.getUsuarios());
		return usuarioMenuDM.getUsuarios();
	}
	public List<Menu> getModulos(){
		return usuarioMenuDM.getModulos();
	}
	public List<Menu> getSubmenus(){
		SelectionModulo();
		return usuarioMenuDM.getSubMenus();
	}
	public List<Menu> getProcesos(){
		return usuarioMenuDM.getPorcesos();
	}
	public List<Tipomenu> getTipoMenu(){
		usuarioMenuDM.settMenu(iTipoMenuServices.getTipoMenu());
		return usuarioMenuDM.gettMenu();
	}
	
	public List<Empresa> getEmpresas(){
		usuarioMenuDM.setEmpresas(iEmpresaServices.getEmpresas());
		return usuarioMenuDM.getEmpresas();
	}
	
	public void SelectionTipoMenu(){
		usuarioMenuDM.setProcesoSeleccionado(new Menu());
		
		if (usuarioMenuDM.gettMenuSeleccionado().getIdtipomenu()!=0){
			usuarioMenuDM.setModulos(iMenuServices.getMenubyNivelTipoMenu(1,usuarioMenuDM.gettMenuSeleccionado().getIdtipomenu()));
			SelectionUsuario();
		}else{
			usuarioMenuDM.setModulos(new ArrayList<Menu>());
		}
	}
	
	public void SelectionModulo(){
		usuarioMenuDM.setSubMenuSeleccionado(new Menu());
		usuarioMenuDM.setProcesoSeleccionado(new Menu());
		if (usuarioMenuDM.getModuloSeleccionado().getIdmenu()!=0){
			usuarioMenuDM.setSubMenus(iMenuServices.getMenubyNivelIdPadre(usuarioMenuDM.getModuloSeleccionado().getIdmenu(), 2));
			usuarioMenuDM.setPorcesos(iMenuServices.getMenubyNivelIdPadre(usuarioMenuDM.getModuloSeleccionado().getIdmenu(), 3));
		}else{
			usuarioMenuDM.setSubMenus(new ArrayList<Menu>());
			usuarioMenuDM.setPorcesos(new ArrayList<Menu>());
		}
	}
	
	public void SelectionSubProceso(){
		usuarioMenuDM.setProcesoSeleccionado(new Menu());
		if (usuarioMenuDM.getSubMenuSeleccionado().getIdmenu()!=0){
			usuarioMenuDM.setPorcesos(iMenuServices.getMenubyNivelIdPadre(usuarioMenuDM.getSubMenuSeleccionado().getIdmenu(), 3));
		}else{
			if (usuarioMenuDM.getModuloSeleccionado().getIdmenu()!=0)
				usuarioMenuDM.setPorcesos(iMenuServices.getMenubyNivelIdPadre(usuarioMenuDM.getModuloSeleccionado().getIdmenu(), 3));
			else
				usuarioMenuDM.setPorcesos(new ArrayList<Menu>());	
		}
	}
	public void SelectionUsuario(){
		if (usuarioMenuDM.getUsuarioSeleccionado().getIdusuario()!=0 && usuarioMenuDM.gettMenuSeleccionado().getIdtipomenu()!=0){
			usuarioMenuDM.setUsuarioMenu(iUsuarioMenuServices.getUsuarioMenuByIdUsuarioTipoUsuario(usuarioMenuDM.getUsuarioSeleccionado().getIdusuario(),usuarioMenuDM.gettMenuSeleccionado().getIdtipomenu()));
		}else
			usuarioMenuDM.setUsuarioMenu(new ArrayList<Usuariomenu>());
	}
	public TreeNode getEstructuraUsuario() throws NumberFormatException, EntidadNoEncontradaException{
		return iUsuarioMenuServices.getEstructuraUsuario(usuarioMenuDM.getUsuarioMenu());
	}
	public void grabar(){
		try {
			iUsuarioMenuServices.ingUsuarioMenu(usuarioMenuDM);
			new MessageUtil().showInfoMessage("Usuario Menu", "Exito al insertar");
			initPantalla();
			
		} catch (Exception e) {
			new MessageUtil().showInfoMessage("Usuario Menu", "Error al insertar: " + e.getMessage());
		}
	}
	public void eliminar(){
		try {
			iUsuarioMenuServices.elimUsuarioMenu(usuarioMenuDM);
			new MessageUtil().showInfoMessage("Usuario Menu", "Exito al eliminar");
			initPantalla();
			
		} catch (Exception e) {
			new MessageUtil().showInfoMessage("Usuario Menu", "Error al eliminar: " + e.getMessage());
		}
	}
}
