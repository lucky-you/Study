package com.zhowin.study.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zhowin.study.R;
import com.zhowin.study.base.BaseFragment;
import com.zhowin.study.utils.ConstantValues;


public class HomeListFragment extends BaseFragment {

    private TextView tvFragmentIndex;

    private int index;

    public static HomeListFragment newInstance(int index) {
        HomeListFragment fragment = new HomeListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValues.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int loadViewLayout() {
        return R.layout.include_home_list_fragment_layout;
    }

    @Override
    public void bindViews(View contentView) {
        index = getArguments().getInt(ConstantValues.INDEX);
        tvFragmentIndex = get(R.id.tvFragmentIndex);
        tvFragmentIndex.setText("我是第" + index + "个fragment");

    }

    @Override
    public void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void setClickListener(View view) {

    }
}
