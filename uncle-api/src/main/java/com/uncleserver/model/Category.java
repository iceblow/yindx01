package com.uncleserver.model;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer dataid;

    private String name;

    private Integer sort;

    private Date addtime;

    public Integer getDataid() {
        return dataid;
    }

    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}