package com.sport.qifan.sport.views.activity.choiceofsports;

import android.content.Context;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.model.business.VollyRequestManager;
import com.sport.qifan.sport.model.module.SportEvents;
import com.sport.qifan.sport.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qifan on 2016/11/15.
 */

public class ChooseSportsPresenter {

    private ChooseSportsView mView;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;
    //all events
    private List<SportEvents> sportEvents;
    //events are chosen
    private List<SportEvents> sportEventsChosen;
    private int totalEvents = 0;
    public ChooseSportsPresenter(ChooseSportsView view) {
        this.mView = view;
    }

    public void init(Context context) {
        this.mContext = context;
        vollyRequestManager = new VollyRequestManager(VolleyUtil.getInstance(mContext).getRequestQueue());
        sportEvents = new ArrayList<>();
        sportEventsChosen = new ArrayList<>();
    }

    /**
     * Initial events Sports
     */
    public void initSportsEvents() {
        sportEvents.add(new SportEvents(R.drawable.basketball, "basketball"));
        sportEvents.add(new SportEvents(R.drawable.soccerball, "soccerball"));
        sportEvents.add(new SportEvents(R.drawable.football, "football"));
        sportEvents.add(new SportEvents(R.drawable.volleyball, "volleyball"));
        sportEvents.add(new SportEvents(R.drawable.badminton, "badminton"));
        sportEvents.add(new SportEvents(R.drawable.pingpang, "pingpang"));
        sportEvents.add(new SportEvents(R.drawable.tennis, "tennis"));
        sportEvents.add(new SportEvents(R.drawable.bicycle, "bicycle"));
        sportEvents.add(new SportEvents(R.drawable.running, "running"));
        sportEvents.add(new SportEvents(R.drawable.swimming, "swimming"));
        sportEvents.add(new SportEvents(R.drawable.exercise, "exercise"));
        sportEvents.add(new SportEvents(R.drawable.boxing, "boxing"));
        totalEvents=sportEvents.size();
        //初始化爱好ViewPager及其下方的圆点
        mView.initGirdViewsAndPoints();
    }
}
