package com.uncleserver.model;

import java.util.Date;

public class PointProgress {
    private Integer recordid;

    private Integer userid;

    private Byte userInfo;

    private Byte firstOrder;

    private Byte shareApp;

    private Date updatetime;

    private Date addtime;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Byte getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Byte userInfo) {
        this.userInfo = userInfo;
    }

    public Byte getFirstOrder() {
        return firstOrder;
    }

    public void setFirstOrder(Byte firstOrder) {
        this.firstOrder = firstOrder;
    }

    public Byte getShareApp() {
        return shareApp;
    }

    public void setShareApp(Byte shareApp) {
        this.shareApp = shareApp;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}