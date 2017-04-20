package com.chenxin.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseTool {

	public static Set<String> extracLinks(String url) {
		
		Set<String> links = new HashSet<String>();
		// 解析该url，将页面中的url放入set返回
		Document doc;
		try {
				doc = Jsoup.connect(url).timeout(5000).get();
				Elements hrefs = doc.select("a[href]");
				for (Element element : hrefs) {
					String href = element.attr("href");
					links.add(href);
			}
		} catch (IOException e) {
			System.out.println("该链接超时！！！" + url);
			e.printStackTrace();
		}

		return links;
	}

}
