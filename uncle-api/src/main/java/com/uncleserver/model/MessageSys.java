package com.uncleserver.model;

import java.io.Serializable;
import java.util.Date;

public class MessageSys  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Byte type;

    private String title;

    private String detail;

    private String link_title;

    private Byte link_type;

    private String link_content;
    
    private Byte del_state;

    private String city;


    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
   

    public String getLink_title() {
		return link_title;
	}

	public void setLink_title(String link_title) {
		this.link_title = link_title;
	}

	public Byte getLink_type() {
		return link_type;
	}

	public void setLink_type(Byte link_type) {
		this.link_type = link_type;
	}

	public String getLink_content() {
		return link_content;
	}

	public void setLink_content(String link_content) {
		this.link_content = link_content;
	}

	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }


    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public Byte getDel_state() {
		return del_state;
	}

	public void setDel_state(Byte del_state) {
		this.del_state = del_state;
	}
}