package com.sport.qifan.sport.views.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.views.BaseActivity;
import com.sport.qifan.sport.views.activity.home.HomeActivity;
import com.sport.qifan.sport.views.activity.signup.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/11/8.
 */

public class LoginActivity extends BaseActivity implements LoginView{

    @BindView(R.id.login_user_name)
    protected EditText userName;
    @BindView(R.id.login_user_pwd)
    protected EditText userPwd;
    @BindView(R.id.btn_sign_in)
    protected Button sign_in;
    @BindView(R.id.login_sign_up)
    protected TextView sign_up;

    private LoginPresenter mPresenter=new LoginPresenter(this);

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(LoginActivity.this);
//        initial data
        mPresenter.init(LoginActivity.this);
    }

    @Override
    protected void initEvents() {
    sign_in.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.goToSignUp();
        }
    });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loginTest();
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUp();
            }
        });
    }

    @Override
    public void showProgessBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return userPwd.getText().toString();
    }

    @Override
    public void goToHomeActivity() {
        Intent intent=new Intent();
        intent.setClass(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToSignUp() {
        Intent intent=new Intent();
        intent.setClass(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
