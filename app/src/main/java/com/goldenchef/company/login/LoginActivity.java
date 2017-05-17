package com.goldenchef.company.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseActivity;
import com.goldenchef.company.entities.LoginEntity;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.main.MainActivity;
import com.goldenchef.company.utils.Constants;
import com.goldenchef.company.utils.Utils;
import com.goldenchef.company.utils.WeakReferenceHandler;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luo-hao on 2017-03-07.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.login_et_phone)
    AppCompatEditText login_et_phone;

    @BindView(R.id.login_et_verification_code)
    AppCompatEditText login_et_verification_code;

    @BindView(R.id.login_tv_get_verification_code)
    AppCompatTextView login_tv_get_verification_code;

    @BindView(R.id.login_btn_login)
    View login_btn_login;

    @Inject
    LoginPresenter mLoginPresenter;

    private int NUM = Constants.CUTDOWN;
    private LoginHandler mHandler = new LoginHandler(this);

    private static class LoginHandler extends WeakReferenceHandler<LoginActivity> {

        public LoginHandler(LoginActivity r) {
            super(r);
        }

        @Override
        protected void handleMessage(LoginActivity reference, Message msg) {
            switch (msg.what) {
                case 0:
                    reference.NUM--;

                    if (reference.NUM == 0) {
                        reference.resetCheckNum();
                    } else {
                        reference.login_tv_get_verification_code.setText(reference.NUM + "秒后重发");
                        reference.login_tv_get_verification_code.setEnabled(false);
                        reference.mHandler.sendEmptyMessageDelayed(0, 1000);
                    }
                    break;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        if (getPreference("isLogin") != null && (Boolean) getPreference("isLogin")) {
            jumpToMain();
        }
        mLoginPresenter.attachView(this);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.login_btn_login, R.id.login_tv_get_verification_code})
    public void onClick(View view) {
        String phoneNumber = null;
        switch (view.getId()) {
            case R.id.login_btn_login:
                //登录

//                jumpToMain();

                phoneNumber = login_et_phone.getText().toString().trim();
                String code = login_et_verification_code.getText().toString().trim();

                if (Utils.isEmpty(phoneNumber)) {
                    showToast(getResources().getString(R.string.phonenum_could_not_empty));
                    login_et_phone.requestFocus();
                    return;
                }

                if (!Utils.isMobileNO(phoneNumber)) {
                    showToast(getResources().getString(R.string.phonenum_format_is_error));
                    login_et_phone.requestFocus();
                    return;
                }

                if (Utils.isEmpty(code)) {
                    showToast(getResources().getString(R.string.code_could_not_empty));
                    login_et_verification_code.requestFocus();
                    return;
                }

                mLoginPresenter.login(phoneNumber, "123456", code);
                break;

            case R.id.login_tv_get_verification_code:
                //获取登录验证码
                phoneNumber = login_et_phone.getText().toString().trim();

                if (Utils.isEmpty(phoneNumber)) {
                    showToast(getResources().getString(R.string.phonenum_could_not_empty));
                    login_et_phone.requestFocus();
                    return;
                }

                if (!Utils.isMobileNO(phoneNumber)) {
                    showToast(getResources().getString(R.string.phonenum_format_is_error));
                    login_et_phone.requestFocus();
                    return;
                }

                mLoginPresenter.getVerificationCod(phoneNumber);
                break;
        }

    }

    public void resetCheckNum() {
        mHandler.removeMessages(0);
        NUM = Constants.CUTDOWN;
        login_tv_get_verification_code.setText("获取验证码");
        login_tv_get_verification_code.setTextColor(getResources().getColor(R.color.text_title_red));
        login_tv_get_verification_code.setEnabled(true);
    }

    @Override
    public void getVerificationCodSuccessful() {
        login_tv_get_verification_code.setTextColor(Color.rgb(0xbe, 0xbe, 0xbe));
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void getVerificationCodFailure(String result) {
        showToast("获取验证码失败");
        Log.e("TAG", result);
    }

    @Override
    public void loginSuccessful(LoginEntity entity) {
        preferenceCommit("isLogin", true);
        preferenceCommit("token", entity.getToken());
        preferenceCommit("username", entity.getPhoneNumber());

        showToast("登录成功");
        jumpToMain();
    }

    /**
     * 跳转至主界面
     */
    private void jumpToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailure(String result) {
        showToast("登录失败");
        Log.e("TAG", result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
    }
}
