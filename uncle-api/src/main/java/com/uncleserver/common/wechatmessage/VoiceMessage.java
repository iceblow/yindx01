package com.uncleserver.common.wechatmessage;

/**
 * 语音消息
 * 
 * @author duanjy
 * @date 2014-02-14
 */
public class VoiceMessage extends BaseMessage {
	// 回复的消息内容
	private Media Voice;

	public Media getVoice() {
		return Voice;
	}

	public void setVoice(Media voice) {
		Voice = voice;
	}
}