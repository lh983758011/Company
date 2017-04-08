package com.goldenchef.company.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.goldenchef.company.BaseActivity;
import com.goldenchef.company.R;
import com.goldenchef.company.home.HomeFragment;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.person.PersonFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_btn_dachu)
    View main_btn_dachu;

    @BindView(R.id.main_btn_shangpu)
    View main_btn_shangpu;

    @BindView(R.id.main_btn_faxian)
    View main_btn_faxian;

    @BindView(R.id.main_btn_wode)
    View main_btn_wode;

    FragmentManager mFragmentManager;
    FragmentTransaction mTransaction;

    HomeFragment homeFragment;
    PersonFragment personFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mFragmentManager = getSupportFragmentManager();

        mTransaction = mFragmentManager.beginTransaction();
        HomeFragment homeFragment = HomeFragment.newInstance();
        mTransaction.add(R.id.main_layout_content, homeFragment);
        mTransaction.commit();

        main_btn_dachu.setSelected(true);
    }

    @OnClick({R.id.main_btn_dachu, R.id.main_btn_shangpu, R.id.main_btn_faxian, R.id.main_btn_wode})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_btn_dachu:
                //大厨
//                StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimaryDark));

                mTransaction = mFragmentManager.beginTransaction();
                if(homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                    mTransaction.add(R.id.main_layout_content, homeFragment);
                }
                hideFragment();
                mTransaction.show(homeFragment);
                mTransaction.commit();

                selectedView(main_btn_dachu);
                break;
            case R.id.main_btn_shangpu:
                //商铺

                selectedView(main_btn_shangpu);
                break;

            case R.id.main_btn_faxian:
                //发现
                selectedView(main_btn_faxian);
                break;
            case R.id.main_btn_wode:
                //我的
//                StatusBarCompat.compat(this, getResources().getColor(R.color.title_red));

                mTransaction = mFragmentManager.beginTransaction();
                if(personFragment == null) {
                    personFragment = PersonFragment.newInstance();
                    mTransaction.add(R.id.main_layout_content, personFragment);
                }
                hideFragment();
                mTransaction.show(personFragment);
                mTransaction.commit();

                selectedView(main_btn_wode);
                break;

        }
    }

    private void hideFragment(){
        if (homeFragment != null)
            mTransaction.hide(homeFragment);
        if (personFragment != null)
            mTransaction.hide(personFragment);
    }

    private void selectedView(View view){
        clearSelected();
        view.setSelected(true);
    }

    private void clearSelected(){
        main_btn_dachu.setSelected(false);
        main_btn_shangpu.setSelected(false);
        main_btn_faxian.setSelected(false);
        main_btn_wode.setSelected(false);
    }
}
