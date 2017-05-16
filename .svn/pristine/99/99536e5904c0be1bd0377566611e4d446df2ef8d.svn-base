package com.uncleserver.service.api.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uncleserver.common.CommonUtils;
import com.uncleserver.common.Constant;
import com.uncleserver.common.alipay.AlipayConfig;
import com.uncleserver.common.alipay.AlipayRefundTrade;
import com.uncleserver.common.alipay.util.OrderInfoUtil2_0;
import com.uncleserver.common.push.IOSPushUtils;
import com.uncleserver.common.push.IosPushDataBase;
import com.uncleserver.common.push.IosPushDateModelBase;
import com.uncleserver.common.push.JPushUtils;
import com.uncleserver.common.wxpay.WxRefund;
import com.uncleserver.dao.RedPacketRecordMapper;
import com.uncleserver.model.Aunt;
import com.uncleserver.model.AuntExtra;
import com.uncleserver.model.AuntMessageDetail;
import com.uncleserver.model.AuntPush;
import com.uncleserver.model.AuntSkill;
import com.uncleserver.model.BalanceRecord;
import com.uncleserver.model.Category;
import com.uncleserver.model.CategoryCity;
import com.uncleserver.model.CategorySecond;
import com.uncleserver.model.CategoryThird;
import com.uncleserver.model.Company;
import com.uncleserver.model.CompanyExtra;
import com.uncleserver.model.CompanyRange;
import com.uncleserver.model.CompanyWithBLOBs;
import com.uncleserver.model.ConfigRedPacket;
import com.uncleserver.model.Coupon;
import com.uncleserver.model.CouponUser;
import com.uncleserver.model.MessageDetail;
import com.uncleserver.model.Order;
import com.uncleserver.model.OrderAunt;
import com.uncleserver.model.OrderAuntTemp;
import com.uncleserver.model.OrderComment;
import com.uncleserver.model.OrderComplaint;
import com.uncleserver.model.OrderPay;
import com.uncleserver.model.OrderPool;
import com.uncleserver.model.OrderRefund;
import com.uncleserver.model.OrderTarget;
import com.uncleserver.model.OrderTargetTemp;
import com.uncleserver.model.OrderTemp;
import com.uncleserver.model.OrderThird;
import com.uncleserver.model.OrderThirdTemp;
import com.uncleserver.model.PickAddress;
import com.uncleserver.model.RedPacketRecord;
import com.uncleserver.model.SerPrice;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.model.UserPush;
import com.uncleserver.modelVo.ApiResult;
import com.uncleserver.modelVo.AuntList;
import com.uncleserver.modelVo.AuntOrderValue;
import com.uncleserver.modelVo.AuntValue;
import com.uncleserver.modelVo.CanUseCouponValue;
import com.uncleserver.modelVo.CategoryFirstValue;
import com.uncleserver.modelVo.CategorySecondValue;
import com.uncleserver.modelVo.CategoryThirdValue;
import com.uncleserver.modelVo.CompanyValue;
import com.uncleserver.modelVo.FirstList;
import com.uncleserver.modelVo.MaintenanceFee;
import com.uncleserver.modelVo.OrderInfo;
import com.uncleserver.modelVo.OrderInfoValue;
import com.uncleserver.modelVo.Orderlist;
import com.uncleserver.modelVo.PayInfo;
import com.uncleserver.modelVo.PayValue;
import com.uncleserver.modelVo.PushData;
import com.uncleserver.modelVo.SecondList;
import com.uncleserver.modelVo.ServerList;
import com.uncleserver.modelVo.TargetValue;
import com.uncleserver.service.api.OrderService;

@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(8);

	private ScheduledExecutorService scheduledOrderPayService = Executors.newScheduledThreadPool(10);

	@Override
	public ApiResult getServerCategory() {

		ApiResult result = new ApiResult();
		List<Category> categoryList = categoryMapper.selectAllCategory();
		List<CategoryFirstValue> categoryFirst = new ArrayList<CategoryFirstValue>();
		if (null != categoryList && categoryList.size() > 0) {
			for (Category category : categoryList) {
				CategoryFirstValue firstValue = new CategoryFirstValue();
				firstValue.setName(category.getName());
				List<CategorySecond> categorySeconds = categorySecondMapper.selectByFid(category.getDataid());
				List<CategorySecondValue> secondsValues = new ArrayList<>();
				if (categorySeconds != null && categorySeconds.size() > 0) {
					for (CategorySecond categorySecond : categorySeconds) {
						CategorySecondValue secondValue = new CategorySecondValue();
						secondValue.setId(categorySecond.getDataid());
						secondValue.setName(categorySecond.getName());
						secondValue.setNeedpic(categorySecond.getNeedpic());
						if (categorySecond.getIconid() != null && categorySecond.getIconid() > 0) {
							String picUrl = getFilePathById(categorySecond.getIconid());
							secondValue.setPicurl(picUrl);
						}
						secondValue.setProfile(categorySecond.getProfile());
						// SerPrice price =
						// serPriceMapper.selectByCategoryId(categorySecond.getDataid());
						// if (null != price) {
						// secondValue.setDeposit_price(
						// price.getDepositPrice().setScale(2,
						// BigDecimal.ROUND_UP).floatValue());
						// }

						secondsValues.add(secondValue);
					}
					firstValue.setSecondList(secondsValues);
				}
				categoryFirst.add(firstValue);
			}

		}
		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("firstList", categoryFirst);
		result.setResult(map);
		return result;
	}
	
	
	
	

	@Override
	public ApiResult getServerCategory(String city) {
		ApiResult result = new ApiResult();
		List<Category> categoryList = categoryMapper.selectAllCategory();
		List<CategoryCity> categoryCityList = categoryCityMapper.selectByCity(city);
		List<CategoryFirstValue> categoryFirst = new ArrayList<CategoryFirstValue>();
		if (null != categoryList && categoryList.size() > 0) {
			for (Category category : categoryList) {
				CategoryFirstValue firstValue = new CategoryFirstValue();
				firstValue.setName(category.getName());
				List<CategorySecond> categorySeconds = categorySecondMapper.selectByFid(category.getDataid());
				List<CategorySecondValue> secondsValues = new ArrayList<>();
				if (categorySeconds != null && categorySeconds.size() > 0) {
					for (CategorySecond categorySecond : categorySeconds) {
						if(!isInCategoryCity(categorySecond,categoryCityList)){
							continue;
						}
						CategorySecondValue secondValue = new CategorySecondValue();
						secondValue.setId(categorySecond.getDataid());
						secondValue.setName(categorySecond.getName());
						secondValue.setNeedpic(categorySecond.getNeedpic());
						if (categorySecond.getIconid() != null && categorySecond.getIconid() > 0) {
							String picUrl = getFilePathById(categorySecond.getIconid());
							secondValue.setPicurl(picUrl);
						}
						secondValue.setProfile(categorySecond.getProfile());
						secondsValues.add(secondValue);
					}
					firstValue.setSecondList(secondsValues);
				}
				categoryFirst.add(firstValue);
			}

		}
		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("firstList", categoryFirst);
		result.setResult(map);
		return result;
	}





	private boolean isInCategoryCity(CategorySecond categorySecond, List<CategoryCity> categoryCityList) {
		boolean result = false;
		for(CategoryCity categoryCity:categoryCityList){
			if(categoryCity.getCategoryid() == categorySecond.getDataid()){
				result = true;
				break;
			}
		}
		return result;
	}





	@Override
	public ApiResult canBookListMap(String longitude, String latitude, String serverid, String thirdids) {
		ApiResult result = new ApiResult();
		List<AuntValue> auntValues = new ArrayList<>();
		List<CompanyValue> companyValues = new ArrayList<>();
		// double dis = 20 * 1.0;
		List<CompanyValue> tempCompanuyValues = getCanBookCompanyList(longitude, latitude, serverid, thirdids, 1); // 需要判断连接
		if (tempCompanuyValues != null && tempCompanuyValues.size() > 0) {
			companyValues.addAll(tempCompanuyValues);
		}
		List<AuntValue> tempAuntValues = getCanBookAuntList(longitude, latitude, serverid, thirdids, 1); // 需要判断连接
		if (tempAuntValues != null && tempAuntValues.size() > 0) {
			auntValues.addAll(tempAuntValues);
		}
		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("auntList", auntValues);
		map.put("companyList", companyValues);
		result.setResult(map);
		return result;
	}

	public List<AuntValue> getCanBookAuntList(String longitude, String latitude, String serverid, String thirdids,
			int type) {
		List<AuntValue> auntValues = new ArrayList<>();
		double dis = 20 * 1.0;
		List<AuntExtra> auntExtraList = new ArrayList<>();
		if (type == 0) { // 0 不判断连接
			auntExtraList = auntExtramapper.selectByDistanceWithoutLine(Double.parseDouble(longitude),
					Double.parseDouble(latitude), dis);
		} else { // 1 判断连接
			auntExtraList = auntExtramapper.selectByDistance(Double.parseDouble(longitude),
					Double.parseDouble(latitude), dis);
		}

		if (auntExtraList != null && auntExtraList.size() > 0) {
			for (AuntExtra extra : auntExtraList) {
				Aunt aunt = auntMapper.selectByPrimaryKey(extra.getAuntid());
				if (aunt != null && aunt.getCompanyid() == 0) {

				} else {
					continue;
				}
				if (aunt.getState() != 1) {
					continue;
				}
				List<AuntSkill> rangeList = auntSkillMapper.selectByAuntId(extra.getAuntid(),
						Integer.parseInt(serverid));
				String thirds = "";
				int count = 0;
				if (null != rangeList && rangeList.size() > 0) {
					AuntValue auntValue = new AuntValue();
					auntValue.setAuntid(extra.getAuntid());
					if (aunt.getAvatar() != null && aunt.getAvatar() > 0) {
						auntValue.setAvatarurl(getFilePathById(aunt.getAvatar()));
					} else {
						auntValue.setAvatarurl(aunt.getThirdAvatar());
					}
					auntValue.setLongitude("" + extra.getLongitude());
					auntValue.setLatitude("" + extra.getLatitude());
					for (AuntSkill skill : rangeList) {
						if (CommonUtils.isEmptyString(thirdids)) {
							if (count == 0) {
								thirds += skill.getThirdid();
							} else {
								thirds += "," + skill.getThirdid();
							}
							count++;
						} else {
							if (count == 0) {
								if (thirdids.contains("" + skill.getThirdid())) {
									thirds += skill.getThirdid();
									count++;
								} else {

								}
							} else {
								if (thirdids.contains("" + skill.getThirdid())) {
									thirds += "," + skill.getThirdid();
									count++;
								} else {

								}
							}
						}
					}
					auntValue.setThirdids(thirds);
					auntValues.add(auntValue);
				}

			}
		}
		return auntValues;
	}

	public List<AuntValue> getCanBookAuntList(String longitude, String latitude, String serverid, String thirdids) {
		List<AuntValue> auntValues = new ArrayList<>();
		double dis = 20 * 1.0;
		List<AuntExtra> auntExtraList = new ArrayList<>();
		auntExtraList = auntExtramapper.selectByDistanceWithoutLineAndState(Double.parseDouble(longitude),
				Double.parseDouble(latitude), dis);
		if (auntExtraList != null && auntExtraList.size() > 0) {
			for (AuntExtra extra : auntExtraList) {
				Aunt aunt = auntMapper.selectByPrimaryKey(extra.getAuntid());
				if (aunt != null && aunt.getCompanyid() == 0) {

				} else {
					continue;
				}
				if (aunt.getState() != 1) {
					continue;
				}
				List<AuntSkill> rangeList = auntSkillMapper.selectByAuntId(extra.getAuntid(),
						Integer.parseInt(serverid));
				String thirds = "";
				int count = 0;
				if (null != rangeList && rangeList.size() > 0) {
					AuntValue auntValue = new AuntValue();
					auntValue.setAuntid(extra.getAuntid());
					if (aunt.getAvatar() != null && aunt.getAvatar() > 0) {
						auntValue.setAvatarurl(getFilePathById(aunt.getAvatar()));
					} else {
						auntValue.setAvatarurl(aunt.getThirdAvatar());
					}
					auntValue.setLongitude("" + extra.getLongitude());
					auntValue.setLatitude("" + extra.getLatitude());
					for (AuntSkill skill : rangeList) {
						if (CommonUtils.isEmptyString(thirdids)) {
							if (count == 0) {
								thirds += skill.getThirdid();
							} else {
								thirds += "," + skill.getThirdid();
							}
							count++;
						} else {
							if (count == 0) {
								if (thirdids.contains("" + skill.getThirdid())) {
									thirds += skill.getThirdid();
									count++;
								} else {

								}
							} else {
								if (thirdids.contains("" + skill.getThirdid())) {
									thirds += "," + skill.getThirdid();
									count++;
								} else {

								}
							}
						}
					}
					auntValue.setThirdids(thirds);
					auntValues.add(auntValue);
				}

			}
		}
		return auntValues;
	}

	public List<CompanyValue> getCanBookCompanyList(String longitude, String latitude, String serverid,
			String thirdids) {
		List<CompanyValue> companyValues = new ArrayList<>();
		double dis = 20 * 1.0;
		List<CompanyWithBLOBs> companyList = companyMapper.selectByDistance(Double.parseDouble(longitude),
				Double.parseDouble(latitude), dis);
		if (null != companyList && companyList.size() > 0) {
			for (Company company : companyList) {
				if (company.getStateDel() != 1) {
					continue;
				}
				// System
				CompanyExtra companyExtra = companyExtraMapper.selectByCompanyId(company.getCompanyid());
				if (companyExtra != null && companyExtra.getState() == 0) {

				} else {
					// continue;
				}
				List<CompanyRange> rangeList = companyRangeMapper.selectByCompanyid(company.getCompanyid(),
						Integer.parseInt(serverid));
				String thirds = "";
				int count = 0;
				if (null != rangeList && rangeList.size() > 0) {
					CompanyValue companyValue = new CompanyValue();
					companyValue.setCompanyid(company.getCompanyid());
					companyValue.setLongitude("" + company.getLongitude());
					companyValue.setLatitude("" + company.getLatitude());
					if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
						companyValue.setLogourl(getFilePathById(company.getLogoPicid()));
					}
					for (CompanyRange range : rangeList) {
						if (CommonUtils.isEmptyString(thirdids)) {
							if (count == 0) {
								thirds += range.getThirdid();
							} else {
								thirds += "," + range.getThirdid();
							}
							count++;
						} else {
							if (count == 0) {
								if (thirdids.contains("" + range.getThirdid())) {
									thirds += range.getThirdid();
									count++;
								} else {

								}
							} else {
								if (thirdids.contains("" + range.getThirdid())) {
									thirds += "," + range.getThirdid();
									count++;
								} else {

								}
							}
						}
					}
					companyValue.setThirdids(thirds);
					companyValues.add(companyValue);
				}

			}
		}
		return companyValues;
	}

	public List<CompanyValue> getCanBookCompanyList(String longitude, String latitude, String serverid, String thirdids,
			int type) {
		List<CompanyValue> companyValues = new ArrayList<>();
		double dis = 20 * 1.0;
		List<CompanyWithBLOBs> companyList = companyMapper.selectByDistance(Double.parseDouble(longitude),
				Double.parseDouble(latitude), dis);
		if (null != companyList && companyList.size() > 0) {
			for (Company company : companyList) {
				if (company.getStateDel() != 1) {
					continue;
				}
				// System
				CompanyExtra companyExtra = companyExtraMapper.selectByCompanyId(company.getCompanyid());
				if (companyExtra != null && companyExtra.getState() == 0) {

				} else {
					continue;
				}
				if (type == 0) {

				} else {
					long nowTime = new Date().getTime();
					if (companyExtra.getHeartbeatTime() != null) {
						long heatTime = companyExtra.getHeartbeatTime().getTime();
						if (Math.abs(nowTime - heatTime) > Constant.DISCONNECT_TIME) {
							continue;
						}
					}
				}

				List<CompanyRange> rangeList = companyRangeMapper.selectByCompanyid(company.getCompanyid(),
						Integer.parseInt(serverid));
				String thirds = "";
				int count = 0;
				if (null != rangeList && rangeList.size() > 0) {
					CompanyValue companyValue = new CompanyValue();
					companyValue.setCompanyid(company.getCompanyid());
					companyValue.setLongitude("" + company.getLongitude());
					companyValue.setLatitude("" + company.getLatitude());
					if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
						companyValue.setLogourl(getFilePathById(company.getLogoPicid()));
					}
					for (CompanyRange range : rangeList) {
						if (CommonUtils.isEmptyString(thirdids)) {
							if (count == 0) {
								thirds += range.getThirdid();
							} else {
								thirds += "," + range.getThirdid();
							}
							count++;
						} else {
							if (count == 0) {
								if (thirdids.contains("" + range.getThirdid())) {
									thirds += range.getThirdid();
									count++;
								} else {

								}
							} else {
								if (thirdids.contains("" + range.getThirdid())) {
									thirds += "," + range.getThirdid();
									count++;
								} else {

								}
							}
						}
					}
					companyValue.setThirdids(thirds);
					companyValues.add(companyValue);
				}

			}
		}
		return companyValues;
	}

	@Override
	public ApiResult canBookAuntList(String longitude, String latitude, String serverid, String thirdids) {
		ApiResult result = new ApiResult();
		List<AuntValue> auntValues = new ArrayList<>();
		double dis = 10 * 1.0;
		List<AuntExtra> auntExtraList = auntExtramapper.selectByDistance(Double.parseDouble(longitude),
				Double.parseDouble(latitude), dis);
		if (auntExtraList != null && auntExtraList.size() > 0) {
			for (AuntExtra extra : auntExtraList) {
				Aunt aunt = auntMapper.selectByPrimaryKey(extra.getAuntid());
				if (aunt != null && aunt.getCompanyid() == 0) {

				} else {
					continue;
				}
				if (aunt.getState() != 1) {
					continue;
				}
				List<AuntSkill> rangeList = auntSkillMapper.selectByAuntId(extra.getAuntid(),
						Integer.parseInt(serverid));
				String thirds = "";
				int count = 0;
				if (null != rangeList && rangeList.size() > 0) {
					AuntValue auntValue = new AuntValue();
					auntValue.setAuntid(extra.getAuntid());
					if (aunt.getAvatar() != null && aunt.getAvatar() > 0) {
						auntValue.setAvatarurl(getFilePathById(aunt.getAvatar()));
					} else {
						auntValue.setAvatarurl(aunt.getThirdAvatar());
					}
					auntValue.setLongitude("" + extra.getLongitude());
					auntValue.setLatitude("" + extra.getLatitude());
					auntValue.setName(aunt.getRealName());
					auntValue.setOrigin_place(aunt.getOriginPlace());
					auntValue.setScore(extra.getScore());
					auntValue.setAge("" + CommonUtils.getAge(aunt.getBirthday(), new Date()));
					for (AuntSkill skill : rangeList) {
						if (CommonUtils.isEmptyString(thirdids)) {
							if (count == 0) {
								thirds += skill.getThirdid();
							} else {
								thirds += "," + skill.getThirdid();
							}
							count++;
						} else {
							if (count == 0) {
								if (thirdids.contains("" + skill.getThirdid())) {
									thirds += skill.getThirdid();
									count++;
								} else {

								}
							} else {
								if (thirdids.contains("" + skill.getThirdid())) {
									thirds += "," + skill.getThirdid();
									count++;
								} else {

								}
							}
						}
					}
					auntValue.setThirdids(thirds);
					auntValues.add(auntValue);
				}

			}
		}
		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("auntList", auntValues);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult canBookCompanyList(String longitude, String latitude, String serverid, String thirdids) {
		ApiResult result = new ApiResult();
		List<CompanyValue> companyValues = new ArrayList<>();
		double dis = 20 * 1.0;
		List<CompanyWithBLOBs> companyList = companyMapper.selectByDistance(Double.parseDouble(longitude),
				Double.parseDouble(latitude), dis);
		if (null != companyList && companyList.size() > 0) {
			for (Company company : companyList) {
				if (company.getStateDel() != 1) {
					continue;
				}
				CompanyExtra companyExtra = companyExtraMapper.selectByCompanyId(company.getCompanyid());
				if (companyExtra.getState() == 0) {

				} else {
					continue;
				}
				long nowTime = new Date().getTime();
				// System.out.println("nowTime: "+nowTime);
				if (companyExtra.getHeartbeatTime() != null) {
					long heatTime = companyExtra.getHeartbeatTime().getTime();
					// System.out.println("heatTime: "+heatTime);
					long diff = nowTime - heatTime;
					// System.out.println("diff time:"+diff);
					if (Math.abs(nowTime - heatTime) > Constant.DISCONNECT_TIME) {
						continue;
					}
				}
				List<CompanyRange> rangeList = companyRangeMapper.selectByCompanyid(company.getCompanyid(),
						Integer.parseInt(serverid));
				String thirds = "";
				int count = 0;
				if (null != rangeList && rangeList.size() > 0) {
					CompanyValue companyValue = new CompanyValue();
					companyValue.setCompanyid(company.getCompanyid());
					companyValue.setLongitude("" + company.getLongitude());
					companyValue.setLatitude("" + company.getLatitude());
					if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
						companyValue.setLogourl(getFilePathById(company.getLogoPicid()));
					}
					companyValue.setName(company.getName());
					companyValue.setProfile(company.getProfile());
					for (CompanyRange range : rangeList) {
						if (CommonUtils.isEmptyString(thirdids)) {
							if (count == 0) {
								thirds += range.getThirdid();
							} else {
								thirds += "," + range.getThirdid();
							}
							count++;
						} else {
							if (count == 0) {
								if (thirdids.contains("" + range.getThirdid())) {
									thirds += range.getThirdid();
									count++;
								} else {

								}
							} else {
								if (thirdids.contains("" + range.getThirdid())) {
									thirds += "," + range.getThirdid();
									count++;
								} else {

								}
							}
						}
					}
					companyValue.setThirdids(thirds);
					companyValues.add(companyValue);
				}
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("companyList", companyValues);
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult booking(User user, String serverid, String addressid, String server_time, String book_type,
			String app_type, String picids, String book, String aunt_type, String auntids, String expect_time,
			String day_time, String thing_count, String third_json, String need_tools, String reason_mark,
			String order_target_json, String m_count, String w_count, String tip_price, String order_type,
			String foodselect, String price) {

		book = CommonUtils.removeAllEmojis(book);

		ApiResult result = new ApiResult();
		OrderTemp orderTemp = new OrderTemp();
		orderTemp.setUserid(user.getUserid());
		orderTemp.setAddressid(Integer.parseInt(addressid));
		PickAddress pickAddress = pickAddressMapper.selectByPrimaryKey(Integer.parseInt(addressid));
		String city = "";
		if (null != pickAddress) {
			city = pickAddress.getArea();
		}
		orderTemp.setCategoryid(Integer.parseInt(serverid));
		orderTemp.setServerTime(CommonUtils.getDateFormat(server_time, "yyyy-MM-dd HH:mm:ss"));
		int bookType = (int) CommonUtils.parseShort(book_type, (short) 0);
		if (bookType == 1) {//1.指定  2.抢单
			orderTemp.setAcceptType((short) 0);
		} else if (bookType == 2) {
			orderTemp.setAcceptType((short) 1);
		}
		
		//如果阿姨ID为空则一定是抢单
		if(CommonUtils.isEmptyString(auntids) || auntids.split(",") == null || auntids.split(",").length == 0){
			orderTemp.setAcceptType((short) 1);
		}
		
		orderTemp.setOrderSource(CommonUtils.parseShort(app_type, (short) 0));
		orderTemp.setPicIds(picids);
		orderTemp.setPosterType((short) 0);
		orderTemp.setExpectTime(CommonUtils.parseFloat(expect_time, 0f));
		if (CommonUtils.isEmptyString(day_time)) {
			orderTemp.setDayTime(new Float(0));
		} else {
			orderTemp.setDayTime(Float.valueOf(day_time));
		}
		if (CommonUtils.isEmptyString(thing_count)) {
			orderTemp.setThingCount(0);
		} else {
			orderTemp.setThingCount(Integer.parseInt(thing_count));
		}
		orderTemp.setReasonMark(reason_mark);
		orderTemp.setFoodselect(foodselect);
		if (CommonUtils.isEmptyString(tip_price)) {
			orderTemp.setTipPrice(new BigDecimal("0.00"));
		} else {
			orderTemp.setTipPrice(new BigDecimal(tip_price));
		}
		orderTemp.setOrderType(CommonUtils.parseShort(order_type, (short) 0));

		if (CommonUtils.isEmptyString(m_count)) {
			orderTemp.setAuntMCount(0);
		} else {
			orderTemp.setAuntMCount(Integer.parseInt(m_count));
		}
		if (CommonUtils.isEmptyString(w_count)) {
			orderTemp.setAuntWCount(0);
		} else {
			orderTemp.setAuntWCount(Integer.parseInt(w_count));
		}
		orderTemp.setNeedTools(CommonUtils.parseShort(order_type, (short) 0));
		// orderTemp.setDepositPrice(new BigDecimal("0"));
		// orderTemp.setTipPrice(new BigDecimal("0"));
		List<CategoryThirdValue> thirdValues = new ArrayList<>();
		List<TargetValue> targetValues = new ArrayList<>();
		if (CommonUtils.isEmptyString(third_json)) {

		} else {
			thirdValues = new Gson().fromJson(third_json, new TypeToken<List<CategoryThirdValue>>() {
			}.getType());
		}
		if (CommonUtils.isEmptyString(order_target_json)) {

		} else {
			targetValues = new Gson().fromJson(order_target_json, new TypeToken<List<TargetValue>>() {
			}.getType());
		}

		ApiResult result1 = setExpectedPrice(orderTemp, thirdValues, targetValues, city, price);
		if (!"1".equals(result1.getC())) {
			return result1;
		}

		if (!CommonUtils.isEmptyString(auntids)) {
			String[] ids = auntids.split(",");
			if (ids != null) {
				int count = ids.length;
				orderTemp.setAuntMCount(count);
			}
		}
		orderTemp.setBook(book);
		orderTempMapper.insert(orderTemp);
		if (thirdValues != null && thirdValues.size() > 0) {
			// 放置三级分类
			for(CategoryThirdValue thirdValue:thirdValues){
				OrderThirdTemp orderThirdTemp = new OrderThirdTemp();
				orderThirdTemp.setOrderid(orderTemp.getOrderid());
				orderThirdTemp.setCount(thirdValue.getCount());
				orderThirdTemp.setName(thirdValue.getName());
				orderThirdTempMapper.insert(orderThirdTemp);
			}
		}
		if (targetValues != null && targetValues.size() > 0) {
			// 放置陪护对象
		    for(TargetValue targetValue:targetValues){
		    	OrderTargetTemp orderTargetTemp = new OrderTargetTemp();
		    	orderTargetTemp.setOrderid(orderTemp.getOrderid());
		    	orderTargetTemp.setSex(targetValue.getSex());
		    	orderTargetTemp.setAge(targetValue.getAge());
		    	orderTargetTemp.setWeight(targetValue.getWeight());
		    	orderTargetTemp.setHeight(targetValue.getHeight());
		    	orderTargetTemp.setBehaviorType((byte)targetValue.getBehavior_type());
		    	orderTargetTemp.setIllnessType((byte)targetValue.getIllness_type());
		    	orderTargetTempMapper.insert(orderTargetTemp);
		    }
			
		}
		int count = 0;
		List<AuntOrderValue> auntOrderList = new ArrayList<>();
		if (CommonUtils.isEmptyString(aunt_type)) {

		} else {
			int aunt_typeI = Integer.parseInt(aunt_type);
			if (aunt_typeI == 1) {
				if (CommonUtils.isEmptyString(auntids)) {

				} else {
					String[] ids = auntids.split(",");

					for (int i = 0; i < ids.length; i++) {
						if (!CommonUtils.isEmptyString(ids[i])) {
							OrderAuntTemp orderAuntTemp = new OrderAuntTemp();
							orderAuntTemp.setOrderid(orderTemp.getOrderid());
							orderAuntTemp.setUserid(Integer.parseInt(ids[i]));
							orderAuntTemp.setUserType((short) 0);
							orderAuntTempMapper.insert(orderAuntTemp);
							AuntOrderValue value = new AuntOrderValue();
							value.setAuntid(Integer.parseInt(ids[i]));
							value.setType(0);
							Aunt aunt = auntMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
							if (aunt.getAuntid() != null && aunt.getAuntid() > 0) {
								value.setPicurl(getFilePathById(aunt.getAuntid()));
							} else {
								value.setPicurl(aunt.getThirdAvatar());
							}
							if (null != aunt) {
								value.setName(aunt.getRealName());
							}
							auntOrderList.add(value);
							count++;
						}
					}

				}
			} else if (aunt_typeI == 2) {
				if (CommonUtils.isEmptyString(auntids)) {

				} else {
					String[] ids = auntids.split(",");
					for (int i = 0; i < ids.length; i++) {
						if (!CommonUtils.isEmptyString(ids[i])) {
							OrderAuntTemp orderAuntTemp = new OrderAuntTemp();
							orderAuntTemp.setOrderid(orderTemp.getOrderid());
							orderAuntTemp.setUserid(Integer.parseInt(ids[i]));
							orderAuntTemp.setUserType((short) 1);
							orderAuntTempMapper.insert(orderAuntTemp);
							AuntOrderValue value = new AuntOrderValue();
							value.setAuntid(Integer.parseInt(ids[i]));
							value.setType(1);
							Company company = companyMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
							if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
								value.setPicurl(getFilePathById(company.getLogoPicid()));
							}
							if (null != company) {
								value.setName(company.getName());
							}
							auntOrderList.add(value);
							count++;
						}
					}
				}
			}
		}
		OrderInfoValue orderInfoValue = new OrderInfoValue();
		orderInfoValue.setTempid(orderTemp.getOrderid());
		PickAddress picUpAddress = pickAddressMapper.selectByPrimaryKey(Integer.parseInt(addressid));
		if (picUpAddress != null) {
			orderInfoValue.setAddress(picUpAddress.getAddressname() + picUpAddress.getAddressdetail());
		}

		CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(Integer.parseInt(serverid));
		orderInfoValue.setServername(categorySecond.getName());
		orderInfoValue.setCategoryid(categorySecond.getDataid());
		if (categorySecond.getDataid() == 5 || categorySecond.getDataid() == 6) {
			if (orderTemp.getAuntMCount() != null) {
				orderInfoValue.setM_count("" + orderTemp.getAuntMCount());
			}
			if (orderTemp.getAuntWCount() != null) {
				orderInfoValue.setW_count("" + orderTemp.getAuntWCount());
			}
		}
		/*
		 * if (Integer.parseInt(book_type) == 1) {
		 * orderInfoValue.setAuntcount("" + auntids.split(",").length); } else {
		 * orderInfoValue.setAuntcount("" + Integer.parseInt(m_count) +
		 * Integer.parseInt(w_count)); }
		 */
		orderInfoValue.setAuntcount("" + count);
		orderInfoValue.setExpect_time(orderTemp.getExpectTime().toString());
		orderInfoValue.setDay_time(orderTemp.getDayTime().toString());
		orderInfoValue.setAuntList(auntOrderList);
		orderInfoValue.setBook(book);
		orderInfoValue.setThird_json(third_json);
		orderInfoValue.setOrder_target_json(order_target_json);
		orderInfoValue.setTip_price(tip_price);
		orderInfoValue.setOrder_type(order_type);
		orderInfoValue.setFoodselect(foodselect);
		orderInfoValue.setReason_mark(reason_mark);
		orderInfoValue.setPrice(price);
		if (!CommonUtils.isEmptyString(need_tools)) {
			orderInfoValue.setNeed_tools(Integer.parseInt(need_tools));
		}

		if (orderTemp.getServerTime() != null) {
			String time1 = CommonUtils.getTimeFormat(orderTemp.getServerTime(), "MM月dd日");
			String time2 = CommonUtils.getTimeFormat(orderTemp.getServerTime(), "HH:mm");
			String week = "";
			Calendar c = Calendar.getInstance();
			c.setTime(orderTemp.getServerTime());
			switch (c.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.MONDAY:
				week = "(周一)";
				break;
			case Calendar.TUESDAY:
				week = "(周二)";
				break;
			case Calendar.WEDNESDAY:
				week = "(周三)";
				break;
			case Calendar.THURSDAY:
				week = "(周四)";
				break;
			case Calendar.FRIDAY:
				week = "(周五)";
				break;
			case Calendar.SATURDAY:
				week = "(周六)";
				break;
			case Calendar.SUNDAY:
				week = "(周日)";
				break;
			default:
				week = "(周一)";
				break;
			}

			orderInfoValue.setServer_time(time1 + week + time2);
		}
		BigDecimal money = new BigDecimal("0.00");

		if (orderTemp.getDepositPrice() != null) {
			money = money.add(orderTemp.getDepositPrice());
		}

		if (orderTemp.getTipPrice() != null) {
			money = money.add(orderTemp.getTipPrice());
		}

		orderInfoValue.setMoney(money.floatValue());

		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("orderInfo", orderInfoValue);
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult order(User user, String tempid, String pay_type) {
		ApiResult result = new ApiResult();
		OrderTemp orderTemp = orderTempMapper.selectByPrimaryKey(Integer.parseInt(tempid));
		if(orderTemp == null){
			result.setCode("2");
			result.setMessage("支付失败,订单已支付");
			return result;
		}
		
		final Order order = new Order();
		// orderMapper.insert(order);
		order.setPosterType(orderTemp.getPosterType());
		order.setUserid(orderTemp.getUserid());

		Long nowcount = orderMapper.selectOrderCount();
		String thirdNumID = createOrderNum(2, nowcount);
		order.setOrdernum(thirdNumID);
		order.setCategoryid(orderTemp.getCategoryid());
		PickAddress pickAddress = pickAddressMapper.selectByPrimaryKey(orderTemp.getAddressid());
		order.setLongitude(pickAddress.getLongitude());
		order.setLatitude(pickAddress.getLatitude());
		order.setPhone(pickAddress.getPhone());
		order.setRname(pickAddress.getRname());
		order.setSex(pickAddress.getSex());
		order.setAddressname(pickAddress.getAddressname());
		order.setAddressdetail(pickAddress.getAddressdetail());
		order.setServerTime(orderTemp.getServerTime());
		order.setPicIds(orderTemp.getPicIds());
		order.setExpectTime(orderTemp.getExpectTime());
		order.setAuntMCount(orderTemp.getAuntMCount());
		order.setAuntWCount(orderTemp.getAuntWCount());
		order.setDayTime(orderTemp.getDayTime());
		order.setThingCount(orderTemp.getThingCount());
		order.setNeedTools(orderTemp.getNeedTools());
		order.setCompanyid(0);
		order.setExpectedPrice(orderTemp.getExpectedPrice());
		order.setDepositPrice(orderTemp.getDepositPrice());
		order.setTipPrice(orderTemp.getTipPrice());

		order.setOtherPirce(new BigDecimal(0));
		order.setCouponPirce(new BigDecimal(0));
		order.setOrderType(orderTemp.getOrderType());
		order.setRelationOrderid(0);
		order.setRatio(new Float(0));
		order.setRatioMoney(new BigDecimal(0));
		order.setState((short) 0);
		order.setComplaintState((short) 0);
		order.setCommentState((short) 0);
		order.setAcceptType(orderTemp.getAcceptType());
		order.setOrderSource(orderTemp.getOrderSource());
		order.setBook(orderTemp.getBook());
		order.setReasonMark(orderTemp.getReasonMark());
		order.setMonth(0f);
		order.setAddtime(new Date());
		order.setCity(pickAddress.getArea());
		if (orderTemp.getCategoryid() == 1 || orderTemp.getCategoryid() == 2 || orderTemp.getCategoryid() == 8) {

			order.setLastPrice(order.getExpectedPrice().multiply(new BigDecimal(order.getExpectTime())));

		} else if (orderTemp.getCategoryid() == 14 || orderTemp.getCategoryid() == 15 || orderTemp.getCategoryid() == 16
				|| orderTemp.getCategoryid() == 17 || orderTemp.getCategoryid() == 18) {
			// 维修工 长期工正式单
			if (order.getOrderType() == 0) {
				order.setLastPrice(new BigDecimal("0")); // 长期单
			} else if (order.getOrderType() == 1) {// 试单
				order.setLastPrice(order.getExpectedPrice().multiply(new BigDecimal(order.getExpectTime())));
			}

		} else {
			// 维修工
			order.setLastPrice(new BigDecimal("0"));
		}
		order.setFoodselect(orderTemp.getFoodselect());
		orderMapper.insert(order);

		// 计算需要支付的价格
		BigDecimal payMoney = new BigDecimal("0.00");
		if (order.getTipPrice() != null) {
			payMoney = payMoney.add(order.getTipPrice());
		}

		if (order.getDepositPrice() != null) {
			payMoney = payMoney.add(order.getDepositPrice());
		}

		OrderPay orderPay = new OrderPay();
		Long nowcountPay = orderPayMapper.selectOrderNumCount();
		String orderNum = createOrderNum(3, nowcountPay);
		orderPay.setOrdernum(orderNum);
		orderPay.setOrderid(order.getOrderid());
		if (order.getTipPrice() != null && order.getTipPrice().floatValue() > 0) {
			orderPay.setType((short) 5);
		} else {
			orderPay.setType((short) 1);
		}

		orderPay.setState((short) 0);
		orderPay.setPayType(CommonUtils.parseShort(pay_type, (short) 0));
		orderPay.setCouponid(0);
		orderPay.setCouponPirce(new BigDecimal(0));
		orderPay.setPirce(payMoney);
		orderPay.setAddtime(new Date());
		orderPayMapper.insert(orderPay);
		CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
		if (order.getAcceptType() == 0) {
			List<OrderAuntTemp> auntTemps = orderAuntTempMapper.selectByOrderid(Integer.parseInt(tempid));
			if (null != auntTemps && auntTemps.size() > 0) {
				for (OrderAuntTemp orderAuntTemp : auntTemps) {
					OrderPool orderPool = new OrderPool();
					orderPool.setOrderid_user(order.getOrderid());
					orderPool.setAuntid(orderAuntTemp.getUserid());
					orderPool.setUser_type((int) orderAuntTemp.getUserType());
					if (order.getServerTime() != null) {
						orderPool.setTitle(second.getName());
					}
					orderPool.setState((byte) 0);
					orderPool.setAddress(order.getAddressname() + order.getAddressdetail());
					if (order.getTipPrice() != null && order.getTipPrice().compareTo(new BigDecimal("0")) == 1) {
						orderPool.setTipState((byte) 1);
						orderPool.setPriceTip(order.getTipPrice());
					} else {
						orderPool.setTipState((byte) 0);
					}
					orderPool.setLatitude(order.getLatitude());
					orderPool.setLongitude(order.getLongitude());
					orderPool.setTotime("上门时间:" + CommonUtils.getTimeFormat(order.getServerTime(), "yyyy-MM-dd HH:mm"));
					orderPool.setPrice(order.getExpectedPrice());
					short accept = Short.valueOf(order.getAcceptType());
					orderPool.setOrderType((byte) accept);
					orderPool.setAddtime(new Date());
					orderPool.setCategoryid(order.getCategoryid());
					if (orderPool.getOrderType() == 0) {
						orderPool.setAunt_m_count(0);
						orderPool.setAunt_w_count(0);
					} else if (orderPool.getOrderType() == 1) {
						if (order.getCategoryid() == 5 || order.getCategoryid() == 6) {
							if (order.getAuntMCount() != null) {
								orderPool.setAunt_m_count(order.getAuntMCount());
							} else {
								orderPool.setAunt_m_count(0);
							}
							if (order.getAuntWCount() != null) {
								orderPool.setAunt_w_count(order.getAuntWCount());
							} else {
								orderPool.setAunt_w_count(0);
							}

						} else {

						}
					}

					orderPoolMapper.insert(orderPool);
				}
			}
		} else {
			setRobOrderPool(order);
		}

		copyTempToofficial(orderTemp, order);

		PayValue value = new PayValue();
		// TODO: 其他两张表的复制
		if (orderPay.getPayType() == 3) {
			// TODO:余额判断 减去余额
			UserExtra extra = userExtraMapper.selectByUserId(user.getUserid());
			if (extra.getBalance() != null && extra.getBalance().compareTo(payMoney) >= 0) {
				BigDecimal decimal = extra.getBalance();
				decimal = decimal.subtract(payMoney);
				extra.setBalance(decimal);
				userExtraMapper.updateByPrimaryKey(extra);
				BalanceRecord record = new BalanceRecord();
				record.setAddtime(new Date());
				record.setUserid(extra.getUserid());
				record.setCount(payMoney);
				record.setType((short) 1);
				balanceRecordMapper.insert(record);
				orderPay.setState((short) 1);
				orderPayMapper.updateByPrimaryKey(orderPay);
				order.setState((short) 1);
				orderMapper.updateByPrimaryKey(order);
				final Order orderQuote = order;
				fixedThreadPool.execute(new Runnable() {
					@Override
					public void run() {

						List<OrderPool> poolLIst = orderPoolMapper.selectListByOrderUser(orderQuote.getOrderid(), 0);
						if (poolLIst != null && poolLIst.size() > 0) {
							for (OrderPool orderPool : poolLIst) {
								orderPool.setState((byte) 1);
								orderPoolMapper.updateByPrimaryKey(orderPool);
								sendOrderMessageToAunt(orderQuote, orderPool);
							}
						}
					}
				});
				
			} else {
				result.setCode("2");
				result.setMessage("预约失败  余额不足 ");
				return result;
			}

			result.setCode("1");
			HashMap<String, Object> resultMap = new HashMap<>();
			resultMap.put("orderid", order.getOrderid());
			result.setResult(resultMap);
			result.setMessage("请求成功");
			return result;
		} else if (orderPay.getPayType() == 1) {
			Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(String.valueOf(payMoney.floatValue()),
					"用户下单", "用户下单", orderNum);
			String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
			String sign = OrderInfoUtil2_0.getSign(params, AlipayConfig.private_key);
			final String orderInfo = orderParam + "&" + sign;
			value.setBody(orderInfo);

			scheduledOrderPayService.schedule(new Runnable() {

				@Override
				public void run() {

					toCancelUnPayOrder(order.getOrderid());
				}
			}, 15 * 60, TimeUnit.SECONDS);

		} else if (orderPay.getPayType() == 2 || orderPay.getPayType() == 4) {
			scheduledOrderPayService.schedule(new Runnable() {

				@Override
				public void run() {

					toCancelUnPayOrder(order.getOrderid());
				}
			}, 15 * 60, TimeUnit.SECONDS);
		}

		
		orderTempMapper.deleteByPrimaryKey(orderTemp.getOrderid());
		
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("pay_type", orderPay.getPayType());
		resultMap.put("pay_money", payMoney.doubleValue());
		resultMap.put("thirdNumID", orderNum);
		resultMap.put("pay", value);
		resultMap.put("orderid", order.getOrderid());
		result.setResult(resultMap);
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	private void toCancelUnPayOrder(int orderid) {
		Order order = orderMapper.selectByPrimaryKey(orderid);
		if (order != null && order.getState() == 0) {
			order.setState((short) 15);
			orderMapper.updateByPrimaryKey(order);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	private void copyTempToofficial(OrderTemp orderTemp, Order order) {

		List<OrderTargetTemp> tempList = orderTargetTempMapper.selectByOrderid(orderTemp.getOrderid());
		if (tempList != null && tempList.size() > 0) {
			for (OrderTargetTemp temp : tempList) {
				OrderTarget orderTarget = new OrderTarget();
				orderTarget.setAge(temp.getAge());
				orderTarget.setBehaviorType(temp.getBehaviorType());
				orderTarget.setHeight(temp.getHeight());
				orderTarget.setIllnessType(temp.getIllnessType());
				orderTarget.setOrderid(order.getOrderid());
				orderTarget.setSex(temp.getSex());
				orderTarget.setWeight(temp.getWeight());
				orderTargetMapper.insert(orderTarget);
			}
		}

		List<OrderThirdTemp> thirdList = orderThirdTempMapper.selectByOrderid(orderTemp.getOrderid());
		if (thirdList != null && thirdList.size() > 0) {
			for (OrderThirdTemp thirdTemp : thirdList) {
				OrderThird orderThird = new OrderThird();
				orderThird.setCount(thirdTemp.getCount());
				orderThird.setName(thirdTemp.getName());
				orderThird.setOrderid(order.getOrderid());
				orderThirdMapper.insert(orderThird);
			}
		}

		/*
		 * List<OrderAuntTemp> auntTempList =
		 * orderAuntTempMapper.selectByOrderid(orderTemp.getOrderid());
		 * if(auntTempList != null && auntTempList.size()>0){ for(OrderAuntTemp
		 * thirdTemp:auntTempList){ OrderAunt orderAunt = new OrderAunt();
		 * orderAunt.set } }
		 */
	}

	private ApiResult setExpectedPrice(OrderTemp temp, List<CategoryThirdValue> thirdValues,
			List<TargetValue> targetValues, String city, String price) {
		ApiResult result = new ApiResult();
		int categoryid = temp.getCategoryid();
		if (categoryid == 1 || categoryid == 2 || categoryid == 8 || categoryid == 5 || categoryid == 6) {
			if (categoryid == 5 || categoryid == 6) {
				SerPrice serPrice = serPriceMapper.selectByCategoryAndThirdIdAndCity(categoryid, 0, city);
				if (serPrice != null) {
					temp.setExpectedPrice(new BigDecimal(price));
					temp.setDepositPrice(serPrice.getDepositPrice());
				} else {
					// temp.setExpectedPrice(decimal);
					// temp.setDepositPrice(decimal);
					result.setCode("2");
					result.setMessage("请求失败,系统配置错误");
					return result;
				}
			} else {
				SerPrice serPrice = serPriceMapper.selectByCategoryAndThirdIdAndCity(categoryid, 0, city);
				if (serPrice != null) {
					temp.setExpectedPrice(serPrice.getPrice());
					temp.setDepositPrice(serPrice.getDepositPrice());
				} else {
					// temp.setExpectedPrice(decimal);
					// temp.setDepositPrice(decimal);
					result.setCode("2");
					result.setMessage("请求失败,系统配置错误");
					return result;
				}
			}

		} else if (categoryid == 14 || categoryid == 15 || categoryid == 16 || categoryid == 17 || categoryid == 18) {
			// 试单
			SerPrice serPrice = serPriceMapper.selectByCategoryAndThirdIdAndCity(categoryid, 0, city);
			// 长期工
			if (temp.getOrderType() == 0) {
				if (serPrice != null) {
					temp.setExpectedPrice(serPrice.getPrice());
					temp.setDepositPrice(serPrice.getDepositPrice());
				} else {
					result.setCode("2");
					result.setMessage("请求失败,系统配置错误");
					return result;
				}
			} else if (temp.getOrderType() == 1) {

				if (serPrice != null) {
					temp.setExpectedPrice(serPrice.getPrice());
					temp.setDepositPrice(serPrice.getPriceSmall());
				} else {
					result.setCode("2");
					result.setMessage("请求失败,系统配置错误");
					return result;
				}
			}

			// SerPrice serPrice =
			// serPriceMapper.selectByCategoryAndThirdIdAndCity(categoryid,
			// 0,city);
			// if(serPrice != null){
			// temp.setExpectedPrice(decimal);
			// temp.setDepositPrice(serPrice.getDepositPrice());
			// }

		} else {
			// 维修工
			SerPrice serPrice = serPriceMapper.selectByCategoryAndThirdIdAndCity(categoryid, 0, city);
			if (serPrice != null) {
				temp.setExpectedPrice(serPrice.getPrice());
				temp.setDepositPrice(serPrice.getDepositPrice());
			} else {
				result.setCode("2");
				result.setMessage("请求失败,系统配置错误");
				return result;
			}
		}
		result.setCode("1");
		return result;

		
	}

	@Override
	public ApiResult getCanUseCoupon(User user, String orderid, String month) {
		ApiResult result = new ApiResult();
		List<CanUseCouponValue> listValue = new ArrayList<CanUseCouponValue>();
		Map<String, Object> map = new HashMap<String, Object>();
		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));

		List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());

		if (order != null && orderAuntList != null && orderAuntList.size() > 0) {
			// 小时工支付服务费
			if (order.getState() == 5 && (order.getCategoryid() == 1 || order.getCategoryid() == 2
					|| order.getCategoryid() == 8 || order.getCategoryid() == 5 || order.getCategoryid() == 6)) {
				// 计算条件金额
				BigDecimal price = new BigDecimal("0.00");
				price = order.getExpectedPrice().multiply(new BigDecimal(String.valueOf(order.getExpectTime())));
				if (order.getOtherPirce() != null) {
					price = price.add(order.getOtherPirce());
				}

				float priceCondition = price.floatValue();
				int cid = order.getCategoryid();
				int companyid = 0;
				boolean isALLCompanySame = false;
				for (int i = 0; i < orderAuntList.size(); i++) {
					if (orderAuntList.get(i).getCompanyid() == null || orderAuntList.get(i).getCompanyid() == 0) {
						isALLCompanySame = false;
						break;
					} else {
						if (i == 0) {
							companyid = orderAuntList.get(i).getCompanyid();
							isALLCompanySame = true;
						} else {
							if (companyid == orderAuntList.get(i).getCompanyid()) {
								isALLCompanySame = true;
							} else {
								isALLCompanySame = false;
								break;
							}
						}
					}
				}
				if (!isALLCompanySame) {
					companyid = 0;
				}
				List<CouponUser> couponUserList = couponUserMapper.selectCanUseCoupon(priceCondition, cid, companyid,
						user.getUserid());
				if (couponUserList != null) {
					for (CouponUser couponUser : couponUserList) {
						CanUseCouponValue va = new CanUseCouponValue();

						va.setCouponid(couponUser.getCouponid());
						if (couponUser.getCompanyid() == 0) {
							va.setTitle("表叔云官方平台");
							va.setRangetype(0);

						} else {
							Company company = companyMapper.selectByPrimaryKey(couponUser.getCompanyid());
							va.setTitle(company.getName());
							va.setRangetype(1);
							if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
								va.setLogourl(getFilePathById(company.getLogoPicid()));
							}

						}
						va.setDiscount(couponUser.getDiscount().toPlainString());
						va.setCondition(couponUser.getCouponCondition().toPlainString());

						CategorySecond secont = categorySecondMapper.selectByPrimaryKey(couponUser.getCategoryid());
						if (secont != null) {
							va.setCategory(secont.getName());
						}
						va.setTimestr("有效期至" + CommonUtils.getTimeFormat(couponUser.getUseEtime(), "yyyy.MM.dd"));
						listValue.add(va);
					}
				}

			} else if (order.getState() == 11 && (order.getCategoryid() == 3 || order.getCategoryid() == 4
					|| order.getCategoryid() == 7 || order.getCategoryid() == 9 || order.getCategoryid() == 10
					|| order.getCategoryid() == 11 || order.getCategoryid() == 12 || order.getCategoryid() == 13)) {// 维修订单

				// 计算条件金额
				BigDecimal price = new BigDecimal("0.00");
				price = order.getLastPrice().add(order.getDepositPrice());
				float priceCondition = price.floatValue();
				int cid = order.getCategoryid();
				int companyid = orderAuntList.get(0).getCompanyid();

				List<CouponUser> couponUserList = couponUserMapper.selectCanUseCoupon(priceCondition, cid, companyid,
						user.getUserid());
				if (couponUserList != null) {
					for (CouponUser couponUser : couponUserList) {
						CanUseCouponValue va = new CanUseCouponValue();

						va.setCouponid(couponUser.getCouponid());
						if (couponUser.getCompanyid() == 0) {
							va.setTitle("表叔云官方平台");
							va.setRangetype(0);

						} else {
							Company company = companyMapper.selectByPrimaryKey(couponUser.getCompanyid());
							va.setTitle(company.getName());
							va.setRangetype(1);
							if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
								va.setLogourl(getFilePathById(company.getLogoPicid()));
							}

						}
						va.setDiscount(couponUser.getDiscount().toPlainString());
						va.setCondition(couponUser.getCouponCondition().toPlainString());

						CategorySecond secont = categorySecondMapper.selectByPrimaryKey(couponUser.getCategoryid());
						if (secont != null) {
							va.setCategory(secont.getName());
						}
						va.setTimestr("有效期至" + CommonUtils.getTimeFormat(couponUser.getUseEtime(), "yyyy.MM.dd"));
						listValue.add(va);
					}
				}

			} else if ((order.getState() == 4 || order.getState() == 5)
					&& (order.getCategoryid() == 14 || order.getCategoryid() == 15 || order.getCategoryid() == 16
							|| order.getCategoryid() == 17 || order.getCategoryid() == 18)) {// 长期工
				// 计算条件金额
				BigDecimal price = new BigDecimal("0.00");
				int monthInt = CommonUtils.parseInt(month, 0);
				if (monthInt > 0 && order.getExpectTime() - order.getMonth() >= monthInt) {
					price = order.getExpectedPrice().multiply(new BigDecimal(month));
					if (order.getOtherPirce() != null) {
						price = price.add(order.getOtherPirce());
					}

					float priceCondition = price.floatValue();
					int cid = order.getCategoryid();
					int companyid = orderAuntList.get(0).getCompanyid();

					List<CouponUser> couponUserList = couponUserMapper.selectCanUseCoupon(priceCondition, cid,
							companyid, user.getUserid());
					if (couponUserList != null) {
						for (CouponUser couponUser : couponUserList) {
							CanUseCouponValue va = new CanUseCouponValue();

							va.setCouponid(couponUser.getCouponid());
							if (couponUser.getCompanyid() == 0) {
								va.setTitle("表叔云官方平台");
								va.setRangetype(0);

							} else {
								Company company = companyMapper.selectByPrimaryKey(couponUser.getCompanyid());
								va.setTitle(company.getName());
								va.setRangetype(1);
								if (company.getLogoPicid() != null && company.getLogoPicid() > 0) {
									va.setLogourl(getFilePathById(company.getLogoPicid()));
								}

							}
							va.setDiscount(couponUser.getDiscount().toPlainString());
							va.setCondition(couponUser.getCouponCondition().toPlainString());

							CategorySecond secont = categorySecondMapper.selectByPrimaryKey(couponUser.getCategoryid());
							if (secont != null) {
								va.setCategory(secont.getName());
							}
							va.setTimestr("有效期至" + CommonUtils.getTimeFormat(couponUser.getUseEtime(), "yyyy.MM.dd"));
							listValue.add(va);
						}
					}

				}

			} else {
			}
		}

		result.setCode("1");
		result.setMessage("请求成功");
		map.put("couponList", listValue);
		result.setResult(map);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult cancel(User user, String orderid) {
		ApiResult apiResult = new ApiResult();
		Map<String, Object> map = new HashMap<String, Object>();
		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));

		if (order == null) {
			apiResult.setCode("2");
			apiResult.setMessage("退单失败,订单不存在");
			return apiResult;
		}

		Short state = order.getState();
		if (state == 0 || state == 6 || state == 7 || state == 8 || state == 9 || state == 12 || state == 13
				|| state == 14 || state == 15) {
			apiResult.setCode("3");
			apiResult.setMessage("退单失败,订单状态错误");
			return apiResult;
		}
		switch (state) {
		case 1:
			// 退单: 1.定金全退
			map.put("state", "定金支付成功");
			BigDecimal refundMoney = new BigDecimal("0.00");
			if (order.getDepositPrice() != null) {
				refundMoney = refundMoney.add(order.getDepositPrice());
			}
			if (order.getTipPrice() != null) {
				refundMoney = refundMoney.add(order.getTipPrice());
			}
			map.put("money", refundMoney.floatValue());
			if (order.getTipPrice() != null && order.getTipPrice().floatValue() > 0) {
				map.put("moneystr", "定金、小费全退");
			} else {
				map.put("moneystr", "定金全退");
			}
			map.put("price", 0);
			map.put("pricestr", "");
			break;
		case 2:
			// 退单: 定金退一半
			map.put("state", "接单/抢单成功");

			refundMoney = new BigDecimal("0.00");
			if (order.getDepositPrice() != null) {
				refundMoney = order.getDepositPrice().divide(new BigDecimal(2));
			}
			if (order.getTipPrice() != null) {
				refundMoney = refundMoney.add(order.getTipPrice());
			}
			map.put("money", refundMoney.floatValue());

			if (order.getTipPrice() != null && order.getTipPrice().floatValue() > 0) {
				map.put("moneystr", "定金退一半,小费全退");
			} else {
				map.put("moneystr", "定金退一半");
			}
			map.put("price", 0);
			map.put("pricestr", "");
			break;
		case 3:
			// 退单: 不退定金
			map.put("state", "已出发");
			map.put("money", 0);
			if (order.getTipPrice() != null && order.getTipPrice().floatValue() > 0) {
				map.put("moneystr", "不退定金,小费全退");
			} else {
				map.put("moneystr", "不退定金");
			}
			map.put("price", 0);
			map.put("pricestr", "");
			break;
		case 4:
			// 退单: 按小时收钱
			map.put("state", "开始服务");
			map.put("money", 0);
			map.put("moneystr", "不退定金、小费");
			map.put("price", 1);
			map.put("pricestr", "由于订单已经开始服务,退单需要您跟阿姨协商本次的服务费用");
			break;
		case 5:
			map.put("state", "服务完成");
			map.put("money", 0);
			map.put("moneystr", "不退定金、小费");
			map.put("price", 1);
			map.put("pricestr", "由于订单已经服务完成,退单需要您跟阿姨协商本次的服务费用");
			break;
		case 10:
			map.put("state", "部分服务人员拒单");
			refundMoney = new BigDecimal("0.00");
			if (order.getDepositPrice() != null) {
				refundMoney = refundMoney.add(order.getDepositPrice());
			}
			if (order.getTipPrice() != null) {
				refundMoney = refundMoney.add(order.getTipPrice());
			}
			map.put("money", refundMoney.floatValue());
			if (order.getTipPrice() != null && order.getTipPrice().floatValue() > 0) {
				map.put("moneystr", "定金、小费全退");
			} else {
				map.put("moneystr", "定金全退");
			}
			map.put("price", 0);
			map.put("pricestr", "");
			break;
		case 11:
			map.put("state", "待支付维修费用");
			map.put("money", 0);
			map.put("moneystr", "不退定金、小费");
			map.put("price", 0);
			map.put("pricestr", "");
			break;
		default:
			apiResult.setCode("3");
			apiResult.setMessage("退单失败,订单状态错误");
			return apiResult;
		}

		apiResult.setMessage("请求成功");
		apiResult.setCode("1");
		apiResult.setResult(map);
		return apiResult;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult cancelOrder(User user, String orderid, String reason, String content, String pay_type) {
		ApiResult apiResult = new ApiResult();

		content = CommonUtils.removeAllEmojis(content);

		HashMap<String, Object> map = new HashMap<>();
		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (order == null) {
			apiResult.setCode("2");
			apiResult.setMessage("订单不存在");
			return apiResult;
		}
		Short state = order.getState();
		if (state == 0 || state == 6 || state == 7 || state == 8 || state == 9 || state == 12 || state == 13
				|| state == 14 || state == 15) {
			apiResult.setCode("3");
			apiResult.setMessage("退单失败,订单状态错误");
			return apiResult;
		}
		// 计算退款金额，进行退款
		if (state == 1 || state == 2 || state == 3 || state == 10) {
			OrderPay payInfo = orderPayMapper.getdownPayMent(CommonUtils.parseInt(orderid, 0));
			if (payInfo == null) {
				apiResult.setCode("2");
				apiResult.setMessage("订单支付信息不存在");
				return apiResult;
			}
			BigDecimal refund_fee = null;
			if (payInfo.getType() == 5) {
				switch (state) {
				case 1:
				case 10:
					refund_fee = payInfo.getPirce();
					break;
				case 2:
					refund_fee = order.getDepositPrice().divide(new BigDecimal(2)).add(order.getTipPrice());
					break;
				case 3:
					refund_fee = order.getTipPrice();
					break;
				}
			} else {
				switch (state) {
				case 1:
				case 10:
					refund_fee = payInfo.getPirce();
					break;
				case 2:
					refund_fee = payInfo.getPirce().divide(new BigDecimal(2));
					break;
				case 3:
					refund_fee = new BigDecimal(0);
					break;
				}
			}
			if (refund_fee.doubleValue() > 0) {
				Long refundOrdernum = orderRefundMapper.selectOrderRefundCount();
				String refundNumId = createOrderNum(5, refundOrdernum);
				short orderPayType = payInfo.getPayType();
				OrderRefund oRefund = new OrderRefund();
				oRefund.setAddtime(new Date());
				oRefund.setOrderid(order.getOrderid());
				oRefund.setOrderpayid(payInfo.getDataid());
				oRefund.setOrdernum(refundNumId);
				oRefund.setType((byte) 1);
				oRefund.setState((byte) 0);
				oRefund.setPayType((byte) orderPayType);
				oRefund.setPirce(refund_fee);
				orderRefundMapper.insertSelective(oRefund);
				if (orderPayType == 1) {
					boolean refundresult = AlipayRefundTrade.alipayRefundRequest(refundNumId, payInfo.getOrdernum(),
							payInfo.getThirdOrderid(), refund_fee.doubleValue());
					if (!refundresult) {
						apiResult.setCode("2");
						apiResult.setMessage("支付宝退款异常，请联系平台");
						return apiResult;
					} else {
						oRefund.setState((byte) 1);
						orderRefundMapper.updateByPrimaryKeySelective(oRefund);
					}
				} else if (orderPayType == 2) {
					boolean refundresult = WxRefund.doRefund(orderPayType, payInfo.getOrdernum(), refundNumId,
							payInfo.getPirce().floatValue(), refund_fee.floatValue());
					if (!refundresult) {
						apiResult.setCode("2");
						apiResult.setMessage("微信退款异常，请联系平台");
						return apiResult;
					} else {
						oRefund.setState((byte) 1);
						orderRefundMapper.updateByPrimaryKeySelective(oRefund);
					}
				} else if (orderPayType == 3) {
					UserExtra userExtra = userExtraMapper.selectByUserId(user.getUserid());
					if (userExtra == null) {
						apiResult.setCode("2");
						apiResult.setMessage("该用户信息不存在");
						return apiResult;
					}
					userExtra.setBalance(userExtra.getBalance().add(refund_fee));
					userExtraMapper.updateByPrimaryKey(userExtra);
					oRefund.setState((byte) 1);
					orderRefundMapper.updateByPrimaryKeySelective(oRefund);
				}
			}
			// 更新订单表
			order.setState((short) 7);
			orderMapper.updateByPrimaryKey(order);
			if (state == 1) {
				orderPoolMapper.deleteByUserOrderId(order.getOrderid());
			}
			if (state == 10) {
				List<OrderAunt> auntOrderList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
				if (auntOrderList != null) {
					for (OrderAunt orderAunt : auntOrderList) {
						orderAunt.setState((short) 7);
						orderAuntMapper.updateByPrimaryKey(orderAunt);
					}
				}
			}
		} else if (state == 11) {
			order.setState((short) 7);
			orderMapper.updateByPrimaryKey(order);
			List<OrderAunt> auntOrderList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
			if (auntOrderList != null) {
				for (OrderAunt orderAunt : auntOrderList) {
					orderAunt.setState((short) 7);
					orderAuntMapper.updateByPrimaryKey(orderAunt);
				}
			}
		} else if (state == 4 || state == 5) {
			order.setState((short) 13);
			orderMapper.updateByPrimaryKey(order);
			List<OrderAunt> auntOrderList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
			if (auntOrderList != null) {
				for (OrderAunt orderAunt : auntOrderList) {
					orderAunt.setState((short) 13);
					orderAuntMapper.updateByPrimaryKey(orderAunt);
				}
			}
		}
		if (state != 11) {
			// 小费全部退还
			List<OrderPay> tipList = orderPayMapper.getTipList(CommonUtils.parseInt(orderid, 0));
			for (OrderPay tipPay : tipList) {
				if (tipPay.getPirce().doubleValue() > 0) {
					Long refundOrdernum = orderRefundMapper.selectOrderRefundCount();
					String refundNumId = createOrderNum(5, refundOrdernum);
					short orderPayType = tipPay.getPayType();
					OrderRefund oRefund = new OrderRefund();
					oRefund.setAddtime(new Date());
					oRefund.setOrderid(order.getOrderid());
					oRefund.setOrderpayid(tipPay.getDataid());
					oRefund.setOrdernum(refundNumId);
					oRefund.setType((byte) 1);
					oRefund.setState((byte) 0);
					oRefund.setPayType((byte) orderPayType);
					oRefund.setPirce(tipPay.getPirce());
					orderRefundMapper.insertSelective(oRefund);
					if (orderPayType == 1) {
						boolean refundresult = AlipayRefundTrade.alipayRefundRequest(refundNumId, tipPay.getOrdernum(),
								tipPay.getThirdOrderid(), tipPay.getPirce().doubleValue());
						if (refundresult) {
							oRefund.setState((byte) 1);
							orderRefundMapper.updateByPrimaryKeySelective(oRefund);
						}
					} else if (orderPayType == 2) {
						boolean refundresult = WxRefund.doRefund(orderPayType, tipPay.getOrdernum(), refundNumId,
								tipPay.getPirce().floatValue(), tipPay.getPirce().floatValue());
						if (refundresult) {
							oRefund.setState((byte) 1);
							orderRefundMapper.updateByPrimaryKeySelective(oRefund);
						}
					} else if (orderPayType == 3) {
						UserExtra userExtra = userExtraMapper.selectByUserId(user.getUserid());
						userExtra.setBalance(userExtra.getBalance().add(tipPay.getPirce()));
						userExtraMapper.updateByPrimaryKey(userExtra);
						oRefund.setState((byte) 1);
						orderRefundMapper.updateByPrimaryKeySelective(oRefund);
					}
				}
			}
		}
		List<OrderAunt> auntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		if(auntList != null && auntList.size()>0){
			for(OrderAunt orderAunt:auntList){
				orderAunt.setState((short)7);
				orderAuntMapper.updateByPrimaryKey(orderAunt);
			}
		}
		
		
		final Order orderQuete = order;
		if(null != orderQuete){
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					sendCancelMessageToAunt(orderQuete);
				}
			});
		}

		apiResult.setCode("1");
		apiResult.setMessage("请求成功");
		apiResult.setResult(map);
		return apiResult;
	}

	@Override
	public ApiResult getAuntServer(int auntid) {

		ApiResult result = new ApiResult();
		List<AuntSkill> auntSkill = auntSkillMapper.selectByAuntIdTo(auntid);
		List<ServerList> serverLists = new ArrayList<>();
		if (auntSkill != null) {
			for (AuntSkill ak : auntSkill) {
				CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(ak.getCategoryid());
				// SerPrice serPrice =
				// serPriceMapper.selectByCategoryId(ak.getCategoryid());
				ServerList server = new ServerList();
				server.setServer_name(categorySecond.getName());
				server.setServerid(categorySecond.getDataid());
				server.setNeedpic(categorySecond.getNeedpic());
				// server.setDeposit_price(
				// serPrice != null ?
				// CommonUtils.parseFloat(serPrice.getDepositPrice() + "", 0) :
				// (float) 0);
				if (categorySecond.getDataid() - 10 == 0) {
					String name = "";
					List<CategoryThird> categoryThirds = categoryThirdMapper
							.selectByCategoryid(categorySecond.getDataid());
					for (CategoryThird ct : categoryThirds) {
						name = ct.getName() + ",";
					}
					server.setThirdtips(name.substring(0, name.length() - 1));
				}
				serverLists.add(server);
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("serverlist", serverLists);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult getCompanyServer(int companyid) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		List<CompanyRange> companyRanges = companyRangeMapper.selectByCompany(companyid);
		List<ServerList> serverLists = new ArrayList<>();
		if (companyRanges != null) {
			for (CompanyRange cr : companyRanges) {
				CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(cr.getCategoryid());
				if (categorySecond != null) {
					// SerPrice serPrice =
					// serPriceMapper.selectByCategoryId(cr.getCategoryid());
					ServerList server = new ServerList();
					server.setServer_name(categorySecond.getName());
					server.setServerid(categorySecond.getDataid());
					server.setNeedpic(categorySecond.getNeedpic());
					// server.setDeposit_price(
					// serPrice != null ?
					// CommonUtils.parseFloat(serPrice.getDepositPrice() + "",
					// 0) : (float) 0);
					if (categorySecond.getDataid() - 10 == 0) {
						String name = "";
						List<CategoryThird> categoryThirds = categoryThirdMapper
								.selectByCategoryid(categorySecond.getDataid());
						for (CategoryThird ct : categoryThirds) {
							name = ct.getName() + ",";
						}
						server.setThirdtips(name.substring(0, name.length() - 1));
					}
					serverLists.add(server);
				}
			}
		}
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("serverlist", serverLists);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult getThirdCategory(int serverid) {
		ApiResult result = new ApiResult();
		// FirstList firstList = new FirstList();
		// List<SecondList> secondList = new ArrayList<>();
		List<FirstList> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		/*
		 * CategorySecond categorySecond = categorySecondMapper
		 * .selectByPrimaryKey(serverid);
		 */
		List<CategoryThird> categoryThirds = categoryThirdMapper.selectByCategoryid(serverid);
		// firstList.setName(categorySecond.getName());
		if (categoryThirds != null) {
			for (CategoryThird categoryThird : categoryThirds) {
				FirstList firstList = new FirstList();
				firstList.setName(categoryThird.getName());
				firstList.setId(categoryThird.getDataid());
				List<SecondList> secondList = new ArrayList<>();
				firstList.setSecondList(secondList);
				list.add(firstList);
				List<CategoryThird> cst = categoryThirdMapper.selectByFid(categoryThird.getDataid());
				if (cst != null && cst.size() > 0) {
					for (CategoryThird c2 : cst) {
						SecondList s = new SecondList();
						s.setId(c2.getDataid());
						s.setName(c2.getName());
						secondList.add(s);

					}
				}

			}
		}

		result.setCode("1");
		result.setMessage("请求成功");
		map.put("firstList", list);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult orderList(int userid, int type, int page) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		List<Orderlist> orderlists = new ArrayList<>();
		List<Order> orders = orderMapper.selectByUseridAndTpye(userid, type, (page - 1) * 10);
		List<Order> ordersMore = orderMapper.selectByUseridAndTpye(userid, type, (page) * 10);

		// Long count = orderMapper.selectByUseridAndTpyeCount(userid, type,
		// (page - 1) * 10);
		for (Order order : orders) {
			Orderlist or = new Orderlist();
			or.setOrderid(order.getOrderid());
			CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
			if (categorySecond != null) {
				or.setServername(categorySecond.getName());
				or.setCategoryid(categorySecond.getDataid());
				if (order.getServerTime() != null) {
					or.setTimetitle(CommonUtils.getTimeFormat(order.getServerTime(), null) + "");
				}
				or.setAddress(order.getAddressname() + order.getAddressdetail());

				if (order.getState() == 0) {
					or.setPaylasttime(
							String.valueOf(15 * 60 - ((new Date().getTime() - order.getAddtime().getTime()) / 1000)));
				} else {
					or.setPaylasttime("0");
				}

				if (order.getState() == 3) {// 已经出发
					or.setServertime("0");
				} else if (order.getState() == 4) {// 开始服务
					if (order.getServerStartTime() != null) {
						or.setServertime(
								String.valueOf((new Date().getTime() - order.getServerStartTime().getTime()) / 1000));
					} else {
						or.setServertime("0");
					}
				} else if (order.getState() == 5 || order.getState() == 6) {// 服务完成
					if (order.getServerStartTime() != null && order.getServerEndTime() != null) {
						or.setServertime(String.valueOf(
								(order.getServerEndTime().getTime() - order.getServerStartTime().getTime()) / 1000));
					} else {
						or.setServertime("0");
					}
				} else {
					or.setServertime("0");
				}

				if (order.getComplaintState() == 1) {
					or.setState(20);
				} else {
					or.setState(order.getState());
				}
				// or
				or.setAccept_type(order.getAcceptType());
				or.setComment_state(order.getCommentState());
				if (order.getOrderType() != null) {
					or.setOrder_type(order.getOrderType());
				} else {
					or.setOrder_type(0);
				}

				if (order.getCategoryid() >= 14 && order.getCategoryid() <= 18) {// 长期工
					if (order.getExpectedPrice() != null) {
						or.setExpected_price(order.getExpectedPrice().floatValue());
					} else {
						or.setExpected_price(0f);
					}

					if (order.getExpectTime() != null) {
						if (order.getMonth() != null) {
							if (order.getExpectTime() - order.getMonth() > 0) {
								or.setCanpay(1);
							} else {
								if (order.getOtherPirce() != null && order.getOtherPirce().floatValue() > 0) {
									or.setCanpay(1);
								} else {
									or.setCanpay(0);
								}
							}
						} else {
							or.setCanpay(1);
						}
					} else {
						or.setCanpay(0);
					}
				} else {
					or.setCanpay(0);
					or.setExpected_price(0f);
				}

				/*
				 * List<AuntList> auntList = new ArrayList<>(); List<OrderAunt>
				 * orderAunt =
				 * orderAuntMapper.selectByOrderidAndUserid(order.getOrderid(),
				 * userid); if (orderAunt != null) { for (OrderAunt oa :
				 * orderAunt) { if (oa.getAuntid() != null && oa.getAuntid() >
				 * 0) { Aunt aunt =
				 * auntMapper.selectByPrimaryKey(oa.getAuntid()); AuntExtra
				 * auntExtra = auntExtramapper.selectByAuntId(oa.getAuntid());
				 * AuntList a = new AuntList(); a.setAuntid(oa.getAuntid()); if
				 * (aunt.getAvatar() != null && aunt.getAvatar() > 0) {
				 * a.setPicurl(getFilePathById(aunt.getAvatar())); } else {
				 * a.setPicurl(aunt.getThirdAvatar()); } if (orderAunt != null)
				 * { a.setName(aunt.getRealName()); } if (auntExtra != null) {
				 * a.setScore(auntExtra.getScore()); }
				 * a.setPhone(aunt.getPhone()); try { if (aunt.getBirthday() !=
				 * null) { a.setAge(CommonUtils.getAge(aunt.getBirthday())); } }
				 * catch (Exception e) { e.printStackTrace(); } auntList.add(a);
				 * }
				 * 
				 * } } or.setAuntList(auntList);
				 */

			}

			orderlists.add(or);
		}
		if (ordersMore == null || ordersMore.size() > 0) {
			map.put("havemore", 1);
		} else {
			map.put("havemore", 0);
		}
		result.setCode("1");
		result.setMessage("请求成功");
		map.put("orderlists", orderlists);
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult orderDetail(int userid, int orderid) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		List<AuntList> auntList = new ArrayList<>();
		String expect_time = "";
		String day_time = "";
		String server_time = "";
		Order order = orderMapper.selectByPrimaryKey(orderid);
		OrderInfo orderInfo = new OrderInfo();
		if (order != null) {
			orderInfo.setOrderid(order.getOrderid());
			if (order.getCommentState() != null) {
				orderInfo.setComment_state(order.getCommentState());
			} else {
				orderInfo.setComment_state(0);
			}

			CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
			if (categorySecond != null) {
				orderInfo.setServername(categorySecond.getName());
				orderInfo.setCategoryid(categorySecond.getDataid());
			}
			if (order.getServerTime() != null) {
				orderInfo.setTimetitle(CommonUtils.getTimeFormat(order.getServerTime(), null) + "");
			}
			orderInfo.setAddress(order.getAddressname() + order.getAddressdetail());

			if (order.getState() == 0) {
				orderInfo.setPaylasttime(
						String.valueOf(15 * 60 - ((new Date().getTime() - order.getAddtime().getTime()) / 1000)));
			} else {
				orderInfo.setPaylasttime("0");
			}

			if (order.getOrderType() != null) {
				orderInfo.setOrder_type(order.getOrderType());
			} else {
				orderInfo.setOrder_type(0);
			}
			orderInfo.setAccept_type(order.getAcceptType());
			
			if (order.getCategoryid() >= 14 && order.getCategoryid() <= 18) {// 长期工
				if (order.getExpectedPrice() != null) {
					orderInfo.setExpected_price(order.getExpectedPrice().floatValue());
				} else {
					orderInfo.setExpected_price(0f);
				}

				if (order.getExpectTime() != null) {
					if (order.getMonth() != null) {
						if (order.getExpectTime() - order.getMonth() > 0) {
							orderInfo.setCanpay(1);
						} else {
							if (order.getOtherPirce() != null && order.getOtherPirce().floatValue() > 0) {
								orderInfo.setCanpay(1);
							} else {
								orderInfo.setCanpay(0);
							}
						}
					} else {
						orderInfo.setCanpay(1);
					}
				} else {
					orderInfo.setCanpay(0);
				}
			} else {
				orderInfo.setCanpay(0);
				orderInfo.setExpected_price(0f);
			}

			/*
			 * if (order.getServerStartTime() != null &&
			 * order.getServerEndTime() != null) {
			 * orderInfo.setServertime(order.getServerStartTime().getTime() -
			 * order.getServerEndTime().getTime() + ""); //expect_time =
			 * order.getServerStartTime().getTime() -
			 * order.getServerEndTime().getTime() + "";
			 * 
			 * }
			 */
			float time = order.getExpectTime();
			expect_time = (int) time + "";

			if (order.getState() == 3) {// 已经出发
				orderInfo.setServertime("0");
			} else if (order.getState() == 4) {// 开始服务
				if (order.getServerStartTime() != null) {
					orderInfo.setServertime(
							String.valueOf((new Date().getTime() - order.getServerStartTime().getTime()) / 1000));
				} else {
					orderInfo.setServertime("0");
				}
			} else if (order.getState() == 5 || order.getState() == 6) {// 服务完成
				if (order.getServerStartTime() != null && order.getServerEndTime() != null) {
					orderInfo.setServertime(String.valueOf(
							(order.getServerEndTime().getTime() - order.getServerStartTime().getTime()) / 1000));
				} else {
					orderInfo.setServertime("0");
				}
			} else {
				orderInfo.setServertime("0");
			}

			if (order.getComplaintState() == 1) {
				orderInfo.setState(20);
			} else {
				orderInfo.setState(order.getState());
			}
			day_time = order.getDayTime() + "";
			if (order.getServerTime() != null) {
				server_time = CommonUtils.getTimeFormat(order.getServerTime(), "yyyy-MM-dd HH:mm") + "";
			}
			
			if(order.getServerStartTime() != null){
				orderInfo.setStart_time(CommonUtils.getTimeFormat(order.getServerStartTime(), "yyyy-MM-dd HH:mm"));
			}
			if(order.getServerEndTime() != null){
				orderInfo.setEnd_time(CommonUtils.getTimeFormat(order.getServerEndTime(), "yyyy-MM-dd HH:mm"));
			}

		}
		List<OrderAunt> orderAunt = orderAuntMapper.selectByOrderidAndUserid(orderid, userid);
		if (orderAunt != null) {
			for (OrderAunt oa : orderAunt) {
				if (oa.getAuntid() != null && oa.getAuntid() > 0) {
					Aunt aunt = auntMapper.selectByPrimaryKey(oa.getAuntid());
					AuntExtra auntExtra = auntExtramapper.selectByAuntId(oa.getAuntid());
					AuntList a = new AuntList();
					a.setAuntid(oa.getAuntid());
					/*
					 * if (!CommonUtils.isEmptyString(oa.getPicIds())) {
					 * a.setPicurl(getFilePathById(new
					 * Integer(oa.getPicIds()))); }
					 */
					if (aunt.getAvatar() != null && aunt.getAvatar() > 0) {
						a.setPicurl(getFilePathById(aunt.getAvatar()));
					} else {
						a.setPicurl(aunt.getThirdAvatar());
					}
					if (orderAunt != null) {
						a.setName(aunt.getRealName());
					}
					if (auntExtra != null) {
						a.setScore(auntExtra.getScore());
					}
					a.setPhone(aunt.getPhone());
					try {
						a.setAge(CommonUtils.getAge(aunt.getBirthday()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					auntList.add(a); //
				}

			}
		}
		int auntcount = auntList.size();
		result.setCode("1");
		result.setMessage("请求成功"); //
		orderInfo.setAuntList(auntList);
		orderInfo.setAuntcount(auntcount);
		orderInfo.setExpect_time(expect_time);
		orderInfo.setDay_time(day_time);
		orderInfo.setServer_time(server_time);
		orderInfo.setOrdernum(order.getOrdernum());
		orderInfo.setReason_mark(order.getReasonMark());
		serPriceDetail(orderInfo,order);
		map.put("orderInfo", orderInfo);
		/*
		 * map.put("auntList", auntList); map.put("auntcount", auntcount);
		 * map.put("expect_time", expect_time); // map.put("day_time",
		 * day_time); map.put("server_time", server_time);//
		 */
		result.setResult(map);
		return result; // 你咋这么纠结。
	}

	private void serPriceDetail(OrderInfo orderInfo, Order order) {
		
		int cid = order.getCategoryid();
		BigDecimal decimal = new BigDecimal("0");
		if (cid == 1 || cid == 2 || cid == 8) {// 普通类
			if(order.getExpectedPrice() != null){
				decimal = decimal.add(order.getExpectedPrice());
			}
			if(order.getExpectTime() != null){
				decimal = decimal.multiply(new BigDecimal(order.getExpectTime()));
			}
			
		} else if(cid == 5 || cid == 6){
			if(cid == 5){
				if(order.getExpectedPrice() != null){
					decimal = decimal.add(order.getExpectedPrice());
				}
				if(order.getExpectTime() != null){
					decimal = decimal.multiply(new BigDecimal(order.getExpectTime()));
				}
				int count = 0;
				if(order.getAuntMCount() != null){
					count += order.getAuntMCount();
				}
				if(order.getAuntWCount() != null){
					count += order.getAuntWCount();
				}
				decimal.multiply(new BigDecimal(""+count));
				
			}else {
				if(order.getExpectedPrice() != null){
					decimal = decimal.add(order.getExpectedPrice());
				}
				if(order.getExpectTime() != null){
					decimal = decimal.multiply(new BigDecimal(order.getExpectTime()));
				}
				int count = 0;
				if(order.getAuntMCount() != null){
					count += order.getAuntMCount();
				}
				if(order.getAuntWCount() != null){
					count += order.getAuntWCount();
				}
				
				decimal.multiply(new BigDecimal(""+count));
				if(order.getDayTime() != null){
					decimal = decimal.multiply(new BigDecimal(order.getDayTime()));
				}
			}
			
			
		}else if (cid == 3 || cid == 4 || cid == 7 || cid == 9 || cid == 10
				|| cid == 11 || cid == 12 || cid == 13) {// 上门费用类
		
			if(order.getDepositPrice() != null){
				decimal = decimal.add(order.getDepositPrice());
			}
			
		} else if (cid == 14 || cid == 15 || cid == 16 || cid == 17 || cid == 18) {// 长期
			if(order.getExpectedPrice() != null){
				decimal = decimal.add(order.getExpectedPrice());
			}
			if(order.getExpectTime() != null){
				decimal = decimal.multiply(new BigDecimal(order.getExpectTime()));
			}
		}
		
		orderInfo.setEstimate_price(decimal.setScale(2, BigDecimal.ROUND_UP).toPlainString());
		
	}

	/*
	 * private BigDecimal getDepositPrice(OrderTemp temp){ BigDecimal decimal =
	 * new BigDecimal(0); return decimal; }
	 */

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult payOrder(String userid, String orderid) {
		ApiResult result = new ApiResult();
		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (order == null) {
			result.setCode("2");
			result.setMessage("订单不存在");
			return result;
		}

		PayInfo info = new PayInfo();
		MaintenanceFee maintenanceFee = new MaintenanceFee();
		int cid = order.getCategoryid();

		if (order.getState() == (short) 5) {// 支付订单最后服务费

			if (cid == 1 || cid == 2 || cid == 8 || cid == 14 || cid == 15 || cid == 16 || cid == 17 || cid == 18) {

				if (order.getOtherPirce() != null) {
					info.setOther_pirce(order.getOtherPirce().doubleValue());
				} else {
					info.setOther_pirce(0.00d);
				}
				info.setPeoplecount(1);

				if (order.getTipPrice() != null) {
					info.setTip_price(order.getTipPrice().doubleValue());
				} else {
					info.setTip_price(0.00d);
				}

				info.setUnit_price(order.getExpectedPrice().doubleValue());
				double hourTime = 0;
				if (order.getServerEndTime() != null && order.getServerStartTime() != null) {
					long timeMill = order.getServerEndTime().getTime() - order.getServerStartTime().getTime();
					double hour = Math.ceil(timeMill / 1000d / 60d / 60d);

					info.setServertime((int) hour);
					hourTime = hour;

				} else {
					if (order.getExpectTime() != null) {
						info.setServertime((int) order.getExpectTime().floatValue());
						hourTime = order.getExpectTime().floatValue();
					} else {
						info.setServertime(0);
						hourTime = 0;
					}

				}
				BigDecimal pay = order.getLastPrice();
				if(cid == 1 || cid == 2 || cid == 8){
					pay = new BigDecimal("0");
					pay = pay.add(order.getExpectedPrice());
					if(hourTime < 2){
						pay = pay.multiply(new BigDecimal(""+hourTime));
					}else {
						pay = pay.multiply(new BigDecimal(""+hourTime));
					}
				}

				if (order.getOtherPirce() != null) {
					pay = pay.add(order.getOtherPirce());
				}
				
				
				pay = pay.subtract(order.getDepositPrice());
				if(pay.compareTo(new BigDecimal(""+0)) == 1){
					
				}else {
					pay = new BigDecimal("0.01");
				}

				info.setPrice(pay.doubleValue());
			} else if (cid == 5 || cid == 6) {
				Integer aunt_m_count = order.getAuntMCount();
				Integer aunt_w_count = order.getAuntWCount();
				if (aunt_m_count == null) {
					aunt_m_count = 0;
				}
				if (aunt_w_count == null) {
					aunt_w_count = 0;
				}
				if (order.getOtherPirce() != null) {
					info.setOther_pirce(order.getOtherPirce().doubleValue());
				} else {
					info.setOther_pirce(0.00d);
				}
				info.setPeoplecount(aunt_m_count + aunt_w_count);
				if (order.getTipPrice() != null) {
					info.setTip_price(order.getTipPrice().doubleValue());
				} else {
					info.setTip_price(0.00d);
				}
				info.setUnit_price(order.getExpectedPrice().doubleValue());
				if (order.getServerEndTime() != null && order.getServerStartTime() != null) {
					long timeMill = order.getServerEndTime().getTime() - order.getServerStartTime().getTime();
					double hour = Math.ceil(timeMill / 1000d / 60d / 60d);

					info.setServertime((int) hour);

				} else {
					if (order.getExpectTime() != null) {
						info.setServertime((int) order.getExpectTime().floatValue());
					} else {
						info.setServertime(0);
					}

				}
				BigDecimal pay = new BigDecimal("0");

				if (cid == 5) {
					pay = pay.add(order.getExpectedPrice()).multiply(new BigDecimal(aunt_m_count + aunt_w_count + ""))
							.multiply(new BigDecimal(order.getDayTime()))
							.multiply(new BigDecimal(order.getExpectTime()));
					
					
					
					
				} else if (cid == 6) {
					pay = pay.add(order.getExpectedPrice()).multiply(new BigDecimal(aunt_m_count + aunt_w_count + ""))
							.multiply(new BigDecimal(order.getExpectTime()));
				}

				if (order.getOtherPirce() != null) {
					pay = pay.add(order.getOtherPirce());
				}

				pay = pay.subtract(order.getDepositPrice());

				info.setPrice(pay.doubleValue());

			} else {
				// 维修工 维修工
				BigDecimal pay = getPayPrice(order, null, null); // 订单最后支付的增值服务费
				if (order.getOtherPirce() != null) {
					info.setOther_pirce(order.getOtherPirce().doubleValue());
				} else {
					info.setOther_pirce(0.00d);
				}

				info.setPeoplecount(1);

				if (order.getTipPrice() != null) {
					info.setTip_price(order.getTipPrice().doubleValue());
				} else {
					info.setTip_price(0.00d);
				}
				info.setUnit_price(0.00d);
				if (order.getServerEndTime() != null && order.getServerStartTime() != null) {
					long timeMill = order.getServerEndTime().getTime() - order.getServerStartTime().getTime();
					double hour = Math.ceil(timeMill / 1000d / 60d / 60d);

					info.setServertime((int) hour);

				} else {
					if (order.getExpectTime() != null) {
						info.setServertime((int) order.getExpectTime().floatValue());
					} else {
						info.setServertime(0);
					}

				}
				info.setPrice(pay.doubleValue());

			}
		} else if (order.getState() == (short) 11) {// 维修类支付第一次的一口价

			BigDecimal decimal = order.getLastPrice(); // 支付第一次服务费
			maintenanceFee.setPrice(decimal.setScale(2, BigDecimal.ROUND_UP).doubleValue());
			CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
			maintenanceFee.setCategoryName(second.getName());

		} else if (order.getState() == (short) 14) {// 支付退款的差价

		} else {
			result.setCode("3");
			result.setMessage("支付失败,订单状态错误");
			return result;
		}

		HashMap<String, Object> map = new HashMap<>();
		map.put("payInfo", info);
		map.put("maintenanceFee", maintenanceFee);
		result.setResult(map);
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult pay(User user, UserExtra extra, String orderid, String couponid, String pay_type) {
		ApiResult result = new ApiResult();
		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		HashMap<String, Object> map = new HashMap<>();
		if (order == null) {
			result.setCode("2");
			result.setMessage("订单不存在");
			return result;
		}

		OrderPay orderPay = new OrderPay();
		Long nowcountPay = orderPayMapper.selectOrderNumCount();
		String orderNum = createOrderNum(3, nowcountPay);
		PayValue value = new PayValue();
		if (order.getState() == 5) {
			orderPay.setOrdernum(orderNum);
			orderPay.setOrderid(order.getOrderid());
			orderPay.setType((short) 2);// TODO: 根据支付类型设置type
			orderPay.setState((short) 0);
			orderPay.setPayType(CommonUtils.parseShort(pay_type, (short) 0));
            if(CommonUtils.isEmptyString(couponid)){
            	orderPay.setCouponid(0);
            	orderPay.setCouponPirce(new BigDecimal("0.00"));
            	order.setCouponid(""+0);
            	order.setCouponPirce(new BigDecimal(""+0));
            	orderMapper.updateByPrimaryKey(order);
            }else {
            	Coupon coupon = couponMapper.selectByPrimaryKey((CommonUtils.parseInt(couponid, 0)));
            	if(null != coupon){
            		orderPay.setCouponid(CommonUtils.parseInt(couponid, 0));
                	orderPay.setCouponPirce(coupon.getDiscount());
                	order.setCouponid(couponid);
                	order.setCouponPirce(coupon.getDiscount());
                	orderMapper.updateByPrimaryKey(order);
                	setCouponUesed(coupon,user.getUserid());
            	}else {
            		orderPay.setCouponid(0);
                	orderPay.setCouponPirce(new BigDecimal("0.00"));
                	order.setCouponid(""+0);
                	order.setCouponPirce(new BigDecimal(""+0));
                	orderMapper.updateByPrimaryKey(order);
            	}
            }
			orderPay.setPirce(getPayPrice(order, CommonUtils.parseInt(couponid, 0), null));
			orderPay.setAddtime(new Date());
			orderPayMapper.insert(orderPay);
		} else if (order.getState() == 11) {

			orderPay.setOrdernum(orderNum);
			orderPay.setOrderid(order.getOrderid());
			orderPay.setType((short) 4);// TODO: 根据支付类型设置type
			orderPay.setState((short) 0);
			orderPay.setPayType(CommonUtils.parseShort(pay_type, (short) 0));

			if(CommonUtils.isEmptyString(couponid)){
            	orderPay.setCouponid(0);
            	orderPay.setCouponPirce(new BigDecimal("0.00"));
            	orderPay.setPirce(order.getLastPrice());
            }else {
            	orderPay.setCouponid(CommonUtils.parseInt(couponid, 0));
            	Coupon coupon = couponMapper.selectByPrimaryKey((CommonUtils.parseInt(couponid, 0)));
            	if(coupon != null){
            		orderPay.setCouponPirce(coupon.getDiscount());
                	BigDecimal decimal = order.getLastPrice();
        			if(coupon.getDiscount() != null){
        				decimal = decimal.subtract(coupon.getDiscount());
        				order.setCouponid(couponid);
                    	order.setCouponPirce(coupon.getDiscount());
                    	orderPay.setPirce(order.getLastPrice().subtract(coupon.getDiscount()));
                    	orderMapper.updateByPrimaryKey(order);
        			}
        			setCouponUesed(coupon,user.getUserid());
            	}else {
            		orderPay.setCouponid(0);
                	orderPay.setCouponPirce(new BigDecimal("0.00"));
                	orderPay.setPirce(order.getLastPrice());
                	order.setCouponid(""+0);
                	order.setCouponPirce(new BigDecimal(""+0));
                	orderMapper.updateByPrimaryKey(order);
            	}
            	
            }
			orderPay.setAddtime(new Date());
			orderPayMapper.insert(orderPay);

		}
		if ("3".equals(pay_type)) { // 余额支付
			UserExtra userExtra = userExtraMapper.selectByUserId(user.getUserid());
			if (userExtra == null) {
				result.setCode("4");
				result.setMessage("用户信息错误");
				return result;
			}
			BigDecimal balance = userExtra.getBalance();

			if (balance.compareTo(orderPay.getPirce()) == -1) {
				result.setCode("5");
				result.setMessage("用户余额不足");
				return result;
			}
			userExtra.setBalance(balance.subtract(orderPay.getPirce()));
			userExtraMapper.updateByPrimaryKey(userExtra);

			// 增余额变动记录
			BalanceRecord record = new BalanceRecord();
			record.setAddtime(new Date());
			record.setCount(orderPay.getPirce());
			record.setType((short) 2);
			record.setUserid(user.getUserid());
			balanceRecordMapper.insert(record);

			orderPay.setState((short) 1);
			orderPayMapper.updateByPrimaryKey(orderPay);

			int cid = order.getCategoryid();
			if (cid == 1 || cid == 2 || cid == 5 || cid == 6 || cid == 8) {// 普通类
				singlePayment(order, orderPay);
				final Order orderQuote = order;
				fixedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
                        sendPayMessageToAunt(orderQuote);
					}
				});
				
			} else if (cid == 3 || cid == 4 || cid == 7 || cid == 9 || cid == 10 || cid == 11 || cid == 12
					|| cid == 13) {// 上门费用类
				// multiplePayments(order);
				if (order.getState() == 5) {
					order.setState((short) 12);
					orderMapper.updateByPrimaryKey(order);
					List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
					if (orderAuntList != null && orderAuntList.size() > 0) {
						for (OrderAunt orderAunt : orderAuntList) {
							orderAunt.setState((short) 12);
							orderAuntMapper.updateByPrimaryKey(orderAunt);
						}
					}
					final Order orderQuote = order;
					fixedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
	                        sendPayMessageToAunt(orderQuote);
						}
					});
					
				} else if (order.getState() == 11) {
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

			} else if (cid == 14 || cid == 15 || cid == 16 || cid == 17 || cid == 18) {// 长期
				singlePayment(order, orderPay);
			}

			value.setPay_type(3);

		} else if ("1".equals(pay_type)) { // 支付宝支付
			String private_key = AlipayConfig.private_key;
			String out_trade_no = orderPay.getOrdernum();
			Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(orderPay.getPirce().doubleValue() + "",
					"订单支付", "订单支付", out_trade_no);
			String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
			String sign = OrderInfoUtil2_0.getSign(params, private_key);
			final String body = orderParam + "&" + sign;
			value.setBody(body);
		} else { // 微信支付
			// getAccessToken();

		}

		map.put("pay", value);
		map.put("pay_money", orderPay.getPirce());
		map.put("thirdNumID", orderPay.getOrdernum());
		result.setResult(map);
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	public ApiResult payOrderMonth(String userid, String orderid) {
		ApiResult result = new ApiResult();
		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (order == null) {
			result.setCode("2");
			result.setMessage("订单不存在");
			return result;
		}
		PayInfo info = new PayInfo();
		info.setPrice(order.getExpectedPrice().setScale(2, BigDecimal.ROUND_UP).doubleValue());
		info.setUnit_price(order.getExpectedPrice().setScale(2, BigDecimal.ROUND_UP).doubleValue());
		info.setPeoplecount(1);
		short orderType = CommonUtils.parseShort(order.getOrderType().toString(), (short) 1);
		info.setOrder_type((int) orderType);
		// info.setPayedMonth(month);
		if (order.getOtherPirce() != null) {
			info.setOther_pirce(order.getOtherPirce().setScale(2, BigDecimal.ROUND_UP).doubleValue());
		} else {
			info.setOther_pirce(0d);
		}
		if (order.getDepositPrice() != null) {
			info.setDeposit_price(order.getDepositPrice().setScale(2, BigDecimal.ROUND_UP).doubleValue());
		} else if (order.getOrderType() == 1) {
			info.setDeposit_price(0d);
		}
		if (order.getTipPrice() != null) {
			info.setTip_price(order.getTipPrice().setScale(2, BigDecimal.ROUND_UP).doubleValue());
		} else {
			info.setTip_price(0d);
		}
		if (order.getState() == 4) {
			info.setSinglepay(1);
		} else if (order.getState() == 5) {
			info.setSinglepay(0);
		}
		if (order.getOrderType() == 0) {
			int expectMonth = order.getExpectTime().intValue();
			int month = order.getMonth().intValue();
			info.setMonth(expectMonth - month);
		} else {
			info.setMonth(0);
			if (order.getServerEndTime() != null && order.getServerStartTime() != null) {
				long timeMill = order.getServerEndTime().getTime() - order.getServerStartTime().getTime();
				double hour = Math.ceil(timeMill / 1000d / 60d / 60d);

				info.setServertime((int) hour);

			} else {
				if (order.getExpectTime() != null) {
					info.setServertime((int) order.getExpectTime().floatValue());
				} else {
					info.setServertime(0);
				}

			}

		}

		HashMap<String, Object> map = new HashMap<>();
		map.put("payInfo", info);
		result.setResult(map);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult payMonth(String userid, String orderid, String couponid, String pay_type, String month) {
		ApiResult result = new ApiResult();
		HashMap<String, Object> map = new HashMap<>();
		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (order == null) {
			result.setCode("2");
			result.setMessage("订单不存在");
			return result;
		}

		User user = userMapper.selectByPrimaryKey(CommonUtils.parseInt(userid, 0));
		if (user == null) {
			result.setCode("4");
			result.setMessage("用户信息错误");
			return result;
		}

		if (order.getOrderType() == 0) {
			if (CommonUtils.isEmptyString(month)) {
				result.setCode("101");
				result.setMessage("正式工月份不能为空");
				return result;
			}
		} else {
			month = "0";
		}

		OrderPay orderPay = new OrderPay();
		Long nowcountPay = orderPayMapper.selectOrderNumCount();
		String orderNum = createOrderNum(3, nowcountPay);
		orderPay.setOrdernum(orderNum);
		orderPay.setOrderid(order.getOrderid());
		orderPay.setType((short) 2);
		orderPay.setState((short) 0);
		orderPay.setPayType(CommonUtils.parseShort(pay_type, (short) 0));
		if(!CommonUtils.isEmptyString(couponid)){
			orderPay.setCouponid(0);
			orderPay.setCouponPirce(new BigDecimal("0.00"));
		}else {
			Coupon coupon = couponMapper.selectByPrimaryKey((CommonUtils.parseInt(couponid, 0)));
			if(coupon != null){
				orderPay.setCouponid(CommonUtils.parseInt(couponid, 0));
	            orderPay.setCouponPirce(coupon.getDiscount());
	            order.setCouponid(couponid);
            	order.setCouponPirce(coupon.getDiscount());
            	orderMapper.updateByPrimaryKey(order);
            	setCouponUesed(coupon,user.getUserid());
			}else {
				orderPay.setCouponid(0);
				orderPay.setCouponPirce(new BigDecimal("0.00"));
				order.setCouponid(""+0);
            	order.setCouponPirce(new BigDecimal(""+0));
            	orderMapper.updateByPrimaryKey(order);
			}
			
		}
		orderPay.setMonth(new Integer(month));

		orderPay.setPirce(getPayPrice(order, CommonUtils.parseInt(couponid, 0), CommonUtils.parseInt(month, 0)));
		orderPay.setAddtime(new Date());
		orderPayMapper.insert(orderPay);
		PayValue value = new PayValue();

		if ("3".equals(pay_type)) { // 余额支付
			UserExtra userExtra = userExtraMapper.selectByUserId(user.getUserid());
			if (userExtra == null) {
				result.setCode("4");
				result.setMessage("用户信息错误");
				return result;
			}
			BigDecimal balance = userExtra.getBalance();

			if (balance.compareTo(orderPay.getPirce()) == -1) {
				result.setCode("5");
				result.setMessage("用户余额不足");
				return result;
			}

			if (order.getOrderType() == 0) {

				// 正式单
				userExtra.setBalance(balance.subtract(orderPay.getPirce()));
				userExtraMapper.updateByPrimaryKey(userExtra);
				if (order.getState() == 4) {
					int payedMonth = order.getMonth().intValue();
					payedMonth += orderPay.getMonth();
					order.setMonth(new Float("" + payedMonth));
					orderMapper.updateByPrimaryKey(order);

					List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
					if (orderAuntList != null && orderAuntList.size() > 0) {
						for (OrderAunt orderAunt : orderAuntList) {
							orderAunt.setMonth(new Float("" + payedMonth));
							orderAuntMapper.updateByPrimaryKey(orderAunt);
						}
					}
				} else if (order.getState() == 5) {
					int payedMonth = order.getMonth().intValue();
					payedMonth += orderPay.getMonth();
					order.setState((short) 6);
					order.setPayTime1(new Date());
					orderMapper.updateByPrimaryKey(order);
					order.setMonth(new Float("" + payedMonth));

					List<OrderAunt> orderAuntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
					if (orderAuntList != null && orderAuntList.size() > 0) {
						for (OrderAunt orderAunt : orderAuntList) {
							orderAunt.setMonth(new Float("" + month));
							orderAunt.setState((short) 6);
							orderAunt.setPayTime1(new Date());
							orderAuntMapper.updateByPrimaryKey(orderAunt);
						}
					}
					final Order orderQuote = order;
					fixedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
	                        sendPayMessageToAunt(orderQuote);
						}
					});
					
					
					settlementBalance(order);
				}
			} else if (order.getOrderType() == 1) {
				// 试单
				singlePayment(order, orderPay);
				final Order orderQuote = order;
				fixedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
                        sendPayMessageToAunt(orderQuote);
					}
				});

			}

			value.setPay_type(3);

		} else if ("1".equals(pay_type)) { // 支付宝支付
			String private_key = AlipayConfig.private_key;
			String out_trade_no = orderPay.getOrdernum();
			Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(orderPay.getPirce() + "", "订单支付", "订单支付",
					out_trade_no);
			String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
			String sign = OrderInfoUtil2_0.getSign(params, private_key);
			final String body = orderParam + "&" + sign;
			value.setBody(body);
		} else { // 微信支付
			// getAccessToken();

		}
		map.put("pay", value);
		map.put("thirdNumID", orderPay.getOrdernum());
		map.put("pay_money", orderPay.getPirce());
		result.setResult(map);
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult complaintOrder(String userid, String orderid, String content) {
		ApiResult result = new ApiResult();

		content = CommonUtils.removeAllEmojis(content);

		List<OrderAunt> orderList = orderAuntMapper.selectByUserOrderId(CommonUtils.parseInt(orderid, 0));
		if (orderList == null || orderList.size() == 0) {
			result.setCode("2");
			result.setMessage("阿姨订单不存在");
			return result;
		}
		Order userOrder = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (userOrder == null) {
			result.setCode("3");
			result.setMessage("订单不存在");
			return result;
		}
		// 新增投诉记录
		OrderComplaint complaint = new OrderComplaint();
		complaint.setOrderid(CommonUtils.parseInt(orderid, 0));
		complaint.setUserid(CommonUtils.parseInt(userid, 0));
		complaint.setUserType(userOrder.getPosterType());
		complaint.setContent(content);
		complaint.setDataType((short) 0);
		complaint.setAddtime(new Date());
		orderComplaintMapper.insert(complaint);

		for (OrderAunt order : orderList) {
			order.setComplaintState((short) 1);// 设置阿姨订单已投诉
			orderAuntMapper.updateByPrimaryKey(order);
		}

		userOrder.setComplaintState((short) 1);// 设置用户订单已投诉
		orderMapper.updateByPrimaryKey(userOrder);
		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult commentOrder(String userid, String orderid, String content, String reason, String score1,
			String score2, String score3) {
		ApiResult result = new ApiResult();

		content = CommonUtils.removeAllEmojis(content);

		List<OrderAunt> orderList = orderAuntMapper.selectByUserOrderId(CommonUtils.parseInt(orderid, 0));
		if (orderList == null || orderList.size() == 0) {
			result.setCode("2");
			result.setMessage("阿姨订单不存在");
			return result;
		}
		Order userOrder = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (userOrder == null) {
			result.setCode("3");
			result.setMessage("订单不存在");
			return result;
		}
		
		userOrder.setCommentState((short) 1);// 设置用户订单已评价
		orderMapper.updateByPrimaryKey(userOrder);
		
		for (OrderAunt order : orderList) {
			// 插入评论数据
			OrderComment comment = new OrderComment();
			comment.setOrderid(CommonUtils.parseInt(orderid, 0));
			comment.setUserid(CommonUtils.parseInt(userid, 0));
			comment.setAuntid(order.getAuntid());
			comment.setLabel(reason);
			comment.setUser_type((byte)0);
			comment.setContent(content);
			comment.setScore1((short) CommonUtils.parseInt(score1, 0));
			comment.setScore2((short) CommonUtils.parseInt(score2, 0));
			comment.setScore3((short) CommonUtils.parseInt(score3, 0));
			Integer count = CommonUtils.parseInt(score1, 0) + CommonUtils.parseInt(score1, 0)
					+ CommonUtils.parseInt(score1, 0);
			if (count < 7) {
				comment.setCommentType((short) 2);
			} else if (count > 11) {
				comment.setCommentType((short) 0);
			} else {
				comment.setCommentType((short) 1);
			}
			comment.setAddtime(new Date());
			orderCommentMapper.insert(comment);
			
			if(order.getAuntid() != null && order.getAuntid()>0){
				// 计算阿姨的平均分
				AuntExtra extra = auntExtramapper.selectByAuntId(order.getAuntid());
				Aunt aunt = auntMapper.selectByPrimaryKey(order.getAuntid());
				
				order.setCommentState((short) 1);// 设置阿姨订单已评价
				orderAuntMapper.updateByPrimaryKey(order);

				if (extra != null && aunt != null) {
					Long total = orderCommentMapper.selectAuntScoreTotal(extra.getAuntid());
					Long size = orderCommentMapper.selectAuntScoreCount(extra.getAuntid());
					if (total != null && size != null) {
						extra.setScore((float) total / (size * 3));
					}
					auntExtramapper.updateByPrimaryKey(extra);
					if (aunt.getCompanyid() != null && aunt.getCompanyid() > 0) {
						CompanyExtra companyExtra = companyExtraMapper.selectByCompanyId(aunt.getCompanyid());
						if (companyExtra != null) {
							Long totalc = orderCommentMapper.selectCompanyScoreTotal(companyExtra.getCompanyid());
							Long sizec = orderCommentMapper.selectCompanyScoreCount(companyExtra.getCompanyid());
							if (totalc != null && sizec != null) {
								companyExtra.setScore((float) totalc / (sizec * 3));
							}
							companyExtraMapper.updateByPrimaryKey(companyExtra);
						}
					}
				}
				
				if(comment.getCommentType() == 0){
					Long auntAccentOrder = orderAuntMapper.selectAuntCompleteOrder(aunt.getAuntid());
					if(auntAccentOrder == null){
						
					}else{
					   if(auntAccentOrder == 1){
						    final RedPacketRecord record = createRedPacketRecord(5,userOrder,1,aunt.getAuntid());
							HashMap<String, Object> map = new HashMap<>();
							map.put("packetid", record.getDataid());
							map.put("count", record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
							
							fixedThreadPool.execute(new Runnable() {
								
								@Override
								public void run() {
									sendRedPacketMessageToAunt(record);
								}
							});
							
							/*result.setResult(map);
							result.setCode("1");
							result.setMessage("请求成功");
							return result;*/
					   }else {
						   final RedPacketRecord record = createRedPacketRecord(4,userOrder,1,aunt.getAuntid());
							HashMap<String, Object> map = new HashMap<>();
							map.put("packetid", record.getDataid());
							map.put("count", record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
							fixedThreadPool.execute(new Runnable() {
								
								@Override
								public void run() {
									sendRedPacketMessageToAunt(record);
								}
							});
							
							/*result.setResult(map);
							result.setCode("1");
							result.setMessage("请求成功");
							return result;*/
					   }
					
					}
					
				
				
				

			}else if(order.getCompanyid() !=null && order.getCompanyid() > 0){
				Long companyAccentOrder = orderAuntMapper.selectAuntCompleteOrder(order.getCompanyid());
				if(companyAccentOrder == null){
					
				}else {
					if(companyAccentOrder == 1){
						final RedPacketRecord record = createRedPacketRecord(5,userOrder,2,order.getCompanyid());
						HashMap<String, Object> map = new HashMap<>();
						map.put("packetid", record.getDataid());
						map.put("count", record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
						fixedThreadPool.execute(new Runnable() {
							
							@Override
							public void run() {
								sendRedPacketMessageToAunt(record);
							}
						});
						/*result.setResult(map);
						result.setCode("1");
						result.setMessage("请求成功");
						return result;*/
					}else {
						final RedPacketRecord record = createRedPacketRecord(4,userOrder,2,order.getCompanyid());
						HashMap<String, Object> map = new HashMap<>();
						map.put("packetid", record.getDataid());
						map.put("count", record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
						fixedThreadPool.execute(new Runnable() {
							
							@Override
							public void run() {
								sendRedPacketMessageToAunt(record);
							}
						});
						/*result.setResult(map);
						result.setCode("1");
						result.setMessage("请求成功");
						return result;*/
					}
				}
			}
			
			}
		}

		
		
		Long orderCount = orderMapper.selectCompleteOrderList(CommonUtils.parseInt(userid, 0),0);
		if(orderCount == 0){
			
		}else {
			if(orderCount == 1){
				//首单 
				final RedPacketRecord record = createRedPacketRecord(3,userOrder,0,CommonUtils.parseInt(userid, 0));
				HashMap<String, Object> map = new HashMap<>();
				map.put("packetid", record.getDataid());
				map.put("count", record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
				result.setResult(map);
				result.setCode("1");
				result.setMessage("请求成功");
				return result;
				
			}else {
				final RedPacketRecord record = createRedPacketRecord(1,userOrder,0,CommonUtils.parseInt(userid, 0));
				if(record == null){
					
				}else {
					HashMap<String, Object> map = new HashMap<>();
					map.put("packetid", record.getDataid());
					map.put("count", record.getCount().setScale(2, BigDecimal.ROUND_UP).floatValue());
					result.setResult(map);
					result.setCode("1");
					result.setMessage("请求成功");
					return result;
				}
				
			}
			
		}
		
		

		result.setCode("1");
		result.setMessage("请求成功");
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public ApiResult payDeposit(User user, String orderid, String pay_type) {
		ApiResult result = new ApiResult();

		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (order == null) {
			result.setCode("2");
			result.setMessage("支付失败,订单不存在");
			return result;
		}

		if (order.getState() != 0) {
			result.setCode("3");
			result.setMessage("支付失败,订单状态错误");
			return result;
		}

		int payTypeInt = CommonUtils.parseInt(pay_type, 0);
		HashMap<String, Object> map = new HashMap<>();
		PayValue value = new PayValue();

		OrderPay orderPay = new OrderPay();
		Long nowcountPay = orderPayMapper.selectOrderNumCount();
		String orderNum = createOrderNum(3, nowcountPay);
		orderPay.setOrdernum(orderNum);
		orderPay.setOrderid(order.getOrderid());
		orderPay.setType((short) 1);
		orderPay.setState((short) 0);
		orderPay.setPayType(CommonUtils.parseShort(pay_type, (short) 0));
		orderPay.setCouponid(0);
		orderPay.setCouponPirce(new BigDecimal(0));
		orderPay.setPirce(order.getDepositPrice());
		orderPay.setAddtime(new Date());

		if (payTypeInt == 1)// 支付宝
		{
			String private_key = AlipayConfig.private_key;
			String out_trade_no = orderPay.getOrdernum();
			Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(order.getDepositPrice().toString(),
					"订单定金支付", "订单定金支付", out_trade_no);
			String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
			String sign = OrderInfoUtil2_0.getSign(params, private_key);
			final String body = orderParam + "&" + sign;
			value.setBody(body);

		} else if (payTypeInt == 2 || payTypeInt == 4) {// 微信
			// getAccessToken();
		} else if (payTypeInt == 3) {// 余额
			UserExtra extra = userExtraMapper.selectByUserId(user.getUserid());
			if (extra != null) {
				if (extra.getBalance() == null
						|| extra.getBalance().doubleValue() < order.getDepositPrice().doubleValue()) {
					result.setCode("6");
					result.setMessage("支付失败,用户余额不足");
					return result;
				}

				// 减去用户的余额
				extra.setBalance(extra.getBalance().subtract(order.getDepositPrice()));
				userExtraMapper.updateByPrimaryKey(extra);

				// 生成用户的余额使用记录
				BalanceRecord record = new BalanceRecord();
				record.setAddtime(new Date());
				record.setCount(order.getDepositPrice());
				record.setType((short) 1);
				record.setUserid(user.getUserid());
				balanceRecordMapper.insert(record);

				// 更新订单状态
				order.setState((short) 1);
				orderMapper.updateByPrimaryKey(order);

				// 生成抢单信息或者生成阿姨订单信息
				// TODO
				orderPay.setState((short) 1);
				List<OrderPool> poolList = orderPoolMapper.selectListByOrderUser(order.getOrderid(), 0);
				if (poolList != null && poolList.size() > 0) {
					for (OrderPool orderpool : poolList) {
						orderpool.setState((byte) 1);
						orderPoolMapper.updateByPrimaryKey(orderpool);
					}
				}
			} else {
				result.setCode("4");
				result.setMessage("支付失败,用户信息异常");
				return result;
			}

		} else {
			result.setCode("5");
			result.setMessage("支付失败,支付方式错误");
			return result;
		}

		orderPayMapper.insert(orderPay);

		map.put("pay_money", orderPay.getPirce().floatValue());
		map.put("pay_type", orderPay.getPayType());
		map.put("thirdNumID", orderNum);
		map.put("pay", value);
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);
		return result;
	}

	@Override
	public ApiResult addTip(User user, String orderid, String count, String pay_type, String openid) {
		ApiResult result = new ApiResult();

		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (order == null) {
			result.setCode("2");
			result.setMessage("打赏失败,订单不存在");
			return result;
		}

		if (order.getState() != 1) {
			result.setCode("3");
			result.setMessage("打赏失败,订单状态错误");
			return result;
		}

		BigDecimal money = new BigDecimal(count);
		int payTypeInt = CommonUtils.parseInt(pay_type, 0);
		HashMap<String, Object> map = new HashMap<>();
		PayValue value = new PayValue();

		OrderPay orderPay = new OrderPay();
		Long nowcountPay = orderPayMapper.selectOrderNumCount();
		String orderNum = createOrderNum(3, nowcountPay);
		orderPay.setOrdernum(orderNum);
		orderPay.setOrderid(order.getOrderid());
		orderPay.setType((short) 3);
		orderPay.setState((short) 0);
		orderPay.setPayType(CommonUtils.parseShort(pay_type, (short) 0));
		orderPay.setCouponid(0);
		orderPay.setCouponPirce(new BigDecimal("0.00"));
		orderPay.setPirce(money);
		orderPay.setAddtime(new Date());

		if (payTypeInt == 1)// 支付宝
		{
			String private_key = AlipayConfig.private_key;
			String out_trade_no = orderPay.getOrdernum();
			Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(money.toString(), "打赏小费", "打赏小费",
					out_trade_no);
			String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
			String sign = OrderInfoUtil2_0.getSign(params, private_key);
			final String body = orderParam + "&" + sign;
			value.setBody(body);

		} else if (payTypeInt == 2) {// 微信
		} else if (payTypeInt == 3) {// 余额
			UserExtra extra = userExtraMapper.selectByUserId(user.getUserid());
			if (extra != null) {
				if (extra.getBalance() == null || extra.getBalance().doubleValue() < money.doubleValue()) {
					result.setCode("6");
					result.setMessage("打赏失败,用户余额不足");
					return result;
				}

				// 减去用户的余额
				extra.setBalance(extra.getBalance().subtract(money));
				userExtraMapper.updateByPrimaryKey(extra);

				// 生成用户的余额使用记录
				BalanceRecord record = new BalanceRecord();
				record.setAddtime(new Date());
				record.setCount(money);
				record.setType((short) 4);
				record.setUserid(user.getUserid());
				balanceRecordMapper.insert(record);

				// 修改订单的相关数据
				if (order.getTipPrice() != null) {
					order.setTipPrice(order.getTipPrice().add(money));
				} else {
					order.setTipPrice(money);
				}
				orderMapper.updateByPrimaryKey(order);

				orderPay.setState((short) 1);

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

			} else {
				result.setCode("4");
				result.setMessage("打赏失败,用户信息异常");
				return result;
			}

		} else if (payTypeInt == 4) {// 微信JS
		} else {
			result.setCode("5");
			result.setMessage("打赏失败,支付方式错误");
			return result;
		}

		orderPayMapper.insert(orderPay);

		map.put("pay_money", orderPay.getPirce().floatValue());
		map.put("pay_type", orderPay.getPayType());
		map.put("pay", value);
		map.put("thirdNumID", orderPay.getOrdernum());
		result.setCode("1");
		result.setMessage("请求成功");
		result.setResult(map);

		return result;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	private void setRobOrderPool(Order order) {

		List<AuntValue> auntValues = getCanBookAuntList(order.getLongitude().toString(), order.getLatitude().toString(),
				order.getCategoryid().toString(), null);
		List<CompanyValue> companyValues = getCanBookCompanyList(order.getLongitude().toString(),
				order.getLatitude().toString(), order.getCategoryid().toString(), null);
		CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
		if (auntValues != null && auntValues.size() > 0) {
			for (AuntValue value : auntValues) {
				OrderPool orderPool = new OrderPool();
				orderPool.setOrderid_user(order.getOrderid());
				orderPool.setAuntid(value.getAuntid());
				orderPool.setUser_type(0);
				if (order.getServerTime() != null) {
					orderPool.setTitle(second.getName());
				}
				orderPool.setState((byte) 0);
				orderPool.setAddress(order.getAddressname() + order.getAddressdetail());
				if (order.getTipPrice() != null && order.getTipPrice().compareTo(new BigDecimal("0")) == 1) {
					orderPool.setTipState((byte) 1);
					orderPool.setPriceTip(order.getTipPrice());
				} else {
					orderPool.setTipState((byte) 0);
				}
				orderPool.setLatitude(order.getLatitude());
				orderPool.setLongitude(order.getLongitude());
				orderPool.setTotime("上门时间:" + CommonUtils.getTimeFormat(order.getServerTime(), "yyyy-MM-dd HH:mm"));
				orderPool.setPrice(order.getExpectedPrice());
				short accept = Short.valueOf(order.getAcceptType());
				orderPool.setOrderType((byte) accept);
				orderPool.setAddtime(new Date());
				orderPool.setCategoryid(order.getCategoryid());
				orderPool.setAunt_m_count(0);
				orderPool.setAunt_w_count(0);
				orderPoolMapper.insert(orderPool);
			}
		}

		if (companyValues != null && companyValues.size() > 0) {
			for (CompanyValue value : companyValues) {
				OrderPool orderPool = new OrderPool();
				orderPool.setOrderid_user(order.getOrderid());
				orderPool.setAuntid(value.getCompanyid());
				orderPool.setUser_type(1);
				if (order.getServerTime() != null) {
					orderPool.setTitle(second.getName());
				}
				orderPool.setState((byte) 0);
				orderPool.setAddress(order.getAddressname() + order.getAddressdetail());
				if (order.getTipPrice() != null && order.getTipPrice().compareTo(new BigDecimal("0")) == 1) {
					orderPool.setTipState((byte) 1);
				} else {
					orderPool.setTipState((byte) 0);
				}
				orderPool.setLatitude(order.getLatitude());
				orderPool.setLongitude(order.getLongitude());
				orderPool.setTotime("上门时间:" + CommonUtils.getTimeFormat(order.getServerTime(), "yyyy-MM-dd HH:mm"));
				orderPool.setPrice(order.getExpectedPrice());
				short accept = Short.valueOf(order.getAcceptType());
				orderPool.setOrderType((byte) accept);
				orderPool.setAddtime(new Date());
				orderPool.setCategoryid(order.getCategoryid());
				if (order.getAuntMCount() != null) {
					orderPool.setAunt_m_count(order.getAuntMCount());
				} else {
					orderPool.setAunt_m_count(0);
				}
				if (order.getAuntWCount() != null) {
					orderPool.setAunt_w_count(order.getAuntWCount());
				} else {
					orderPool.setAunt_w_count(0);
				}

				orderPoolMapper.insert(orderPool);
			}
		}

	}

	@Override
	public List<Order> selectOrderFailed() {
		List<Order> list = orderMapper.selectFailedOrderList();
		return list;
	}

	@Override
	public List<Order> selectOVerTimeOrderFailed() {
		List<Order> list = orderMapper.selectOverTimeOrderList();
		return list;
	}

	@Override
	public int updateOrder(Order record) {
		return orderMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteOrderPoolDate(int orderid) {
		return orderPoolMapper.deleteOrderPoolDate(orderid);
	}

	@Override
	public List<OrderAunt> selectAuntOrderByUserOrder(int orderid) {
		List<OrderAunt> List = orderAuntMapper.selectByUserOrderId(orderid);
		return List;
	}

	@Override
	public int updateAuntOrder(OrderAunt orderAunt) {
		return orderAuntMapper.updateByPrimaryKey(orderAunt);
	}

	@Override
	public void messageToUserOrderFailed(Order order) {
		User user = userMapper.selectByPrimaryKey(order.getUserid());
		if (user == null) {
			return;
		}
		UserPush userPush = userPushMapper.selectByUserId(user.getUserid());
		if (userPush == null || (userPush.getIsaccept() != null && userPush.getIsaccept() == 1)) {
			return;
		}

		PushData data = new PushData();
		CategorySecond second = categorySecondMapper.selectByPrimaryKey(order.getCategoryid());
		String name = second.getName();
		data.setT("3");
		data.setM("您的 " + name + " 订单由于错过上门时间未被接单已经取消");
		data.setD("orderid:" + order.getOrderid());
		if (order.getPosterType() == 0) {
			MessageDetail detail = new MessageDetail();
			detail.setSysMessageid(0);
			detail.setTitle("新消息");
			detail.setDetail("您的 " + name + " 订单由于错过上门时间未被接单已经取消");
			detail.setLinkTitle("新消息");
			detail.setAddtime(new Date());
			sendSystemMessageToUser(user.getUserid(), 1, detail, null);
			if (userPush.getDevicetype() == 1) {
				JPushUtils.pushToAndroidById(new Gson().toJson(data), userPush.getPushKey(),
						Constant.JIGUANG_PUSH_USER_SECRET, Constant.JIGUANG_PUSH_USER_KEY);
			} else {
				IosPushDataBase base = new IosPushDataBase();
				base.setAlert("您的 " + name + " 订单由于错过上门时间未被接单已经取消");
				base.setBadge(1);
				base.setSound("default");
				IosPushDateModelBase model = new IosPushDateModelBase();
				model.setAps(base);
				model.setT(3);
				model.setD("orderid:" + order.getOrderid());
				IOSPushUtils.sendPushToSingle(userPush.getPushKey(), new Gson().toJson(model), 0);
			}

		} else if (order.getPosterType() == 1) {
			Company company = companyMapper.selectByPrimaryKey(order.getUserid());
			if (company == null) {
				return;
			}
			AuntPush auntPush = auntPushMapper.selectByUserId(company.getCompanyid(), (short) 1);
			if (auntPush == null || (auntPush.getIsaccept() != null && auntPush.getIsaccept() == 1)) {
				return;
			}

			AuntMessageDetail detail = new AuntMessageDetail();
			detail.setSysMessageid(0);
			detail.setTitle("新消息");
			detail.setDetail("您的 " + name + " 订单由于错过上门时间未被接单已经取消");
			detail.setLinkTitle("新消息");
			detail.setAddtime(new Date());
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
				model.setT(3);
				model.setD("orderid:" + order.getOrderid());
				IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 0);
			}
		}

	}

	@Override
	public void messageToAuntOrderFailed(OrderAunt orderaunt) {
		if (orderaunt.getAuntid() == null || orderaunt.getAuntid() == 0) {
			Company company = companyMapper.selectByPrimaryKey(orderaunt.getCompanyid());
			if (company == null) {
				return;
			}
			AuntPush auntPush = auntPushMapper.selectByUserId(company.getCompanyid(), (short) 1);
			if (auntPush == null || (auntPush.getIsaccept() != null && auntPush.getIsaccept() == 1)) {
				return;
			}

			CategorySecond second = categorySecondMapper.selectByPrimaryKey(orderaunt.getCategoryid());
			String name = second.getName();
			PushData data = new PushData();
			data.setT("3");
			data.setM("您的 " + name + " 订单由于错过上门时间未被接单已经取消");
			data.setD("orderid:" + orderaunt.getOrderid());

			AuntMessageDetail detail = new AuntMessageDetail();
			detail.setSysMessageid(0);
			detail.setTitle("新消息");
			detail.setDetail("您的 " + name + " 订单由于错过上门时间未被接单已经取消");
			detail.setLinkTitle("新消息");
			detail.setAddtime(new Date());
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
				model.setT(3);
				model.setD("orderid:" + orderaunt.getOrderid());
				IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 0);
			}
		} else {
			Aunt aunt = auntMapper.selectByPrimaryKey(orderaunt.getAuntid());
			if (aunt == null) {
				return;
			}
			AuntPush auntPush = auntPushMapper.selectByUserId(aunt.getAuntid(), (short) 0);
			if (auntPush == null || (auntPush.getIsaccept() != null && auntPush.getIsaccept() == 1)) {
				return;
			}

			CategorySecond second = categorySecondMapper.selectByPrimaryKey(orderaunt.getCategoryid());
			String name = second.getName();
			PushData data = new PushData();
			data.setT("3");
			data.setM("您的 " + name + " 订单由于错过上门时间未被接单已经取消");
			data.setD("orderid:" + orderaunt.getOrderid());

			AuntMessageDetail detail = new AuntMessageDetail();
			detail.setSysMessageid(0);
			detail.setTitle("新消息");
			detail.setDetail("您的 " + name + " 订单由于错过上门时间未被接单已经取消");
			detail.setLinkTitle("新消息");
			detail.setAddtime(new Date());
			sendSystemMessageToUser(aunt.getAuntid(), 2, null, detail);
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
				model.setT(3);
				model.setD("orderid:" + orderaunt.getOrderid());
				IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 0);
			}
		}
	}

	// TODO: 优惠券 积分抵扣 计算最后支付费用
	public BigDecimal getPayPrice(Order order, Integer couponid, Integer month) {
		BigDecimal decimal = new BigDecimal("0.00");
		int categoryid = order.getCategoryid();
		// 小时工
		if (categoryid == 1 || categoryid == 2 || categoryid == 8) {
			
			double serverTime = 0;
			if (order.getServerEndTime() != null && order.getServerStartTime() != null) {
				long timeMill = order.getServerEndTime().getTime() - order.getServerStartTime().getTime();
				double hour = Math.ceil(timeMill / 1000d / 60d / 60d);
				serverTime = hour;
			} else {
				if (order.getExpectTime() != null) {
					serverTime = order.getExpectTime().floatValue();
				} else {
					serverTime = 0;
				}

			}
			
			if(serverTime < 2){
				serverTime = 2;
			}
			
			decimal = decimal.add(order.getExpectedPrice()).multiply(new BigDecimal(serverTime));
			if (order.getOtherPirce() != null) {
				decimal = decimal.add(order.getOtherPirce());
			}
			if (order.getDepositPrice() != null) {
				decimal = decimal.subtract(order.getDepositPrice());
			}
			
			if(couponid != null && couponid>0){
				Coupon coupon = couponMapper.selectByPrimaryKey(couponid);
				if(coupon.getDiscount() != null){
					decimal = decimal.subtract(coupon.getDiscount());
				}
				
			}
			if(decimal.compareTo(new BigDecimal(""+0)) == 1){
				
			}else {
				decimal = new BigDecimal("0.01");
			}
			
			
			// 长期工
		} else if (categoryid == 14 || categoryid == 15 || categoryid == 16 || categoryid == 17 || categoryid == 18) {
			if (order.getOrderType() == 0) {
				// 正式单
				// decimal = decimal.add(order.getLastPrice());

				if (month == 0) {

					if (order.getOtherPirce() != null) {
						decimal = decimal.add(order.getOtherPirce());
					}

				} else {
					int payedMonth = order.getMonth().intValue();
					int exceptMonth = order.getExpectTime().intValue();
					int lastMonth = exceptMonth - payedMonth;
					if (lastMonth == month) {
						// 支付的月份
						decimal = decimal.add(order.getExpectedPrice()).multiply(new BigDecimal("" + month));
						if (order.getOtherPirce() != null) {
							decimal = decimal.add(order.getOtherPirce());
						}
						if (order.getDepositPrice() != null) {
							decimal = decimal.subtract(order.getDepositPrice());
						}

					} else {
						decimal = decimal.add(order.getExpectedPrice()).multiply(new BigDecimal("" + month));

					}

				}

				if(couponid != null && couponid>0){
					Coupon coupon = couponMapper.selectByPrimaryKey(couponid);
					if(coupon.getDiscount() != null){
						decimal = decimal.subtract(coupon.getDiscount());
					}
				}
				
			} else if (order.getOrderType() == 1) {
				// 试单
				decimal = decimal.add(order.getExpectedPrice()).multiply(new BigDecimal(order.getExpectTime()));
				if (order.getOtherPirce() != null) {
					decimal = decimal.add(order.getOtherPirce());
				}
				if (order.getDepositPrice() != null) {
					decimal = decimal.subtract(order.getDepositPrice());
				}
			}
		} else if (categoryid == 5 || categoryid == 6) {
			Integer aunt_m_count = order.getAuntMCount();
			Integer aunt_w_count = order.getAuntWCount();
			if (aunt_m_count == null) {
				aunt_m_count = 0;
			}
			if (aunt_w_count == null) {
				aunt_w_count = 0;
			}
			if (categoryid == 5) {

				decimal = decimal.add(order.getExpectedPrice())
						.multiply(new BigDecimal("" + aunt_m_count + aunt_w_count))
						.multiply(new BigDecimal("" + order.getDayTime()))
						.multiply(new BigDecimal(order.getExpectTime()));
				if (order.getOtherPirce() != null) {
					decimal = decimal.add(order.getOtherPirce());
				}
				if (order.getDepositPrice() != null) {
					decimal = decimal.subtract(order.getDepositPrice());
				}
			} else {

				decimal = decimal.add(order.getExpectedPrice())
						.multiply(new BigDecimal("" + aunt_m_count + aunt_w_count))
						.multiply(new BigDecimal(order.getExpectTime()));

				if (order.getOtherPirce() != null) {
					decimal = decimal.add(order.getOtherPirce());
				}
				if (order.getDepositPrice() != null) {
					decimal = decimal.subtract(order.getDepositPrice());
				}
			}

			if(couponid != null && couponid>0){
				Coupon coupon = couponMapper.selectByPrimaryKey(couponid);
				if(coupon.getDiscount() != null){
					decimal = decimal.subtract(coupon.getDiscount());
				}
				
			}
			
		} else {

			decimal = order.getOtherPirce();
			/*if(couponid != null && couponid>0){
				Coupon coupon = couponMapper.selectByPrimaryKey(couponid);
				if(coupon.getDiscount() != null){
					decimal = decimal.subtract(coupon.getDiscount());
				}
				
			}*/
		}
		return decimal.setScale(2, BigDecimal.ROUND_UP);
	}

	@Override
	public List<Order> selectTwoHourAlermOrder(String stime, String etime) {
		List<Order> list = orderMapper.selectTwoHourAlermOrder(stime, etime);
		return list;
	}

	@Override
	public void sendAlarmMessageToUser(Order order) {

		PushData data = new PushData();
		data.setT("2");
		data.setM("尊敬的客户，还有2小时服务人员就要上门啦，请确保家中有人并且手机保持畅通");
		data.setD("orderid:" + order.getOrderid());
		if (order != null && order.getState() < 3) {
			if (order.getPosterType() == 0) {
				User user = userMapper.selectByPrimaryKey(order.getUserid());
				UserPush userPush = userPushMapper.selectByUserId(user.getUserid());
				MessageDetail detail = new MessageDetail();
				detail.setSysMessageid(0);
				detail.setTitle("新消息");
				detail.setDetail("尊敬的客户，还有2小时服务人员就要上门啦，请确保家中有人并且手机保持畅通");
				detail.setLinkTitle("新消息");
				detail.setLinkType((short) 1);
				detail.setLinkContent("" + order.getOrderid());
				detail.setAddtime(new Date());
				sendSystemMessageToUser(user.getUserid(), 1, detail, null);
				if (userPush.getDevicetype() == 1) {
					JPushUtils.pushToAndroidById(new Gson().toJson(data), userPush.getPushKey(),
							Constant.JIGUANG_PUSH_USER_SECRET, Constant.JIGUANG_PUSH_USER_KEY);
				} else {
					IosPushDataBase base = new IosPushDataBase();
					base.setAlert("尊敬的客户，还有2小时服务人员就要上门啦，请确保家中有人并且手机保持畅通");
					base.setBadge(1);
					base.setSound("default");
					IosPushDateModelBase model = new IosPushDateModelBase();
					model.setAps(base);
					model.setT(2);
					model.setD("orderid:" + order.getOrderid());
					IOSPushUtils.sendPushToSingle(userPush.getPushKey(), new Gson().toJson(model), 0);
				}

			} else if (order.getPosterType() == 1) {
				Company company = companyMapper.selectByPrimaryKey(order.getUserid());
				AuntPush auntPush = auntPushMapper.selectByUserId(company.getCompanyid(), (short) 1);
				AuntMessageDetail detail = new AuntMessageDetail();
				detail.setSysMessageid(0);
				detail.setTitle("新消息");
				detail.setDetail("尊敬的客户，还有2小时服务人员就要上门啦，请确保家中有人并且手机保持畅通");
				detail.setLinkTitle("新消息");
				detail.setLinkType((byte) 1);
				detail.setLinkContent("" + order.getOrderid());
				detail.setAddtime(new Date());
				sendSystemMessageToUser(company.getCompanyid(), 2, null, detail);
				if (auntPush.getDevicetype() == 1) {
					JPushUtils.pushToAndroidById(new Gson().toJson(data), auntPush.getPushKey(),
							Constant.JIGUANG_PUSH_USER_SECRET, Constant.JIGUANG_PUSH_USER_KEY);
				} else {
					IosPushDataBase base = new IosPushDataBase();
					base.setAlert("尊敬的客户，还有2小时服务人员就要上门啦，请确保家中有人并且手机保持畅通");
					base.setBadge(1);
					base.setSound("default");
					IosPushDateModelBase model = new IosPushDateModelBase();
					model.setAps(base);
					model.setT(2);
					model.setD("orderid:" + order.getOrderid());
					IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 1);
				}
			}
		}

	}

	@Override
	public void sendAlarmMessageToAunt(Order order) {

		List<OrderAunt> auntOrderList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		if (auntOrderList != null) {
			for (OrderAunt orderAunt : auntOrderList) {
				PushData data = new PushData();
				data.setT("2");
				data.setM("亲爱的服务人员，还有2小时候您就要上门服务啦，请再次联系好客户");
				data.setD("orderid:" + orderAunt.getOrderid());
				if (order != null && order.getState() < 3) {
					AuntPush auntPush = null;
					if (orderAunt.getAuntid() != null && orderAunt.getAuntid() > 0) {
						auntPush = auntPushMapper.selectByUserId(orderAunt.getAuntid(), (short) 0);
					} else {
						auntPush = auntPushMapper.selectByUserId(orderAunt.getAuntid(), (short) 1);
					}

					if (auntPush != null) {
						AuntMessageDetail detail = new AuntMessageDetail();
						detail.setSysMessageid(0);
						detail.setTitle("新消息");
						detail.setLinkTitle("亲爱的服务人员，还有2小时候您就要上门服务啦，请再次联系好客户");
						detail.setLinkType((byte) 1);
						detail.setLinkContent("" + orderAunt.getOrderid());
						detail.setDetail("亲爱的服务人员，还有2小时候您就要上门服务啦，请再次联系好客户");
						detail.setAddtime(new Date());
						sendSystemMessageToUser(auntPush.getUserid(), 2, null, detail);
						if (auntPush.getDevicetype() == 1) {

							JPushUtils.pushToAndroidById(new Gson().toJson(data), auntPush.getPushKey(),
									Constant.JIGUNAG_PUSH_AUNT_SECRET, Constant.JIGUAN_PUSH_AUNT_KEY);
						} else if (auntPush.getDevicetype() == 2) {
							// tokenList.add(auntPush.getPushKey());
							// TODO: 阿姨端推送证书修改
							IosPushDataBase base = new IosPushDataBase();
							base.setAlert("亲爱的服务人员，还有2小时候您就要上门服务啦，请再次联系好客户");
							base.setBadge(1);
							base.setSound("default");
							IosPushDateModelBase model = new IosPushDateModelBase();
							model.setAps(base);
							model.setT(2);
							model.setD("orderid:" + orderAunt.getOrderid());
							IOSPushUtils.sendPushToSingle(auntPush.getPushKey(), new Gson().toJson(model), 1);
						}
					}

				}
			}
		}
	}

	@Override
	public ApiResult calcelBook(User user, String orderid) {
		ApiResult result = new ApiResult();

		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (order == null) {
			result.setCode("2");
			result.setMessage("取消失败,订单不存在");
			return result;
		}

		if (order.getState() != 0) {
			result.setCode("3");
			result.setMessage("取消失败,订单状态错误");
			return result;
		}

		order.setState((short) 15);
		orderMapper.updateByPrimaryKey(order);
		
		//删除orderPool
		orderPoolMapper.deleteByUserOrderId(order.getOrderid());

		result.setCode("1");
		result.setMessage("取消预约成功");
		return result;
	}

	@Override
	public ApiResult comfirmWages(User user, String orderid) {
		ApiResult result = new ApiResult();

		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (order == null) {
			result.setCode("2");
			result.setMessage("请求失败,订单不存在");
			return result;
		}

		if (order.getState() != 11) {
			result.setCode("3");
			result.setMessage("请求失败,订单状态错误");
			return result;
		}

		if (order.getCategoryid() == null || (order.getCategoryid() != 14 && order.getCategoryid() != 15
				&& order.getCategoryid() != 16 && order.getCategoryid() != 17 && order.getCategoryid() != 18)) {
			result.setCode("4");
			result.setMessage("请求失败,订单服务类型错误");
			return result;
		}

		order.setState((short) 4);
		orderMapper.updateByPrimaryKey(order);

		List<OrderAunt> auntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		if (auntList != null) {
			for (OrderAunt orderAunt : auntList) {
				orderAunt.setState((short) 4);
				orderAuntMapper.updateByPrimaryKey(orderAunt);
			}
		}

		result.setCode("1");
		result.setMessage("确认成功");
		return result;
	}

	@Override
	public ApiResult comfirmOrder(User user, String orderid) {
		ApiResult result = new ApiResult();

		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (order == null) {
			result.setCode("2");
			result.setMessage("请求失败,订单不存在");
			return result;
		}

		if (order.getState() != 12) {
			result.setCode("3");
			result.setMessage("请求失败,订单状态错误");
			return result;
		}
		if (order.getCategoryid() == null || order.getCategoryid() == 1 || order.getCategoryid() == 2
				|| order.getCategoryid() == 8 || order.getCategoryid() == 5 || order.getCategoryid() == 6
				|| order.getCategoryid() == 14 || order.getCategoryid() == 15 || order.getCategoryid() == 16
				|| order.getCategoryid() == 17 || order.getCategoryid() == 18) {
			result.setCode("4");
			result.setMessage("请求失败,订单服务类型错误");
			return result;
		}
		order.setState((short) 6);
		orderMapper.updateByPrimaryKey(order);

		List<OrderAunt> auntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
		if (auntList != null) {
			for (OrderAunt orderAunt : auntList) {
				orderAunt.setState((short) 6);
				orderAuntMapper.updateByPrimaryKey(orderAunt);
			}
		}
		settlementBalance(order);
		result.setCode("1");
		result.setMessage("确认成功");
		return result;
	}

	@Override
	public List<Order> selectOrderUnComfirmed() {
		List<Order> list = orderMapper.selectOrderUnComfirmed();
		return list;
	}

	@Override
	public ApiResult comfirmMultiOrder(User user, String orderid) {
		ApiResult result = new ApiResult();

		Order order = orderMapper.selectByPrimaryKey(CommonUtils.parseInt(orderid, 0));
		if (order == null) {
			result.setCode("2");
			result.setMessage("请求失败,订单不存在");
			return result;
		}

		if (order.getState() != 10) {
			result.setCode("3");
			result.setMessage("请求失败,订单状态错误");
			return result;
		}
		if (order.getCategoryid() == 5 || order.getCategoryid() == 6) {

			order.setState((short) 2);
			orderMapper.updateByPrimaryKey(order);

			List<OrderAunt> auntList = orderAuntMapper.selectByUserOrderId(order.getOrderid());
			if (auntList != null) {
				for (OrderAunt orderAunt : auntList) {
					orderAunt.setState((short) 2);
					orderAuntMapper.updateByPrimaryKey(orderAunt);
				}
			}
		} else {
			result.setCode("4");                                                                                       
			result.setMessage("请求失败,订单服务类型错误");
			return result;

		}

		result.setCode("1");
		result.setMessage("确认成功");
		return result;
	}

	
	private void setCouponUesed(Coupon coupon, Integer userid){
		
		CouponUser couponUser = couponUserMapper.selectCouponByUserid(coupon.getCouponid(), userid);
		couponUser.setState((byte)1);
		couponUserMapper.updateByPrimaryKey(couponUser);
		
		
	}
	
	//生成红包
	private RedPacketRecord createRedPacketRecord(int type,Order order,int userType,int userid){
		RedPacketRecord record = null;
		if(type == 1){
			Date payedTime = order.getPayTime1();
			long payedTimeLong = 0;
			if(payedTime != null){
				 payedTimeLong = payedTime.getTime();
			}else {
				payedTimeLong = 0;
			}
			long nowTime = new Date().getTime();
			if(nowTime - payedTimeLong < 5*60*1000){
				record = new RedPacketRecord();
				record.setOrderid(order.getOrderid());
				record.setAddtime(new Date());
				record.setState((byte)0);
				record.setType((byte)type);
				record.setUserType(userType);
				ConfigRedPacket configRedPacket = configRedPacketMapper.selectConfigByKey(Constant.RED_PACKET_SHARE);
				//BigDecimal decimal = configRedPacket.getConfigvalue();
				//TODO:添加随机数逻辑
				Random random = new Random();
				int count = random.nextInt(10) + 1;
				record.setCount(new BigDecimal(count));
				record.setUserid(userid);
				redPacketRecordMapper.insert(record);
				
			}else {
				
			}
		}else if(type == 3){
			//TODO:添加随机数逻辑
			record = new RedPacketRecord();
			record.setOrderid(order.getOrderid());
			record.setAddtime(new Date());
			record.setState((byte)0);
			record.setType((byte)type);
			record.setUserType(userType);
			Random random = new Random();
			int count = random.nextInt(5) + 5;
			record.setCount(new BigDecimal(count));
			record.setUserid(userid);
			redPacketRecordMapper.insert(record);
		}else if(type == 4){
			record = new RedPacketRecord();
			record.setOrderid(order.getOrderid());
			record.setAddtime(new Date());
			record.setState((byte)0);
			record.setType((byte)type);
			record.setUserType(userType);
			Random random = new Random();
			int count = random.nextInt(10) + 1;
			record.setCount(new BigDecimal(count));
			record.setUserid(userid);
			redPacketRecordMapper.insert(record);
		}else if(type == 5){
			record = new RedPacketRecord();
			record.setOrderid(order.getOrderid());
			record.setAddtime(new Date());
			record.setState((byte)0);
			record.setType((byte)type);
			record.setUserType(userType);
			Random random = new Random();
			int count = random.nextInt(45) + 5;
			record.setCount(new BigDecimal(count));
			record.setUserid(userid);
			redPacketRecordMapper.insert(record);
		}
		return record;
	}
	
	
}
