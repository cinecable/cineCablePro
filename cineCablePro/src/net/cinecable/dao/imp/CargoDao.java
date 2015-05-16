package net.cinecable.dao.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import net.cinecable.dao.ICargoDao;
import net.cinecable.enums.Estados;

import org.hibernate.Query;

import pojo.annotations.Cargos;

@Stateless
public class CargoDao extends GenericDao<Cargos, Long> implements ICargoDao {

	public CargoDao() {
		super(Cargos.class);
	}

	@Override
	public List<Cargos> getCargosByIdgeneracion(Integer idGeneracion) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Cargos o where o.estado.idestado=:isEst ");
		sql.append("and o.factura.idgeneracion=:idCargo");
		Query query = em.createQuery(sql.toString());
		query.setParameter("isEst", Estados.ACTIVO.getDescription());
		query.setParameter("idCargo", idGeneracion.intValue());
		List<Cargos> result = query.list();
		return result;
	}

	@Override
	public String generarCargos(int idcuenta, Date fecha, int estadoCuenta, int codProducto, boolean cargoNegativo, int idUsuario) {
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		String sql = "select facturacion(:idcuenta,:fecha,:estCuenta,:codProducto,:negativo,:idusuario)";
		Query query = em.createSQLQuery(sql)
		.setParameter("idcuenta", idcuenta)
		.setParameter("fecha",df.format(fecha))
		.setParameter("estCuenta", estadoCuenta)
		.setParameter("codProducto", codProducto)
		.setParameter("negativo", cargoNegativo)
		.setParameter("idusuario", idUsuario);
		String result = (String) query.uniqueResult();
		return result;
	}

}
