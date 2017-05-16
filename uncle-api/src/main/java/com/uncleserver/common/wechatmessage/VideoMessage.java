package com.uncleserver.common.wechatmessage;

/**
 * 视频消息
 * 
 * @author duanjy
 * @date 2014-02-14
 */
public class VideoMessage extends BaseMessage {
	// 回复的消息内容
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}