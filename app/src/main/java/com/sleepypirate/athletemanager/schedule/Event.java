package com.sleepypirate.athletemanager.schedule;

import java.util.Date;

/**
 * Created by Nic on 1/15/2015.
 */
public class Event {

    private long id;
    private String event;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return event;
    }

    public void setComment(String comment) {
        this.event = comment;
    }

    @Override
    public String toString() {
        return event;
    }
}
