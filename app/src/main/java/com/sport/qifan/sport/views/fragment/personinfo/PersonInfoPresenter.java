package com.sport.qifan.sport.views.fragment.personinfo;

/**
 * Created by qifan on 2016/12/10.
 */

public class PersonInfoPresenter {
    private PersonInfoView view;

    public PersonInfoPresenter(PersonInfoView view){
        this.view = view;
    }


    public void goToDetailActivity(){
        view.goToDetail();
    }
}
