package com.weixin.responsemsg;
/**
 * 视频消息
 * @author wz
 */
public class VideoMessage extends BaseMessage{
	//视频
	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
}
