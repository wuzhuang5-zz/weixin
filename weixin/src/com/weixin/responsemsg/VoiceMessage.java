package com.weixin.responsemsg;
/**
 * 语音消息
 * @author wz
 */
public class VoiceMessage extends BaseMessage{
	//语音
	private Voice voice;

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}
}
