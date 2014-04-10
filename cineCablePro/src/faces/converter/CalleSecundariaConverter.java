package faces.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import pojo.annotations.Callesecundaria;
import util.MessageUtil;
import bo.negocio.CallesecundariaBO;

public class CalleSecundariaConverter  implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
		if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(submittedValue);
                List<Callesecundaria> lisCallesecundaria= new CallesecundariaBO().ConsultarCalleSxCalleS(id);
                return lisCallesecundaria.get(0);
            } catch(Exception ex) {
            	ex.printStackTrace();
            	new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
            }
        }

        return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Object value) {
		if (value instanceof Callesecundaria){
			return String.valueOf(((Callesecundaria) value).getIdcallesecundaria());
		} else {
			return "";
		}
	}
}
