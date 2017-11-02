package com.weixin.requestmsg;
/**
 * 视频消息
 * @author wz
 */
public class VideoMessage extends BaseMessage{
	//视频消息媒体ID
	private String MediaId;
	//视频消息缩略图的媒体ID
	private String ThumbMediaId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
