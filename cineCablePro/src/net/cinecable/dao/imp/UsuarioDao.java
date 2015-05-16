package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import net.cinecable.dao.IUsuarioDao;

import pojo.annotations.Usuario;

@Stateless
public class UsuarioDao extends GenericDao<Usuario, Integer> implements IUsuarioDao{

	public UsuarioDao() {
		super(Usuario.class);
		// TODO Auto-generated constructor stub
	}

}
