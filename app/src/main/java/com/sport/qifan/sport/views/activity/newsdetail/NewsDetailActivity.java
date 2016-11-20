package com.sport.qifan.sport.views.activity.newsdetail;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.utils.LoggerUtil;
import com.sport.qifan.sport.utils.ToastUtil;
import com.sport.qifan.sport.views.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/17.
 */

public class NewsDetailActivity extends BaseActivity implements NewsDetailView {
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    private NewsDetailPrensenter newsDetailPrensenter=new NewsDetailPrensenter(this);
    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newsdetail_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(NewsDetailActivity.this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        collapsingToolbarLayout.setTitle("Title");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newsdetail_activity, menu);
        return true;
    }

    @Override
    protected void initEvents() {
        /**
         * 用于监听toolbar 上的like button
         */
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_liked) {
                    if (item.isChecked()) {
                        item.setIcon(R.drawable.disliked);
                        item.setChecked(false);
                    } else {
                        item.setIcon(R.drawable.liked);
                        item.setChecked(true);
                    }
                }
                return true;
            }
        });

    }

    /**
     * 用于监听Toolbar上的点击监听
     * 在Toolbar上的左上角的返回箭头的键值为Android.R.id.home，切记为Android.R.id.home，而不是R.id.home，否则可能监听不到左上角监听的点击事件
     *
     * @param item {@link MenuItem} that was clicked
     * @return <code>true</code> if the event was handled, <code>false</code> otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgessBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
