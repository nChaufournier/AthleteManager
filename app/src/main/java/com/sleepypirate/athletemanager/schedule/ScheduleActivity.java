package com.sleepypirate.athletemanager.schedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sleepypirate.athletemanager.Databases.EventsDataSource;
import com.sleepypirate.athletemanager.MainActivity;
import com.sleepypirate.athletemanager.R;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is the schedule activity. It is where you will be able to view your classes, on a calendar
 * and view what your up and coming assignments are.
 * Plan to link it to google calendar and be able to add classes, homework, etc. directly to your
 * google calendar so it is not just device specific.
 */
public class ScheduleActivity extends Activity {

    ListView lvCalendar;
    EventAdapter mHomeworkAdapter;
    CalendarView calendarView;
    ImageButton expandListView;
    ImageButton collapseListView;
    RelativeLayout calBottomRL;
    Animation animSlideDown;
    Animation animSlideUp;
    Animation animMoveUp;
    Animation animTest;
    TextView bottomDate;
    TextView noEvent;
    RelativeLayout rlSchedule;
    ImageButton fabSchedule;
    String today, calSelection;


    EventsDataSource db;
    private String[] arCalendarEvents = {
            "Calc: Book Problems ",
            "Ethics: Reading",
            "Religion: Test Tomorrow",
            "Religion: Reading",
            "Networking: Program"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);
        //Used for Home Button

        getActionBar().setDisplayHomeAsUpEnabled(true);
        db = new EventsDataSource(this);
        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }




        calendarView = (CalendarView) findViewById(R.id.cvScheduleCalendar);
        lvCalendar = (ListView) findViewById(R.id.lvCalendar);
        noEvent = (TextView) findViewById(R.id.tvNoEvent);
        bottomDate = (TextView) findViewById(R.id.bottomDate);


        //Set the initial ListView When Activity is created
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        final String selectedDate = sdf.format(new Date(calendarView.getDate()));
        today = selectedDate;
        //Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show();
        if(!db.getEventByDate(selectedDate).isEmpty()) {
            //lvCalendar.setVisibility(view.VISIBLE);
            //noEvent.setVisibility(view.GONE);
            mHomeworkAdapter = new EventAdapter(getApplicationContext(), R.layout.schedulerow,  db.getEventByDate(selectedDate));
            if (mHomeworkAdapter != null) {
                lvCalendar.setAdapter(mHomeworkAdapter);
            }
        }else{
            //Toast.makeText(getApplicationContext(), "Should display 'No Event Add Event'", Toast.LENGTH_SHORT).show();
            //lvCalendar.setVisibility(view.GONE);
            noEvent.setVisibility(View.VISIBLE);
        }

        calSelection = today;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                String date;
                //The date is passed as #/##/## this makes it so it is passed as ##/##/## so
                //The db will be able to query
                if(month < 9){
                    if(day <= 9){
                        date = "0"+(month + 1) + "/0" + day + "/" + (year-2000);
                    }else{
                        date = "0"+(month + 1) + "/" + day + "/" + (year-2000);
                    }
                    //Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
                }else{
                    if(day <= 9){
                        date = (month + 1) + "/0" + day + "/" + (year-2000);
                    }else{
                        date = (month + 1) + "/" + day + "/" + (year-2000);
                    }
                }
                calSelection = date;
                //This changes the bottom bar to what ever the selected date is if date is current
                //date Changes it to 'Today'
                if(date.equals(today)){
                    bottomDate.setText("Today");
                }else {
                    bottomDate.setText((month + 1) + "/" + day + "/" + (year - 2000));
                }
                //Checks if there is an event on the selected date
                if(!db.getEventByDate(date).isEmpty()) {
                    lvCalendar.setVisibility(view.VISIBLE);
                    noEvent.setVisibility(view.GONE);
                    mHomeworkAdapter = new EventAdapter(getApplicationContext(), R.layout.schedulerow,  db.getEventByDate(date));
                    if (mHomeworkAdapter != null) {
                        lvCalendar.setAdapter(mHomeworkAdapter);
                    }
                }else{
                    lvCalendar.setVisibility(view.GONE);
                    noEvent.setVisibility(view.VISIBLE);
                }
            }
        });

        //This is where the onclick happens for the list view.
        lvCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.v("Date:", bottomDate.getText().toString());
                Event event = db.getEventByDate(calSelection).get(position);
                //Toast.makeText(getApplicationContext(), event.get_id()+"", Toast.LENGTH_SHORT).show();
                showMessage(event.getDate(), event);
            }
        });

        //For Animations
        calBottomRL = (RelativeLayout) findViewById(R.id.calBottomRL);
        expandListView = (ImageButton) findViewById(R.id.expandListView);
        collapseListView = (ImageButton) findViewById(R.id.collapseListView);
        rlSchedule = (RelativeLayout) findViewById(R.id.rlSchedule);
        animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        animMoveUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_slide_out_top);
        animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_slide_in_top);
        animTest = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movedown);
        bottomDate.setOnClickListener(new View.OnClickListener() {
            boolean up = false;
            @Override
            public void onClick(View v) {
                if(up == false){
                    rlSchedule.startAnimation(animSlideUp);
                    //calendarView.startAnimation(animSlideDown);
                    calendarView.setVisibility(v.GONE);
                    collapseListView.setVisibility(v.VISIBLE);
                    expandListView.setVisibility(v.GONE);
                    up = true;
                }else{
                    calendarView.startAnimation(animSlideDown);
                    calendarView.setVisibility(v.VISIBLE);
                    calBottomRL.startAnimation(animSlideDown);
                    collapseListView.setVisibility(v.GONE);
                    expandListView.setVisibility(v.VISIBLE);
                    up = false;
                }
            }
        });

        fabSchedule = (ImageButton) findViewById(R.id.fab_schedule);

        fabSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddEvent.class);
                i.putExtra("date", calSelection);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_today, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
                finish();
                return true;
            case R.id.goToToday:
                calendarView.setDate(System.currentTimeMillis());

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMessage(String title, final Event event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(event.getName()+ "\nType: "
                + event.getType() + "\nTime: "
                + event.getTime() + "\nNote:"
                + event.getNote());
        builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), AddEvent.class);
                intent.putExtra("Event", event.get_id());
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), event.get_id()+"", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Does nothing but close the dialog box
            }
        });
        builder.show();
    }
}
