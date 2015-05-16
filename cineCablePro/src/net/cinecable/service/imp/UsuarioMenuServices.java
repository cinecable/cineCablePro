package net.cinecable.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.IUsuarioMenuDao;
import net.cinecable.dm.UsuarioMenuDM;
import net.cinecable.exception.EntidadNoEncontradaException;
import net.cinecable.service.IMenuServices;
import net.cinecable.service.IUsuarioMenuServices;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import pojo.annotations.Menu;
import pojo.annotations.Usuariomenu;
import util.MessageUtil;
@Stateless
public class UsuarioMenuServices implements IUsuarioMenuServices{
	@EJB
	private IMenuServices iMenuServices;
	@EJB
	private IUsuarioMenuDao iUsuarioMenuDao;
	

	@Override
	public List<Usuariomenu> getUsuarioMenuByIdUsuario(Integer idUsua) {
		List<Usuariomenu> listaUsuMenu= new ArrayList<Usuariomenu>();
		if (idUsua!=0){
			listaUsuMenu=iUsuarioMenuDao.getUsuarioMenuByIdUsuario(idUsua);
		}
		return listaUsuMenu;
	}

	@Override
	public List<Usuariomenu> getUsuarioMenuByIdUsuarioTipoUsuario(Integer idUsua,Integer tipoMenu) {
		List<Usuariomenu> listaUsuMenu= new ArrayList<Usuariomenu>();
		if (idUsua!=0){
			listaUsuMenu=iUsuarioMenuDao.getUsuarioMenuByIdUsuarioTipoUsuario(idUsua,tipoMenu);
		}
		return listaUsuMenu;
	}
	
	@Override
	public TreeNode getEstructuraUsuario(List<Usuariomenu> listaUsuaMenu) throws NumberFormatException, EntidadNoEncontradaException {
		TreeNode root= new DefaultTreeNode("Root", null);
		if (!listaUsuaMenu.isEmpty()){
			for (Usuariomenu usuaMenu : listaUsuaMenu){

				if (usuaMenu.getStrruta()==null || usuaMenu.getStrproceso()==null) continue;
				String [] modulos=usuaMenu.getStrruta().split("/");
				if (modulos.length==0) continue;
				
				Menu menu= iMenuServices.getMenuById(Integer.parseInt(modulos[0]));
				TreeNode TreeModulo= new DefaultTreeNode(menu.getNombre(), root);
				
				String [] procesos=usuaMenu.getStrproceso().split("/");
				if (procesos.length==0) continue;
				
				
				
				int cont=0;
				for (String modulo:modulos){
					if (cont!=0)
						SubMenus(TreeModulo,modulo,procesos[cont]);
					cont++;
				}
				
				
				String [] proc=procesos[0].split("-");
				if (proc.length==0) continue;
				if (menu.getIdmenu()==Integer.parseInt(proc[0])) continue;
				for (String pro:proc){
					Menu menuProceso= iMenuServices.getMenuById(Integer.parseInt(pro));
					TreeNode TreeProceso= new DefaultTreeNode(menuProceso.getNombre(), TreeModulo);
				}
			
				
			}
			
		}
		return root;
	}

	private void SubMenus(TreeNode treeModulo, String modulo, String procesos) throws NumberFormatException, EntidadNoEncontradaException {
		Menu menu= iMenuServices.getMenuById(Integer.parseInt(modulo));
		TreeNode SubMenu= new DefaultTreeNode(menu.getNombre(), treeModulo);
		String [] proc=procesos.split("-");
		for (String pro:proc){
			Menu menuProceso= iMenuServices.getMenuById(Integer.parseInt(pro));
			TreeNode TreeProceso= new DefaultTreeNode(menuProceso.getNombre(), SubMenu);
		}
	}

	@Override
	public void ingUsuarioMenu(UsuarioMenuDM usuarioMenuDM) throws Exception{
		if (usuarioMenuDM.getUsuarioSeleccionado().getIdusuario()==0){
			new MessageUtil().showInfoMessage("Usuario Menu", "Seleccione un usuario");
			throw new Exception("Faltan datos");
		}
		
		if (usuarioMenuDM.gettMenuSeleccionado().getIdtipomenu()==0){
			new MessageUtil().showInfoMessage("Usuario Menu", "Seleccione un tipo de usuario");
			throw new Exception("Faltan datos");
		}
		if (usuarioMenuDM.getModuloSeleccionado().getIdmenu()==0){
			new MessageUtil().showInfoMessage("Usuario Menu", "Seleccione un Modulo");
			throw new Exception("Faltan datos");
		}
		
		if (usuarioMenuDM.getProcesoSeleccionado().getIdmenu()==0){
			new MessageUtil().showInfoMessage("Usuario Menu", "Seleccione un Proceso");
			throw new Exception("Faltan datos");
		}
		if (usuarioMenuDM.getEmpresaSeleccionada().getIdempresa()==0){
			new MessageUtil().showInfoMessage("Usuario Menu", "Seleccione una empresa");
			throw new Exception("Faltan datos");
		}
		Usuariomenu userMenu= new  Usuariomenu();
		userMenu.setIdempresa(usuarioMenuDM.getEmpresaSeleccionada().getIdempresa());
		userMenu.setIdestado(1);
		userMenu.setIdmenu(usuarioMenuDM.gettMenuSeleccionado().getIdtipomenu());
		userMenu.setIdusuario(usuarioMenuDM.getUsuarioSeleccionado().getIdusuario());
		Usuariomenu usarioMenuEncontrado=null;
		//For para saber si ya existe el modulo ingresado
		for (Usuariomenu menu:usuarioMenuDM.getUsuarioMenu()){
			if (menu.getStrruta()==null) continue;
			String modulo=menu.getStrruta().split("/")[0];
			if (Integer.parseInt(modulo)==usuarioMenuDM.getModuloSeleccionado().getIdmenu() && menu.getIdmenu()==userMenu.getIdmenu()){
				usarioMenuEncontrado=menu;
				break;
			}
		}
		//Insertar
		if (usarioMenuEncontrado==null){
			userMenu.setIdusuariomenu(0);
			
			if (usuarioMenuDM.getSubMenuSeleccionado().getIdmenu()!=0){
				userMenu.setStrproceso(userMenu.getStrproceso() + "/"+usuarioMenuDM.getSubMenuSeleccionado().getIdmenu());
				userMenu.setStrruta(usuarioMenuDM.getModuloSeleccionado().getIdmenu() + "/" + usuarioMenuDM.getProcesoSeleccionado().getIdmenu());
			}else{
				userMenu.setStrproceso(""+usuarioMenuDM.getProcesoSeleccionado().getIdmenu());
				userMenu.setStrruta(""+usuarioMenuDM.getModuloSeleccionado().getIdmenu());
			}
			iUsuarioMenuDao.crear(userMenu);
			
		}else{
			userMenu.setIdusuariomenu(usarioMenuEncontrado.getIdusuariomenu());
			userMenu.setStrruta(usarioMenuEncontrado.getStrruta());
			userMenu.setStrproceso(usarioMenuEncontrado.getStrproceso());
			if (usuarioMenuDM.getSubMenuSeleccionado().getIdmenu()!=0){
				int cont=0;
				boolean subMenuEncontrado=false;
				for (String submenus:userMenu.getStrruta().split("/")){
					if (Integer.parseInt(submenus)==usuarioMenuDM.getSubMenuSeleccionado().getIdmenu()){
						subMenuEncontrado=true;
						break;
					}
					cont++;
				}
				if (subMenuEncontrado){
					boolean procesoEncontrado=false;
					String [] proceso=userMenu.getStrproceso().split("/")[cont].split("-");
					for (String proc:proceso){
						if (Integer.parseInt(proc)==usuarioMenuDM.getProcesoSeleccionado().getIdmenu()){
							procesoEncontrado=true;
						}
					}
					if (procesoEncontrado){
						new MessageUtil().showInfoMessage("Usuario Menu", "Este proceso ya se encuentra insertado");
						throw new Exception("Faltan datos"); 
					}else{
						String[] procesos=userMenu.getStrproceso().split("/");
						procesos[cont]+= "-" + usuarioMenuDM.getProcesoSeleccionado().getIdmenu();
						userMenu.setStrproceso("");
						
						for (String pro:procesos){
							
								userMenu.setStrproceso(userMenu.getStrproceso() + pro + "/");
							
						}
						userMenu.setStrproceso(userMenu.getStrproceso().substring(0,userMenu.getStrproceso().length() - 1));
					}
					
				}else{
					userMenu.setStrproceso(userMenu.getStrproceso() + "/" + usuarioMenuDM.getSubMenuSeleccionado().getIdmenu());
					userMenu.setStrruta(userMenu.getStrruta() + "/" +usuarioMenuDM.getProcesoSeleccionado().getIdmenu());
				}
			}else{
				boolean procesoEncontrado=false;
				String [] procesos=userMenu.getStrproceso().split("/")[0].split("-");
				for (String proceso:procesos){
					if (Integer.parseInt(proceso)==usuarioMenuDM.getProcesoSeleccionado().getIdmenu()){
						procesoEncontrado=true;
					}
				}
				if (procesoEncontrado){
					new MessageUtil().showInfoMessage("Usuario Menu", "Este proceso ya se encuentra insertado");
					throw new Exception("Faltan datos"); 
				}else{
					String [] proc= userMenu.getStrproceso().split("/");
					proc[0]=proc[0] +"-" + usuarioMenuDM.getProcesoSeleccionado().getIdmenu();
					userMenu.setStrproceso("");
					for (String pro:proc){
						
							userMenu.setStrproceso(userMenu.getStrproceso() + pro + "/");

					}
					userMenu.setStrproceso(userMenu.getStrproceso().substring(0, userMenu.getStrproceso().length() - 1));
				}
			} 
			iUsuarioMenuDao.actualizar(userMenu);
		}
		
	}

	@Override
	public void elimUsuarioMenu(UsuarioMenuDM usuarioMenuDM) throws Exception{
		if (usuarioMenuDM.getUsuarioSeleccionado().getIdusuario()==0){
			new MessageUtil().showInfoMessage("Usuario Menu", "Seleccione un usuario");
			throw new Exception("Faltan datos");
		}
		
		if (usuarioMenuDM.gettMenuSeleccionado().getIdtipomenu()==0){
			new MessageUtil().showInfoMessage("Usuario Menu", "Seleccione un tipo de usuario");
			throw new Exception("Faltan datos");
		}
		if (usuarioMenuDM.getModuloSeleccionado().getIdmenu()==0){
			new MessageUtil().showInfoMessage("Usuario Menu", "Seleccione un Modulo");
			throw new Exception("Faltan datos");
		}
		
		if (usuarioMenuDM.getEmpresaSeleccionada().getIdempresa()==0){
			new MessageUtil().showInfoMessage("Usuario Menu", "Seleccione una empresa");
			throw new Exception("Faltan datos");
		}
		
		Usuariomenu userMenu= new  Usuariomenu();
		userMenu.setIdempresa(usuarioMenuDM.getEmpresaSeleccionada().getIdempresa());
		userMenu.setIdestado(1);
		userMenu.setIdmenu(usuarioMenuDM.gettMenuSeleccionado().getIdtipomenu());
		userMenu.setIdusuario(usuarioMenuDM.getUsuarioSeleccionado().getIdusuario());
		Usuariomenu usarioMenuEncontrado=null;
		//For para saber si ya existe el modulo ingresado
		for (Usuariomenu menu:usuarioMenuDM.getUsuarioMenu()){
			if (menu.getStrruta()==null) continue;
			String modulo=menu.getStrruta().split("/")[0];
			if (Integer.parseInt(modulo)==usuarioMenuDM.getModuloSeleccionado().getIdmenu() && menu.getIdmenu()==userMenu.getIdmenu() && menu.getIdusuario()==userMenu.getIdusuario()){
				usarioMenuEncontrado=menu;
				break;
			}
		}
		if (usarioMenuEncontrado==null){
			new MessageUtil().showInfoMessage("Usuario Menu", "Modulo no encontrado");
			throw new Exception("Faltan datos");
		}else{
			userMenu.setIdusuariomenu(usarioMenuEncontrado.getIdusuariomenu());
			if (usuarioMenuDM.getSubMenuSeleccionado().getIdmenu()!=0){
				String [] SubMenus=usarioMenuEncontrado.getStrruta().split("/");
				int contSubmenus=0;
				boolean SubmenuEncontrado=false;
				//El primero no se toma por que siempre es un modulo
				for (String subMenu:SubMenus){
					if (contSubmenus!=0){
						if (Integer.parseInt(subMenu)==usuarioMenuDM.getSubMenuSeleccionado().getIdmenu()){
							SubmenuEncontrado=true;
							break;
						}
					}
					contSubmenus++;
				}
				if (!SubmenuEncontrado){
					new MessageUtil().showInfoMessage("Usuario Menu", "Sub-menu no encontrado");
					throw new Exception("Faltan datos");
				}
				String []procesos=usarioMenuEncontrado.getStrproceso().split("/");
				if (procesos[contSubmenus]==null){
					new MessageUtil().showInfoMessage("Usuario Menu", "Procesos relacionados al sub-menu no encontrados");
					throw new Exception("Faltan datos");
				}
				if (usuarioMenuDM.getProcesoSeleccionado().getIdmenu()==0){
					userMenu.setStrruta("");
					for (String subMenu:SubMenus){
						if (Integer.parseInt(subMenu)!=usuarioMenuDM.getSubMenuSeleccionado().getIdmenu()){
							userMenu.setStrruta(userMenu.getStrruta() + subMenu + "/");
						}
					}
					userMenu.setStrruta(userMenu.getStrruta().substring(0,userMenu.getStrruta().length() - 1));
					int contProceso=0;
					userMenu.setStrproceso("");
					for (String proceso:procesos){
						if (contProceso!=contSubmenus){
							userMenu.setStrproceso(userMenu.getStrproceso() + proceso + "/");
						}
						contProceso++;
					}	
					userMenu.setStrproceso(userMenu.getStrproceso().substring(0, userMenu.getStrproceso().length() - 1));
				}else{
					userMenu.setStrruta(usarioMenuEncontrado.getStrruta());
					String [] proc=procesos[contSubmenus].split("-");
					boolean procesoEncontrado=false;
					for (String pro:proc){
						if (usuarioMenuDM.getProcesoSeleccionado().getIdmenu()==Integer.parseInt(pro)){
							procesoEncontrado=true;
							break;
						}
					}
					if (!procesoEncontrado){
						new MessageUtil().showInfoMessage("Usuario Menu", "Proceso relacionado al sub-menu no encontrado");
						throw new Exception("Faltan datos");
					}
					String procesoNuevo="";
					for (String pro:proc){
						if (usuarioMenuDM.getProcesoSeleccionado().getIdmenu()!=Integer.parseInt(pro))
							procesoNuevo=procesoNuevo+pro+"-";
					}
					procesoNuevo=procesoNuevo.substring(0, procesoNuevo.length() - 1);
					procesos[contSubmenus]=procesoNuevo;
					userMenu.setStrproceso("");
					for (String proceso:procesos){
						userMenu.setStrproceso(userMenu.getStrproceso() + proceso + "/");
					}
					userMenu.setStrproceso(userMenu.getStrproceso().substring(0,userMenu.getStrproceso().length() - 1));
				}
			}else{
				if (usuarioMenuDM.getProcesoSeleccionado().getIdmenu()!=0){
					userMenu.setStrruta(usarioMenuEncontrado.getStrruta());
					String []procesos=usarioMenuEncontrado.getStrproceso().split("/");
					String [] proc=procesos[0].split("-");
					boolean procesoEncontrado=false;
					for (String pro:proc){
						if (usuarioMenuDM.getProcesoSeleccionado().getIdmenu()==Integer.parseInt(pro)){
							procesoEncontrado=true;
							break;
						}
					}
					if (!procesoEncontrado){
						new MessageUtil().showInfoMessage("Usuario Menu", "Proceso relacionado al sub-menu no encontrado");
						throw new Exception("Faltan datos");
					}
					String procesoNuevo="";
					for (String pro:proc){
						if (usuarioMenuDM.getProcesoSeleccionado().getIdmenu()!=Integer.parseInt(pro)){
							procesoNuevo=procesoNuevo+pro+"-";
						}
					}
					procesoNuevo=procesoNuevo.substring(0, procesoNuevo.length() -1);
					procesos[0]=procesoNuevo;
					userMenu.setStrproceso("");
					for (String proceso:procesos){
						userMenu.setStrproceso(userMenu.getStrproceso() + proceso + "/");
					}
					userMenu.setStrproceso(userMenu.getStrproceso().substring(0, userMenu.getStrproceso().length() - 1));
				}else{
					iUsuarioMenuDao.eliminar(userMenu);
				}
			}
			iUsuarioMenuDao.actualizar(userMenu);
		}
		
	}



}
