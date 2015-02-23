package com.sleepypirate.athletemanager.schedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sleepypirate.athletemanager.Databases.EventsDataSource;
import com.sleepypirate.athletemanager.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the schedule activity. It is where you will be able to view your classes, on a calendar
 * and view what your up and coming assignments are.
 * Plan to link it to google calendar and be able to add classes, homework, etc. directly to your
 * google calendar so it is not just device specific.
 */
public class ScheduleActivity extends Activity {

    ListView lvCalendar;
    ArrayAdapter mHomeworkAdapter;
    CalendarView calendarView;
    ImageButton expandListView;
    ImageButton collapseListView;
    RelativeLayout calBottomRL;
    Animation animSlideDown;
    Animation animSlideUp;
    Animation animMoveUp;
    Animation animTest;
    TextView bottomDate;
    RelativeLayout rlSchedule;


    EventsDataSource db;
    private String[] arCalendarEvents = {
            "Calc: Book Problems ",
            "Ethics: Reading",
            "Religion: Test Tomorrow",
            "Religion: Reading",
            "Networking: Program"

    };
    private String[] arCalendarEvents2 = {
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

        bottomDate = (TextView) findViewById(R.id.bottomDate);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                String date;
                //The date is passed as #/##/## this makes it so it is passed as ##/##/## so
                //The db will be able to query
                if(month < 9){
                    date = "0"+(month + 1) + "/" + day + "/" + (year-2000);
                }else{
                    date = (month + 1) + "/" + day + "/" + (year-2000);
                }
                //This changes the bottom bar to what ever the selected date is
                bottomDate.setText((month + 1) + "/" + day + "/" + (year-2000));

                //db.getEventByDate((month + 1) + "/" + day + "/" + (year-2000));

                //Checks if there is an event on the selected date
                if(!db.getEventByDate(date).isEmpty()) {
                    //Toast.makeText(getApplicationContext(), db.getEventByDate(date).toString(), Toast.LENGTH_LONG).show();
                    /*showMessage("Events for " + date, "Read pages 1-10 Stephenson\n" +
                            "Reading\n" +
                            date + "\n" +
                            "   This is where the note goes");//db.getEventByDate(date).toString());
                    */
                    mHomeworkAdapter = new EventAdapter(getApplicationContext(), R.layout.schedulerow,  db.getEventByDate(date));
                    if (mHomeworkAdapter != null) {
                        lvCalendar.setAdapter(mHomeworkAdapter);
                    }
                }
                //Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();



                /*if (mHomeworkAdapter != null) {
                    lvCalendar.setAdapter(mHomeworkAdapter);
                }*/
                //if()
            }
        });

        //mHomeworkAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arCalendarEvents);





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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lift, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.addItem:
                animTest = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_fade_in);

                Intent i = new Intent(getApplicationContext(), AddEvent.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
