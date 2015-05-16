package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Tipomenu;

@Local
public interface ITipoMenuServices {
	List<Tipomenu> getTipoMenu();
}
