package com.sport.qifan.sport.views.fragment.home;

import com.sport.qifan.sport.model.module.SportsFirstClass;
import com.sport.qifan.sport.views.BaseView;

import java.util.List;

/**
 * Created by qifan on 2016/11/13.
 */

public interface HomeFragView extends BaseView {
    void refreshList(List<SportsFirstClass> sportsList);
}
