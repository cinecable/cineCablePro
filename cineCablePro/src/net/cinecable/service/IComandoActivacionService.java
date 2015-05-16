package net.cinecable.service;

import java.rmi.RemoteException;

import javax.ejb.Local;

import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.Ordenes;
import net.cinecable.ws.WebServiceException;
import pojo.annotations.Comandos;
import pojo.annotations.Ctacliente;

@Local
public interface IComandoActivacionService {

	public void ejecutarComando(Ctacliente cta, Comandos comando, Materiales mat, String Observacion, Ordenes ord) throws WebServiceException, RemoteException;

	void crearCuenta(Ctacliente cuenta) throws WebServiceException, RemoteException;

	void actualizarCuenta(Ctacliente cuenta) throws WebServiceException, RemoteException;

	void desactivarCuenta(Ctacliente cuenta, String Observacion) throws WebServiceException, RemoteException;

	void borrarDispositivo(Materiales material, String Observacion) throws WebServiceException, RemoteException;

	void desbloquearDipositivo(Materiales material, String Observacion) throws WebServiceException, RemoteException;

	void bloquearDipositivo(Materiales material, String Observacion) throws WebServiceException, RemoteException;

	void resetearDispositivo(Materiales material) throws WebServiceException, RemoteException;

}
