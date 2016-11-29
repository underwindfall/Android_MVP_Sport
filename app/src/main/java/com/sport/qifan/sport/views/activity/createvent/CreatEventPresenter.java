package com.sport.qifan.sport.views.activity.createvent;

import android.content.Context;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.model.business.VollyRequestManager;
import com.sport.qifan.sport.model.module.SportEvents;
import com.sport.qifan.sport.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qifan on 2016/11/29.
 */

public class CreatEventPresenter {
    private CreatEventView creatEventView;
    private Context context;
    private List<SportEvents> sportEvents;
    private int totalEvents;
    private VollyRequestManager vollyRequestManager;

    public CreatEventPresenter(CreatEventView creatEventView) {
        this.creatEventView = creatEventView;
    }

    public void init(Context context){
        this.context=context;
        vollyRequestManager=new VollyRequestManager(VolleyUtil.getInstance(context).getRequestQueue());
    }
    /**
     * test Unit
     */
    public List<SportEvents> initSportEvents() {
        sportEvents = new ArrayList<>();
        //Event从服务器获取比较好一点
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

        totalEvents = sportEvents.size();
        return sportEvents;
    }
    public int getTotalEvents(){
        return totalEvents;
    }
}
