package bean.controladores;

import global.Parametro;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.LazyScheduleModel;

import pojo.annotations.Evento;
import util.MessageUtil;
import bo.negocio.EventoBO;

@ManagedBean
@ViewScoped
public class AgendaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7768359561218674115L;
	private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private String theme;
    private TimeZone timezone = TimeZone.getDefault();
    private List<Evento> lisEvento;
    private Evento evento;

	public AgendaBean() {
		evento = new Evento();
		consultarEventos();
    }
	
	@SuppressWarnings("serial")
	private void consultarEventos(){
		try
		{
			lazyEventModel = new LazyScheduleModel() {
				public void loadEvents(Date start, Date end) {
	                clear();
	                EventoBO eventoBO = new EventoBO();
	                lisEvento = eventoBO.lisEvento(start, end);
	                for(Evento even : lisEvento){
	                	long diff = (even.getFechahasta().getTime()-even.getFechadesde().getTime());
	                	boolean isAllDay = (diff==(Parametro.DAY_IN_MILLISECONDS-1));
	                	DefaultScheduleEvent defaultScheduleEvent = new DefaultScheduleEvent(even.getTitulo(), even.getFechadesde(), even.getFechahasta(), isAllDay);
	                	defaultScheduleEvent.setData(even);
	                	addEvent(defaultScheduleEvent);
	                }
	            }
	    	};
		}catch(Exception re){
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}
    
	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
    }
	
	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}
	
	public ScheduleEvent getEvent() {
		return event;
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getTheme() {
		return theme;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}
	
	public TimeZone getTimezone() {
		return timezone;
	}
	
    public void addEvent(ActionEvent actionEvent) {
        grabarEvento();
    }
    
    private void grabarEvento(){
    	try{
    		EventoBO eventoBO = new EventoBO();
    		
    		Calendar endDate = Calendar.getInstance();
    		endDate.setTime(event.getEndDate());
    		if(event.isAllDay()){
    			endDate.setTime(event.getStartDate());
    			endDate.set(Calendar.DATE, endDate.get(Calendar.DATE)+1);
    			endDate.set(Calendar.MILLISECOND, endDate.get(Calendar.MILLISECOND)-1);
    		}
    		
    		Evento eventoTmp = (event.getData()==null)?new Evento(0,null,null,null,null,0,0,0):(Evento)event.getData();
    		eventoTmp.setTitulo(event.getTitle());
    		eventoTmp.setDescripcion(evento.getDescripcion());
    		eventoTmp.setFechadesde(event.getStartDate());
    		eventoTmp.setFechahasta(endDate.getTime());
    		if(event.getId() == null){
                lazyEventModel.addEvent(event);
                eventoBO.newEvento(eventoTmp);
            }else{
                lazyEventModel.updateEvent(event);
                eventoBO.updateEvento(eventoTmp);
            }
    		event = new DefaultScheduleEvent();
    		new MessageUtil().showInfoMessage("Exito!", "Registro completo!");
    	}catch(Exception e){
    		e.printStackTrace();
    		new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
    	}
    }
    
    public void eliminarEvento(ActionEvent actionEvent){
    	if(event.getId() != null){
    		try {
	            lazyEventModel.updateEvent(event);
	            Evento evento = (Evento)event.getData();
	            
	            EventoBO eventoBO = new EventoBO();
	            eventoBO.deleteEvento(evento.getIdevento());
	            new MessageUtil().showInfoMessage("Exito!", "Registro eliminado!");
    		} catch(Exception e){
    			e.printStackTrace();
    			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
    		}
        }
    }
    
    public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
    	event = selectEvent.getScheduleEvent();
    	evento = event.getData() == null ? new Evento() :  (Evento) event.getData();
    }
	
	public void onDateSelect(DateSelectEvent selectEvent) {
		event = new DefaultScheduleEvent("Evento", selectEvent.getDate(), selectEvent.getDate());
		evento = event.getData() == null ? new Evento() :  (Evento) event.getData();
	}
	
	public void onEventMove(ScheduleEntryMoveEvent event) {
		new MessageUtil().showInfoMessage("Evento ha sido modificado!", "Selecciona el evento y guarda tus cambios!");
	}
	
	public void onEventResize(ScheduleEntryResizeEvent event) {
		new MessageUtil().showInfoMessage("Evento ha sido modificado!", "Selecciona el evento y guarda tus cambios!");
	}
    
}
