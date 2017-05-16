package com.goldenchef.company.login;

import com.goldenchef.company.common.BasePresenter;
import com.goldenchef.company.common.BaseView;

/**
 * Created by luo-hao on 2017-03-16.
 */

public class LoginContract {

    interface View extends BaseView {
        void getVerificationCodSuccessful();

        void getVerificationCodFailure(String result);

        void loginSuccessful(String token);

        void loginFailure(String result);
    }

    interface Presenter extends BasePresenter<View> {
        void login(String phoneNum, String password, String code);
        void getVerificationCod(String phoneNum);
    }

}
