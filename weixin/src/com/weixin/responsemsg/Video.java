package com.weixin.responsemsg;
/**
 * 视频Model
 * @author wz
 */
public class Video {
	//媒体文件ID
	private String MediaID;
	public String getMediaID() {
		return MediaID;
	}
	public void setMediaID(String mediaID) {
		MediaID = mediaID;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	//缩略图的媒体ID
	private String ThumbMediaId;
}
