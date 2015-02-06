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

    /*---------------------------Other Operations---------------------------------------------------

    public ScheduleDB openToRead() throws android.database.SQLException {


        return this;
    }
    */



    //----------------------------CRUD OPERATIONS---------------------------------------------------

    /*public void addEvent(Event event){
        //for logging
        Log.d("addEvent", event.toString());

        //Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //* One way of inserting
        db.execSQL("INSERT INTO events VALUES('"+event.getName()+ "','" +event.getType()+"','"
                +event.getDate()+"','"+event.getNote()+"');");

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, event.getName());
        values.put(KEY_TYPE, event.getType());
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_NOTE, event.getNote());

        db.insert(TABLE_SCHEDULE, null, values);
        /*

        //Close
        db.close();
    }

    public Event getEvent(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SCHEDULE, new String[]{KEY_ID,
                        KEY_NAME, KEY_TYPE, KEY_DATE, KEY_NOTE }, KEY_ID + "=?",
                new String[]{ String.valueOf(id) }, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        Event event = new Event(Integer.parseInt(cursor.getString(id)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return event;

    }

    public List<Event> getAllEvents(){
        List<Event> events = new LinkedList<>();

        //Build Query
        String query="SELECT * FROM " + TABLE_SCHEDULE;

        //Get reference to writable DB

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //Go Over each row, build schedule and add it to list
        Event event;
        if(cursor.moveToFirst()){
            do{
                event = new Event();
                event.setName(cursor.getString(1));
                event.setType(cursor.getString(2));
                event.setDate(cursor.getString(3));
                event.setNote(cursor.getString(4));
                events.add(event);
            }while (cursor.moveToNext());
        }

        Log.d("getAllEvents()", events.toString());

        return events;
    }*/



}
