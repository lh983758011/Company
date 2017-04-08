package com.goldenchef.company.position;

import android.support.annotation.NonNull;

import com.goldenchef.company.api.ApiService;
import com.goldenchef.company.entities.responses.BaseResponse;
import com.goldenchef.company.utils.GsonConverter;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by luo-hao on 2017-03-11.
 */

public class PublishJobPresenter implements PositionContract.Presenter {

    private PositionContract.View mPublishJobView;
    private Subscription mSubscription;
    private ApiService mApiService;

    @Inject
    public PublishJobPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void publishJob(Map<String, String> map) {
        mSubscription = mApiService.publishJob(map).subscribeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(String s) {

                        BaseResponse response = GsonConverter.GsonToBean(s, BaseResponse.class);

                        return Observable.just(response.isStatus());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean result) {
                        if (result)
                            mPublishJobView.publishJobSuccessful("职位发布成功");
                        else
                            mPublishJobView.publishJobFailure("职位发布失败");

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mPublishJobView.publishJobFailure(throwable.getMessage());
                    }
                });
    }

    @Override
    public void attachView(@NonNull PositionContract.View view) {
        mPublishJobView = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
        mPublishJobView = null;
    }
}
