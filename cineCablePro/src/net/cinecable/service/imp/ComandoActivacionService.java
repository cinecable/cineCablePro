package net.cinecable.service.imp;

import java.rmi.RemoteException;
import java.util.Calendar;

import javax.ejb.Stateless;

import net.cinecable.enums.TipoEjecucionComando;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.Ordenes;
import net.cinecable.service.IComandoActivacionService;
import net.cinecable.ws.Account;
import net.cinecable.ws.Device;
import net.cinecable.ws.WebServiceException;
import pojo.annotations.Comandos;
import pojo.annotations.Ctacliente;
import pojo.annotations.Prodservicio;
import pojo.annotations.Producto;
import pojo.annotations.Servicio;

@Stateless
public class ComandoActivacionService extends WsMetodos implements IComandoActivacionService {

	@Override
	public void ejecutarComando(Ctacliente cta, Comandos comando, Materiales mat, String Observacion, Ordenes ord) throws WebServiceException, RemoteException {
		Account accClient = null;
		accClient = getAccountByExternalId((long) cta.getIdcuenta());
		final TipoEjecucionComando comEjec = comando.getTipoComando();

		switch (comEjec) {
		case ACT:

			Device dev = new Device();
			dev.setAccountId(accClient.getAccountId());
			dev.setInstallDate(Calendar.getInstance().getTime().getTime());
			dev.setAccountId(accClient.getAccountId());
			dev.setAccountNumber(accClient.getAccountNumber());
			dev.setAddressApartment(accClient.getAddressApartment());
			dev.setPrimaryMacAddress(mat.getMacAddres());

			Producto producto = ord.getProducto().getProducto();
			Servicio servAct = null;
			for (Prodservicio prodServ : producto.getProdServicio()) {
				if (!prodServ.getServicio().getIdcontrolador().equals("0")) {
					servAct = prodServ.getServicio();
				}
			}

			dev.setServiceClass(servAct.getServiceClass());

			getWsPort().addDeviceToAccountById(dev, getUsuarioWs());
			getWsPort().activateDevice(mat.getMacAddres(), Observacion, getUsuarioWs());
		case DES:
			getWsPort().deactivateDevice(mat.getMacAddres(), Observacion, getUsuarioWs());
			break;
		case DEL:
			getWsPort().deleteDeviceFromAccountByMac(accClient.getAccountNumber(), mat.getMacAddres(), getUsuarioWs());
			break;
		default:
			break;
		}
	}

	public void crearCuenta(Ctacliente cuenta) throws WebServiceException, RemoteException {
		Account cta = new Account();
		cta.setAccountNumber("" + cuenta.getIdcuenta());
		cta.setExternalAccountID("" + cuenta.getIdcuenta());
		cta.setFirstName(cuenta.getNombre());
		getWsPort().createAccount(cta, getUsuarioWs());
	}

	public void actualizarCuenta(Ctacliente cuenta) throws WebServiceException, RemoteException {
		Account cta = null;
		cta = getAccountByExternalId((long) cuenta.getIdcuenta());
		getWsPort().updateAccountById(cta, getUsuarioWs());

	}

	public void desactivarCuenta(Ctacliente cuenta, String Observacion) throws WebServiceException, RemoteException {
		Account cta = null;
		cta = getAccountByExternalId((long) cuenta.getIdcuenta());
		getWsPort().deactivateAccount(cta.getAccountNumber(), Observacion, getUsuarioWs());
	}

	public void borrarDispositivo(Materiales material, String Observacion) throws WebServiceException, RemoteException {
		getWsPort().deactivateDevice(material.getMacAddres(), Observacion, getUsuarioWs());
	}

	public void desbloquearDipositivo(Materiales material, String Observacion) throws WebServiceException, RemoteException {

		getWsPort().unlockDevice(material.getNroSerie(), Observacion, getUsuarioWs());

	}

	public void bloquearDipositivo(Materiales material, String Observacion) throws WebServiceException, RemoteException {

		getWsPort().lockDevice(material.getNroSerie(), Observacion, getUsuarioWs());

	}

	public void resetearDispositivo(Materiales material) throws net.cinecable.ws.rst.WebServiceException, RemoteException {

		getWsPortDevice().resetModem(material.getMacAddres(), getUsuarioWs());

	}

}
