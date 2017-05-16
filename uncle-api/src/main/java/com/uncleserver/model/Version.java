package com.uncleserver.model;

import java.io.Serializable;
import java.util.Date;

public class Version implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer versionid;

    private String versionstr;

    private Byte platformtype;

    private Byte apptype;

    private Byte poststate;

    private Byte versiontype;

    private Date posttime;

    private String downloadurl;

    private Date addtime;

    private String newcontent;

    public Integer getVersionid() {
        return versionid;
    }

    public void setVersionid(Integer versionid) {
        this.versionid = versionid;
    }

    public String getVersionstr() {
        return versionstr;
    }

    public void setVersionstr(String versionstr) {
        this.versionstr = versionstr == null ? null : versionstr.trim();
    }

    public Byte getPlatformtype() {
        return platformtype;
    }

    public void setPlatformtype(Byte platformtype) {
        this.platformtype = platformtype;
    }

    public Byte getApptype() {
        return apptype;
    }

    public void setApptype(Byte apptype) {
        this.apptype = apptype;
    }

    public Byte getPoststate() {
        return poststate;
    }

    public void setPoststate(Byte poststate) {
        this.poststate = poststate;
    }

    public Byte getVersiontype() {
        return versiontype;
    }

    public void setVersiontype(Byte versiontype) {
        this.versiontype = versiontype;
    }

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl == null ? null : downloadurl.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getNewcontent() {
        return newcontent;
    }

    public void setNewcontent(String newcontent) {
        this.newcontent = newcontent == null ? null : newcontent.trim();
    }
}