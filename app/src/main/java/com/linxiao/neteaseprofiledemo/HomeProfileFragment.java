package com.linxiao.neteaseprofiledemo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;


import com.linxiao.neteaseprofiledemo.base.ScrollObservableFragment;
import com.linxiao.neteaseprofiledemo.widget.ObservableScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 歌手信息展示所用页面
 *
 * @author linxiao
 * @version 1.0.0
 */
public class HomeProfileFragment extends ScrollObservableFragment {

    private View contentView;

    @Bind(R.id.osvHomeRecommend)
    ObservableScrollView osvHomeRecommend;

    public HomeProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_home_profile, container, false);
            ButterKnife.bind(this, contentView);
            initView();
        }
        return contentView;
    }

    private void initView() {

        osvHomeRecommend.setOnScrollChangedListener(new ObservableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView scrollView, int scrolledX, int scrolledY, int dx, int dy) {
                doOnScrollChanged(scrolledX, scrolledY, dx, dy);
            }
        });

    }

    @Override
    public void setScrolledY(int scrolledY) {
        if (osvHomeRecommend != null) {
            osvHomeRecommend.scrollTo(0, scrolledY);

        }
    }
}
