package com.goldenchef.company.position.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.goldenchef.company.BaseActivity;
import com.goldenchef.company.R;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 职位描述
 */
public class PositionDescriptionActivity extends BaseActivity {

    public static final int POSITION_DESCRIPTION_RESULT_CODE = 0x0005;
    public static final String CONTENT = "position_description_content";

    @BindView(R.id.position_description_tb)
    Toolbar position_description_tb;

    @BindView(R.id.position_description_et_description)
    AppCompatEditText position_description_et_description;

    @BindView(R.id.position_description_tv_num)
    AppCompatTextView position_description_tv_num;

    private Intent mIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_description);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {
        mIntent = getIntent();
        String content = mIntent.getStringExtra(CONTENT);
        if (!Utils.isEmpty(content)){
            position_description_et_description.setText(content);
            position_description_et_description.setSelection(content.length());
        }
    }

    @Override
    protected void initView() {
        position_description_tb.setTitle("");
        setSupportActionBar(position_description_tb);

        position_description_et_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence content, int start, int before, int count) {
                position_description_tv_num.setText(String.format(getString(R.string.number_of_content), String.valueOf(content.length()), String.valueOf(999)));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.position_description_btn_back, R.id.position_description_btn_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.position_description_btn_back:
                onBackPressed();
                break;
            case R.id.position_description_btn_save:
                //保存
                String content = position_description_et_description.getText().toString().trim();
                if (Utils.isEmpty(content)){
                    showToast(getString(R.string.please_input_content));
                    return;
                }
                mIntent.putExtra(CONTENT, content);

                setResult(POSITION_DESCRIPTION_RESULT_CODE, mIntent);
                finish();
                break;
        }
    }
}
