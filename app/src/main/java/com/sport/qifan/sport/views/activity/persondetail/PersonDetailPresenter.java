package com.sport.qifan.sport.views.activity.persondetail;

import android.content.Context;

import com.sport.qifan.sport.model.business.VollyRequestManager;
import com.sport.qifan.sport.utils.VolleyUtil;

/**
 * Created by qifan on 2016/12/10.
 */

public class PersonDetailPresenter {
    private PersonDetailView view;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;
    public PersonDetailPresenter(PersonDetailView view) {
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
}
