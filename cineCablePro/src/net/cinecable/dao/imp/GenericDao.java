/*
 * GenericDao.java
 * 
 * Copyright (c) 2011 MTOP.
 * Todos los derechos reservados.
 */

package net.cinecable.dao.imp;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.PersistenceException;

import net.cinecable.dao.IGenericDao;
import net.cinecable.exception.EntidadNoBorradaException;
import net.cinecable.exception.EntidadNoEncontradaException;
import net.cinecable.exception.EntidadNoGrabadaException;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;

@SuppressWarnings("unchecked")
public class GenericDao<T, PK extends Serializable> implements IGenericDao<T, PK> {

	@AroundInvoke
	public Object init(InvocationContext ctx) throws Exception {
		try {
			em = HibernateUtil.getSessionFactory().openSession();
			return ctx.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			close();
		}
	}

	protected void close() {
		try {
			em.flush();
			em.clear();
		} finally {
			em.close();
		}
	}

	protected Session em;

	private final Class<T> type;

	public GenericDao(final Class<T> type) {
		this.type = type;
	}

	public void crear(final T o) throws EntidadNoGrabadaException {
		try {
			em.saveOrUpdate(o);
		} catch (final PersistenceException e) {
			throw new EntidadNoGrabadaException("Error al grabar: ".concat(o.toString()), e);
		}
	}

	public T recuperar(final PK id) throws EntidadNoEncontradaException {
		final T entidad = (T) em.get(type, id);
		if (entidad == null) {
			final StringBuffer msg = new StringBuffer();
			msg.append(type.getSimpleName());
			msg.append('[');
			msg.append(id.toString());
			msg.append("] no encontrada.");
			throw new EntidadNoEncontradaException(msg.toString());
		}
		return entidad;
	}

	public void actualizar(final T o) throws EntidadNoGrabadaException {
		try {
			em.update(o);
		} catch (final PersistenceException e) {
			throw new EntidadNoGrabadaException("Error al actualizar: ".concat(o.toString()), e);
		}
	}

	public void eliminar(final T o) throws EntidadNoBorradaException {
		em.delete(o);
	}

	public List<T> obtenerTodos() {
		final String className = type.getSimpleName();
		final StringBuffer sql = new StringBuffer();
		sql.append("from ").append(className);
		final Query query = em.createQuery(sql.toString());
		return query.list();
	}

	public Long contar() {
		final String tableName = type.getSimpleName();
		final Query query = em.createQuery("select count(*) from " + tableName);
		return (Long) query.uniqueResult();
	}

	public List<T> encontrarPagina(final Integer firstRow, final Integer maxResults) {
		final String className = type.getSimpleName();
		final StringBuffer sql = new StringBuffer();
		sql.append("from ").append(className);
		final Query query = em.createQuery(sql.toString());
		query.setFirstResult(firstRow);
		query.setMaxResults(maxResults);
		return query.list();
	}

	public List<T> encontrarPorQuery(final String sql, final Map<String, Object> parametros) {
		final Query query = em.createQuery(sql);
		final Iterator<String> claves = parametros.keySet().iterator();
		while (claves.hasNext()) {
			final String clave = claves.next();
			query.setParameter(clave, parametros.get(clave));
		}
		return query.list();
	}

	public void refrescar(final T o) {
		em.refresh(o);
	}


}