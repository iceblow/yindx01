package com.uncleserver.model;

import java.util.Date;

public class AuntSkill {
    private Integer dataid;

    private Integer auntid;

    private Integer categoryid;

    private Integer thirdid;

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

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getThirdid() {
        return thirdid;
    }

    public void setThirdid(Integer thirdid) {
        this.thirdid = thirdid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}