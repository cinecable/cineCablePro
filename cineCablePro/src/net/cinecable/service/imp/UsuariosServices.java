package net.cinecable.service.imp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pojo.annotations.Usuario;
import net.cinecable.dao.IUsuarioDao;
import net.cinecable.service.IUsuarioServices;
@Stateless
public class UsuariosServices implements IUsuarioServices{
	@EJB
	private IUsuarioDao iUsuarioDao;
	@Override
	public List<Usuario> getUsuarios() {
		return iUsuarioDao.obtenerTodos();
	}
	
}
