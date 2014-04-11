package bean.controladores;

import global.Parametro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;

import bo.negocio.armaListMenuBO;

import util.FacesUtil;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class MenuBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6630209447002049179L;

	private int activeIndex;
	private int activeIdItem;
	private String logoMenu;
	private DefaultMenuModel menuPrincipal;
	private DefaultMenuModel menuCliente;
	private String tagMenu;

	public MenuBean() {
		menuPrincipal = new DefaultMenuModel();
		activeIndex = 0;
		activeIdItem = 0;

		menuPrincipal = armarMenu(Parametro.TIPO_MENU_PRINCIPAL);
		menuCliente = armarMenu(Parametro.TIPO_MENU_CLIENTE);
	}

	

	private DefaultMenuModel armarMenu(int idtipomenu) {
		DefaultMenuModel menu = new DefaultMenuModel();
		String strParametrosUrl = new FacesUtil().getParametrosUrl();
		try {
			int contador = 0;
			String elementoPorcionPro = "";
			int IdMenuPrincipal = 0;
			int PosicionParametro=0;
			
			armaListMenuBO armaListMenuBO = new armaListMenuBO();
			ArrayList<String> subMenus = new ArrayList<String>();
			ArrayList<String> procesos = new ArrayList<String>();

			Map<Integer, String> MapNombreMenu = new HashMap<Integer, String>();
			Map<Integer, String> mapIconoMenu = new HashMap<Integer, String>();
			Map<Integer, String> mapBeans = new HashMap<Integer, String>();
			Map<Integer, Integer> mapPosPar = new HashMap<Integer, Integer>();
			Iterator<String> nombreIterator = subMenus.iterator();

			UsuarioBean usuarioBean = (UsuarioBean) new FacesUtil()
					.getSessionBean("usuarioBean");
			armaListMenuBO.armaListas(idtipomenu, usuarioBean.getUsuario()
					.getIdusuario(),mapPosPar, subMenus, procesos, mapBeans,
					mapIconoMenu, MapNombreMenu);

			nombreIterator = subMenus.iterator();

			while (nombreIterator.hasNext()) {
				String elementoSubMenu = nombreIterator.next();

				if (elementoSubMenu != null && elementoSubMenu.length() > 0) {
					String elemsSSUbMen[] = null;
					if (elementoSubMenu.split("/").length > 0) {
						elemsSSUbMen = elementoSubMenu.split("/");
					}

					String elementoProceso = procesos.get(contador);
					Submenu submenuPrincipal = new Submenu();
					int cuenta2 = 0;
					if (elementoProceso != null && elementoProceso.length() > 0) {
						for (String elementoFinalSubMenu : elemsSSUbMen) {

							String elemenSProces[] = null;

							elementoPorcionPro = "";
							if (elementoProceso.split("/").length > 0) {
								elemenSProces = elementoProceso.split("/");
								elementoPorcionPro = elemenSProces[cuenta2];
							}

							if (elementoPorcionPro != null
									&& elementoPorcionPro.length() > 0) {
								if (elementoFinalSubMenu != null
										&& elementoFinalSubMenu.length() > 0) {

									String elemen2SProces[] = elementoPorcionPro
											.split("-");

									int idSubMenu = Integer
											.parseInt(elementoFinalSubMenu);

									String nombreSubMenu = (String) MapNombreMenu
											.get(idSubMenu);
									// String
									// rutaSubBeans=(String)mapBeans.get(idSubMenu);
									String nombreSubIcono = (String) mapIconoMenu
											.get(idSubMenu);

									if (nombreSubMenu != null
											&& nombreSubMenu.length() > 0) {
										Submenu submenuSecundario = new Submenu();
										if (cuenta2 == 0) {
											IdMenuPrincipal = idSubMenu;
											// First submenu

											submenuPrincipal
													.setLabel(nombreSubMenu);
											submenuPrincipal
													.setIcon(nombreSubIcono);

										} // if contador == 0
										else {

											submenuSecundario
													.setLabel(nombreSubMenu);
											submenuSecundario
													.setIcon(nombreSubIcono);
										}

										for (String elementoFinalProceso : elemen2SProces) {

											int idProceso = Integer
													.parseInt(elementoFinalProceso);

											String nombreProMenu = (String) MapNombreMenu
													.get(idProceso);
											String rutaProBeans = (String) mapBeans
													.get(idProceso);
											String nombreProIcono = (String) mapIconoMenu
													.get(idProceso);
											// rutaProBeans="clientesNuevos.jsf?faces-redirect=true";
											/*PosicionParametro=(Integer) mapPosPar.get(idProceso);
											if (PosicionParametro == 1) {
												rutaProBeans=rutaProBeans + "?" +strParametrosUrl;
											}*/
											if(strParametrosUrl != null && strParametrosUrl.trim().length() > 0 && rutaProBeans != null){
												rutaProBeans += "?" +strParametrosUrl;
											}
											//Se concatena parametro para opcion Consulta Facturas con IdProceso 38
											/*if(strParametrosUrl != null && strParametrosUrl.trim().length() > 0 && idProceso == 38){
												rutaProBeans += "?" +strParametrosUrl;
											}
											//Se concatena parametro para opcion Consulta Pagos con IdProceso 39
											if(strParametrosUrl != null && strParametrosUrl.trim().length() > 0 && idProceso == 39){
												rutaProBeans += "?" +strParametrosUrl;
											}
											//Se concatena parametro para opcion Consulta Excedentes con IdProceso 40
											if(strParametrosUrl != null && strParametrosUrl.trim().length() > 0 && idProceso == 40){
												rutaProBeans += "?" +strParametrosUrl;
											}
											//Se concatena parametro para opcion Consulta Creditos con IdProceso 41
											if(strParametrosUrl != null && strParametrosUrl.trim().length() > 0 && idProceso == 41){
												rutaProBeans += "?" +strParametrosUrl;
											}*/
											if (cuenta2 == 0
													&& IdMenuPrincipal != idProceso) {

												MenuItem menuItem = new MenuItem();
												menuItem.setValue(nombreProMenu);

												menuItem.setUrl(rutaProBeans);
												menuItem.setIcon(nombreProIcono);

												submenuPrincipal.getChildren()
														.add(menuItem);
												// menuPrincipal.addMenuItem(menuItem);

											} else {
												if (cuenta2 != 0) {
													MenuItem item = new MenuItem();
													item.setValue(nombreProMenu);
													item.setUrl(rutaProBeans);
													item.setIcon(nombreProIcono);
													submenuSecundario
															.getChildren().add(
																	item);

													submenuPrincipal
															.getChildren()
															.add(submenuSecundario);
												}

											}

										} // for elementoFinalProceso

									}// if nombreSubMenu

								} // if elementoFinalSubMenu

							} // if elementoPorcionPro

							cuenta2 += 1;

						} // for elementoFinalSubMenu
						if (submenuPrincipal.getChildCount() > 0) {
							menu.addSubmenu(submenuPrincipal);
						}

					}// elementoProceso != null

				}// elementoSubMenu != null

				contador += 1;

			} // fin while
			
			

		}// fin try
		catch (Exception ex) {
			ex.printStackTrace();
			new MessageUtil().showFatalMessage("Informativo!",
					"Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
		for (int x = 1; x <= 15; x = x + 1)

		{
			MenuItem Agregados = new MenuItem();
			menu.addMenuItem(Agregados);
		}
		return menu;

	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public int getActiveIdItem() {
		return activeIdItem;
	}

	public void setActiveIdItem(int activeIdItem) {
		this.activeIdItem = activeIdItem;
	}

	public String getLogoMenu() {
		return logoMenu;
	}

	public void setLogoMenu(String logoMenu) {
		this.logoMenu = logoMenu;
	}

	public DefaultMenuModel getMenuPrincipal() {
		return menuPrincipal;
	}

	public DefaultMenuModel getMenuCliente() {
		return menuCliente;
	}

	public String getTagMenu() {
		return tagMenu;
	}

}
