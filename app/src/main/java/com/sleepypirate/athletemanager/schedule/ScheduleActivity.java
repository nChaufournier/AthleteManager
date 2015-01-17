package com.sleepypirate.athletemanager.schedule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.sleepypirate.athletemanager.R;

import org.w3c.dom.Text;

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
    ImageButton expandListView;
    ImageButton collapseListView;
    RelativeLayout calBottomRL;
    Animation animSlideDown;
    Animation animSlideUp;
    Animation animMoveUp;
    Animation animTest;
    SQLiteDatabase db;
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
                Toast.makeText(getApplicationContext(), month + 1 + "/" + day + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });
        calBottomRL = (RelativeLayout) findViewById(R.id.calBottomRL);
        expandListView = (ImageButton) findViewById(R.id.expandListView);
        collapseListView = (ImageButton) findViewById(R.id.collapseListView);


        animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        animMoveUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_slide_out_top);
        expandListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendarView.startAnimation(animMoveUp);
                calBottomRL.startAnimation(animSlideUp);
                calendarView.setVisibility(v.GONE);
                collapseListView.setVisibility(v.VISIBLE);
                expandListView.setVisibility(v.GONE);
            }
        });
        animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_slide_in_top);
        animTest = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movedown);
        collapseListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.startAnimation(animSlideDown);
                calendarView.setVisibility(v.VISIBLE);
                calBottomRL.startAnimation(animSlideDown);
                collapseListView.setVisibility(v.GONE);
                expandListView.setVisibility(v.VISIBLE);

            }
        });


        //Database Information
        //db = openOrCreateDatabase("ScheduleDB", Context.MODE_PRIVATE, null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS schedule(key INT, eventName VARCHAR, type VARCHAR, date DATE, note VARCHAR");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lift, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
}
