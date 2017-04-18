package com.chenxin.test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//io测试
public class Test2 {
	
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\BaiduNetdiskDownload\\2014.07.30新增1000套.rar");
		InputStream is = null;
		OutputStream os = null;
		long star = System.currentTimeMillis();
		try {
			is = new FileInputStream(file);
			os = new FileOutputStream("chenxin.rar");
			
			byte[] buffer = new byte[512];
			int position = 0;
			while((position=is.read(buffer))!=-1){
				os.write(buffer, 0, position);
			}
			/*int tempByte = -1;
			while((tempByte=is.read())>0){
				os.write(tempByte);
			}*/
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				is.close();
			}
			if(os!=null){
				os.close();
			}
		}
		System.out.println("ok:"+(System.currentTimeMillis()-star)/1000+"秒");
	}

}
