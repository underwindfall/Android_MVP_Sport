package com.sport.qifan.sport.views.activity.choiceofsports;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.custome.NoScrollGridView;
import com.sport.qifan.sport.views.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/15.
 */

public class ChooseSportsActivity extends BaseActivity implements ChooseSportsView {

    @BindView(R.id.ll_point_container)
    protected LinearLayout mPointContainer;
    @BindView(R.id.event_type_container)
    protected ViewPager mGridViewContainer;
    @BindView(R.id.choose_sports_container)
    protected NoScrollGridView gvSportsChoose;
    @BindView(R.id.confirm_btn)
    protected Button btnConfirm;
    private ChooseSportsPresenter mPresenter=new ChooseSportsPresenter(this);
    //save each gridview
    private ArrayList<View> listGridView;

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chose_sports_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(ChooseSportsActivity.this);
        mPresenter.init(ChooseSportsActivity.this);
        listGridView=new ArrayList<>();
        mPresenter.initSportsEvents();
        mGridViewContainer.setAdapter(new MyViewPagerAdapter());
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

    /**
     *  //初始化爱好ViewPager及其下方的圆点
     */
    @Override
    public void initGirdViewsAndPoints() {

    }

    /**
     *viewpager's adapter
     */
    private class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listGridView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View gv=listGridView.get(position);
            mGridViewContainer.addView(gv);
            return gv;
        }
    }
}
