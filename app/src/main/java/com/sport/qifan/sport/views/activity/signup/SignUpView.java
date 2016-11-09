package com.sport.qifan.sport.views.activity.signup;

/**
 * Created by qifan on 2016/11/8.
 */

public interface SignUpView {
    String getUserName();

    String getUserPwd();

    String getUserPwd2();

    String getUserEmail();

    String getUserSex();

    void showProgressBar();

    void hideProgressBar();
}
