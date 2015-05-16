package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IReservaOrdenesBodegaDao;
import net.cinecable.model.base.ReservacionesOrdenesBodega;

@Stateless
public class ReservaOrdenesBodegaDao extends GenericDao<ReservacionesOrdenesBodega, Long> implements IReservaOrdenesBodegaDao {

	public ReservaOrdenesBodegaDao() {
		super(ReservacionesOrdenesBodega.class);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReservacionesOrdenesBodega> getReservasOrdenesBodegaByTecnicoAndTipoOrden(Long codTecnico, Long TipOrden) {
		StringBuilder sql = new StringBuilder();
		sql.append("from ReservacionesOrdenesBodega o ");
		sql.append("where o.tecnico.idpersona=:idtec ");
		if (TipOrden != null)
			sql.append("and o.orden.tipoOperacion.idtipooperacion=:tipop");
		Query query = em.createQuery(sql.toString());
		query.setParameter("idtec", codTecnico);
		if (TipOrden != null)
			query.setParameter("tipop", TipOrden);
		List<ReservacionesOrdenesBodega> result = query.list();
		return result;
	}

}
