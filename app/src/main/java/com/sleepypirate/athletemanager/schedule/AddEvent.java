package com.sleepypirate.athletemanager.schedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sleepypirate.athletemanager.Databases.EventsDataSource;
import com.sleepypirate.athletemanager.R;

import java.sql.SQLException;

/**
 * AddEvents from this page
 */
public class AddEvent extends Activity {
    private Spinner typeSpinner;
    private EditText name;
    private EditText pickDate;
    private EditText pickTime;
    private EditText note;
    private Button btnSave;
    private Button btnView;
    private String calText;
    private Boolean edit;
    //SQLiteDatabase db;
    private EventsDataSource db;
    Bundle extras;//= getIntent().getExtras();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creation);

        db = new EventsDataSource(this);
        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Used for Home Button
        getActionBar().setDisplayHomeAsUpEnabled(true);


        //Attach items to the button
        pickDate = (EditText) findViewById(R.id.dpPickDate);
        pickTime = (EditText) findViewById(R.id.tpTime);
        name = (EditText) findViewById(R.id.etEventName);
        typeSpinner = (Spinner) findViewById(R.id.spinType);
        //btnSave = (Button) findViewById(R.id.btnEventSave);
        btnView = (Button) findViewById(R.id.btnViewRecords);
        note = (EditText) findViewById(R.id.etNote);

        extras = getIntent().getExtras();
        if(extras != null){
            if (extras.containsKey("Event")){
                Long value = extras.getLong("Event");
                Event editEvent = db.getEventById(value);
                name.setText(editEvent.getName());
                pickDate.setText(editEvent.getDate());
                pickTime.setText(editEvent.getTime());
                note.setText(editEvent.getNote());
                edit = true;
            }else if(extras.containsKey("date")){
                String value = extras.getString("date");
                pickDate.setText(value);
                edit = false;
            }
        }else{
            edit = false;

        }

        ArrayAdapter<CharSequence> ddAdapter = ArrayAdapter.createFromResource(this,
                R.array.event_type, R.layout.test_activity);

        //Specify the layout to use when the list of choices appears
        ddAdapter.setDropDownViewResource(R.layout.test_activity);
        //Apply the adapter to the spinner
        typeSpinner.setAdapter(ddAdapter);

        //Set the date in a spinner type fashion
        SetDate fromDate = new SetDate(pickDate, this);
        calText = pickDate.getText().toString();

        //Set time in a spinner type fashion
        SetTime fromTime = new SetTime(pickTime, this);



        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),  typeSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                showMessage("All Items", db.getAllEvents().toString());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (edit){
            getMenuInflater().inflate(R.menu.menu_edit, menu);
        }else{
            getMenuInflater().inflate(R.menu.save, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.mbtnSave:
                if (name != null && pickDate != null) {
                    Event newEvent = new Event();
                    if(edit){
                        Long value = extras.getLong("Event");
                        newEvent.set_id(value);
                    }
                    newEvent.setName(name.getText().toString());
                    newEvent.setType(typeSpinner.getSelectedItem().toString());
                    newEvent.setDate(pickDate.getText().toString());
                    newEvent.setTime(pickTime.getText().toString());
                    newEvent.setNote(note.getText().toString());
                    if(edit){
                        db.editEvent(newEvent);
                        Toast.makeText(getApplicationContext(), "Event Updated", Toast.LENGTH_LONG).show();
                    }else {
                        db.createEvent(newEvent);
                        Toast.makeText(getApplicationContext(), "Event Created", Toast.LENGTH_LONG).show();
                    }
                    //Goes back to the scheduleActivity
                    Intent i = new Intent(getApplicationContext(), ScheduleActivity.class);
                    startActivity(i);

                } else {
                    showMessage("Error", "Please add either a Name or Date");
                }

                break;
            case R.id.mbtnDelete:
                //extras = getIntent().getExtras();
                if(extras != null) {
                    Long value = extras.getLong("Event");
                    db.deleteEvent(value);
                }

                Intent i = new Intent(getApplicationContext(), ScheduleActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Event Deleted", Toast.LENGTH_LONG).show();

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
