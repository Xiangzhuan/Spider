package com.chenxin.zhihuTest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test3 {
	public static void main(String[] args) throws IOException {
		Document document = Jsoup.connect("https://www.zhihu.com/question/58467809").get();
		System.out.println(document);
		OutputStream outputStream = new FileOutputStream("123.html");
		ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(document.toString().getBytes());
		
		Elements elements = document.select("div.List-item span[class=RichText CopyrightRichText-richText]");
		for (Element element : elements) {
			System.out.println(element);   
		}
	
		
		byte[] buffer = new byte[1024];
		int flag = -1;
		while((flag=arrayInputStream.read(buffer))!=-1){
			outputStream.write(buffer, 0, flag);
		}
		
	}

}
