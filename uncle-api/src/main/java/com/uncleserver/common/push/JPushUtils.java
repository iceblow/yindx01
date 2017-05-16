package com.uncleserver.common.push;

import com.uncleserver.common.Constant;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

public class JPushUtils {
	
	//全部推送
	public static void pushObjectAndroid(String msg_content) {
		
		 JPushClient jpushClient = new JPushClient(Constant.JIGUANG_DEV_SECRET, Constant.JIGUANG_PUSH_KEY, null, ClientConfig.getInstance());
		 Message message = Message.content(msg_content);
		 PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android()).setMessage(message).build();
		 try {
		        PushResult result = jpushClient.sendPush(payload);
		        System.out.println("Got result -"+result);
            } catch (APIConnectionException e) {
		        // Connection error, should retry later
		    	e.printStackTrace();
		    	return;
		       
		    } catch (APIRequestException e) {
		        // Should review the error, and fix the request
		    	e.printStackTrace();
		    	return;
		    }
	}
	
	//根据别名推送
	public static void pushToAndroidByAlias(String msg_content,String alias){
		JPushClient jpushClient = new JPushClient(Constant.JIGUANG_DEV_SECRET, Constant.JIGUANG_PUSH_KEY, null, ClientConfig.getInstance());
		Message message = Message.content(msg_content);
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.alias(alias)).setMessage(message).build();
		 try {
		        PushResult result = jpushClient.sendPush(payload);
		        System.out.println("Got result -"+result);

		    } catch (APIConnectionException e) {
		        // Connection error, should retry later
		    	e.printStackTrace();
		    	return;
		       
		    } catch (APIRequestException e) {
		        // Should review the error, and fix the request
		    	e.printStackTrace();
		    	return;
		    }
	}
	
	//根据ID推送
	public static void pushToAndroidById(String msg_content,String registration_id,String secret,String key){
		JPushClient jpushClient = new JPushClient(secret, key, null, ClientConfig.getInstance());
		Message message = Message.content(msg_content);
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.registrationId(registration_id)).setMessage(message).build();
		 try {
		        PushResult result = jpushClient.sendPush(payload);
		        System.out.println("Got result -"+result);

		    } catch (APIConnectionException e) {
		        // Connection error, should retry later
		    	e.printStackTrace();
		    	return;
		       
		    } catch (APIRequestException e) {
		        // Should review the error, and fix the request
		    	e.printStackTrace();
		    	return;
		    }
	}
	
	
	
	
	
}
