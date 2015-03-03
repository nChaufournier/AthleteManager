package com.sleepypirate.athletemanager.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This is a database class for SQLite and will hold all of the Exercises
 */
public class ExerciseDB extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "exercise.db";
    private static final int DATABASE_VERSION =1;

    //Table Name Exercise
    public static final String TABLE_EXERCISE = "exercises";

    //Exercise Table Column names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "exerciseName";
    public static final String KEY_TYPE = "exerciseType";
    public static final String KEY_REPTIME = "exerciseRepTime";
    public static final String KEY_NOTE = "exerciseNote";
    public static final String KEY_REPS = "exerciseReps";
    public static final String KEY_TIME = "exerciseTime";

    public static final String DATABASE_CREATE = "CREATE TABLE "+ TABLE_EXERCISE + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT,"
            + KEY_TYPE + " TEXT,"
            + KEY_REPTIME + " TEXT,"
            + KEY_NOTE + " TEXT,"
            + KEY_REPS + " TEXT,"
            + KEY_TIME + " TEXT);";


    public ExerciseDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older exercise table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);

        //Create fresh Exercise table
        this.onCreate(db);
    }
}
