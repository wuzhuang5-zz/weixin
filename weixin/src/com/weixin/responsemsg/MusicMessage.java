package com.weixin.responsemsg;
/**
 * 音乐消息
 * @author wz
 */
public class MusicMessage extends BaseMessage{
	//音乐
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
}
