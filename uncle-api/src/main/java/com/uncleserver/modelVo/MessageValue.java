package com.uncleserver.modelVo;

public class MessageValue {
	
	private int id;
	private String title;
	private int type;
	private int read_state;
	private int unread_count;
	private String timestr;
	private String lastmessage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getRead_state() {
		return read_state;
	}
	public void setRead_state(int read_state) {
		this.read_state = read_state;
	}
	public int getUnread_count() {
		return unread_count;
	}
	public void setUnread_count(int unread_count) {
		this.unread_count = unread_count;
	}
	public String getTimestr() {
		return timestr;
	}
	public void setTimestr(String timestr) {
		this.timestr = timestr;
	}
	public String getLastmessage() {
		return lastmessage;
	}
	public void setLastmessage(String lastmessage) {
		this.lastmessage = lastmessage;
	}
	
}
