package net.cinecable.dao.imp;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.ICargoDao;
import net.cinecable.dao.IMonitoreoTrazaDao;
import net.cinecable.dao.IOrdenesDao;
import net.cinecable.dao.IReservacionBodegaMaterialesDao;
import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoSolicitudes;
import net.cinecable.model.base.MonitoreoTraza;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.ReservacionesBodegaMateriales;
import net.cinecable.service.IComandoActivacionService;
import net.cinecable.ws.WebServiceException;

import org.hibernate.Query;

import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Direccion;
import pojo.annotations.Estado;
import pojo.annotations.Persona;
import pojo.annotations.Prodservicio;

@Stateless
public class OrdenesDao extends GenericDao<Ordenes, Long> implements IOrdenesDao {

	public OrdenesDao() {
		super(Ordenes.class);
	}

	@EJB
	private IComandoActivacionService icomandoService;
	@EJB
	private IReservacionBodegaMaterialesDao iMaterialesDao;
	@EJB
	private ICargoDao icargoDao;
	@EJB
	private IMonitoreoTrazaDao imonTrazaDao;

	@Override
	public List<Ordenes> consultaOrdenesporEstado(Estados estado) {
		StringBuilder sql = new StringBuilder();
		sql.append("select o from Ordenes o ");
		if (estado != null) {
			sql.append("where o.estado.idestado=:estado order by o.idOrdenes");
		}
		Query query = em.createQuery(sql.toString());

		if (estado != null) {
			query.setParameter("estado", estado.getDescription());
		}
		List<Ordenes> lres = query.list();
		return lres;
	}

	@Override
	public List<Ordenes> consultaOrdenesAsignadaTecnico(Persona Tecnico, Estados... estados) {
		StringBuilder sql = new StringBuilder();
		sql.append("select o from Ordenes o,OrdenesAsignaciones e ");
		sql.append("where not exists(from ReservacionesOrdenesBodega r where r.orden=o) ");
		sql.append("and o=e.orden ");
		sql.append(" and o.idproductoprincipal = 0 ");
		sql.append("and e.tecnico=:tec and o.estado.idestado in :estados order by o.idOrdenes ");
		
		Query query = em.createQuery(sql.toString());
		query.setParameter("tec", Tecnico);
		Integer lest[] = new Integer[estados.length];
		for (int i = 0; i < estados.length; i++) {
			lest[i] = estados[i].getDescription();
		}
		List<Ordenes> result = null;
		try {
			query.setParameterList("estados", lest);
			result = query.list();
		} catch (Exception e) {
			Integer lest2[] = new Integer[estados.length];
			for (int i = 0; i < estados.length; i++) {
				lest2[i] = estados[i].getDescription();
			}
			query.setParameterList("estados", lest2);
			result = query.list();
		}
		
		for (Ordenes orden : result) {
			orden.getCuentaCliente().getClientes().getApellido1();
			if(orden.getCuentaCliente() == null) {
				orden.setCuentaCliente(new Ctacliente(0, null, new Clientes()));
			}else{
				if(orden.getCuentaCliente().getClientes() == null){
					orden.getCuentaCliente().setClientes(new Clientes());
				}
			}
		}
		
		return result;
	}

	@Override
	public List<Ordenes> consultaOrdenesAsignadaTecnicoReporte(Persona Tecnico, Estados... estados) {
		StringBuilder sql = new StringBuilder();
		sql.append("select o from Ordenes o,OrdenesAsignaciones e ");
		sql.append("where exists(from ReservacionesOrdenesBodega r where r.orden=o) ");
		sql.append("and o=e.orden ");
		sql.append("and e.tecnico=:tec and o.estado.idestado in :estados order by o.idOrdenes ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tec", Tecnico);
		Integer lest[] = new Integer[estados.length];
		for (int i = 0; i < estados.length; i++) {
			lest[i] = estados[i].getDescription();
		}
		List<Ordenes> result = null;
		try {
			query.setParameterList("estados", lest);
			result = query.list();
		} catch (Exception e) {
			Integer lest2[] = new Integer[estados.length];
			for (int i = 0; i < estados.length; i++) {
				lest2[i] =  estados[i].getDescription();
			}
			query.setParameterList("estados", lest2);
			result = query.list();
		}
		
		for (Ordenes orden : result) {
			orden.getCuentaCliente().getClientes().getApellido1();
			if(orden.getCuentaCliente() == null) {
				orden.setCuentaCliente(new Ctacliente(0, null, new Clientes()));
			}else{
				if(orden.getCuentaCliente().getClientes() == null){
					orden.getCuentaCliente().setClientes(new Clientes());
				}
			}
		}
		
		return result;
	}

	@Override
	public List<Ordenes> consultaOrdenesAsignadaTecnicoReporte2(Persona Tecnico, Estados... estados) {
		StringBuilder sql = new StringBuilder();
		sql.append("select o from Ordenes o,OrdenesAsignaciones e ");
		sql.append("where o=e.orden ");
		sql.append("and e.tecnico=:tec and o.estado.idestado in :estados order by o.idOrdenes ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tec", Tecnico);
		Integer lest[] = new Integer[estados.length];
		for (int i = 0; i < estados.length; i++) {
			lest[i] = estados[i].getDescription();
		}
		List<Ordenes> result = null;
		try {
			query.setParameterList("estados", lest);
			result = query.list();
		} catch (Exception e) {
			Integer lest2[] = new Integer[estados.length];
			for (int i = 0; i < estados.length; i++) {
				lest2[i] = estados[i].getDescription();
			}
			query.setParameterList("estados", lest2);
			result = query.list();
		}
		return result;
	}

	@Override
	public List<Ordenes> consultaOrdenesporEstadoTipo(Estados estado, Long tipOperacion) {
		List<Ordenes> listaOrdenes = new ArrayList<Ordenes>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select o from Ordenes o ");
		sql.append(" left join fetch o.cuentaCliente as p ");
		sql.append(" where o.idproductoprincipal = 0 ");

		if (estado != null && tipOperacion != null) {
			sql.append("and o.estado.idestado = :estado and o.tipoOperacion.idtipooperacion=:tipop order by o.idOrdenes");
		}
		Query query = em.createQuery(sql.toString());

		if (estado != null && tipOperacion != null) {
			query.setParameter("estado", estado.getDescription());
			query.setParameter("tipop", tipOperacion.intValue());
		}
		listaOrdenes = query.list();
		
		if(listaOrdenes != null && listaOrdenes.size() == 0){
			listaOrdenes = new ArrayList<Ordenes>();
		}
		
		for (Ordenes orden : listaOrdenes) {
			orden.getCuentaCliente().getClientes().getApellido1();
			if(orden.getCuentaCliente() == null) {
				orden.setCuentaCliente(new Ctacliente(0, null, new Clientes()));
			}else{
				if(orden.getCuentaCliente().getClientes() == null){
					orden.getCuentaCliente().setClientes(new Clientes());
				}
			}
		}
	
		return listaOrdenes;
	}

 
	public boolean consultaOrdenesxStsTipoCli(int tipOperacion, String idDoc)  {
		
		if (idDoc.isEmpty() || idDoc.equals("")) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select o from Ordenes o  ");
		sql.append(" where o.cuentaCliente.clientes.idcliente=:idDoc ");
		if ( tipOperacion !=0) {
			sql.append("and o.estado.idestado in (:estadoCtaClie) and o.tipoOperacion.idtipooperacion=:tipop order by o.idOrdenes");
		}
		Query query = em.createQuery(sql.toString());

		List<Integer> estado = Arrays.asList(Estados.ACTIVO.getDescription(), Estados.INGRESADOPAGADO.getDescription(),3);
		if (estado != null && tipOperacion != 0) {
			query.setParameterList("estadoCtaClie", estado);
			query.setParameter("tipop", tipOperacion);
			query.setParameter("idDoc", idDoc);
		}
		
		if (query.list().size() > 0) {
			return true;
		} else {
			return false;
		}
			
	}
	
	@Override
	public Long CountByFechaTipoOpe(Date fecha, int tipOpe) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from Ordenes o ");
		sql.append("where o.fechaEjecucion= :fec ");
		sql.append("and o.tipoOperacion.idtipooperacion= :tip");
		Query query = em.createQuery(sql.toString());
		query.setParameter("fec", fecha);
		query.setParameter("tip", tipOpe);
		Long totalOrdenes = (Long) query.uniqueResult();
		return totalOrdenes;
	}

	@Override
	public List<Ordenes> consultaOrdenesTecnico(Persona Tecnico, Estados... estado) {
		StringBuilder sql = new StringBuilder();
		sql.append("select o from Ordenes o, OrdenesAsignaciones p ");
		sql.append("where p.orden=o ");
		sql.append("and p.tecnico=:tec and o.estado.idestado in :estados order by o.idOrdenes ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tec", Tecnico);
		Integer lest[] = new Integer[estado.length];
		for (int i = 0; i < estado.length; i++) {
			lest[i] = estado[i].getDescription();
		}

		List<Ordenes> result = null;
		try {
			query.setParameterList("estados", lest);
			result = query.list();
		} catch (Exception e) {
			Integer lest2[] = new Integer[estado.length];
			for (int i = 0; i < estado.length; i++) {
				lest2[i] = estado[i].getDescription();
			}
			query.setParameterList("estados", lest2);
			result = query.list();
		}
		for(Ordenes orden : result){
			orden.getCuentaCliente().getClientes().getNombre1();
			/*if(orden.getCuentaCliente() != null ){
				if(orden.getCuentaCliente().getClientes() == null){
					orden.getCuentaCliente().setClientes(new Clientes()); 
				}
			}else{
				orden.setCuentaCliente(new Ctacliente(0, null, new Clientes()));
			}*/
		}

		return result;
	}

	@Override
	public void insOperacion(List<Direccion> listadireccion, Ordenes orden) throws Exception {

		/**
		 * Insertamos direcciones en caso de que sea por tipo de cambio de
		 * direccion
		 */

		int estadoOrden = 0;

		try {

			for (int i = 0; i < listadireccion.size(); i++) {
				if (listadireccion.get(i).getReferenciadir() != null && listadireccion.get(i).getReferenciadir().getReferencia() != null && !listadireccion.get(i).getReferenciadir().getReferencia().equals("")) {
					listadireccion.get(i).getReferenciadir().setIdcuenta(orden.getCuentaCliente().getIdcuenta());
					em.saveOrUpdate(listadireccion.get(i).getReferenciadir());
				}

				Estado estadoDireccion = new Estado();
				estadoDireccion.setIdestado(Estados.PENDIENTE.getDescription());
				listadireccion.get(i).setEstado(estadoDireccion);
				if (listadireccion.get(i).getReferenciadir() != null && listadireccion.get(i).getReferenciadir().getIdreferencia() == 0)
					listadireccion.get(i).setReferenciadir(null);
				listadireccion.get(i).setCorrespondencia("N");
				listadireccion.get(i).setVineta("N");
				listadireccion.get(i).setCtacliente(orden.getCuentaCliente());
				if (listadireccion.get(i).getCalleprincipal() == null || listadireccion.get(i).getCalleprincipal().getIdcalleprincipal() == 0)
					listadireccion.get(i).setCalleprincipal(null);
				if (listadireccion.get(i).getCallesecundaria() == null || listadireccion.get(i).getCallesecundaria().getIdcallesecundaria() == 0)
					listadireccion.get(i).setCallesecundaria(null);
				em.saveOrUpdate(listadireccion.get(i));
			}

			if (orden.getTipoOperacion().getIdtipooperacion() == TipoSolicitudes.Cancelacion.getDescripcion()) {
				Estado estado = new Estado();
				estado.setIdestado(Estados.INACTIVO.getDescription());
				orden.getProducto().setEstado(estado);
				em.saveOrUpdate(orden.getProducto());
			}

			for (Prodservicio prodServicio : orden.getProducto().getProducto().getProdServicio()) {
				Calendar c = Calendar.getInstance();
				c.setTime(orden.getFechaEjecucion());

				/**
				 * en servicios esta el campo idcontrolador. si es diferente de
				 * 0 es manual caso contrario es automatico
				 */
				if (prodServicio.getServicio().getIdcontrolador().equals("0")) {
					estadoOrden = Estados.PENDIENTE.getDescription();
					continue;
				}
				// Activaciones
				if (orden.getTipoOperacion().getIdtipooperacion() == TipoSolicitudes.Reconexion.getDescripcion()) {
					List<ReservacionesBodegaMateriales> materiales = this.iMaterialesDao.getMaterialesbyProductoCuenta(orden.getCuentaCliente(), prodServicio.getProducto());

					for (ReservacionesBodegaMateriales mat : materiales) {
						MonitoreoTraza monTraza = new MonitoreoTraza();
						try {
							icomandoService.desbloquearDipositivo(mat.getMaterial(), "Reactivación");
							monTraza.setActivadoSinError(true);
							monTraza.setMenError("OK");
						} catch (WebServiceException e) {
							estadoOrden = Estados.PENDIENTE.getDescription();
							String err = e.getFaultString().replace("com.promptlink.ips.web.webservice.WebServiceException:", "");
							monTraza.setActivadoSinError(false);
							monTraza.setMenError(err);
						} catch (RemoteException e2) {
							estadoOrden = Estados.PENDIENTE.getDescription();
						}
						monTraza.setCliente(orden.getCuentaCliente().getClientes());
						monTraza.setCuentaCliente(orden.getCuentaCliente());
						monTraza.setMaterial(mat.getMaterial());
						monTraza.setOrden(orden);
						monTraza.setTiempoComandoEjecucion(Calendar.getInstance());
						this.imonTrazaDao.crear(monTraza);
					}
				}

				// Cancelaciones
				if (orden.getTipoOperacion().getIdtipooperacion() == TipoSolicitudes.Cancelacion.getDescripcion()) {
					List<ReservacionesBodegaMateriales> materiales = this.iMaterialesDao.getMaterialesbyProductoCuenta(orden.getCuentaCliente(), prodServicio.getProducto());

					for (ReservacionesBodegaMateriales mat : materiales) {
						MonitoreoTraza monTraza = new MonitoreoTraza();
						try {
							icomandoService.borrarDispositivo(mat.getMaterial(), "Bloqueo");
							monTraza.setActivadoSinError(true);
							monTraza.setMenError("OK");
						} catch (WebServiceException e) {
							estadoOrden = Estados.PENDIENTE.getDescription();
							String err = e.getFaultString().replace("com.promptlink.ips.web.webservice.WebServiceException:", "");
							monTraza.setActivadoSinError(false);
							monTraza.setMenError(err);
						} catch (RemoteException e2) {
							estadoOrden = Estados.PENDIENTE.getDescription();
						}
						monTraza.setCliente(orden.getCuentaCliente().getClientes());
						monTraza.setCuentaCliente(orden.getCuentaCliente());
						monTraza.setMaterial(mat.getMaterial());
						monTraza.setOrden(orden);
						monTraza.setTiempoComandoEjecucion(Calendar.getInstance());
						this.imonTrazaDao.crear(monTraza);
					}
				}

				if (!prodServicio.getServicio().getIdcontrolador().equals("0")) {
					//2 paso llamar funcion de cargos
					String result = icargoDao.generarCargos(orden.getCuentaCliente().getIdcuenta(), new Date(), orden.getProducto().getEstado().getIdestado(), orden.getProducto().getProducto().getIdproducto(), false, orden.getUsuario().getIdusuario());
					if (!result.equals("OK")) {
						throw new Exception(result);
					}
				}

			}

			Estado estado = new Estado();
			estado.setIdestado(estadoOrden != 0 ? estadoOrden : Estados.REALIZADA.getDescription());
			orden.setEstado(estado);
			em.saveOrUpdate(orden);

		} catch (Exception e) {
			throw new Exception("Error al insertar la orden: " + e.getMessage());
		}

	}

}
