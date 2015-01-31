package com.sleepypirate.athletemanager.schedule;

import java.util.Date;

/**
 * Created by Nic on 1/15/2015.
 */
public class Event {

    private String name;
    private String type;
    private String date;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    @Override
    public String toString() {
        return "\nName: " + name
                +"\nType: " + type
                +"\nData: " +date
                +"\nNote: " + note;
    }
}
