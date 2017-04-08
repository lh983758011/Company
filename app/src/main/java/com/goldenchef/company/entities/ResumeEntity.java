package com.goldenchef.company.entities;

/**
 * 简历实体
 */
public class ResumeEntity {
    private String id;

    private String uid;

    private String isPublic;

    private String viewedTimes;

    private String jobExpected;

	private String jobExpectedStr;

    private String salaryExpected;

    private String locationExpectedId;

    private String updateTime;

    private String isDelete;

    private String isAutoModel;

    private String workType;

	private String workTypeStr;

    private String resumeProfileUrl;//简历头像
    
    private String uploadResult;//文件上传结果 0:上传成功 1表示上传失败 2：未上传
    
    private String createTime;
    
    private String expectedJobDesc;

	private String age;

	private String sex;

	private String education;

	private String workYear;

	private String name;
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getViewedTimes() {
		return viewedTimes;
	}

	public void setViewedTimes(String viewedTimes) {
		this.viewedTimes = viewedTimes;
	}

	public String getJobExpected() {
		return jobExpected;
	}

	public void setJobExpected(String jobExpected) {
		this.jobExpected = jobExpected;
	}

	public String getSalaryExpected() {
		return salaryExpected;
	}

	public void setSalaryExpected(String salaryExpected) {
		this.salaryExpected = salaryExpected;
	}

	public String getLocationExpectedId() {
		return locationExpectedId;
	}

	public void setLocationExpectedId(String locationExpectedId) {
		this.locationExpectedId = locationExpectedId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getIsAutoModel() {
		return isAutoModel;
	}

	public void setIsAutoModel(String isAutoModel) {
		this.isAutoModel = isAutoModel;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}


	public String getResumeProfileUrl() {
		return resumeProfileUrl;
	}

	public void setResumeProfileUrl(String resumeProfileUrl) {
		this.resumeProfileUrl = resumeProfileUrl;
	}

	public String getUploadResult() {
		return uploadResult;
	}

	public void setUploadResult(String uploadResult) {
		this.uploadResult = uploadResult;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getExpectedJobDesc() {
		return expectedJobDesc;
	}

	public void setExpectedJobDesc(String expectedJobDesc) {
		this.expectedJobDesc = expectedJobDesc;
	}

	public String getJobExpectedStr() {
		return jobExpectedStr;
	}

	public void setJobExpectedStr(String jobExpectedStr) {
		this.jobExpectedStr = jobExpectedStr;
	}

	public String getWorkTypeStr() {
		return workTypeStr;
	}

	public void setWorkTypeStr(String workTypeStr) {
		this.workTypeStr = workTypeStr;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getWorkYear() {
		return workYear;
	}

	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Resume [id=" + id + ", uid=" + uid + ", isPublic=" + isPublic
				+ ", viewedTimes=" + viewedTimes + ", jobExpected="
				+ jobExpected + ", salaryExpected=" + salaryExpected
				+ ", locationExpectedId=" + locationExpectedId
				+ ", updateTime=" + updateTime + ", isDelete=" + isDelete
				+ ", isAutoModel=" + isAutoModel + ", workType=" + workType
				+ ", resumeProfileUrl=" + resumeProfileUrl + ", uploadResult="
				+ uploadResult + ", createTime=" + createTime
				+ ", expectedJobDesc=" + expectedJobDesc + "]";
	}

	

}