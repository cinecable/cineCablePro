package faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import pojo.annotations.Fpago;
import util.MessageUtil;
import bo.negocio.FpagoBO;

public class FpagoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
		if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(submittedValue);
                Fpago fpago= new FpagoBO().getFpagoById(id);
                return fpago;
            } catch(Exception ex) {
            	ex.printStackTrace();
            	new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
            }
        }

        return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Object value) {
		if (value instanceof Fpago){
			return String.valueOf(((Fpago) value).getIdfpago());
		} else {
			return "";
		}
	}
}
