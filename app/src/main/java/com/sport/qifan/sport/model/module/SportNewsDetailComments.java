package com.sport.qifan.sport.model.module;

import java.io.Serializable;

/**
 * Created by qifan on 2016/11/18.
 */

public class SportNewsDetailComments implements Serializable{
    private int user_id;
    private String user_name;
    private String user_comment;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }
}
