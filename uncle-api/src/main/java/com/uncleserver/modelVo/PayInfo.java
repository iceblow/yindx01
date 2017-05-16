package com.uncleserver.modelVo;

public class PayInfo {

	private double price;

	private int servertime;

	private int peoplecount;

	private double unit_price;
	private double deposit_price;
	private double tip_price;
	private double other_pirce;
	
	//private int payedMonth;
	private int month;
	private int singlepay;
	private int order_type;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getServertime() {
		return servertime;
	}

	public void setServertime(int servertime) {
		this.servertime = servertime;
	}

	public int getPeoplecount() {
		return peoplecount;
	}

	public void setPeoplecount(int peoplecount) {
		this.peoplecount = peoplecount;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public double getDeposit_price() {
		return deposit_price;
	}

	public void setDeposit_price(double deposit_price) {
		this.deposit_price = deposit_price;
	}

	public double getTip_price() {
		return tip_price;
	}

	public void setTip_price(double tip_price) {
		this.tip_price = tip_price;
	}

	public double getOther_pirce() {
		return other_pirce;
	}

	public void setOther_pirce(double other_pirce) {
		this.other_pirce = other_pirce;
	}

	/*public int getPayedMonth() {
		return payedMonth;
	}

	public void setPayedMonth(int payedMonth) {
		this.payedMonth = payedMonth;
	}*/

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getSinglepay() {
		return singlepay;
	}

	public void setSinglepay(int singlepay) {
		this.singlepay = singlepay;
	}

	public int getOrder_type() {
		return order_type;
	}

	public void setOrder_type(int order_type) {
		this.order_type = order_type;
	}
    
	
}
