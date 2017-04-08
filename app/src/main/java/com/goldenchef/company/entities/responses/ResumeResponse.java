package com.goldenchef.company.entities.responses;

import com.goldenchef.company.entities.ResumeEntity;

import java.util.List;

/**
 * Created by luo-hao on 2017-03-19.
 */

public class ResumeResponse extends BaseResponse {

    private List<ResumeEntity> map;

    public List<ResumeEntity> getMap() {
        return map;
    }

    public void setMap(List<ResumeEntity> map) {
        this.map = map;
    }
}
