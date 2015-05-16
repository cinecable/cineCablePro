package bean.controladores;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bo.negocio.UsuarioBO;

import pojo.annotations.Usuario;
import util.FacesUtil;
import util.FileUtil;
import util.MessageUtil;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7272888203959662221L;
	private Usuario usuario;
	private String username;
	private String password;
	private String ip;
	private String sid;
	private boolean autenticado;
	
	public UsuarioBean(){
		ip = new FacesUtil().getIp();
		sid = new FacesUtil().getSid();
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getIp() {
		return ip;
	}

	public String getSid() {
		return sid;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public String login(){
		String strRedirect = null;
		
		try{
			usuario = new UsuarioBO().getByUserPasswd(username, password);
			
			if(usuario!=null && usuario.getIdusuario() > 0){
				autenticado = true;
				FacesUtil facesUtil = new FacesUtil();
				strRedirect = (String) facesUtil.getSessionBean("urlrequested");
				
				if(strRedirect != null && !strRedirect.isEmpty()){
					facesUtil.removeSessionBean("urlrequested");
				}else{
					FileUtil fileUtil = new FileUtil();
					strRedirect = fileUtil.getPropertyValue("home");
				}
			}else{
				new MessageUtil().showWarnMessage("Autenticación fallida","Usuario o Contraseña no existen.");
			}
		}catch(Exception re){
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
		
		return strRedirect;
	}
	
	public String logout(){
		String strRedirect = null;
		
		try{
			FacesUtil facesUtil = new FacesUtil();
			facesUtil.logout();
			FileUtil fileUtil = new FileUtil();
			strRedirect = fileUtil.getPropertyValue("login");
			facesUtil.redirect(strRedirect);
		}catch(Exception re){
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
		
		return strRedirect;
	}
	
	public String homePage(){
		String homePage = null;
		
		try{
			FileUtil fileUtil = new FileUtil();
			homePage = fileUtil.getPropertyValue("home");
		}
		catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
		
		return homePage;
	}
	
}