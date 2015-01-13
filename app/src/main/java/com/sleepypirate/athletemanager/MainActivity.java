package com.sleepypirate.athletemanager;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
    Button btnLifts;
    Button btnFood;
    ImageButton btnSchedule;
    ListView lvUpdates;
    String[] recentAct = {
            "TODAY",
            "Workouts: 1hr",
            "Homework: 3 Due",
            "Calories: 1200",
            "Team Meeting: Friday 6pm Study Room",
            "Practice Tuesday 6:30pm Aux Gym",
            "YESTERDAY",
            "Workouts: 3hrs",
            "Calories: 2600",
            "Homework: 1 Due",
            "Practice: 6:30"

    };
    private ArrayAdapter mArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLifts = (Button) findViewById(R.id.btnLifts);
        btnFood = (Button) findViewById(R.id.btnFood);
        btnSchedule = (ImageButton) findViewById(R.id.btnSchedule);
        lvUpdates = (ListView) findViewById(R.id.lvUpdates);

        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recentAct);
        if(lvUpdates !=null){
            lvUpdates.setAdapter(mArrayAdapter);
        }
        btnLifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LiftActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
