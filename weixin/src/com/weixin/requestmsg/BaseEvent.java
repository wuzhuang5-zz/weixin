package com.weixin.requestmsg;
/**
 * 事件基类
 * @author wz
 */
public class BaseEvent {
	//开发者微信号
	private String ToUserName;
	//发送账号
	private String FromUserName;
	//消息创建时间
	private long CreateTime;
	//消息类型
	private String MsgType;
	//事件类型
	private String Event;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
}
