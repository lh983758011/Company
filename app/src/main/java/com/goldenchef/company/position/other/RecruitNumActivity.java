package com.goldenchef.company.position.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.goldenchef.company.BaseActivity;
import com.goldenchef.company.R;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 招聘人数
 */
public class RecruitNumActivity extends BaseActivity {

    @BindView(R.id.recruit_num_tb)
    Toolbar recruit_num_tb;

    @BindView(R.id.recruit_num_et_num)
    AppCompatEditText recruit_num_et_num;

    public static final int RECRUIT_NUM_RESULT_CODE = 0x0003;
    public static final String CONTENT = "recruit_num_content";

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit_num);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {
        mIntent = getIntent();
        String content = mIntent.getStringExtra(CONTENT);
        if (!Utils.isEmpty(content)){
            recruit_num_et_num.setText(content);
            recruit_num_et_num.setSelection(content.length());
        }
    }

    @Override
    protected void initView() {
        recruit_num_tb.setTitle("");
        setSupportActionBar(recruit_num_tb);
    }

    @OnClick({R.id.recruit_num_btn_back, R.id.recruit_num_btn_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.recruit_num_btn_back:
                onBackPressed();
                break;
            case R.id.recruit_num_btn_save:
                //保存
                String content = recruit_num_et_num.getText().toString().trim();
                if (Utils.isEmpty(content))
                {
                    showToast(getString(R.string.please_input_content));
                    return;
                }

                mIntent.putExtra(CONTENT, content);
                setResult(RECRUIT_NUM_RESULT_CODE, mIntent);
                finish();

                break;
        }
    }
}
