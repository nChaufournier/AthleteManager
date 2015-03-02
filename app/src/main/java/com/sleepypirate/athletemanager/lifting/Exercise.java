package com.sleepypirate.athletemanager.lifting;

/**
 * Created by Nic on 1/7/2015.
 */
public class Exercise {
    private long _id;
    private String exerciseName;
    private String exerciseGroup;
    private String exerciseRep;

    public Exercise(long _id, String exerciseName, String exerciseGroup, String exerciseRep) {
        this._id = _id;
        this.exerciseName = exerciseName;
        this.exerciseGroup = exerciseGroup;
        this.exerciseRep = exerciseRep;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseGroup() {
        return exerciseGroup;
    }

    public void setExerciseGroup(String exerciseGroup) {
        this.exerciseGroup = exerciseGroup;
    }

    public String getExerciseRep() {
        return exerciseRep;
    }

    public void setExerciseRep(String exerciseRep) {
        this.exerciseRep = exerciseRep;
    }
}
