package com.goldenchef.company.entities;

import java.io.Serializable;

/**
 * Created by luo-hao on 2017-03-12.
 */

public class LoginEntity implements Serializable {

    private String id;
    private String phoneNumber;
    private String isAuhenticatoin;
    private String coName;
    private String coProfileUrl;
    private String coAddress;
    private String coCertificatesUrl;
    private String coCreateTime;
    private String coIsVip;
    private String coVipDeadline;
    private String updateTime;
    private String vipOpeningTime;
    private String password;
    private String token;
    private String status;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIsAuhenticatoin() {
        return isAuhenticatoin;
    }

    public void setIsAuhenticatoin(String isAuhenticatoin) {
        this.isAuhenticatoin = isAuhenticatoin;
    }

    public void setCoIsVip(String coIsVip) {
        this.coIsVip = coIsVip;
    }

    public String getCoIsVip() {
        return coIsVip;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    public String getCoProfileUrl() {
        return coProfileUrl;
    }

    public void setCoProfileUrl(String coProfileUrl) {
        this.coProfileUrl = coProfileUrl;
    }

    public String getCoAddress() {
        return coAddress;
    }

    public void setCoAddress(String coAddress) {
        this.coAddress = coAddress;
    }

    public String getCoCertificatesUrl() {
        return coCertificatesUrl;
    }

    public void setCoCertificatesUrl(String coCertificatesUrl) {
        this.coCertificatesUrl = coCertificatesUrl;
    }

    public String getCoCreateTime() {
        return coCreateTime;
    }

    public void setCoCreateTime(String coCreateTime) {
        this.coCreateTime = coCreateTime;
    }

    public String getCoVipDeadline() {
        return coVipDeadline;
    }

    public void setCoVipDeadline(String coVipDeadline) {
        this.coVipDeadline = coVipDeadline;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getVipOpeningTime() {
        return vipOpeningTime;
    }

    public void setVipOpeningTime(String vipOpeningTime) {
        this.vipOpeningTime = vipOpeningTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
