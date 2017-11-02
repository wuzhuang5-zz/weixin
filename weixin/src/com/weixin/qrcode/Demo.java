package com.weixin.qrcode;
/**
 * 通过ticket换取二维码案例
 * @author wz
 *
 */
public class Demo {
	public static void main(String[] args) {
		String ticket = "aaaaaaa";
		String savePath = "G:/download";
		CreateQrcode.getQRCode(ticket, savePath);
	}
}
