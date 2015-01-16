package com.sleepypirate.athletemanager.schedule;

import java.util.Date;

/**
 * Class to create Tests, quizes, and Exams
 */
public class AssesmentEvent extends Event {
    String type;
    Date due;
    ClassEvent schoolClass;
    String note;
    int grade;
    public AssesmentEvent(String name) {
        super(name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
