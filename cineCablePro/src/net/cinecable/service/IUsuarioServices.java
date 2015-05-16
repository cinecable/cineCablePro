package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Usuario;

@Local
public interface IUsuarioServices {
	List<Usuario> getUsuarios();
}
