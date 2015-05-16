/*
 * EntidadNoEncontradaException.java
 * 
 * Copyright (c) 2011 MTOP.
 * Todos los derechos reservados.
 */

package net.cinecable.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class EntidadNoEncontradaException extends Exception {

	private static final long serialVersionUID = 1L;

	public EntidadNoEncontradaException() {
		super();
	}

	public EntidadNoEncontradaException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public EntidadNoEncontradaException(final String arg0) {
		super(arg0);
	}

	public EntidadNoEncontradaException(final Throwable arg0) {
		super(arg0);
	}
}