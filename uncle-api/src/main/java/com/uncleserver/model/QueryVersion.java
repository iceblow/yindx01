package com.uncleserver.model;

import java.io.Serializable;

import com.uncleserver.common.CommonUtils;

public class QueryVersion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public QueryVersion(){
		
	}
	public QueryVersion(Version v){
		this.versionid = v.getVersionid();
		this.versionstr = v.getVersionstr();
		this.platformtype=v.getPlatformtype();
		if(v.getPlatformtype()==0){
			this.platformtypename = "安卓端";
		}else{
			this.platformtypename = "IOS端";
		}
		this.apptype = v.getApptype();
		if(v.getApptype()==0){
			this.apptypename = "用户端";
		}else{
			this.apptypename = "阿姨端";
		}
		this.poststate = v.getPoststate();
		if(poststate==0){
			this.poststatename = "未发布";
		}else{
			this.poststatename = "已发布";
		}
		this.versiontype = v.getVersiontype();
		if(versiontype==0){
			this.versiontypename = "小版本";
		}else{
			this.versiontypename = "大版本";
		}
		this.posttime =	CommonUtils.getTimeFormat(v.getPosttime(), "yyyy-MM-dd HH:mm:ss");
		this.downloadurl = v.getDownloadurl(); 
		this.addtime = CommonUtils.getTimeFormat(v.getAddtime(), "yyyy-MM-dd HH:mm:ss");
		this.newcontent = v.getNewcontent();
	}

	private Integer versionid;

    private String versionstr;

    private Byte platformtype;
    
    private  String platformtypename;

    private Byte apptype;
    
    private String apptypename;

    private Byte poststate;
    
    private String poststatename;

    private Byte versiontype;
    
    private String versiontypename;

    private String posttime;

    private String downloadurl;

    private String addtime;

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
		this.versionstr = versionstr;
	}

	public Byte getPlatformtype() {
		return platformtype;
	}

	public void setPlatformtype(Byte platformtype) {
		this.platformtype = platformtype;
	}

	public String getPlatformtypename() {
		return platformtypename;
	}

	public void setPlatformtypename(String platformtypename) {
		this.platformtypename = platformtypename;
	}

	public Byte getApptype() {
		return apptype;
	}

	public void setApptype(Byte apptype) {
		this.apptype = apptype;
	}

	public String getApptypename() {
		return apptypename;
	}

	public void setApptypename(String apptypename) {
		this.apptypename = apptypename;
	}

	public Byte getPoststate() {
		return poststate;
	}

	public void setPoststate(Byte poststate) {
		this.poststate = poststate;
	}

	public String getPoststatename() {
		return poststatename;
	}

	public void setPoststatename(String poststatename) {
		this.poststatename = poststatename;
	}

	public Byte getVersiontype() {
		return versiontype;
	}

	public void setVersiontype(Byte versiontype) {
		this.versiontype = versiontype;
	}

	public String getVersiontypename() {
		return versiontypename;
	}

	public void setVersiontypename(String versiontypename) {
		this.versiontypename = versiontypename;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}

	public String getDownloadurl() {
		return downloadurl;
	}

	public void setDownloadurl(String downloadurl) {
		this.downloadurl = downloadurl;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getNewcontent() {
		return newcontent;
	}

	public void setNewcontent(String newcontent) {
		this.newcontent = newcontent;
	}
    
    
}
