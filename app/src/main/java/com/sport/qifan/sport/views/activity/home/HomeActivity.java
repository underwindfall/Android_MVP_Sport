package com.sport.qifan.sport.views.activity.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.media.TransportPerformer;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.custome.CommonHeadView;
import com.sport.qifan.sport.utils.ToastUtil;
import com.sport.qifan.sport.views.BaseActivity;
import com.sport.qifan.sport.views.fragment.agenda.AgendaFragment;
import com.sport.qifan.sport.views.fragment.friendrequest.FriendRequestFragment;
import com.sport.qifan.sport.views.fragment.home.HomeFragment;
import com.sport.qifan.sport.views.fragment.news.NewsFragment;
import com.sport.qifan.sport.views.fragment.personinfo.PersonInfoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/8.
 */

public class HomeActivity extends BaseActivity implements HomeView {
    @BindView(R.id.root_view)
    protected View rootView;
    @BindView(R.id.vp_home_activity_content)
    protected ViewPager mViewPager;
    //bottom tab layout
    @BindView(R.id.rg_home_bottom_tab)
    protected RadioGroup mRg;
    //toolbar
    @BindView(R.id.tool_bar)
    protected CommonHeadView mToolbar;
    @BindView(R.id.btn_creat_event)
    protected ImageView creatEventBtn;
    private List<Fragment> mfragmentList;

    private HomePresenter mPresenter = new HomePresenter(this);
    private MyFragmentPagerAdapter mAdapter;
    private int userType=1;

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(HomeActivity.this);
        initFragments();
        FragmentManager manager = getSupportFragmentManager();
        mAdapter = new MyFragmentPagerAdapter(manager, mfragmentList);
        mViewPager.setAdapter(mAdapter);

    }

    private void initFragments() {
        mfragmentList = new ArrayList<>();
        mfragmentList.add(new HomeFragment());
        //// TODO: 2016/11/14  后续填补fragment

//        mfragmentList.add(new AgendaFragment());
//        mfragmentList.add(new FriendRequestFragment());
//        mfragmentList.add(new NewsFragment());
//        mfragmentList.add(new PersonInfoFragment());
    }

    @Override
    protected void initEvents() {
        creatEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType==0){
                    mPresenter.toCreateEvent();
                }else if (userType==1){

                }else{
                    ToastUtil.show(HomeActivity.this,"异常登录",2000);
                }
            }
        });
    }

    /**
     * make right Btn visible or not
     * @param show
     */
    @Override
    public void setToolBarRightBtnVisible(boolean show) {
    mToolbar.setRightImageShow(show);
    }

    @Override
    public void hiddenToolBarAnim() {

    }

    @Override
    public void showToolBarAnim() {

    }

    /**
     *
     * get which tab btn is checked
     * @param position
     * @return
     */
    @Override
    public RadioButton getCheckedBtn(int position) {
        return (RadioButton) mRg.getChildAt(position);
    }

    @Override
    public void goToCreateEventActivity() {

    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
