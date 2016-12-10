package com.sport.qifan.sport.views.activity.persondetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.custome.MyTabLayout;
import com.sport.qifan.sport.views.BaseActivity;
import com.sport.qifan.sport.views.fragment.friendrequest.FriendRequestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/12/10.
 */

public class PersonDetailActivity extends BaseActivity implements PersonDetailView {
    @BindView(R.id.view_pager)
    protected ViewPager viewPager;
    @BindView(R.id.tab_layout)
    protected MyTabLayout myTabLayout;

    private FragmentPagerAdapter adapter;
    private List<String> tabTitles;

    private List<Fragment> fragments;

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_persondetail_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(PersonDetailActivity.this);

        tabTitles = new ArrayList<>();
        tabTitles.add("tab 1");
        tabTitles.add("tab 2");
        tabTitles.add("tab 3");
        myTabLayout.setTabItems(tabTitles);
        myTabLayout.heightLightTextView(0);

        fragments = new ArrayList<>();
        fragments.add(new FriendRequestFragment());
        fragments.add(new FriendRequestFragment());
        fragments.add(new FriendRequestFragment());

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initEvents() {
        myTabLayout.setOnTabSelectedListener(new MyTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                myTabLayout.scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                myTabLayout.heightLightTextView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void showProgessBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
