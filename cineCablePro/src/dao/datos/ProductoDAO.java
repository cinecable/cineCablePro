package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Producto;

public class ProductoDAO {

	public Producto getProductoByIdproducto(Session session, int idproducto) throws Exception {
		Producto producto = null;
		
		String hql = " from Producto ";
		hql += " where idproducto = :idproducto ";
		
		Query query = session.createQuery(hql)
				.setInteger("idproducto", idproducto);
		
		producto = (Producto) query.uniqueResult();
		
		return producto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> lisProductoByIdCuenta(Session session, int idcuenta, int idempresa) throws Exception {
		List<Producto> lisProducto = null;
		
		String hql = " select p from Ctasprod as c join c.producto as p ";
		hql += " where c.ctacliente.idcuenta = :idcuenta ";
		hql += " and c.estado.idestado = :idestado ";
		hql += " and c.empresa.idempresa = :idempresa ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setInteger("idestado", 3)//ojo ver x q estaba en 1, por eso no salían los productos
				.setInteger("idempresa", idempresa);
		
		lisProducto = (List<Producto>) query.list();
		
		return lisProducto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductoById(Session session, int idProducto) throws Exception {
		List<Producto> lisProductos = null;
		
		String hql = " from Producto ";
		hql += " where idproducto = :idProducto ";
		
		Query query = session.createQuery(hql)
				.setInteger("idProducto", idProducto);
		
		lisProductos = (List<Producto>) query.list();
		
		return lisProductos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> lisProductoByEmpresa(Session session, int idempresa) throws Exception {
		List<Producto> lisProductos = null;
		
		String hql = " from Producto ";
		hql += " where empresa.idempresa = :idempresa ";
		hql += " and estado.idestado = :idestado ";
	
		
		Query query = session.createQuery(hql)
				.setInteger("empresa.idempresa", idempresa)
				.setInteger("estado.idestado", 1);
	
		
		lisProductos = (List<Producto>) query.list();
		
		return lisProductos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> lisProductoXQuery(Session session, int idEmpresa,String query) throws Exception {
      List<Producto> lisProducto;

      Criteria criteria = session.createCriteria(Producto.class)
              .add( Restrictions.eq("empresa.idempresa", idEmpresa))
              .add( Restrictions.eq("estado.idestado", 1))
              
              .addOrder( Order.asc("nombre"));
              if(query != null && query.trim().length() > 0){
                  criteria.add( Restrictions.like("nombre",query.replaceAll(" ", "%")+"%").ignoreCase());
              }
              

      lisProducto = (List<Producto>)criteria.list();

      return lisProducto;
  }
	
	@SuppressWarnings("unchecked")
	public List<Producto> lisProductoByNombre(Session session, int idEmpresa, String nombre, int pageSize, int pageNumber, int[] args) throws Exception {
		List<Producto> lisProducto = null;
		
		Criteria criteria = session.createCriteria(Producto.class)
				.add( Restrictions.eq("empresa.idempresa", idEmpresa))
				.add( Restrictions.eq("estado.idestado", 1) );
		
		if(nombre != null && nombre.trim().length() > 0){
			criteria.add( Restrictions.like("nombre", "%"+nombre.replaceAll(" ", "%")+"%").ignoreCase());
		}
		
		criteria.addOrder(Order.asc("nombre"))
		.setMaxResults(pageSize)
		.setFirstResult(pageNumber);
			
		lisProducto = (List<Producto>) criteria.list();
		
		if(lisProducto != null && lisProducto.size() > 0)
		{
			Criteria criteriaCount = session.createCriteria( Producto.class)
				.setProjection( Projections.rowCount())
				.add( Restrictions.eq("empresa.idempresa", idEmpresa))
				.add( Restrictions.eq("estado.idestado", 1) );
			
			if(nombre != null && nombre.trim().length() > 0){
				criteriaCount.add( Restrictions.like("nombre", "%"+nombre.replaceAll(" ", "%")+"%").ignoreCase());
			}
			
			Object object = criteriaCount.uniqueResult();
			int count = (object==null?0:Integer.parseInt(object.toString()));
			args[0] = count;
		} else {
			args[0] = 0;
		}
		
		return lisProducto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> lisProductoByNombreJerarquia(Session session, int idEmpresa, String nombre, int jerarquia, String[] filtroIn, int pageSize, int pageNumber, int[] args) throws Exception {
		List<Producto> lisProducto = null;
		
		Criteria criteria = session.createCriteria(Producto.class)
				.add( Restrictions.eq("empresa.idempresa", idEmpresa))
				.add( Restrictions.eq("estado.idestado", 1) )
				.add( Restrictions.eq("jerarquia", jerarquia) );
		
		if(nombre != null && nombre.trim().length() > 0){
			criteria.add( Restrictions.like("nombre", "%"+nombre.replaceAll(" ", "%")+"%").ignoreCase());
		}
		
		if(filtroIn != null && filtroIn.length > 0){
			criteria.add( Restrictions.in("tipojerarquia", filtroIn));
		}
		
		criteria.addOrder(Order.asc("nombre"))
		.setMaxResults(pageSize)
		.setFirstResult(pageNumber);
			
		lisProducto = (List<Producto>) criteria.list();
		
		if(lisProducto != null && lisProducto.size() > 0)
		{
			Criteria criteriaCount = session.createCriteria( Producto.class)
				.setProjection( Projections.rowCount())
				.add( Restrictions.eq("empresa.idempresa", idEmpresa))
				.add( Restrictions.eq("estado.idestado", 1) )
				.add( Restrictions.eq("jerarquia", jerarquia) );
			
			if(nombre != null && nombre.trim().length() > 0){
				criteriaCount.add( Restrictions.like("nombre", "%"+nombre.replaceAll(" ", "%")+"%").ignoreCase());
			}
			
			if(filtroIn != null && filtroIn.length > 0){
				criteria.add( Restrictions.in("tipojerarquia", filtroIn));
			}
			
			Object object = criteriaCount.uniqueResult();
			int count = (object==null?0:Integer.parseInt(object.toString()));
			args[0] = count;
		} else {
			args[0] = 0;
		}
		
		return lisProducto;
	}
	
}
