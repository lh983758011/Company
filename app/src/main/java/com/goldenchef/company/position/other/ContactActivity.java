package com.goldenchef.company.position.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseActivity;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

public class ContactActivity extends BaseActivity {

    public static final int CONTACT_RESULT_CODE = 0x0004;
    public static final String CONTENT = "contact_content";
    private Intent mIntent;

    @BindView(R.id.contact_tb)
    Toolbar contact_tb;

    @BindView(R.id.contact_et_number)
    AppCompatEditText contact_et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {
        mIntent = getIntent();

        String content = mIntent.getStringExtra(CONTENT);
        if (!Utils.isEmpty(content)) {
            contact_et_number.setText(content);
            contact_et_number.setSelection(content.length());
        }

    }

    @Override
    protected void initView() {
        contact_tb.setTitle("");
        setSupportActionBar(contact_tb);
    }

    @OnClick({R.id.contact_btn_back, R.id.contact_btn_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.contact_btn_back:
                onBackPressed();
                break;
            case R.id.contact_btn_save:
                //保存
                String content = contact_et_number.getText().toString().trim();
                if (Utils.isEmpty(content)) {
                    showToast(getString(R.string.please_input_content));
                    return;
                }

                mIntent.putExtra(CONTENT, content);
                setResult(CONTACT_RESULT_CODE, mIntent);
                finish();
                break;
        }
    }
}
