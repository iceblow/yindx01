package com.uncleserver.model;

import java.math.BigDecimal;
import java.util.Date;

public class SerPrice {
    private Integer dataid;

    private String city;

    private Integer categoryid;

    private Integer thirdCategoryid;

    private BigDecimal price;

    private BigDecimal depositPrice;

    private BigDecimal priceSmall;

    private Date addtime;

    public Integer getDataid() {
        return dataid;
    }

    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getThirdCategoryid() {
        return thirdCategoryid;
    }

    public void setThirdCategoryid(Integer thirdCategoryid) {
        this.thirdCategoryid = thirdCategoryid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(BigDecimal depositPrice) {
        this.depositPrice = depositPrice;
    }

    public BigDecimal getPriceSmall() {
        return priceSmall;
    }

    public void setPriceSmall(BigDecimal priceSmall) {
        this.priceSmall = priceSmall;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}