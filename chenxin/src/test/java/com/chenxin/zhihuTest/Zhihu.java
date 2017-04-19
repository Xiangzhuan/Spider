package com.chenxin.zhihuTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Zhihu {

	private String question; // 问题
	private String zhihuUrl; // 连接
	private String description; // 问题描述
	private ArrayList<String> answers; // 回答数组

	public Zhihu() {
		question = "";
		zhihuUrl = "";
		description = "";
		answers = new ArrayList<String>();
	}

	// 这里无法获得完整页面，可以使用htmlunit解决
	//添加回答集合与描述
	public void addAnswers(String url) throws IOException {
		if (getRealUrl(url)) {
			System.out.println("正在抓取：" + zhihuUrl);
			Document doc = Jsoup.connect(zhihuUrl).get();
			Elements elements = doc
					.select("div.List-item span[class=RichText CopyrightRichText-richText],div[data-za-module=QuestionItem] span.RichText");
			for (Element element : elements) {
				answers.add(element.select(
						"span[class=RichText CopyrightRichText-richText]")
						.text());
				description = element.select("span.RichText").text();
			}
		}
	}

	public boolean getRealUrl(String url) {
		Pattern pattern = Pattern.compile("question/(.*?)/");
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			zhihuUrl = "http://www.zhihu.com/question/" + matcher.group(1);
		} else {
			pattern = Pattern.compile("question/(.*+)");
			if((matcher=pattern.matcher(url)).find()){
				zhihuUrl = "http://www.zhihu.com/question/" + matcher.group(1);
				return true;
			}
			return false;
		}
		return true;
	}
	
	//格式化输出
	public String writeString() {  
        String result = "";  
        result += "问题：" + question + "\r\n";  
        result += "描述：" + description + "\r\n";  
        result += "链接：" + zhihuUrl + "\r\n";  
        for (int i = 0; i < answers.size(); i++) {  
            result += "回答" + i + "：" + answers.get(i) + "\r\n";  
        }  
        result += "\r\n\r\n";  
        return result;  
}  

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getZhihuUrl() {
		return zhihuUrl;
	}

	public void setZhihuUrl(String zhihuUrl) {
		this.zhihuUrl = zhihuUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "问题：" + question + ",\n 连接=" + zhihuUrl
				+ ",\n 描述=" + description + ",\n 回答="
				+ answers + "\n\n";
	}
}
