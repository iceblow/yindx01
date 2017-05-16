package com.uncleserver.model;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer roleid;

    private String rolename;

    private Byte type;

    private String permissionids;

    private Date addtime;
    
    private String permissions;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPermissionids() {
        return permissionids;
    }

    public void setPermissionids(String permissionids) {
        this.permissionids = permissionids == null ? null : permissionids.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
}