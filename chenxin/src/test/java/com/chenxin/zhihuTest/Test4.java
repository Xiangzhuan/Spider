package com.chenxin.zhihuTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Test4 {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet("https://www.zhihu.com/question/58467809");

		CloseableHttpResponse response = httpClient.execute(httpGet);

		System.out.println(response.getStatusLine().getStatusCode());

		InputStream inputStream = response.getEntity().getContent();
		
		OutputStream outputStream = new FileOutputStream("123.html");
		
		byte[] buffer = new byte[1024];
		int flag =0;
		while((flag = inputStream.read(buffer))!=-1){
			outputStream.write(buffer, 0, flag);
		}
		
		outputStream.close();
		inputStream.close();

	}

}
