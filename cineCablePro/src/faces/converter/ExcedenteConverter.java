package faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import pojo.annotations.Excedentes;
import util.MessageUtil;
import bo.negocio.ExcedentesBO;

public class ExcedenteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
		if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(submittedValue);
                Excedentes excedentes= new ExcedentesBO().getExcedenteById(id);
                return excedentes;
            } catch(Exception ex) {
            	ex.printStackTrace();
            	new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
            }
        }

        return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Object value) {
		if (value instanceof Excedentes){
			return String.valueOf(((Excedentes) value).getIdexcedente());
		} else {
			return "";
		}
	}

}
