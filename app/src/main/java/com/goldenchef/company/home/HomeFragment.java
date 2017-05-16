package com.goldenchef.company.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseFragment;
import com.goldenchef.company.entities.ResumeEntity;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.SharedPreferencesUtil;
import com.goldenchef.company.utils.StatusBarCompat;
import com.goldenchef.company.widgets.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 大厨
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    @BindView(R.id.home_tb)
    Toolbar home_tb;

    @BindView(R.id.home_rv)
    RecyclerView home_rv;

    @BindView(R.id.home_rl)
    SwipeRefreshLayout home_rl;

    @BindView(R.id.home_vp_imgs)
    ViewPager home_vp_imgs;

    @BindView(R.id.home_ll_dots)
    ViewGroup home_ll_dots;

    private List<ResumeEntity> mDatas = new ArrayList<>();
    private List<View> mViews = null;
    private List<View> mDotViews = new ArrayList<>();
    private Map<String, String> paramsMap = new HashMap<>();
    private int currentPage = 0;
    private int pageSize = 5;

    private int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    private HomeAdapter mHomeAdapter;

    @Inject
    HomePresenter mHomePresenter;

    public static HomeFragment newInstance() {
        HomeFragment instance = new HomeFragment();
        return instance;
    }

    @Override
    public int initContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initInjector(AppComponent appComponent) {
        DaggerHomeComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initUI() {
        home_tb.setTitle("");
        home_tb.setPadding(0, StatusBarCompat.getStatusBarHeight(mContext), 0, 0);
        ((AppCompatActivity) mContext).setSupportActionBar(home_tb);

        createDots();
        createImageViews();
        //图片
        HomeImageAdapter homeImageAdapter = new HomeImageAdapter(mContext, mViews);
        home_vp_imgs.setAdapter(homeImageAdapter);
        home_vp_imgs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 4; i++) {
                    home_ll_dots.getChildAt(i).setSelected(false);
                }
                home_ll_dots.getChildAt(position).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mHomeAdapter = new HomeAdapter(mContext, mDatas);
        mHomeAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        home_rv.setLayoutManager(manager);
        home_rv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        home_rv.setAdapter(mHomeAdapter);

        home_rl.setColorSchemeResources(new int[]{R.color.colorAccent, R.color.colorPrimaryDark, R.color.text_title_red, R.color.orange});
        home_rl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                home_rl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        home_rl.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void initData() {
        mHomePresenter.attachView(this);

        paramsMap.put("token", (String) SharedPreferencesUtil.getPreference(mContext, "token"));
        paramsMap.put("currentPage", String.valueOf(currentPage));
        paramsMap.put("pageSize", String.valueOf(pageSize));
        paramsMap.put("id", "");

        showContent();
        home_rl.setRefreshing(true);
        mHomePresenter.selectJobs((String) SharedPreferencesUtil.getPreference(mContext, "token"), currentPage, pageSize, "");
    }


    private void createImageViews() {
        mViews = new ArrayList<>();
        ImageView imageView = null;
        for (int i = 0; i < 4; i++) {
            imageView = new ImageView(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mViews.add(imageView);
        }
    }

    private void createDots() {
        mDotViews.clear();
        ImageView imageView = null;
        for (int i = 0; i < 4; i++) {
            imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
            if (i != 3)
                params.rightMargin = 20;
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.background_dot_gray);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (i == 0)
                imageView.setSelected(true);
            home_ll_dots.addView(imageView);
        }

    }

    @Override
    public void showList(List<ResumeEntity> datas) {
        showContent();

        home_rl.setRefreshing(false);
        mDatas.addAll(datas);
        mHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDatasFailure(String message) {
        home_rl.setRefreshing(false);
        showToast(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomePresenter.detachView();
    }
}
