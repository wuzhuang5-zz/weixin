package com.weixin.demo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XStream示例程序
 * @author wz
 */
public class XStreamDemo {
	/**
	 * Java对象转换为XML
	 * @param person
	 * @return
	 */
	public static String javaObject2Xml(Person person) {
		XStream xs = new XStream(new DomDriver());
		//给Person类定义别名
		xs.alias("person", person.getClass());
		return xs.toXML(person);
	}
	/**
	 * XML转换为Java对象
	 * @param xml
	 * @return
	 */
	public static Object xml2JavaObject(String xml) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("person", Person.class);
		Person person = (Person) xs.fromXML(xml);
		return person;
	}
	public static void main(String[] args) {
		//构建Person对象
		Person p1 = new Person();
		p1.setName("武装");
		p1.setSex("男");
		p1.setAddress("内蒙古");
		//将p1对象转换为XML字符串
		System.out.println(javaObject2Xml(p1));
		//构造XML字符串
		String xml = "<person><name>璐瑶</name><sex>男</sex><address>北京</address></person>";
		//将xml字符串转换成person对象
		Person p2 = (Person) xml2JavaObject(xml);
		System.out.println(p2.getName() + "" + p2.getSex() + "" + p2.getAddress());
	}
}
