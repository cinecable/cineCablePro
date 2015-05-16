package net.cinecable.service.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pojo.annotations.Costoservicio;
import pojo.annotations.Direccion;
import pojo.annotations.Estado;
import pojo.annotations.Tipooperacion;
import net.cinecable.dao.IBajaOrdenesDao;
import net.cinecable.dao.IDireccionDao;
import net.cinecable.dao.IMaterialesDao;
import net.cinecable.dao.IOrdenesDao;
import net.cinecable.dao.IReservacionBodegaMaterialesDao;
import net.cinecable.dao.IinventarioDao;
import net.cinecable.dm.BajaOrdenesDM;
import net.cinecable.enums.Estados;
import net.cinecable.enums.MovimientoInventario;
import net.cinecable.enums.TipoPropietario;
import net.cinecable.enums.TipoSolicitudes;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.BajaOrdenes;
import net.cinecable.model.base.Inventario;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.ReservacionesBodegaMateriales;
import net.cinecable.service.IBajaOrdenServices;

@Stateless
public class BajaOrdenService implements IBajaOrdenServices {

	@EJB
	IBajaOrdenesDao ibajaOrdenesDao;
	@EJB
	IReservacionBodegaMaterialesDao iReservacionesBodegaMaterial;
	@EJB
	IOrdenesDao iOrdenesDao;
	@EJB
	IMaterialesDao imaterialesDao;
	@EJB
	IinventarioDao iInventarioDao;
	@EJB
	IDireccionDao idireccionDao;

	@Override
	public void guardarBaja(BajaOrdenes baja, BajaOrdenesDM bajaOrden, List<Costoservicio> serviciosAdicc) throws EntidadNoGrabadaException {

		Ordenes ordenBaja = bajaOrden.getOrdenSeleccionada();

		if (ordenBaja.getTipoOperacion().getIdtipooperacion() == TipoSolicitudes.CambioDireccion.getDescripcion()) {

			Ordenes ordenReinstalacion = new Ordenes();
			ordenReinstalacion.setCuentaCliente(ordenBaja.getCuentaCliente());
			ordenReinstalacion.setFechaEjecucion(new Date());
			ordenReinstalacion.setObservacion("RECONEXION AUTOMATICA GENERADA");
			ordenReinstalacion.setMotivo(ordenBaja.getMotivo());
			// Tipo Operacion
			Tipooperacion operacionReinstalacion = new Tipooperacion();
			operacionReinstalacion.setIdtipooperacion(TipoSolicitudes.CambioDireccion.getDescripcion());

			ordenReinstalacion.setTipoOperacion(operacionReinstalacion);
			ordenReinstalacion.setProducto(ordenBaja.getProducto());
			iOrdenesDao.crear(ordenReinstalacion);
		}
		if (ordenBaja.getTipoOperacion().getIdtipooperacion() == TipoSolicitudes.ReconexionCambioDireccion.getDescripcion()) {
			Direccion dirAnt = idireccionDao.getDireccionbyEstadoYCuenta(ordenBaja.getCuentaCliente(), Estados.ACTIVO);
			Estado est = new Estado();
			est.setIdestado(Estados.INACTIVO.getDescription());
			dirAnt.setEstado(est);
			idireccionDao.actualizar(dirAnt);

			Direccion dirAct = idireccionDao.getDireccionbyEstadoYCuenta(ordenBaja.getCuentaCliente(), Estados.PENDIENTE);
			Estado est2 = new Estado();
			est.setIdestado(Estados.ACTIVO.getDescription());
			dirAnt.setEstado(est2);
			idireccionDao.actualizar(dirAct);
		}

		// crea el registro de la baja
		baja.setOrden(ordenBaja);
		ibajaOrdenesDao.crear(baja);
		//2 paso llamar funcion de cargos
		ibajaOrdenesDao.guardaCargos(ordenBaja, serviciosAdicc, baja.getUsuario());
		// actualiza los registros de las reservaciones de la bodegaas
		// materiales
		for (ReservacionesBodegaMateriales reserMaterial : bajaOrden.getMaterialCliente()) {
			// actualizar inventario
			Inventario inv = iInventarioDao.getInventarioActivobyMaterial(reserMaterial.getMaterial());
			inv.setEstado(Estados.INACTIVO.getDescription());
			iInventarioDao.actualizar(inv);
			// crear nuevo inventario
			inv.setIdInventario(null);
			inv.setEstado(Estados.ACTIVO.getDescription());
			inv.setFechaMovimiento(Calendar.getInstance().getTime());
			inv.setPropietario(TipoPropietario.CLI);
			inv.setNroUnidades(reserMaterial.getCantidad());
			inv.setTipMovimiento(MovimientoInventario.TRASPAS);
			iInventarioDao.crear(inv);
			iReservacionesBodegaMaterial.actualizar(reserMaterial);
		}

		for (ReservacionesBodegaMateriales reserMaterial : bajaOrden.getMaterialTecnico()) {
			// actualizar inventario

			Inventario inv = iInventarioDao.getInventarioActivobyMaterial(reserMaterial.getMaterial());
			if (!reserMaterial.isModificadoBaja()) {
				inv.setEstado(Estados.INACTIVO.getDescription());
				iInventarioDao.actualizar(inv);
			}
			// crear nuevo inventario
			inv.setIdInventario(null);
			inv.setEstado(Estados.ACTIVO.getDescription());
			inv.setFechaMovimiento(Calendar.getInstance().getTime());
			inv.setPropietario(TipoPropietario.BOD);
			inv.setNroUnidades(reserMaterial.getCantidad());
			inv.setTipMovimiento(MovimientoInventario.TRASPAS);
			iInventarioDao.crear(inv);

			// volver a dejar la cantidad de materiales a bodega
			Materiales mat = reserMaterial.getMaterial();
			mat.setCantidad(reserMaterial.getCantidad());
			imaterialesDao.actualizar(mat);
		}

		// actualzia el estado de la orden
		Estado estado = new Estado();
		estado.setIdestado(Estados.REALIZADA.getDescription());
		//consultar estado en la base con ese codigo
		//estado.setNombre(nombre);
		
		ordenBaja.setEstado(estado);
		ordenBaja.setFechaFinalizacion(new Date());
		iOrdenesDao.actualizar(ordenBaja);
	}
}
