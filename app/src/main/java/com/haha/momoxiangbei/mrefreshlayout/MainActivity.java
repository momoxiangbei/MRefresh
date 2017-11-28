package com.haha.momoxiangbei.mrefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.haha.momoxiangbei.refreshlayout.LoadState;
import com.haha.momoxiangbei.refreshlayout.MSwipeRefreshLayout;
import com.haha.momoxiangbei.refreshlayout.RecyclerViewLoadMore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MSwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        refreshLayout = findViewById(R.id.refresh_layout);
        myAdapter = new MyAdapter(this, mDataList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

        // 添加headerView 自定义的下拉刷新样式
        View headerContainer = LayoutInflater.from(this).inflate(R.layout.header_layout, null, true);
        LinearLayout headerView = headerContainer.findViewById(R.id.header_view);
        refreshLayout.setHeaderView(headerView);

        // 下拉刷新
        refreshLayout.setOnRefreshListener(new MSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDataList.clear();
                        for (int i = 0; i < 10; i++) {
                            mDataList.add("item" + i);
                        }
                        myAdapter.setData(mDataList);
                        // 刷新完成，设置刷新状态为false，隐藏Footer
                        refreshLayout.setRefreshing(false);
                        myAdapter.setLoadState(LoadState.GONE);
                    }
                }, 200);
            }
        });

        // 上拉刷新
        recyclerView.addOnScrollListener(new RecyclerViewLoadMore(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {

                // 设置Footer为Loading状态
                myAdapter.setLoadState(LoadState.LOADING);

                // 400ms之后加载数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDataList.size() < 40) {
                            for (int i = 0; i < 10; i++) {
                                mDataList.add("item" + i);
                            }
                            myAdapter.setData(mDataList);
                            // 加载完成隐藏Footer
                            myAdapter.setLoadState(LoadState.GONE);
                        } else {
                            // 加载到底部，显示已经到底了
                            myAdapter.setLoadState(LoadState.END);
                        }

                    }
                }, 400);

            }
        });
    }

}
