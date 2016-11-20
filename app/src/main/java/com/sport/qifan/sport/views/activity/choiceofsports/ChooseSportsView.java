package com.sport.qifan.sport.views.activity.choiceofsports;

import com.sport.qifan.sport.model.module.SportEvents;
import com.sport.qifan.sport.views.BaseView;

import java.util.List;

/**
 * Created by qifan on 2016/11/15.
 */

public interface ChooseSportsView extends BaseView {
    void initGirdViewsAndPoints();

    void refreshEventChoose(List<SportEvents> sportEventsChosen);
}
