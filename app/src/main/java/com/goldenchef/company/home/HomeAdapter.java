package com.goldenchef.company.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldenchef.company.R;
import com.goldenchef.company.entities.ResumeEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luo-hao on 2017-03-04.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private List<ResumeEntity> mDatas = null;
    private LayoutInflater mInflater = null;
    private View.OnClickListener mOnClickListener;
    private Context mContext;

    public HomeAdapter(Context context, List<ResumeEntity> datas) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDatas = datas;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View contentView = mInflater.inflate(R.layout.home_listview_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(contentView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDatas == null || mDatas.size() <= 0)
            return;
        ResumeEntity resumeEntity = mDatas.get(0);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.home_item_tv_name.setText(resumeEntity.getName().equals("") ? "王小明" : resumeEntity.getName()); //名字
        viewHolder.home_item_tv_detail.setText(String.format(mContext.getString(R.string.resume_detail)
                , resumeEntity.getSex().equals("") ? "男" : resumeEntity.getSex()
                , resumeEntity.getAge().equals("") ? "18" : resumeEntity.getAge()
                , resumeEntity.getEducation().equals("") ? "高中" : resumeEntity.getEducation()
                , resumeEntity.getWorkYear().equals("") ? "3" : resumeEntity.getWorkYear()));

        viewHolder.home_item_tv_date.setText(resumeEntity.getCreateTime()); //创建时间
        int salary = Integer.parseInt(resumeEntity.getSalaryExpected()); //期望薪资
        String salaryStr = null;
        if (salary % 1000 != 0) {
            float salaryF = (float) salary / 1000;
            salaryStr = String.format(mContext.getString(R.string.salary_f), salaryF);
        } else {
            salaryStr = String.format(mContext.getString(R.string.salary), (salary / 1000));
        }
        viewHolder.home_item_tv_salary.setText(salaryStr);
        viewHolder.home_item_tv_excepted_job.setText(String.format(mContext.getString(R.string.excepted_job), resumeEntity.getJobExpectedStr())); //期望工作

        if (mOnClickListener != null) {
            viewHolder.home_item_layout.setTag(position);
            viewHolder.home_item_layout.setOnClickListener(mOnClickListener);
        }

    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_item_layout)
        View home_item_layout;
        @BindView(R.id.home_item_tv_date)
        TextView home_item_tv_date;
        @BindView(R.id.home_item_tv_excepted_job)
        TextView home_item_tv_excepted_job;
        @BindView(R.id.home_item_iv)
        ImageView home_item_iv;
        @BindView(R.id.home_item_tv_salary)
        TextView home_item_tv_salary;
        @BindView(R.id.home_item_tv_name)
        TextView home_item_tv_name;
        @BindView(R.id.home_item_tv_other)
        TextView home_item_tv_other;
        @BindView(R.id.home_item_tv_detail)
        TextView home_item_tv_detail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
