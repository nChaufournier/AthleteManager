package com.sleepypirate.athletemanager.lifting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sleepypirate.athletemanager.Databases.ExerciseDataSource;
import com.sleepypirate.athletemanager.R;

import java.sql.SQLException;

/**
 * Created by Nic on 3/1/2015.
 */
public class AddExercise extends Activity {
    private EditText etExName;
    private EditText spExType;
    private Spinner spExRepTime;
    private EditText etExNote;
    private Button btnView;

    private ExerciseDataSource db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_creation);
        db = new ExerciseDataSource(this);
        try{
            db.open();
        }catch (SQLException e){
            e.printStackTrace();
        }

        getActionBar().setDisplayHomeAsUpEnabled(true);
        etExName = (EditText) findViewById(R.id.etExName);
        spExType = (EditText) findViewById(R.id.spExType);
        //R.array.exercise_catagory
        /*ArrayAdapter<CharSequence> ddExType = ArrayAdapter.createFromResource(this,
                R.array.exercise_catagory, R.layout.test_activity);

        spExType.setAdapter(ddExType);*/
        spExType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListMessage("Title");
            }
        });
        spExRepTime = (Spinner) findViewById(R.id.spRepTime);
        ArrayAdapter<CharSequence> ddExRep = ArrayAdapter.createFromResource(this,
                R.array.reps_time, R.layout.test_activity);
        spExRepTime.setAdapter(ddExRep);
        etExNote = (EditText) findViewById(R.id.etExNote);

        btnView = (Button) findViewById(R.id.btnViewRecords);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("All Exercises", db.getAllExercises().toString());
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
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.mbtnSave:
                if(etExName != null){
                    Exercise newExercise = new Exercise();
                    newExercise.setExerciseName(etExName.getText().toString());
                    newExercise.setExerciseType(spExType.getText().toString());
                    newExercise.setExerciseRepTime(spExRepTime.getSelectedItem().toString());
                    newExercise.setExNote(etExNote.getText().toString());
                    db.createExercise(newExercise);
                }
                else{
                    showMessage("Error", "Something Went Wrong Try Again");
                }

                Intent i = new Intent(getApplicationContext(), LiftActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Exercise Created", Toast.LENGTH_SHORT).show();


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

    public void showListMessage(String title)
    {
        //Exercise exercises[] = db.getAllExercises().toArray();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.test_activity, null);
        builder.setView(convertView);
        builder.setCancelable(true);
        builder.setTitle(title);

        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
        ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(this, android.R.layout.simple_list_item_1, db.getAllExercises());
        lv.setAdapter(adapter);
        //builder.setMessage(message);
        builder.show();
    }
}
