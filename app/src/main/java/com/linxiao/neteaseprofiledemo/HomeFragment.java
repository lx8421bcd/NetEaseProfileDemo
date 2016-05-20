package com.linxiao.neteaseprofiledemo;


import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.linxiao.neteaseprofiledemo.base.BaseFragment;
import com.linxiao.neteaseprofiledemo.base.ScrollObservableFragment;
import com.linxiao.neteaseprofiledemo.widget.CusSwipeRefreshLayout;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页
 * <p>负责首页显示逻辑</p>
 * <p>创建时间: 2016-4-21</p>
 *
 * @author 洛克
 * @version 1.0.0
 */
public class HomeFragment extends BaseFragment {

    private View contentView;

    @Bind(R.id.srlRefresh)
    CusSwipeRefreshLayout srlRefresh;

    @Bind(R.id.rlHead)
    RelativeLayout rlHead;

    @Bind(R.id.rlNavBar)
    RelativeLayout rlNavBar;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.tabStrip)
    PagerSlidingTabStrip tabStrip;

    @Bind(R.id.llHeader)
    LinearLayout llHeader;

    int slidingDistance;

    int currScrollY = 0;

    int currentPosition = 0;

    private List<ScrollObservableFragment> displayFragments;
    private List<String> displayPageTitles = Arrays.asList("热门", "专辑", "MV", "歌手信息");
    private GradientDrawable gd;
    private float kSearchViewColor;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_home, container, false);
            ButterKnife.bind(this, contentView);

            initView(savedInstanceState);
        }
        initSlidingParams();
        return contentView;
    }

    private void initView(Bundle savedInstanceState) {

        rlHead.bringToFront();

        displayFragments = new ArrayList<>();
        for (int i = 0; i < displayPageTitles.size() - 1; i++) {
            displayFragments.add(HomeListFragment.newInstance("", ""));
        }
        displayFragments.add(new HomeProfileFragment());

        ScrollObservableFragment.OnScrollChangedListener onScrollChangedListener = new ScrollObservableFragment.OnScrollChangedListener() {

            @Override
            public void onScrollChanged(ScrollObservableFragment fragment, int scrolledX, int scrolledY, int dx, int dy) {
                if(fragment.equals(displayFragments.get(currentPosition))) {
                    scrollChangeHeader(scrolledY);
                }
            }
        };
        for (ScrollObservableFragment fragment : displayFragments) {
            fragment.setOnScrollChangedListener(onScrollChangedListener);
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return displayFragments.get(position);
            }

            @Override
            public int getCount() {
                return displayFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return displayPageTitles.get(position);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                LogcatUtils.d("HomeFragment", "position = " + position);
//                displayFragments.get(position).setScrolledY(currScrollY);
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                displayFragments.get(position).setScrolledY(currScrollY);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabStrip.setViewPager(viewPager);
        viewPager.setCurrentItem(currentPosition);
        srlRefresh.setOnInterceptTouchEventListener(new CusSwipeRefreshLayout.OnInterceptTouchEventListener() {
            @Override
            public boolean onInterceptTouchEvent(MotionEvent event) {
                return currScrollY == 0;
            }
        });
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srlRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void initSlidingParams() {
        int headerSize = getResources().getDimensionPixelOffset(R.dimen.home_header_size);
        int navBarHeight = getResources().getDimensionPixelOffset(R.dimen.nav_bar_height);
        int tabStripHeight = getResources().getDimensionPixelOffset(R.dimen.tabstrip_height);
        slidingDistance = headerSize - navBarHeight - tabStripHeight;
        Log.d("HomeFragment", "slidingDistance" + slidingDistance);
        gd = new GradientDrawable();
        gd.setCornerRadius(getResources().getDimensionPixelOffset(R.dimen.search_view_corner_radius));
        kSearchViewColor = (234 - 255) * 1.0f / slidingDistance;
    }



    private void scrollChangeHeader(int scrolledY) {
        if (scrolledY < 0) {
            scrolledY = 0;
        }
        if (scrolledY < slidingDistance) {
            rlNavBar.setBackgroundColor(Color.argb(scrolledY * 192 / slidingDistance, 0x00, 0x00, 0x00));
            int rgb = (int) (scrolledY * kSearchViewColor + 255);
            gd.setColor(Color.argb(0x80, rgb, rgb, rgb));
            llHeader.setPadding(0, -scrolledY, 0, 0);
            currScrollY = scrolledY;
        } else {
            rlNavBar.setBackgroundColor(Color.argb(192, 0x00, 0x00, 0x00));
            gd.setColor(Color.argb(0x80, 234, 234, 234));
            llHeader.setPadding(0, -slidingDistance, 0, 0);
            currScrollY = slidingDistance;
        }
    }



}