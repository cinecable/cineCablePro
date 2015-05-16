package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class ConsultaCtasProd implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6499159436503566895L;

	
    private List<String> listaDeNombres;
	public ConsultaCtasProd() {
		// TODO Auto-generated constructor stub
		listaDeNombres = new ArrayList<String>();
        listaDeNombres.add("Juan");
        listaDeNombres.add("Maria");
        listaDeNombres.add("Alberto");
        listaDeNombres.add("Lucia");
	}
	public List<String> getListaDeNombres() {
        return listaDeNombres;
    }
}
