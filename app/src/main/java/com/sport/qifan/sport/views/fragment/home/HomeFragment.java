package com.sport.qifan.sport.views.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.model.module.SportsFirstClass;
import com.sport.qifan.sport.model.module.SportsSecondClass;
import com.sport.qifan.sport.utils.LoggerUtil;
import com.sport.qifan.sport.views.BaseFragment;
import com.sport.qifan.sport.views.activity.eventdetail.EventDetailActivity;

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

    private static final int REFRESH_COMPLETE = 0X110;
    private HomeFragPresenter mPresenter = new HomeFragPresenter(this);
    private MyExpandableListAdpater adapter;

    private Handler homehandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    homeContainer.setRefreshing(false);
                    break;
            }
        }
    };

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
        homeContainer.setOnRefreshListener(this);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
               // LoggerUtil.d("ffff","OK click");
                mPresenter.goToEventDetail(groupPosition, childPosition);
                return false;
            }
        });
    }

    /**
     * when we use OnRefreshListner we use callback OnRefresh methode
     */
    @Override
    public void onRefresh() {
        //这里用presenter去请求服务器
        homehandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }

    @Override
    public void refreshList(List<SportsFirstClass> sportsList) {
        adapter.setSportTypes(sportsList);
//        adapter.refresh();
        adapter.notifyDataSetChanged();
    }

    /**
     * go to pages of event detail
     *
     * @param item
     */
    @Override
    public void goToEventDetail(SportsSecondClass item) {
    //    LoggerUtil.d("tttt","OK done");
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", item);
        intent.putExtras(bundle);
        intent.setClass(getActivity(), EventDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgessBar() {
        homeContainer.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        homeContainer.setRefreshing(false);
    }


}
