package com.uncleserver.service.api.Impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.common.push.IOSPushUtils;
import com.uncleserver.common.push.IosPushDataBase;
import com.uncleserver.common.push.IosPushDateModelBase;
import com.uncleserver.common.push.JPushUtils;
import com.uncleserver.common.qiniu.QiniuFileUtils;
import com.uncleserver.common.wxpay.AccessToken;
import com.uncleserver.dao.AboutMapper;
import com.uncleserver.dao.AgreementMapper;
import com.uncleserver.dao.AreaMapper;
import com.uncleserver.dao.AuntBalanceRecordMapper;
import com.uncleserver.dao.AuntBannerMapper;
import com.uncleserver.dao.AuntCardMapper;
import com.uncleserver.dao.AuntCashMapper;
import com.uncleserver.dao.AuntDisplayMapper;
import com.uncleserver.dao.AuntExtraMapper;
import com.uncleserver.dao.AuntInfoSetMapper;
import com.uncleserver.dao.AuntInvitationMapper;
import com.uncleserver.dao.AuntLevelSetMapper;
import com.uncleserver.dao.AuntLikeMapper;
import com.uncleserver.dao.AuntMapper;
import com.uncleserver.dao.AuntMessageDetailMapper;
import com.uncleserver.dao.AuntMessageMapper;
import com.uncleserver.dao.AuntMessageSysMapper;
import com.uncleserver.dao.AuntPointProgressMapper;
import com.uncleserver.dao.AuntPointRecordMapper;
import com.uncleserver.dao.AuntPushMapper;
import com.uncleserver.dao.AuntSignSetMapper;
import com.uncleserver.dao.AuntSkillMapper;
import com.uncleserver.dao.AuntWatchMapper;
import com.uncleserver.dao.BackReasonMapper;
import com.uncleserver.dao.BalanceRecordMapper;
import com.uncleserver.dao.CategoryCityMapper;
import com.uncleserver.dao.CategoryMapper;
import com.uncleserver.dao.CategorySecondMapper;
import com.uncleserver.dao.CategoryThirdMapper;
import com.uncleserver.dao.CitiesMapper;
import com.uncleserver.dao.CompanyExtraMapper;
import com.uncleserver.dao.CompanyMapper;
import com.uncleserver.dao.CompanyRangeMapper;
import com.uncleserver.dao.ConfigMapper;
import com.uncleserver.dao.ConfigRedPacketMapper;
import com.uncleserver.dao.CouponMapper;
import com.uncleserver.dao.CouponUserMapper;
import com.uncleserver.dao.FeedBackAuntMapper;
import com.uncleserver.dao.FeedBackMapper;
import com.uncleserver.dao.FileInfoMapper;
import com.uncleserver.dao.HomeAdMapper;
import com.uncleserver.dao.HomeBannerMapper;
import com.uncleserver.dao.HomeIconMapper;
import com.uncleserver.dao.HomeItemMapper;
import com.uncleserver.dao.InvitationMapper;
import com.uncleserver.dao.LevelSetMapper;
import com.uncleserver.dao.ManagePuseMapper;
import com.uncleserver.dao.MessageDetailMapper;
import com.uncleserver.dao.MessageMapper;
import com.uncleserver.dao.MessageSysMapper;
import com.uncleserver.dao.OrderAuntMapper;
import com.uncleserver.dao.OrderAuntTempMapper;
import com.uncleserver.dao.OrderCommentMapper;
import com.uncleserver.dao.OrderComplaintMapper;
import com.uncleserver.dao.OrderMapper;
import com.uncleserver.dao.OrderPayMapper;
import com.uncleserver.dao.OrderPoolMapper;
import com.uncleserver.dao.OrderRefundMapper;
import com.uncleserver.dao.OrderTargetMapper;
import com.uncleserver.dao.OrderTargetTempMapper;
import com.uncleserver.dao.OrderTempMapper;
import com.uncleserver.dao.OrderThirdMapper;
import com.uncleserver.dao.OrderThirdTempMapper;
import com.uncleserver.dao.PickAddressCompanyMapper;
import com.uncleserver.dao.PickAddressMapper;
import com.uncleserver.dao.PointProgressMapper;
import com.uncleserver.dao.PointRecordMapper;
import com.uncleserver.dao.ProviencesMapper;
import com.uncleserver.dao.RatioMapper;
import com.uncleserver.dao.RechargeOrderMapper;
import com.uncleserver.dao.RedPacketRecordMapper;
import com.uncleserver.dao.SerPriceMapper;
import com.uncleserver.dao.SignRecordAuntMapper;
import com.uncleserver.dao.SignRecordMapper;
import com.uncleserver.dao.SignSetMapper;
import com.uncleserver.dao.TutorialsMapper;
import com.uncleserver.dao.UserExtraMapper;
import com.uncleserver.dao.UserMapper;
import com.uncleserver.dao.UserPushMapper;
import com.uncleserver.dao.VCodeAuntMapper;
import com.uncleserver.dao.VCodeMapper;
import com.uncleserver.dao.VersionMapper;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.AuntBalanceRecord;
import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.AuntMessage;
import com.uncleserver.model.AuntMessageDetail;
import com.uncleserver.model.AuntPush;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.model.Config;
import com.uncleserver.model.CouponUser;
import com.uncleserver.model.FileInfo;
import com.uncleserver.model.LevelSet;
import com.uncleserver.model.Message;
import com.uncleserver.model.MessageDetail;
import com.uncleserver.model.Order;
import com.uncleserver.model.OrderAunt;
import com.uncleserver.model.OrderPay;
import com.uncleserver.model.OrderPool;
import com.uncleserver.model.Ratio;
import com.uncleserver.model.RedPacketRecord;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.model.UserPush;
import com.uncleserver.modelVo.PushData;
import com.uncleserver.modelVo.UserModel;
import com.uncleserver.service.api.BaseService;

public class BaseServiceImpl implements BaseService {
	@Resource
	protected AuntPointProgressMapper auntPointProgressMapper;
	@Resource
	protected PointProgressMapper pointProgressMapper;
	@Resource
	protected AgreementMapper agreementMapper;
	@Resource
	protected OrderAuntMapper orderAuntMapper;
	@Resource
	protected BalanceRecordMapper balanceRecordMapper;
	@Resource
	protected ConfigMapper configMapper;
	@Resource
	protected FeedBackMapper feedBackMapper;
	@Resource
	protected CategoryThirdMapper categoryThirdMapper;
	@Resource
	protected FileInfoMapper fileInfoMapper;
	@Resource
	protected LevelSetMapper levelSetMapper;
	@Resource
	protected PickAddressMapper pickAddressMapper;
	@Resource
	protected PointRecordMapper pointRecordMapper;
	@Resource
	protected SignRecordMapper signRecordMapper;
	@Resource
	protected SignSetMapper signSetMapper;
	@Resource
	protected UserExtraMapper userExtraMapper;
	@Resource
	protected UserMapper userMapper;
	@Resource
	protected UserPushMapper userPushMapper;
	@Resource
	protected VCodeMapper vCodeMapper;

	@Resource
	protected HomeBannerMapper homeBannerMapper;
	@Resource
	protected HomeIconMapper homeIconMapper;
	@Resource
	protected HomeItemMapper homeItemMapper;
	@Resource
	protected HomeAdMapper homeAdMapper;
	@Resource
	protected AuntMapper auntMapper;
	@Resource
	protected CompanyMapper companyMapper;
	@Resource
	protected AuntExtraMapper auntExtramapper;

	@Resource
	protected VersionMapper versionMapper;
	@Resource
	protected CitiesMapper citiesMapper;
	@Resource
	protected AuntPushMapper auntPushMapper;
	@Resource
	protected RechargeOrderMapper rechargeOrderMapper;

	@Resource
	protected CouponUserMapper couponUserMapper;
	@Resource
	protected CouponMapper couponMapper;

	@Resource
	protected MessageMapper messageMapper;
	@Resource
	protected MessageDetailMapper messageDetailMapper;
	@Resource
	protected MessageSysMapper messageSysMapper;

	@Resource
	protected CategoryMapper categoryMapper;
	@Resource
	protected CategorySecondMapper categorySecondMapper;
	@Resource
	protected CategoryCityMapper categoryCityMapper;

	@Resource
	protected SerPriceMapper serPriceMapper;
	@Resource
	protected BackReasonMapper backReasonMapper;
	@Resource
	protected AboutMapper aboutMapper;
	@Resource
	protected CompanyRangeMapper companyRangeMapper;
	@Resource
	protected CompanyExtraMapper companyExtraMapper;
	@Resource
	protected AuntSkillMapper auntSkillMapper;
	@Resource
	protected OrderTempMapper orderTempMapper;
	@Resource
	protected OrderTargetTempMapper orderTargetTempMapper;
	@Resource
	protected OrderThirdTempMapper orderThirdTempMapper;
	@Resource
	protected OrderMapper orderMapper;
	@Resource
	protected OrderTargetMapper orderTargetMapper;
	@Resource
	protected OrderThirdMapper orderThirdMapper;
	@Resource
	protected OrderAuntTempMapper orderAuntTempMapper;
	@Resource
	protected OrderCommentMapper orderCommentMapper;
	@Resource
	protected OrderComplaintMapper orderComplaintMapper;
	@Resource
	protected OrderPayMapper orderPayMapper;
	@Resource
	protected AuntLikeMapper auntLikeMapper;
	@Resource
	protected VCodeAuntMapper vCodeAuntMapper;
	@Resource
	protected AuntPointRecordMapper auntPointRecordMapper;
	@Resource
	protected AuntLevelSetMapper auntLevelSetMapper;
	@Resource
	protected SignRecordAuntMapper signRecordAuntMapper;
	@Resource
	protected FeedBackAuntMapper feedbackAuntMapper;
	@Resource
	protected AuntSignSetMapper auntSignSetMapper;
	@Resource
	protected AuntMessageMapper auntMessageMapper;
	@Resource
	protected AuntMessageDetailMapper auntMessageDetailMapper;
	@Resource
	protected AuntMessageSysMapper auntMessageSysMapper;
	@Resource
	protected AuntBannerMapper auntBannerMapper;

	@Resource
	protected AuntInfoSetMapper auntInfoSetMapper;
	@Resource
	protected AuntDisplayMapper auntDisplayMapper;
	@Resource
	protected AuntCardMapper auntCardMapper;
	@Resource
	protected AuntCashMapper auntCashMapper;

	@Resource
	protected OrderPoolMapper orderPoolMapper;
	@Resource
	protected AuntBalanceRecordMapper auntBalanceRecordMapper;
	@Resource
	protected ProviencesMapper proviencesMapper;
	@Resource
	protected CitiesMapper citysMapper;
	@Resource
	protected PickAddressCompanyMapper pickAddressCompanyMapper;
	@Resource
	protected AreaMapper areaMapper;
	@Resource
	protected TutorialsMapper tutorialsmapper;
	@Resource
	protected AuntWatchMapper auntWatchmapper;

	@Resource
	protected RatioMapper ratioMapper;

	@Resource
	protected OrderRefundMapper orderRefundMapper;

	@Resource
	protected ManagePuseMapper managePuseMapper;
	
	@Resource
	protected InvitationMapper invitationMapper;
	@Resource
	protected AuntInvitationMapper auntInvitationMapper;
	@Resource
	protected ConfigRedPacketMapper configRedPacketMapper;
	@Resource
	protected RedPacketRecordMapper redPacketRecordMapper;
	

	public AccessToken accessToken;
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 根据图片ID获取图片访问路径
	 */
	@Override
	@Transactional(readOnly = false)
	public String getFilePathById(Integer fileid) {
		if (fileid == null || fileid == 0)
			return null;
		FileInfo file = fileInfoMapper.selectByPrimaryKey(fileid);
		if (file != null) {
			if (file.getState() == 0) {
				file.setState((byte) 1);
				fileInfoMapper.updateByPrimaryKey(file);
			}
			String url = QiniuFileUtils.getFilePath(file.getFilePath());
			return url.replace(File.separatorChar, '/');
		}
		return null;
	}

	/**
	 * true表示可以发送
	 */
	@Override
	public boolean checkCanSendVcode(String phone) {
		Config config = configMapper.selectConfigByKey(Constant.DAY_MAX_SMS);
		if (config != null) {
			int count = CommonUtils.parseInt(config.getConfigvalue(), 0);
			if (count > 0) {
				Long sendedcount = vCodeMapper.setectTodaySendCount(phone);
				if (sendedcount != null && sendedcount >= count) {
					return false;
				}
			}
		}
		return true;
	}

	public UserModel createUserModel(User user, UserExtra extra) {
		UserModel model = new UserModel();

		model.setAccesstoken(extra.getAccesstoken());
		String filePath = getFilePathById(user.getAvatar());
		if (!CommonUtils.isEmptyString(filePath)) {
			model.setAvatarurl(filePath);
		} else {
			model.setAvatarurl(user.getThirdAvatar());
		}
		if (extra.getBalance() != null) {
			model.setBalance(extra.getBalance().floatValue());
		} else {
			model.setBalance(0f);
		}

		if (user.getBirthday() != null) {
			model.setBirthday(CommonUtils.getTimeFormat(user.getBirthday(), "yyyy-MM-dd"));
		} else {
			model.setBirthday("未设置");
		}

		model.setLevel(user.getLevel());
		LevelSet set = levelSetMapper.setlectSetByLevel(user.getLevel());
		if (set != null) {
			model.setLevelname(set.getTitle());
		}
		model.setUserid(user.getUserid());
		model.setPhone(user.getPhone());
		model.setPoint(extra.getPoint());
		model.setQq_id(user.getQqId());
		model.setReal_name(user.getRealName());

		if (user.getSex() != null) {
			model.setSex(user.getSex());
		} else {
			model.setSex("未设置");
		}

		Calendar now = Calendar.getInstance();
		if (extra.getLastSign() != null) {
			String dateStr1 = CommonUtils.getTimeFormat(extra.getLastSign(), "yyyy-MM-dd");
			String dateStr2 = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
			if (dateStr1.equals(dateStr2)) {
				model.setToday_sign(1);
			} else {
				model.setToday_sign(0);
			}
		} else {
			model.setToday_sign(0);
		}

		// 本周连续签到天数
		now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateStrMonday = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
		now.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		now.add(Calendar.DAY_OF_YEAR, 1);
		String dateStrSunday = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");

		Long count = signRecordMapper.selectWeekSignCount(dateStrMonday, dateStrSunday, user.getUserid());
		if (count == null) {
			model.setSign_week(0);
		} else {
			model.setSign_week(count.intValue());
		}

		// 优惠券数量暂时未实现
		model.setCouponcount(0);

		if (user.getSignature() != null) {
			model.setSignature(user.getSignature());
		} else {
			model.setSignature("未设置");
		}
		model.setSina_id(user.getSinaId());
		model.setTel(user.getTel());
		model.setWx_id(user.getWxId());
		Calendar calendarEnd = Calendar.getInstance();
		Date nowTime = new Date();
		calendarEnd.setTime(nowTime);
		calendarEnd.add(Calendar.DAY_OF_MONTH, -7);
		Calendar calendarStart = calendarEnd;
		String dateStr = CommonUtils.getTimeFormat(calendarStart.getTime(), "yyyy-MM-dd");
		String dateEnd = CommonUtils.getTimeFormat(nowTime, "yyyy-MM-dd");
		List<CouponUser> couponList = couponUserMapper.selectCouponById(user.getUserid(), dateStr, dateEnd);
		if(couponList != null){
			model.setCouponcount(couponList.size());
		}
	
		return model;
	}

	@Override
	public User selectUserByUserId(int userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		return user;
	}

	@Override
	public UserExtra selectUserExtraByUserID(int userid) {
		UserExtra extra = userExtraMapper.selectByUserId(userid);
		return extra;
	}

	/**
	 * 取得微信access_token
	 * 
	 * @return
	 */
	@Override
	public AccessToken getAccessToken() {
		if (accessToken != null && accessToken.isExpires()) {
			return this.accessToken;
		}
		String requestUrl = access_token_url.replace("APPID", Constant.WXCHAT_APPID).replace("APPSECRET",
				Constant.WXCHAT_APPSECRET);
		JsonObject jsonObject = CommonUtils.httpRequest(requestUrl, "GET", null);
		// 请求成功
		if (null != jsonObject) {
			try {
				String access_token = jsonObject.get("access_token").getAsString();
				accessToken = new AccessToken(jsonObject.get("access_token").getAsString(),
						jsonObject.get("expires_in").getAsInt());
			} catch (Exception e) {
				accessToken = null;
			}
		}
		return this.accessToken;
	}

	/**
	 * 生成订单号
	 */
	@Override
	public String createOrderNum(int type, Long nowcount) {
		StringBuilder orderNumBuilder = new StringBuilder();
		// 首字母分类
		switch (type) {
		case 1:// 用户充值
			orderNumBuilder.append("R");
			break;
		case 2:// 用户订单
			orderNumBuilder.append("B");
			break;
		case 3:// 订单支付
			orderNumBuilder.append("P");
			break;
		case 4:// 阿姨订单
			orderNumBuilder.append("A");
			break;
		case 5:// 退款
			orderNumBuilder.append("F");
			break;
		default:
			return null;
		}
		// 四位年份
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		orderNumBuilder.append(String.valueOf(year));

		// 年份中的天
		int dayOfYear = now.get(Calendar.DAY_OF_YEAR);
		String dayOfYearString = String.valueOf(dayOfYear);
		String dayOfYearAppend = dayOfYearString;
		for (int i = 0; i < 3 - dayOfYearString.length(); i++) {
			dayOfYearAppend = "0" + dayOfYearAppend;
		}
		orderNumBuilder.append(dayOfYearAppend);

		// 小时
		int hour = now.get(Calendar.HOUR_OF_DAY);
		String hourString = String.valueOf(hour);
		if (hourString.length() == 1) {
			hourString = "0" + hourString;
		}
		orderNumBuilder.append(hourString);

		// 订单数量
		int orderCount = 0;
		if (nowcount != null) {
			orderCount = nowcount.intValue();
		}
		orderCount++;
		String orderCountString = String.valueOf(orderCount);
		if (orderCountString.length() < 3) {
			String orderCountAppend = orderCountString;
			for (int i = 0; i < 3 - orderCountString.length(); i++) {
				orderCountAppend = "0" + orderCountAppend;
			}
			orderCountString = orderCountAppend;
		}
		orderNumBuilder.append(orderCountString);

		return orderNumBuilder.toString();
	}

	@Override
	public UserExtra selectUserExtraByToken(String accesstoken) {
		UserExtra extra = userExtraMapper.selectByAccesstoken(accesstoken);
		return extra;
	}

	@Override
	public AuntExtra selectAuntExtraByToken(String accesstoken) {
		AuntExtra extra = auntExtramapper.selectByAccesstoken(accesstoken);
		return extra;
	}

	@Override
	public Aunt selectAuntByAuntID(int anutid) {
		Aunt aunt = auntMapper.selectByPrimaryKey(anutid);
		return aunt;
	}

	@Override
	public AuntExtra selectAuntExtraByAuntID(int anutid) {
		AuntExtra extra = auntExtramapper.selectByAuntId(anutid);
		return extra;
	}

	@Override
	public CompanyExtra selectCompanyByToken(String accesstoken) {
		CompanyExtra extra = companyExtraMapper.selectByAccesstoken(accesstoken);
		return extra;
	}

	@Override
	public Aunt selectAuntByAuntId(int auntid) {
		Aunt aunt = auntMapper.selectByPrimaryKey(auntid);
		return aunt;
	}

	@Override
	public Company selectCompanyByCid(int companyId) {
		Company company = companyMapper.selectByPrimaryKey(companyId);
		return company;
	}

	@Override
	public AuntExtra selectAuntExtraByAuntId(int auntid) {
		AuntExtra extra = auntExtramapper.selectByAuntId(auntid);
		return extra;
	}

	@Override
	public CompanyExtra selectCompanyExtraByCid(int companyId) {
		CompanyExtra extra = companyExtraMapper.selectByCompanyId(companyId);
		return extra;
	}

	protected boolean checkSession(String accesstoken, UserExtra extra) {
		return !CommonUtils.isEmptyString(accesstoken) && extra != null && accesstoken.equals(extra.getAccesstoken());
	}

	protected boolean checkSession(String accesstoken, AuntExtra extra) {
		return !CommonUtils.isEmptyString(accesstoken) && extra != null && accesstoken.equals(extra.getAccesstoken());
	}

	protected boolean checkSession(String accesstoken, CompanyExtra extra) {
		return !CommonUtils.isEmptyString(accesstoken) && extra != null && accesstoken.equals(extra.getAccesstoken());
	}

	/**
	 * 检查访问令牌是否过期
	 * 
	 * @param tokentime
	 * @return true 过期
	 */
	protected boolean checkTokenTime(Date tokentime) {
		if (tokentime == null) {
			return true;
		}

		Calendar now = Calendar.getInstance();
		Calendar token = Calendar.getInstance();
		token.setTime(tokentime);
		token.add(Calendar.DAY_OF_MONTH, 30);
		if (token.before(now)) {
			return true;
		}

		return false;
	}

	/**
	 * 发送系统消息给用户 usertype 1.用户 2.阿姨 3.公司
	 */
	@Override
	// @Transactional(readOnly = false)
	public void sendSystemMessageToUser(int userid, int usertype, MessageDetail detail, AuntMessageDetail auntDetail) {
		if (usertype == 1) {// 用户端
			Message message = messageMapper.selectByUserIdAndType(userid, 1);
			if (message == null) {
				message = new Message();
				message.setAddtime(new Date());
				message.setLastMsgTime(new Date());
				message.setReadState((short) 0);
				message.setTitle("系统消息");
				message.setType((byte) 1);
				message.setUnreadCount(1);
				message.setUserid(userid);

				messageMapper.insert(message);

				detail.setMessageid(message.getId());
				messageDetailMapper.insert(detail);

			} else {
				message.setLastMsgTime(new Date());
				message.setReadState((short) 0);
				if (message.getUnreadCount() == null) {
					message.setUnreadCount(1);
				} else {
					message.setUnreadCount(message.getUnreadCount() + 1);
				}
				messageMapper.updateByPrimaryKey(message);

				detail.setMessageid(message.getId());
				messageDetailMapper.insert(detail);

			}

		} else {// 阿姨端
			AuntMessage message = auntMessageMapper.selectByUserIdAndType(userid, 1);
			if (message == null) {
				message = new AuntMessage();
				message.setAddtime(new Date());
				message.setLastMsgTime(new Date());
				message.setReadState((byte) 0);
				message.setTitle("系统消息");
				message.setType((byte) 1);
				message.setUnreadCount(1);
				message.setUserid(userid);
				message.setUserType((byte) 0);
				auntMessageMapper.insert(message);

				auntDetail.setMessageid(message.getId());
				auntMessageDetailMapper.insert(auntDetail);

			} else {
				message.setLastMsgTime(new Date());
				message.setReadState((byte) 0);
				if (message.getUnreadCount() == null) {
					message.setUnreadCount(1);
				} else {
					message.setUnreadCount(message.getUnreadCount() + 1);
				}
				auntMessageMapper.updateByPrimaryKey(message);

				auntDetail.setMessageid(message.getId());
				auntMessageDetailMapper.insert(auntDetail);

			}
		}
	}

	// 单次支付 日常保洁(1) 擦窗清洁(2) 宴会帮工(5) 酒席小工(6) 家常洗衣(8) 居家家护(保姆)(14) 月嫂(15) 育儿嫂(16)
	// 老人陪护(17) 病人陪护(18)
	public void singlePayment(Order order, OrderPay pay) {

		if (order.getCategoryid() != null) {
			if (order.getCategoryid() == 1 || order.getCategoryid() == 2 || order.getCategoryid() == 5
					|| order.getCategoryid() == 6 || order.getCategoryid() == 8) {
				payHourlyWorker(order);
				
			} else if (order.getCategoryid() == 14 || order.getCategoryid() == 15 || order.getCategoryid() == 16
					|| order.getCategoryid() == 17 || order.getCategoryid() == 18) {
				payPermanentworker(order, pay);
				
			} else {

			}
		}

	}

	// 多次支付 上门费用 家电清洗(3) 买菜做饭(4) 皮具保养(7) 鞋类洗护(9) 管道维修(11) 水电维修(12) 上门开锁(13)
	// 家电维修(10)
	public void multiplePayments(Order order) {
		// if(orderPay.getType() == )
		order.setState((short) 12);
		orderMapper.updateByPrimaryKey(order);
		List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		if (orderAuntList != null && orderAuntList.size() > 0) {
			for (OrderAunt orderAunt : orderAuntList) {
				orderAunt.setState((short) 12);
				orderAuntMapper.updateByPrimaryKey(orderAunt);
			}
		}
	}

	// 长期工支付
	public void payPermanentworker(Order order, OrderPay orderpay) {

		if (order.getOrderType() == 0) {

			if (order.getState() == 4) {
				int month = order.getMonth().intValue();
				month += orderpay.getMonth();
				order.setMonth(new Float("" + month));
				orderMapper.updateByPrimaryKey(order);

				List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
				if (orderAuntList != null && orderAuntList.size() > 0) {
					for (OrderAunt orderAunt : orderAuntList) {
						orderAunt.setMonth(new Float("" + month));
						orderAuntMapper.updateByPrimaryKey(orderAunt);
					}
				}
			} else if (order.getState() == 5) {
				int month = order.getMonth().intValue();
				month += orderpay.getMonth();
				order.setState((short) 6);
				order.setPayTime1(new Date());
				orderMapper.updateByPrimaryKey(order);
				order.setMonth(new Float("" + month));

				List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
				if (orderAuntList != null && orderAuntList.size() > 0) {
					for (OrderAunt orderAunt : orderAuntList) {
						orderAunt.setMonth(new Float("" + month));
						orderAunt.setState((short) 6);
						orderAunt.setPayTime1(new Date());
						orderAuntMapper.updateByPrimaryKey(orderAunt);
					}
				}
				settlementBalance(order);
			}

		} else if (order.getOrderType() == 1) {
			// 试单
			order.setState((short) 6);
			order.setPayTime1(new Date());
			orderMapper.updateByPrimaryKey(order);

			List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
			if (orderAuntList != null && orderAuntList.size() > 0) {
				for (OrderAunt orderAunt : orderAuntList) {
					orderAunt.setState((short) 6);
					orderAunt.setPayTime1(new Date());
					orderAuntMapper.updateByPrimaryKey(orderAunt);
				}
			}
			settlementBalance(order);
		}

	}

	// 小时工支付
	public void payHourlyWorker(Order order) {
		order.setState((short) 6);
		order.setPayTime1(new Date());
		orderMapper.updateByPrimaryKey(order);

		List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		if (orderAuntList != null && orderAuntList.size() > 0) {
			for (OrderAunt orderAunt : orderAuntList) {
				orderAunt.setState((short) 6);
				orderAunt.setPayTime1(new Date());
				orderAuntMapper.updateByPrimaryKey(orderAunt);
			}
		}
		settlementBalance(order);
	}

	// 第一次支付维修工
	public void firstPayMaintenanceWorker(Order order) {

		order.setState((short) 4);
		orderMapper.updateByPrimaryKey(order);

		List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		if (orderAuntList != null && orderAuntList.size() > 0) {
			for (OrderAunt orderAunt : orderAuntList) {
				orderAunt.setState((short) 4);
				orderAuntMapper.updateByPrimaryKey(orderAunt);
			}
		}

	}

	// 第二次支付维修工
	public void sencondPayMaintenanceWorker(Order order) {

		order.setState((short) 6);
		order.setPayTime1(new Date());
		orderMapper.updateByPrimaryKey(order);

		List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		if (orderAuntList != null && orderAuntList.size() > 0) {
			for (OrderAunt orderAunt : orderAuntList) {
				orderAunt.setState((short) 6);
				orderAunt.setPayTime1(new Date());
				orderAuntMapper.updateByPrimaryKey(orderAunt);
			}
		}

	}
	
	public void sendPayMessageToAunt(Order order){
		List<OrderAunt> auntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		PushData pushData = new PushData();
		if(auntList != null && auntList.size()>0){
			for(OrderAunt orderAunt:auntList){
				CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
				String name = second.getName();
				if(orderAunt.getAuntid() != null && orderAunt.getAuntid() >0 && orderAunt.getCompanyid() !=null && orderAunt.getCompanyid() == 0){
					AuntExtra extra = auntExtramapper.selectByAuntId(orderAunt.getAuntid());
					AuntPush auntPush = auntPushMapper.selectByUserId(orderAunt.getAuntid(), (short) 0);
					if(auntPush != null){
						pushData.setT("5");
						pushData.setM("您的 " + name + " 订单已经付款完成");
						pushData.setD("orderid:" + orderAunt.getOrderid());
						AuntMessageDetail detail = new AuntMessageDetail();
						detail.setSysMessageid(0);
						detail.setTitle("新消息");
						detail.setDetail("您的 " + name + " 订单已经付款完成");
						detail.setLinkTitle("查看订单详情");
						detail.setAddtime(new Date());
						detail.setLinkType((byte) 5);
						detail.setLinkContent(""+orderAunt.getOrderid());
						sendSystemMessageToUser(auntPush.getUserid(), 2, null, detail);
						if (auntPush.getDevicetype() == 1) {

							JPushUtils.pushToAndroidById(new Gson().toJson(pushData), auntPush.getPushKey(),
									Constant.JIGUNAG_PUSH_AUNT_SECRET, Constant.JIGUAN_PUSH_AUNT_KEY);
						} else if (auntPush.getDevicetype() == 2) {
							// tokenList.add(auntPush.getPushKey());
							// TODO: 阿姨端推送证书修改
							IosPushDataBase base = new IosPushDataBase();
							base.setAlert("您的 " + name + " 订单已经付款完成");
							base.setBadge(1);
							base.setSound("default");
							IosPushDateModelBase model = new IosPushDateModelBase();
							model.setAps(base);
							model.setT(5);
							model.setD("orderid:" + orderAunt.getOrderid());
							IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 1);
						}
					}
					
				}else if(orderAunt.getAuntid() != null && orderAunt.getAuntid() == 0 && orderAunt.getCompanyid() !=null && orderAunt.getCompanyid() > 0){
					Company company = companyMapper.selectByPrimaryKey(orderAunt.getCompanyid());
					//CompanyExtra extra = companyExtraMapper.selectByCompanyId(company.getCompanyid());
					AuntPush auntPush = auntPushMapper.selectByUserId(orderAunt.getCompanyid(), (short) 1);
					if(auntPush != null){
						pushData.setT("5");
						pushData.setM("您的 " + name + " 订单已经付款完成");
						pushData.setD("orderid:" + orderAunt.getOrderid());
						AuntMessageDetail detail = new AuntMessageDetail();
						detail.setSysMessageid(0);
						detail.setTitle("新消息");
						detail.setDetail("您的 " + name + " 订单已经付款完成");
						detail.setLinkTitle("查看订单详情");
						detail.setAddtime(new Date());
						detail.setLinkType((byte) 5);
						detail.setLinkContent(""+orderAunt.getOrderid());
						sendSystemMessageToUser(auntPush.getUserid(), 2, null, detail);
						if (auntPush.getDevicetype() == 1) {

							JPushUtils.pushToAndroidById(new Gson().toJson(pushData), auntPush.getPushKey(),
									Constant.JIGUNAG_PUSH_AUNT_SECRET, Constant.JIGUAN_PUSH_AUNT_KEY);
						} else if (auntPush.getDevicetype() == 2) {
							// tokenList.add(auntPush.getPushKey());
							// TODO: 阿姨端推送证书修改
							IosPushDataBase base = new IosPushDataBase();
							base.setAlert("您的 " + name + " 订单已经付款完成");
							base.setBadge(1);
							base.setSound("default");
							IosPushDateModelBase model = new IosPushDateModelBase();
							model.setAps(base);
							model.setT(5);
							model.setD("orderid:" + orderAunt.getOrderid());
							IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 1);
						}
					}
				}
			}
		}
	}
	
	public void sendEndMessageToUser(Order order){
		PushData pushData = new PushData();
		CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
		String name = second.getName();
		pushData.setT("4");
		pushData.setM("您的 " + name + " 订单已经完成");
		pushData.setD("orderid:" + order.getOrderid());
		if (order.getPosterType() == 0) {
			UserPush userPush = userPushMapper.selectByUserId(order.getUserid());
			MessageDetail detail = new MessageDetail();
			detail.setSysMessageid(0);
			detail.setTitle("新消息");
			detail.setDetail("您的 " + name + " 订单已经完成");
			detail.setLinkTitle("查看订单详情");
			detail.setAddtime(new Date());
			detail.setLinkType((short)1);
			detail.setLinkContent(""+order.getOrderid());
			sendSystemMessageToUser(order.getUserid(), 1, detail, null);
			if (userPush.getDevicetype() == 1) {
				JPushUtils.pushToAndroidById(new Gson().toJson(pushData), userPush.getPushKey(),
						Constant.JIGUANG_PUSH_USER_SECRET, Constant.JIGUANG_PUSH_USER_KEY);
			} else {
				IosPushDataBase base = new IosPushDataBase();
				base.setAlert("您的 " + name + " 订单已经完成");
				base.setBadge(1);
				base.setSound("default");
				IosPushDateModelBase model = new IosPushDateModelBase();
				model.setAps(base);
				model.setT(4);
				model.setD("orderid:" + order.getOrderid());
				IOSPushUtils.sendPushToSingle(userPush.getPushKey(), new Gson().toJson(model), 0);
			}

		} else if (order.getPosterType() == 1) {
			Company company = companyMapper.selectByPrimaryKey(order.getUserid());
			AuntPush auntPush = auntPushMapper.selectByUserId(company.getCompanyid(), (short) 1);
			AuntMessageDetail detail = new AuntMessageDetail();
			detail.setSysMessageid(0);
			detail.setTitle("新消息");
			detail.setDetail("您的 " + name + " 订单已经完成");
			detail.setLinkTitle("查看订单详情");
			detail.setAddtime(new Date());
			detail.setLinkType((byte)5);
			detail.setLinkContent(""+order.getOrderid());
			sendSystemMessageToUser(company.getCompanyid(), 2, null, detail);
			if (auntPush.getDevicetype() == 1) {
				JPushUtils.pushToAndroidById(new Gson().toJson(pushData), auntPush.getPushKey(),
						Constant.JIGUANG_PUSH_USER_SECRET, Constant.JIGUANG_PUSH_USER_KEY);
			} else {
				IosPushDataBase base = new IosPushDataBase();
				base.setAlert("您的 " + name + " 订单已经完成");
				base.setBadge(1);
				base.setSound("default");
				IosPushDateModelBase model = new IosPushDateModelBase();
				model.setAps(base);
				model.setT(5);
				model.setD("orderid:" + order.getOrderid());
				IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 0);
			}
		}
		
	}
	
	public void sendCancelMessageToUser(Order order){
		PushData pushData = new PushData();
		CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
		String name = second.getName();
		pushData.setT("6");
		pushData.setM("您的 " + name + " 订单已经退单");
		pushData.setD("orderid:" + order.getOrderid());
		if (order.getPosterType() == 0) {
			UserPush userPush = userPushMapper.selectByUserId(order.getUserid());
			MessageDetail detail = new MessageDetail();
			detail.setSysMessageid(0);
			detail.setTitle("新消息");
			detail.setDetail("您的 " + name + " 订单已经退单");
			detail.setLinkTitle("查看订单详情");
			detail.setAddtime(new Date());
			detail.setLinkType((short)1);
			detail.setLinkContent(""+order.getOrderid());
			sendSystemMessageToUser(order.getUserid(), 1, detail, null);
			if (userPush.getDevicetype() == 1) {
				JPushUtils.pushToAndroidById(new Gson().toJson(pushData), userPush.getPushKey(),
						Constant.JIGUANG_PUSH_USER_SECRET, Constant.JIGUANG_PUSH_USER_KEY);
			} else {
				IosPushDataBase base = new IosPushDataBase();
				base.setAlert("您的 " + name + " 订单已经退单");
				base.setBadge(1);
				base.setSound("default");
				IosPushDateModelBase model = new IosPushDateModelBase();
				model.setAps(base);
				model.setT(6);
				model.setD("orderid:" + order.getOrderid());
				IOSPushUtils.sendPushToSingle(userPush.getPushKey(), new Gson().toJson(model), 0);
			}

		} else if (order.getPosterType() == 1) {
			Company company = companyMapper.selectByPrimaryKey(order.getUserid());
			AuntPush auntPush = auntPushMapper.selectByUserId(company.getCompanyid(), (short) 1);
			AuntMessageDetail detail = new AuntMessageDetail();
			detail.setSysMessageid(0);
			detail.setTitle("新消息");
			detail.setDetail("您的 " + name + " 订单已经退单");
			detail.setLinkTitle("查看订单详情");
			detail.setAddtime(new Date());
			detail.setLinkType((byte)1);
			detail.setLinkContent(""+order.getOrderid());
			sendSystemMessageToUser(company.getCompanyid(), 2, null, detail);
			if (auntPush.getDevicetype() == 1) {
				JPushUtils.pushToAndroidById(new Gson().toJson(pushData), auntPush.getPushKey(),
						Constant.JIGUANG_PUSH_USER_SECRET, Constant.JIGUANG_PUSH_USER_KEY);
			} else {
				IosPushDataBase base = new IosPushDataBase();
				base.setAlert("您的 " + name + " 订单已经退单");
				base.setBadge(1);
				base.setSound("default");
				IosPushDateModelBase model = new IosPushDateModelBase();
				model.setAps(base);
				model.setT(6);
				model.setD("orderid:" + order.getOrderid());
				IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 0);
			}
		}
	}
	
	public void sendCancelMessageToAunt(Order order){
		List<OrderAunt> auntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		PushData pushData = new PushData();
		if(auntList != null && auntList.size()>0){
			for(OrderAunt orderAunt:auntList){
				CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
				String name = second.getName();
				if(orderAunt.getAuntid() != null && orderAunt.getAuntid() >0 && orderAunt.getCompanyid() !=null && orderAunt.getCompanyid() == 0){
					//AuntExtra extra = auntExtramapper.selectByAuntId(orderAunt.getAuntid());
					AuntPush auntPush = auntPushMapper.selectByUserId(orderAunt.getAuntid(), (short) 0);
					if(auntPush != null){
						pushData.setT("6");
						pushData.setM("您的 " + name + " 订单已经退单");
						pushData.setD("orderid:" + orderAunt.getOrderid());
						AuntMessageDetail detail = new AuntMessageDetail();
						detail.setSysMessageid(0);
						detail.setTitle("新消息");
						detail.setDetail("您的 " + name + " 订单已经退单");
						detail.setLinkTitle("查看订单详情");
						detail.setAddtime(new Date());
						detail.setLinkType((byte) 5);
						detail.setLinkContent(""+orderAunt.getOrderid());
						sendSystemMessageToUser(auntPush.getUserid(), 2, null, detail);
						if (auntPush.getDevicetype() == 1) {

							JPushUtils.pushToAndroidById(new Gson().toJson(pushData), auntPush.getPushKey(),
									Constant.JIGUNAG_PUSH_AUNT_SECRET, Constant.JIGUAN_PUSH_AUNT_KEY);
						} else if (auntPush.getDevicetype() == 2) {
							// tokenList.add(auntPush.getPushKey());
							// TODO: 阿姨端推送证书修改
							IosPushDataBase base = new IosPushDataBase();
							base.setAlert("您的 " + name + " 订单已经退单");
							base.setBadge(1);
							base.setSound("default");
							IosPushDateModelBase model = new IosPushDateModelBase();
							model.setAps(base);
							model.setT(6);
							IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 1);
						}
					}
					
				}else if(orderAunt.getAuntid() != null && orderAunt.getAuntid() == 0 && orderAunt.getCompanyid() !=null && orderAunt.getCompanyid() > 0){
					//Company company = companyMapper.selectByPrimaryKey(orderAunt.getCompanyid());
					//CompanyExtra extra = companyExtraMapper.selectByCompanyId(company.getCompanyid());
					AuntPush auntPush = auntPushMapper.selectByUserId(orderAunt.getCompanyid(), (short) 1);
					if(auntPush != null){
						pushData.setT("6");
						pushData.setM("您的 " + name + " 订单已经退单");
						pushData.setD("orderid:" + orderAunt.getOrderid());
						AuntMessageDetail detail = new AuntMessageDetail();
						detail.setSysMessageid(0);
						detail.setTitle("新消息");
						detail.setDetail("您的 " + name + " 订单已经接单");
						detail.setLinkTitle("查看订单详情");
						detail.setAddtime(new Date());
						detail.setLinkType((byte) 5);
						detail.setLinkContent(""+orderAunt.getOrderid());
						sendSystemMessageToUser(auntPush.getUserid(), 2, null, detail);
						if (auntPush.getDevicetype() == 1) {

							JPushUtils.pushToAndroidById(new Gson().toJson(pushData), auntPush.getPushKey(),
									Constant.JIGUNAG_PUSH_AUNT_SECRET, Constant.JIGUAN_PUSH_AUNT_KEY);
						} else if (auntPush.getDevicetype() == 2) {
							// tokenList.add(auntPush.getPushKey());
							// TODO: 阿姨端推送证书修改
							IosPushDataBase base = new IosPushDataBase();
							base.setAlert("您的 " + name + " 订单已经退单");
							base.setBadge(1);
							base.setSound("default");
							IosPushDateModelBase model = new IosPushDateModelBase();
							model.setAps(base);
							model.setT(6);
							IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 1);
						}
					}
				}
			}
		}
	}
	
	public void sendRedPacketMessageToAunt(RedPacketRecord record){
		if(record.getUserType() == 1){
			AuntPush auntPush = auntPushMapper.selectByUserId(record.getUserid(), (short) 0);
			if(auntPush != null){
				PushData pushData = new PushData();
				pushData.setT("7");
				pushData.setM("您有新的红包");
				pushData.setD(record.getDataid()+";"+record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
				AuntMessageDetail detail = new AuntMessageDetail();
				detail.setSysMessageid(0);
				detail.setTitle("新红包");
				detail.setDetail("您有新的红包");
				detail.setLinkTitle("新红包");
				detail.setAddtime(new Date());
				detail.setLinkType((byte) 7);
				detail.setLinkContent( record.getDataid()+";"+record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
				sendSystemMessageToUser(auntPush.getUserid(), 2, null, detail);
				if (auntPush.getDevicetype() == 1) {

					JPushUtils.pushToAndroidById(new Gson().toJson(pushData), auntPush.getPushKey(),
							Constant.JIGUNAG_PUSH_AUNT_SECRET, Constant.JIGUAN_PUSH_AUNT_KEY);
				} else if (auntPush.getDevicetype() == 2) {
					// tokenList.add(auntPush.getPushKey());
					// TODO: 阿姨端推送证书修改
					IosPushDataBase base = new IosPushDataBase();
					base.setAlert("您有新的红包");
					base.setBadge(1);
					base.setSound("default");
					IosPushDateModelBase model = new IosPushDateModelBase();
					model.setAps(base);
					model.setT(7);
					model.setD( record.getDataid()+";"+record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
					IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 1);
				}
			}
		}else if(record.getUserType() == 2){
			AuntPush auntPush = auntPushMapper.selectByUserId(record.getUserid(), (short) 1);
			if(null != auntPush){
				PushData pushData = new PushData();
				pushData.setT("7");
				pushData.setM("您有新的红包");
				pushData.setD("packetid:" + record.getDataid()+";count"+record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
				AuntMessageDetail detail = new AuntMessageDetail();
				detail.setSysMessageid(0);
				detail.setTitle("新红包");
				detail.setDetail("您有新的红包");
				detail.setLinkTitle("新红包");
				detail.setAddtime(new Date());
				detail.setLinkType((byte) 7);
				detail.setLinkContent( record.getDataid()+";"+record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
				sendSystemMessageToUser(auntPush.getUserid(), 2, null, detail);
				if (auntPush.getDevicetype() == 1) {

					JPushUtils.pushToAndroidById(new Gson().toJson(pushData), auntPush.getPushKey(),
							Constant.JIGUNAG_PUSH_AUNT_SECRET, Constant.JIGUAN_PUSH_AUNT_KEY);
				} else if (auntPush.getDevicetype() == 2) {
					// tokenList.add(auntPush.getPushKey());
					// TODO: 阿姨端推送证书修改
					IosPushDataBase base = new IosPushDataBase();
					base.setAlert("您有新的红包");
					base.setBadge(1);
					base.setSound("default");
					IosPushDateModelBase model = new IosPushDateModelBase();
					model.setAps(base);
					model.setT(7);
					model.setD(record.getDataid()+";"+record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	public void sendOrderMessageToAunt(Order order, OrderPool orderPool) {

		PushData pushData = new PushData();
		pushData.setT("1");
		// String categoryName = "";
		CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
		if (orderPool.getUser_type() != null) {
			if (orderPool.getUser_type() == 0) {
				if (orderPool.getAuntid() != null && orderPool.getAuntid() > 0) {
					AuntExtra extra = auntExtramapper.selectByAuntId(orderPool.getAuntid());
					AuntPush auntPush = auntPushMapper.selectByUserId(orderPool.getAuntid(), (short) 0);
					if (auntPush != null) {
						String disTance = CommonUtils.getDistance(order.getLatitude().toString(),
								order.getLongitude().toString(), extra.getLatitude().toString(),
								extra.getLongitude().toString());
						String content = "距离你" + disTance + "公里," + order.getAddressname() + order.getAddressdetail()
								+ ",";
						content += "有一笔“" + second.getName() + "” 的订单,预约时间 "
								+ CommonUtils.getTimeFormat(order.getServerTime(), "yyyy-MM-dd HH:mm");
						pushData.setD(content);
						AuntMessageDetail detail = new AuntMessageDetail();
						detail.setSysMessageid(0);
						detail.setTitle("新订单");
						detail.setLinkTitle("查看订单详情");
						detail.setLinkType((byte) 1);
						detail.setLinkContent(""+order.getOrderid()+","+orderPool.getOrderid());
						detail.setDetail(content);
						detail.setAddtime(new Date());
						sendSystemMessageToUser(auntPush.getUserid(), 2, null, detail);
						if (auntPush.getDevicetype() == 1) {

							if (extra.getState() == 0) {
								JPushUtils.pushToAndroidById(new Gson().toJson(pushData), auntPush.getPushKey(),
										Constant.JIGUNAG_PUSH_AUNT_SECRET, Constant.JIGUAN_PUSH_AUNT_KEY);
							}
						} else if (auntPush.getDevicetype() == 2) {
							// tokenList.add(auntPush.getPushKey());
							// TODO: 阿姨端推送证书修改
							IosPushDataBase base = new IosPushDataBase();
							base.setAlert(content);
							base.setBadge(1);
							base.setSound("default");
							IosPushDateModelBase model = new IosPushDateModelBase();
							model.setAps(base);
							model.setT(1);
							if (extra.getState() == 0) {
								IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 1);
							}
						}
					}

				}
			} else if (orderPool.getUser_type() == 1) {
				if (orderPool.getAuntid() != null && orderPool.getAuntid() > 0) {
					Company company = companyMapper.selectByPrimaryKey(orderPool.getAuntid());
					CompanyExtra extra = companyExtraMapper.selectByCompanyId(company.getCompanyid());
					AuntPush auntPush = auntPushMapper.selectByUserId(orderPool.getAuntid(), (short) 1);
					if (auntPush != null) {
						String disTance = CommonUtils.getDistance(order.getLatitude().toString(),
								order.getLongitude().toString(), company.getLatitude().toString(),
								company.getLongitude().toString());
						String content = "距离你" + disTance + "公里," + order.getAddressname() + order.getAddressdetail()
								+ ",";
						content += "有一笔“" + second.getName() + "” 的订单,预约时间 "
								+ CommonUtils.getTimeFormat(order.getServerTime(), "yyyy-MM-dd HH:mm");
						pushData.setD(content);
						AuntMessageDetail detail = new AuntMessageDetail();
						detail.setSysMessageid(0);
						detail.setTitle("新订单");
						detail.setLinkTitle("查看订单详情");
						detail.setLinkContent(""+order.getOrderid()+","+orderPool.getOrderid());
						detail.setLinkType((byte) 1);
						detail.setDetail(content);
						detail.setAddtime(new Date());
						sendSystemMessageToUser(auntPush.getUserid(), 2, null, detail);
						if (auntPush.getDevicetype() == 1) {
							if (extra.getState() == 0) {
								JPushUtils.pushToAndroidById(new Gson().toJson(pushData), auntPush.getPushKey(),
										Constant.JIGUNAG_PUSH_AUNT_SECRET, Constant.JIGUAN_PUSH_AUNT_KEY);
							}

						} else if (auntPush.getDevicetype() == 2) {
							IosPushDataBase base = new IosPushDataBase();
							base.setAlert(content);
							base.setBadge(1);
							base.setSound("default");
							IosPushDateModelBase model = new IosPushDateModelBase();
							model.setAps(base);
							model.setT(1);
							if (extra.getState() == 0) {
								IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 1);
							}
						}
					}
				}
			}
		}

	}

	public void setTip(Order order, BigDecimal tip) {
		if (order.getTipPrice() != null) {
			order.setTipPrice(order.getTipPrice().add(tip));
		} else {
			order.setTipPrice(tip);
		}

		// OrderPool数据更新
		List<OrderPool> poolList = orderPoolMapper.selectListByUserOrderId(order.getOrderid());
		if (poolList != null) {
			for (OrderPool orderPool : poolList) {
				orderPool.setPriceTip(order.getTipPrice());
				orderPoolMapper.updateByPrimaryKey(orderPool);
			}
		}
		// 如果是多人接单的情况下可能部分人已经接单
		List<OrderAunt> orderauntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		if (orderauntList != null) {
			for (OrderAunt orderaunt : orderauntList) {
				orderaunt.setTipPrice(order.getTipPrice());
				orderAuntMapper.updateByPrimaryKey(orderaunt);
			}
		}

		orderMapper.updateByPrimaryKey(order);
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public void settlementBalance(Order order) {

		if (order.getState() == 6) {

			int cid = order.getCategoryid();
			if (cid == 1 || cid == 2 || cid == 8) {// 小时工普通类
				List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
				if (orderAuntList != null && orderAuntList.size() > 0) {
					for (OrderAunt orderAunt : orderAuntList) {
						BigDecimal decimal = new BigDecimal("0");
						if (order.getTipPrice() != null) {
							decimal = decimal.add(orderAunt.getTipPrice());
						}
						if (order.getOtherPirce() != null) {
							decimal = decimal.add(order.getOtherPirce());
						}
						BigDecimal excepedPrice = new BigDecimal("0");
						excepedPrice = excepedPrice.add(order.getExpectedPrice())
								.multiply(new BigDecimal(order.getExpectTime()));
						decimal = decimal.add(excepedPrice);
						BigDecimal rationDecimal = new BigDecimal(orderAunt.getRatio());
						rationDecimal = rationDecimal.multiply(new BigDecimal(order.getExpectTime()));
						if (orderAunt.getAuntid() == 0 && orderAunt.getCompanyid() > 0) {

							decimal = decimal.subtract(rationDecimal);
							if (decimal.compareTo(new BigDecimal("0")) == 1) {
								AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
								balanceRecode.setUserid(orderAunt.getCompanyid());
								balanceRecode.setUserType((byte) 1);
								balanceRecode.setType((byte) 2);
								balanceRecode.setCount(decimal);
								balanceRecode.setAddtime(new Date());
								auntBalanceRecordMapper.insert(balanceRecode);
								
								CompanyExtra extra = companyExtraMapper.selectByCompanyId(orderAunt.getCompanyid());
								extra.setBalance(extra.getBalance().add(decimal));
								extra.setUseTotal(extra.getUseTotal().add(decimal));
								companyExtraMapper.updateByPrimaryKey(extra);
							}

						} else if (orderAunt.getCompanyid() == 0 && orderAunt.getAuntid() > 0) {
							Aunt aunt = auntMapper.selectByPrimaryKey(orderAunt.getAuntid());
							if (aunt.getKingState() == 1) {
								if (decimal.compareTo(new BigDecimal("0")) == 1) {
									AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
									balanceRecode.setUserid(orderAunt.getAuntid());
									balanceRecode.setUserType((byte) 0);
									balanceRecode.setType((byte) 2);
									balanceRecode.setCount(decimal);
									balanceRecode.setAddtime(new Date());
									auntBalanceRecordMapper.insert(balanceRecode);
									
									AuntExtra extra = auntExtramapper.selectByAuntId(orderAunt.getAuntid());
									extra.setBalance(extra.getBalance().add(decimal));
									extra.setUseTotal(extra.getUseTotal().add(decimal));
									auntExtramapper.updateByPrimaryKey(extra);
								}
							} else {
								decimal = decimal.subtract(rationDecimal);
								if (decimal.compareTo(new BigDecimal("0")) == 1) {
									AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
									balanceRecode.setUserid(orderAunt.getAuntid());
									balanceRecode.setUserType((byte) 0);
									balanceRecode.setType((byte) 2);
									balanceRecode.setCount(decimal);
									balanceRecode.setAddtime(new Date());
									auntBalanceRecordMapper.insert(balanceRecode);
									
									AuntExtra extra = auntExtramapper.selectByAuntId(orderAunt.getAuntid());
									extra.setBalance(extra.getBalance().add(decimal));
									extra.setUseTotal(extra.getUseTotal().add(decimal));
									auntExtramapper.updateByPrimaryKey(extra);
								}
							}

						}

					}
				} //

			} else if (cid == 5 || cid == 6) {
				// 小时工多人
				
				List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
				if (orderAuntList != null && orderAuntList.size() > 0) {
					for (OrderAunt orderAunt : orderAuntList) {
						BigDecimal decimal = new BigDecimal("0");
						if (order.getTipPrice() != null) {
							decimal = decimal.add(orderAunt.getTipPrice()).divide(new BigDecimal(orderAuntList.size()),2,BigDecimal.ROUND_UP);
						}
						if (order.getOtherPirce() != null) {
							decimal = decimal.add(order.getOtherPirce()).divide(new BigDecimal(orderAuntList.size()),2,BigDecimal.ROUND_UP);
						}
						BigDecimal excepedPrice = new BigDecimal("0");
						if(cid == 5){
							excepedPrice = excepedPrice.add(order.getExpectedPrice())
									.multiply(new BigDecimal(order.getExpectTime()));
						}else if(cid == 6){
							excepedPrice = excepedPrice.add(order.getExpectedPrice())
									.multiply(new BigDecimal(order.getExpectTime())).multiply(new BigDecimal(order.getDayTime()));
						}
						decimal = decimal.add(excepedPrice);
						BigDecimal rationDecimal = new BigDecimal(orderAunt.getRatio());
						rationDecimal = rationDecimal.multiply(new BigDecimal(order.getExpectTime()));
						if (orderAunt.getAuntid() == 0 && orderAunt.getCompanyid() > 0) {

							decimal = decimal.subtract(rationDecimal);
							if (decimal.compareTo(new BigDecimal("0")) == 1) {
								Integer auntMCount = orderAunt.getAunt_m_count();
								Integer auntWCount = orderAunt.getAunt_w_count();
								if(auntMCount == null){
									auntMCount = 0;
								}
								if(auntWCount == null){
									auntWCount = 0;
								}
								AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
								balanceRecode.setUserid(orderAunt.getCompanyid());
								balanceRecode.setUserType((byte) 1);
								balanceRecode.setType((byte) 2);
								balanceRecode.setCount(decimal.multiply(new BigDecimal(auntMCount + auntWCount)));
								balanceRecode.setAddtime(new Date());
								auntBalanceRecordMapper.insert(balanceRecode);
								
								CompanyExtra extra = companyExtraMapper.selectByCompanyId(orderAunt.getCompanyid());
								extra.setBalance(extra.getBalance().add(decimal.multiply(new BigDecimal(auntMCount + auntWCount))));
								extra.setUseTotal(extra.getUseTotal().add(decimal.multiply(new BigDecimal(auntMCount + auntWCount))));
								companyExtraMapper.updateByPrimaryKey(extra);
								
							}

						} else if (orderAunt.getCompanyid() == 0 && orderAunt.getAuntid() > 0) {
							Aunt aunt = auntMapper.selectByPrimaryKey(orderAunt.getAuntid());
							if (aunt.getKingState() == 1) {
								if (decimal.compareTo(new BigDecimal("0")) == 1) {
									AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
									balanceRecode.setUserid(orderAunt.getAuntid());
									balanceRecode.setUserType((byte) 0);
									balanceRecode.setType((byte) 2);
									balanceRecode.setCount(decimal);
									balanceRecode.setAddtime(new Date());
									auntBalanceRecordMapper.insert(balanceRecode);
									
									AuntExtra extra = auntExtramapper.selectByAuntId(orderAunt.getAuntid());
									extra.setBalance(extra.getBalance().add(decimal));
									extra.setUseTotal(extra.getUseTotal().add(decimal));
									auntExtramapper.updateByPrimaryKey(extra);
								}
							} else {
								decimal = decimal.subtract(rationDecimal);
								if (decimal.compareTo(new BigDecimal("0")) == 1) {
									AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
									balanceRecode.setUserid(orderAunt.getAuntid());
									balanceRecode.setUserType((byte) 0);
									balanceRecode.setType((byte) 2);
									balanceRecode.setCount(decimal);
									balanceRecode.setAddtime(new Date());
									auntBalanceRecordMapper.insert(balanceRecode);
									
									AuntExtra extra = auntExtramapper.selectByAuntId(orderAunt.getAuntid());
									extra.setBalance(extra.getBalance().add(decimal));
									extra.setUseTotal(extra.getUseTotal().add(decimal));
									auntExtramapper.updateByPrimaryKey(extra);
								}
							}

						}

					}
				}
			} else if (cid == 3 || cid == 4 || cid == 7 || cid == 9 || cid == 10 || cid == 11 || cid == 12
					|| cid == 13) {// 上门费用类
				List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
				if (orderAuntList != null && orderAuntList.size() > 0) {
					for (OrderAunt orderAunt : orderAuntList) {
						BigDecimal decimal = new BigDecimal("0");
						if (order.getTipPrice() != null) {
							decimal = decimal.add(orderAunt.getTipPrice());
						}
						if (order.getOtherPirce() != null) {
							decimal = decimal.add(order.getOtherPirce());
						}
						if(order.getDepositPrice() != null){
							decimal = decimal.add(order.getDepositPrice());
						}
						BigDecimal excepedPrice = new BigDecimal("0");
						excepedPrice = excepedPrice.add(order.getLastPrice());
						decimal = decimal.add(excepedPrice);
						
						BigDecimal rationDecimal = new BigDecimal(orderAunt.getRatio());
						if (orderAunt.getAuntid() == 0 && orderAunt.getCompanyid() > 0) {
							decimal = decimal.subtract(rationDecimal);
							if (decimal.compareTo(new BigDecimal("0")) == 1) {
								AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
								balanceRecode.setUserid(orderAunt.getCompanyid());
								balanceRecode.setUserType((byte) 1);
								balanceRecode.setType((byte) 2);
								balanceRecode.setCount(decimal);
								balanceRecode.setAddtime(new Date());
								auntBalanceRecordMapper.insert(balanceRecode);
								
								CompanyExtra extra = companyExtraMapper.selectByCompanyId(orderAunt.getCompanyid());
								extra.setBalance(extra.getBalance().add(decimal));
								extra.setUseTotal(extra.getUseTotal().add(decimal));
								companyExtraMapper.updateByPrimaryKey(extra);
							}

						} else if (orderAunt.getCompanyid() == 0 && orderAunt.getAuntid() > 0) {
							Aunt aunt = auntMapper.selectByPrimaryKey(orderAunt.getAuntid());
							if (aunt.getKingState() == 1) {
								if (decimal.compareTo(new BigDecimal("0")) == 1) {
									AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
									balanceRecode.setUserid(orderAunt.getAuntid());
									balanceRecode.setUserType((byte) 0);
									balanceRecode.setType((byte) 2);
									balanceRecode.setCount(decimal);
									balanceRecode.setAddtime(new Date());
									auntBalanceRecordMapper.insert(balanceRecode);
									
									AuntExtra extra = auntExtramapper.selectByAuntId(orderAunt.getAuntid());
									extra.setBalance(extra.getBalance().add(decimal));
									extra.setUseTotal(extra.getUseTotal().add(decimal));
									auntExtramapper.updateByPrimaryKey(extra);
								}
							} else {
								decimal = decimal.subtract(rationDecimal);
								if (decimal.compareTo(new BigDecimal("0")) == 1) {
									AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
									balanceRecode.setUserid(orderAunt.getAuntid());
									balanceRecode.setUserType((byte) 0);
									balanceRecode.setType((byte) 2);
									balanceRecode.setCount(decimal);
									balanceRecode.setAddtime(new Date());
									auntBalanceRecordMapper.insert(balanceRecode);
									
									AuntExtra extra = auntExtramapper.selectByAuntId(orderAunt.getAuntid());
									extra.setBalance(extra.getBalance().add(decimal));
									extra.setUseTotal(extra.getUseTotal().add(decimal));
									auntExtramapper.updateByPrimaryKey(extra);
								}
							}

						}

					}
				}
			} else if (cid == 14 || cid == 15 || cid == 16 || cid == 17 || cid == 18) {// 长期
				List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
				if (orderAuntList != null && orderAuntList.size() > 0) {
					for (OrderAunt orderAunt : orderAuntList) {
						BigDecimal decimal = new BigDecimal("0");
						if (order.getTipPrice() != null) {
							decimal = decimal.add(orderAunt.getTipPrice());
						}
						if (order.getOtherPirce() != null) {
							decimal = decimal.add(order.getOtherPirce());
						}
						BigDecimal excepedPrice = new BigDecimal("0");
						excepedPrice = excepedPrice.add(order.getExpectedPrice())
								.multiply(new BigDecimal(order.getExpectTime()));
						decimal = decimal.add(excepedPrice);
						BigDecimal rationDecimal = new BigDecimal(orderAunt.getRatio());
						if (orderAunt.getAuntid() == 0 && orderAunt.getCompanyid() > 0) {
							decimal = decimal.multiply(rationDecimal);
							if (decimal.compareTo(new BigDecimal("0")) == 1) {
								AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
								balanceRecode.setUserid(orderAunt.getCompanyid());
								balanceRecode.setUserType((byte) 1);
								balanceRecode.setType((byte) 2);
								balanceRecode.setCount(decimal);
								balanceRecode.setAddtime(new Date());
								auntBalanceRecordMapper.insert(balanceRecode);
								
								CompanyExtra extra = companyExtraMapper.selectByCompanyId(orderAunt.getCompanyid());
								extra.setBalance(extra.getBalance().add(decimal));
								extra.setUseTotal(extra.getUseTotal().add(decimal));
								companyExtraMapper.updateByPrimaryKey(extra);
							}

						} else if (orderAunt.getCompanyid() == 0 && orderAunt.getAuntid() > 0) {
							Aunt aunt = auntMapper.selectByPrimaryKey(orderAunt.getAuntid());
							if (aunt.getKingState() == 1) {
								if (decimal.compareTo(new BigDecimal("0")) == 1) {
									AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
									balanceRecode.setUserid(orderAunt.getAuntid());
									balanceRecode.setUserType((byte) 0);
									balanceRecode.setType((byte) 2);
									balanceRecode.setCount(decimal);
									balanceRecode.setAddtime(new Date());
									auntBalanceRecordMapper.insert(balanceRecode);
									
									AuntExtra extra = auntExtramapper.selectByAuntId(orderAunt.getAuntid());
									extra.setBalance(extra.getBalance().add(decimal));
									extra.setUseTotal(extra.getUseTotal().add(decimal));
									auntExtramapper.updateByPrimaryKey(extra);
								}
							} else {
								decimal = decimal.multiply(rationDecimal);
								if (decimal.compareTo(new BigDecimal("0")) == 1) {
									AuntBalanceRecord balanceRecode = new AuntBalanceRecord();
									balanceRecode.setUserid(orderAunt.getAuntid());
									balanceRecode.setUserType((byte) 0);
									balanceRecode.setType((byte) 2);
									balanceRecode.setCount(decimal);
									balanceRecode.setAddtime(new Date());
									auntBalanceRecordMapper.insert(balanceRecode);
									
									AuntExtra extra = auntExtramapper.selectByAuntId(orderAunt.getAuntid());
									extra.setBalance(extra.getBalance().add(decimal));
									extra.setUseTotal(extra.getUseTotal().add(decimal));
									auntExtramapper.updateByPrimaryKey(extra);
								}
							}

						}

					}
				}

			}
			// return decimal;
		}
	}

}