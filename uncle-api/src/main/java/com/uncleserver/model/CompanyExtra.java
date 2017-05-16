package com.uncleserver.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CompanyExtra implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer dataid;

	private Integer companyid;

	private Float score;

	private BigDecimal balance;

	private BigDecimal useTotal;

	private Date lastLogin;

	private Date lastBuy;

	private Date heartbeatTime;

	private Short state;

	private String accesstoken;

	private Date tokenTime;

	private Short lastDevicetype;

	private Short fontSet;

	private Short voiceSet;

	private Date addtime;

	public Integer getDataid() {
		return dataid;
	}

	public void setDataid(Integer dataid) {
		this.dataid = dataid;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getUseTotal() {
		return useTotal;
	}

	public void setUseTotal(BigDecimal useTotal) {
		this.useTotal = useTotal;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastBuy() {
		return lastBuy;
	}

	public void setLastBuy(Date lastBuy) {
		this.lastBuy = lastBuy;
	}

	public Date getHeartbeatTime() {
		return heartbeatTime;
	}

	public void setHeartbeatTime(Date heartbeatTime) {
		this.heartbeatTime = heartbeatTime;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken == null ? null : accesstoken.trim();
	}

	public Date getTokenTime() {
		return tokenTime;
	}

	public void setTokenTime(Date tokenTime) {
		this.tokenTime = tokenTime;
	}

	public Short getLastDevicetype() {
		return lastDevicetype;
	}

	public void setLastDevicetype(Short lastDevicetype) {
		this.lastDevicetype = lastDevicetype;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Short getFontSet() {
		return fontSet;
	}

	public void setFontSet(Short fontSet) {
		this.fontSet = fontSet;
	}

	public Short getVoiceSet() {
		return voiceSet;
	}

	public void setVoiceSet(Short voiceSet) {
		this.voiceSet = voiceSet;
	}

}