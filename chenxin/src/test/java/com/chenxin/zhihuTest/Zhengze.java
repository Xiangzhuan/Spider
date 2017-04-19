package com.chenxin.zhihuTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zhengze {
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("question/(.*+)");
		Matcher matcher = pattern.matcher("http://www.zhihu.com/question/54745381");
		System.out.println(matcher.find());
		System.out.println(matcher.group(1));
		
	}
}
