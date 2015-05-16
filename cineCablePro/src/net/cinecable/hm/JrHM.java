package net.cinecable.hm;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.cinecable.model.base.Auxiliar;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Session;
import org.hibernate.mapping.AuxiliaryDatabaseObject;

import util.HibernateUtil;

@ManagedBean(name = "jrreport")
@SessionScoped
public class JrHM {

	private JasperPrint jasperPrint;

	private Session session = HibernateUtil.getSessionFactory().openSession();
	@SuppressWarnings("deprecation")
	private Connection connection = session.connection();

	private String reportFile, format = "pdf";

	private Map<String, Object> params;
	
	private List<Auxiliar> auxiliar;

	public String getReportFile() {
		return reportFile;
	}

	public void setReportFile(String reportFile) {
		this.reportFile = "/reportes/" + reportFile;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	

	public List<Auxiliar> getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(List<Auxiliar> auxiliar) {
		this.auxiliar = auxiliar;
	}

	private void reportBuilder() throws JRException {
		if (params == null)
			return;
		String Url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/logoCinecableReport.jpg");
		params.put("url", Url);
		String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath(this.reportFile);
		jasperPrint = JasperFillManager.fillReport(report, params, connection);
	}
	
	private void reportBuilderDataSourse() throws JRException {
		String Url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/logoCinecableReport.jpg");
		params.put("url", Url);
		String report = FacesContext.getCurrentInstance().getExternalContext().getRealPath(this.reportFile);
		JRDataSource datasource=new JRBeanCollectionDataSource(auxiliar);
		jasperPrint = JasperFillManager.fillReport(report, params,datasource);
	}

	public void export() throws JRException {
		if (this.format.equals("pdf"))
			exportToPdf();
	}

	private void exportToPdf() throws JRException {
		reportBuilder();
		if (params == null)
			return;
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		ServletOutputStream servletStream = null;
		httpServletResponse.reset();
		try {
			servletStream = httpServletResponse.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
		FacesContext.getCurrentInstance().responseComplete();

	}

}
