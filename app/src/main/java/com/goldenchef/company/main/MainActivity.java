package com.goldenchef.company.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseActivity;
import com.goldenchef.company.home.HomeFragment;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.message.MessageListFragment;
import com.goldenchef.company.person.PersonFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_btn_dachu)
    View main_btn_dachu;

    @BindView(R.id.main_btn_shangpu)
    View main_btn_shangpu;

    @BindView(R.id.main_btn_xiaoxi)
    View main_btn_xiaoxi;

    @BindView(R.id.main_btn_faxian)
    View main_btn_faxian;

    @BindView(R.id.main_btn_wode)
    View main_btn_wode;

    FragmentManager mFragmentManager;

    HomeFragment mHomeFragment;
    PersonFragment mPersonFragment;
    MessageListFragment mMessageListFragment;

    private Fragment mOldFragment, mCurrentFragment;

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
        mHomeFragment = new HomeFragment();
        mMessageListFragment = new MessageListFragment();
        mPersonFragment = new PersonFragment();

        switchFragment(new Fragment(), mHomeFragment);
        main_btn_dachu.setSelected(true);
    }

    @OnClick({R.id.main_btn_dachu, R.id.main_btn_shangpu, R.id.main_btn_xiaoxi, R.id.main_btn_faxian, R.id.main_btn_wode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_dachu:
                //大厨
//                StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimaryDark));
                switchFragment(mCurrentFragment, mHomeFragment);

                selectedView(main_btn_dachu);
                break;
            case R.id.main_btn_shangpu:
                //商铺

                selectedView(main_btn_shangpu);
                break;

            case R.id.main_btn_xiaoxi:
                //消息
                switchFragment(mCurrentFragment, mMessageListFragment);

                selectedView(main_btn_xiaoxi);
                break;

            case R.id.main_btn_faxian:
                //发现
                selectedView(main_btn_faxian);
                break;
            case R.id.main_btn_wode:
                //我的
//                StatusBarCompat.compat(this, getResources().getColor(R.color.title_red));
                switchFragment(mCurrentFragment, mPersonFragment);

                selectedView(main_btn_wode);
                break;

        }
    }

    /**
     * 切换Fragment
     *
     * @param from
     * @param to
     */
    private void switchFragment(Fragment from, Fragment to) {
        if (to == null)
            return;

        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
            if (!to.isAdded())
                mTransaction.hide(from).add(R.id.main_layout_content, to);
            else
                mTransaction.hide(from).show(to);
            mTransaction.commit();
        }
    }


    private void selectedView(View view) {
        clearSelected();
        view.setSelected(true);
    }

    private void clearSelected() {
        main_btn_dachu.setSelected(false);
        main_btn_shangpu.setSelected(false);
        main_btn_faxian.setSelected(false);
        main_btn_xiaoxi.setSelected(false);
        main_btn_wode.setSelected(false);
    }
}
