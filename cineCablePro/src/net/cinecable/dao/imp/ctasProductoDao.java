package net.cinecable.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IctasProductoDao;
import pojo.annotations.Ctasprod;
import pojo.annotations.Producto;

@Stateless
public class ctasProductoDao extends GenericDao<Ctasprod, Integer> implements
		IctasProductoDao {

	public ctasProductoDao() {
		super(Ctasprod.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Producto> getProductoById(int cta) {
		List<Producto> listaProductos = new ArrayList<Producto>();
		StringBuilder sql = new StringBuilder();
		sql.append("from Producto o ");
		sql.append("where o.idproducto in (");
		sql.append("select c.producto.idproducto from Ctasprod c where c.ctacliente.idcuenta= :cuenta )");
		Query query = em.createQuery(sql.toString());
		query.setParameter("cuenta", cta);
		listaProductos = query.list();
		return listaProductos;
	}

	@Override
	public List<Ctasprod> getProductoByIdCta(int cta, int estado) {
		List<Ctasprod> listaProductos = new ArrayList<Ctasprod>();
		StringBuilder sql = new StringBuilder();
		sql.append(" from Ctasprod as o left join fetch o.producto as p  ");
		sql.append(" where o.ctacliente.idcuenta= :cuenta and o.estado.idestado= :estado ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("cuenta", cta);
		query.setParameter("estado", estado);
		listaProductos = query.list();
		for (Ctasprod prod : listaProductos) {
			prod.getProducto().getProdServicio().size();
		}
		return listaProductos;
	}

}
