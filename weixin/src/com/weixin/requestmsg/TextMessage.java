package com.weixin.requestmsg;
/**
 * 文本消息
 * @author wz
 */
public class TextMessage extends BaseMessage{
	//消息内容
	private String Content;
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		this.Content = content;
	}
}
