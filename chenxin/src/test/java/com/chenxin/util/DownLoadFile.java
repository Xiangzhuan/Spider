package com.chenxin.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DownLoadFile {

	/**
	 * 根据 URL 和网页类型生成需要保存的网页的文件名，去除 URL 中的非文件名字符
	 */
	public String getFileNameByUrl(String url, String contentType) {
		// 移除http:
		url.substring(7);
		// text/html类型
		if (contentType.indexOf("html") != -1) {
			url = url.replace("[\\?/:*|<>\"]", "_");
			return url;
		} else { // 如application/pdf
			return url.replace("[\\?/:*<>\"]", "_") + "."
					+ contentType.substring(contentType.lastIndexOf("/") + 1);
		}
	}

	// 保存网页字节数组到本地文件，filepath为要保存的文件的相对地址
	private void saveToLocal(byte[] data, String filePath) throws IOException {
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					new File(filePath)));
			for(int i =0;i<data.length;i++){
				out.write(data[i]);
			}
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//下载URL指定的网页
	public String downloadFile(String url)   {
		String filePath = null;
		//1.生成httpclient对象，并且设置参数
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//设置http连接超时5s
		
		//2.httpGet
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		//3.执行
		try {
			response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode!=HttpStatus.SC_OK){
				System.err.println(new Date()+" Method failed:"+response.getStatusLine());
				filePath = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//4.处理内容
		//根据网页url生成保存时的文件名
		try {
			byte[] responseBody = EntityUtils.toByteArray(response.getEntity());
			filePath = "temp\\"+getFileNameByUrl(url,response.getFirstHeader("Content-Type").getValue());
			saveToLocal(responseBody, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return filePath;
		
	}

}
