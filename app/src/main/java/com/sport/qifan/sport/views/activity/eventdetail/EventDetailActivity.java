package com.sport.qifan.sport.views.activity.eventdetail;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.views.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/29.
 */

public class EventDetailActivity extends BaseActivity implements EventDetailView {
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event_detail_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(EventDetailActivity.this);
        toolbar.setTitle("Title");
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void showProgessBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
