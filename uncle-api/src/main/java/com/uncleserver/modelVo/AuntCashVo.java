package com.uncleserver.modelVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AuntCashVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer cashid;
    
    private Integer userid;
    
    private String auntname;
    
    private String companyname;

    private BigDecimal money;

    private Byte type;

    private String account;

    private String name;

    private Byte state;

    private Integer adminid;

    private Date dotime;

    private Date addtime;
    
    private Byte userType;

    public Integer getCashid() {
        return cashid;
    }

    public void setCashid(Integer cashid) {
        this.cashid = cashid;
    }

	public String getAuntname() {
		return auntname;
	}

	public void setAuntname(String auntname) {
		this.auntname = auntname;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public Date getDotime() {
        return dotime;
    }

    public void setDotime(Date dotime) {
        this.dotime = dotime;
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
    
    
}