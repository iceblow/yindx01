package com.uncleserver.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer orderid;

	private Short posterType;

	private Integer userid;

	private String ordernum;

	private Integer categoryid;

	private Double longitude;

	private Double latitude;

	private String phone;

	private String rname;

	private String sex;

	private String addressname;

	private String addressdetail;

	private Date serverTime;

	private String picIds;

	private Float expectTime;

	private Float month;

	private Integer auntMCount;

	private Integer auntWCount;

	private Float dayTime;

	private Integer thingCount;

	private Short needTools;

	private Integer companyid;

	private BigDecimal expectedPrice;

	private BigDecimal depositPrice;

	private BigDecimal tipPrice;

	private BigDecimal lastPrice;

	private BigDecimal otherPirce;

	private BigDecimal couponPirce;

	private Short orderType;

	private Integer relationOrderid;

	private Float ratio;

	private BigDecimal ratioMoney;

	private Short state;

	private Short complaintState;

	private Short commentState;

	private Short acceptType;

	private Short orderSource;

	private String book;

	private Date serverStartTime;

	private Date serverEndTime;

	private Date acceptTime;

	private Date payTime2;

	private Date payTime1;

	private String foodselect;

	private String reasonMark;

	private Date addtime;

	private Date payedTime;

	private String couponid;

	private String city;

	private String categoryname;

	private Integer auntid;
	
	
	private String strPosterType;
	private String username;
	private String companyname;
	private String strOrderType;
	private String strState;
	private String strComplaintState;
	private String strCommentState;
	private String strAcceptType;
	private String strOrderSource;
	

	public String getStrPosterType() {
		return strPosterType;
	}

	public void setStrPosterType(String strPosterType) {
		this.strPosterType = strPosterType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getStrOrderType() {
		return strOrderType;
	}

	public void setStrOrderType(String strOrderType) {
		this.strOrderType = strOrderType;
	}

	public String getStrState() {
		return strState;
	}

	public void setStrState(String strState) {
		this.strState = strState;
	}

	public String getStrComplaintState() {
		return strComplaintState;
	}

	public void setStrComplaintState(String strComplaintState) {
		this.strComplaintState = strComplaintState;
	}

	public String getStrCommentState() {
		return strCommentState;
	}

	public void setStrCommentState(String strCommentState) {
		this.strCommentState = strCommentState;
	}

	public String getStrAcceptType() {
		return strAcceptType;
	}

	public void setStrAcceptType(String strAcceptType) {
		this.strAcceptType = strAcceptType;
	}

	public String getStrOrderSource() {
		return strOrderSource;
	}

	public void setStrOrderSource(String strOrderSource) {
		this.strOrderSource = strOrderSource;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Short getPosterType() {
		return posterType;
	}

	public void setPosterType(Short posterType) {
		this.posterType = posterType;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum == null ? null : ordernum.trim();
	}

	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname == null ? null : rname.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getAddressname() {
		return addressname;
	}

	public void setAddressname(String addressname) {
		this.addressname = addressname == null ? null : addressname.trim();
	}

	public String getAddressdetail() {
		return addressdetail;
	}

	public void setAddressdetail(String addressdetail) {
		this.addressdetail = addressdetail == null ? null : addressdetail.trim();
	}

	public Date getServerTime() {
		return serverTime;
	}

	public void setServerTime(Date serverTime) {
		this.serverTime = serverTime;
	}

	public String getPicIds() {
		return picIds;
	}

	public void setPicIds(String picIds) {
		this.picIds = picIds == null ? null : picIds.trim();
	}

	public Float getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(Float expectTime) {
		this.expectTime = expectTime;
	}

	public Integer getAuntMCount() {
		return auntMCount;
	}

	public void setAuntMCount(Integer auntMCount) {
		this.auntMCount = auntMCount;
	}

	public Integer getAuntWCount() {
		return auntWCount;
	}

	public void setAuntWCount(Integer auntWCount) {
		this.auntWCount = auntWCount;
	}

	public Float getDayTime() {
		return dayTime;
	}

	public void setDayTime(Float dayTime) {
		this.dayTime = dayTime;
	}

	public Integer getThingCount() {
		return thingCount;
	}

	public void setThingCount(Integer thingCount) {
		this.thingCount = thingCount;
	}

	public Short getNeedTools() {
		return needTools;
	}

	public void setNeedTools(Short needTools) {
		this.needTools = needTools;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public BigDecimal getExpectedPrice() {
		return expectedPrice;
	}

	public void setExpectedPrice(BigDecimal expectedPrice) {
		this.expectedPrice = expectedPrice;
	}

	public BigDecimal getDepositPrice() {
		return depositPrice;
	}

	public void setDepositPrice(BigDecimal depositPrice) {
		this.depositPrice = depositPrice;
	}

	public BigDecimal getTipPrice() {
		return tipPrice;
	}

	public void setTipPrice(BigDecimal tipPrice) {
		this.tipPrice = tipPrice;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public BigDecimal getOtherPirce() {
		return otherPirce;
	}

	public void setOtherPirce(BigDecimal otherPirce) {
		this.otherPirce = otherPirce;
	}

	public BigDecimal getCouponPirce() {
		return couponPirce;
	}

	public void setCouponPirce(BigDecimal couponPirce) {
		this.couponPirce = couponPirce;
	}

	public Short getOrderType() {
		return orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	public Integer getRelationOrderid() {
		return relationOrderid;
	}

	public void setRelationOrderid(Integer relationOrderid) {
		this.relationOrderid = relationOrderid;
	}

	public Float getRatio() {
		return ratio;
	}

	public void setRatio(Float ratio) {
		this.ratio = ratio;
	}

	public BigDecimal getRatioMoney() {
		return ratioMoney;
	}

	public void setRatioMoney(BigDecimal ratioMoney) {
		this.ratioMoney = ratioMoney;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Short getComplaintState() {
		return complaintState;
	}

	public void setComplaintState(Short complaintState) {
		this.complaintState = complaintState;
	}

	public Short getCommentState() {
		return commentState;
	}

	public void setCommentState(Short commentState) {
		this.commentState = commentState;
	}

	public Short getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(Short acceptType) {
		this.acceptType = acceptType;
	}

	public Short getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(Short orderSource) {
		this.orderSource = orderSource;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book == null ? null : book.trim();
	}

	public Date getServerStartTime() {
		return serverStartTime;
	}

	public void setServerStartTime(Date serverStartTime) {
		this.serverStartTime = serverStartTime;
	}

	public Date getServerEndTime() {
		return serverEndTime;
	}

	public void setServerEndTime(Date serverEndTime) {
		this.serverEndTime = serverEndTime;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Date getPayTime2() {
		return payTime2;
	}

	public void setPayTime2(Date payTime2) {
		this.payTime2 = payTime2;
	}

	public Date getPayTime1() {
		return payTime1;
	}

	public void setPayTime1(Date payTime1) {
		this.payTime1 = payTime1;
	}

	public String getFoodselect() {
		return foodselect;
	}

	public void setFoodselect(String foodselect) {
		this.foodselect = foodselect == null ? null : foodselect.trim();
	}

	public String getReasonMark() {
		return reasonMark;
	}

	public void setReasonMark(String reasonMark) {
		this.reasonMark = reasonMark == null ? null : reasonMark.trim();
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getPayedTime() {
		return payedTime;
	}

	public void setPayedTime(Date payedTime) {
		this.payedTime = payedTime;
	}

	public String getCouponid() {
		return couponid;
	}

	public void setCouponid(String couponid) {
		this.couponid = couponid == null ? null : couponid.trim();
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public Integer getAuntid() {
		return auntid;
	}

	public void setAuntid(Integer auntid) {
		this.auntid = auntid == null ? 0 : auntid;
	}

	public Float getMonth() {
		return month;
	}

	public void setMonth(Float month) {
		this.month = month;
	}

}