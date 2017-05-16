package com.uncleserver.model;

import java.io.Serializable;
import java.util.Date;

public class AuntBanner implements Serializable {
    private Integer bannerid;

    private Integer picid;

    private Byte type;

    private Integer sort;

    private String contentid;

    private Date addtime;

    public Integer getBannerid() {
        return bannerid;
    }

    public void setBannerid(Integer bannerid) {
        this.bannerid = bannerid;
    }

    public Integer getPicid() {
        return picid;
    }

    public void setPicid(Integer picid) {
        this.picid = picid;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid == null ? null : contentid.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}