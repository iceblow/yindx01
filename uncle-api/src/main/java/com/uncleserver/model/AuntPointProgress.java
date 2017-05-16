package com.uncleserver.model;

import java.util.Date;

public class AuntPointProgress {
    private Integer recordid;

    private Integer auntid;

    private Byte auntInfo;

    private Byte videoTimes;
    
    private String videoIds;

    private Date updatetime;

    private Date addtime;
    
    public String getVideoIds() {
		return videoIds;
	}

	public void setVideoIds(String videoIds) {
		this.videoIds = videoIds;
	}

	public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getAuntid() {
        return auntid;
    }

    public void setAuntid(Integer auntid) {
        this.auntid = auntid;
    }

    public Byte getAuntInfo() {
        return auntInfo;
    }

    public void setAuntInfo(Byte auntInfo) {
        this.auntInfo = auntInfo;
    }

    public Byte getVideoTimes() {
        return videoTimes;
    }

    public void setVideoTimes(Byte videoTimes) {
        this.videoTimes = videoTimes;
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