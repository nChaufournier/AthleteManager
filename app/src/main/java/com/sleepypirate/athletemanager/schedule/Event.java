package com.sleepypirate.athletemanager.schedule;

/**
 * Event Object used for the schedule activities
 */
public class Event {
    private long _id;
    private String name;
    private String type;
    private String date;
    private String note;
    private String time;


    public Event(){

    }

    public Event(long _id, String name, String type, String date, String time,String note) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.time = time;
        this.note = note;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "\nName: " + name
                + "\nType: " + type
                + "\nDate: " + date
                + "\nTime: " + time
                + "\nNote: " + note;
    }


}
