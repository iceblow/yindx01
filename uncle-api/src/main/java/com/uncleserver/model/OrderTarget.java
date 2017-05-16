package com.uncleserver.model;

public class OrderTarget {
    private Integer dataid;

    private Integer orderid;

    private String sex;

    private Integer age;

    private Float weight;

    private Float height;

    private Byte behaviorType;

    private Byte illnessType;

    public Integer getDataid() {
        return dataid;
    }

    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Byte getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(Byte behaviorType) {
        this.behaviorType = behaviorType;
    }

    public Byte getIllnessType() {
        return illnessType;
    }

    public void setIllnessType(Byte illnessType) {
        this.illnessType = illnessType;
    }
}