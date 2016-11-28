package com.sport.qifan.sport.views.fragment.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ExpandableListView;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.model.module.SportsFirstClass;
import com.sport.qifan.sport.views.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by qifan on 2016/11/11.
 */

public class HomeFragment extends BaseFragment implements HomeFragView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.expandable_list)
    protected ExpandableListView expandableListView;
    @BindView(R.id.home_swipe_container)
    protected SwipeRefreshLayout homeContainer;

    private HomeFragPresenter mPresenter = new HomeFragPresenter(this);
    private MyExpandableListAdpater adapter;

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_layout;
    }

    @Override
    public void initDatas() {
        mPresenter.init(getActivity());
        adapter = new MyExpandableListAdpater(getActivity());
        expandableListView.setAdapter(adapter);
        homeContainer.setColorSchemeColors(android.R.color.holo_red_light,
                R.color.holo_orange_dark,
                R.color.holo_yellow_dark,
                android.R.color.holo_green_light,
                R.color.holo_teal_light,
                R.color.holo_blue_light,
                R.color.holo_purple_light);
        homeContainer.setRefreshing(false);
        mPresenter.getSportEventsTest();
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void refreshList(List<SportsFirstClass> sportsList) {
        adapter.setSportTypes(sportsList);
//        adapter.refresh();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgessBar() {

    }

    @Override
    public void hideProgressBar() {

    }


}
