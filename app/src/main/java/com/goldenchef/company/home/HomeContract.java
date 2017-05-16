package com.goldenchef.company.home;

import com.goldenchef.company.common.BasePresenter;
import com.goldenchef.company.common.BaseView;
import com.goldenchef.company.entities.ResumeEntity;

import java.util.List;

/**
 * Created by luo-hao on 2017-03-16.
 */

public class HomeContract {

    interface View extends BaseView {
        void showList(List<ResumeEntity> datas);

        void showDatasFailure(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void selectJobs(String token, int currentPage, int pageSize, String id);
    }

}
