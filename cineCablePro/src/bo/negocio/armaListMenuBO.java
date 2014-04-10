package bo.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.hibernate.Session;

import pojo.annotations.Menu;
import pojo.annotations.Usuariomenu;
import util.HibernateUtil;
import dao.datos.UsuariomenuDAO;
import dao.datos.menuDAO;

public class armaListMenuBO {

	public armaListMenuBO() {
		
	}

	public void armaListas(int idtipomenu,int idusuario,Map<Integer, Integer> mapParametros,ArrayList<String> subMenus,ArrayList<String> procesos,Map<Integer, String> mapBeans,Map<Integer, String> mapIconoMenu,Map<Integer, String> MapNombreMenu) throws Exception {
		Session session = null;
		
		 int idMenu;
		 String nombreMenu="";
	     String nombreIcono ="";
	     String rutaBeans="";
	     int  PosicionParametrosMenu=0;   
	     		    
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			
			menuDAO menuDAO = new menuDAO();
			List<Menu> lisMenu = menuDAO.lisMenu(session, 1,idtipomenu);
			
			for(Menu opciones : lisMenu){
	               idMenu=opciones.getIdmenu();  
	               nombreMenu=opciones.getNombre();
	               rutaBeans=opciones.getPaginaurl();
	               nombreIcono=opciones.getIconourl();
	               PosicionParametrosMenu=opciones.getOrden();
	               
	               MapNombreMenu.put(idMenu, nombreMenu);  
	               mapIconoMenu.put(idMenu, nombreIcono); 
	               mapBeans.put(idMenu, rutaBeans); 
	               mapParametros.put(idMenu, PosicionParametrosMenu);
	            }
	          
			 
			UsuariomenuDAO usuariomenuDAO = new UsuariomenuDAO();
			List<Usuariomenu> lisUsuariomenu = usuariomenuDAO.lisUsuariomenu(session, idusuario,idtipomenu);
			if(lisUsuariomenu != null && lisUsuariomenu.size() > 0){
				
				for(Usuariomenu opciones : lisUsuariomenu){
						            
		             subMenus.add(opciones.getStrruta());
		             procesos.add(opciones.getStrproceso()) ;
				}
			}
				
			
		}
		catch(Exception ex){
			throw new Exception(ex);
		}
		finally{
			session.close();
		}
		
		
	}
}
