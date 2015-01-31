package com.sleepypirate.athletemanager.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sleepypirate.athletemanager.schedule.Event;

import org.w3c.dom.Comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nic on 1/21/2015.
 */
public class EventsDataSource {

    private SQLiteDatabase database;
    private ScheduleDB dbHelper;
    //private String[] allColumns = {
    //    ScheduleDB.COLUMN_EVENT };

    public EventsDataSource(Context context){
        dbHelper = new ScheduleDB(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Event createEvent(String event) {

       /* ContentValues values = new ContentValues();
        values.put(ScheduleDB.COLUMN_EVENT, event);
        long insertId = database.insert(ScheduleDB.TABLE_EVENTS, null, values);
        Cursor cursor = database.query(ScheduleDB.TABLE_EVENTS,
                allColumns, + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Event newEvent = cursorToEvent(cursor);
        cursor.close();
        return newEvent;*/
        Event newEvent = new Event();
        return newEvent;
    }

    public void deleteEvent(Event event){
        System.out.println("Comment deleted with id: ");
        //database.delete(ScheduleDB.TABLE_EVENTS, ScheduleDB.COLUMN_ID + " = " +
        //        id, null);
    }

    /*public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();

        //Cursor cursor = database.query(ScheduleDB.TABLE_EVENTS,
         //       allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Event event = cursorToEvent(cursor);
            events.add(event);
            cursor.moveToNext();
        }
        cursor.close();
        return events;
    }*/


    private Event cursorToEvent(Cursor cursor){
        Event event = new Event();
        //event.setId(cursor.getLong(0));
        //event.setComment(cursor.getString(1));
        return event;
    }
}
