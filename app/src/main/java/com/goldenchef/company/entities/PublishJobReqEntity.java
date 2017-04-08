package com.goldenchef.company.entities;

import java.io.Serializable;

/**
 * Created by jianghua on 2016/10/18.
 */

public class PublishJobReqEntity implements Serializable {

    private String token;//用户登录唯一表示符
    private String interfaceAutho;//接口使用权限认证  暂时传空字符串
    private String companyId;//企业编号
    private String coName;//企业名称
    private String require;//招聘人数
    private String jobNameId;//工作编号
    private String workLocation;//工作地点
    private String paidSalary;//期望薪资
    private String jobWelfare;//福利
    private String jobContent;//工作内容
    private int workYears; //工作年限
    private int age; //年龄要求
    private int phone; //联系电话

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInterfaceAutho() {
        return interfaceAutho;
    }

    public void setInterfaceAutho(String interfaceAutho) {
        this.interfaceAutho = interfaceAutho;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    public String getJobNameId() {
        return jobNameId;
    }

    public void setJobNameId(String jobNameId) {
        this.jobNameId = jobNameId;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getPaidSalary() {
        return paidSalary;
    }

    public void setPaidSalary(String paidSalary) {
        this.paidSalary = paidSalary;
    }

    public String getJobWelfare() {
        return jobWelfare;
    }

    public void setJobWelfare(String jobWelfare) {
        this.jobWelfare = jobWelfare;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public int getWorkYears() {
        return workYears;
    }

    public void setWorkYears(int workYears) {
        this.workYears = workYears;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
