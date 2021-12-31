package com.example.event.ui.home;

import java.util.Date;

public class Event {
    String event_name,posted_by,status,description;
    String key,link;
    Date posted_on,event_on,deadline;
    int id;

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(String posted_by) {
        this.posted_by = posted_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPosted_on() {
        return posted_on;
    }

    public void setPosted_on(Date posted_on) {
        this.posted_on = posted_on;
    }

    public Date getEvent_on() {
        return event_on;
    }

    public void setEvent_on(Date event_on) {
        this.event_on = event_on;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event(){}
    public Event(int id,String event_name,String status,String posted_by,Date posted_on,Date event_on)
    {
        this.id=id;
        this.event_name=event_name;
        this.status=status;
        this.posted_by=posted_by;
        this.posted_on=posted_on;
        this.event_on=event_on;
    }
    public Event(String event_name, String posted_by,Date posted_on, Date event_on,Date deadline,String description,String link) {
        this.event_name = event_name;
        this.posted_by = posted_by;
        this.posted_on = posted_on;
        this.event_on = event_on;
        this.description=description;
        this.link=link;
        this.deadline=deadline;
    }
    public Event(String event_name,String posted_by,String status,Date posted_on,Date event_on,Date deadline,String key)
    {
        this.event_name=event_name;
        this.posted_by=posted_by;
        this.status=status;
        this.posted_on=posted_on;
        this.event_on=event_on;
        this.deadline=deadline;
        this.key=key;
    }
}
