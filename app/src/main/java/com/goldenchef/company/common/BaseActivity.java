package com.goldenchef.company.common;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.goldenchef.company.MyApplication;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.SharedPreferencesUtil;

import butterknife.ButterKnife;

/**
 * Created by luo-hao on 2017-03-05.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private MaterialDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initInjector(MyApplication.get(this).getAppComponent());
        initView();
        initData();
    }

    protected abstract void initInjector(AppComponent appComponent);

    protected abstract void initData();

    protected abstract void initView();

    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

    protected void preferenceCommit(String key, Object value) {
        SharedPreferencesUtil.preferenceCommit(this, key, value);
    }

    protected Object getPreference(String key) {
       return SharedPreferencesUtil.getPreference(this, key);
    }

    protected void showProgressDialog(String str) {
        mProgressDialog = new MaterialDialog.Builder(this)
                .content(str)
                .progress(true, 0).build();
        mProgressDialog.show();
    }

    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
