package com.sport.qifan.sport.views.activity.home;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RadioButton;

/**
 * Created by qifan on 2016/11/8.
 */

public interface HomeView {
    void setToolBarRightBtnVisible(boolean b);

    void hiddenToolBarAnim();

    void showToolBarAnim();

    RadioButton getCheckedBtn(int position);

    void goToCreateEventActivity();

    View initPopupView();

    void setBackGroundAlpha(float v);

    void showPopupWindow(PopupWindow popupWindow);

    void goToChoseSports();

    Context getContext();
}
