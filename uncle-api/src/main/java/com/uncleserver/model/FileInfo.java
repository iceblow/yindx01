package com.uncleserver.model;

import java.io.Serializable;
import java.util.Date;

public class FileInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer fileid;

    private Byte filetype;

    private String filePath;

    private Byte state;

    private Date addtime;

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public Byte getFiletype() {
        return filetype;
    }

    public void setFiletype(Byte filetype) {
        this.filetype = filetype;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}