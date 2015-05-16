package net.cinecable.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.dm.ImprimirReporteDm;
import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoPersona;
import net.cinecable.hm.JrHM;
import net.cinecable.service.IOrdenesService;
import net.cinecable.service.IPersonaServices;

@ManagedBean(name = "imprimirReporteController")
@ViewScoped
public class ImprimirReporteController extends BaseController {
	@EJB
	IPersonaServices ipersonaService;
	@EJB
	IOrdenesService iOrdenesService;

	@PostConstruct
	public void init() {
		imprimirReporteDm.setCodTecnicoSeleccionado(null);
		imprimirReporteDm.setListOrdenes(null);
		imprimirReporteDm.setTecnicos(ipersonaService.getListaPersonasbyTipo(TipoPersona.TEC));
	}

	public void consultarOrdenes() {
		if (imprimirReporteDm.getTecnicoSeleccionado() == null) {
			showInfo("Repore de Ordenes", "debe seleccionar un técnico");
			return;
		}
		imprimirReporteDm.setListOrdenes(iOrdenesService.consultaOrdenesAsignadaTecnicoReporte2(imprimirReporteDm.getTecnicoSeleccionado(), Estados.PROCESO, Estados.ASIGNADA));
	}

	@ManagedProperty(value = "#{imprimirReporteDm}")
	private ImprimirReporteDm imprimirReporteDm;
	@ManagedProperty(value = "#{jrreport}")
	private JrHM report;

	public ImprimirReporteDm getImprimirReporteDm() {
		return imprimirReporteDm;
	}

	public void setImprimirReporteDm(ImprimirReporteDm imprimirReporteDm) {
		this.imprimirReporteDm = imprimirReporteDm;
	}

	public void showReport() {
		report.setReportFile("rptvisitatecnica.jasper");
		if (imprimirReporteDm.getTecnicoSeleccionado() == null)
			return;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codtenico", (long) imprimirReporteDm.getTecnicoSeleccionado().getIdpersona());
		report.setParams(params);
	}

	public JrHM getReport() {
		return report;
	}

	public void setReport(JrHM report) {
		this.report = report;
	}

}
