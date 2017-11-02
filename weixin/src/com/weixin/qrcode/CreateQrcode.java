package com.weixin.qrcode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.weixin.util.CommonUtil;
import com.weixin.util.UrlEncode;

import net.sf.json.JSONObject;

/**
 * 创建二维码
 * @author wz
 *
 */
public class CreateQrcode {
	private static Logger logger = Logger.getLogger(CreateQrcode.class);
	public static String createPermanentQRCode(String accessToken,int sceneId) {
		String ticket = null;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		//需要提交的json数据
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\":{\"scene\":{\"scene_id\"}}}";
		//创建永久带参二维码
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, sceneId));
		if(null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				logger.info("创建永久带参二维码成功ticket:"+ticket);
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("创建二维码失败");
			}
		}
		return ticket;
	}
	/**
	 * 创建一个场景id为617的永久二维码
	 * @param args
	 */
	public static void main(String[] args) {
		//获取接口访问凭证
		String accessToken = CommonUtil.getToken("APPID", "APPSECRET").getAccessToken();
		//创建永久二维码
		String ticket = createPermanentQRCode(accessToken,617);
	}
	/**
	 * 根据ticket换取二维码
	 * @param ticket
	 * @param savePath
	 * @return
	 */
	public static String getQRCode(String ticket,String savePath) {
		String filePath = null;
		//拼接请求地址
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		requestUrl = requestUrl.replace("TICKET",UrlEncode.urlEncodeUTF8(ticket));
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if(!savePath.endsWith("/")) {
				savePath += "/";
			}
			//将ticket作为文件名
			filePath = savePath + ticket + "jpg";
			//将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();
			bis.close();
			conn.disconnect();
			logger.info("根据ticket换取二维码成功，filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			logger.error("根据ticket换取二维码失败");
		}
		return filePath;
	}
}
