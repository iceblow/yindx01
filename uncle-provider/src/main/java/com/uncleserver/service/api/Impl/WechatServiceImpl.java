package com.uncleserver.service.api.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.common.MessageUtil;
import com.uncleserver.model.Config;
import com.uncleserver.model.PointRecord;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.service.api.WechatService;

@Service("wechatService")
public class WechatServiceImpl extends BaseServiceImpl implements WechatService {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public User getUSerInfoByUnionId(String openid, String unioid, String nickname, String headimgurl,String sessionid) {
		User user = userMapper.selectUserByWxUnionId(unioid);
		UserExtra userExtra = null;
		
		if (null == user) {
			user = new User();
			user.setAddtime(new Date());
			user.setPhone("");
			user.setTel("");
			user.setPassword("");
			user.setLevel((short) 0);
			user.setRealName(nickname);
			user.setThirdAvatar(headimgurl);
			user.setNewcoupon((short) 0);
			user.setWxId(openid);
			user.setWxUnionid(unioid);

			userMapper.insert(user);
			userExtra = new UserExtra();
			userExtra.setUserid(user.getUserid());
			// 查询是否需要赠送积分
			Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_USER);
			int sendPoint = 0;
			if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
				sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
			}
			userExtra.setPoint(sendPoint);
			userExtra.setPoint_total(sendPoint);
			userExtra.setBalance(new BigDecimal("0.00"));
			userExtra.setUseTotal(new BigDecimal("0.00"));
			userExtra.setSignDay(0);
			String accesstoken = CommonUtils.MD5(sessionid + user.getUserid());
			userExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
			userExtra.setTokenTime(new Date());
			userExtra.setUpdatetime(new Date());
			userExtraMapper.insertSelective(userExtra);
			if (sendPoint > 0) {
				PointRecord record = new PointRecord();
				record.setAddtime(new Date());
				record.setCount(sendPoint);
				record.setType((short) 1);
				record.setUserid(user.getUserid());
				pointRecordMapper.insertSelective(record);
			}
		} else {
			if (CommonUtils.isEmptyString(user.getRealName())) {
				user.setRealName(nickname);
			}
			user.setWxId(openid);
			user.setWxUnionid(unioid);
			user.setThirdAvatar(headimgurl);
			userMapper.updateByPrimaryKey(user);
			// 更新用户信息
			userExtra = userExtraMapper.selectByUserId(user.getUserid());
			if (userExtra == null) {
				userExtra = new UserExtra();
				userExtra.setUserid(user.getUserid());

				// 查询是否需要赠送积分
				Config config = configMapper.selectConfigByKey(Constant.REGISTER_POINT_USER);
				int sendPoint = 0;
				if (config != null && !CommonUtils.isEmptyString(config.getConfigvalue())) {
					sendPoint = CommonUtils.parseInt(config.getConfigvalue(), 0);
				}
				userExtra.setPoint(sendPoint);
				userExtra.setPoint_total(0);
				userExtra.setBalance(new BigDecimal("0.00"));
				userExtra.setUseTotal(new BigDecimal("0.00"));
				userExtra.setSignDay(0);
				String accesstoken = CommonUtils.MD5(sessionid + user.getUserid());
				userExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
				userExtra.setTokenTime(new Date());
				userExtra.setUpdatetime(new Date());
				userExtra.setLastLogin(new Date());
				userExtraMapper.insertSelective(userExtra);
			} else {
				userExtra.setLastLogin(new Date());
				String accesstoken = CommonUtils.MD5(sessionid + user.getUserid());
				userExtra.setAccesstoken(CommonUtils.MD5(accesstoken));
				userExtra.setTokenTime(new Date());
				userExtraMapper.updateByPrimaryKey(userExtra);
			}
		}
		
		return user;
	}

	/**
	 * 解析微信发过来的消息
	 * 
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");

			String msgType = requestMap.get("MsgType");
			String event = requestMap.get("Event");
			String EventKey = requestMap.get("EventKey");

			System.out.println("WX EVENT PUSH: fromUserName:" + fromUserName + " toUserName:" + toUserName + " msgType:"
					+ msgType + " event:" + event + " EventKey:" + EventKey);

			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					// 用户关注了微信公众号
				} else if (event.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					if (EventKey.equals("")) {// 用户点击的是我们的自定义菜单
					}
				}
			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

	@Override
	public User getUserByUserId(String userid) {
		User user = userMapper.selectByPrimaryKey(CommonUtils.parseInt(userid, 0));
		return user;
	}
	
	@Override
	public UserExtra getUserExtraByUserId(int userid) {
		UserExtra extra = userExtraMapper.selectByUserId(userid);
		return extra;
	}
}
