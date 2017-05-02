package com.chenxin.zhihuTest;

import java.io.IOException;
import java.util.Set;

import org.apache.log4j.Logger;

import com.chenxin.util.LinkQueue;
import com.chenxin.util.ParseTool;

public class Main2Test {
	
	static Logger logger = Logger.getLogger(Main2Test.class);
	
	public static void main(String[] args) throws IOException {
		// LinkQueue linkQueue = new LinkQueue(); // 已经访问的url
		// Queue queue = new Queue(); // 将要访问的url

		/*
		 * Set<String> links = ParseTool
		 * .extracLinks("http://www.zhihu.com/explore/recommendations"); for
		 * (String link : links) { System.out.println(link); }
		 */
		crawling("http://www.zhihu.com/explore/recommendations");
	}

	public static void crawling(String url) {
		// 初始化
		LinkQueue.addUnvisitedUrl(url);
		while (!LinkQueue.unVisitedUrlsEmpty()
				&& LinkQueue.getVisitedUrlNum() <= 1000) {
			System.out.println(LinkQueue.getVisitedUrlNum());
			// 队头出列
			String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
			if (visitUrl == null) {
				// continue用于结束循环体中其后语句的执行，并跳回循环程序块的开头执行下一次循环，而不是立刻循环体。
				continue;
			}
			// 下载网页
			System.out.println(visitUrl);
			logger.error("aaaaaaaaaa");
			// 将Url放入已访问的URL中
			LinkQueue.addVisitedUrl(visitUrl);
			// 提取出下载网页中的url
			Set<String> links = ParseTool.extracLinks(visitUrl);
			// 新的未访问的url入队
			if (links != null) {
				for (String link : links) {
					if (link.startsWith("http://www.zhihu.com/")
							|| link.startsWith("https://www.zhihu.com/")) {
						LinkQueue.addUnvisitedUrl(link);
					}else if(link.startsWith("/")){
						link = "http://www.zhihu.com" +link;
						LinkQueue.addUnvisitedUrl(link);
					}
				}
			}
		}

	}

}
