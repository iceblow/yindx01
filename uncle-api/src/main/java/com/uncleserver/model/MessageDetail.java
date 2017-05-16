package com.uncleserver.model;

import java.util.Date;

public class MessageDetail {
    private Integer id;

    private Integer messageid;

    private Integer sysMessageid;

    private String title;

    private String detail;

    private String linkTitle;

    private Short linkType;

    private String linkContent;

    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public Integer getSysMessageid() {
        return sysMessageid;
    }

    public void setSysMessageid(Integer sysMessageid) {
        this.sysMessageid = sysMessageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle == null ? null : linkTitle.trim();
    }

    public Short getLinkType() {
        return linkType;
    }

    public void setLinkType(Short linkType) {
        this.linkType = linkType;
    }

    public String getLinkContent() {
        return linkContent;
    }

    public void setLinkContent(String linkContent) {
        this.linkContent = linkContent == null ? null : linkContent.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}