package com.sport.qifan.sport.views.activity.home;

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
}
