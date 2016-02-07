package com.catalyst.travller.app.data;

/**
 * Created by rankush.agrawal on 06-02-2016.
 */
public class AttendeeBean {

    private int eventId;
    private UserInfoBean userInfo;

    public AttendeeBean(int eventId, UserInfoBean userInfo) {
        this.eventId = eventId;
        this.userInfo = userInfo;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }
}
