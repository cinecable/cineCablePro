package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import net.cinecable.dao.IMaterialesDao;
import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoEquipo;
import net.cinecable.enums.TipoMaterial;
import net.cinecable.enums.TipoPropietario;
import net.cinecable.enums.TipoUnidadMedida;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.ReservacionesBodegaMateriales;

import org.hibernate.Query;

@Stateless
public class MaterialesDao extends GenericDao<Materiales, Long> implements IMaterialesDao {

	public MaterialesDao() {
		super(Materiales.class);
	}

	@Override
	public List<Materiales> getAllbyTipoEquipo(TipoEquipo tipEquipo) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Materiales o ");
		sql.append("where o.tipoMaterial.tipEquipoMaterial =:tipmat ");
		sql.append("and o.cantidad > 0 ");
		sql.append("and o.estado=:est ");
		sql.append("order by o.idUnidad ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tipmat", tipEquipo);
		query.setParameter("est", Estados.ACTIVO.getDescription());
		List<Materiales> result = query.list();
		return result;
	}

	@Override
	public List<Materiales> getAllbyTipoMaterialNoOcupadoInventario(String tipMaterial) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Materiales o ");
		sql.append("where not exists(from Inventario o where o.unidad=o) ");
		sql.append("and o.tipoMaterial.descAbrev like :tipmat ");
		sql.append("and o.estado=:est");
		sql.append("order by o.idUnidad ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tipmat", tipMaterial);
		query.setParameter("est", Estados.ACTIVO.getDescription());
		List<Materiales> result = query.list();
		return result;
	}

	@Override
	public List<Materiales> getMaterialTecnicoControl(Long idTecnico, Ordenes orden, TipoMaterial... tipoMaterial) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct o from Materiales o,ReservacionesBodegaMateriales p,ReservacionesOrdenesBodega q ");
		sql.append("where o=p.material ");
		sql.append("and p.reservaOrdenBodega=q ");
		sql.append("and q.tecnico.idpersona=:codPer ");
		sql.append("and q.orden.estado.idestado in (:est) ");
		sql.append("and o.tipoUnidad = :tipUnidad ");
		sql.append("and q.orden=:ord ");
		sql.append("and p.material.tipoMaterial.tipMaterialGen in (:tmat) ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("ord", orden);
		query.setParameter("codPer", idTecnico.intValue());
		Integer[] estados = new Integer[] { Estados.MONITOREO.getDescription(), Estados.PROCESO.getDescription() };
		query.setParameterList("est", estados);
		query.setParameterList("tmat", tipoMaterial);
		query.setParameter("tipUnidad", TipoUnidadMedida.NA);
		List<Materiales> result = query.list();
		return result;
	}

	@Override
	public List<Materiales> getMaterialPersonaCuentaControl(Long idCuenta, TipoMaterial... tipoMaterial) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct o from Materiales o,ReservacionesOrdenesBodega q, ReservacionesBodegaMateriales p, Inventario i ");
		sql.append("where q.orden.cuentaCliente.idcuenta=:idcuenta ");
		sql.append("and p.reservaOrdenBodega=q ");
		sql.append("and o=p.material ");
		sql.append("and q.orden.estado.idestado in (:est) ");
		sql.append("and i.unidad=o ");
		sql.append("and i.propietario=:tipProp ");
		sql.append("and o.tipoUnidad = :tipUnidad ");
		sql.append("and o.tipoMaterial.tipMaterialGen in (:tmat)");
		Query query = em.createQuery(sql.toString());
		query.setParameter("idcuenta", idCuenta.intValue());
		Integer[] estados = new Integer[] { Estados.MONITOREO.getDescription(), Estados.PROCESO.getDescription() };
		query.setParameterList("est", estados);
		query.setParameterList("tmat", tipoMaterial);
		query.setParameter("tipProp", TipoPropietario.CLI);
		query.setParameter("tipUnidad", TipoUnidadMedida.NA);
		List<Materiales> result = query.list();
		return result;
	}

	@Override
	public List<ReservacionesBodegaMateriales> getMaterialTecnicoBaja(Long idTecnico, Ordenes orden) {
		StringBuilder sql = new StringBuilder("select distinct rbm from ReservacionesBodegaMateriales rbm ");
		sql.append("inner join rbm.reservaOrdenBodega rob ");
		sql.append("inner join rob.orden ord ");
		sql.append("where ord=:ord ");
		sql.append("and ord.estado.idestado=:est ");
		sql.append("and rbm.estado=:esti ");
		sql.append("and exists(select 1 from Inventario i where i.unidad=rbm.material and i.propietario=:prop and i.estado=:esti and i.orden=:ord) ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("est", Estados.MONITOREADA.getDescription());
		query.setParameter("ord", orden);
		query.setParameter("prop", TipoPropietario.TEC);
		query.setParameter("esti", Estados.ACTIVO.getDescription());
		List<ReservacionesBodegaMateriales> result = query.list();
		return result;
	}

	@Override
	public List<ReservacionesBodegaMateriales> getMaterialPersonaCuentaBaja(Long idCuenta, Ordenes orden) {
		StringBuilder sql = new StringBuilder("select distinct rbm from ReservacionesBodegaMateriales rbm ");
		sql.append("inner join rbm.reservaOrdenBodega rob ");
		sql.append("inner join rob.orden ord ");
		sql.append("where ord=:ord ");
		sql.append("and ord.estado.idestado = :est ");
		sql.append("and rbm.estado=:esti ");
		sql.append("and exists(select i from Inventario i where i.unidad=rbm.material and  i.propietario=:prop and i.estado=:esti and i.orden=:ord) ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("est", Estados.MONITOREADA.getDescription());
		query.setParameter("ord", orden);
		query.setParameter("prop", TipoPropietario.CLI);
		query.setParameter("esti", Estados.ACTIVO.getDescription());
		List<ReservacionesBodegaMateriales> result = query.list();
		return result;
	}

	@Override
	public List<Materiales> getMaterialProductoCuenta(Long idCuenta) {
		StringBuilder sql = new StringBuilder();
		sql.append("select o from Materiales o,ReservacionesOrdenesBodega q, ReservacionesBodegaMateriales p, Inventario i ");
		sql.append("where q.orden.cuentaCliente.idcuenta=:idcuenta ");
		sql.append("and p.reservaOrdenBodega=q ");
		sql.append("and o=p.material ");
		sql.append("and q.orden.estado.idestado = :est ");
		sql.append("and i.unidad=o ");
		sql.append("and i.propietario=:tipProp");
		Query query = em.createQuery(sql.toString());
		query.setParameter("idcuenta", idCuenta.intValue());
		query.setParameter("est", Estados.REALIZADA.getDescription());
		query.setParameter("tipProp", TipoPropietario.CLI);
		List<Materiales> result = query.list();
		return result;
	}

	@Override
	public Materiales getMaterialesxSerieoMacEstado(String serieMac, Estados estado) {
		StringBuilder sql = new StringBuilder("from Materiales o where o.nroSerie=:ser ");
		sql.append("or o.macAddres=:ser and o.estado=:est ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("est", estado.getDescription());
		query.setParameter("ser", serieMac);
		List<Materiales> result = query.list();
		if (!result.isEmpty())
			return result.get(0);
		return null;
	}

}
