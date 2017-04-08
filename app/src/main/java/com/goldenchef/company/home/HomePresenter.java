package com.goldenchef.company.home;

import android.support.annotation.NonNull;

import com.goldenchef.company.api.ApiService;
import com.goldenchef.company.entities.JobTypeEntity;
import com.goldenchef.company.entities.ResumeEntity;
import com.goldenchef.company.entities.responses.JobsTypeResponse;
import com.goldenchef.company.entities.responses.ResumeResponse;
import com.goldenchef.company.utils.GsonConverter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by luo-hao on 2017-03-14.
 */
public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mHomeView;

    private Subscription mSubscription;

    private ApiService mApiService;

    @Inject
    public HomePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void selectJobs(String token, int currentPage, int pageSize, String id) {

//        Map<String, Object> map = new HashMap<>();
//        map.put("ct", "1490093353");
//        map.put("op", "z10102");
//        map.put("appid", "20021");
//        map.put("token", "");
//        map.put("sign", "5BA98AC64D051172DA83672CE5D274EA");
//        map.put("user_id", "");
//        map.put("create_os", "2");
//        map.put("create_unique", "E7CE3B95508A2144C3ED29B2331BF2C4");
//        map.put("user_name", "18256089757");
//        map.put("passwd", "F26D24135138C70C2C74026422D6CC3D");
//        map.put("register_type", "2");
//        Map<String, String> map2 = new HashMap<>();
//        map2.put("nickname", "nick");
//        map.put("user_info", map2);
//        map.put("sms_code", "123456");
//        mApiService.test(map).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.e("TAG", "result" + s);
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//
//                    }
//                });



        mSubscription = mApiService.selectJobs(token, currentPage, pageSize, id).subscribeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<ResumeEntity>>() {
                    @Override
                    public Observable<ResumeEntity> call(String s) {
                        ResumeResponse resumeResponse = GsonConverter.GsonToBean(s, ResumeResponse.class);
                        return Observable.from(resumeResponse.getMap());
                    }
                })
                .flatMap(new Func1<ResumeEntity, Observable<ResumeEntity>>() {
                    @Override
                    public Observable<ResumeEntity> call(final ResumeEntity resumeEntity) {
                        return mApiService.selectJobType("cust_jobs201609071036151223")
                                .subscribeOn(Schedulers.io())
                                .map(new Func1<String, ResumeEntity>() {
                                    @Override
                                    public ResumeEntity call(String s) {
                                        JobsTypeResponse response = GsonConverter.GsonToBean(s, JobsTypeResponse.class);

                                        List<JobTypeEntity> jobTypes = response.getMap().getJobs();
                                        if (jobTypes != null && jobTypes.size() > 0)
                                            resumeEntity.setJobExpectedStr(jobTypes.get(0).getName());

                                        return resumeEntity;
                                    }
                                });
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ResumeEntity>>() {
                    @Override
                    public void call(List<ResumeEntity> results) {
                        if (results != null && results.size() > 0)
                            mHomeView.showList(results);
                        else
                            mHomeView.showDatasFailure("没有数据");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mHomeView.showDatasFailure(throwable.getMessage());
                    }
                });
    }

    @Override
    public void attachView(@NonNull HomeContract.View view) {
        mHomeView = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
        mHomeView = null;
    }

}
