package dao.datos;

import net.cinecable.model.base.OrdenesAsignaciones;

import org.hibernate.Session;

public class OrdasignacionesDAO {
	
	public void actualizarOrdenesAsignaciones(Session session, OrdenesAsignaciones ordenesAsignaciones) throws Exception {
		session.update(ordenesAsignaciones);
	}
	
	public void ingresarOrdasignaciones(Session session, OrdenesAsignaciones ordenesAsignaciones) throws Exception {
		session.save(ordenesAsignaciones);
	}

}
