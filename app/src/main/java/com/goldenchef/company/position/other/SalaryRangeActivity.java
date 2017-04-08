package com.goldenchef.company.position.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;

import com.goldenchef.company.BaseActivity;
import com.goldenchef.company.R;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 薪资范围
 */
public class SalaryRangeActivity extends BaseActivity {

    public static final int SALARY_RANGE_RESULT_CODE = 0x0001;
    public static final String CONTENT ="salary_range";

    @BindView(R.id.salary_range_rg_type)
    RadioGroup salary_range_rg_type;

    @BindView(R.id.salary_range_line)
    View salary_range_line;

    @BindView(R.id.salary_range_edt_low)
    AppCompatEditText salary_range_edt_low;

    @BindView(R.id.salary_range_edt_high)
    AppCompatEditText salary_range_edt_high;

    @BindView(R.id.salary_range_tb)
    Toolbar salary_range_tb;

    private Intent mIntent;
    /**
     * 类型
     * 0，每月；1，每日；2，每时；
     */
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_range);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {
        mIntent = getIntent();

        String content = mIntent.getStringExtra(CONTENT);
        if (!Utils.isEmpty(content)){
            if (content.contains("-")){
                salary_range_rg_type.check(R.id.salary_range_rb_month);
                String[] range = content.split("/")[0].split("-");
                salary_range_edt_low.setText(range[0]);
                salary_range_edt_high.setText(range[1]);
            }else{
                if (content.contains("天")) {
                    salary_range_rg_type.check(R.id.salary_range_rb_day);
                }else if (content.contains("时")){
                    salary_range_rg_type.check(R.id.salary_range_rb_hour);
                }
                salary_range_edt_low.setText(content.split("/")[0]);
            }
        }
    }

    @Override
    protected void initView() {
        salary_range_tb.setTitle("");
        setSupportActionBar(salary_range_tb);

        salary_range_rg_type.check(R.id.salary_range_rb_month);
        salary_range_rg_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.salary_range_rb_month:
                        //每月
                        salary_range_edt_low.setVisibility(View.VISIBLE);
                        salary_range_edt_high.setVisibility(View.VISIBLE);
                        salary_range_line.setVisibility(View.VISIBLE);
                        flag = 0;
                        break;
                    case R.id.salary_range_rb_day:
                        //每天
                        salary_range_edt_high.setVisibility(View.GONE);
                        salary_range_line.setVisibility(View.GONE);
                        flag = 1;
                        break;
                    case R.id.salary_range_rb_hour:
                        //每小时
                        salary_range_edt_high.setVisibility(View.GONE);
                        salary_range_line.setVisibility(View.GONE);
                        flag = 2;
                        break;
                }
            }
        });
    }

    @OnClick({R.id.salary_range_btn_back, R.id.salary_range_btn_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.salary_range_btn_back:
                onBackPressed();
                break;
            case R.id.salary_range_btn_save:
                //保存
                if (mIntent != null){
                    StringBuilder content = new StringBuilder();

                    String lowStr = salary_range_edt_low.getText().toString().trim();

                    if (Utils.isEmpty(lowStr))
                    {
                        showToast(getString(R.string.please_input_content));
                        return;
                    }

                    content.append(lowStr);

                    if (salary_range_edt_high.getVisibility() == View.VISIBLE) {
                        String hightStr = salary_range_edt_high.getText().toString().trim();

                        if (Utils.isEmpty(hightStr))
                        {
                            showToast(getString(R.string.please_input_content));
                            return;
                        }

                        content.append("-");
                        content.append(hightStr);
                        content.append("/月");
                    }

                    if(flag == 1)
                        content.append("/天");
                    else if (flag == 2)
                        content.append("/时");

                    mIntent.putExtra(CONTENT, content.toString());

                    setResult(SALARY_RANGE_RESULT_CODE, mIntent);
                    finish();
                }

                break;
        }
    }
}
