package com.sleepypirate.athletemanager.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sleepypirate.athletemanager.schedule.Event;

import org.w3c.dom.Comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to connect to database from different activities
 */
public class EventsDataSource {

    private SQLiteDatabase database;
    private ScheduleDB dbHelper;
    private String[] allColumns = { ScheduleDB.KEY_ID,
        ScheduleDB.KEY_NAME, ScheduleDB.KEY_TYPE, ScheduleDB.KEY_DATE, ScheduleDB.KEY_NOTE };
    private String[] showEvent = {ScheduleDB.KEY_ID,
            ScheduleDB.KEY_NAME, ScheduleDB.KEY_TYPE};

    public EventsDataSource(Context context){
        dbHelper = new ScheduleDB(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Event createEvent(Event event) {

        ContentValues values = new ContentValues();
        values.put(ScheduleDB.KEY_NAME, event.getName());
        values.put(ScheduleDB.KEY_TYPE, event.getType());
        values.put(ScheduleDB.KEY_DATE, event.getDate());
        values.put(ScheduleDB.KEY_NOTE, event.getNote());

        long insertId = database.insert(ScheduleDB.TABLE_SCHEDULE, null, values);
        Cursor cursor = database.query(ScheduleDB.TABLE_SCHEDULE,
                allColumns,ScheduleDB.KEY_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Event newEvent = cursorToEvent(cursor);
        cursor.close();
        //Event newEvent = new Event();
        return newEvent;
    }

    public void deleteEvent(Event event){
        System.out.println("Comment deleted with id: ");
        //database.delete(ScheduleDB.TABLE_EVENTS, ScheduleDB.COLUMN_ID + " = " +
        //        id, null);
    }

    public List<Event> getEventByDate(String date){
        List<Event> events = new ArrayList<Event>();
                 //String selectQuery = "SELECT * FROM "+ ScheduleDB.TABLE_SCHEDULE+ " WHERE " + ScheduleDB.KEY_DATE+ " = '" + date+"';";
        String selectDate = ScheduleDB.KEY_DATE+ " = '" + date+"'";
        //Log.v("Date", selectDate);
                //database.execSQL("SELECT * FROM "+ ScheduleDB.TABLE_SCHEDULE+ " WHERE " + ScheduleDB.KEY_DATE+ " = " + date);

        //SELECT * FROM events WHERE date = 'date';
        Cursor cursor = database.query(ScheduleDB.TABLE_SCHEDULE, showEvent, selectDate, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Event event = new Event();
            event.set_id(cursor.getLong(0));
            event.setName(cursor.getString(1));
            event.setType(cursor.getString(2));
            events.add(event);
            cursor.moveToNext();
        }
        cursor.close();
        return events;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();

        Cursor cursor = database.query(ScheduleDB.TABLE_SCHEDULE,
               allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Event event = cursorToEvent(cursor);
            events.add(event);
            cursor.moveToNext();
        }
        cursor.close();
        return events;
    }


    private Event cursorToEvent(Cursor cursor){
        Event event = new Event();
        event.set_id(cursor.getLong(0));
        event.setName(cursor.getString(1));
        event.setType(cursor.getString(2));
        event.setDate(cursor.getString(3));
        event.setNote(cursor.getString(4));
        return event;
    }
}
