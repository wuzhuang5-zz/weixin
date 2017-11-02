package com.weixin.responsemsg;
/**
 * 图文Model
 * @author wz
 */
public class Article {
	//图文消息名称
	private String Title;
	//图文消息描述
	private String Description;
	//图片链接
	private String PicUrl;
	//点击图文消息跳转链接
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
}
