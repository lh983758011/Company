package com.goldenchef.company.login;

import android.support.annotation.NonNull;

import com.goldenchef.company.api.ApiService;
import com.goldenchef.company.entities.LoginEntity;
import com.goldenchef.company.entities.responses.LoginResponse;
import com.goldenchef.company.utils.EasemobUtil;
import com.goldenchef.company.utils.GsonConverter;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by luo-hao on 2017-03-07.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;
    private ApiService mApiService;
    private Subscription mSubscription;

    @Inject
    public LoginPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 登录
     *
     * @param phoneNum
     * @param password
     * @param code
     */
    public void login(final String phoneNum, final String password, String code) {
        mSubscription = mApiService.login(phoneNum, "", code).subscribeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<LoginEntity>>() {
                    @Override
                    public Observable<LoginEntity> call(String s) {
                        LoginResponse response = GsonConverter.GsonToBean(s, LoginResponse.class);

                        LoginResponse.LoginMap loginMap = response.getMap();

                        LoginEntity entity = null;
                        if (loginMap != null)
                            entity = loginMap.getCompanyUser();

                        //暂时这样处理
                        //应该放在注册时
                        EasemobUtil.registUser(phoneNum, password);

                        return Observable.just(entity);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<LoginEntity>() {
                    @Override
                    public void call(final LoginEntity result) {
                        if (result != null) {
                            //环信登录
                            EasemobUtil.login(phoneNum, password);

                            mLoginView.loginSuccessful(result.getToken());


                        } else
                            mLoginView.loginFailure("登录失败");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mLoginView.loginFailure(throwable.getMessage());
                    }
                });
    }

    /**
     * 获取验证码
     *
     * @param phoneNum
     */
    public void getVerificationCod(String phoneNum) {
        mSubscription = mApiService.getVerificationCod(phoneNum).subscribeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            return Observable.just((Boolean) jsonObject.get("status"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return Observable.just(false);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean result) {
                        if (result)
                            mLoginView.getVerificationCodSuccessful();
                        else
                            mLoginView.getVerificationCodFailure("获取验证码失败");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mLoginView.getVerificationCodFailure(throwable.getMessage());
                    }
                });
    }


    @Override
    public void attachView(@NonNull LoginContract.View view) {
        mLoginView = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
        mLoginView = null;
    }
}
