package net.cinecable.hm;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.service.IComandoActivacionService;

@ManagedBean(name = "monitoreoEjecucionHm")
@SessionScoped
public class MonitoreoEjecucionComandoHM {

	@EJB
	IComandoActivacionService icomandoActivacion;

}
