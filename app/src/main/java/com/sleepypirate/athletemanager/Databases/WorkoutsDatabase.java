package com.sleepypirate.athletemanager.Databases;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sleepypirate.athletemanager.lifting.Exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nic on 3/26/15.
 */
public class WorkoutsDatabase {
    Map<String, Exercise> workoutDb = new Map<String, Exercise>() {
        @Override
        public void clear() {

        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @NonNull
        @Override
        public Set<Entry<String, Exercise>> entrySet() {
            return null;
        }

        @Override
        public Exercise get(Object key) {
            return workoutDb.get(key);
            //return null;
        }

        @Override
        public boolean isEmpty() {
            return workoutDb.isEmpty();
            //return false;
        }

        @NonNull
        @Override
        public Set<String> keySet() {
            return null;
        }

        @Override
        public Exercise put(String key, Exercise value) {
            workoutDb.put(key, value);
            return null;
        }

        @Override
        public void putAll(Map<? extends String, ? extends Exercise> map) {

        }

        @Override
        public Exercise remove(Object key) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @NonNull
        @Override
        public Collection<Exercise> values() {
            return null;
        }
    };
    public WorkoutsDatabase() {
    }

    public void addExercise(Exercise ex, String date){
        workoutDb.put(date, ex);
    }

    public void showAll(String date){
        Log.v("Workout:", workoutDb.get(date).toString());
    }

    public boolean isEmpty(){
        return workoutDb.isEmpty();
    }
}
