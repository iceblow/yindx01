package com.uncleserver.model;

import java.util.Date;

public class AuntCard {
    private Integer cardid;

    private Integer auntid;

    private String account;

    private String name;

    private Date addtime;
    
    private Byte userType;

    public Integer getCardid() {
        return cardid;
    }

    public void setCardid(Integer cardid) {
        this.cardid = cardid;
    }

    public Integer getAuntid() {
        return auntid;
    }

    public void setAuntid(Integer auntid) {
        this.auntid = auntid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}
}