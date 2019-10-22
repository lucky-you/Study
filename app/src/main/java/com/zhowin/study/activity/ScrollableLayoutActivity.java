package com.zhowin.study.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.zhowin.study.R;
import com.zhowin.study.adapter.HomePageAdapter;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.fragment.HomeListFragment;

import java.util.ArrayList;
import java.util.List;

public class ScrollableLayoutActivity extends BaseActivity {
    private SlidingTabLayout slidingTabLayout;
    private ViewPager noScrollViewPager;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private HomePageAdapter homePageAdapter;

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_scrollable_layout;
    }

    @Override
    public void bindViews(View contentView) {
        slidingTabLayout = get(R.id.slidingTabLayout);
        noScrollViewPager = get(R.id.noScrollViewPager);
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {
        titleList.add("推荐");
        titleList.add("同城");
        titleList.add("小队");
        for (int i = 0; i < titleList.size(); i++) {
            mFragments.add(HomeListFragment.newInstance(i));
        }
        homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, titleList);
        noScrollViewPager.setAdapter(homePageAdapter);
        slidingTabLayout.setViewPager(noScrollViewPager);
        noScrollViewPager.setOffscreenPageLimit(mFragments.size());
    }

    @Override
    public void setClickListener(View view) {

    }
}
