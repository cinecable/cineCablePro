package net.cinecable.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IUsuarioMenuDao;
import pojo.annotations.Usuariomenu;
@Stateless
public class UsuarioMenuDao extends GenericDao<Usuariomenu, Long> implements IUsuarioMenuDao{

	public UsuarioMenuDao() {
		super(Usuariomenu.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Usuariomenu> getUsuarioMenuByIdUsuario(Integer idUsua) {
		List<Usuariomenu> listaUsuaMenu= new ArrayList<Usuariomenu>();
		StringBuilder sql= new StringBuilder();
		sql.append("select o from Usuariomenu o ");
		sql.append("where o.idusuario = :usua");
		Query query= em.createQuery(sql.toString());
		query.setParameter("usua", idUsua);
		listaUsuaMenu=query.list();
		return listaUsuaMenu;
	}
	
	@Override
	public List<Usuariomenu> getUsuarioMenuByIdUsuarioTipoUsuario(Integer idUsua,Integer tipoMenu) {
		List<Usuariomenu> listaUsuaMenu= new ArrayList<Usuariomenu>();
		StringBuilder sql= new StringBuilder();
		sql.append("select o from Usuariomenu o ");
		sql.append("where o.idusuario = :usua ");
		sql.append("and o.idmenu = :tipMenu");
		Query query= em.createQuery(sql.toString());
		query.setParameter("usua", idUsua);
		query.setParameter("tipMenu", tipoMenu);
		listaUsuaMenu=query.list();
		return listaUsuaMenu;
	}
	
}
