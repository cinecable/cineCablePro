package net.cinecable.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import util.FacesUtil;

public class StreamUtil {

	public static String IOStreamtoString(InputStream stream) {
		StringWriter sW = new StringWriter();
		try {
			IOUtils.copy(stream, sW, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sW.toString();
	}

	public static InputStream getInputStreamFromPath(String file) {
		FacesUtil fu = new FacesUtil();
		try {
			return new FileInputStream(new File(fu.getRealPath(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void cleanDirectory(String name) {
		FacesUtil fu = new FacesUtil();
		try {
			FileUtils.cleanDirectory(FileUtils.getFile(fu.getRealPath(name)));
		} catch (IOException e) {
			System.out.println("Not able to delete-Stream opened");
		}
	}

	public static void dataStringToFile(String path, String nameFile, String Data) {
		try {
			//File image = new File(getServletContext().getRealPath("/resources/images/huella.jpg"));
			String file=new FacesUtil().getRealPath("temp") + File.separator + nameFile+".txt";
			FileUtils.writeStringToFile(new File(file), Data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
