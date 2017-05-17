package com.goldenchef.company;

import android.app.Application;
import android.content.Context;

import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.injector.component.DaggerAppComponent;
import com.goldenchef.company.injector.module.ApiModule;
import com.goldenchef.company.utils.CrashHandler;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by luo-hao on 2017-03-11.
 */

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);

        //dagger2
        mAppComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .build();

        //环信
        EMOptions options = new EMOptions();
        //默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(true);
        options.setAutoLogin(true); //自动登录
        //初始化
        EaseUI.getInstance().init(this, options);
    }

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
