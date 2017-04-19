package com.chenxin.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileUtil {
	/**
	 * 创建文件及文件夹
	 * @param filePath
	 * @return
	 */
	public static boolean createNewFile(String filePath){
		boolean isSuccess = true;
		//将"\\"转化为/，没有则不产生变化
		String filePathTurn = filePath.replaceAll("\\\\", "/");
		//过滤文件名
		int index = filePathTurn.lastIndexOf("/");
		String dir = filePathTurn.substring(0, index);
		//创建文件夹
		File fileDir = new File(dir);
		isSuccess = fileDir.mkdirs();
		//创建文件
		File file = new File(filePathTurn);
		try {
			isSuccess = file.createNewFile();
		} catch (IOException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	/**
	 * 写入文件
	 * @param content
	 * @param filePath
	 * @param isAppend
	 * @return
	 */
	public static boolean writeIntoFile(String content,String filePath,boolean isAppend){
		boolean isSuccess = true;
		String dir = filePath;
		//过滤掉文件名
		if(filePath.lastIndexOf("/")!=-1){
		int index = filePath.lastIndexOf("/");
		dir = filePath.substring(0, index);
		}
		/*//创建除文件的路径
		File fileDir = new File(dir);
		fileDir.mkdirs();*/
		//再创建路径下的文件
		/*File file = null;
		try {
			file = new File(filePath);
			file.createNewFile();
		} catch (IOException e) {
			isSuccess = false;
			e.printStackTrace();
		}*/
		//写入文件
		File file = new File(filePath);
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file, isAppend);
			fileWriter.write(content);
			fileWriter.flush();
			
		} catch (IOException e) {
			isSuccess = false;
			e.printStackTrace();
		}finally{
			if(fileWriter!=null){
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isSuccess;
	}

}
