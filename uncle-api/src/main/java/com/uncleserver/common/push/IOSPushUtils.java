package com.uncleserver.common.push;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;

import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.devices.exceptions.InvalidDeviceTokenFormatException;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PayloadPerDevice;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.transmission.NotificationProgressListener;
import javapns.notification.transmission.NotificationThread;
import javapns.notification.transmission.NotificationThreads;

/**
 * 苹果原生推送工具类
 * 
 * @author guok
 * @version 2017-01-23
 *
 */
public class IOSPushUtils {

	/**
	 * 发送推送到单个设备
	 * 
	 * @param deviceToken
	 * @param message
	 *            IosPushDateModel实例json字符串
	 * @return
	 */
	public static int sendPushToSingle(String deviceToken, String message, int type) {

		try {
			PushNotificationPayload payLoad = PushNotificationPayload.fromJSON(message);
			PushNotificationManager pushManager = new PushNotificationManager();
			// String path =
			// IOSPushUtils.class.getClassLoader().getResource(CommonUtils.getIosPushFileName()).getPath();
			if (type == 0) {
				pushManager.initializeConnection(new AppleNotificationServerBasicImpl(CommonUtils.getIosPushPath(),
						CommonUtils.getIosPushPsw(), Constant.IOS_PUSH_TYPE));
			} else {
				pushManager.initializeConnection(new AppleNotificationServerBasicImpl(CommonUtils.getIosAuntPushPath(),
						CommonUtils.getIosPushPsw(), Constant.IOS_PUSH_TYPE));
			}

			List<PushedNotification> notifications = new ArrayList<PushedNotification>();
			Device device = new BasicDevice();
			device.setToken(deviceToken);
			PushedNotification notification = pushManager.sendNotification(device, payLoad, false);
			notifications.add(notification);
			List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
			List<PushedNotification> successNotifications = PushedNotification
					.findSuccessfulNotifications(notifications);
			int failed = failedNotifications.size();
			int successful = successNotifications.size();
			if (successful > 0 && failed == 0) {
				System.out.println("success:" + successNotifications.toString());
				return 1;
			} else {
				System.out.println("failed:" + failedNotifications.toString());
				return 2;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return 98;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 推送信息到设备列表
	 * 
	 * @param tokenList
	 * @param message
	 * @return
	 */
	public static int sendPushToDevices(List<String> tokenList, String message, int type) {

		try {
			// String path =
			// IOSPushUtils.class.getClassLoader().getResource(CommonUtils.getIosPushFileName()).getPath();
			AppleNotificationServer server = null;
			if (type == 0) {
				server = new AppleNotificationServerBasicImpl(CommonUtils.getIosPushPath(), CommonUtils.getIosPushPsw(),
						Constant.IOS_PUSH_TYPE);
			} else {
				server = new AppleNotificationServerBasicImpl(CommonUtils.getIosAuntPushPath(),
						CommonUtils.getIosPushPsw(), Constant.IOS_PUSH_TYPE);
			}

			List<PayloadPerDevice> list = new ArrayList<PayloadPerDevice>();
			PushNotificationPayload payLoad = PushNotificationPayload.fromJSON(message);
			for (String token : tokenList) {
				PayloadPerDevice pay = new PayloadPerDevice(payLoad, token);
				list.add(pay);
			}
			NotificationThreads work = new NotificationThreads(server, list, 5);//
			work.setListener(DEBUGGING_PROGRESS_LISTENER);// 对线程的监听，一定要加上这个监听
			work.start(); // 启动线程
			work.waitForAllThreads();// 等待所有线程启动完成
		} catch (KeystoreException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
			return 98;
		} catch (InvalidDeviceTokenFormatException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static final NotificationProgressListener DEBUGGING_PROGRESS_LISTENER = new NotificationProgressListener() {
		public void eventThreadStarted(NotificationThread notificationThread) {
			System.out.println("   [EVENT]: thread #" + notificationThread.getThreadNumber() + " started with "
					+ " devices beginning at message id #" + notificationThread.getFirstMessageIdentifier());
		}

		public void eventThreadFinished(NotificationThread thread) {
			System.out.println("   [EVENT]: thread #" + thread.getThreadNumber() + " finished: pushed messages #"
					+ thread.getFirstMessageIdentifier() + " to " + thread.getLastMessageIdentifier() + " toward "
					+ " devices");
		}

		public void eventConnectionRestarted(NotificationThread thread) {
			System.out.println(
					"   [EVENT]: connection restarted in thread #" + thread.getThreadNumber() + " because it reached "
							+ thread.getMaxNotificationsPerConnection() + " notifications per connection");
		}

		public void eventAllThreadsStarted(NotificationThreads notificationThreads) {
			System.out.println("   [EVENT]: all threads started: " + notificationThreads.getThreads().size());
		}

		public void eventAllThreadsFinished(NotificationThreads notificationThreads) {
			System.out.println("   [EVENT]: all threads finished: " + notificationThreads.getThreads().size());
		}

		public void eventCriticalException(NotificationThread notificationThread, Exception exception) {
			System.out.println("   [EVENT]: critical exception occurred: " + exception);
		}
	};
}
