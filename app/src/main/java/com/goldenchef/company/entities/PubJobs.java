package com.goldenchef.company.entities;


import java.io.Serializable;

public class PubJobs implements Serializable{

	private String id;
	/**
	 *公司名称
	 */
	private String coName;
	/**
	 *
	 */
	private String time;
	/**
	 * 招聘人数
	 */
	private String require;
	/**
	 * 招聘职位编号
	 */
	private String jobNameId;
	/**
	 * 工作地点
	 */
	private String workLocation;
	/**
	 *
	 */
	private Integer isClosed;
	/**
	 * 期望薪资
	 */
	private String paidSalary;
	/**
	 * 福利
	 */
	private String jobWelfare;
	/**
	 * 工作内容
	 */
	private String jobContent;

	private String createTime;
	private String updateTime;
	/**
	 * 企业编号
	 */
	private String companyId;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 年龄要求
	 */
	private String age;
	/**
	 * 工作年限
	 */
	private String workYears;
	private String workType;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Integer getIsClosed() {
		return isClosed;
	}
	public void setIsClosed(Integer isClosed) {
		this.isClosed = isClosed;
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
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getWorkYears() {
		return workYears;
	}
	public void setWorkYears(String workYears) {
		this.workYears = workYears;
	}
	
	
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	@Override
	public String toString() {
		return "{id=" + id + ", coName=" + coName + ", time=" + time
				+ ", require=" + require + ", jobNameId=" + jobNameId
				+ ", workLocation=" + workLocation + ", isClosed=" + isClosed
				+ ", paidSalary=" + paidSalary + ", jobWelfare=" + jobWelfare
				+ ", jobContent=" + jobContent + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", companyId=" + companyId
				+ ", phone=" + phone + ", age=" + age + ", workYears="
				+ workYears + ", workType=" + workType + "}";
	}
	
	

}