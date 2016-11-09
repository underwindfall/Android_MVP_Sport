package com.sport.qifan.sport.views;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.sport.qifan.sport.utils.ActivityManager;

/**
 * Created by qifan on 2016/11/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        ActivityManager.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
        if (notitle()) {
            setNoTitle();
        }
        setContentView(getLayoutId());
        initDatasAndViews();
        initEvents();
    }

    protected abstract boolean notitle();

    protected void setNoTitle() {
//        set Notitle and status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * set up for layout of activity
     * @return id of Layout
     */
    protected abstract int getLayoutId();

    /**
     * initialise Data and View
     */
    protected abstract void initDatasAndViews();
    /**
     * initialise Event
     */
    protected abstract void initEvents();

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().exit(this);
        super.onDestroy();
    }
}
