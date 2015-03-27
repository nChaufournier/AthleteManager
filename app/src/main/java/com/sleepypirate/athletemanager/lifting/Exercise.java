package com.sleepypirate.athletemanager.lifting;

/**
 * Created by Nic on 1/7/2015.
 */
public class Exercise {
    private long _id;
    private String exerciseName;
    private String exerciseType;
    private String exerciseRepTime;
    private String exNote;
    private int exNumReps;
    private int exNumSets;
    private int exTime;


    public Exercise(){

    }
    public Exercise(long _id, String exerciseName, String exerciseType, String exerciseRepTime) {
        this._id = _id;
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
        this.exerciseRepTime = exerciseRepTime;
    }

    public int getExNumReps() {
        return exNumReps;
    }

    public void setExNumReps(int exNumReps) {
        this.exNumReps = exNumReps;
    }

    public int getExTime() {
        return exTime;
    }

    public void setExTime(int exTime) {
        this.exTime = exTime;
    }

    public int getExNumSets() {
        return exNumSets;
    }

    public void setExNumSets(int exNumSets) {
        this.exNumSets = exNumSets;
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

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseGroup) {
        this.exerciseType = exerciseGroup;
    }

    public String getExerciseRepTime() {
        return exerciseRepTime;
    }

    public void setExerciseRepTime(String exerciseRep) {
        this.exerciseRepTime = exerciseRep;
    }

    public String getExNote() {
        return exNote;
    }

    public void setExNote(String exNote) {
        this.exNote = exNote;
    }



    @Override
    public String toString() {
        return "\n" +
                exerciseName + '\n' +
                "Muscle Group: " + exerciseType + '\n' +
                "Reps/Time: " + exerciseRepTime + "\n" +
                "Note: "+exNote+
                "\n";
    }
}
