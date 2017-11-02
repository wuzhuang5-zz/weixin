package com.weixin.requestmsg;
/**
 * 图片消息类
 * @author wz
 */
public class ImageMessage extends BaseMessage{
	private String PicUrl;
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		this.PicUrl = picUrl;
	}
}
