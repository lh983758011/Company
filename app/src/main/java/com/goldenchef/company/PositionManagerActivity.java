package com.goldenchef.company;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.goldenchef.company.entities.PositionEntity;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.widgets.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * 职位管理
 */
public class PositionManagerActivity extends BaseActivity {


    private ManagePositionAdapter mAdapter = null;
    private List<PositionEntity> mPositionEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_manager);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setType(0);
        positionEntity.setName("");
        mPositionEntities.add(positionEntity);

        positionEntity = new PositionEntity();
        positionEntity.setType(1);
        positionEntity.setName("");
        mPositionEntities.add(positionEntity);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.manage_tb);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.manage_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mAdapter= new ManagePositionAdapter(this, mPositionEntities);
        recyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.position_manager_btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.position_manager_btn_back:
                //返回
                onBackPressed();
                break;
        }
    }

}
