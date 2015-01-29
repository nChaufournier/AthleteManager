package com.sleepypirate.athletemanager.schedule;

import java.util.Date;

/**
 * Used to create homework type objects for the scheduler
 */
public class AssignmentEvent extends Event{
    String type;
    String priority;//Might change to int
    Date due;
    ClassEvent schoolClass;
    String note;
    Boolean complete;
    public AssignmentEvent(String name) {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public ClassEvent getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(ClassEvent schoolClass) {
        this.schoolClass = schoolClass;
    }


    public String getNote() {
        return note;
    }


    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
