package com.goldenchef.company.entities.responses;

import com.goldenchef.company.entities.JobTypeEntity;

import java.util.List;

/**
 * Created by luo-hao on 2017-03-19.
 *
 * 工作类型响应
 */
public class JobsTypeResponse extends BaseResponse {

    private JobTypeMap map;

    public JobTypeMap getMap() {
        return map;
    }

    public void setMap(JobTypeMap map) {
        this.map = map;
    }

    public class JobTypeMap{
        private List<JobTypeEntity> jobs;

        public List<JobTypeEntity> getJobs() {
            return jobs;
        }

        public void setJobs(List<JobTypeEntity> jobs) {
            this.jobs = jobs;
        }
    }
}
