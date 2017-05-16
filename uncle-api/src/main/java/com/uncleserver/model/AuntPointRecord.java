package com.uncleserver.model;

import java.util.Date;

public class AuntPointRecord {
    private Integer recordid;

    private Integer auntid;

    private Integer count;

    private Byte type;

    private Date addtime;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}