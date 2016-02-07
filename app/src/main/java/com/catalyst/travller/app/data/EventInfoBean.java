package com.catalyst.travller.app.data;

/**
 * Created by rankush.agrawal on 06-02-2016.
 */
public class EventInfoBean {

    private int eventId;
    private long eventStartTime;
    private long eventEndTime;
    private String eventCreatorId;
    private String eventName;
    private String eventDesc;
    private int eventStatus;


    public EventInfoBean(long eventStartTime, long eventEndTime, String eventCreatorId, String eventName, String eventDesc) {
       // this.eventId = eventId;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventCreatorId = eventCreatorId;
        this.eventName = eventName;
        this.eventDesc = eventDesc;
       // this.eventStatus = eventStatus;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(long eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public long getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(long eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventCreatorId() {
        return eventCreatorId;
    }

    public void setEventCreatorId(String eventCreatorId) {
        this.eventCreatorId = eventCreatorId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public int getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(int eventStatus) {
        this.eventStatus = eventStatus;
    }
}
