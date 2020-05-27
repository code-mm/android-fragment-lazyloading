package com.example.maohuawei.fragmentlazyloading;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by maohuawei on 2018/3/2.
 */

public abstract class BaseFragment extends Fragment {

    /*标志位 判断数据是否初始化*/
    private boolean isInitData = false;
    /*标志位 判断fragment是否可见*/
    private boolean isVisibleToUser = false;
    /*标志位 判断view已经加载完成 避免空指针操作*/
    private boolean isPrepareView = false;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepareView = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayout(), container, false);

        intiView(view);

        return view;
    }

    protected abstract void intiView(View view);

    /*懒加载方法*/
    private void lazyInitData() {
        if (!isInitData && isVisibleToUser && isPrepareView) {
            isInitData = true;
            initData();
        }
    }

    protected abstract void initData();

    protected abstract int getLayout();


    /*当fragment由可见变为不可见和不可见变为可见时回调*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        lazyInitData();
    }


    /*fragment生命周期中onViewCreated之后的方法 在这里调用一次懒加载 避免第一次可见不加载数据*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lazyInitData();
    }
}
