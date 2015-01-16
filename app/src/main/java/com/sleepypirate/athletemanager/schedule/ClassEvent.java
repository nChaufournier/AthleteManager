package com.sleepypirate.athletemanager.schedule;

import java.util.Date;

/**
 * Created by Nic on 1/15/2015.
 */
public class ClassEvent extends Event {
    String teacher;
    String location;
    Date start, finish;
    int repetitions;

    public ClassEvent(String name) {
        super(name);
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
