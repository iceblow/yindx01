package com.uncleserver.modelVo;

public class VersionModel {
	private String posttime;
	private String downloadurl;
	private String newcontent;
	private int versiontype; // 0.小版本 1.大版本

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

	public String getNewcontent() {
		return newcontent;
	}

	public void setNewcontent(String newcontent) {
		this.newcontent = newcontent;
	}

	public int getVersiontype() {
		return versiontype;
	}

	public void setVersiontype(int versiontype) {
		this.versiontype = versiontype;
	}

}
