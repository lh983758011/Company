package com.goldenchef.company.message;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseFragment;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.StatusBarCompat;

import butterknife.BindView;

/**
 * Created by luo-hao on 2017/5/16.
 */

public class MessageListFragment extends BaseFragment {

    @BindView(R.id.message_list_tb)
    Toolbar message_list_tb;

    public MessageListFragment() {

    }

    public static MessageListFragment newInstance() {
        return new MessageListFragment();
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_message_list;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void initUI() {
        message_list_tb.setTitle("");
        message_list_tb.setPadding(0, StatusBarCompat.getStatusBarHeight(mContext), 0, 0);
        ((AppCompatActivity) mContext).setSupportActionBar(message_list_tb);
    }

    @Override
    public void initData() {

    }
}
