package com.example.maohuawei.fragmentlazyloading;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by maohuawei on 2018/3/2.
 */

public class FragmentTemplate extends BaseFragment {

    private Handler handler = new Handler();
    private List<String> list = new ArrayList<>();

    private RecyclerView rv_view;
    private RVAdapter adapter;


    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void intiView(View view) {
        rv_view = view.findViewById(R.id.rv_view);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

    }

    @Override
    protected void initData() {

        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                list.clear();
                list.add("皑如山上雪");
                list.add("皎若云间月");
                list.add("闻君有两意");
                list.add("故来相决绝");
                list.add("今日斗酒会");
                list.add("明日沟水头");
                list.add("躞蹀御沟上");
                list.add("沟水东西流");
                list.add("凄凄复凄凄");
                list.add("嫁娶不须啼");
                list.add("愿得一人心");
                list.add("白首不相离");
                list.add("竹竿何袅袅");
                list.add("鱼尾何簁簁");
                list.add("男儿重意气");
                list.add("何用钱刀为");

                adapter.notifyDataSetChanged();
            }
        }, 1500);


    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_template;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new RVAdapter(getContext(), list);

        rv_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        rv_view.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(() -> {


                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });

                }).start();

            }
        });
    }
}
