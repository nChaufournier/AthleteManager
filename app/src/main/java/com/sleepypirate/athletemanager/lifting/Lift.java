package com.sleepypirate.athletemanager.lifting;

/**
 * Created by Nic on 1/7/2015.
 */
public class Lift {
    String[] typesOfLifts;
    String name;
    String uses;
    String partOfBody;
    String flat;
    /*String[] chest = {"Bench", "Press", "Fly", "Dip", "Push Up"};
    String[] legs = {"Squat", "Lunge", "Leg Extension", "Leg Curl"};
    String[] back = {"Row", "Pull Down", "Pull Up", "Chin Up", "Deadlifts"};
    String[] core = {"Plank", "Sit Up", "Chop", "Flutter Kicks"};*/

    public Lift(String name, String uses, String partOfBody) {
        this.name = name;
        this.uses = uses;
        this.partOfBody = partOfBody;
    }

    public String[] getTypesOfLifts() {
        return typesOfLifts;
    }

    public void setTypesOfLifts(String[] typesOfLifts) {
        this.typesOfLifts = typesOfLifts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    public String getPartOfBody() {
        return partOfBody;
    }

    public void setPartOfBody(String partOfBody) {
        this.partOfBody = partOfBody;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "Name: "+ uses + " " + name + " Muscle Area:" + partOfBody;
    }
}
