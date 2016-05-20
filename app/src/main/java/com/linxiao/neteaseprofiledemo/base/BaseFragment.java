package com.linxiao.neteaseprofiledemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 *
 * @author linxiao
 * @version 1.0
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.setRetainInstance(true);
    }


    /**
     * 通过此方法简化绑定View流程,避免每次都写强制转型
     * */
    protected <T extends View> T findView(View layoutView, int resId) {
        return (T) layoutView.findViewById(resId);
    }



}
