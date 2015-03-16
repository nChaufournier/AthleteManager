package com.sleepypirate.athletemanager.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

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
        ScheduleDB.KEY_NAME, ScheduleDB.KEY_TYPE, ScheduleDB.KEY_DATE,ScheduleDB.KEY_TIME, ScheduleDB.KEY_NOTE };
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
        values.put(ScheduleDB.KEY_TIME, event.getTime());
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

    public void editEvent(Event event){
        ContentValues values = new ContentValues();
        values.put(ScheduleDB.KEY_ID, event.get_id());
        values.put(ScheduleDB.KEY_NAME, event.getName());
        values.put(ScheduleDB.KEY_TYPE, event.getType());
        values.put(ScheduleDB.KEY_DATE, event.getDate());
        values.put(ScheduleDB.KEY_TIME, event.getTime());
        values.put(ScheduleDB.KEY_NOTE, event.getNote());
        long insertId = event.get_id();
        database.update(ScheduleDB.TABLE_SCHEDULE,
                values,ScheduleDB.KEY_ID + " = " + insertId, null);
    }

    public void deleteEvent(long id){
        System.out.println("Comment deleted with id: ");
        //Toast.makeText(context, "Delete Event", Toast.LENGTH_SHORT).show();
        database.delete(ScheduleDB.TABLE_SCHEDULE, ScheduleDB.KEY_ID + " = " +
                id, null);
    }

    public Event getEventById(long id){
        String selectId = ScheduleDB.KEY_ID + " = '" + id+"'";
        Cursor cursor = database.query(ScheduleDB.TABLE_SCHEDULE, allColumns, selectId, null, null, null, null);
        cursor.moveToFirst();
            Event event = new Event();
            event.set_id(cursor.getLong(0));
            event.setName(cursor.getString(1));
            event.setType(cursor.getString(2));
            event.setDate(cursor.getString(3));
            event.setTime(cursor.getString(4));
            event.setNote(cursor.getString(5));
            cursor.moveToNext();
        cursor.close();
        return event;
    }

    public List<Event> getEventByDate(String date){
        List<Event> events = new ArrayList<Event>();
        String selectDate = ScheduleDB.KEY_DATE+ " = '" + date+"'";
        //Log.v("Date", selectDate);
                //database.execSQL("SELECT * FROM "+ ScheduleDB.TABLE_SCHEDULE+ " WHERE " + ScheduleDB.KEY_DATE+ " = " + date);

        //SELECT * FROM events WHERE date = 'date';
        Cursor cursor = database.query(ScheduleDB.TABLE_SCHEDULE, allColumns, selectDate, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Event event = new Event();
            event.set_id(cursor.getLong(0));
            event.setName(cursor.getString(1));
            event.setType(cursor.getString(2));
            event.setDate(cursor.getString(3));
            event.setTime(cursor.getString(4));
            event.setNote(cursor.getString(5));
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
        event.setTime(cursor.getString(4));
        event.setNote(cursor.getString(5));
        return event;
    }
}
