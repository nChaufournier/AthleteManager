package com.sleepypirate.athletemanager.lifting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import android.widget.Toast;

import com.sleepypirate.athletemanager.Databases.ExerciseDataSource;
import com.sleepypirate.athletemanager.MainActivity;
import com.sleepypirate.athletemanager.R;

import org.w3c.dom.Text;

import java.net.FileNameMap;
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
        getActionBar().setDisplayHomeAsUpEnabled(true);

        woName = (EditText) findViewById(R.id.woName);
        woEx1 = (EditText) findViewById(R.id.woEx1);
        woEx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.popup, null, false), 500, 500, true);
                pw.showAtLocation(v.findViewById(R.id.woEx1), Gravity.CENTER, 0, 0);
*/
                showMessage();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMessage()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exercises");
        builder.setItems(R.array.exercise_type, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int position){
                if(position == 1){
                    Toast.makeText(getApplicationContext(), dialog.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        builder.show();

    }
}
