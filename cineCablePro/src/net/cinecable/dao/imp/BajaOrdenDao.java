package net.cinecable.dao.imp;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.IBajaOrdenesDao;
import net.cinecable.dao.ICargoDao;
import net.cinecable.enums.Estados;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.BajaOrdenes;
import net.cinecable.model.base.Ordenes;
import pojo.annotations.Cargos;
import pojo.annotations.Costoservicio;
import pojo.annotations.Estado;
import pojo.annotations.Prodservicio;
import pojo.annotations.Usuario;

@Stateless
public class BajaOrdenDao extends GenericDao<BajaOrdenes, Long> implements IBajaOrdenesDao {

	public BajaOrdenDao() {
		super(BajaOrdenes.class);
	}

	@EJB
	private ICargoDao icargoDao;

	public void guardaCargos(Ordenes orden, List<Costoservicio> cargosAdicionales, Usuario usuario) throws EntidadNoGrabadaException {

		Estado estado = new Estado();
		estado.setIdestado(Estados.REALIZADA.getDescription());
		orden.setEstado(estado);
		orden.getProducto().setEstado(estado);
		em.saveOrUpdate(orden.getProducto());
		em.saveOrUpdate(orden);

		for (Costoservicio costo : cargosAdicionales) {
			Cargos cargo = new Cargos();
			cargo.setValcargo(costo.getCosto());
			cargo.setNivel((short) 1);
			cargo.setUsuario(orden.getUsuario());
			Estado estadoCargo = new Estado();
			estadoCargo.setIdestado(Estados.ACTIVO.getDescription());
			cargo.setEstado(estadoCargo);
			cargo.setFecha(new Date());
			cargo.setEmpresa(orden.getEmpresa());
			cargo.setMotivo(costo.getServicio().getNombre());
			cargo.setValpendiente(costo.getCosto());
			cargo.setValbase(costo.getCosto());
			cargo.setDescuento(0F);
			cargo.setIdrubropadre(0);
			cargo.setProducto("Exceso de Materiales");
			icargoDao.crear(cargo);
		}

		for (Prodservicio prodServicio : orden.getProducto().getProducto().getProdServicio()) {
			if (prodServicio.getServicio().getIdcontrolador().equals("0")) {
				//3 paso llamar funcion de cargos
				String result = icargoDao.generarCargos(orden.getCuentaCliente().getIdcuenta(), new Date(), orden.getProducto().getEstado().getIdestado(), orden.getProducto().getProducto().getIdproducto(), false, (orden.getUsuario() == null ? 1 : orden.getUsuario().getIdusuario()));
				if (!result.equals("OK")) {
					throw new EntidadNoGrabadaException(result);
				}
			}
		}
	}

}
