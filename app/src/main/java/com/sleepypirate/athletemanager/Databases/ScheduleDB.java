package com.sleepypirate.athletemanager.Databases;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This is a database class for SQLite and will hold all of the classes
 */
public class ScheduleDB extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;
    private static final String EVENTS_TABLE_NAME = "scheduelEvents";
    private static final String DATABASE_NAME = "events";
    /*private static final String EVENTS_TABLE_CREATE =
                    "CREATE TABLE " + EVENTS_TABLE_NAME + " (" +
                    KEY_WORD + " TEXT, " +
                    KEY_DEFINITION + " TEXT);";*/

    public ScheduleDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(EVENTS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
