package com.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.weixin.service.CoreService;
import com.weixin.util.SignUtil;
/**
 * 请求处理的核心类
 * @author wz
 */

public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 请求校验(确认请求来自微信服务器)
	 */
	private final static Logger logger = Logger.getLogger(CoreServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("请求校验，确认请求来自微信服务器");
		//微信加密签名
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		//请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败。
		if(SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	/**
	 * 处理微信服务器发来的消息
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//将请求、响应的编码均设置为UTF-8(防止中文乱码)
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//接收参数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		PrintWriter out = response.getWriter();
		//请求校验
		if(SignUtil.checkSignature(signature, timestamp, nonce)) {
			//调用核心服务接收处理请求
			logger.info("调用核心服务");
			String respXml = CoreService.processRequest(request);
			logger.info("respXml------"+respXml);
			out.println(respXml);
		}
		out.close();
		out = null;
	}
}
