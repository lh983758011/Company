package com.goldenchef.company.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseActivity;
import com.goldenchef.company.injector.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 退出界面
 */
public class SignOutActivity extends BaseActivity {

    @BindView(R.id.sign_out_tb)
    Toolbar sign_out_tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        sign_out_tb.setTitle("");
        setSupportActionBar(sign_out_tb);
    }

    @OnClick({R.id.sign_out_btn, R.id.setting_btn_back, R.id.setting_layout_modify_password, R.id.setting_layout_change_phone})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.sign_out_btn:
                //退出
                preferenceCommit("isLogin", false);
                preferenceCommit("token", "");

                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.setting_btn_back:
                //返回
                onBackPressed();
                break;
            case R.id.setting_layout_modify_password:
                //修改密码
                break;
            case R.id.setting_layout_change_phone:
                //更换手机

                break;
        }
    }
}
