package com.weixin.util;

import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import com.weixin.demo.MyX509TrustManager;
import com.weixin.entity.SNSUserInfo;
import com.weixin.entity.Token;
import com.weixin.entity.WeixinOauth2Token;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 通用工具类
 * @author wz
 */
public class CommonUtil {
	public static Logger logger = Logger.getLogger(CommonUtil.class);
//	private static Logger logger = Logger.getLogger(CommonUtil.class);
	//获取凭证
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * 发送https请求
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static JSONObject httpsRequest(String requestUrl,String requestMethod,String outputStr) {
		JSONObject jsonObject = null;
		try {
			//创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext .init(null, tm, new java.security.SecureRandom());
			
			//从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			//设置请求方式
			conn.setRequestMethod(requestMethod);
			//当outputStr不为null时，向输出流写数据
			if(null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				//注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			//从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader br = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer sb = new StringBuffer();
			while((str = br.readLine()) != null) {
				sb.append(str);
			}
			//释放资源
			br.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	/**
	 * 获取接口访问凭证
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static Token getToken(String appid,String appsecret) {
		Token token = null;
		String requestUrl = token_url.replace("APPID",appid).replaceAll("APPSECRET", appsecret);
		//发起GET请求获取凭证
		JSONObject jsonObject = httpsRequest(requestUrl,"GET",null);
		if(null != jsonObject) {
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				token = null;
				e.printStackTrace();
			}
		}
		return token;
	}
	/**
	 * 获取网页授权凭证
	 * @param appId
	 * @param appSecret
	 * @param code
	 * @return
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId,String appSecret,String code) {
		WeixinOauth2Token wat = null;
		//拼接请求地址
		String requestUrl = "https//api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET",appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		//获取网页授权凭证
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("获取网页授权凭证失败");
			}
		}
		return wat;
	}
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId,String refreshToken) {
		WeixinOauth2Token wat = null;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN",refreshToken);
		//刷新网页授权凭证
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("刷新网页授权凭证失败");
			}
		}
		return wat;
	}
	@SuppressWarnings({"deprecation","unchecked"})
	public static SNSUserInfo getSNSUserInfo(String accessToken,String openId) {
		SNSUserInfo snsUserInfo = null;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		//通过网页授权获取用户信息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				//用户的标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				//昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				//性别
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				//用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				//用户所在省份
				snsUserInfo.setCity(jsonObject.getString("city"));
				//用户头像
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				//用户特权信息
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"),List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("获取用户信息失败");
			}
		}
		return snsUserInfo;
	}
}
