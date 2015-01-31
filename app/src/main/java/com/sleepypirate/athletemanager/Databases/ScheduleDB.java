package com.sleepypirate.athletemanager.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sleepypirate.athletemanager.schedule.Event;

import java.util.LinkedList;
import java.util.List;

/**
 * This is a database class for SQLite and will hold all of the classes
 */
public class ScheduleDB extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "schedule.db";
    private  static final int DATABASE_VERSION = 1;

    //Schedule table name
    public static final String TABLE_SCHEDULE = "events";

    //Schedule Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_DATE = "date";
    private static final String KEY_NOTE = "note";
    private static final String[] COLUMNS = {KEY_NAME,KEY_TYPE,KEY_DATE,KEY_NOTE};


    public ScheduleDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE = "CREATE TABLE events("
                + "eventName TEXT," +
                "type TEXT," +
                "date TEXT," +
                "note TEXT);";
        db.execSQL(DATABASE_CREATE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older schedule table if existed
        db.execSQL("DROP TABLE IF EXISTS schedule");

        //create fresh schedule table
        this.onCreate(db);
    }

    //---------------------------Other Operations---------------------------------------------------

    public ScheduleDB openToRead() throws android.database.SQLException {


        return this;
    }




    //----------------------------CRUD OPERATIONS---------------------------------------------------

    public void addEvent(Event event){
        //for logging
        Log.d("addEvent", event.toString());

        //Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO events VALUES('"+event.getName()+ "','" +event.getType()+"','"
                +event.getDate()+"','"+event.getNote()+"');");

        //Close
        db.close();
    }

    public List<Event> getAllEvents(){
        List<Event> events = new LinkedList<Event>();

        //Build Query
        String query="SELECT * FROM " + TABLE_SCHEDULE;

        //Get reference to writable DB

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //Go Over each row, build schedule and add it to list
        Event event = null;
        if(cursor.moveToFirst()){
            do{
                event = new Event();
                event.setName(cursor.getString(0));
                event.setType(cursor.getString(1));
                event.setDate(cursor.getString(2));
                event.setNote(cursor.getString(3));
                events.add(event);
            }while (cursor.moveToNext());
        }

        Log.d("getAllEvents()", events.toString());

        return events;
    }



}
