package com.weixin.menu;
/**
 * click类型的按钮
 * @author wz
 */
public class ClickButton extends Button{
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	private String key;
}
