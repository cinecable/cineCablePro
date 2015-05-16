package bean.controladores;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import net.cinecable.dao.IAsignacionSolucitudDao;
import net.cinecable.dao.IOrdenesDao;
import net.cinecable.model.base.ParamAsignacionOrden;

@ManagedBean
@ViewScoped
public class CalendarOrdenesBean implements Serializable {
	
	private static final long serialVersionUID = -4510020124302752212L;
	
	private Date fecha;
	private String strSpecialDates;
	private boolean mostrarCalendar;
	private int intTipoOperacion;
	private Map<String, Integer> lisMes = new LinkedHashMap<String, Integer>();
	private Map<String, Integer> lisAnio = new LinkedHashMap<String, Integer>();
	
	@EJB
	private IAsignacionSolucitudDao iAsignacion;
	@EJB
	private IOrdenesDao iOrdenesDao;
	
	public CalendarOrdenesBean() {
		fecha = null;
		mostrarCalendar = false;
		strSpecialDates = "";
		intTipoOperacion = 0;
		lisMes = new LinkedHashMap<String, Integer>();
		lisAnio = new LinkedHashMap<String, Integer>();
	}
	
	public void inicializarMeses(){
		lisMes = new LinkedHashMap<String, Integer>();
		lisMes.put("Enero", 1);
		lisMes.put("Febrero", 2);
		lisMes.put("Marzo", 3);
		lisMes.put("Abril", 4);
		lisMes.put("Mayo", 5);
		lisMes.put("Junio", 6);
		lisMes.put("Julio", 7);
		lisMes.put("Agosto", 8);
		lisMes.put("Septiembre", 9);
		lisMes.put("Octubre", 10);
		lisMes.put("Noviembre", 11);
		lisMes.put("Diciembre", 12);
	}
	
	public void inicializarAnios(){
		lisAnio = new LinkedHashMap<String, Integer>();
		int anio = Calendar.getInstance().get(Calendar.YEAR);
		for(int i = anio - 10;i <= anio + 10;i++){
			lisAnio.put(String.valueOf(i), i);
		}
	}
	
	public void mostrarEventosAgendados(int idTipoOperacion, int mes){
		
		if(idTipoOperacion > 0){
			this.intTipoOperacion = idTipoOperacion;
			Calendar primeraFechaMes = Calendar.getInstance();
			primeraFechaMes.setTime(fecha);
			//primeraFechaMes.set(primeraFechaMes.get(Calendar.YEAR),mes-1,1,0,0,0);
			
			Calendar ultimaFechaMes = Calendar.getInstance();
			ultimaFechaMes.set(primeraFechaMes.get(Calendar.YEAR),primeraFechaMes.get(Calendar.MONTH),primeraFechaMes.getMaximum(Calendar.DAY_OF_MONTH),0,0,0);
			
			List<ParamAsignacionOrden> lisParamAsignacionOrden = new ArrayList<ParamAsignacionOrden>();
			lisParamAsignacionOrden = iAsignacion.AsigXfechas(idTipoOperacion, 
															primeraFechaMes.getTime(), 
															ultimaFechaMes.getTime());
	
			if(lisParamAsignacionOrden == null || lisParamAsignacionOrden.isEmpty()){
				fecha = null;
				RequestContext.getCurrentInstance().execute("alert('No se han parametrizado ordenes para este mes');");
			}
			
			//crea lista de días agendados en el mes
			List<String> lisDiasAgendados = new ArrayList<String>();
			for(ParamAsignacionOrden evento : lisParamAsignacionOrden){
				Calendar calFechaAsignacion = Calendar.getInstance();
				calFechaAsignacion.setTime(evento.getFechaasignacion());
				
				lisDiasAgendados.add(calFechaAsignacion.get(Calendar.YEAR)+"/"+(calFechaAsignacion.get(Calendar.MONTH)+1)+"/"+calFechaAsignacion.get(Calendar.DAY_OF_MONTH));
			}
			
			//pasa días a una cadena separada por comas
			Iterator<String> itrDates = lisDiasAgendados.iterator();
			strSpecialDates = "";
			while(itrDates.hasNext()){
				String date = itrDates.next();
				strSpecialDates += date;
				if(itrDates.hasNext()){
					strSpecialDates += ",";
				}
			}
		}
	}
	
	public void validaFecha() {
		List<String> meses = iAsignacion.DiasxMes(intTipoOperacion, new SimpleDateFormat("MM").format(fecha));
		Long total = iOrdenesDao.CountByFechaTipoOpe(fecha, intTipoOperacion);
		ParamAsignacionOrden param = iAsignacion.ValidaDias(intTipoOperacion,fecha);
		if (param == null) {
			String mensaje = "No se a parametrizado ordenes para este dia \\n";
			String mensajeDias = "Los días disponibles para el mes seleccionado son: ";
			String dias = "";
			for (int i = 0; i < meses.size(); i++) {
				if (meses.size() == (i + 1))
					dias += meses.get(i);
				else
					dias += meses.get(i) + "-";
			}
			if (dias != null && !dias.equals("")) {
				RequestContext.getCurrentInstance().execute(
						"alert('" + mensaje + mensajeDias + "\\n" + dias
								+ "');");
				fecha = null;
				return;
			} else {
				RequestContext.getCurrentInstance().execute(
						"alert('" + mensaje + "');");
				return;
			}
		}
		if (total >= param.getNoasignaciones()) {
			RequestContext.getCurrentInstance().execute(
					"alert('No se pueden asignar mas solicitudes este dia');");
			fecha = null;
		}

	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getStrSpecialDates() {
		return strSpecialDates;
	}

	public void setStrSpecialDates(String strSpecialDates) {
		this.strSpecialDates = strSpecialDates;
	}

	public boolean isMostrarCalendar() {
		return mostrarCalendar;
	}

	public void setMostrarCalendar(boolean mostrarCalendar) {
		this.mostrarCalendar = mostrarCalendar;
	}

	public int getIntTipoOperacion() {
		return intTipoOperacion;
	}

	public void setIntTipoOperacion(int intTipoOperacion) {
		this.intTipoOperacion = intTipoOperacion;
	}

	public Map<String, Integer> getLisMes() {
		return lisMes;
	}

	public void setLisMes(Map<String, Integer> lisMes) {
		this.lisMes = lisMes;
	}

	public Map<String, Integer> getLisAnio() {
		return lisAnio;
	}

	public void setLisAnio(Map<String, Integer> lisAnio) {
		this.lisAnio = lisAnio;
	}
	
	
}
