package com.sport.qifan.sport.views.fragment.agenda;

import com.sport.qifan.sport.custome.PersonInfoPopupWindow;
import com.sport.qifan.sport.views.BaseView;

/**
 * Created by qifan on 2016/11/15.
 */

public interface AgendaFragView extends BaseView {
    void setBackGroundAlpha(float v);

    void showPersonInfoPopupWindow(PersonInfoPopupWindow popupWindow);
}
