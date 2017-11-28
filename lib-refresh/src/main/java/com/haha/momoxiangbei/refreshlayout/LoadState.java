package com.haha.momoxiangbei.refreshlayout;

/**
 * Created by yuyuan on 2017/11/25.
 */

public enum LoadState {
    GONE,      // 隐藏FooterViewHolder
    LOADING,   // 加载中
    END,       // 加载到底部
    FAILED,;   // 加载失败,点击加载更多
}
