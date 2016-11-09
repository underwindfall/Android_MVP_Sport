package com.sport.qifan.sport.views.activity.login;

import com.sport.qifan.sport.views.BaseView;

/**
 * Created by qifan on 2016/11/8.
 */

public interface LoginView extends BaseView {

    String getUserName();

    String getUserPwd();

    void goToHomeActivity();
    void goToSignUp();
}
