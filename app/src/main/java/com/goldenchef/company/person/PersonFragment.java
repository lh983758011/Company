package com.goldenchef.company.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.goldenchef.company.PositionManagerActivity;
import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseFragment;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.login.SignOutActivity;
import com.goldenchef.company.position.EditPositionActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonFragment extends BaseFragment {

    @BindView(R.id.person_tb)
    Toolbar person_tb;

    @BindView(R.id.person_layout_post_position)
    View person_layout_post_position;

    @BindView(R.id.person_layout_manage_position)
    View person_layout_manage_position;

   public static PersonFragment newInstance(){
       PersonFragment instance = new PersonFragment();
       return instance;
   }

    @Override
    public int initContentView() {
        return R.layout.activity_person;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void initUI() {
        showContent();

        person_tb.setTitle("");
//        person_tb.setPadding(0, StatusBarCompat.getStatusBarHeight(mContext), 0, 0);

        ((AppCompatActivity)mContext).setSupportActionBar(person_tb);

        person_tb.setElevation(0);
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.person_layout_post_position, R.id.person_layout_manage_position, R.id.person_layout_check_chef, R.id.person_layout_check_invite_chef
    ,R.id.person_btn_setting})
    public void onClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.person_layout_post_position:
                //发布职位
                intent = new Intent(mContext, EditPositionActivity.class);
                mContext.startActivity(intent);


                break;
            case R.id.person_layout_manage_position:
                //职位管理
                intent = new Intent(mContext, PositionManagerActivity.class);
                mContext.startActivity(intent);

                break;
            case R.id.person_layout_check_chef:
                break;
            case R.id.person_layout_check_invite_chef:
                break;
            case R.id.person_btn_setting:
                //设置
                Intent intent1 = new Intent(mContext, SignOutActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
