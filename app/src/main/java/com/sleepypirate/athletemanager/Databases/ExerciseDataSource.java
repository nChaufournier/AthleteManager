package com.sleepypirate.athletemanager.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sleepypirate.athletemanager.lifting.Exercise;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nic on 3/3/2015.
 */
public class ExerciseDataSource {
    private SQLiteDatabase database;
    private ExerciseDB dbHelper;
    private String[] allColumns = { ExerciseDB.KEY_ID,
        ExerciseDB.KEY_NAME, ExerciseDB.KEY_TYPE, ExerciseDB.KEY_REPTIME, ExerciseDB.KEY_NOTE,
        ExerciseDB.KEY_REPS, ExerciseDB.KEY_TIME};

    public ExerciseDataSource(Context context) {
        dbHelper = new ExerciseDB(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Exercise createExercise(Exercise exercise) {
        ContentValues values = new ContentValues();
        values.put(ExerciseDB.KEY_NAME, exercise.getExerciseName());
        values.put(ExerciseDB.KEY_TYPE, exercise.getExerciseType());
        values.put(ExerciseDB.KEY_REPTIME, exercise.getExerciseRepTime());
        values.put(ExerciseDB.KEY_NOTE, exercise.getExNote());
        values.put(ExerciseDB.KEY_REPS, exercise.getExNumReps());
        values.put(ExerciseDB.KEY_TIME, exercise.getExTime());

        long insertId = database.insert(ExerciseDB.TABLE_EXERCISE, null, values);
        Cursor cursor = database.query(ExerciseDB.TABLE_EXERCISE,
                allColumns, ExerciseDB.KEY_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Exercise newExercise = cursorToExercise(cursor);
        cursor.close();
        return newExercise;
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();

        Cursor cursor = database.query(ExerciseDB.TABLE_EXERCISE,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while ((!cursor.isAfterLast())){
            Exercise exercise = cursorToExercise(cursor);
            exercises.add(exercise);
            cursor.moveToNext();
        }
        cursor.close();
        return exercises;
    }

    private Exercise cursorToExercise(Cursor cursor){
        Exercise exercise = new Exercise();
        exercise.set_id(cursor.getLong(0));
        exercise.setExerciseName(cursor.getString(1));
        exercise.setExerciseType(cursor.getString(2));
        exercise.setExerciseRepTime(cursor.getString(3));
        exercise.setExNote(cursor.getString(4));
        exercise.setExNumReps(cursor.getInt(5));
        exercise.setExTime(cursor.getInt(6));
        return exercise;
    }


}
