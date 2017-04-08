package com.goldenchef.company;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.goldenchef.company.injector.component.AppComponent;

import butterknife.ButterKnife;

/**
 * Created by luo-hao on 2017-03-06.
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mContext;
    private TextView tvLoading;
    private View mContentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup main = (ViewGroup) inflater.inflate(R.layout.epf_layout, container, false);

        View content = onCreateContentView(inflater);
        View empty = onCreateContentEmptyView(inflater);
        View progress = onCreateProgressView(inflater);

        mContentView = main;

        replaceViewById(main, R.id.epf_content, content);
        replaceViewById(main, R.id.epf_empty, empty);
        replaceViewById(main, R.id.epf_progress, progress);

        return main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getBundle(getArguments());
        initInjector(MyApplication.get(mContext).getAppComponent());
        initUI();
        initData();
    }

    public View onCreateContentView(LayoutInflater inflater){
        return inflater.inflate(initContentView(), null);
    }

    public View onCreateContentEmptyView(LayoutInflater inflater) {
        View empty = inflater.inflate(R.layout.empty_view_layout, null);
        empty.findViewById(R.id.btnReload).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                onReloadClicked();
            }
        });
        return empty;
    }

    public View onCreateProgressView(LayoutInflater inflater) {
        View loading = inflater.inflate(R.layout.loading_view_layout, null);
        return loading;

    }

    private void replaceViewById(ViewGroup container, int viewId, View newView) {
        if (newView == null) {
            return;
        }
        newView.setId(viewId);
        View oldView = container.findViewById(viewId);
        int index = container.indexOfChild(oldView);
        container.removeView(oldView);
        container.addView(newView, index);

        newView.setVisibility(View.GONE);
    }

    public abstract int initContentView();

    /**
     * 得到Activity传进来的值
     */
    public abstract void getBundle(Bundle bundle);

    public abstract void initInjector(AppComponent appComponent);

    public abstract void initUI();

    public abstract void initData();

    public void setLoadingText(String text) {
        tvLoading.setText(text);
    }

    public void onReloadClicked() {

    }

    public void showContent() {
        dismissAll();
        mContentView.findViewById(R.id.epf_content).setVisibility(View.VISIBLE);
    }

    public void showEmpty() {
        dismissAll();
        mContentView.findViewById(R.id.epf_empty).setVisibility(View.VISIBLE);
    }

    public void showProgress() {
        dismissAll();
        mContentView.findViewById(R.id.epf_progress).setVisibility(View.VISIBLE);
    }

    private void dismissAll(){
        mContentView.findViewById(R.id.epf_content).setVisibility(View.GONE);
        mContentView.findViewById(R.id.epf_empty).setVisibility(View.GONE);
        mContentView.findViewById(R.id.epf_progress).setVisibility(View.GONE);
    }

    protected void showToast(String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_LONG).show();
    }

}
