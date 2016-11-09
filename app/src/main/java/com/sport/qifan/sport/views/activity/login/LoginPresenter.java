package com.sport.qifan.sport.views.activity.login;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.sport.qifan.sport.constant.Constant;
import com.sport.qifan.sport.model.business.VollyRequestManager;
import com.sport.qifan.sport.model.business.VollyResponse;
import com.sport.qifan.sport.model.module.LoginResponse;
import com.sport.qifan.sport.utils.SharedPreferenceUtil;
import com.sport.qifan.sport.utils.ToastUtil;
import com.sport.qifan.sport.utils.VolleyUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qifan on 2016/11/8.
 */

public class LoginPresenter {

    private LoginView view;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;

    public LoginPresenter(LoginView view) {
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

    public void goToSignUp() {
        view.goToSignUp();
    }

    public void login() {
        String userName = view.getUserName();
        String userPwd = view.getUserPwd();
        if (TextUtils.isEmpty(userName)) {
            ToastUtil.show(mContext, "User Name is not allowed empty", 1500);
            return;
        }
        if (TextUtils.isEmpty(userPwd)) {
            ToastUtil.show(mContext, "User Password is not allowed empty", 1500);
            return;
        }
        String url = Constant.HOST + Constant.LOGIN_PATH;
        VollyResponse<LoginResponse> loginResponseVollyResponse = new VollyResponse<>();
        Map<String, String> params = new HashMap<>();
        params.put("loginName", userName);
        params.put("password", userPwd);
        view.showProgessBar();
        vollyRequestManager.doPost(mContext, url, loginResponseVollyResponse, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSucess(VollyResponse response) {
                view.hideProgressBar();
                //存储用户登录信息，cookie之类的
                LoginResponse result = (LoginResponse) response.getResult(LoginResponse.class);
                SharedPreferenceUtil.put(mContext, "user_base_info", result);
                view.goToHomeActivity();
            }

            @Override
            public void onFailed(String msg) {
                view.hideProgressBar();
                ToastUtil.show(mContext, msg, 1500);
            }
        });
    }

    public void loginTest() {
        view.goToHomeActivity();
    }
}
