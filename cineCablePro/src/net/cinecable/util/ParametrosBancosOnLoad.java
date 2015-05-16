package net.cinecable.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import util.FileUtil;

public class ParametrosBancosOnLoad {

	public enum LISTAVALORES {
		pichinchaAhorro(consultaprop("pichinchaAhorro")), pichinchaCorriente(consultaprop("pichinchaCorriente"));

		private long codigo;

		private LISTAVALORES(long value) {
			this.codigo = value;
		}

		public long getcodigo() {
			return codigo;
		}

		private static long consultaprop(String bs) {
			Properties prp = new Properties();
			long as;
			try {
				prp.load(FileUtil.class.getClassLoader().getResourceAsStream("bancos.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			as = Long.parseLong(prp.getProperty(bs));
			return as;

		}

	}
}
