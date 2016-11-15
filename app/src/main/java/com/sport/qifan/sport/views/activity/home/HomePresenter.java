package com.sport.qifan.sport.views.activity.home;

/**
 * Created by qifan on 2016/11/8.
 */

public class HomePresenter {
    private  HomeView view;
    private boolean isToolBarVisible = true;
    private int currentPage = 0;
    public HomePresenter(HomeView homeView) {
        this.view = homeView;
    }

    /**
     * hide or show toolbar depende on different page
     * @param position position of page
     */
    public void toolBarAnim(int position){
        if(position==0){
            view.setToolBarRightBtnVisible(true);
        }else {
            view.setToolBarRightBtnVisible(false);
        }
        if (position==3&&isToolBarVisible){
            view.hiddenToolBarAnim();
        }else if (position!=3&&!isToolBarVisible){
            view.showToolBarAnim();
        }
    }

    /**
     * selon the position to set page in viewpager
     * @param position
     */
    public void setPage(int position){
        currentPage=position;
        view.getCheckedBtn(position).setChecked(true);
        //change some infos of the toolbar


    }
    public void toCreateEvent(){
        view.goToCreateEventActivity();
    }
    public void popupChoseWindow(){
        //获取数据，
    }


}
