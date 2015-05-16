package net.cinecable.service.imp;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.IOrdenesAsignacioneDao;
import net.cinecable.dao.IOrdenesDao;
import net.cinecable.dao.ITecnicoSupervisorDao;
import net.cinecable.enums.Estados;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.OrdenesAsignaciones;
import net.cinecable.model.base.TecnicoSupervisor;
import net.cinecable.service.IOrdenesAsignacionesService;

@Stateless
public class OrdenesAsignacionesService implements IOrdenesAsignacionesService {

	@EJB
	IOrdenesAsignacioneDao iordenesDao;

	@EJB
	IOrdenesDao iordenDao;

	@EJB
	ITecnicoSupervisorDao itecDao;

	@Override
	public void guardarInfoAsignacion(List<OrdenesAsignaciones> list, List<Ordenes> ordenes, TecnicoSupervisor tecsupinfo) {
		try {
			for (OrdenesAsignaciones ord : list) {
				if (ord.getEstado() == Estados.ASIGNADA.getDescription()) {
					iordenesDao.crear(ord);
				} else if (ord.getEstado() == Estados.ACTIVO.getDescription() || ord.getEstado() == Estados.INACTIVO.getDescription()) {
					if (ord.getIdOrdAsignacion() != null)
						iordenesDao.actualizar(ord);
				}
			}

			for (Ordenes orden : ordenes) {
				iordenDao.actualizar(orden);
			}

			tecsupinfo.setFechaAsignacion(new Date());
			itecDao.crear(tecsupinfo);
		} catch (EntidadNoGrabadaException e) {

		}
	}

	@Override
	public OrdenesAsignaciones getOrdenbyId(Ordenes orden) {
		return iordenesDao.getOrdenbyId(orden);
	}

}
