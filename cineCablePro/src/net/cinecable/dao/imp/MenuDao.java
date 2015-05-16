package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IMenuDao;

import pojo.annotations.Menu;
@Stateless
public class MenuDao extends GenericDao<Menu, Integer> implements IMenuDao {

	public MenuDao() {
		super(Menu.class);
	}

	@Override
	public List<Menu> getMenubyNivel(Integer nivel) {
		StringBuilder sql= new StringBuilder();
		sql.append("select o from Menu o ");
		sql.append("where o.nivel = :niv");
		Query query= em.createQuery(sql.toString());

		query.setParameter("niv", nivel);
		List<Menu> listaMenu=query.list();
		
		return listaMenu;
	}
	
	@Override
	public List<Menu> getMenubyNivelTipoMenu(Integer nivel,Integer tipoMenu) {
		StringBuilder sql= new StringBuilder();
		sql.append("select o from Menu o ");
		sql.append("where o.nivel = :niv ");
		sql.append("and o.tipomenu.idtipomenu = :tipMen ");
		Query query= em.createQuery(sql.toString());

		query.setParameter("niv", nivel);
		query.setParameter("tipMen", tipoMenu);
		List<Menu> listaMenu=query.list();
		
		return listaMenu;
	}

	@Override
	public List<Menu> getMenubyNivelIdPadre(Integer idPadre, Integer nivel) {
		StringBuilder sql= new StringBuilder();
		sql.append("select o from Menu o ");
		sql.append("where o.nivel = :niv " );
		sql.append("and o.idmenupadre = :idPad");
		Query query= em.createQuery(sql.toString());

		query.setParameter("niv", nivel);
		query.setParameter("idPad", idPadre);
		List<Menu> listaMenu=query.list();
		return listaMenu;
	}

}
