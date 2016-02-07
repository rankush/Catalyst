package com.catalyst.travller.app.data;

/**
 * Created by rankush.agrawal on 06-02-2016.
 */
public class LocationBean {

    private double longitude;
    private double latitude;
    private int eventId;
    private String name;
    private String desc;



    public LocationBean(double longitude, double latitude, int eventId, String name, String desc) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.eventId = eventId;
        this.name = name;
        this.desc = desc;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
