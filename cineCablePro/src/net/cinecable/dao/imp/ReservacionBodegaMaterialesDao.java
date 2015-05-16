package net.cinecable.dao.imp;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.IControlBodegaDao;
import net.cinecable.dao.IMaterialesDao;
import net.cinecable.dao.IOrdenesDao;
import net.cinecable.dao.IReservacionBodegaMaterialesDao;
import net.cinecable.dao.IinventarioDao;
import net.cinecable.enums.Estados;
import net.cinecable.enums.MovimientoInventario;
import net.cinecable.enums.TipoPropietario;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.ControlBodega;
import net.cinecable.model.base.Inventario;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.ReservacionesBodegaMateriales;
import net.cinecable.model.base.ReservacionesOrdenesBodega;

import org.hibernate.Query;

import pojo.annotations.Ctacliente;
import pojo.annotations.Estado;
import pojo.annotations.Producto;

@Stateless
public class ReservacionBodegaMaterialesDao extends GenericDao<ReservacionesBodegaMateriales, Long> implements IReservacionBodegaMaterialesDao {

	public ReservacionBodegaMaterialesDao() {
		super(ReservacionesBodegaMateriales.class);
	}

	@EJB
	IMaterialesDao imaterialesDao;
	@EJB
	IinventarioDao iInventarioDao;
	@EJB
	IOrdenesDao iordenDao;
	@EJB
	IControlBodegaDao iControlBodegaDao;

	@Override
	public void guardarReservacionesBodegaMateriales(ControlBodega control) throws EntidadNoGrabadaException {
		for (ReservacionesOrdenesBodega ordBod : control.getReservas()) {
			Ordenes orden = ordBod.getOrden();
			ordBod.setControlBodega(control);
			Estado estado = new Estado();
			estado.setIdestado(Estados.PROCESO.getDescription());
			orden.setEstado(estado);
			iordenDao.actualizar(orden);
			for (ReservacionesBodegaMateriales matbod : ordBod.getReservaMateriales()) {
				try {
					Materiales material = matbod.getMaterial();
					material.setCantidad(material.getCantidad() - matbod.getCantidadTotal());
					imaterialesDao.actualizar(matbod.getMaterial());

					Inventario inv = new Inventario();
					inv.setEstado(Estados.ACTIVO.getDescription());
					inv.setFechaMovimiento(Calendar.getInstance().getTime());
					inv.setNroUnidades(matbod.getCantidadTotal());
					inv.setPropietario(TipoPropietario.TEC);
					inv.setTipMovimiento(MovimientoInventario.TRASPAS);
					inv.setTipoUnidad(matbod.getMaterial().getTipoUnidad());
					inv.setUnidad(matbod.getMaterial());
					inv.setCtaCliente(orden.getCuentaCliente());
					inv.setNroSerie(material.getNroSerie());
					inv.setOrden(orden);

					iInventarioDao.crear(inv);
				} catch (EntidadNoGrabadaException e) {
					throw new EntidadNoGrabadaException(e);
				}
			}
		}
		iControlBodegaDao.crear(control);

	}

	@Override
	public void guardarReservacionesBodegaMaterialesModificada(ControlBodega control) throws EntidadNoGrabadaException {
		for (ReservacionesOrdenesBodega ordBod : control.getReservas()) {
			Ordenes orden = ordBod.getOrden();
			ordBod.setControlBodega(control);
			control.setEstado(Estados.INACTIVO.getDescription());
			ordBod.setEstado(Estados.INACTIVO.getDescription());

			for (ReservacionesBodegaMateriales matbod : ordBod.getReservaMateriales()) {
				try {

					Materiales material = matbod.getMaterial();
					material.setCantidad(material.getCantidad() + matbod.getCantidadTotal());
					imaterialesDao.actualizar(matbod.getMaterial());

					if (matbod.getEstado() == Estados.INACTIVO.getDescription()) {
						Inventario inv = new Inventario();
						inv.setEstado(Estados.ACTIVO.getDescription());
						inv.setFechaMovimiento(Calendar.getInstance().getTime());
						inv.setNroUnidades(matbod.getCantidadTotal());
						inv.setPropietario(TipoPropietario.BOD);
						inv.setTipMovimiento(MovimientoInventario.TRASPAS);
						inv.setTipoUnidad(matbod.getMaterial().getTipoUnidad());
						inv.setUnidad(matbod.getMaterial());
						inv.setCtaCliente(orden.getCuentaCliente());
						inv.setNroSerie(material.getNroSerie());
						inv.setOrden(orden);
						iInventarioDao.crear(inv);
					}
					matbod.setEstado(Estados.INACTIVO.getDescription());

				} catch (EntidadNoGrabadaException e) {
					throw new EntidadNoGrabadaException(e);
				}
			}
		}
		iControlBodegaDao.actualizar(control);
	}

	@Override
	public List<ReservacionesBodegaMateriales> getMaterialesbyProductoCuenta(Ctacliente cuenta, Producto producto) {
		StringBuilder sql = new StringBuilder("from ReservacionesBodegaMateriales o ");
		sql.append("where o.reservaOrdenBodega.orden.cuentaCliente=:cuenta ");
		sql.append("and o.producto=:producto ");
		sql.append("and o.estado=:est ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("cuenta", cuenta);
		query.setParameter("producto", producto);
		query.setParameter("est", Estados.ACTIVO.getDescription());
		List<ReservacionesBodegaMateriales> result = query.list();
		return result;
	}
}
