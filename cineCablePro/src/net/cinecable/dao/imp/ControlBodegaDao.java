package net.cinecable.dao.imp;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;

import net.cinecable.dao.IControlBodegaDao;
import net.cinecable.enums.Estados;
import net.cinecable.model.base.ControlBodega;
import net.cinecable.model.base.ReservacionesBodegaMateriales;
import net.cinecable.model.base.ReservacionesOrdenesBodega;

import org.hibernate.Query;

import pojo.annotations.Persona;

@Stateless
public class ControlBodegaDao extends GenericDao<ControlBodega, Long> implements IControlBodegaDao {

	public ControlBodegaDao() {
		super(ControlBodega.class);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<ControlBodega> getControlBodegabyTecnicoYestado(Persona tecnico) {
		StringBuilder sql = new StringBuilder("select distinct o from ControlBodega o , in(o.reservas) as res ");
		sql.append("where o.tecnico=:per and res.orden.estado!=:est and o.estado=:est2");
		Query query = em.createQuery(sql.toString());
		query.setParameter("per", tecnico);
		query.setParameter("est2", Estados.ACTIVO.getDescription());
		query.setParameter("est", Estados.REALIZADA.getDescription());
		List<ControlBodega> result = query.list();
		for (ControlBodega bod : result) {
			for (ReservacionesOrdenesBodega rob : bod.getReservas()) {
				Iterator<ReservacionesBodegaMateriales> materiales = rob.getReservaMateriales().iterator();
				while (materiales.hasNext()) {
					ReservacionesBodegaMateriales material = materiales.next();
					if (material.getEstado() == Estados.INACTIVO.getDescription())
						materiales.remove();
				}
			}
		}
		return result;
	}
}
