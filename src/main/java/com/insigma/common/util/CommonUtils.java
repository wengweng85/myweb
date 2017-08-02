package com.insigma.common.util;

import java.io.File;

public class CommonUtils {
	/**
	 * 根据路径下的文件名
	 * @param path
	 * @return
	 */
	public static String getNewFile(String path){
		File savePath = new File(path);
		File[] files = savePath.listFiles();
		if(files.length == 0){
			return "";
		}
		File file = null;
		for(int i=0; i<files.length; i++){
			if(i==0){
				file = files[i];
			}else if(files[i].lastModified() >= files[i-1].lastModified()){
				file = files[i];
			}
		}
		return file.getName();
	}
}
