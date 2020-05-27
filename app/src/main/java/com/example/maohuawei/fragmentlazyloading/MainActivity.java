package com.example.maohuawei.fragmentlazyloading;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab_layout;

    private ViewPager vp_main;

    private List<String> title;

    private List<FragmentTemplate> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab_layout = findViewById(R.id.tab_layout);
        vp_main = findViewById(R.id.vp_main);

        title = new ArrayList<>();

        title.add("1");
        title.add("2");
        title.add("3");
        title.add("4");
        title.add("5");

        fragments = new ArrayList<>();


        fragments.add(new FragmentTemplate());
        fragments.add(new FragmentTemplate());
        fragments.add(new FragmentTemplate());
        fragments.add(new FragmentTemplate());
        fragments.add(new FragmentTemplate());

        vp_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        tab_layout.setupWithViewPager(vp_main);


    }
}
