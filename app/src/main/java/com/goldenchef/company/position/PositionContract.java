package com.goldenchef.company.position;

import com.goldenchef.company.BasePresenter;
import com.goldenchef.company.BaseView;

import java.util.Map;

/**
 * Created by luo-hao on 2017-03-16.
 */

public interface PositionContract {

    interface View extends BaseView{
        void publishJobSuccessful(String result);

        void publishJobFailure(String result);
    }

    interface Presenter extends BasePresenter<View>{
        void publishJob(Map<String, String> map);
    }

}
