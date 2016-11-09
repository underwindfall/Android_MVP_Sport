package com.sport.qifan.sport.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilites for manageing all activities
 * Created by qifan on 2016/11/8.
 */

public class ActivityManager {
    //    list for each Acitivity
    private List<Activity> mList = new ArrayList<>();
    private static ActivityManager instance;

    //  private constracteur
    private ActivityManager() {
    }

    //    create an instance
    public synchronized static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }

    //    add Activity in the mList
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    //    close all acitivites
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    // finish an activity and pop the activity from the mList
    public void exit(Activity activity) {
        mList.remove(activity);
        activity.finish();
    }
}
