package com.sport.qifan.sport.views.fragment.agenda;

import android.content.Context;
import android.widget.PopupWindow;

import com.sport.qifan.sport.custome.PersonInfoPopupWindow;
import com.sport.qifan.sport.model.business.VollyRequestManager;
import com.sport.qifan.sport.model.business.VollyResponse;
import com.sport.qifan.sport.model.module.AgendaEvents;
import com.sport.qifan.sport.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qifan on 2016/11/15.
 */

public class AgendaFragPresenter {
    private AgendaFragView view;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;
    private List<AgendaEvents> agendaEventsList;

    public AgendaFragPresenter(AgendaFragView view) {
        this.view = view;
    }

    /**
     * initial presenter
     * get Activity context
     * initial VolleyRequestManager
     *
     * @param context
     */
    public void init(Context context) {
        this.mContext = context;
        vollyRequestManager = new VollyRequestManager(VolleyUtil.getInstance(mContext).getRequestQueue());
    }
    /**
     * test Unit
     */
    public List<AgendaEvents> getAgendaEventsTest(){
        agendaEventsList=new ArrayList<>();
        for (int i=0;i<10;i++){
            AgendaEvents item=new AgendaEvents();
            item.setUser_name("user"+i);
            item.setUser_events_name("BASKETBALL"+i);
            item.setUser_Image_id("image"+i);
            item.setUser_events_time("12:00");
            item.setUser_events_place("UTT");
            item.setEvents_Progress(""+i*10);
            agendaEventsList.add(item);
        }
        return agendaEventsList;
    }

    public void  getAgendaEvents(){
        Map<String ,String> params=new HashMap<>();
        params.put("user_id","123456");
        String url = "";
        VollyResponse<AgendaEvents> response=new VollyResponse<>();
        vollyRequestManager.doPost(mContext,url, response, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSucess(VollyResponse response) {

            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }


    public void showPopupWindow() {
        //获取数据，通过构造函数塞到popupWindow里
        final PersonInfoPopupWindow popupWindow = new PersonInfoPopupWindow(mContext);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setBackGroundAlpha(1f);
            }
        });
        view.showPersonInfoPopupWindow(popupWindow);
        view.setBackGroundAlpha(0.55f);
    }
}
