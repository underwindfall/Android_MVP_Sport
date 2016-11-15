package com.sport.qifan.sport.views.activity.home;

import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.utils.ActivityManager;
import com.sport.qifan.sport.utils.ToastUtil;

/**
 * Created by qifan on 2016/11/8.
 */

public class HomePresenter {

    private HomeView view;
    private boolean isToolBarVisible = true;
    private int currentPage = 0;
    private PopupWindow popupWindow;
    private long mExitTime;

    public HomePresenter(HomeView homeView) {
        this.view = homeView;
    }

    /**
     * We change different page we use this function
     *
     * @param position position of page
     */
    public void toolBarAnim(int position) {
        if (position == 0) {
            view.setToolBarRightBtnVisible(true);
        } else {
            view.setToolBarRightBtnVisible(false);
        }
        if (position == 3 && isToolBarVisible) {
            view.hiddenToolBarAnim();
        } else if (position != 3 && !isToolBarVisible) {
            view.showToolBarAnim();
        }
    }

    /**
     * according to the position to set page in viewpager
     *
     * @param position
     */
    public void setPage(int position) {
        currentPage = position;
        view.getCheckedBtn(position).setChecked(true);
        //change some infos of the toolbar


    }

    public void toCreateEvent() {
        view.goToCreateEventActivity();
    }

    /**
     * get data and use data to fill up popupChoseWindow by method constructor
     */
    public void popupChoseWindow() {
        popupWindow = new PopupWindow(view.initPopupView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        /**
         * setTouchable ToolBox can be touched or not
         * setFocusable EditText can be used or not
         * setOutSideTouchable Once outsidePopWindow is touched, popwindow will be vanished ,the most important is setBackgroundDrawable must be settled
         */
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setBackGroundAlpha(1.0f);
            }
        });
        //show ShowPopWindow
        view.showPopupWindow(popupWindow);
        view.setBackGroundAlpha(0.55f);
    }

    /**
     * Action to Click Right Button
     */
    public void goToChoseSports() {
        if (currentPage == 0) {
            view.goToChoseSports();
        }
    }

    /**
     *Action for key Back for all of fragment
     */
    public void keyBackAction(){
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtil.show(view.getContext(), "click again to quit", 2000);
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityManager.getInstance().exit();
        }
    }
/**
 *   public void setToolBarVisible(boolean visible) {
 this.isToolBarVisible = visible;
 }
 */
}
