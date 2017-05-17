package com.goldenchef.company.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldenchef.company.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luo-hao on 2017/5/17.
 */

public class MessageListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mDatas;
    private OnItemClickListener mOnItemClickListener;

    public MessageListAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        View view = View.inflate(mContext, R.layout.layout_message_list_item, null);

        return new MessageItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        final MessageItemViewHolder itemViewHolder = (MessageItemViewHolder) viewHolder;

        itemViewHolder.message_item_tv_name.setText(mDatas.get(position));

        itemViewHolder.message_item_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(itemViewHolder.message_item_ll, position);
            }
        });
    }

    public class MessageItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message_item_ll)
        View message_item_ll;
        @BindView(R.id.message_item_img_header)
        ImageView message_item_img_header;
        @BindView(R.id.message_item_tv_name)
        TextView message_item_tv_name;
        @BindView(R.id.message_item_tv_time)
        TextView message_item_tv_time;

        public MessageItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
