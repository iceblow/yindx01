package com.uncleserver.model;

import java.util.Date;

public class AuntLike {
	private Integer dataid;

	private Integer auntid;

	private Integer userid;

	private Date addtime;

	public Integer getDataid() {
		return dataid;
	}

	public void setDataid(Integer dataid) {
		this.dataid = dataid;
	}

	public Integer getAuntid() {
		return auntid;
	}

	public void setAuntid(Integer auntid) {
		this.auntid = auntid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

}