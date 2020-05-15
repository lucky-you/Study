package com.zhowin.study.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.zhowin.study.R;
import com.zhowin.study.adapter.ThatSnapHelperItemAdapter;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.model.ThatMessageList;

import java.util.ArrayList;
import java.util.List;


/**
 * SnapHelper 实现recyclerView 类似ViewPager的效果
 */
public class SnapHelperRecyclerViewActivity extends BaseActivity {

    private RecyclerView SnapHelperRecyclerView;
    private ThatSnapHelperItemAdapter thatSnapHelperItemAdapter;
    private List<ThatMessageList> thatMessageList = new ArrayList<>();

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_snap_helper_recycler_view;
    }

    @Override
    public void bindViews(View contentView) {
        SnapHelperRecyclerView = get(R.id.SnapHelperRecyclerView);
        for (int i = 0; i < 25; i++) {
            thatMessageList.add(new ThatMessageList(1, "测试的第" + i + "条"));
        }
        thatSnapHelperItemAdapter = new ThatSnapHelperItemAdapter(thatMessageList);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void processLogic(Bundle savedInstanceState) {
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(SnapHelperRecyclerView);

//        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
//        linearSnapHelper.attachToRecyclerView(SnapHelperRecyclerView);

        SnapHelperRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false));
        SnapHelperRecyclerView.setAdapter(thatSnapHelperItemAdapter);


    }

    @Override
    public void setClickListener(View view) {

    }
}
