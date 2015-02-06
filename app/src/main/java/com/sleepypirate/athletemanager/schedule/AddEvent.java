package com.sleepypirate.athletemanager.schedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sleepypirate.athletemanager.Databases.EventsDataSource;
import com.sleepypirate.athletemanager.Databases.ScheduleDB;
import com.sleepypirate.athletemanager.R;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * AddEvents from this page
 */
public class AddEvent extends Activity {
    private Spinner typeSpinner;
    private EditText name;
    private EditText pickDate;
    private EditText note;
    private Button btnSave;
    private Button btnView;
    private String calText;
    private String spinText;
    //SQLiteDatabase db;
    EventsDataSource db = new EventsDataSource(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creation);



        //Used for Home Button
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Attach items to the button
        pickDate = (EditText) findViewById(R.id.dpPickDate);
        name = (EditText) findViewById(R.id.etEventName);
        typeSpinner = (Spinner) findViewById(R.id.spinType);
        btnSave = (Button) findViewById(R.id.btnEventSave);
        btnView = (Button) findViewById(R.id.btnViewRecords);

        note = (EditText) findViewById(R.id.etNote);
        ArrayAdapter<CharSequence> ddAdapter = ArrayAdapter.createFromResource(this,
                R.array.event_type, R.layout.test_activity);

        //Specify the layout to use when the list of choices appears
        ddAdapter.setDropDownViewResource(R.layout.test_activity);
        //Apply the adapter to the spinner
        typeSpinner.setAdapter(ddAdapter);
        spinText = typeSpinner.getSelectedItem().toString();

        //Set the date in a spinner type fashion
        SetDate fromDate = new SetDate(pickDate, this);
        calText = pickDate.getText().toString();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name != null && pickDate != null) {
                    Event newEvent = new Event();
                    newEvent.setName(name.getText().toString());
                    newEvent.setType(spinText);
                    newEvent.setDate(pickDate.getText().toString());
                    newEvent.setNote(note.getText().toString());
                    db.createEvent(newEvent);

                    //Goes back to the scheduleActivity
                    Intent i = new Intent(getApplicationContext(), ScheduleActivity.class);
                    startActivity(i);
                }else{
                    showMessage("Error", "Please add either a Name or Date");
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("All Items", db.getAllEvents().toString());
                //db.getEvent(0);
            }
        });














        /* Old database stuff. Only works in create vent
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS schedule(" +
                "eventName TEXT," +
                "type TEXT," +
                "date TEXT," +
                "note TEXT);");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO schedule VALUES('"+name.getText()+ "','" + spinText+"','"+pickDate.getText().toString()+"','"+note.getText()+"');");
                Log.e("CALTEXT:", pickDate.getText().toString());
                Toast.makeText(getApplicationContext(), spinText, Toast.LENGTH_SHORT).show();

                //Create a cursor to show the newly inputted event
                Cursor c =db.rawQuery("SELECT * FROM schedule", null);
                c.moveToLast();
                showMessage("Assignment", c.getString(0)+"\nType: " + c.getString(1)+"\nDate: "+
                        c.getString(2)+"\nNote: "+c.getString(3));
                c.close();

                //Goes back to the scheduleActivity
                Intent i = new Intent(getApplicationContext(), ScheduleActivity.class);
                startActivity(i);
            }
        });


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c =db.rawQuery("SELECT * FROM schedule", null);
                if(c.getCount()==0){
                    showMessage("Error", "No Records Found");
                    return;
                }
                c.moveToFirst();
                while (c.moveToNext()){
                    showMessage("Assignment", c.getString(0)+"\nType: " + c.getString(1)+"\nDate: "+
                            c.getString(2)+"\nNote: "+c.getString(3));
                }

                c.close();

            }
        });*/
        //cur.close();
    }

    /*protected void onResume(){
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
