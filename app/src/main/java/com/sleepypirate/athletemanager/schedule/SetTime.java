package com.sleepypirate.athletemanager.schedule;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

/**
 * This is for a time editText. It will display a time picker when tapped and allow you to select
 * the time with a spinner
 */
public class SetTime implements View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener{

    private EditText editText;
    private Calendar myCalendar;

    public SetTime(EditText editText, Context ctx) {
        this.editText = editText;
        this.editText.setOnFocusChangeListener(this);
        myCalendar= Calendar.getInstance();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);
            new TimePickerDialog(v.getContext(), this, hour, minute, true).show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        editText.setText(hourOfDay+":"+String.format("%02d", minute));
        Toast.makeText(view.getContext(), hourOfDay+":"+String.format("%02d", minute), Toast.LENGTH_SHORT).show();
    }


}
