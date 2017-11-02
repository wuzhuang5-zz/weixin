package com.weixin.responsemsg;
/**
 * 音乐Model
 * @author wz
 */
public class Music {
	//音乐标题
	private String Title;
	//音乐描述
	private String Description;
	//音乐链接
	private String MusicUrl;
	//高质量音乐链接，WI-FI环境优先使用该链接播放音乐
	private String HQmusicUrl;
	//缩略图的媒体ID，通过上传多媒体文件得到的ID
	private String ThumbMediaId;
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
	public String getMusicUrl() {
		return MusicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}
	public String getHQmusicUrl() {
		return HQmusicUrl;
	}
	public void setHQmusicUrl(String hQmusicUrl) {
		HQmusicUrl = hQmusicUrl;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
}
