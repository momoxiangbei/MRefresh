package com.haha.momoxiangbei.refreshlayout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class RecyclerViewLoadMore extends RecyclerView.OnScrollListener {

    private int currentPage = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public RecyclerViewLoadMore(LinearLayoutManager linearLayoutManager) {
        mLinearLayoutManager = linearLayoutManager;
    }

    public void resetCurrentPage() {
        currentPage = 1;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        // 屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        //当前RecyclerView的所有子项个数
        int totalItemCount = mLinearLayoutManager.getItemCount();


        if (dy > 0 && totalItemCount - lastVisibleItemPosition == 1) {
            currentPage++;
            onLoadMore(currentPage);
        }
    }

    public abstract void onLoadMore(int currentPage);
}
