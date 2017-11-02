package com.weixin.util;
/**
 * URL编码
 * @author wz
 *
 */
public class UrlEncode {
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source,"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
