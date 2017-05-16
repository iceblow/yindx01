package com.uncleserver.modelVo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class ApiResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String c;

	private String m;

	private String r;

	public ApiResult() {

	}

	public ApiResult(String c) {
		this.c = c;
	}

	public ApiResult(String c, String m) {
		this.c = c;
		this.m = m;
	}

	public String getC() {
		return c;
	}

	public void setCode(String c) {
		this.c = c;
	}

	public void setMessage(String m) {
		this.m = m;
	}

	public String getM() {
		return m;
	}

	public String getR() {
		return r;
	}

	public void setResult(Object r) {
		this.r = JSONObject.toJSONString(r);
	}

}
