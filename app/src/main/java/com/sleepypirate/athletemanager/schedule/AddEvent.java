package com.sleepypirate.athletemanager.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sleepypirate.athletemanager.R;

/**
 * AddEvents from this page
 */
public class AddEvent extends Activity{
    Spinner typeSpinner;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creation);
        //Used for Home Button
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Attach items to the button
        name = (EditText) findViewById(R.id.etEventName);
        typeSpinner = (Spinner) findViewById(R.id.spinType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.event_type, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply yhe adapter to the spinner
        typeSpinner.setAdapter(adapter);

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
