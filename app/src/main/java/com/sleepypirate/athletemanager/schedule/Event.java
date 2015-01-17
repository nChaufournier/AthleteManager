package com.sleepypirate.athletemanager.schedule;

import java.util.Date;

/**
 * Created by Nic on 1/15/2015.
 */
public class Event {

    String name;
    String note;
    Date dueDate;

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
