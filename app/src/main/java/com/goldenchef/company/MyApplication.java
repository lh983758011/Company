package com.goldenchef.company;

import android.app.Application;
import android.content.Context;

import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.injector.component.DaggerAppComponent;
import com.goldenchef.company.injector.module.ApiModule;
import com.goldenchef.company.utils.CrashHandler;

/**
 * Created by luo-hao on 2017-03-11.
 */

public class MyApplication extends Application{

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);

        mAppComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }

    public static MyApplication get(Context context){
        return (MyApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
