package com.goldenchef.company;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldenchef.company.entities.PositionEntity;

import java.util.List;

/**
 * @author luo_hao
 * @pram 修改日期
 * @date 创建日期：2017/3/3
 */
public class ManagePositionAdapter extends RecyclerView.Adapter {

    private static final int TYPE_0 = 0;
    private static final int TYPE_1 = 1;

    private LayoutInflater mInflater;
    private List<PositionEntity> mDatas = null;

    public ManagePositionAdapter(Context context, List<PositionEntity> datas) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDatas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas != null && mDatas.size() > 0){
            PositionEntity positionEntity = mDatas.get(position);
            if (positionEntity.getType() == 0){
                return TYPE_0;
            }else if (positionEntity.getType() == 1){
                return TYPE_1;
            }
        }

        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == TYPE_0) {
            View contentView = mInflater.inflate(R.layout.first_listview_item_layout, parent, false);
            viewHolder = new ViewHolder1(contentView);
        } else if (viewType == TYPE_1) {
            View contentView = mInflater.inflate(R.layout.first_listview_item_2_layout, parent, false);
            viewHolder = new ViewHolder2(contentView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1) {

        } else if (holder instanceof ViewHolder2) {

        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {

        public ViewHolder1(View itemView) {
            super(itemView);
        }


    }

    class ViewHolder2 extends RecyclerView.ViewHolder {

        public ViewHolder2(View itemView) {
            super(itemView);
        }


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
