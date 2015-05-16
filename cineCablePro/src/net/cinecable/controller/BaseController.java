package net.cinecable.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import util.MessageUtil;

public class BaseController {

	protected void setMessage(String title, String Compound) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, Compound));
	}

	protected void showInfo(String title, String body) {
		new MessageUtil().showInfoMessage(title, body);
	}

	protected void update(String idComponent) {
		RequestContext.getCurrentInstance().update(idComponent);
	}
}
