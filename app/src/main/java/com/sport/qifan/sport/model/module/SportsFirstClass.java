package com.sport.qifan.sport.model.module;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qifan on 2016/11/20.
 */

public class SportsFirstClass implements Serializable {
    private String type;
    private List<SportsSecondClass> sports;
    private String imageUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SportsSecondClass> getSports() {
        return sports;
    }

    public void setSports(List<SportsSecondClass> sports) {
        this.sports = sports;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
