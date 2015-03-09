package com.sleepypirate.athletemanager.lifting;

import java.util.ArrayList;

/**
 * This is a class that will hold multiple exercises and be displayed on the
 * Exercise Fragment.
 */
public class Workout {
    private long _id;
    private String woName;
    private ArrayList<Exercise> woList;
    private Exercise woExercise;
    private String woDate;
    private int woTime;

    public Workout() {

    }

    public Workout(long _id, String woName, ArrayList<Exercise> woList) {
        this._id = _id;
        this.woName = woName;
        this.woList = woList;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getWoName() {
        return woName;
    }

    public void setWoName(String woName) {
        this.woName = woName;
    }

    public ArrayList<Exercise> getWoList() {
        return woList;
    }

    public void setWoList(ArrayList<Exercise> woList) {
        this.woList = woList;
    }

    public Exercise getWoExercise() {
        return woExercise;
    }

    public void setWoExercise(Exercise woExercise) {
        this.woExercise = woExercise;
    }

    public String getWoDate() {
        return woDate;
    }

    public void setWoDate(String woDate) {
        this.woDate = woDate;
    }

    public int getWoTime() {
        return woTime;
    }

    public void setWoTime(int woTime) {
        this.woTime = woTime;
    }
}
