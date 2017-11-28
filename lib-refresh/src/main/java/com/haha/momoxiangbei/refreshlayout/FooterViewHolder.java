package com.haha.momoxiangbei.refreshlayout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.haha.momoxiangbei.lib_refresh.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class FooterViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout container;
    public ProgressBar progressBar;
    public TextView infoText;
    public View bottomView;

    public FooterViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.loading_progress);
        infoText = itemView.findViewById(R.id.info_text);
        container = itemView.findViewById(R.id.loading_root);
        bottomView = itemView.findViewById(R.id.bottom_view);
    }

    /**
     * 设置加载状态
     *
     * @param state 可在LoadState类中扩展
     */
    public void setState(LoadState state) {
        if (container == null || progressBar == null || infoText == null || bottomView == null) {
            return;
        }
        if (state == null) {
            container.setVisibility(GONE);
            return;
        }
        switch (state) {
            case GONE:   // 隐藏FooterViewHolder
                container.setVisibility(GONE);
                break;

            case LOADING: // 加载中
                container.setVisibility(VISIBLE);
                progressBar.setVisibility(VISIBLE);
                infoText.setText(itemView.getContext().getString(R.string.footer_loading));
                break;

            case END:     // 加载到底部
                container.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                infoText.setText(itemView.getContext().getString(R.string.footer_end));
                break;

            case FAILED:  // 加载失败,点击加载更多
                container.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                infoText.setText(itemView.getContext().getString(R.string.footer_failed));
                break;

            default:      // 默认隐藏Footer
                container.setVisibility(GONE);
                break;
        }
    }


}