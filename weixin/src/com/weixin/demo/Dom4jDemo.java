package com.weixin.demo;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Dom4j解析XML的简单示例
 * @author wz
 */
public class Dom4jDemo {
	public static void main(String[] args) throws Exception {
		//构造XML字符串
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<person>");
		sb.append("<name>武装</name>");
		sb.append("<sex>男</sex>");
		sb.append("<address>内蒙古</address>");
		sb.append("</person>");
		
		//通过解析XML字符串创建Document对象
		Document document = DocumentHelper.parseText(sb.toString());
		//得到XML的根元素（本例中是person）
		Element root = document.getRootElement();
		//得到根元素person的所有子节点
		List<Element> elementList = root.elements();
		for(Element e : elementList) {
			System.out.println(e.getName() + "=>" + e.getText());
		}
	}
}
