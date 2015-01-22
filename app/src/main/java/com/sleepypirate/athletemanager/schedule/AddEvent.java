package com.sleepypirate.athletemanager.schedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

import com.sleepypirate.athletemanager.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * AddEvents from this page
 */
public class AddEvent extends Activity{
    Spinner typeSpinner;
    EditText name;
    EditText pickDate;
    EditText note;
    Button btnSave;
    Button btnView;
    String calText;
    String spinText;

    SQLiteDatabase db;
    Calendar myCal;

    Calendar myCalendar = Calendar.getInstance();

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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.event_type, R.layout.test_activity);

        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.test_activity);
        //Apply the adapter to the spinner
        typeSpinner.setAdapter(adapter);
        spinText = typeSpinner.getSelectedItem().toString();

        //Set the date in a spinner type fashion
        SetDate fromDate = new SetDate(pickDate, this);
        calText = pickDate.getText().toString();


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
        });
        //cur.close();
    }


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
