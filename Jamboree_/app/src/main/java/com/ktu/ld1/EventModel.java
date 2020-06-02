package com.ktu.ld1;

public class EventModel {

    private String eventName;
    private String eventRating;
    private String eventImage;

    public EventModel(String eventName, String eventRating, String eventImage) {
        this.eventName = eventName;
        this.eventRating = eventRating;
        this.eventImage = eventImage;
    }

    public EventModel() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventRating() {
        return eventRating;
    }

    public void setEventRating(String eventRating) {
        this.eventRating = eventRating;
    }

    public String getEventImage() {

        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
}
