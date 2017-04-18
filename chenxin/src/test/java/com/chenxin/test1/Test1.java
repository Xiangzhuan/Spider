package com.chenxin.test1;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream.GetField;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Test1 {
	
	private static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	private static boolean downloadPage(String path) throws ClientProtocolException, IOException {
		InputStream is = null;
		OutputStream os = null;
		HttpPost HttpPost = new HttpPost(path);
		
		CloseableHttpResponse response = httpClient.execute(HttpPost);
		System.out.println(response.getAllHeaders());
		System.out.println(response.getEntity().getContentEncoding());
		System.out.println(response.getStatusLine().toString());
		System.out.println(response.getProtocolVersion());
		System.out.println(response.getEntity().getContentLength());
		System.out.println(response.getEntity().getContent());
		
		System.out.println(response.getLastHeader("Content-Type").getValue());
		
		is = response.getEntity().getContent();
		os = new FileOutputStream("lichenxin.html");
		byte[] buffer = new byte[1024];
		while(true){
			int byteRead = is.read(buffer);
			if(byteRead==-1)
				break;
			os.write(buffer,0,byteRead);
		}
		
		if(os!=null){
			os.close();
		}
		if(response!=null){
			response.close();
		}
		return false;
	}
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String path="http://zhuanzhuan.name";
		downloadPage(path);
	}

}
