package com.uncleserver.model;

import java.io.Serializable;

public class Cities implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private Integer provienceid;

    private Byte state;

    private Byte fastState;
    
    private Integer cityid;
    
    private String cityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getProvienceid() {
        return provienceid;
    }

    public void setProvienceid(Integer provienceid) {
        this.provienceid = provienceid;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getFastState() {
        return fastState;
    }

    public void setFastState(Byte fastState) {
        this.fastState = fastState;
    }

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}