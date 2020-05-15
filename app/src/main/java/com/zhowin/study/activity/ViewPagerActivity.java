package com.zhowin.study.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.zhowin.study.R;
import com.zhowin.study.adapter.HomePageAdapter;
import com.zhowin.study.adapter.ThatSnapHelperItemAdapter;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.fragment.HomeListFragment;
import com.zhowin.study.model.ThatMessageList;
import com.zhowin.study.view.CustomSnapHelper;
import com.zhowin.study.view.VerticalPageTransformer;
import com.zhowin.study.view.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends BaseActivity {

    private VerticalViewPager viewPager;
    private RecyclerView recyclerView;


    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_view_pager;
    }

    @Override
    public void bindViews(View contentView) {
        viewPager = get(R.id.viewPager);
        recyclerView = get(R.id.recyclerView);
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {
        setViewPagerData();

//        setRecyclerViewData();

    }

    private void setViewPagerData() {
        viewPager.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        List<Fragment> mFragments = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        titleList.add("同城");
        titleList.add("推荐");
        titleList.add("同城");
        titleList.add("小队");
        titleList.add("推荐");
        titleList.add("同城");
        titleList.add("小队");
        titleList.add("同城");
        titleList.add("小队");
        for (int i = 0; i < titleList.size(); i++) {
            mFragments.add(HomeListFragment.newInstance(i));
        }

        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, titleList);
        viewPager.setAdapter(homePageAdapter);
        viewPager.setPageTransformer(false, new VerticalPageTransformer());
    }

    @SuppressLint("WrongConstant")
    private void setRecyclerViewData() {
        viewPager.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        List<ThatMessageList> thatMessageList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            thatMessageList.add(new ThatMessageList(1, "测试的第" + i + "条"));
        }
        ThatSnapHelperItemAdapter thatSnapHelperItemAdapter = new ThatSnapHelperItemAdapter(thatMessageList);
//        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
//        linearSnapHelper.attachToRecyclerView(SnapHelperRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false));

        CustomSnapHelper mMySnapHelper = new CustomSnapHelper();
        mMySnapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(thatSnapHelperItemAdapter);
    }

    @Override
    public void setClickListener(View view) {

    }
}
