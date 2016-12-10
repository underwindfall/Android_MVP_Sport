package com.sport.qifan.sport.views.fragment.personinfo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.views.BaseFragment;
import com.sport.qifan.sport.views.activity.persondetail.PersonDetailActivity;

import butterknife.BindView;

/**
 * Created by qifan on 2016/11/13.
 */

public class PersonInfoFragment extends BaseFragment implements PersonInfoView{
    @BindView(R.id.test)
    protected Button btn;
    private PersonInfoPresenter mPresenter = new PersonInfoPresenter(this);
    @Override
    public int getLayoutId() {
        return R.layout.personinfo_fragment_layout;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initEvents() {
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToDetail();
        }
    });
    }

    @Override
    public void goToDetail() {
        Intent it=new Intent();
        it.setClass(getActivity(), PersonDetailActivity.class);
        startActivity(it);
    }

    @Override
    public void showProgessBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
