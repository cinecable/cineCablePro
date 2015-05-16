package net.cinecable.dao;

import javax.ejb.Local;

import pojo.annotations.Controlador;
import net.cinecable.model.base.UserWs;

@Local
public interface IUserWsDao extends IGenericDao<UserWs, Long> {

	UserWs getUsuario(Controlador ctrl);

}
