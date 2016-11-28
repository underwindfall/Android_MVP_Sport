package com.sport.qifan.sport.views.fragment.home;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.sport.qifan.sport.model.business.VollyRequestManager;
import com.sport.qifan.sport.model.business.VollyResponse;
import com.sport.qifan.sport.model.module.SportEvents;
import com.sport.qifan.sport.model.module.SportsFirstClass;
import com.sport.qifan.sport.model.module.SportsSecondClass;
import com.sport.qifan.sport.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qifan on 2016/11/13.
 */

public class HomeFragPresenter {
    private HomeFragView mView;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;
    private List<SportsFirstClass> sportsList;

    public HomeFragPresenter(HomeFragView mView) {
        this.mView = mView;
    }

    public void init(Context context) {
        this.mContext=context;
        vollyRequestManager=new VollyRequestManager(VolleyUtil.getInstance(mContext).getRequestQueue());
    }

    /**
     * Test Unit
     */
    public void getSportEventsTest() {
        sportsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SportsFirstClass item = new SportsFirstClass();
            item.setType("type" + i);
            List<SportsSecondClass> list1 = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                SportsSecondClass item1 = new SportsSecondClass();
                item1.setEvent_creator_name("user" + i);
                item1.setEvent_start_time("12:30");
                item1.setEvent_end_time("15:30");
                item1.setEvent_place("Troyes");
                list1.add(item1);
            }
            item.setSports(list1);
            sportsList.add(item);
        }
        mView.refreshList(sportsList);
    }
    public void getSportEvents() {
        //这个可能不需要分页
        Map<String, String> params = new HashMap<>();
        params.put("user_id", "123456");
        params.put("user_like", "sssss");

        String url = "";
        VollyResponse<SportEvents> response = new VollyResponse<>();
        vollyRequestManager.doPost(mContext, url, response, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSucess(VollyResponse response) {

            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }


}
