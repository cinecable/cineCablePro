package net.cinecable.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import util.FacesUtil;

public class ZipUtil {
	List<String> fileList;
	private final String SOURCE_FOLDER;
	String _filter;

	public ZipUtil(String outputname) {
		FacesUtil fu = new FacesUtil();
		SOURCE_FOLDER = fu.getRealPath("temp");
		fileList = new ArrayList<String>();
		generateFileList(new File(SOURCE_FOLDER));
		zipIt(SOURCE_FOLDER + File.separator + outputname + ".zip");
	}

	public ZipUtil(String outputname, String filter) {
		FacesUtil fu = new FacesUtil();
		SOURCE_FOLDER = fu.getRealPath("temp");
		fileList = new ArrayList<String>();
		this._filter = filter;
		generateFileList(new File(SOURCE_FOLDER));
		zipIt(SOURCE_FOLDER + File.separator + outputname + ".zip");
	}

	/**
	 * Zip it
	 * 
	 * @param zipFile
	 *            output ZIP file location
	 */
	public void zipIt(String zipFile) {

		byte[] buffer = new byte[1024];

		try {

			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);

			System.out.println("Output to Zip : " + zipFile);

			for (String file : this.fileList) {

				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);

				FileInputStream in = new FileInputStream(SOURCE_FOLDER + File.separator + file);

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();
			}
			zos.closeEntry();
			zos.close();
			fos.close();
			System.out.println("Done");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Traverse a directory and get all files, and add the file into fileList
	 * 
	 * @param node
	 *            file or directory
	 */
	public void generateFileList(File node) {

		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				if (this._filter != null) {
					if (!filename.contains(_filter))
						continue;
				}
				generateFileList(new File(node, filename));
			}
		}
	}

	/**
	 * Format the file path for zip
	 * 
	 * @param file
	 *            file path
	 * @return Formatted file path
	 */
	private String generateZipEntry(String file) {
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}
}
