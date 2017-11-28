package com.haha.momoxiangbei.mrefreshlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haha.momoxiangbei.refreshlayout.FooterViewHolder;
import com.haha.momoxiangbei.refreshlayout.LoadState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xueying on 2017/11/28.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NORMAL = 1;
    private static final int FOOTER = 2;

    private List<String> mDataList = new ArrayList<>();
    private LoadState mLoadState;
    private Context mContext;

    public MyAdapter(Context context, List<String> mDataList) {
        mContext = context;
        this.mDataList = mDataList;
    }

    public void setData(ArrayList<String> data) {
        mDataList = data;
        notifyDataSetChanged();
    }

    /**
     * 更新底部加载状态
     *
     * @param state
     */
    public void setLoadState(LoadState state) {
        mLoadState = state;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        } else if (viewType == FOOTER) {
            return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.loading_more, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).textView.setText(mDataList.get(position));
        } else if (holder instanceof FooterViewHolder) {
            if (position == 0) {
                ((FooterViewHolder) holder).setState(LoadState.GONE);
            }
            ((FooterViewHolder) holder).setState(mLoadState);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER;
        } else {
            return NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 1 : mDataList.size() + 1;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item);
        }
    }
}
