package com.sport.qifan.sport.model.module;

/**
 * Created by qifan on 2016/11/17.
 */

public class SportsNews {
    //resource id of image
    private int news_image;
    //resource title of news
    private String news_title;
    //resource text ot News
    private String news_context;
    //this item is number of reviews;
    private int news_views;
    //this item is number of responses
    private int news_apply;
    //this item is liked
    private boolean isLiked = false;

    public SportsNews() {
    }


    public int getNews_image() {
        return news_image;
    }

    public void setNews_image(int news_image) {
        this.news_image = news_image;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_context() {
        return news_context;
    }

    public void setNews_context(String news_context) {
        this.news_context = news_context;
    }

    public int getNews_views() {
        return news_views;
    }

    public void setNews_views(int news_views) {
        this.news_views = news_views;
    }

    public int getNews_apply() {
        return news_apply;
    }

    public void setNews_apply(int news_apply) {
        this.news_apply = news_apply;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
