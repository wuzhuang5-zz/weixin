package com.weixin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.entity.SNSUserInfo;
import com.weixin.entity.WeixinOauth2Token;
import com.weixin.util.CommonUtil;
/**
 * 授权后的回调请求处理
 * @author wz
 *
 */
public class OAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");
		//用户同意授权后，能获取到code
		String code = request.getParameter("code");
		//用户同意授权
		if(!"authdeny".equals(code)) {
			//获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = CommonUtil.getOauth2AccessToken("APPID", "APPSECRET", code);
			//网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			//用户标识
			String openId = weixinOauth2Token.getOpenId();
			//获取用户信息
			SNSUserInfo snsUserInfo = CommonUtil.getSNSUserInfo(accessToken, openId);
			//设置要传递的参数
			request.setAttribute("snsUserInfo",snsUserInfo);
		}
		//跳转到index.jsp
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
