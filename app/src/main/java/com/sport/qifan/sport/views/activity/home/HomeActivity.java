package com.sport.qifan.sport.views.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.media.TransportPerformer;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.custome.CommonHeadView;
import com.sport.qifan.sport.utils.LoggerUtil;
import com.sport.qifan.sport.utils.ToastUtil;
import com.sport.qifan.sport.views.BaseActivity;
import com.sport.qifan.sport.views.activity.choiceofsports.ChooseSportsActivity;
import com.sport.qifan.sport.views.activity.createvent.CreatEventActivity;
import com.sport.qifan.sport.views.fragment.agenda.AgendaFragment;
import com.sport.qifan.sport.views.fragment.friendrequest.FriendRequestFragment;
import com.sport.qifan.sport.views.fragment.home.HomeFragment;
import com.sport.qifan.sport.views.fragment.news.NewsFragment;
import com.sport.qifan.sport.views.fragment.personinfo.PersonInfoFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/8.
 */

public class HomeActivity extends BaseActivity implements HomeView {
    private static final int CHOICE_SPORTS_FINISH_OK = 0x123;
    private int userType = 1;
    private static final int PAGE_ONE = 0;
    private static final int PAGE_TWO = 1;
    private static final int PAGE_THREE = 2;
    private static final int PAGE_FOUR = 3;

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
        mPresenter.setPage(PAGE_ONE);
    }

    private void initFragments() {
        mfragmentList = new ArrayList<>();
        mfragmentList.add(new HomeFragment());
        mfragmentList.add(new AgendaFragment());
        mfragmentList.add(new NewsFragment());
        mfragmentList.add(new PersonInfoFragment());
    }

    @Override
    protected void initEvents() {
        creatEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType == 0) {
                    mPresenter.toCreateEvent();
                } else if (userType == 1) {
                    mPresenter.popupChoseWindow();
                } else {
                    ToastUtil.show(HomeActivity.this, "异常登录", 2000);
                }
            }
        });
        /**
         * addOnPageChangeListener instead of setOnPageChangeListener
         */
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 当页面在滑动时至滑动被停止之前
             * this function is used for scrolling unless we stop
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPresenter.toolBarAnim(position);
            }

            /**
             * if we select a page, we use this function
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                mPresenter.setPage(position);
            }

            /**
             * 页面状态分为静止、滑动时和滑动后
             * 页面状态改变时调用
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        /**
         * According to change of fragment ,Radio Button will change
         */
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_home:
                        mViewPager.setCurrentItem(PAGE_ONE);
                        break;
                    case R.id.btn_calendar:
                        mViewPager.setCurrentItem(PAGE_TWO);
                        break;
                    case R.id.btn_news:
                        mViewPager.setCurrentItem(PAGE_THREE);
                        break;
                    case R.id.btn_person:
                        mViewPager.setCurrentItem(PAGE_FOUR);
                        break;
                }
            }
        });
        /**
         * Title ToolBar Button Onclick Action
         */
        mToolbar.setCallbck(new CommonHeadView.CALLBACK() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                mPresenter.goToChoseSports();
            }

            @Override
            public void onCenterClick() {

            }
        });
    }
    // TODO: 2016/11/15  hiddenToolBarAnim/showToolBarAnim

    /**
     * make right Btn visible or not
     *
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
     * Below there are Method PopWindow
     *
     * @return
     */
    @Override
    public View initPopupView() {
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_chose_layout, null);
        Button eventBtn = (Button) popupView.findViewById(R.id.btn_creat_event);
        Button postBtn = (Button) popupView.findViewById(R.id.btn_creat_post);
        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.toCreateEvent();
            }
        });
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(HomeActivity.this, "跳转到Post界面", 1500);
            }
        });
        return popupView;
    }

    @Override
    public void setBackGroundAlpha(float v) {
        WindowManager.LayoutParams windowlp = HomeActivity.this.getWindow().getAttributes();
        windowlp.alpha = v;
        HomeActivity.this.getWindow().setAttributes(windowlp);
    }

    @Override
    public void showPopupWindow(PopupWindow popupWindow) {
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    /**
     * get which tab btn is checked
     *
     * @param position
     * @return
     */
    @Override
    public RadioButton getCheckedBtn(int position) {
        return (RadioButton) mRg.getChildAt(position);
    }

    @Override
    public void goToCreateEventActivity() {
        Intent intent=new Intent();
        intent.setClass(HomeActivity.this, CreatEventActivity.class);
        startActivity(intent);
    }

    /**
     * Action for Right Button On Top ToolBar
     */
    @Override
    public void goToChoseSports() {
        Intent intent = new Intent();
        intent.setClass(HomeActivity.this, ChooseSportsActivity.class);
        startActivityForResult(intent, CHOICE_SPORTS_FINISH_OK);
    }

    @Override
    public Context getContext() {
        return HomeActivity.this;
    }

    /**
     * If we succeed in choosing Sports, these data will be sent back to HomeActivity
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOICE_SPORTS_FINISH_OK) {
            // TODO: 2016/11/15 重新刷新界面
            LoggerUtil.i("HomeActiviy", "选择运动成功");
        } else {
            ToastUtil.show(HomeActivity.this, "添加运动失败，请重新添加", 1500);
        }
    }

    /**
     * override onKeyDown for function of double clicking to exit app
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mPresenter.keyBackAction();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * Fragment Adapter
     */
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
