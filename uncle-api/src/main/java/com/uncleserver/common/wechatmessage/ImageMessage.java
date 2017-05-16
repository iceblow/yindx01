package com.uncleserver.common.wechatmessage;

/**
 * 图片消息
 * 
 * @author duanjy
 * @date 2014-02-14
 */
public class ImageMessage extends BaseMessage {
	// 回复的消息内容
	private Media Image;

	public Media getImage() {
		return Image;
	}

	public void setImage(Media image) {
		Image = image;
	}
}