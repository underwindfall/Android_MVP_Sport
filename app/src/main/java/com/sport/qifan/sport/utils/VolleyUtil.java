package com.sport.qifan.sport.utils;

/**
 * Created by qifan on 2016/11/8.
 */

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * tool to manager RequestNetwork of Queue
 */
public class VolleyUtil {
    //    private attribute
    private static VolleyUtil singleQueue;
    private RequestQueue requestQueue;
    private static Context context;

    //    private Constructor
    private VolleyUtil(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    //    method for get request Queue
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    //    method to get instance of Object
    public static synchronized VolleyUtil getInstance(Context context) {
        if (singleQueue == null) {
            singleQueue = new VolleyUtil(context.getApplicationContext());
        }
        return singleQueue;
    }
}
