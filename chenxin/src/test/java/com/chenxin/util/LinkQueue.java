package com.chenxin.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 记录已经访问过得URL，每当要访问一个URL的时候，首先要在这个数据结构中查找， 如果已经存在则丢弃它
 *  HashSet
 * 结构中的URL不能重复
 * @author j
 */
public class LinkQueue {
	// 已访问的url集合，即Visied表
	private static Set<Object> visitedUrl = new HashSet<Object>();
	// 待访问的 url 集合，TODO
	private static Queue unVisitedUrl = new Queue();

	// 获得 URL 队列，未访问的
	public static Queue getUnVisitedUrl() {
		return unVisitedUrl;
	}

	// 添加到访问过的 URL 队列中
	public static void addVisitedUrl(String url) {
		visitedUrl.add(url);
	}

	// 移除访问过的 URL
	public static void removeVisitedUrl(String url) {
		visitedUrl.remove(url);
	}

	// 未访问的 URL 出队列
	public static Object unVisitedUrlDeQueue() {
		return unVisitedUrl.deQueue();
	}

	// 入队 保证每个url只被访问一次
	public static void addUnvisitedUrl(String url) {
		if (url != null && !url.trim().equals("") && !visitedUrl.contains(url)
				&& unVisitedUrl.contains(url)) {
			unVisitedUrl.enQueue(url);
		}
	}
	
	//获得已经访问过得url数目
	public static int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	//判断未访问的URL队列是否为空
	public static boolean unVisitedUrlsEmpty(){
		return unVisitedUrl.empty();
	}

}
