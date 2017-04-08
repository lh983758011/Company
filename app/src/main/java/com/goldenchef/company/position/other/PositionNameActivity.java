package com.goldenchef.company.position.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.goldenchef.company.BaseActivity;
import com.goldenchef.company.R;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 职位名称
 */
public class PositionNameActivity extends BaseActivity {

    public static final int POSITION_NAME_RESULT_CODE = 0x0002;
    public static final String CONTENT = "position_name";

    @BindView(R.id.position_name_tb)
    Toolbar position_name_tb;

    @BindView(R.id.position_name_tv_num)
    TextView position_name_tv_num;

    @BindView(R.id.position_name_et_name)
    AppCompatEditText position_name_et_name;

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_name);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {
        mIntent = getIntent();
        String positionName = mIntent.getStringExtra(CONTENT);
        if (!Utils.isEmpty(positionName)) {
            position_name_et_name.setText(positionName);
            position_name_et_name.setSelection(positionName.length());
        }
    }

    @Override
    protected void initView() {
        position_name_tb.setTitle("");
        setSupportActionBar(position_name_tb);

        position_name_et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence content, int start, int before, int count) {
                position_name_tv_num.setText(String.format(getString(R.string.number_of_content), String.valueOf(content.length()), String.valueOf(12)));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.position_name_btn_back, R.id.position_name_btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.position_name_btn_back:
                onBackPressed();
                break;
            case R.id.position_name_btn_save:
                //保存
                String positionName = position_name_et_name.getText().toString().trim();
                if (Utils.isEmpty(positionName)) {
                    showToast(getString(R.string.please_input_content));
                    return;
                }

                mIntent.putExtra(CONTENT, positionName);
                setResult(POSITION_NAME_RESULT_CODE, mIntent);
                finish();

                break;
        }
    }
}
