package com.sport.qifan.sport.views.activity.newsdetail;


import android.content.Context;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.model.business.VollyRequestManager;
import com.sport.qifan.sport.model.module.SportsNews;
import com.sport.qifan.sport.utils.VolleyUtil;

import java.util.ArrayList;

/**
 * Created by qifan on 2016/11/17.
 */

public class NewsDetailPrensenter {
    private NewsDetailView newsDetailView;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;
    private ArrayList<SportsNews> sportsList;

    public NewsDetailPrensenter(NewsDetailView newsDetailView) {
        this.newsDetailView = newsDetailView;
    }
    public void init(Context context){
        this.mContext=context;
        vollyRequestManager = new VollyRequestManager(VolleyUtil.getInstance(mContext).getRequestQueue());
    }

    /**
     * test Unit
     */
    public void getSportsNewsTest() {
        sportsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SportsNews sportsNews = new SportsNews();
            sportsNews.setNews_image(R.drawable.event_defaut_bg);
            sportsNews.setNews_title("BASKETBALL MATCH CREATED BY UTT");
            sportsNews.setNews_context("This is a great match which you can fight wiz kobe and james, they will teach you to play baskeball, show blba lbakankakabkb bbakkbakb kbkabk kabkb kabkbak bkakb ");
            sportsNews.setNews_views(120);
            sportsNews.setNews_apply(10);
            sportsNews.setLiked(false);
            sportsList.add(sportsNews);
        }
//        newFragView.refreshList(sportsList);
    }

}
