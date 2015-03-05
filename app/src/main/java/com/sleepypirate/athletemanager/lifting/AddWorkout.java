package com.sleepypirate.athletemanager.lifting;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sleepypirate.athletemanager.Databases.ExerciseDataSource;
import com.sleepypirate.athletemanager.MainActivity;
import com.sleepypirate.athletemanager.R;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is where workouts will be created
 */
public class AddWorkout extends Activity {
    private EditText woName;
    private EditText woEx1;
    private EditText woEx2;
    private EditText woEx3;
    ImageButton woFabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        woFabAdd = (ImageButton) findViewById(R.id.fab_exAdd);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
