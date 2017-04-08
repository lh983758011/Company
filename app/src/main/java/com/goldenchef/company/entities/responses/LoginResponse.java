package com.goldenchef.company.entities.responses;

import com.goldenchef.company.entities.LoginEntity;

/**
 * Created by luo-hao on 2017-03-19.
 */

public class LoginResponse extends BaseResponse {

    private LoginMap map;

    public LoginMap getMap() {
        return map;
    }

    public void setMap(LoginMap map) {
        this.map = map;
    }

    public class LoginMap{
        private LoginEntity companyUser;

        public LoginEntity getCompanyUser() {
            return companyUser;
        }

        public void setCompanyUser(LoginEntity companyUser) {
            this.companyUser = companyUser;
        }
    }

}
