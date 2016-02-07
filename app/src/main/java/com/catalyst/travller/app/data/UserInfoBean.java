package com.catalyst.travller.app.data;

/**
 * Created by rankush.agrawal on 06-02-2016.
 */
public class UserInfoBean {

    private String user;
    private String password;
    private String name;
    private long phone;
/*    private List<Integer> eventCreatedList;
    private List<Integer> eventAttendingList;*/

    public UserInfoBean(String user, String password, String name, long phone) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

/*
    public List<Integer> getEventCreatedList() {
        return eventCreatedList;
    }

    public void setEventCreatedList(List<Integer> eventCreatedList) {
        this.eventCreatedList = eventCreatedList;
    }

    public List<Integer> getEventAttendingList() {
        return eventAttendingList;
    }

    public void setEventAttendingList(List<Integer> eventAttendingList) {
        this.eventAttendingList = eventAttendingList;
    }*/

    public String getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User name " + name + " password: " + password + " Email: " + user + " Phone: " + phone;
    }
}
