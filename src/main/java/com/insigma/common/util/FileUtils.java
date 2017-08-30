package com.insigma.common.util;

import java.io.*;
import java.util.*;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company: Gemway
 * </p>
 * 
 * @author Fantasy
 * @version 1.0
 */

public class FileUtils {
	public FileUtils() {
	}

	public static String extractFileExt(String pathName) {
		int i = pathName.lastIndexOf(".");
		if (i < 0) {
			return "";
		}
		return pathName.substring(i);
	}

	@SuppressWarnings("unused")
	public static void saveToFile(String name, byte[] data) throws Exception {
		File dir = new File(extractFilePath(name));
		dir.mkdirs();
		File file = new File(name);
		FileOutputStream fo = new FileOutputStream(file);
		int len;
		fo.write(data);
		fo.flush();
		fo.close();

	}

	public static void saveToFile(String name, InputStream is) throws Exception {
		File dir = new File(extractFilePath(name));
		dir.mkdirs();
		File file = new File(name);
		FileOutputStream fo = new FileOutputStream(file);
		int len;
		byte b[] = new byte[1024];
		while ((len = is.read(b, 0, 1024)) != -1) {
			fo.write(b, 0, len);
		}

		fo.flush();
		fo.close();

	}

	public static void saveToFile(String path, String filename, String content) throws Exception {
		saveToFile(path, filename, content.getBytes());
	}

	public static void saveToFile(String path, String filename, byte[] content) throws Exception {
		File dir = new File(path);
		dir.mkdirs();
		FileOutputStream fo = new FileOutputStream(new File(path, filename));
		fo.write(content);
		fo.flush();
		fo.close();
	}

	public static void saveToFile(String pathname, String content) throws Exception {
		String path = extractFilePath(pathname);

		String filename = FileUtils.extractFileName(pathname);
		saveToFile(path, filename, content);
	}

	public static void saveToFile(String path, String filename, InputStream is) throws Exception {
		File dir = new File(path);
		dir.mkdirs();
		File file = new File(path, filename);
		FileOutputStream fo = new FileOutputStream(file);
		int len;
		byte b[] = new byte[1024];
		while ((len = is.read(b, 0, 1024)) != -1) {
			fo.write(b, 0, len);
		}

		fo.flush();
		fo.close();

	}

	public static String extractFileName(String pathName) {
		int i = pathName.lastIndexOf(File.separator);
		if (i < 0) {
			return pathName;
		}

		return pathName.substring(i + 1);
	}

	public static String extractFilePath(String pathName) {
		int i = pathName.lastIndexOf(File.separator);
		if (i < 0) {
			return "";
		}
		return pathName.substring(0, i);
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	public static void copy2(String from, String to, boolean overwrite) throws FileNotFoundException, IOException {
		File src = new File(from);
		File dest = new File(to);
		try {
			FileInputStream fis = new FileInputStream(src);
			if ((!dest.exists()) || overwrite) {

				FileOutputStream fos = new FileOutputStream(to, !overwrite);

				byte[] buf = new byte[512];
				int size = fis.read(buf);
				while (size != -1) {
					fos.write(buf, 0, size);
					size = fis.read(buf);
				}
				fos.close();
			}
			fis.close();
		} catch (FileNotFoundException fnfe) {
			if (src.isDirectory()) {
				/* ԭ�ļ���Ŀ¼ ����Ŀ¼ */
				File[] files = src.listFiles();
				Stack stack = new Stack();
				for (int i = 0; i < files.length; i++) {
					dest.mkdirs();
					System.out.println(files[i].getName());
					copy2(src + File.separator + files[i].getName(), to + File.separator + files[i].getName(),
							overwrite);
				}
			} else {
				/* ԭ�ļ������ڻ������쳣���, �׳��쳣 */
				throw fnfe;
			}
		} catch (IOException e) {
			/* δ֪��I/O�쳣 */
			throw e;
		}
	}

	/**
	 * ѹ��zip�ļ�
	 * 
	 * @param zipFileName
	 *            ��Ҫ��ѹ��zip�ļ�
	 * @param outPath
	 *            ���·�����ļ���Ŀ¼��
	 */
	@SuppressWarnings("rawtypes")
	public static void unzip(String zipFileName, String outPath) throws Exception {
		File file = new File(zipFileName);
		if (file.exists()) {
			ZipFile zipFile = new ZipFile(zipFileName);
			Enumeration e = zipFile.getEntries();
			ZipEntry zipEntry = null;
			while (e.hasMoreElements()) {
				zipEntry = (ZipEntry) e.nextElement();
				String entryName = zipEntry.getName();
				String names[] = entryName.split("/");
				int length = names.length;
				if (!outPath.substring(outPath.length() - 1).equals("/")) {
					outPath += "/";
				}
				String path = outPath;
				createDirectory(outPath, "");
				for (int v = 0; v < length; v++) {
					if (v < length - 1) {
						path += names[v] + "/";
						new File(path).mkdir();
					} else { // ���һ��
						if (entryName.endsWith("/")) { // ΪĿ¼,�򴴽��ļ���
							new File(outPath + entryName).mkdir();
						} else {
							InputStream in = zipFile.getInputStream(zipEntry);
							OutputStream os = new FileOutputStream(new File(outPath + entryName));
							byte[] buf = new byte[1024];
							int len;
							while ((len = in.read(buf)) > 0) {
								os.write(buf, 0, len);
							}
							in.close();
							os.close();
						}
					}
				}
			}
			zipFile.close();
		} else {
			throw new Exception("�ļ�������");
		}
	}

	/**
	 * ����Ŀ¼
	 * 
	 * @param directory
	 *            ��ѹ�ļ����Ŀ¼
	 * @param subDirectory
	 *            ��Ŀ¼��û��ʱ�ɴ�����ַ�����
	 */
	private static void createDirectory(String directory, String subDirectory) {
		String dir[];
		File fl = new File(directory);
		try {
			if (subDirectory == "" && fl.exists() != true)
				fl.mkdir();
			else if (subDirectory != "") {
				dir = subDirectory.replace('\\', '/').split("/");
				for (int i = 0; i < dir.length; i++) {
					File subFile = new File(directory + File.separator + dir[i]);
					if (subFile.exists() == false)
						subFile.mkdir();
					directory += File.separator + dir[i];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// zip��ѹ����
	public static void main(String[] args) {
		try {
			unzip("f:\\test\\test.zip", "f:\\test2\\");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
