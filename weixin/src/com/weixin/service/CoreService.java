package com.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.weixin.responsemsg.Article;
import com.weixin.responsemsg.NewsMessage;
import com.weixin.responsemsg.TextMessage;
import com.weixin.util.MessageUtil;

/**
 * 核心服务类
 * @author wz
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
	private static final Logger logger = Logger.getLogger(CoreService.class);
	public static String processRequest(HttpServletRequest request) {
		//XML格式的消息数据
		String respXML = null;
		//默认返回的文本消息内容
		String respContent = "未知的消息类型";
		try {
			//调用parseXml方法解析请求消息
			logger.info("调用parseXml方法解析消息"+request);
			Map<String,String> requestMap = MessageUtil.parseXml(request);
			//发送方账号
			String fromUserName = requestMap.get("FromUserName");
			//开发者微信号
			String toUserName = requestMap.get("ToUserName");
			//消息类型
			String msgType = requestMap.get("MsgType");
			logger.info("fromUserName=="+fromUserName+"---------"+"msgType=="+msgType);
			//回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
			
			//文本消息
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息";
			}
			//图片消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息";
			}
			//语音消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息";
			}
			//视频消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息";
			}
			//链接消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息";
			}
			//事件消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
			//事件类型
				String eventType = requestMap.get("Event");
					//关注
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注";
				}
					//取消关注
				if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					//取消订阅后，用户不会再收到公众账号发送的消息，因此不需要回复。
				}
			//扫描带参数二维码
				else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					//处理扫描带参数二维码事件
				}
				//上报地理位置
				else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					//处理上报地理位置事件
				}
				//自定义菜单
				else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					logger.info("进入自定义菜单");
					//事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					logger.info("eventKey------"+eventKey);
					//根据key值判断用户点击的按钮
					if(eventKey.equals("oschina")){
						Article article = new Article();
						article.setTitle("开源中国");
						article.setDescription("开源中国社区成立于2008年，是目前中国最大的开源技术社区");
						article.setUrl("http://m.oschina.net");
						article.setPicUrl("");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						//创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticle(articleList);
						respContent = MessageUtil.messageToXml(newsMessage);
					}
					else if(eventKey.equals("iteye")) {
						textMessage.setContent("ITeye创办于2003年的JavaEye");
						respContent = MessageUtil.messageToXml(textMessage);
					}
				}
			}
			//设置文本消息的内容
			textMessage.setContent(respContent);
			//将文本消息转换为XML
			respXML = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("respXML-------"+respXML);
		return respXML;
	}
}
