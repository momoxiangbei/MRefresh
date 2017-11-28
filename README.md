# MRefresh

支持RecyclerView的上拉下拉刷新，非入侵式，可以灵活自定义。

MSwipeRefreshLayout为扩展的SwipeRefreshLayout，可以无缝切换google的SwipeRefreshLayout

1. 支持RecyclerView，ListView，ScrollView，GridView等等
2. 可自定义HeaderView和FooterView
3. 可以设设置目标View跟随手指滑动(默认)，也可以设置为不跟随setTargetScrollWithLayout(false)
4. 可以根据下拉过程中distance的值做一系列动画

FooterViewHolder是上拉加载更多的FooterView 简化了Adapter中FooterView的添加，非入侵式，可以随意修改样式

1. 通过setState设置上拉刷新的各种状态
2. 可在LoadState中扩展上拉刷新的状态

具体使用可参照demo


