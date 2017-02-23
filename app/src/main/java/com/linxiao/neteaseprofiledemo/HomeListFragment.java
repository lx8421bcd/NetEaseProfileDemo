package com.linxiao.neteaseprofiledemo;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.linxiao.neteaseprofiledemo.base.ScrollObservableFragment;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 列表信息展示所用页面
 * @author linxiao
 * @version 1.0.0
 */
public class HomeListFragment extends ScrollObservableFragment {

    private int scrolledX = 0, scrolledY = 0;


    private View contentView;

    @Bind(R.id.rcvGoodsList)
    RecyclerView rcvGoodsList;

    public HomeListFragment() {
        // Required empty public constructor
    }

    public static HomeListFragment newInstance() {
        HomeListFragment fragment = new HomeListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_home_list, container, false);
            ButterKnife.bind(this, contentView);
            initView();
        }
        return contentView;
    }

    private void initView() {
        final RCVListAdapter adapter = new RCVListAdapter(getContext());
        View header = new View(getContext());
        header.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        header.getLayoutParams().height = getResources().getDimensionPixelOffset(R.dimen.home_header_size);
        adapter.setHeader(header);

        rcvGoodsList.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvGoodsList.addItemDecoration(new HorizontalDecoration(1, new ColorDrawable(
                ContextCompat.getColor(getContext(),R.color.white_gray))));
        rcvGoodsList.setItemAnimator(new DefaultItemAnimator());
        rcvGoodsList.setAdapter(adapter);

        adapter.setDataSource(Arrays.asList("","","","","","","","","","","","","","","","","",
                "","","","",""));
        adapter.notifyDataSetChanged();

        rcvGoodsList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrolledX += dx;
                scrolledY += dy;
                if(HomeListFragment.this.isResumed()) {
                    doOnScrollChanged(scrolledX, scrolledY, dx, dy);

                }
            }
        });
    }


    @Override
    public void setScrolledY(int scrolledY) {
        if(rcvGoodsList != null) {
            if (this.scrolledY >= scrolledY) {
                int scrollDistance = (this.scrolledY - scrolledY) * -1;
                rcvGoodsList.scrollBy(0, scrollDistance);
            }
            else {
                rcvGoodsList.scrollBy(0, scrolledY);
            }
        }
    }
}
