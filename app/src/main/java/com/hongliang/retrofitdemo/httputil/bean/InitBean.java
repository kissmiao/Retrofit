package com.hongliang.retrofitdemo.httputil.bean;

public class InitBean {
    /**
     * orgId : 1
     * readme : 关于APP相关的信息已经如何处理相关的逻辑信息以及测试新消息和保持持久性的相关的信息下
     * contact : 027-3248932
     * proId : 3
     */

    private String orgId;
    private String readme;
    private String contact;
    private String proId;
    private String wechatId;
    private String wechatName;
    private String wechatImg;
    private String timeid;
    //1为原来的四要素接口，2是走签约绑卡流程（发送验证吗，有弹框）
    private String authBankCardType;


    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getWechatImg() {
        return wechatImg;
    }

    public void setWechatImg(String wechatImg) {
        this.wechatImg = wechatImg;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getTimeid() {
        return timeid;
    }

    public void setTimeid(String timeid) {
        this.timeid = timeid;
    }

    public String getAuthBankCardType() {
        return authBankCardType;
    }

    public void setAuthBankCardType(String authBankCardType) {
        this.authBankCardType = authBankCardType;
    }
}
