package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import pojo.annotations.Tipooperacion;

public class TipooperacionDAO {

	@SuppressWarnings("unchecked")
	public List<Tipooperacion> tipooperacionById(Session session) throws Exception {
		List<Tipooperacion> tipooperacion = null;



		Criteria criteria = session.createCriteria(Tipooperacion.class);


		tipooperacion = (List<Tipooperacion>)criteria.list();

		return tipooperacion;
	}
}
