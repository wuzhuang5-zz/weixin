package com.weixin.responsemsg;
/**
 * 图片消息
 * @author wz
 */
public class ImageMessage extends BaseMessage{
	//图片
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
