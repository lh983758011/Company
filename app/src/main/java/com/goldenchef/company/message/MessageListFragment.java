package com.goldenchef.company.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseFragment;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.StatusBarCompat;
import com.hyphenate.easeui.EaseConstant;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by luo-hao on 2017/5/16.
 */

public class MessageListFragment extends BaseFragment {

    @BindView(R.id.message_list_tb)
    Toolbar message_list_tb;
    @BindView(R.id.message_list_rl)
    SwipeRefreshLayout message_list_rl;
    @BindView(R.id.message_list_rv)
    RecyclerView message_list_rv;

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

        showContent();
    }

    @Override
    public void initData() {

        final String[] names = {"18981907025", "18200388511"};

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        message_list_rv.setLayoutManager(manager);
        MessageListAdapter messageListAdapter = new MessageListAdapter(mContext, Arrays.asList(names));
        message_list_rv.setAdapter(messageListAdapter);
        messageListAdapter.setOnItemClickListener(new MessageListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, names[position]);
                startActivity(intent);
            }
        });

    }
}
