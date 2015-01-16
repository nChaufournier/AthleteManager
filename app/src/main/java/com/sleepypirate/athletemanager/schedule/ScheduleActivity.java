package com.sleepypirate.athletemanager.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.sleepypirate.athletemanager.R;

import java.util.List;

/**
 * This is the schedule activity. It is where you will be able to view your classes, on a calendar
 * and view what your up and coming assignments are.
 * Plan to link it to google calendar and be able to add classes, homework, etc. directly to your
 * google calendar so it is not just device specific.
 */
public class ScheduleActivity extends Activity{

    ListView lvCalendar;
    ArrayAdapter mHomeworkAdapter;
    CalendarView calendarView;
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

        mHomeworkAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arCalendarEvents);
        lvCalendar = (ListView)findViewById(R.id.lvCalendar);
        calendarView = (CalendarView) findViewById(R.id.cvScheduleCalendar);
        if(mHomeworkAdapter != null){
            lvCalendar.setAdapter(mHomeworkAdapter);
        }

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(getApplicationContext(), month+1 + "/" + day + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

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
}
