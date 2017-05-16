package com.uncleserver.common;

import java.math.BigDecimal;

import com.google.gson.Gson;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.push.IOSPushUtils;
import com.uncleserver.common.push.IosPushDataBase;
import com.uncleserver.common.push.IosPushDateModelBase;

public class Test {

	public static void main(String[] args) {
		
		IosPushDataBase base = new IosPushDataBase();
		base.setAlert("您的 " + "aaaa" + " 订单已经被接单");
		base.setBadge(1);
		base.setSound("default");
		IosPushDateModelBase model = new IosPushDateModelBase();
		model.setAps(base);
		model.setT(1);
		model.setD("orderid:" + "1");
		IOSPushUtils.sendPushToSingle("e3c10835ae6db04b1fb81991f43c112b26c80bcad631c35997e75d03e8a8807a", new Gson().toJson(model), 1);
	}
}
