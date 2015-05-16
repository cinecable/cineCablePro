package net.cinecable.service;


import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Nodos;


@Local
public interface Inodos {
	public List<Nodos> getAllNodos();
}
