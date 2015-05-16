package net.cinecable.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;


import net.cinecable.dao.IAreaDao;
import pojo.annotations.Area;
@Stateless
public class AreaDao extends GenericDao<Area, Long> implements IAreaDao{

	public AreaDao() {
		super(Area.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAreasbyEstado(int estado) {
		List<Area> listaArea= new ArrayList<Area>();
		try{
			StringBuilder sql= new StringBuilder();
			sql.append("select o from Area o ");
			sql.append("where o.estado.idestado= :est");
			Query query=em.createQuery(sql.toString());
			query.setParameter("est", estado);
			listaArea=query.list();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaArea;
	}

}
