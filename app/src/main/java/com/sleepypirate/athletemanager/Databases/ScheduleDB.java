package com.sleepypirate.athletemanager.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This is a database class for SQLite and will hold all of the classes
 */
public class ScheduleDB extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "schedule.db";
    private  static final int DATABASE_VERSION = 1;

    //Table Name Schedule
    public static final String TABLE_SCHEDULE = "events";

    //Schedule Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "eventName";
    public static final String KEY_TYPE = "type";
    public static final String KEY_DATE = "date";
    public static final String KEY_NOTE = "note";

    public static final String DATABASE_CREATE = "CREATE TABLE "+TABLE_SCHEDULE +"("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT,"
            + KEY_TYPE + " TEXT,"
            + KEY_DATE + " TEXT,"
            + KEY_NOTE + " TEXT);";


    public ScheduleDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older schedule table if existed
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SCHEDULE);

        //create fresh schedule table
        this.onCreate(db);
    }

}
