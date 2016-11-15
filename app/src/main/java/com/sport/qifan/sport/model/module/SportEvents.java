package com.sport.qifan.sport.model.module;

/**
 * Created by qifan on 2016/11/16.
 */

public class SportEvents {
    //resource id of image
    private int event_image;
    //this item is selected
    private boolean isSelected = false;
    //sport's name
    private String event_name;

    public SportEvents() {
    }

    public SportEvents(int event_image,String event_name) {
        this.event_name = event_name;
        this.event_image = event_image;
    }

    public SportEvents(int event_image, boolean isSelected, String event_name) {
        this.event_image = event_image;
        this.isSelected = isSelected;
        this.event_name = event_name;
    }

    public int getEvent_image() {
        return event_image;
    }

    public void setEvent_image(int event_image) {
        this.event_image = event_image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }
}
