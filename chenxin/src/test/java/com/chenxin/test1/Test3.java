package com.chenxin.test1;

import java.io.IOException;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test3 {
	
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.baidu.com").get();
		Elements els = doc.select("a");
		for(Element element:els){
			System.out.println(els);
		}
	}
	
}
