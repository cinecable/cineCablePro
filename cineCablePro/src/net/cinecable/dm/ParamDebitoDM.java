package net.cinecable.dm;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import pojo.annotations.Bancos;

@ManagedBean(name="paramDebitoDM")
public class ParamDebitoDM {
	private List<Bancos> bancos= new ArrayList<Bancos>();

	public List<Bancos> getBancos() {
		return bancos;
	}

	public void setBancos(List<Bancos> bancos) {
		this.bancos = bancos;
	}
	
}
