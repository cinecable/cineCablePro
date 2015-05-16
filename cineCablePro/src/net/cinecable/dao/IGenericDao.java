/*
 * IGenericDao.java
 * 
 * Copyright (c) 2011 MTOP.
 * Todos los derechos reservados.
 */

package net.cinecable.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.cinecable.exception.EntidadNoBorradaException;
import net.cinecable.exception.EntidadNoEncontradaException;
import net.cinecable.exception.EntidadNoGrabadaException;

public interface IGenericDao<T, PK extends Serializable> {

	/**
	 * Guardar un objeto en la base de datos.
	 * 
	 * @param o
	 *            the o
	 * 
	 * @return la llave primaria
	 * 
	 * @throws EntidadNoGrabadaException
	 *             the entidad no grabada exception
	 */
	void crear(T o) throws EntidadNoGrabadaException;

	/**
	 * Obtener un objeto almacenado utilizando su llave primaria.
	 * 
	 * @param id
	 *            llave primaria.
	 * 
	 * @return the T
	 * 
	 * @throws EntidadNoEncontradaException
	 *             the entidad no encontrada exception
	 */
	T recuperar(PK id) throws EntidadNoEncontradaException;

	/**
	 * Almacenar los cambios hechos a un objeto.
	 * 
	 * @param o
	 *            the o
	 * 
	 * @throws EntidadNoGrabadaException
	 *             the entidad no grabada exception
	 */
	void actualizar(T o) throws EntidadNoGrabadaException;

	/**
	 * Eliminar un objeto de la base de datos.
	 * 
	 * @param o
	 *            the o
	 * 
	 * @throws EntidadNoBorradaException
	 *             the entidad no borrada exception
	 */
	void eliminar(T o) throws EntidadNoBorradaException;

	/**
	 * Refresca un objeto de la base de datos.
	 * 
	 * @param o
	 *            the o
	 */
	void refrescar(T o);

	/**
	 * Obtiene todas las entidades.
	 * 
	 * @return the list< t>
	 */
	List<T> obtenerTodos();

	/**
	 * Contar todas las entidades.
	 * 
	 * @return the long
	 */
	Long contar();

	/**
	 * Encuentra una pagina de datos en la base.
	 * 
	 * @param firstRow
	 *            the first row
	 * @param maxResults
	 *            the max results
	 * 
	 * @return the list< t>
	 */
	List<T> encontrarPagina(final Integer firstRow, final Integer maxResults);

	/**
	 * Busca de acuerdo a una consulta y lista de parametros.
	 * 
	 * @param sql
	 *            the sql
	 * @param parametros
	 *            the parametros
	 * 
	 * @return the list< t>
	 */
	List<T> encontrarPorQuery(String sql, Map<String, Object> parametros);

}