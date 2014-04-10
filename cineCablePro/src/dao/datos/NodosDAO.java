package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Nodos;

public class NodosDAO {
	@SuppressWarnings("unchecked")
	public List<Nodos> lisNodoBySector(Session session, int idSector) throws Exception {
        List<Nodos> lisNodos;

        Criteria criteria = session.createCriteria(Nodos.class)
                .add( Restrictions.eq("sector.idsector", idSector))
                .add( Restrictions.eq("idestado", 1))
                .addOrder( Order.asc("nombre"));

        lisNodos = (List<Nodos>)criteria.list();

        return lisNodos;
    }
  
  @SuppressWarnings("unchecked")
	public List<Nodos> lisNodoxNodo(Session session, int idNodos) throws Exception {
        List<Nodos> lisNodos;

        Criteria criteria = session.createCriteria(Nodos.class)
                .add( Restrictions.eq("idnodo", idNodos))
                .add( Restrictions.eq("idestado", 1))
                .addOrder( Order.asc("nombre"));

        lisNodos = (List<Nodos>)criteria.list();

        return lisNodos;
    }
}
