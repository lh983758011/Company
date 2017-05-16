package com.goldenchef.company.position;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseActivity;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.position.other.ContactActivity;
import com.goldenchef.company.position.other.PositionDescriptionActivity;
import com.goldenchef.company.position.other.PositionNameActivity;
import com.goldenchef.company.position.other.RecruitNumActivity;
import com.goldenchef.company.position.other.SalaryRangeActivity;
import com.goldenchef.company.utils.PickerViewUtils;
import com.goldenchef.company.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑职位
 */
public class EditPositionActivity extends BaseActivity implements PositionContract.View {

    @BindView(R.id.edit_tb)
    Toolbar edit_tb;

    @BindView(R.id.edit_tv_experience)
    TextView edit_tv_experience;

    @BindView(R.id.edit_position_btn_commit)
    AppCompatButton edit_position_btn_commit;

    @BindView(R.id.edit_tv_age)
    TextView edit_tv_age;

    @BindView(R.id.edit_tv_company_num)
    TextView edit_tv_company_num;

    @BindView(R.id.edit_tv_work_type)
    TextView edit_tv_work_type;

    @BindView(R.id.edit_tv_position_type)
    TextView edit_tv_position_type;

    @BindView(R.id.edit_tv_position_name)
    TextView edit_tv_position_name;

    @BindView(R.id.edit_tv_salary_range)
    TextView edit_tv_salary_range;

    @BindView(R.id.edit_tv_recruit_num)
    TextView edit_tv_recruit_num;

    @BindView(R.id.edit_tv_position_description)
    TextView edit_tv_position_description;

    @BindView(R.id.edit_tv_contact)
    TextView edit_tv_contact;

    @Inject
    PublishJobPresenter mPublishJobPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_position);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {
        DaggerPositionComponent.builder().appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    protected void initData() {
        mPublishJobPresenter.attachView(this);
    }

    @Override
    protected void initView() {
        edit_tb.setTitle("");
        setSupportActionBar(edit_tb);
    }

    @OnClick({R.id.edit_btn_back, R.id.edit_layout_experience, R.id.edit_layout_age, R.id.edit_layout_company_num, R.id.edit_layout_work_type
            , R.id.edit_layout_position_type, R.id.edit_layout_position_name, R.id.edit_layout_salary_range, R.id.edit_layout_recruit_num
            , R.id.edit_layout_position_description, R.id.edit_layout_contact, R.id.edit_position_btn_commit})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.edit_btn_back:
                //返回
                onBackPressed();
                break;
            case R.id.edit_layout_experience:
                //工作经验
                showDialog(PickerViewUtils.getExperience(), edit_tv_experience);
                break;
            case R.id.edit_layout_age:
                //年龄要求
                showDialog(PickerViewUtils.getAge(), edit_tv_age);
                break;
            case R.id.edit_layout_company_num:
                //公司人数
                showDialog(PickerViewUtils.getCompanyNum(), edit_tv_company_num);
                break;
            case R.id.edit_layout_work_type:
                //工作方式
                showPopWindow();
                break;
            case R.id.edit_layout_position_type:
                //职位类型
//                intent = new Intent(this, )
                break;
            case R.id.edit_layout_position_name:
                //职位名称
                intent = new Intent(this, PositionNameActivity.class);
                String positionName = edit_tv_position_name.getText().toString();

                if (!Utils.isEmpty(positionName))
                    intent.putExtra(PositionNameActivity.CONTENT, positionName);

                startActivityForResult(intent, 0);
                break;
            case R.id.edit_layout_salary_range:
                //薪资范围
                intent = new Intent(this, SalaryRangeActivity.class);
                String salaryRange = edit_tv_salary_range.getText().toString();

                if (!Utils.isEmpty(salaryRange))
                    intent.putExtra(SalaryRangeActivity.CONTENT, salaryRange);

                startActivityForResult(intent, 0);
                break;
            case R.id.edit_layout_recruit_num:
                //招聘人数
                intent = new Intent(this, RecruitNumActivity.class);
                String recruitNum = edit_tv_recruit_num.getText().toString();

                if (!Utils.isEmpty(recruitNum))
                    intent.putExtra(RecruitNumActivity.CONTENT, recruitNum);

                startActivityForResult(intent, 0);
                break;
            case R.id.edit_layout_position_description:
                //职位描述
                intent = new Intent(this, PositionDescriptionActivity.class);
                String positionDescription = edit_tv_position_description.getText().toString();

                if (!Utils.isEmpty(positionDescription))
                    intent.putExtra(PositionDescriptionActivity.CONTENT, positionDescription);
                startActivityForResult(intent, 0);
                break;
            case R.id.edit_layout_contact:
                //联系电话
                intent = new Intent(this, ContactActivity.class);
                String contact = edit_tv_contact.getText().toString();

                if (!Utils.isEmpty(contact))
                    intent.putExtra(ContactActivity.CONTENT, contact);

                startActivityForResult(intent, 0);
                break;
            case R.id.edit_position_btn_commit:
                //发布简历
                showProgressDialog("正在提交中...");

                Map<String, String> map = new HashMap<>();
                map.put("coName", "王小二公司");
                map.put("require", "50");
                map.put("jobNameId", "9527");
                map.put("workLocation", "上海");
                map.put("paidSalary", "500-1500");
                map.put("jobWelfare", "双休");
                map.put("companyId", "8373733");
                map.put("jobContent", "什么都涉及");
                map.put("token", (String) getPreference("token"));
                map.put("interfaceAutho", "");
                map.put("companyId", "123");
                map.put("workYears", "5");
                map.put("age", "30");
                map.put("phone", "18200388621");

                mPublishJobPresenter.publishJob(map);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if (resultCode == SalaryRangeActivity.SALARY_RANGE_RESULT_CODE) {
            String salaryRange = data.getStringExtra(SalaryRangeActivity.CONTENT);
            edit_tv_salary_range.setText(salaryRange);
        } else if (resultCode == PositionNameActivity.POSITION_NAME_RESULT_CODE) {
            String positionName = data.getStringExtra(PositionNameActivity.CONTENT);
            edit_tv_position_name.setText(positionName);
        } else if (resultCode == RecruitNumActivity.RECRUIT_NUM_RESULT_CODE) {
            String recruitNum = data.getStringExtra(RecruitNumActivity.CONTENT);
            edit_tv_recruit_num.setText(recruitNum);
        } else if (resultCode == ContactActivity.CONTACT_RESULT_CODE) {
            String concact = data.getStringExtra(ContactActivity.CONTENT);
            edit_tv_contact.setText(concact);
        } else if (resultCode == PositionDescriptionActivity.POSITION_DESCRIPTION_RESULT_CODE) {
            String positionDescription = data.getStringExtra(PositionDescriptionActivity.CONTENT);
            edit_tv_position_description.setText(positionDescription);
        }

    }

    /**
     * 显示经验
     */
    private void showDialog(final List<String> datas, final TextView textView) {
        OptionsPickerView<String> mOptionsCity = new OptionsPickerView<>(new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                textView.setText(datas.get(options1));
            }
        }));
        mOptionsCity.setPicker(datas);
        mOptionsCity.show();
    }

    /**
     * 工作类型
     */
    private void showPopWindow() {
        backgroundAlpha(0.5f);
        View contentView = View.inflate(this, R.layout.layout_work_type_dialog, null);
        final PopupWindow popupWindow = new PopupWindow(contentView, Utils.getScreenWidth(this) * 9 / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);

        RadioButton work_type_all = (RadioButton) contentView.findViewById(R.id.work_type_rb_all);
        RadioButton work_type_part_time = (RadioButton) contentView.findViewById(R.id.work_type_rb_part_time);

        String work_type = edit_tv_work_type.getText().toString();
        if (!Utils.isEmpty(work_type)) {
            if (work_type.equals("全职"))
                work_type_all.setChecked(true);
            else if (work_type.equals("兼职"))
                work_type_part_time.setChecked(true);
        }

        work_type_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = ((RadioButton) v).getText().toString();
                edit_tv_work_type.setText(content);
                popupWindow.dismiss();
            }
        });
        work_type_part_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = ((RadioButton) v).getText().toString();
                edit_tv_work_type.setText(content);
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(edit_position_btn_commit, Gravity.BOTTOM, 0, 30);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void publishJobSuccessful(String result) {
        dismissProgress();
        showToast(result);
    }

    @Override
    public void publishJobFailure(String result) {
        dismissProgress();
        showToast(result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPublishJobPresenter.detachView();
    }
}
