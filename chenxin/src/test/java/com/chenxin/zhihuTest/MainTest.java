package com.chenxin.zhihuTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.chenxin.util.fileUtil;

public class MainTest {

	public static void main(String[] args) throws IOException {

		Document doc = Jsoup.connect(
				"http://www.zhihu.com/explore/recommendations").get();
		Elements hrefs = doc.select("a.question_link");
		List<Zhihu> results = new ArrayList<Zhihu>();

		for (Element element : hrefs) {
			String question = element.text();
			String href = "http://www.zhihu.com" + element.attr("href");
			System.out.println(href);
			if (StringUtils.isNotBlank(question)
					&& StringUtils.isNotBlank(href)) {
				Zhihu zhihuTem = new Zhihu();
				zhihuTem.setQuestion(question);
				zhihuTem.setZhihuUrl(href);
				zhihuTem.addAnswers(href);
				results.add(zhihuTem);
			}
		}
		for(Zhihu zhihu:results){
			fileUtil.writeIntoFile(zhihu.writeString(),"123.txt", true);
			System.out.println(zhihu.getQuestion()+"完成！");
		}
	}
}
