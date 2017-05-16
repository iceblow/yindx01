package com.uncleserver.service.api.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.common.push.IOSPushUtils;
import com.uncleserver.common.push.IosPushDataBase;
import com.uncleserver.common.push.IosPushDateModelBase;
import com.uncleserver.common.push.JPushUtils;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.AuntBanner;
import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.AuntMessageDetail;
import com.uncleserver.model.AuntPush;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.model.MessageDetail;
import com.uncleserver.model.Order;
import com.uncleserver.model.OrderAunt;
import com.uncleserver.model.OrderComplaint;
import com.uncleserver.model.OrderPool;
import com.uncleserver.model.Ratio;
import com.uncleserver.model.User;
import com.uncleserver.model.UserPush;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.AuntHomeOrderModel;
import com.uncleserver.modelVo.HomeBannerValue;
import com.uncleserver.modelVo.PushData;
import com.uncleserver.service.api.AuntHomeService;

@Service("auntHomeService")
public class AuntHomeServiceImpl extends BaseServiceImpl implements AuntHomeService {

	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

	// private ScheduledExecutorService scheduledUserService =
	// Executors.newScheduledThreadPool(10);
	//
	// private ScheduledExecutorService scheduledAuntService =
	// Executors.newScheduledThreadPool(10);

	@Override
	public ApiResult getBanner() {
		ApiResult result = new ApiResult();

		List<HomeBannerValue> bannerValues = new ArrayList<>();

		List<AuntBanner> bannerList = auntBannerMapper.selectBannerList();
		if (bannerList != null && bannerList.size() > 0) {
			for (AuntBanner auntBanner : bannerList) {
				HomeBannerValue homeBannerValue = new HomeBannerValue();
				homeBannerValue.setBannerid(auntBanner.getBannerid());
				homeBannerValue.setType(auntBanner.getType());
				homeBannerValue.setContentid(auntBanner.getContentid());
				if (auntBanner.getPicid() != null && auntBanner.getPicid() > 0) {
					homeBannerValue.setPicurl(getFilePathById(auntBanner.getPicid()));
				}
				bannerValues.add(homeBannerValue);
			}

		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("bannerList", bannerValues);

		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult setState(String state, CompanyExtra extra) {
		ApiResult result = new ApiResult();

		short stateInt = CommonUtils.parseShort(state, (short) 0);
		if (stateInt != 0 && stateInt != 1) {
			result.setCode("2");
			result.setMessage("请求失败,状态错误");
		}
		extra.setState(stateInt);
		companyExtraMapper.updateByPrimaryKey(extra);

		result.setCode("1");
		result.setMessage("请求成功");

		HashMap<String, Object> map = new HashMap<>();
		map.put("state", extra.getState());
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult setState(String state, AuntExtra extra) {
		ApiResult result = new ApiResult();

		short stateInt = CommonUtils.parseShort(state, (short) 0);
		if (stateInt != 0 && stateInt != 1) {
			result.setCode("2");
			result.setMessage("请求失败,状态错误");
		}
		extra.setState(stateInt);
		auntExtramapper.updateByPrimaryKey(extra);

		result.setCode("1");
		result.setMessage("请求成功");

		HashMap<String, Object> map = new HashMap<>();
		map.put("state", extra.getState());
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult getOrderList(int userid, int usertype, String longitude, String latitude) {
		ApiResult result = new ApiResult();

		List<AuntHomeOrderModel> orderList = new ArrayList<>();

		List<OrderPool> poolList = orderPoolMapper.selectListByUserIdAndType(userid, usertype);
		if (poolList != null && poolList.size() > 0) {
			for (OrderPool orderPool : poolList) {

				AuntHomeOrderModel model = new AuntHomeOrderModel();
				model.setAddress(orderPool.getAddress());
				model.setAddtime(CommonUtils.getTimeFormat(orderPool.getAddtime(), "yyyy-MM-dd HH:mm"));

				if (orderPool.getLatitude() > 0 && orderPool.getLongitude() > 0
						&& !CommonUtils.isEmptyAllString(longitude, latitude)
						&& CommonUtils.parseFloat(longitude, 0f) > 0 && CommonUtils.parseFloat(latitude, 0f) > 0) {
					model.setDistance(CommonUtils.getDistance(String.valueOf(orderPool.getLatitude()),
							String.valueOf(orderPool.getLongitude()), latitude, longitude));
				}

				model.setTotime(orderPool.getTotime());
				model.setOrder_type(orderPool.getOrderType());
				model.setOrderid(orderPool.getOrderid());
				model.setPrice(orderPool.getPrice().doubleValue() + "元");
				model.setTip_state(orderPool.getTipState());
				model.setTitle(orderPool.getTitle());
				model.setOrderid_user(orderPool.getOrderid_user());
				model.setCategoryid(orderPool.getCategoryid());
				if(orderPool.getPriceTip() != null){
					model.setPrice_tip(orderPool.getPriceTip().toPlainString());
				}
				if (orderPool.getAunt_m_count() != null) {
					model.setAunt_m_count(orderPool.getAunt_m_count());
				}
				if (orderPool.getAunt_w_count() != null) {
					model.setAunt_w_count(orderPool.getAunt_w_count());
				}
				orderList.add(model);
			}
		}
		result.setCode("1");
		result.setMessage("请求成功");
		HashMap<String, Object> map = new HashMap<>();
		map.put("orderList", orderList);
		result.setResult(map);

		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult acceptOrder(int userid, int usertype, int orderid, int orderid_user, int auntMCount,
			int auntWCount) {
		ApiResult result = new ApiResult();

		OrderPool orderPool = orderPoolMapper.selectByPrimaryKey(orderid);
		final Order order = orderMapper.selectByPrimaryKey(orderid_user);
		if (order == null) {
			result.setCode("2");
			result.setMessage("接单失败,订单不存在");
			return result;
		}

		if (usertype == 0) {// 阿姨

			Long orderCount = orderAuntMapper.getAuntInServiceOrderCount(userid, usertype);
			if (orderCount != null && orderCount > 0) {
				result.setCode("2");
				result.setMessage("有服务中订单,无法接单");
				return result;
			}
		}
		if (orderPool == null) {
			if (order.getState() == 7) {
				result.setCode("3");
				if (order.getAcceptType() == 0) {
					result.setMessage("接单失败,订单已退单");
				} else {
					result.setMessage("抢单失败,订单已退单");
				}

				return result;
			} else if (order.getState() == 9) {
				result.setCode("4");
				result.setMessage("抢单失败,订单已失效");
				return result;
			} else {
				result.setCode("5");
				result.setMessage("抢单失败,订单已被别人抢走了");
				return result;
			}
		}

		if (order.getCategoryid() == 5 || order.getCategoryid() == 6) {
			result = acceptOrderByMultiAunt(order, userid, usertype, auntMCount, auntWCount);
		} else {
			// 删除OrderPool
			orderPoolMapper.deleteByUserOrderId(orderid_user);
			// 更新订单状态
			order.setState((short) 2);
			order.setAcceptTime(new Date());
			orderMapper.updateByPrimaryKey(order);
			final int order_id = order.getOrderid();
			/**
			 * 发送接单消息给用户
			 */
			// 生成阿姨端订单
			OrderAunt orderAunt = new OrderAunt();
			orderAunt.setAcceptTime(new Date());
			orderAunt.setAcceptType(order.getAcceptType());
			orderAunt.setAddressdetail(order.getAddressdetail());
			orderAunt.setAddressname(order.getAddressname());
			orderAunt.setAddtime(new Date());
			if (usertype == 0) {
				orderAunt.setAuntid(userid);
			} else {
				orderAunt.setAuntid(0);
			}
			orderAunt.setBook(order.getBook());
			orderAunt.setCategoryid(order.getCategoryid());
			orderAunt.setCommentState(order.getCommentState());
			if (usertype == 0) {
				orderAunt.setCompanyid(0);
			} else {
				orderAunt.setCompanyid(userid);
			}
			orderAunt.setComplaintState(order.getComplaintState());
			orderAunt.setCouponid(order.getCouponid());
			orderAunt.setCouponPirce(order.getCouponPirce());
			orderAunt.setDayTime(order.getDayTime());
			orderAunt.setDepositPrice(order.getDepositPrice());
			orderAunt.setExpectedPrice(order.getExpectedPrice());
			orderAunt.setExpectTime(order.getExpectTime());
			orderAunt.setFoodselect(order.getFoodselect());
			orderAunt.setLastPrice(order.getLastPrice());
			orderAunt.setLatitude(order.getLatitude());
			orderAunt.setLongitude(order.getLongitude());
			orderAunt.setNeedTools(order.getNeedTools());

			Long count = orderAuntMapper.selectOrderNumCount();
			String ordernum = createOrderNum(4, count);
			orderAunt.setOrdernum(ordernum);

			orderAunt.setOrderSource(order.getOrderSource());
			orderAunt.setOrderType(order.getOrderType());
			orderAunt.setOtherPirce(order.getOtherPirce());
			orderAunt.setPayTime2(order.getPayTime2());
			orderAunt.setPhone(order.getPhone());
			orderAunt.setPicIds(order.getPicIds());
			orderAunt.setPosterType(order.getPosterType());
			orderAunt.setRatio(order.getRatio());
			orderAunt.setRatioMoney(order.getRatioMoney());
			orderAunt.setReasonMark(order.getReasonMark());
			orderAunt.setBook(order.getBook());
			orderAunt.setRelationOrderid(order.getRelationOrderid());
			orderAunt.setRname(order.getRname());
			orderAunt.setSex(order.getSex());
			orderAunt.setState((short) 2);
			orderAunt.setThingCount(order.getThingCount());
			orderAunt.setTipPrice(order.getTipPrice());
			orderAunt.setUserid(order.getUserid());
			orderAunt.setUserOrderid(order.getOrderid());
			orderAunt.setServerTime(order.getServerTime());
			orderAunt.setFoodselect(order.getFoodselect());
			setRatioMoney(orderAunt);
			Integer server_type = 1;
			if (usertype == 0) {
				server_type = 1;
			} else if (usertype == 1) {
				server_type = 2;
			}
			orderAuntMapper.insert(orderAunt);
			result.setCode("1");
			if (order.getAcceptType() == 0) {
				result.setMessage("接单成功");
			} else {
				result.setMessage("抢单成功");
			}

			fixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {

					sendMessageToUser(order);
				}
			});

		}
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult refuseOrder(String userid, String user_type, String orderid, String orderid_user, String reason,
			String content) {
		ApiResult result = new ApiResult();
		int orderidInt = CommonUtils.parseInt(orderid, 0);
		short userTypeInt = CommonUtils.parseShort(user_type, (short) 0);
		int useridInt = CommonUtils.parseInt(userid, 0);
		int orderidUserInt = CommonUtils.parseInt(orderid_user, 0);
		OrderPool orderPool = orderPoolMapper.selectByPrimaryKey(orderidInt);
		Order order = orderMapper.selectByPrimaryKey(orderidUserInt);

		if (order == null) {
			result.setCode("2");
			result.setMessage("拒单失败,订单不存在");
			return result;
		}
		if (orderPool == null) {
			if (order.getState() == 7) {
				result.setCode("3");
				result.setMessage("拒单失败,订单已退单");
				return result;
			} else if (order.getState() == 9) {
				result.setCode("4");
				result.setMessage("单失败,订单已失效");
				return result;
			}
		}

		if (order.getCategoryid() == 5 || order.getCategoryid() == 6) {

			// 指定
			List<OrderAunt> orderAuntList = orderAuntMapper.selectAcceptByUserOrderId(order.getOrderid());
			List<OrderAunt> refuseorderAuntList = orderAuntMapper.selectRefuseByUserOrderId(order.getOrderid());
			int needPeople = order.getAuntMCount();
			int alredyAcceptCount = 0;
			int refuseCount = 0;
			if (orderAuntList == null) {
				alredyAcceptCount = 0;
			} else {
				alredyAcceptCount = orderAuntList.size();
			}
			if (refuseorderAuntList == null) {
				refuseCount = 0;
			} else {
				refuseCount = refuseorderAuntList.size();
			}

			if (refuseCount > 0) {

				if (refuseCount + 1 == needPeople) {
					order.setState((short) 8);
					order.setAcceptTime(new Date());
					orderMapper.updateByPrimaryKey(order);
					Integer userType = new Integer(userTypeInt);
					orderPoolMapper.deleteAuntOrCompanyData(orderidUserInt, useridInt, userTypeInt);
					doRefuseOrderByMultiAunt(order, useridInt, userType, content, reason);

				} else {
					if (alredyAcceptCount + refuseCount + 1 == needPeople) {
						// 更新订单状态
						order.setState((short) 10);
						order.setAcceptTime(new Date());
						orderMapper.updateByPrimaryKey(order);
						Integer userType = new Integer(userTypeInt);
						orderPoolMapper.deleteAuntOrCompanyData(orderidUserInt, useridInt, userTypeInt);
						doRefuseOrderByMultiAunt(order, useridInt, userType, content, reason);
						List<OrderAunt> alOrderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
						if (alOrderAuntList != null && alOrderAuntList.size() > 0) {
							for (OrderAunt orderAunt : alOrderAuntList) {
								orderAunt.setState((short) 10);
								orderAunt.setAcceptTime(new Date());
								orderAuntMapper.updateByPrimaryKey(orderAunt);
							}
						}
					} else {
						Integer userType = new Integer(userTypeInt);
						orderPoolMapper.deleteAuntOrCompanyData(orderidUserInt, useridInt, userTypeInt);
						doRefuseOrderByMultiAunt(order, useridInt, userType, content, reason);
					}
				}

			} else {

				if (refuseCount + 1 == needPeople) {
					order.setState((short) 8);
					order.setAcceptTime(new Date());
					orderMapper.updateByPrimaryKey(order);
					Integer userType = new Integer(userTypeInt);
					orderPoolMapper.deleteAuntOrCompanyData(orderidUserInt, useridInt, userTypeInt);
					doRefuseOrderByMultiAunt(order, useridInt, userType, content, reason);

				} else {
					if (alredyAcceptCount + 1 == needPeople) {

						// 更新订单状态
						order.setState((short) 10);
						order.setAcceptTime(new Date());
						orderMapper.updateByPrimaryKey(order);
						Integer userType = new Integer(userTypeInt);
						orderPoolMapper.deleteAuntOrCompanyData(orderidUserInt, useridInt, userTypeInt);
						doRefuseOrderByMultiAunt(order, useridInt, userType, content, reason);
						List<OrderAunt> alOrderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
						if (alOrderAuntList != null && alOrderAuntList.size() > 0) {
							for (OrderAunt orderAunt : alOrderAuntList) {
								orderAunt.setState((short) 10);
								orderAunt.setAcceptTime(new Date());
								orderAuntMapper.updateByPrimaryKey(orderAunt);
							}
						}

					} else {
						Integer userType = new Integer(userTypeInt);
						orderPoolMapper.deleteAuntOrCompanyData(orderidUserInt, useridInt, userTypeInt);
						doRefuseOrderByMultiAunt(order, useridInt, userType, content, reason);
					}
				}
			}
		} else {

			// 修改订单状态
			// 删除OrderPool
			orderPoolMapper.deleteByUserOrderId(orderidUserInt);
			// 更新订单状态
			order.setState((short) 8);
			orderMapper.updateByPrimaryKey(order);
			// TODO
			// 退款操作

			// 添加拒单记录
			OrderComplaint orderComplaint = new OrderComplaint();
			orderComplaint.setAddtime(new Date());
			orderComplaint.setContent(content);
			orderComplaint.setDataType((short) 1);
			orderComplaint.setOrderid(order.getOrderid());
			orderComplaint.setTitle(reason);
			orderComplaint.setUserid(useridInt);
			orderComplaint.setUserType(userTypeInt);
			orderComplaintMapper.insert(orderComplaint);

		}

		result.setCode("1");
		result.setMessage("拒单成功");
		return result;
	}

	private void sendMessageToUser(Order order) {
		PushData data = new PushData();
		CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
		String name = second.getName();
		data.setT("1");
		data.setM("您的 " + name + " 订单已经被接单");
		data.setD("orderid:" + order.getOrderid());
		if (order.getPosterType() == 0) {
			User user = userMapper.selectByPrimaryKey(order.getUserid());
			UserPush userPush = userPushMapper.selectByUserId(user.getUserid());
			MessageDetail detail = new MessageDetail();
			detail.setSysMessageid(0);
			detail.setTitle("新消息");
			detail.setDetail("您的 " + name + " 订单已经被接单");
			detail.setLinkTitle("查看订单详情");
			detail.setAddtime(new Date());
			detail.setLinkType((short)1);
			detail.setLinkContent(""+order.getOrderid());
			sendSystemMessageToUser(user.getUserid(), 1, detail, null);
			if (userPush.getDevicetype() == 1) {
				JPushUtils.pushToAndroidById(new Gson().toJson(data), userPush.getPushKey(),
						Constant.JIGUANG_PUSH_USER_SECRET, Constant.JIGUANG_PUSH_USER_KEY);
			} else {
				IosPushDataBase base = new IosPushDataBase();
				base.setAlert("您的 " + name + " 订单已经被接单");
				base.setBadge(1);
				base.setSound("default");
				IosPushDateModelBase model = new IosPushDateModelBase();
				model.setAps(base);
				model.setT(1);
				model.setD("orderid:" + order.getOrderid());
				IOSPushUtils.sendPushToSingle(userPush.getPushKey(), new Gson().toJson(model), 0);
			}

		} else if (order.getPosterType() == 1) {
			Company company = companyMapper.selectByPrimaryKey(order.getUserid());
			AuntPush auntPush = auntPushMapper.selectByUserId(company.getCompanyid(), (short) 1);
			AuntMessageDetail detail = new AuntMessageDetail();
			detail.setSysMessageid(0);
			detail.setTitle("新消息");
			detail.setDetail("您的 " + name + " 订单已经被接单");
			detail.setLinkTitle("查看订单详情");
			detail.setAddtime(new Date());
			detail.setLinkContent(""+order.getOrderid());
			sendSystemMessageToUser(company.getCompanyid(), 2, null, detail);
			if (auntPush.getDevicetype() == 1) {
				JPushUtils.pushToAndroidById(new Gson().toJson(data), auntPush.getPushKey(),
						Constant.JIGUANG_PUSH_USER_SECRET, Constant.JIGUANG_PUSH_USER_KEY);
			} else {
				IosPushDataBase base = new IosPushDataBase();
				base.setAlert("您的 " + name + " 订单已经被接单");
				base.setBadge(1);
				base.setSound("default");
				IosPushDateModelBase model = new IosPushDateModelBase();
				model.setAps(base);
				model.setT(1);
				model.setD("orderid:" + order.getOrderid());
				IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 0);
			}
		}
	}

	// @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	private ApiResult acceptOrderByMultiAunt(Order order, Integer userid, Integer userType, int aunt_m_count,
			int aunt_w_count) {
		ApiResult apiResult = new ApiResult();
		apiResult.setCode("1");
		if (order.getAcceptType() == 0) {
			apiResult.setMessage("接单成功");
		} else {
			apiResult.setMessage("抢单成功");
		}
		if (order.getAcceptType() == 0) {
			// 指定
			List<OrderAunt> orderAuntList = orderAuntMapper.selectAcceptByUserOrderId(order.getOrderid());
			List<OrderAunt> refuseorderAuntList = orderAuntMapper.selectRefuseByUserOrderId(order.getOrderid());
			int needPeople = order.getAuntMCount();
			int alredyAcceptCount = 0;
			int refuseCount = 0;
			if (orderAuntList == null) {
				alredyAcceptCount = 0;
			} else {
				alredyAcceptCount = orderAuntList.size();
			}
			if (refuseorderAuntList == null) {
				refuseCount = 0;
			} else {
				refuseCount = refuseorderAuntList.size();
			}

			if (refuseCount > 0) {

				if (alredyAcceptCount + refuseCount + 1 == needPeople) {
					order.setState((short) 10);
					order.setAcceptTime(new Date());
					orderMapper.updateByPrimaryKey(order);
					doAcceptOrderByMultiAunt(order, userid, userType);
					List<OrderAunt> alOrderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
					if (alOrderAuntList != null && alOrderAuntList.size() > 0) {
						for (OrderAunt orderAunt : alOrderAuntList) {
							orderAunt.setState((short) 10);
							orderAunt.setAcceptTime(new Date());
							orderAuntMapper.updateByPrimaryKey(orderAunt);
						}
					}
					orderPoolMapper.deleteAuntOrCompanyData(order.getOrderid(), userid, userType);

				} else {
					doAcceptOrderByMultiAunt(order, userid, userType);
					orderPoolMapper.deleteAuntOrCompanyData(order.getOrderid(), userid, userType);
				}

			} else {

				if (alredyAcceptCount + 1 == needPeople) {
					// 更新订单状态
					order.setState((short) 2);
					order.setAcceptTime(new Date());
					orderMapper.updateByPrimaryKey(order);
					doAcceptOrderByMultiAunt(order, userid, userType);
					List<OrderAunt> alOrderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
					if (alOrderAuntList != null && alOrderAuntList.size() > 0) {
						for (OrderAunt orderAunt : alOrderAuntList) {
							orderAunt.setState((short) 2);
							orderAunt.setAcceptTime(new Date());
							orderAuntMapper.updateByPrimaryKey(orderAunt);
						}
					}
					orderPoolMapper.deleteAuntOrCompanyData(order.getOrderid(), userid, userType);

				} else {

					doAcceptOrderByMultiAunt(order, userid, userType);
					orderPoolMapper.deleteAuntOrCompanyData(order.getOrderid(), userid, userType);

				}

			}

		} else if (order.getAcceptType() == 1) {
			// 抢单的情况
			if (userType == 0) {
				// 阿姨接单
				int auntMCount = order.getAuntMCount(); // 需要男性数量
				int auntWCount = order.getAuntWCount(); // 需要女性数量
				int totalNeedCount = auntMCount + auntWCount;
				// int alAcceptCount = 0;

				Long alMCount = orderAuntMapper.selectAuntMCount(order.getOrderid());
				Long alWCount = orderAuntMapper.selectAuntWCount(order.getOrderid());
				long alMCountL = 0;
				long alWCountL = 0;
				if (alMCount == null) {

				} else {
					alMCountL = alMCount;
				}
				if (alWCount == null) {

				} else {
					alWCountL = alWCount;
				}
				int alMCountI = (int) alMCountL;
				int alWCountI = (int) alWCountL;

				List<OrderAunt> auntList = orderAuntMapper.selectSingleAuntAcceptByUserOrderId(order.getOrderid());
				if (auntList != null && auntList.size() > 0) {
					for (OrderAunt orderAunt : auntList) {
						Aunt aunt = auntMapper.selectByPrimaryKey(orderAunt.getAuntid());
						if (aunt != null) {
							if (!CommonUtils.isEmptyString(aunt.getSex())) {
								if (aunt.getSex().equals("男")) {
									alMCountI++;
								} else {
									alWCountI++;
								}
							} else {
								alMCountI++;
							}
						}
					}
				}

				Aunt aunt = auntMapper.selectByPrimaryKey(userid);
				if (alMCountI + alWCountI + 1 == totalNeedCount) {

					if (!CommonUtils.isEmptyString(aunt.getSex())) {
						if (aunt.getSex().equals("男")) {
							if (alMCountI >= auntMCount) {
								apiResult.setCode("3");
								apiResult.setMessage("接单失败接单性别不能为男");
							} else {
								order.setState((short) 2);
								order.setAcceptTime(new Date());
								orderMapper.updateByPrimaryKey(order);
								doAcceptOrderByMultiAunt(order, userid, userType);
								List<OrderAunt> alOrderAuntList = orderAuntMapper
										.selectByUserOrderId(order.getOrderid());
								if (alOrderAuntList != null && alOrderAuntList.size() > 0) {
									for (OrderAunt orderAunt : alOrderAuntList) {
										orderAunt.setState((short) 2);
										orderAunt.setAcceptTime(new Date());
										orderAuntMapper.updateByPrimaryKey(orderAunt);
									}
								}
								orderPoolMapper.deleteByUserOrderId(order.getOrderid());

							}
						} else {

							if (alWCountI >= auntWCount) {
								apiResult.setCode("2");
								apiResult.setMessage("接单失败接单性别不能为女");
							} else {
								order.setState((short) 2);
								order.setAcceptTime(new Date());
								orderMapper.updateByPrimaryKey(order);
								doAcceptOrderByMultiAunt(order, userid, userType);
								List<OrderAunt> alOrderAuntList = orderAuntMapper
										.selectByUserOrderId(order.getOrderid());
								if (alOrderAuntList != null && alOrderAuntList.size() > 0) {
									for (OrderAunt orderAunt : alOrderAuntList) {
										orderAunt.setState((short) 2);
										orderAunt.setAcceptTime(new Date());
										orderAuntMapper.updateByPrimaryKey(orderAunt);
									}
								}
								orderPoolMapper.deleteByUserOrderId(order.getOrderid());
							}
						}

					} else {
						if (alMCountI >= auntMCount) {
							apiResult.setCode("3");
							apiResult.setMessage("接单失败接单性别不能为男");
						} else {
							order.setState((short) 2);
							order.setAcceptTime(new Date());
							orderMapper.updateByPrimaryKey(order);
							doAcceptOrderByMultiAunt(order, userid, userType);
							List<OrderAunt> alOrderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
							if (alOrderAuntList != null && alOrderAuntList.size() > 0) {
								for (OrderAunt orderAunt : alOrderAuntList) {
									orderAunt.setState((short) 2);
									orderAunt.setAcceptTime(new Date());
									orderAuntMapper.updateByPrimaryKey(orderAunt);
								}
							}
							orderPoolMapper.deleteAuntOrCompanyData(order.getOrderid(), userid, userType);

						}
					}
				} else {

					if (!CommonUtils.isEmptyString(aunt.getSex())) {
						if (aunt.getSex().equals("男")) {
							if (alMCountI >= auntMCount) {
								apiResult.setCode("3");
								apiResult.setMessage("接单失败接单性别不能为男");
							} else {
								doAcceptOrderByMultiAunt(order, userid, userType);
								orderPoolMapper.deleteAuntOrCompanyData(order.getOrderid(), userid, userType);
								List<OrderPool> poolList = orderPoolMapper.selectListByOrderUser(order.getOrderid(), 1);
								if (poolList != null && poolList.size() > 0) {
									for (OrderPool pool : poolList) {
										int mcount = pool.getAunt_m_count();
										mcount--;
										if (mcount < 0) {
											mcount = 0;
										}
										pool.setAunt_m_count(mcount);
										orderPoolMapper.updateByPrimaryKey(pool);
									}
								}

							}
						} else {

							if (alWCountI >= auntWCount) {
								apiResult.setCode("2");
								apiResult.setMessage("接单失败接单性别不能为女");
							} else {
								doAcceptOrderByMultiAunt(order, userid, userType);
								orderPoolMapper.deleteAuntOrCompanyData(order.getOrderid(), userid, userType);
								List<OrderPool> poolList = orderPoolMapper.selectListByOrderUser(order.getOrderid(), 1);
								if (poolList != null && poolList.size() > 0) {
									for (OrderPool pool : poolList) {
										int wcount = pool.getAunt_w_count();
										wcount--;
										if (wcount < 0) {
											wcount = 0;
										}
										pool.setAunt_w_count(wcount);
										orderPoolMapper.updateByPrimaryKey(pool);
									}
								}
							}
						}

					} else {
						if (alMCountI >= auntMCount) {
							apiResult.setCode("3");
							apiResult.setMessage("接单失败接单性别不能为男");
						} else {
							doAcceptOrderByMultiAunt(order, userid, userType);
							orderPoolMapper.deleteAuntOrCompanyData(order.getOrderid(), userid, userType);
							List<OrderPool> poolList = orderPoolMapper.selectListByOrderUser(order.getOrderid(), 1);
							if (poolList != null && poolList.size() > 0) {
								for (OrderPool pool : poolList) {
									int mcount = pool.getAunt_m_count();
									mcount--;
									if (mcount < 0) {
										mcount = 0;
									}
									pool.setAunt_m_count(mcount);
									orderPoolMapper.updateByPrimaryKey(pool);
								}
							}

						}
					}

				}
			} else {
				// 公司接单
				int auntMCount = order.getAuntMCount(); // 需要男性数量
				int auntWCount = order.getAuntWCount(); // 需要女性数量
				int totalNeedCount = auntMCount + auntWCount;
				// int alAcceptCount = 0;

				Long alMCount = orderAuntMapper.selectAuntMCount(order.getOrderid());
				Long alWCount = orderAuntMapper.selectAuntWCount(order.getOrderid());
				long alMCountL = 0;
				long alWCountL = 0;
				if (alMCount == null) {

				} else {
					alMCountL = alMCount;
				}
				if (alWCount == null) {

				} else {
					alWCountL = alWCount;
				}
				int alMCountI = (int) alMCountL;
				int alWCountI = (int) alWCountL;

				List<OrderAunt> auntList = orderAuntMapper.selectSingleAuntAcceptByUserOrderId(order.getOrderid());
				if (auntList != null && auntList.size() > 0) {
					for (OrderAunt orderAunt : auntList) {
						Aunt aunt = auntMapper.selectByPrimaryKey(orderAunt.getAuntid());
						if (aunt != null) {
							if (!CommonUtils.isEmptyString(aunt.getSex())) {
								if (aunt.getSex().equals("男")) {
									alMCountI++;
								} else {
									alWCountI++;
								}
							} else {
								alMCountI++;
							}
						}
					}
				}

				if (alMCountI + aunt_m_count == auntMCount && alWCountI + aunt_w_count == auntWCount) {
					order.setState((short) 2);
					order.setAcceptTime(new Date());
					orderMapper.updateByPrimaryKey(order);
					doAcceptOrderByMultiCompany(order, userid, userType, aunt_m_count, aunt_w_count);
					List<OrderAunt> alOrderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
					if (alOrderAuntList != null && alOrderAuntList.size() > 0) {
						for (OrderAunt orderAunt : alOrderAuntList) {
							orderAunt.setState((short) 2);
							orderAunt.setAcceptTime(new Date());
							orderAuntMapper.updateByPrimaryKey(orderAunt);
						}
					}
					orderPoolMapper.deleteByUserOrderId(order.getOrderid());
				} else {

					if (alMCountI + aunt_m_count > auntMCount) {
						apiResult.setCode("5");
						apiResult.setMessage("提供男性数量超过上限");
					} else if (alWCountI + aunt_w_count > auntWCount) {
						apiResult.setCode("5");
						apiResult.setMessage("提供女性数量超过上限");
					} else {

						doAcceptOrderByMultiCompany(order, userid, userType, aunt_m_count, aunt_w_count);
						orderPoolMapper.deleteAuntOrCompanyData(order.getOrderid(), userid, userType);
						List<OrderPool> poolList = orderPoolMapper.selectListByOrderUser(order.getOrderid(), 1);
						if (poolList != null && poolList.size() > 0) {
							for (OrderPool pool : poolList) {
								int mcount = pool.getAunt_m_count();
								mcount = mcount - aunt_m_count;
								if (mcount < 0) {
									mcount = 0;
								}
								int wcount = pool.getAunt_w_count();
								wcount = wcount - aunt_w_count;
								if (wcount < 0) {
									wcount = 0;
								}
								pool.setAunt_m_count(mcount);
								pool.setAunt_w_count(wcount);
								orderPoolMapper.updateByPrimaryKey(pool);
							}
						}

					}

				}

			}

		}

		return apiResult;
	}

	/*
	 * private void doAcceptOrderBySingleAunt(Order order, Integer userid,
	 * Integer userType){
	 * orderPoolMapper.deleteByUserOrderId(order.getOrderid()); // 更新订单状态
	 * order.setState((short) 2); order.setAcceptTime(new Date());
	 * orderMapper.updateByPrimaryKey(order); final int order_id =
	 * order.getOrderid();
	 *//**
		 * 发送接单消息给用户
		 *//*
		 * // 生成阿姨端订单 OrderAunt orderAunt = new OrderAunt();
		 * orderAunt.setAcceptTime(new Date());
		 * orderAunt.setAcceptType(order.getAcceptType());
		 * orderAunt.setAddressdetail(order.getAddressdetail());
		 * orderAunt.setAddressname(order.getAddressname());
		 * orderAunt.setAddtime(new Date()); if (userType == 0) {
		 * orderAunt.setAuntid(userid); } else { orderAunt.setAuntid(0); }
		 * orderAunt.setBook(order.getBook());
		 * orderAunt.setCategoryid(order.getCategoryid());
		 * orderAunt.setCommentState(order.getCommentState()); if (userType ==
		 * 0) { orderAunt.setCompanyid(0); } else {
		 * orderAunt.setCompanyid(userid); }
		 * orderAunt.setComplaintState(order.getComplaintState());
		 * orderAunt.setCouponid(order.getCouponid());
		 * orderAunt.setCouponPirce(order.getCouponPirce());
		 * orderAunt.setDayTime(order.getDayTime());
		 * orderAunt.setDepositPrice(order.getDepositPrice());
		 * orderAunt.setExpectedPrice(order.getExpectedPrice());
		 * orderAunt.setExpectTime(order.getExpectTime());
		 * orderAunt.setFoodselect(order.getFoodselect());
		 * orderAunt.setLastPrice(order.getLastPrice());
		 * orderAunt.setLatitude(order.getLatitude());
		 * orderAunt.setLongitude(order.getLongitude());
		 * orderAunt.setNeedTools(order.getNeedTools());
		 * 
		 * Long count = orderAuntMapper.selectOrderNumCount(); String ordernum =
		 * createOrderNum(4, count); orderAunt.setOrdernum(ordernum);
		 * 
		 * orderAunt.setOrderSource(order.getOrderSource());
		 * orderAunt.setOrderType(order.getOrderType());
		 * orderAunt.setOtherPirce(order.getOtherPirce());
		 * orderAunt.setPayTime2(order.getPayTime2());
		 * orderAunt.setPhone(order.getPhone());
		 * orderAunt.setPicIds(order.getPicIds());
		 * orderAunt.setPosterType(order.getPosterType());
		 * orderAunt.setRatio(order.getRatio());
		 * orderAunt.setRatioMoney(order.getRatioMoney());
		 * orderAunt.setReasonMark(order.getReasonMark());
		 * orderAunt.setRelationOrderid(order.getRelationOrderid());
		 * orderAunt.setRname(order.getRname());
		 * orderAunt.setSex(order.getSex()); orderAunt.setState((short) 2);
		 * orderAunt.setThingCount(order.getThingCount());
		 * orderAunt.setTipPrice(order.getTipPrice());
		 * orderAunt.setUserid(order.getUserid());
		 * orderAunt.setUserOrderid(order.getOrderid());
		 * orderAunt.setServerTime(order.getServerTime()); }
		 */

	// @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	private OrderAunt doAcceptOrderByMultiAunt(Order order, Integer userid, Integer userType) {
		// 生成阿姨端订单
		OrderAunt orderAunt = new OrderAunt();
		orderAunt.setAcceptTime(new Date());
		orderAunt.setAcceptType(order.getAcceptType());
		orderAunt.setAddressdetail(order.getAddressdetail());
		orderAunt.setAddressname(order.getAddressname());
		orderAunt.setAddtime(new Date());
		if (userType == 0) {
			orderAunt.setAuntid(userid);
		} else {
			orderAunt.setAuntid(0);
		}
		orderAunt.setBook(order.getBook());
		orderAunt.setCategoryid(order.getCategoryid());
		orderAunt.setCommentState(order.getCommentState());
		if (userType == 0) {
			orderAunt.setCompanyid(0);
		} else {
			orderAunt.setCompanyid(userid);
		}
		orderAunt.setComplaintState(order.getComplaintState());
		orderAunt.setCouponid(order.getCouponid());
		orderAunt.setCouponPirce(order.getCouponPirce());
		orderAunt.setDayTime(order.getDayTime());
		orderAunt.setDepositPrice(order.getDepositPrice());
		orderAunt.setExpectedPrice(order.getExpectedPrice());
		orderAunt.setExpectTime(order.getExpectTime());
		orderAunt.setFoodselect(order.getFoodselect());
		orderAunt.setLastPrice(order.getLastPrice());
		orderAunt.setLatitude(order.getLatitude());
		orderAunt.setLongitude(order.getLongitude());
		orderAunt.setNeedTools(order.getNeedTools());

		Long count = orderAuntMapper.selectOrderNumCount();
		String ordernum = createOrderNum(4, count);
		orderAunt.setOrdernum(ordernum);

		orderAunt.setOrderSource(order.getOrderSource());
		orderAunt.setOrderType(order.getOrderType());
		orderAunt.setOtherPirce(order.getOtherPirce());
		orderAunt.setPayTime2(order.getPayTime2());
		orderAunt.setPhone(order.getPhone());
		orderAunt.setPicIds(order.getPicIds());
		orderAunt.setPosterType(order.getPosterType());
		orderAunt.setRatio(order.getRatio());
		orderAunt.setRatioMoney(order.getRatioMoney());
		orderAunt.setReasonMark(order.getReasonMark());
		orderAunt.setBook(order.getBook());
		orderAunt.setRelationOrderid(order.getRelationOrderid());
		orderAunt.setRname(order.getRname());
		orderAunt.setSex(order.getSex());
		orderAunt.setState((short) 16);
		orderAunt.setThingCount(order.getThingCount());
		orderAunt.setTipPrice(order.getTipPrice());
		orderAunt.setUserid(order.getUserid());
		orderAunt.setUserOrderid(order.getOrderid());
		orderAunt.setServerTime(order.getServerTime());
		orderAunt.setFoodselect(order.getFoodselect());
		setRatioMoney(orderAunt);
		Integer server_type = 1;
		if (userType == 0) {
			server_type = 1;
		} else if (userType == 1) {
			server_type = 2;
		}
		orderAuntMapper.insert(orderAunt);
		return orderAunt;
	}

	// @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	private void doAcceptOrderByMultiCompany(Order order, Integer userid, Integer userType, int auntMCount,
			int auntWCount) {
		// 生成阿姨端订单
		OrderAunt orderAunt = new OrderAunt();
		orderAunt.setAcceptTime(new Date());
		orderAunt.setAcceptType(order.getAcceptType());
		orderAunt.setAddressdetail(order.getAddressdetail());
		orderAunt.setAddressname(order.getAddressname());
		orderAunt.setAddtime(new Date());
		if (userType == 0) {
			orderAunt.setAuntid(userid);
		} else {
			orderAunt.setAuntid(0);
		}
		orderAunt.setBook(order.getBook());
		orderAunt.setCategoryid(order.getCategoryid());
		orderAunt.setCommentState(order.getCommentState());
		if (userType == 0) {
			orderAunt.setCompanyid(0);
		} else {
			orderAunt.setCompanyid(userid);
		}
		orderAunt.setComplaintState(order.getComplaintState());
		orderAunt.setCouponid(order.getCouponid());
		orderAunt.setCouponPirce(order.getCouponPirce());
		orderAunt.setDayTime(order.getDayTime());
		orderAunt.setDepositPrice(order.getDepositPrice());
		orderAunt.setExpectedPrice(order.getExpectedPrice());
		orderAunt.setExpectTime(order.getExpectTime());
		orderAunt.setFoodselect(order.getFoodselect());
		orderAunt.setLastPrice(order.getLastPrice());
		orderAunt.setLatitude(order.getLatitude());
		orderAunt.setLongitude(order.getLongitude());
		orderAunt.setNeedTools(order.getNeedTools());

		Long count = orderAuntMapper.selectOrderNumCount();
		String ordernum = createOrderNum(4, count);
		orderAunt.setOrdernum(ordernum);

		orderAunt.setOrderSource(order.getOrderSource());
		orderAunt.setOrderType(order.getOrderType());
		orderAunt.setOtherPirce(order.getOtherPirce());
		orderAunt.setPayTime2(order.getPayTime2());
		orderAunt.setPhone(order.getPhone());
		orderAunt.setPicIds(order.getPicIds());
		orderAunt.setPosterType(order.getPosterType());
		orderAunt.setRatio(order.getRatio());
		orderAunt.setRatioMoney(order.getRatioMoney());
		orderAunt.setReasonMark(order.getReasonMark());
		orderAunt.setBook(order.getBook());
		orderAunt.setRelationOrderid(order.getRelationOrderid());
		orderAunt.setRname(order.getRname());
		orderAunt.setSex(order.getSex());
		orderAunt.setState((short) 16);
		orderAunt.setThingCount(order.getThingCount());
		orderAunt.setTipPrice(order.getTipPrice());
		orderAunt.setUserid(order.getUserid());
		orderAunt.setUserOrderid(order.getOrderid());
		orderAunt.setServerTime(order.getServerTime());
		orderAunt.setAunt_m_count(auntMCount);
		orderAunt.setAunt_w_count(auntWCount);
		orderAunt.setFoodselect(order.getFoodselect());
		setRatioMoney(orderAunt);
		Integer server_type = 1;
		if (userType == 0) {
			server_type = 1;
		} else if (userType == 1) {
			server_type = 2;
		}
		orderAuntMapper.insert(orderAunt);

	}

	// @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	private void doRefuseOrderByMultiAunt(Order order, Integer userid, Integer userType, String content,
			String reason) {
		OrderAunt orderAunt = new OrderAunt();
		orderAunt.setAcceptTime(new Date());
		orderAunt.setAcceptType(order.getAcceptType());
		orderAunt.setAddressdetail(order.getAddressdetail());
		orderAunt.setAddressname(order.getAddressname());
		orderAunt.setAddtime(new Date());
		if (userType == 0) {
			orderAunt.setAuntid(userid);
		} else {
			orderAunt.setAuntid(0);
		}
		orderAunt.setBook(order.getBook());
		orderAunt.setCategoryid(order.getCategoryid());
		orderAunt.setCommentState(order.getCommentState());
		if (userType == 0) {
			orderAunt.setCompanyid(0);
		} else {
			orderAunt.setCompanyid(userid);
		}
		orderAunt.setComplaintState(order.getComplaintState());
		orderAunt.setCouponid(order.getCouponid());
		orderAunt.setCouponPirce(order.getCouponPirce());
		orderAunt.setDayTime(order.getDayTime());
		orderAunt.setDepositPrice(order.getDepositPrice());
		orderAunt.setExpectedPrice(order.getExpectedPrice());
		orderAunt.setExpectTime(order.getExpectTime());
		orderAunt.setFoodselect(order.getFoodselect());
		orderAunt.setLastPrice(order.getLastPrice());
		orderAunt.setLatitude(order.getLatitude());
		orderAunt.setLongitude(order.getLongitude());
		orderAunt.setNeedTools(order.getNeedTools());

		Long count = orderAuntMapper.selectOrderNumCount();
		String ordernum = createOrderNum(4, count);
		orderAunt.setOrdernum(ordernum);

		orderAunt.setOrderSource(order.getOrderSource());
		orderAunt.setOrderType(order.getOrderType());
		orderAunt.setOtherPirce(order.getOtherPirce());
		orderAunt.setPayTime2(order.getPayTime2());
		orderAunt.setPhone(order.getPhone());
		orderAunt.setPicIds(order.getPicIds());
		orderAunt.setPosterType(order.getPosterType());
		orderAunt.setRatio(order.getRatio());
		orderAunt.setRatioMoney(order.getRatioMoney());
		orderAunt.setReasonMark(order.getReasonMark());
		orderAunt.setBook(order.getBook());
		orderAunt.setRelationOrderid(order.getRelationOrderid());
		orderAunt.setRname(order.getRname());
		orderAunt.setSex(order.getSex());
		orderAunt.setState((short) 8);
		orderAunt.setThingCount(order.getThingCount());
		orderAunt.setTipPrice(order.getTipPrice());
		orderAunt.setUserid(order.getUserid());
		orderAunt.setUserOrderid(order.getOrderid());
		orderAunt.setServerTime(order.getServerTime());
		orderAunt.setFoodselect(order.getFoodselect());
		orderAuntMapper.insert(orderAunt);
		OrderComplaint orderComplaint = new OrderComplaint();
		orderComplaint.setAddtime(new Date());
		orderComplaint.setContent(content);
		orderComplaint.setDataType((short) 1);
		orderComplaint.setOrderid(order.getOrderid());
		orderComplaint.setTitle(reason);
		orderComplaint.setUserid(userid);
		int user_type = userType;
		orderComplaint.setUserType((short) user_type);
		setRatioMoney(orderAunt);
		orderComplaintMapper.insert(orderComplaint);
	}

	private void setRatioMoney(OrderAunt orderAunt) {
		Order order = orderMapper.selectByPrimaryKey(orderAunt.getUserOrderid());
		if (orderAunt.getAuntid() == 0 && orderAunt.getCompanyid() > 0) {
			Ratio ration = ratioMapper.selectByCity(order.getCity(), order.getCategoryid(), 1);
			if (ration != null) {
				orderAunt.setRatio(ration.getRatio());
			} else {
				orderAunt.setRatio(new Float("" + 0));
			}

		} else if (orderAunt.getCompanyid() == 0 && orderAunt.getAuntid() > 0) {
			Ratio ration = ratioMapper.selectByCity(order.getCity(), order.getCategoryid(), 2);
			if (ration != null) {
				orderAunt.setRatio(ration.getRatio());
			} else {
				orderAunt.setRatio(new Float("" + 0));
			}
		}
	}

}
